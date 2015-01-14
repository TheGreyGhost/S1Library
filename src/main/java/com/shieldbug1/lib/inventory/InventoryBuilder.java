package com.shieldbug1.lib.inventory;

import static com.shieldbug1.lib.inventory.MarkedDirtyListener.NONE;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.builder.Builder;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;

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
	
	public InventoryBuilder setSupplier(Supplier<? extends List<ItemStack>> supplier)
	{
		throw new UnsupportedOperationException();
	}
	
	public IInventory build()
	{
		return new DefaultInventory(this.name, this.stackSizeLimit, this.listener, this.size);
	}
	
	public class InfiniteInventoryBuilder extends InventoryBuilder
	{
		private Supplier<? extends List<ItemStack>> supplier = Suppliers.ofInstance(Lists.<ItemStack>newArrayList());//XXX JAVA 8
		
		@Override
		public InventoryBuilder setSize(int size)
		{
			throw new UnsupportedOperationException();
		}
		
		public InventoryBuilder setSupplier(Supplier<? extends List<ItemStack>> supplier)
		{
			this.supplier = supplier;
			return this;
		}
		
		@Override
		public IInventory build()
		{
			return new InfiniteInventory(InventoryBuilder.this.name, InventoryBuilder.this.stackSizeLimit, InventoryBuilder.this.listener, this.supplier);
		}
	}
}
