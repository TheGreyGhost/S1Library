package com.shieldbug1.lib.util;

import java.util.concurrent.Callable;

import net.minecraft.server.MinecraftServer;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * A helper class for miscellaneous server-related methods.
 */
public final class ServerUtils
{
	private ServerUtils(){}
	
	/**	
	 * Schedules a Callable which will be attempted to run at the beginning of the next server tick.
	 * @see MinecraftServer#callFromMainThread(Callable) MinecraftServer.getServer().callFromMainThread(Callable)
	 */
	public static <T> ListenableFuture<T> callFromMainThread(Callable<T> callable)
	{
		return MinecraftServer.getServer().callFromMainThread(callable);
	}
	
	/**
	 * Schedules a runnable which will be attempted to run at the beginning of the next server tick.
	 * @see MinecraftServer#addScheduledTask(Runnable) MinecraftServer.getServer().addScheduledTask(Runnable)
	 */
	public static void callFromMainThread(Runnable runnable)
	{
		MinecraftServer.getServer().addScheduledTask(runnable);
	}
}
