package com.shieldbug1.lib.inventory;

import net.minecraft.item.ItemStack;

public class DefaultInventory extends InventoryBase //TODO javadoc
{
	private final ItemStack[] inventory;
	private final int size;
	
	public DefaultInventory(String customName, int stackSizeLimit, MarkedDirtyListener listener, int size)
	{
		super(customName, stackSizeLimit, listener);
		this.inventory = new ItemStack[size];
		this.size = size;
	}

	@Override
	public int getSizeInventory()
	{
		return this.size;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return index < this.size && index > 0 ? this.inventory[index] : null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		if(index < this.size && index > 0)
		{
			this.inventory[index] = stack;
			if(stack != null && stack.stackSize > this.getInventoryStackLimit())
			{
				stack.stackSize = this.getInventoryStackLimit();
			}
			this.markDirty();
		}
	}

	@Override
	public void clear()
	{
		for(int i = 0; i < this.size; i++)
		{
			this.inventory[i] = null;
		}
	}
}
