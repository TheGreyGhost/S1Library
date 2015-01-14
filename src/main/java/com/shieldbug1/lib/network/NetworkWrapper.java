package com.shieldbug1.lib.network;

import static com.shieldbug1.lib.util.CoreFunctions.checkNotNull;
import static net.minecraftforge.fml.relauncher.Side.CLIENT;
import static net.minecraftforge.fml.relauncher.Side.SERVER;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkWrapper //TODO javadoc
{
	private final SimpleNetworkWrapper wrapper;
	private byte discriminator = 0;
	
	public NetworkWrapper(SimpleNetworkWrapper wrapper)
	{
		this.wrapper = checkNotNull(wrapper, "SimpleNetworkWrapper instance cannot be null!");
	}
	
	public <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
			Class<REQ> messageClass, Class<? extends IMessageHandler<REQ, REPLY>> handlerClass, Side side)
	{
		wrapper.registerMessage(handlerClass, messageClass, discriminator++, side);
	}
	
	public <REQ extends IMessage, REPLY extends IMessage> void registerClientMessage(
			Class<REQ> messageClass, Class<? extends IMessageHandler<REQ, REPLY>> handlerClass)
	{
		wrapper.registerMessage(handlerClass, messageClass, discriminator++, CLIENT);
	}
	
	public <REQ extends IMessage, REPLY extends IMessage> void registerServerMessage(
			Class<REQ> messageClass, Class<? extends IMessageHandler<REQ, REPLY>> handlerClass)
	{
		wrapper.registerMessage(handlerClass, messageClass, discriminator++, SERVER);
	}
	
	public Packet getPacketFrom(IMessage message)
	{
		return wrapper.getPacketFrom(message);
	}

	public void sendToAll(IMessage message)
	{
		wrapper.sendToAll(message);
	}

	public void sendTo(IMessage message, EntityPlayerMP player)
	{
		wrapper.sendTo(message, player);
	}

	public void sendToAllAround(IMessage message, TargetPoint point)
	{
		wrapper.sendToAllAround(message, point);
	}

	public void sendToDimension(IMessage message, int dimensionId)
	{
		wrapper.sendToDimension(message, dimensionId);
	}
	
	public void sendToServer(IMessage message)
	{
		wrapper.sendToServer(message);
	}
	
	/**
	 * Sends the message all players tracking the entity.
	 * @param entity - the entity to find all trackers for.
	 * @param message - the message to send to all trackers.
	 */
	public void sendToAllTracking(Entity entity, IMessage message)
	{
		NetworkHelper.sendToAllTracking(this.wrapper, entity, message);
	}
	
	/**
	 * Sends the message to all players tracking the TileEntity.
	 * @param tileEntity - the tileEntity to find all trackers for.
	 * @param message - the message to send to all trackers.
	 * 
	 */
	public void sendToAllTracking(TileEntity tileEntity, IMessage message)
	{
		NetworkHelper.sendToAllTracking(this.wrapper, tileEntity, message);
	}
	
	public void sendToWorld(IMessage message, World world)
	{
		this.sendToDimension(message, world.provider.getDimensionId());
	}
	
	public void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double radius)
	{
		this.sendToAllAround(message, new TargetPoint(dimension, x, y, z, radius));
	}
}
