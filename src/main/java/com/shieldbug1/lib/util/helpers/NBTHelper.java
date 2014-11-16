package com.shieldbug1.lib.util.helpers;

import java.util.UUID;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class NBTHelper
{
	private NBTHelper(){}
	
	/**
	 * Sets a tag to another tag.
	 * @param compound - the tag to append <i>to.<i>
	 * @param key - the key to put the tag under.
	 * @param value - the tag to put in the key.
	 */
	public static void setTag(NBTTagCompound compound, String key, NBTTagCompound value)
	{
		compound.setTag(key, compound);
	}
	
	/**
	 * Retrieves a tag from another tag.
	 * @param compound - tag to get from.
	 * @param key - key to get tag from.
	 * @return the tag if there is one, otherwise it returns a new NBTTagCompound.
	 */
	public static NBTTagCompound getTag(NBTTagCompound compound, String key)
	{
		return compound.getCompoundTag(key);
	}
	
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
		if(stack.stackTagCompound == null)
		{
			stack.stackTagCompound = new NBTTagCompound();
		}
		return stack.stackTagCompound;
	}
}
