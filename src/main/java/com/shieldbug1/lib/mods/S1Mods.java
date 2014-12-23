package com.shieldbug1.lib.mods;

import net.minecraftforge.fml.common.*;

import com.shieldbug1.lib.mods.api.IMod;
import com.shieldbug1.lib.mods.api.ModBase;

/**
 * All mods created by me. These <b>must</b> implement {@link ModBase} (in the base class).
 */
public enum S1Mods implements IMod
{
	S1CORE("S1CORE"),
	BITS_AND_PIECES("bap");
	
	private final String modName;
	private final boolean loaded;
	
	private S1Mods(String modID)
	{
		this.modName = modID;
		this.loaded = Loader.isModLoaded(modName);
	}
	
	@Override
	public boolean isLoaded()
	{
		return this.loaded;
	}
	
	@Override
	public ModContainer getModContainer()
	{
		return FMLCommonHandler.instance().findContainerFor(this.modName);
	}
	
	@Override
	public ModBase instance()
	{
		return (ModBase) (this.getModContainer() != null ? this.getModContainer().getMod() : null);
	}
	
	@Override
	public String getVersion()
	{
		return this.getModContainer() != null ? this.getModContainer().getVersion() : "";
	}

	@Override
	public String getModID()
	{
		return this.modName;
	}
	
}
