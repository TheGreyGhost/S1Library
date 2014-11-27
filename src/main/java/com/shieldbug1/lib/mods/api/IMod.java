package com.shieldbug1.lib.mods.api;

import net.minecraftforge.fml.common.ModContainer;

/**
 * An interface that describes all mods, that wraps around the ModContainer wrapper.
 */
public interface IMod //A wrapper around a wrapper around a wrapper around a wrapper... -slams head on desk-
{
	/**
	 * @return true if this mod is currently loaded.
	 */
	public abstract boolean isLoaded();
	
	/**
	 * @return the ModContanier object of this Mod.
	 */
	public abstract ModContainer getModContainer();
	
	/**
	 * @return the actual instance of this mod.
	 */
	public abstract Object instance();
	
	/**
	 * @return the currently loaded version of this mod.
	 */
	public abstract String getVersion();
	
	/**
	 * @return the ModID for this given mod.
	 */
	public abstract String getModID();
	
}
