package com.shieldbug1.lib.util;

//import java.time.*;

public enum Holiday //XXX Waiting for Java 8 support. ETA is 1 year+, but one class that does nothing is alright. I might downgrade this to use Java 7 eventually.
{
	/*NEW_YEARS_DAY(Month.JANUARY, 1, 2),
	VALENTINES(Month.FEBRUARY, 13, 14, 15),
	APRIL_FOOLS(Month.APRIL, 1, 2),
	HALLOWEEN(Month.OCTOBER, 30, 31),
	CHRISTMAS_EVE(Month.DECEMBER, 24),
	CHRISTMAS_DAY(Month.DECEMBER, 25, 26),
	NEW_YEARS_EVE(Month.DECEMBER, 30, 31);
	
	private final List<Integer> days;
	private final Month month;
	
	private Holiday(Month month, Integer... days)
	{
		this.days = Lists.newArrayList(days);
		this.month = month;
	}
	
	public boolean isNow()
	{
		return this.days.contains(ZonedDateTime.now(ZoneOffset.UTC).getDayOfMonth()) && ZonedDateTime.now(ZoneOffset.UTC).getMonth().equals(this.month);
	}
	
	public static void checkHolidays()
	{
		for(Holiday holiday : values())
		{
			if(holiday.isNow())
			{
				EventDispatcher.instance().fireHolidayEvent(holiday);
			}
		}
	}*/
}
