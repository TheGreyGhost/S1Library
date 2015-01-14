package com.shieldbug1.lib.java;

import static org.apache.logging.log4j.Level.INFO;
import net.minecraftforge.fml.common.FMLLog;
import sun.reflect.Reflection;

import com.shieldbug1.lib.java.ClassContextStrategy.AccessibilityManager;
import com.shieldbug1.lib.java.ClassContextStrategy.ReflectionStrategy;

public final class Java
{
	private Java(){}
	
	/**
	 * @return the singleton instance of the UnsafeWrapper, which allows use of the {@code sun.misc.Unsafe} class.
	 */
	public static UnsafeWrapper getUnsafeWrapper()
	{
		return UnsafeWrapper.INSTANCE;
	}
	
	/**
	 * Useful to rethrow checked exceptions!
	 * @param e - the throwable to throw.
	 * @return nothing, ever.
	 */
	public static <T extends Throwable> RuntimeException throwUnchecked(Throwable e) throws T //XXX JAVA 8 look at calls and remove explicit type 
	{
        throw (T) e;
    }
	
	/**
	 * @return the class that called the class that called this method.
	 */
	public static Class<?> getCallingClass()
	{
		return getCallingClass(1); //we go one more back because we call our own method.
	}
	
	/**
	 * @param index - how far back the calling class should be: 0 is the calling class, 1 is the calling class before it, etc.
	 * @return the calling class according to the index.
	 */
	public static Class<?> getCallingClass(int index)
	{
		return  strategy.getCallingClass(index);
	}
	
	private static final ClassContextStrategy strategy;
	
	static
	{
		boolean useReflection = true;
		try
		{
			Reflection.getCallerClass(0);
		}
		catch(UnsupportedOperationException | NoSuchMethodError e)
		{
			FMLLog.log("S1LIB", INFO, "Could not use Reflection strategy to get caller class - using ManagerStrategy instead.");
			useReflection = false;
		}
		strategy = useReflection ? new ReflectionStrategy() : new AccessibilityManager();
		
	}
	
	/*public static <T> Stream<T> streamFromIterable(Iterable<T> iterator, boolean parallel) XXX UNcomment this in Java 8.
	{
		return StreamSupport.stream(iterator.spliterator(), parallel);
	}*/
}
