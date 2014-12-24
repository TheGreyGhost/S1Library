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
	
	/**
	 * @return the logical side that code is currently running in.
	 * @see FMLCommonHandler#getEffectiveSide() FMLCommonHandler.instance().getEffectiveSide()
	 */
	public static Side logical()
	{
		return FMLCommonHandler.instance().getEffectiveSide();
	}
	
	/**
	 * @return the environment side we are in (aka what jar are we using). 
	 */
	public static Side environment()
	{
		return FMLCommonHandler.instance().getSide();
	}
	
	/**
	 * @return true when we are on the logical server.
	 */
	public static boolean logicalServer()
	{
		return logical() == Side.SERVER;
	}
	
	/**
	 * @return true when we are using the server jar.
	 */
	public static boolean environmentServer()
	{
		return environment() == Side.SERVER;
	}
	
	/**
	 * @return true when we are on the logical client.
	 */
	public static boolean logicalClient()
	{
		return logical() == Side.CLIENT;
	}
	
	/**
	 * @return true when we are using the client jar.
	 */
	public static boolean environmentClient()
	{
		return environment() == Side.CLIENT;
	}
	
	/**
	 * This method uses a {@code World} instance to get the logical side. This is faster than simply
	 * using {@link #logical()}.
	 * @param world - the world object to use to get the logical side.
	 * @return the logical side through using an instance of {@code World}.
	 */
	public static Side logicalSide(World world)
	{
		return world.isRemote ? Side.CLIENT : Side.SERVER;
	}
	
	/**
	 * Equivalent to {@code !world.isRemote}
	 * @return true if the world is on a logical server.
	 * @see #logicalSide(World).
	 */
	public static boolean logicalServer(World world)
	{
		return !world.isRemote;
	}
	
	/**
	 * Equivalent to {@code world.isRemote}
	 * @return true if the world is on a logical client.
	 * @see #logicalSide(World).
	 */
	public static boolean logicalClient(World world)
	{
		return world.isRemote;
	}
	
	/**
	 * This method uses a {@code Entity} instance to get the logical side. This is faster than simply
	 * using {@link #logical()}.
	 * @param entity - the entity object to use to get the logical side.
	 * @return the logical side through using an instance of {@code Entity}.
	 */
	public static Side logicalSide(Entity entity)
	{
		return entity.worldObj.isRemote ? Side.CLIENT : Side.SERVER;
	}
	
	/**
	 * Equivalent to {@code !entity.world.isRemote}
	 * @return true if the entity is on a logical server.
	 * @see #logicalSide(Entity).
	 */
	public static boolean logicalServer(Entity entity)
	{
		return !entity.worldObj.isRemote;
	}
	
	/**
	 * Equivalent to {@code entity.world.isRemote}
	 * @return true if the entity is on a logical client.
	 * @see #logicalSide(Entity).
	 */
	public static boolean logicalClient(Entity entity)
	{
		return entity.worldObj.isRemote;
	}
}
