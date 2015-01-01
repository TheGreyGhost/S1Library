package com.shieldbug1.lib.java;

import static com.shieldbug1.lib.java.Java.JavaVersion.*;
import sun.reflect.Reflection;

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
	 * A method that retrieves the current version of Java.
	 * @return the version of Java that is being run right now.
	 */
	public static JavaVersion getJavaVersion()
	{
		switch(System.getProperty("java.version").substring(0, 3))
		{
		case "1.6": return V1_6;
		case "1.7": return V1_7;
		case "1.8": return V1_8;
		default: return UNKNOWN;
		}
	}
	
	/**
	 * @return the class that called the class that called this method.
	 */
	public Class<?> getCallingClass()
	{
		return Reflection.getCallerClass(3);
	}
	
	/**
	 * @param index - how far back the calling class should be: 0 is the calling class, 1 is the calling class before it, etc.
	 * @return the calling class according to the index.
	 */
	public Class<?> getCallingClass(int index)
	{
		return Reflection.getCallerClass(index + 3);
	}
	
	public static enum JavaVersion	{	V1_6, V1_7, V1_8, UNKNOWN;	}
}
