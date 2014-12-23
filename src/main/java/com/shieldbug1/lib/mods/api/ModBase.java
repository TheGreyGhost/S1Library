package com.shieldbug1.lib.mods.api;

import com.shieldbug1.lib.mods.update.Updater;

/**
 * The base interface for all of my mods.
 */
public interface ModBase
{
	/**
	 * Returns this mod's {@link Updater} instance. Can not be null.
	 * @return the mod's instance of {@link Updater}.
	 */
	public abstract Updater getUpdater();
	/**
	 * A method to return the Serial ID of this mod. This number should ideally start at 0, and must never decrease.
	 * It should raise every time the mod's version updates.
	 * @return the Serial 'Version' ID of this mod.
	 */
	public abstract int getSerialID();
}