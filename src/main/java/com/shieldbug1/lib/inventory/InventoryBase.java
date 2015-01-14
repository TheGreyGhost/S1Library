package com.shieldbug1.lib.inventory;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.shieldbug1.lib.util.helpers.ItemHelper.areItemsEqualWithMetadataAndNBT;

import java.util.AbstractCollection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class InventoryBase extends AbstractCollection<ItemStack> implements IInventory //TODO javadoc
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
				return selected;
			}
			else
			{
				ItemStack ret = selected.splitStack(count);
				if(selected.stackSize == 0)
				{
					this.setInventorySlotContents(index, null);
				}
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
	
	protected transient int modCount = 0;
	
	@Override
	public void markDirty()
	{
		this.modCount++;
		this.listener.markDirty();
	}
	
	public abstract ItemStack[] toArray();

	@Override
	public int size()
	{
		return this.getSizeInventory();
	}

	@Override
	public boolean isEmpty()
	{
		for(ItemStack stack : this)
		{
			if(stack != null)
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean contains(Object o)
	{
		if(o == null)
		{
			for(ItemStack stack : this)
			{
				if(stack == null)
				{
					return true;
				}
			}
			return false;
		}
		if(o instanceof ItemStack)
		{
			ItemStack other = (ItemStack) o;
			for(ItemStack stack : this)
			{
				if(areItemsEqualWithMetadataAndNBT(other, stack))
				{
					return true;
				}
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean add(ItemStack e)
	{
		for(int i = 0; i < this.size(); i++)
		{
			ItemStack stack = this.getStackInSlot(i);
			if(stack == null)
			{
				this.setInventorySlotContents(i, e);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Object o)
	{
		if(o instanceof ItemStack)
		{
			ItemStack other = (ItemStack) o;
			for(int i = 0; i < this.size(); i++)
			{
				ItemStack stack = this.getStackInSlot(i);
				if(areItemsEqualWithMetadataAndNBT(other, stack))
				{
					this.setInventorySlotContents(i, null);
					return true;
				}
			}
		}
		return false;
	}
}
