package com.shieldbug1.lib.math.counter;
/**
 * An interface that describes a counter. Useful for only doing something every so often. Example usuage:
 * <pre>
 * final Counter counter = ...;
 * 
 * public void someMethod()
 * {
 *   if(counter.tick())
 *   {
 *       doSomething();
 *   }
 *   else
 *   {
 *       doSomethingElse(counter.getCount());
 *   }
 * }
 * </pre>
 */
public interface Counter
{
	/**
	 * A method that increments the current count.
	 * @return whether the counter reached it's goal (e.g. resetting when reaching a certain value, or passing a certain value).
	 */
	public abstract boolean tick();
	/**
	 * @return the current count of the counter.
	 */
	public abstract int getCount();
}
