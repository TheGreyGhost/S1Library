package com.shieldbug1.lib.mods.update;

import static org.apache.logging.log4j.Level.INFO;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;

/**
 * Default implementation of {@link Updater}.
 */
public class UpdaterBase implements Updater
{
	private final String updateURL, modURL, modName, modID;
	private boolean foundUpdateClient = false;
	
	/**
	 * @param updateUrl - the url to the serial id file.
	 *         The first thing in the file shoul be an integer of the serial ID of the mod.
	 *         This serial ID should raise every version change.
	 * @param modUrl - the url to where the latest version of the mod can be found.
	 * @param modName - the name of the mod.
	 * @param modID - the mod id of the mod.
	 */
	public UpdaterBase(String updateUrl, String modUrl, String modName, String modID)
	{
		this.updateURL = updateUrl;
		this.modURL = modUrl;
		this.modName = modName;
		this.modID = modID;
	}
	
	@Override
	public String getUpdateURL()
	{
		return this.updateURL;
	}

	@Override
	public void handleUpdateFound(Side side)
	{
		if(side.isClient())
		{
			this.foundUpdateClient = true;
			MinecraftForge.EVENT_BUS.register(this);
		}
		FMLLog.log(this.modID, INFO, "An update for " + this.modName + " has been found. You can find the latest version at " + this.modURL);
	}
	
	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event)
	{
		if(event.entity instanceof EntityPlayer && event.world.isRemote && this.foundUpdateClient) //client
		{
			((EntityPlayer)event.entity).addChatComponentMessage(new ChatComponentText("An update for " + this.modName +" has been found. You can find the latest version by clicking here.")
			.setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, this.modURL))));
			this.foundUpdateClient = false;
			MinecraftForge.EVENT_BUS.unregister(this);
		}
	}

}
