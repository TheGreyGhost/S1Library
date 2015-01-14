package com.shieldbug1.lib.inventory;

import static com.shieldbug1.lib.inventory.MarkedDirtyListener.NONE;
import net.minecraft.inventory.IInventory;

import org.apache.commons.lang3.builder.Builder;

public class InventoryBuilder implements Builder<IInventory> //TODO javadoc
{
	private String name;
	private int stackSizeLimit = 64; //default
	private MarkedDirtyListener listener = NONE;
	private int size;
	
	public InventoryBuilder setName(String customName)
	{
		this.name = customName;
		return this;
	}
	
	public InventoryBuilder setInfinite()
	{
		return new InfiniteInventoryBuilder();
	}
	
	public InventoryBuilder setStackSizeLimit(int stackSizeLimit)
	{
		this.stackSizeLimit = stackSizeLimit;
		return this;
	}
	
	public InventoryBuilder setDirtyListener(MarkedDirtyListener listener)
	{
		this.listener = listener;
		return this;
	}
	
	public InventoryBuilder setSize(int size)
	{
		this.size = size;
		return this;
	}
	
	public IInventory build()
	{
		return new DefaultInventory(this.name, this.stackSizeLimit, this.listener, this.size);
	}
	
	private class InfiniteInventoryBuilder extends InventoryBuilder
	{
		@Override
		public InventoryBuilder setSize(int size)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public IInventory build()
		{
			return new InfiniteInventory(InventoryBuilder.this.name, InventoryBuilder.this.stackSizeLimit, InventoryBuilder.this.listener);
		}
	}
}
