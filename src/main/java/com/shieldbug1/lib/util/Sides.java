package com.shieldbug1.lib.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * A pure convenience class to allow for easier reading of code.
 */
public final class Sides
{
	private Sides(){}
	
	public static Side logical()
	{
		return FMLCommonHandler.instance().getEffectiveSide();
	}
	
	public static Side environment()
	{
		return FMLCommonHandler.instance().getSide();
	}
	
	public static boolean logicalServer()
	{
		return logical() == Side.SERVER;
	}
	
	public static boolean environmentServer()
	{
		return environment() == Side.SERVER;
	}
	
	public static boolean logicalClient()
	{
		return logical() == Side.CLIENT;
	}
	
	public static boolean environmentClient()
	{
		return environment() == Side.CLIENT;
	}
	
	public static Side logicalSide(World world)
	{
		return world.isRemote ? Side.CLIENT : Side.SERVER;
	}
	
	public static boolean logicalServer(World world)
	{
		return !world.isRemote;
	}
	
	public static boolean logicalClient(World world)
	{
		return world.isRemote;
	}
	
	public static Side logicalSide(Entity entity)
	{
		return entity.worldObj.isRemote ? Side.CLIENT : Side.SERVER;
	}
	
	public static boolean logicalServer(Entity entity)
	{
		return !entity.worldObj.isRemote;
	}
	
	public static boolean logicalClient(Entity entity)
	{
		return entity.worldObj.isRemote;
	}
}
