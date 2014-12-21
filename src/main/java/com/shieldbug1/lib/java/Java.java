package com.shieldbug1.lib.java;

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
	 * @return the Java version.
	 */
	public static String getJavaVersion()
	{
		return System.getProperty("java.version");
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
}
