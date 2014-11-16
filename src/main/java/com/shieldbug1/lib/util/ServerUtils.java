package com.shieldbug1.lib.util;

import java.util.Queue;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.Validate;

import scala.actors.threadpool.Executors;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.*;

public final class ServerUtils
{
	private ServerUtils(){}
	
	private static final Queue<ListenableFutureTask> taskQueue = Queues.newArrayDeque();
	private static final Thread thisThread = Thread.currentThread();
	
	
	/**	
	 * Schedules a callable which will be attempted to run at the beginning of the next server tick.
	 */
	public static <T> ListenableFuture<T> scheduleCallable(Callable<T> callable)
	{
		Validate.notNull(callable);
		if (Thread.currentThread() != thisThread)
		{
			ListenableFutureTask listenablefuturetask = ListenableFutureTask.create(callable);
			synchronized(taskQueue)
			{
				taskQueue.add(listenablefuturetask);
				return listenablefuturetask;
			}
		}
		else
		{
			try
			{
				return Futures.immediateFuture(callable.call());
			}
			catch (Exception exception)
			{
				return Futures.immediateFailedCheckedFuture(exception);
			}
		}
	}
	
	/**
	 * Schedules a runnable which will be attempted to run at the beginning of the next server tick.
	 */
	public static void scheduleRunnable(Runnable runnable)
	{
		scheduleCallable((Callable<?>)Executors.callable(runnable));
	}
	
	public static void preTick()//INTERNAL
	{
		synchronized(taskQueue)
		{
			while(!taskQueue.isEmpty())
			{
				taskQueue.poll().run();
			}
		}
	}
	
	public static void postTick()//INTERNAL
	{
		
	}
}
