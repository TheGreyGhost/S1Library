package com.shieldbug1.lib.math;

import java.util.Random;
/**
 * A Utility class for common mathematical operations.
 */
public final class MathUtils //XXX finish javadoc
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
	
	/**
	 * @return the value of a to be between the values of max and min, inclusive.
	 */
	public static int clamp(int a, int min, int max)
	{
		return a < min ? min : a > max ? max : a;
	}
	
	/**
	 * @return the value of a to be between the values of max and min, inclusive.
	 */
	public static float clamp(float a, float min, float max)
	{
		return a < min ? min : a > max ? max : a;
	}
	
	/**
	 * @return the value of a to be between the values of max and min, inclusive.
	 */
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
	
	/**
	 * Rounds the double down to nearest integer.
	 * @return the rounded integer.
	 */
	public static int floor(double d)
	{
		int i = (int) d;
		return i > d ? (i - 1) : i;
	}
	
	/**
	 * @return the smallest of the two integers.
	 */
	public static int min(int a, int b)
	{
		return a < b ? a : b;
	}
	
	/**
	 * @return the largest of the two integers.
	 */
	public static int max(int a, int b)
	{
		return a > b ? a : b;
	}
}
