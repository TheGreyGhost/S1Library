package com.shieldbug1.lib.network;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

import com.shieldbug1.lib.java.Java;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public final class NetworkHelper
{
	private NetworkHelper(){}
	
	/**
	 * Sends the message all players tracking the entity.
	 */
	public static void sendToAllTracking(SimpleNetworkWrapper wrapper, Entity entity, IMessage message)
	{
		if(entity.worldObj instanceof WorldServer)
		{
			((WorldServer)entity.worldObj).getEntityTracker().func_151247_a(entity, wrapper.getPacketFrom(message));
		}
	}
	
	/**
	 * Sends the message to all players tracking the TileEntity.
	 */
	public static void sendToAllTracking(SimpleNetworkWrapper wrapper, TileEntity tileEntity, IMessage message)
	{
		if(tileEntity.getWorldObj() instanceof WorldServer)
		{
			//TODO ((WorldServer)tileEntity.getWorldObj()).getPlayerManager().sendToWatching(tileEntity.xCoord >> 4, tileEntity.zCoord >> 4, wrapper.getPacketFrom(message));
		}
	}
	
	/**
	 * Writes NBT data to ByteBuf instances, without casting to short (vanilla is noob)
	 */
	public static void writeNBTToBuffer(ByteBuf buf, NBTTagCompound nbt)
	{
		if(nbt == null)
		{
			buf.writeInt(-1);
		}
		else
		{
			try
			{
				byte[] bytes = CompressedStreamTools.compress(nbt);
				buf.writeInt(bytes.length);
				buf.writeBytes(bytes);
			}
			catch (IOException e)
			{
				Java.<RuntimeException>throwUnchecked(e);
			}
		}
	}
	
	/**
	 * Reads NBT data from ByteBuf instances, without casting to short (vanilla is super noob)
	 */
	public static NBTTagCompound readNBTFromBuffer(ByteBuf buf)
	{
		int length = buf.readInt();
		if(length < 0)
		{
			return null;
		}
		else
		{
			byte[] bytes = new byte[length];
            buf.readBytes(bytes);
            try
            {
				return CompressedStreamTools.func_152457_a(bytes, new NBTSizeTracker(2097152L)); //Cap of 2MB?
			}
            catch (IOException e)
            {
				throw Java.<RuntimeException>throwUnchecked(e); //impossible.
			}
		}
	}
}
