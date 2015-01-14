package com.shieldbug1.lib.inventory;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.google.common.base.Supplier;

public class InfiniteInventory extends InventoryBase //TODO javadoc
{
	private final List<ItemStack> inventory;
	
	public InfiniteInventory(String customName, int stackSizeLimit, MarkedDirtyListener listener, Supplier<? extends List<ItemStack>> supplier)
	{
		super(customName, stackSizeLimit, listener);
		this.inventory = supplier.get();
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.size();
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return index < this.getSizeInventory() && index > 0 ? this.inventory.get(index) : null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		if(index < this.getSizeInventory() && index > 0)
		{
			this.inventory.set(index, stack);
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
		this.inventory.clear();
		this.markDirty();
	}

	@Override
	public ItemStack[] toArray()
	{
		return (ItemStack[]) this.inventory.toArray();
	}

	@Override
	public Iterator<ItemStack> iterator()
	{
		return this.inventory.iterator();
	}
}
