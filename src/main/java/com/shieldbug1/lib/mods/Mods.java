package com.shieldbug1.lib.mods;

import net.minecraftforge.fml.common.*;

import com.shieldbug1.lib.mods.api.IMod;

public enum Mods implements IMod
{
	NEI("NotEnoughItems"),
	WAILA("Waila"),
	EE3("ee3"),
	THAUMCRAFT("Thaumcraft"),
	TINKERS_CONSTRUCT("TConstruct"),
	BLOOD_MAGIC("AWWayofTime");
	
	private final String modName;
	private final boolean loaded;
	
	private Mods(String modID)
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
	public Object instance()
	{
		return this.getModContainer() != null ? this.getModContainer().getMod() : null;
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
