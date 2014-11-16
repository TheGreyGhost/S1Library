package com.shieldbug1.lib.area;

import java.lang.reflect.Type;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.*;
public final class WorldPosition
{
	private static final Gson serialiser = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(WorldPosition.class, Serialiser.INSTANCE).create();
	
	public final int x, y, z, dimID; // Using dimension ids, so that the world object never gets leaked.
	private Validator validator; //TODO make this work better, with a way of saving validators to nbt as well?
	
	public WorldPosition(World world, int x, int y, int z)
	{
		this(world, x, y, z, null);
	}
	
	public WorldPosition(World world, int x, int y, int z, Validator validator)
	{
		this(world.provider.dimensionId, x, y, z, validator);
	}
	
	public WorldPosition(int dimID, int x, int y, int z, Validator validator)
	{
		this.dimID = dimID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.validator = validator;
	}
	
	public World getWorld()
	{
		return DimensionManager.getWorld(this.dimID);
	}
	
	public TileEntity getTileEntity()
	{
		return this.getWorld().getTileEntity(x, y, z);
	}
	
	public Block getBlock()
	{
		return this.getWorld().getBlock(x, y, z);
	}
	
	public int getMetadata()
	{
		return this.getWorld().getBlockMetadata(x, y, z);
	}
	
	public int getDimensionID()
	{
		return this.dimID;
	}
	
	public boolean isPositionValid()
	{
		return validator != null ? validator.validate(this.getWorld(), x, y, z) : true;
	}
	
	public AxisAlignedBB toAABB()
	{
		return AxisAlignedBB.getBoundingBox(this.x, this.y, this.z, this.x+1, this.y+1, this.z+1);
	}
	
	public WorldPosition moveInDirection(ForgeDirection direction)
	{
		return new WorldPosition(this.dimID, this.x + direction.offsetX, this.y + direction.offsetY, this.z + direction.offsetZ, null);
	}
	
	public void writeToNBT(String key, NBTTagCompound compound)
	{
		NBTTagCompound position = new NBTTagCompound();
		position.setInteger("xPos", this.x);
		position.setInteger("yPos", this.y);
		position.setInteger("zPos", this.z);
		position.setInteger("dimID", this.dimID);
		compound.setTag(key, position);
	}
	
	public static WorldPosition readFromNBT(String key, NBTTagCompound compound, Validator validator)
	{
		NBTTagCompound position = (NBTTagCompound) compound.getTag(key);
		return new WorldPosition(position.getInteger("dimID"), position.getInteger("xPos"), position.getInteger("yPos"), position.getInteger("zPos"), validator);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof WorldPosition))
		{
			return false;
		}
		WorldPosition other = (WorldPosition) o;
		return new EqualsBuilder()
		.append(this.x, other.x)
		.append(this.y, other.y)
		.append(this.z, other.z)
		.append(this.dimID, other.dimID)
		.build();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(13, 37)
		.append(this.x)
		.append(this.y)
		.append(this.z)
		.append(this.dimID)
		.build();
	}
	
	@Override
	public String toString()
	{
		return this.toJson();
	}
	
	public String toJson()
	{
		return serialiser.toJson(this, WorldPosition.class);
	}
	
	public static WorldPosition fromJson(String json)
	{
		return serialiser.fromJson(json, WorldPosition.class);
	}

	private static enum Serialiser implements JsonSerializer<WorldPosition>, JsonDeserializer<WorldPosition>
	{
		INSTANCE;
		
		@Override
		public WorldPosition deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
		{
			JsonObject object = (JsonObject) json;
			return new WorldPosition(DimensionManager.getWorld(object.get("dimID").getAsInt()), object.get("xPos").getAsInt(), object.get("yPos").getAsInt(), object.get("zPos").getAsInt());
		}

		@Override
		public JsonElement serialize(WorldPosition src, Type typeOfSrc,JsonSerializationContext context)
		{
			JsonObject positionObj = new JsonObject();
			
			positionObj.addProperty("xPos", src.x);
			positionObj.addProperty("yPos", src.y);
			positionObj.addProperty("zPos", src.z);
			positionObj.addProperty("dimID", src.dimID);
			
			return positionObj;
		}
		
	}
	
	/** A simple interface to allow validation of locations to certain conditions. */
//XXX JAVA 8	@FunctionalInterface
	public static abstract interface Validator
	{
		/** @return true if condition is met. */
		public abstract boolean validate(World world, int x, int y, int z);
		
		public static class BlockValidator implements Validator
		{
			private final Block block;
			
			public BlockValidator(Block block)
			{
				this.block = block;
			}
			
			@Override
			public boolean validate(World world, int x, int y, int z)
			{
				return this.block == world.getBlock(x, y, z);
			}
		}
		
		public static class TileEntityValidator implements Validator
		{
			private final Class<? extends TileEntity> tileEntityClass;
			
			public TileEntityValidator(Class<? extends TileEntity> clazz)
			{
				this.tileEntityClass = clazz;
			}
			
			@Override
			public boolean validate(World world, int x, int y, int z)
			{
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				
				return tileEntity != null ? this.tileEntityClass.isAssignableFrom(tileEntity.getClass()) : this.tileEntityClass == null;
			}
		}
	}
}
