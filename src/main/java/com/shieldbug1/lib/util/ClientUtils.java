package com.shieldbug1.lib.util;

import java.util.concurrent.Callable;

import net.minecraft.client.Minecraft;

import com.google.common.util.concurrent.ListenableFuture;

public final class ClientUtils//TODO Javadoc
{
	private ClientUtils(){}
	
	/**
	 * Schedules a callable which will be attempted to run at the beginning of the next client tick.
	 */
	public static <T> ListenableFuture<T> callFromMainThread(Callable<T> callable)
	{
		return Minecraft.getMinecraft().addScheduledTask(callable);
	}
	
	/**
	 * Schedules a runnable which will be attempted to run at the beginning of the next client tick.
	 */
	public static ListenableFuture<Void> callFromMainThread(Runnable runnable)
	{
		return Minecraft.getMinecraft().addScheduledTask(runnable);
	}
}
