package com.shieldbug1.lib.recipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;

import com.shieldbug1.lib.java.ReflectionCache;

/**
 * An implementation of ShapedRecipes that also gives a player to check.
 */
public abstract class PlayerBasedShapedRecipe extends ShapedRecipes //TODO verify this still works.
{
	public PlayerBasedShapedRecipe(int width, int height, ItemStack[] recipe, ItemStack output)
	{
		super(width, height, recipe, output);
	}
	
	@Override
	public final boolean matches(InventoryCrafting ic, World world)
	{
		EntityPlayer player = this.getPlayer(ic);
		return super.matches(ic, world) && player != null && this.playerCheck(player, world);
	}
	
	/**
	 * @return true if the player fulfils all conditions in order to craft this recipe.
	 */
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
