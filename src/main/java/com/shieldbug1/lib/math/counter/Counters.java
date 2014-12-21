package com.shieldbug1.lib.math.counter;

public class Counters //TODO merge with Counter class in Java 1.8
{
	private Counters(){}

	private static final Counter EMPTY_COUNTER = new Counter()
	{
		@Override public boolean tick(){ return false; }
		@Override public int getCount(){ return 0; }
	};
	
	/**
	 * Returns a new instance of {@link TickCounter}.
	 * @param tickMax - the maximum value of the tick counter before it resets (exclusive).
	 * If this number is negative, the counter never resets. Must not equal 0.
	 * @return a new {@link TickCounter}.
	 */
	public static TickCounter newTickCounter(int tickMax)
	{
		return new TickCounter(tickMax);
	}
	
	/**
	 * Wraps the given counter in a {@link SynchronisedWrapper}, which synchronises on every method call.
	 * @param counter - the counter to wrap.
	 * @return the {@link SynchronisedWrapper}.
	 */
	public static SynchronisedWrapper synchronised(Counter counter)
	{
		return new SynchronisedWrapper(counter);
	}
	
	/**
	 * Returns a new instance of {@link IncrementCounter}.
	 * @param increment - how much the counter increments by each time. Must be greater than 0.
	 * @param tickMax - the maximum value of the tick counter before it resets (exclusive).
	 * If this number is negative, the counter never resets. Must not equal 0.
	 * @return a new {@link IncrementCounter}.
	 */
	public static IncrementCounter newIncrementCounter(int increment, int tickMax)
	{
		return new IncrementCounter(increment, tickMax);
	}
	
	/**
	 * A counter that runs a {@code Runnable} whenever the parent's {@code tick()} method returns true.
	 * @param parent - the counter to delegate the method calls to.
	 * @param runnable - what to run after the parent returns true on {@code tick()}.
	 * @return a {@link RunnableCounter} wrapper around the given counter.
	 */
	public static RunnableCounter newRunnableCounter(Counter parent, Runnable runnable)
	{
		return new RunnableCounter(parent, runnable);
	}
	
	/**
	 * @return a counter that always returns false on {@code tick()} and 0 on {@code getCount()}.
	 */
	public static Counter emptyCounter()
	{
		return EMPTY_COUNTER;
	}
}
