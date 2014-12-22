package com.shieldbug1.lib.math.counter;

import static com.shieldbug1.lib.util.CoreFunctions.checkArgument;

/**
 * A synchronised implementation of {@link Counter} that wraps around another implementation.
 */
public class SynchronisedWrapper implements Counter
{
	private final Counter parent;
	
	SynchronisedWrapper(Counter parent)
	{
		this.parent = checkArgument(parent != null, parent, "Parent counter must not be null!");
	}

	@Override
	public boolean tick()
	{
		synchronized(this.parent)
		{
			return this.parent.tick();
		}
	}

	@Override
	public int getCount()
	{
		synchronized(this.parent)
		{
			return this.parent.getCount();
		}
	}

}
