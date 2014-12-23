package com.shieldbug1.lib.mods.update;

import net.minecraftforge.fml.relauncher.Side;
/**
 * Updater interface to allow for easier mod updating.
 * @see UpdaterBase for default implementation.
 */
public interface Updater
{
	/** @return the url where update information can be found. */
	public abstract String getUpdateURL();
	
	/** Handle updating here.*/
	public abstract void handleUpdateFound(Side side);
}
