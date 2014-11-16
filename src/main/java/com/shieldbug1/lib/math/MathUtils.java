package com.shieldbug1.lib.math;

import java.util.Random;

public final class MathUtils
{
	private MathUtils(){}
	/**
	 * An approximation of the 'Golden Ratio'. (1.6180339887498948482)
	 */
	public static final double PHI = 1.6180339887498948482;
	/**
	 * An approximation of the positive square root of five. (2.23606797749978969)
	 */
	public static final double SQRT_5 = 2.23606797749978969;
	/**
	 * An approximate decimal representation of the Golden Angle in degrees.
	 */
	public static final double GOLDEN_ANGLE_DEG = 137.5077640500378546463;
	/**
	 * An approximate decimal representation of the Golden Angle in radians.
	 */
	public static final double GOLDEN_ANGLE_RAD = 2.39996322972865332223;
	/**
	 * TAU IS BETTER THAN PI.
	 */
	public static final double TAU = 2 * Math.PI;
	
	/**
	 * Simple helper method that returns has a chance of returning true.
	 * @param chance - the % chance of returning true (between 0 and 100).
	 * @return true {@code chance * 100} times every 100 times called, approximately.
	 */
	public static boolean randomChance(Random random, int chance)
	{
		return random.nextDouble() < (chance / 100D);
	}
	
	public static int clamp(int a, int min, int max)
	{
		return a < min ? min : a > max ? max : a;
	}
	
	public static float clamp(float a, float min, float max)
	{
		return a < min ? min : a > max ? max : a;
	}
	
	public static double clamp(double a, double min, double max)
	{
		return a < min ? min : a > max ? max : a;
	}
	
	public static float interpolate(float a, float b, float d)
	{
		return a + (b - a) * d;
	}

	public static double interpolate(double a, double b, double d)
	{
		return a + (b - a) * d;
	}
	
	public static int interpolate(int a, int b, int d)
	{
		return a + (b - a) * d;
	}
	
	public static int floor(double d)
	{
		int i = (int) d;
		return i > d ? (i - 1) : i;
	}
	
	public static int min(int a, int b)
	{
		return a < b ? a : b;
	}
	
	public static int max(int a, int b)
	{
		return a > b ? a : b;
	}
	
	/**
	 * @return {@link Byte#valueOf(String)} unless it throws a {@code NumberFormatException}, in which case it returns null.
	 * @see Byte#valueOf(String)
	 */
	public static Byte byteParseSafe(String string)
	{
		try
		{
			return Byte.valueOf(string);
		}
		catch(NumberFormatException e)
		{
			return null;
		}
	}
	
	/**
	 * @return {@link Short#valueOf(String)} unless it throws a {@code NumberFormatException}, in which case it returns null.
	 * @see Short#valueOf(String)
	 */
	public static Short shortParseSafe(String string)
	{
		try
		{
			return Short.valueOf(string);
		}
		catch(NumberFormatException e)
		{
			return null;
		}
	}
	
	/**
	 * @return {@link Integer#valueOf(String)} unless it throws a {@code NumberFormatException}, in which case it returns null.
	 * @see Integer#valueOf(String)
	 */
	public static Integer intParseSafe(String string)
	{
		try
		{
			return Integer.valueOf(string);
		}
		catch(NumberFormatException e)
		{
			return null;
		}
	}
	
	/**
	 * @return {@link Long#valueOf(String)} unless it throws a {@code NumberFormatException}, in which case it returns null.
	 * @see Long#valueOf(String)
	 */
	public static Long longParseSafe(String string)
	{
		try
		{
			return Long.valueOf(string);
		}
		catch(NumberFormatException e)
		{
			return null;
		}
	}
}
