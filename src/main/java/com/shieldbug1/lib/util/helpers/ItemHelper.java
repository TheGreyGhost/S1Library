package com.shieldbug1.lib.util.helpers;

import java.util.Comparator;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public final class ItemHelper
{
	private ItemHelper(){}
	
	private static final Comparator<ItemStack> ITEM_COMPARATOR = new Comparator<ItemStack>() //XXX JAVA 8
			{
		
		@Override
		public int compare(ItemStack o1, ItemStack o2)
		{
			if(o1 != null && o2 != null) //Are both non-null?
			{
				Item item1 = o1.getItem(), item2 = o2.getItem();
				int id1 = Item.getIdFromItem(item1), id2 = Item.getIdFromItem(item2);
				
				if(id1 == id2) //Same item id?
				{
					if(item1 == item2) //Same item?
					{
						return 0;
					}
					else
					{
						return item1.getUnlocalizedName(o1).compareToIgnoreCase(item2.getUnlocalizedName(o2)); //Same ID, different item, sorting by name.
					}
				}
				else
				{
					return id1 < id2 ? -1 : 1; //If the first comes before in ID, it goes first, otherwise it goes after.
				}
			}
			else if(o1 != null)
			{
				return -1; //First isn't null, so it must be before null!
			}
			else if(o2 != null)
			{
				return 1; //First is null, so it must be after non-null!
			}
			else
			{
				return 0; //Both null, same value!
			}
		}
			};
			
			private static final Comparator<ItemStack> DAMAGE_WILDCARD_COMPARATOR = new Comparator<ItemStack>() //XXX JAVA 8
					{
				
				@Override
				public int compare(ItemStack o1, ItemStack o2)
				{
					if(o1 != null && o2 != null) //Are both non-null?
					{
						Item item1 = o1.getItem(), item2 = o2.getItem();
						int id1 = Item.getIdFromItem(item1), id2 = Item.getIdFromItem(item2);
						
						if(id1 == id2) //Same item id?
						{
							if(item1 == item2) //Same item?
							{
								int meta1 = o1.getItemDamage(), meta2 = o2.getItemDamage();
								
								if(meta1 == meta2 || meta1 == OreDictionary.WILDCARD_VALUE)//Same damage?
								{
									return 0;
								}
								else
								{
									return meta1 < meta2 ? -1 : 1; //lower metadata goes first.
								}
							}
							else
							{
								return item1.getUnlocalizedName(o1).compareToIgnoreCase(item2.getUnlocalizedName(o2)); //Same ID, different item, sorting by name.
							}
						}
						else
						{
							return id1 < id2 ? -1 : 1; //If the first comes before in ID, it goes first, otherwise it goes after.
						}
					}
					else if(o1 != null)
					{
						return -1; //First isn't null, so it must be before null!
					}
					else if(o2 != null)
					{
						return 1; //First is null, so it must be after non-null!
					}
					else
					{
						return 0; //Both null, same value!
					}
				}
					};
	
	private static final Comparator<ItemStack> DAMAGE_COMPARATOR = new Comparator<ItemStack>() //XXX JAVA 8
			{
		
		@Override
		public int compare(ItemStack o1, ItemStack o2)
		{
			if(o1 != null && o2 != null) //Are both non-null?
			{
				Item item1 = o1.getItem(), item2 = o2.getItem();
				int id1 = Item.getIdFromItem(item1), id2 = Item.getIdFromItem(item2);
				
				if(id1 == id2) //Same item id?
				{
					if(item1 == item2) //Same item?
					{
						int meta1 = o1.getItemDamage(), meta2 = o2.getItemDamage();
						
						if(meta1 == meta2)//Same damage?
						{
							return 0;
						}
						else
						{
							return meta1 < meta2 ? -1 : 1; //lower metadata goes first.
						}
					}
					else
					{
						return item1.getUnlocalizedName(o1).compareToIgnoreCase(item2.getUnlocalizedName(o2)); //Same ID, different item, sorting by name.
					}
				}
				else
				{
					return id1 < id2 ? -1 : 1; //If the first comes before in ID, it goes first, otherwise it goes after.
				}
			}
			else if(o1 != null)
			{
				return -1; //First isn't null, so it must be before null!
			}
			else if(o2 != null)
			{
				return 1; //First is null, so it must be after non-null!
			}
			else
			{
				return 0; //Both null, same value!
			}
		}
			};
	
	private static final Comparator<ItemStack> NBT_COMPARATOR = new Comparator<ItemStack>() //XXX JAVA 8
			{
		
		@Override
		public int compare(ItemStack o1, ItemStack o2)
		{
			if(o1 != null && o2 != null) //Are both non-null?
			{
				Item item1 = o1.getItem(), item2 = o2.getItem();
				int id1 = Item.getIdFromItem(item1), id2 = Item.getIdFromItem(item2);
				
				if(id1 == id2) //Same item id?
				{
					if(item1 == item2) //Same item?
					{
						int meta1 = o1.getItemDamage(), meta2 = o2.getItemDamage();
						
						if(meta1 == meta2)//Same damage?
						{
							if(o1.hasTagCompound() && o2.hasTagCompound()) //Both have compound?
							{
								if(ItemHelper.isNBTEqual(o1.stackTagCompound, o2.stackTagCompound)) //Same NBT data?
								{
									return o1.stackSize < o2.stackSize ? -1 : o1.stackSize == o2.stackSize ? 0 : -1; //First stack size is smaller, comes before. Stack size is equal, then they are equal. Stack size is greater, comes after.
								}
								else	
								{		
									return o1.stackTagCompound.hashCode() < o2.stackTagCompound.hashCode() ? -1 : o1.stackTagCompound.hashCode() == o2.stackTagCompound.hashCode() ? 0 : 1; //Hashcode implementation, we resort to hashcode reliance. Smaller hashcode goes first.
								}
							}
							else
							{
								if(o1.hasTagCompound())
								{
									return 1; //This has a tag, while the other doesn't, this places this one after the second.
								}
								else if(o2.hasTagCompound())
								{
									return -1; //First doesn't have tag, second does, this puts first infront of second.
								}
								else
								{
									return 0; //Neither have tag.
								}
							}
						}
						else
						{
							return meta1 < meta2 ? -1 : 1; //lower metadata goes first.
						}
					}
					else
					{
						return item1.getUnlocalizedName(o1).compareToIgnoreCase(item2.getUnlocalizedName(o2)); //Same ID, different item, sorting by name.
					}
				}
				else
				{
					return id1 < id2 ? -1 : 1; //If the first comes before in ID, it goes first, otherwise it goes after.
				}
			}
			else if(o1 != null)
			{
				return -1; //First isn't null, so it must be before null!
			}
			else if(o2 != null)
			{
				return 1; //First is null, so it must be after non-null!
			}
			else
			{
				return 0; //Both null, same value!
			}
		}
			};
	
	
	public static boolean areItemsEqual(ItemStack stack1, ItemStack stack2)
	{
		if(stack1 == null || stack2 == null) //Is one null?
		{
			return false; //Are both null?
		}
		
		return stack1.getItem() == stack1.getItem();
	}
	
	public static boolean areItemsEqualWithMetadata(ItemStack stack1, ItemStack stack2)
	{
		return areItemsEqual(stack1, stack2) && stack1.getItemDamage() == stack2.getItemDamage() || stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE;
	}
	
	public static boolean areItemsEqualWithNBT(ItemStack stack1, ItemStack stack2)
	{
		return areItemsEqual(stack1, stack2) && isNBTEqual(stack1.stackTagCompound, stack2.stackTagCompound);
	}
	
	public static boolean isNBTEqual(NBTTagCompound compound1, NBTTagCompound compound2)
	{
		return compound1 != null ? compound1.equals(compound2) : compound2 == null;
	}
	
	public static boolean areItemsEqualWithMetadataAndNBT(ItemStack stack1, ItemStack stack2)
	{
		return areItemsEqualWithMetadata(stack1, stack2) && isNBTEqual(stack1.stackTagCompound, stack2.stackTagCompound);
	}
	
	public static Comparator<ItemStack> getComparator(SortLevel level)
	{
		switch(level)
		{
		case ITEM:
			return ITEM_COMPARATOR;
		case DAMAGE_WILDCARD:
			return DAMAGE_WILDCARD_COMPARATOR;
		case DAMAGE:
			return DAMAGE_COMPARATOR;
		case NBT:
			return NBT_COMPARATOR;
		default:
			return null;
		}
	}
	
	public static enum SortLevel
	{
		ITEM, DAMAGE_WILDCARD, DAMAGE, NBT;
	}
	
}
