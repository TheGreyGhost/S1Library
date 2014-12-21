package com.shieldbug1.lib.math.counter;

import static com.shieldbug1.lib.util.CoreFunctions.checkArgument;

public class TickCounter implements Counter
{
	/** How often this counter should 'tick' (when should it reset). Ticking goes from 0 to this, exclusive. */
	protected final int tickMax;
	/** Current tick count, between 0 (inclusive) and tickAmount (exclusive).*/
	private int currentTick = 0;

	TickCounter(int tickMax)
	{
		this.tickMax = checkArgument(tickMax != 0, tickMax, "tickAmount must not be equal to 0!");
	}
	
	@Override
	public boolean tick()
	{
		this.currentTick++;
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
