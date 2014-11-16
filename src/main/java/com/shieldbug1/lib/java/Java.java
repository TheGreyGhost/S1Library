package com.shieldbug1.lib.java;



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
	
}
