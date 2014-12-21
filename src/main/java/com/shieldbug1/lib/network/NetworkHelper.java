package com.shieldbug1.lib.network;

import io.netty.buffer.ByteBuf;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import com.shieldbug1.lib.java.Java;

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
			((WorldServer)entity.worldObj).getEntityTracker().sendToAllTrackingEntity(entity, wrapper.getPacketFrom(message));
		}
	}
	
	/**
	 * Sends the message to all players tracking the TileEntity.
	 */
	public static void sendToAllTracking(SimpleNetworkWrapper wrapper, TileEntity tileEntity, IMessage message)
	{
		if(tileEntity.getWorld() instanceof WorldServer)
		{
			//TODO ((WorldServer)tileEntity.getWorldObj()).getPlayerManager().sendToWatching(tileEntity.xCoord >> 4, tileEntity.zCoord >> 4, wrapper.getPacketFrom(message));
		}
	}
	
	public static void a()
	{
		
	}
	
	/**
	 * Writes NBT data to ByteBuf instances, without casting to short (vanilla is noob)
	 */
	public static void writeNBTToBuffer(ByteBuf buf, NBTTagCompound nbt)
	{
		if(nbt == null)
		{
			buf.writeInt(0);
		}
		else
		{
			byte[] bytes = compress(nbt);
			buf.writeInt(bytes.length);
			buf.writeBytes(bytes);
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
            return decompress(bytes, new NBTSizeTracker(1 << 21)); //Cap of 2MB? (2097152L)
		}
	}
	
	public static byte[] compress(NBTTagCompound compound)
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		
		try(DataOutputStream dataOut = new DataOutputStream(new GZIPOutputStream(byteStream));)
		{
			CompressedStreamTools.write(compound, dataOut);
			return byteStream.toByteArray();
		}
		catch(IOException e)
		{
			throw Java.<RuntimeException>throwUnchecked(e);
		}
	}
	
	public static NBTTagCompound decompress(byte[] bytes, NBTSizeTracker size)
	{
		try(DataInputStream dataIn = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(bytes)))))
		{
			return CompressedStreamTools.func_152456_a(dataIn, size);
		}
		catch (IOException e)
		{
			throw Java.<RuntimeException>throwUnchecked(e);
		}
	}
}
