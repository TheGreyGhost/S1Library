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

/**
 * A utility class for Network related methods.
 */
public final class NetworkHelper
{
	private NetworkHelper(){}
	
	/**
	 * Sends the message all players tracking the entity.
	 * @param wrapper - the SimpleNetworkWrapper to send from.
	 * @param entity - the entity to find all trackers for.
	 * @param message - the message to send to all trackers.
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
	 * @param wrapper - the SimpleNetworkWrapper to send from.
	 * @param tileEntity - the tileEntity to find all trackers for.
	 * @param message - the message to send to all trackers.
	 * 
	 * @deprecated Currently does nothing, pending pull request on MinecraftForge github.
	 */
	@Deprecated
	public static void sendToAllTracking(SimpleNetworkWrapper wrapper, TileEntity tileEntity, IMessage message)
	{
		if(tileEntity.getWorld() instanceof WorldServer)
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
	
	/**
	 * Compresses a NBTTagCompound to a byte array.
	 * To be decompressed again later using {@link #decompress(byte[], NBTSizeTracker)}.
	 * @param compound - the compound to compress.
	 * @return the compressed byte array.
	 */
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
	
	/**
	 * Decompresses a byte array into an NBTTagCompound.
	 * Compression must've been done through {@link #compress(NBTTagCompound)}.
	 * @param bytes - the byte array to decompress.
	 * @param size - the maximum size of data? TODO figure this one out.
	 * @return the decompressed NBTTagCompound.
	 */
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
