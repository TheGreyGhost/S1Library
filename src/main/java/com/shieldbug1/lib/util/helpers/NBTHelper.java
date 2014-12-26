package com.shieldbug1.lib.util.helpers;

import java.util.UUID;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;

/**
 * A class to help with using the Named Binary Tag (NBT) classes.
 */
public final class NBTHelper
{	
	/**
	 * A simple class to allow for use of generics with helper methods
	 * {@link #set(NBTTagCompound, String, Object)} an {@link #get(NBTTagCompound, String)}.
	 * 
	 * Alternate way: Enum of data types with a switch.
	 */
	public static abstract class DataType<T>
	{
		public static final DataType<NBTBase> TAG = new DataType<NBTBase>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, NBTBase value)
					{
						compound.setTag(key, value);
					}

					@Override
					public NBTBase get(NBTTagCompound compound, String key)
					{
						return compound.getTag(key);
					}
				};
		public static final DataType<Byte> BYTE = new DataType<Byte>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, Byte value)
					{
						compound.setByte(key, value);
					}

					@Override
					public Byte get(NBTTagCompound compound, String key)
					{
						return compound.getByte(key);
					}
				};
		public static final DataType<Short> SHORT = new DataType<Short>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, Short value)
					{
						compound.setShort(key, value);
					}

					@Override
					public Short get(NBTTagCompound compound, String key)
					{
						return compound.getShort(key);
					}
				};
		public static final DataType<Integer> INTEGER = new DataType<Integer>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, Integer value)
					{
						compound.setInteger(key, value);
					}
		
					@Override
					public Integer get(NBTTagCompound compound, String key)
					{
						return compound.getInteger(key);
					}
				};
		public static final DataType<Long> LONG = new DataType<Long>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, Long value)
					{
						compound.setLong(key, value);
					}
		
					@Override
					public Long get(NBTTagCompound compound, String key)
					{
						return compound.getLong(key);
					}
				};
		public static final DataType<Float> FLOAT = new DataType<Float>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, Float value)
					{
						compound.setFloat(key, value);
					}
		
					@Override
					public Float get(NBTTagCompound compound, String key)
					{
						return compound.getFloat(key);
					}
				};
		public static final DataType<Double> DOUBLE = new DataType<Double>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, Double value)
					{
						compound.setDouble(key, value);
					}

					@Override
					public Double get(NBTTagCompound compound, String key)
					{
						return compound.getDouble(key);
					}
				};
		public static final DataType<String> STRING = new DataType<String>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, String value)
					{
						compound.setString(key, value);
					}
		
					@Override
					public String get(NBTTagCompound compound, String key)
					{
						return compound.getString(key);
					}
				};
		public static final DataType<byte[]> BYTE_ARRAY = new DataType<byte[]>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, byte[] value)
					{
						compound.setByteArray(key, value);
					}
		
					@Override
					public byte[] get(NBTTagCompound compound, String key)
					{
						return compound.getByteArray(key);
					}
				};
		public static final DataType<int[]> INT_ARRAY = new DataType<int[]>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, int[] value)
					{
						compound.setIntArray(key, value);
					}
		
					@Override
					public int[] get(NBTTagCompound compound, String key)
					{
						return compound.getIntArray(key);
					}
				};
		public static final DataType<Boolean> BOOLEAN = new DataType<Boolean>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, Boolean value)
					{
						compound.setBoolean(key, value);
					}
						
					@Override
					public Boolean get(NBTTagCompound compound, String key)
					{
						return compound.getBoolean(key);
					}
				};
		public static final DataType<NBTTagCompound> COMPOUND_TAG = new DataType<NBTTagCompound>()
				{
					@Override
					public void set(NBTTagCompound compound, String key, NBTTagCompound value)
					{
						compound.setTag(key, value);
					}
						
					@Override
					public NBTTagCompound get(NBTTagCompound compound, String key)
					{
						return compound.getCompoundTag(key);
					}
				};
		private DataType()
		{
			
		}
		
		public abstract void set(NBTTagCompound compound, String key, T value);
		public abstract T get(NBTTagCompound compound, String key);
	}
	
	private NBTHelper(){}
	
	/**
	 * Creates an NBTTagCompound containing information about an ItemStack.
	 * @param stack - stack to write to NBT.
	 * @return the NBTTagCompound corresponding to the itemstack.
	 */
	public static NBTTagCompound loadItemStackToNBT(ItemStack stack)
	{
		NBTTagCompound compound = new NBTTagCompound();
		stack.writeToNBT(compound);
		
		return compound;
	}
	
	/**
	 * Recreates an ItemStack from the given NBTTagCompound.
	 * @param compound - The compound to get the ItemStack from.
	 * @return the ItemStack that was created.
	 */
	public static ItemStack loadItemStackFromNBT(NBTTagCompound compound)
	{
		return ItemStack.loadItemStackFromNBT(compound);
	}
	
	/**
	 * @param uuid - the UUID to get the NBTTagCompound for.
	 * @return the NBTTagCompound from the UUID.
	 */
	public static NBTTagCompound getTagFromUUID(UUID uuid)
	{
		NBTTagCompound compound = new NBTTagCompound();
		compound.setLong("mSigBits", uuid.getMostSignificantBits());
		compound.setLong("lSigBits", uuid.getLeastSignificantBits());
		return compound;
	}
	
	/**
	 * @param compound - the compound to get the UUID from.
	 * @return a UUID from the given NBTTagCompound as saved in {@link #getTagFromUUID(UUID)}.
	 */
	public static UUID getUUIDFromTag(NBTTagCompound compound)
	{
		return new UUID(compound.getLong("mSigBits"), compound.getLong("lSigBits"));
	}
	
	/**
	 * A safe method for getting an ItemStack's stackTagCompound. This method will never return null.
	 * @param stack - the stack to get the Tag for.
	 * @return the NBTTagCompound belonging to this ItemStack.<br><b>Never null.</b></br>
	 */
	public static NBTTagCompound getItemStackNBTTagCompound(ItemStack stack)
	{
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		return stack.getTagCompound();
	}
	
	/**
	 * Sets a value in a non-null NBTTagCompound.
	 * @param type - the type of data to set.
	 * @param compound - the NBTTagCompound to write to.
	 * @param key - the key to write to.
	 * @param value - the value to set.
	 */
	public static <T> void set(DataType<T> type, NBTTagCompound compound, String key,  T value)
	{
		type.set(compound, key, value);
	}
	
	/**
	 * Gets a value from a non-null NBTTagCompound.
	 * @param type - the type of data to get.
	 * @param compound - the NBTTagCompound to get from.
	 * @param key - the key to read from.
	 */
	public static <T> T get(DataType<T> type, NBTTagCompound compound, String key)
	{
		return type.get(compound, key);
	}
}
