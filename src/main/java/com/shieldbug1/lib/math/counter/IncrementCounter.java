package com.shieldbug1.lib.math.counter;

import static com.shieldbug1.lib.util.CoreFunctions.checkArgument;

public class IncrementCounter implements Counter
{
	private final int increment;
	private final int tickMax;
	private int currentTick = 0;
	
	IncrementCounter(int increment, int tickMax)
	{
		this.increment = checkArgument(increment > 0, increment, "Incremement must be greater than 0.");
		this.tickMax = checkArgument(tickMax != 0, tickMax, "Tick count must not be 0.");
	}

	@Override
	public boolean tick()
	{
		this.currentTick += increment;
		if(this.tickMax > 0 && this.currentTick >= this.tickMax)
		{
			this.currentTick = 0;
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
		return this.currentTick;
	}
}
