package com.shieldbug1.lib.math.counter;

import static com.shieldbug1.lib.util.CoreFunctions.checkArgument;

/**
 * An implementation of {@link Counter} that executes a Runnable every time its parent {@link Counter} 
 * returns true on {@link Counter#tick()}.
 */
public class RunnableCounter implements Counter
{
	private final Counter parent;
	private final Runnable runnable;
	
	RunnableCounter(Counter counter, Runnable runnable)
	{
		this.parent = checkArgument(counter != null, counter, "Parent counter must not be null.");
		this.runnable = checkArgument(runnable != null, runnable, "Runnable must not be null.");
	}
	
	
	@Override
	public boolean tick()
	{
		if(this.parent.tick())
		{
			this.runnable.run();
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int getCount()
	{
		return this.parent.getCount();
	}

}
