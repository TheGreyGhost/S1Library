package com.shieldbug1.lib.inventory;

import java.util.*;

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
		this.markDirty();
	}

	@Override
	public ItemStack[] toArray()
	{
		return Arrays.copyOf(this.inventory, this.size);
	}
	
	
	/* Pure copy of ArrayList#iterator */
	@Override
	public Iterator<ItemStack> iterator()
	{
		return new Itr();
	}
	
	private class Itr implements Iterator<ItemStack>
	{
		int cursor = 0;
		int lastRet = -1;
		int expectedModCount = modCount;
		
		@Override
		public boolean hasNext()
		{
			return cursor != size;
		}

		@Override
		public ItemStack next()
		{
			checkForComod();
			int i = cursor;
			if(i >= size)
			{
				throw new NoSuchElementException();
			}
			cursor = i + 1;
			return inventory[lastRet = i];
		}

		@Override
		public void remove()
		{
			if(lastRet < 0)
			{
				throw new IllegalStateException();
			}
			checkForComod();
			DefaultInventory.this.setInventorySlotContents(lastRet, null);
			cursor = lastRet;
			lastRet = -1;
			expectedModCount = modCount;
		}
		
		private void checkForComod()
		{
			if(modCount != expectedModCount)
			{
				throw new ConcurrentModificationException();
			}
		}
		
	}
}
