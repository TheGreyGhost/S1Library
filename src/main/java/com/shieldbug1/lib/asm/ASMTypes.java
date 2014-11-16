package com.shieldbug1.lib.asm;

import org.objectweb.asm.Type;

public class ASMTypes
{
	private ASMTypes(){}
	
	public static final Type TILE_ENTITY_TYPE = Type.getType("Lnet/minecraft/tileentity/TileEntity;");
	public static final Type ITEM_TYPE = Type.getType("Lnet/minecraft/item/Item;");
	public static final Type WORLD_TYPE = Type.getType("Lnet/minecraft/world/World;");
	public static final Type BLOCK_TYPE = Type.getType("Lnet/minecraft/block/Block;");
	public static final Type ITEMSTACK_STYPE = Type.getType("Lnet/minecraft/item/ItemStack;");
	public static final Type ENTITYPLAYER_TYPE = Type.getType("Lnet/minecraft/entity/player/EntityPlayer;");
}
