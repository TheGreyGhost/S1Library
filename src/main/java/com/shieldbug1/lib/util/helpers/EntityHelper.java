package com.shieldbug1.lib.util.helpers;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import com.google.common.collect.Lists;
import com.shieldbug1.lib.util.Sides;

public final class EntityHelper
{
	private EntityHelper(){}
	
	/**
	 * @param world - the world to get the entities from.
	 * @return all the loaded entities in the given world.
	 * @see World#loadedEntityList
	 */
	public static List<Entity> getLoadedEntities(World world)
	{
		return world.loadedEntityList;
	}
	
	/**
	 * A convenience method that uses {@link List#forEach(java.util.function.Consumer)} in order to create another list from World#loadedEntityList.
	 * Note that this method uses {@link Class#isInstance(Object)} rather than {@link Class#equals(Object)}.
	 * @param entityClass - the type of entity the list should have.
	 * @param world - the world to get the entities for.
	 * @return all loaded entities of the given type.
	 */
	public static <T extends Entity> List<T> getLoadedEntitiesOfType(Class<T> entityClass, World world)
	{
		List<T> list = Lists.newArrayList();
		for(Entity entity : getLoadedEntities(world))
		{
			if(entityClass.isInstance(entity))
			{
				list.add((T) entity);
			}
		}//XXX JAVA 8 List#forEach
		return list;
	}
	
	/**
	 * A convenience method that uses {@link List#forEach(java.util.function.Consumer)} in order to create another list from World#loadedEntityList.
	 * Note that this method uses {@link Class#equals(Object)}.
	 * If you want to include any class that extends the given type, use {@link #getLoadedEntitiesOfType(Class, World)} instead.
	 * @param entityClass - the type of entity the list should have.
	 * @param world - the world to get the entities for.
	 * @return all loaded entities of the given type.
	 */
	public static <T extends Entity> List<T> getLoadedEntitiesOfTypeHard(Class<T> entityClass, World world)
	{
		List<T> ret = Lists.newArrayList();
		for(Entity entity : getLoadedEntities(world))
		{
			if(entity.getClass().equals(entityClass))
			{
				ret.add((T) entity);
			}
		}
		//XXX Java 8 List#forEach
		return ret;
	}
	
	/**
	 * Teleports the given entity to the given world at the given x, y, and z positions.
	 * @param entity - the entity to teleport.
	 * @param world - the world to teleport the entity to.
	 * @param x - the x position to teleport to.
	 * @param y - the y position to teleport to.
	 * @param z - the z position to teleport to.
	 * @return the new entity instance to use - the old entity is 'dead'.
	 */
	public static Entity teleportEntity(Entity entity, World world, double x, double y, double z)
	{
		WorldServer oldWorld = (WorldServer)entity.worldObj;
		WorldServer newWorld = (WorldServer)world;
		if(entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			if(Sides.logicalServer(player)) //Server
			{
				player.worldObj.theProfiler.startSection("changeDimension");
				ServerConfigurationManager config = player.mcServer.getConfigurationManager();
				player.closeScreen();
				player.dimension = newWorld.provider.getDimensionId();
				player.playerNetServerHandler.sendPacket(new S07PacketRespawn(player.dimension, player.worldObj.getDifficulty(), newWorld.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType()));
				oldWorld.removeEntity(player);
				player.isDead = false;
				player.setLocationAndAngles(x, y, z, player.rotationYaw, player.rotationPitch);
				newWorld.spawnEntityInWorld(player);
				player.setWorld(newWorld);
				config.func_72375_a(player, oldWorld);
				player.playerNetServerHandler.setPlayerLocation(x, y, z, entity.rotationYaw, entity.rotationPitch);
				player.theItemInWorldManager.setWorld(newWorld);
				config.updateTimeAndWeatherForPlayer(player, newWorld);
				config.syncPlayerInventory(player);
				player.worldObj.theProfiler.endSection();
				oldWorld.resetUpdateEntityTick();
				newWorld.resetUpdateEntityTick();
				player.worldObj.theProfiler.endSection();
				for(Iterator<PotionEffect> potionIterator = player.getActivePotionEffects().iterator(); potionIterator.hasNext(); )
				{
					player.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(player.getEntityId(), potionIterator.next()));
				}
				player.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
				FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, oldWorld.provider.getDimensionId(), player.dimension);
			}
			player.worldObj.theProfiler.endSection();
			return player;
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			entity.writeToNBTOptional(tag);
			entity.setDead();
			Entity teleportedEntity = EntityList.createEntityFromNBT(tag, newWorld);
			if(teleportedEntity != null)
			{
				teleportedEntity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
				teleportedEntity.forceSpawn = true;
				newWorld.spawnEntityInWorld(teleportedEntity);
				teleportedEntity.setWorld(newWorld);
			}
			oldWorld.resetUpdateEntityTick();
			newWorld.resetUpdateEntityTick();
			return teleportedEntity;
		}
	}
	
	/**
	 * @param entity - the entity to get the facing direction for.
	 * @return the ForgeDirection the entity is facing.
	 */
	public static EnumFacing getEntityFaceDirection(EntityLivingBase entity)
	{
		return entity.func_174811_aO();
	}
}
