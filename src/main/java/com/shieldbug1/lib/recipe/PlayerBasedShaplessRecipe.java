package com.shieldbug1.lib.recipe;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

import com.shieldbug1.lib.java.ReflectionCache;

public abstract class PlayerBasedShaplessRecipe extends ShapelessRecipes
{
	public PlayerBasedShaplessRecipe(ItemStack output, List<ItemStack> items)
	{
		super(output, items);
	}

	@Override
	public final boolean matches(InventoryCrafting ic, World world)
	{
		EntityPlayer player = this.getPlayer(ic);
		return super.matches(ic, world) && player != null && this.playerCheck(player, world);
	}
	
	public abstract boolean playerCheck(EntityPlayer player, World world);
	
	private EntityPlayer getPlayer(InventoryCrafting ic)
	{
		Container container = ReflectionCache.getFieldValue(InventoryCrafting.class, ic, "eventHandler", "field_70465_c");
		if(container instanceof ContainerPlayer)
		{
			return ReflectionCache.getFieldValue(ContainerPlayer.class, (ContainerPlayer)container, "thePlayer", "field_82862_h");
		}
		else if(container instanceof ContainerWorkbench)
		{
			return ReflectionCache.getFieldValue(SlotCrafting.class, (SlotCrafting)container.getSlot(0), "thePlayer", "field_75238_b");
		}
		else
		{
			return null;
		}
	}
}
