package com.shieldbug1.lib.util;

import java.util.concurrent.Callable;

import net.minecraft.client.Minecraft;

import com.google.common.util.concurrent.ListenableFuture;

public final class ClientUtils
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
	public static void callFromMainThread(Runnable runnable)
	{
		Minecraft.getMinecraft().addScheduledTask(runnable);
	}
}
