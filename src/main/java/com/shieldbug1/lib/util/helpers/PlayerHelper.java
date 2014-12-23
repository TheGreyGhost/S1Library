package com.shieldbug1.lib.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Helper class to deal with EntityPlayer instances.
 */
public final class PlayerHelper
{
	private PlayerHelper(){}
	
	/**
	 * Checks whether the specified player has operator status on current server.
	 * @param player - the player to check for operator status.
	 * @return true if the player is an op.
	 */
	public static boolean isPlayerOp(EntityPlayer player)
	{
		if(FMLCommonHandler.instance().getMinecraftServerInstance() != null && player != null)
		{
			return FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getOppedPlayers().getEntry(player.getGameProfile()) != null;
		}
		return false;
	}
	
	/**
	 * Checks if the specified player is considered a 'FakePlayer' (includes null players).
	 * @param player - player to check.
	 * @return true if player was found to be Fake.
	 */
	public static boolean isFakePlayer(EntityPlayer player)
	{
		if(player == null || player instanceof FakePlayer || player.getGameProfile() == null || player.getGameProfile().getId() == null)
		{
			return true;
		}
		if(player instanceof EntityPlayerMP) //Server Player
		{
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			if(playerMP.playerNetServerHandler == null)
			{
				return true;
			}
			try
			{
				playerMP.getPlayerIP();
			}
			catch(Exception e)
			{
				return true;
			}
			return !FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.contains(player);
		}
		return false;
	}
	
	/**
	 * Gets a MovingObjectPosition instance for the player's look.
	 * @param player - the player to get the MovingObjectPosition for.
	 * @param reach - the maximum distance to find a MovingObjectPosition for.
	 * @param fluid - whether fluid blocks count or not.
	 * @return a MovingObjectPosition if found, otherwise null.
	 */
	public static MovingObjectPosition getPlayerLookMOP(EntityPlayer player, double reach, boolean fluid)
	{
		float playerPitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch);
		float playerYaw = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw);
		double playerPosX = player.prevPosX + (player.posX - player.prevPosX);
		double playerPosY = (player.prevPosY + (player.posY - player.prevPosY) + 1.6200000000000001D) - player.getYOffset();
		double playerPosZ = player.prevPosZ + (player.posZ - player.prevPosZ);
		Vec3 vecPlayer = new Vec3(playerPosX, playerPosY, playerPosZ);
		double cosYaw = Math.cos(Math.toRadians(-playerYaw) - Math.PI);
		double sinYaw = Math.sin(Math.toRadians(-playerYaw) - Math.PI);
		double cosPitch = -Math.cos(Math.toRadians(-playerPitch));
		double sinPitch = Math.sin(Math.toRadians(-playerPitch));
		double pointX = sinYaw * cosPitch;
		double pointY = sinPitch;
		double pointZ = cosYaw * cosPitch;
		Vec3 vecPoint = vecPlayer.addVector(pointX * reach, pointY * reach, pointZ * reach);
		return player.worldObj.rayTraceBlocks(vecPlayer, vecPoint, fluid);
	}
}
