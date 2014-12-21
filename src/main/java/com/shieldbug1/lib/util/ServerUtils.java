package com.shieldbug1.lib.util;

import java.util.concurrent.Callable;

import net.minecraft.server.MinecraftServer;

import com.google.common.util.concurrent.ListenableFuture;

public final class ServerUtils
{
	private ServerUtils(){}
	
	/**	
	 * Schedules a callable which will be attempted to run at the beginning of the next server tick.
	 */
	public static <T> ListenableFuture<T> callFromMainThread(Callable<T> callable)
	{
		return MinecraftServer.getServer().callFromMainThread(callable);
	}
	
	/**
	 * Schedules a runnable which will be attempted to run at the beginning of the next server tick.
	 */
	public static void callFromMainThread(Runnable runnable)
	{
		MinecraftServer.getServer().addScheduledTask(runnable);
	}
}
