package com.shieldbug1.lib.mods.api;

import com.shieldbug1.lib.mods.update.Updater;

public interface ModBase
{
	public abstract Updater getUpdater();
	public abstract int getSerialID();
}