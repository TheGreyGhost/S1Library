package com.shieldbug1.lib.java;

public enum TriState //TODO Javadoc
{
	TRUE(true),
	FALSE(false),
	UNKNOWN(null);
	
	private final Boolean bool;
	
	private TriState(Boolean bool)
	{
		this.bool = bool;
	}
	
	public Boolean toBoolean()
	{
		return this.bool;
	}
	
	public TriState not()
	{
		return this == UNKNOWN ? UNKNOWN : this.bool ? FALSE : TRUE;
	}
	
	public TriState and(TriState state)
	{
		return this == FALSE || state == FALSE ? FALSE : this == TRUE && state == TRUE ? TRUE : UNKNOWN;
	}
	
	public TriState or(TriState state)
	{
		return this == TRUE || state == TRUE ? TRUE : this == FALSE && state == FALSE ? FALSE : UNKNOWN;
	}
	
	public TriState xor(TriState state)
	{
		return this == UNKNOWN || state == UNKNOWN ? UNKNOWN : this == state ? FALSE : TRUE;
	}
	
	public boolean isUnknown()
	{
		return this == UNKNOWN;
	}
	
	public static TriState fromBoolean(Boolean bool)
	{
		if(bool == null)
		{
			return UNKNOWN;
		}
		return bool ? TRUE : FALSE;
	}
}
