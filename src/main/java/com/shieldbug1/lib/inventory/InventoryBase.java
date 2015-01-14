package com.shieldbug1.lib.inventory;

import static com.google.common.base.Strings.isNullOrEmpty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class InventoryBase implements IInventory //TODO javadoc
{
	private final String customName;
	private final int stackSizeLimit;
	private final MarkedDirtyListener listener;
	
	protected InventoryBase(String customName, int stackSizeLimit, MarkedDirtyListener listener)
	{
		this.customName = customName;
		this.stackSizeLimit = stackSizeLimit;
		this.listener = listener;
	}
	
	@Override
	public String getName()
	{
		return this.customName;
	}

	@Override
	public boolean hasCustomName()
	{
		return !isNullOrEmpty(this.getName());
	}

	@Override
	public IChatComponent getDisplayName()
	{
		return new ChatComponentText(this.getName());
	}

	@Override
	public int getInventoryStackLimit()
	{
		return this.stackSizeLimit;
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		ItemStack selected = this.getStackInSlot(index);
		if(selected != null)
		{
			if(selected.stackSize <= count)
			{
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return selected;
			}
			else
			{
				ItemStack ret = selected.splitStack(count);
				if(selected.stackSize == 0)
				{
					this.setInventorySlotContents(index, null);
				}
				this.markDirty();
				return ret;
			}
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		ItemStack stack = this.getStackInSlot(index);
		if(stack != null)
		{
			this.setInventorySlotContents(index, null);
			return stack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player)	{	}

	@Override
	public void closeInventory(EntityPlayer player)	{	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return true;
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)	{	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}
	
	@Override
	public void markDirty()
	{
		this.listener.markDirty();
	}
}
