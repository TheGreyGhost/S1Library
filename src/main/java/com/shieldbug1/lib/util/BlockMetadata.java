package com.shieldbug1.lib.util;

import static com.shieldbug1.lib.util.CoreFunctions.checkArgument;

/**
 * A bit manipulation class specific to block metadata.
 * It can be used both when setting metadata, and when reading metadata.
 * 
 * @deprecated use the new BlockStates stuff (damn it, 1.8).
 */
@Deprecated
public class BlockMetadata
{
	private int meta;

	/**
	 * Constructor mainly for writing purposes. This constructs a new BlockMetadata object which has metadata 0.
	 */
	public BlockMetadata()
	{
		this(0);
	}
	
	/**
	 * Constructor mainly for reading purposes.
	 * @param startingMeta - the starting metadata. Must be between 0 and 15 (inclusive).
	 */
	public BlockMetadata(int startingMeta)
	{
		this.meta = checkArgument(0 <= startingMeta && startingMeta < 16, startingMeta, "Block Metadata must between 0 and 15!");
	}
	
	/**
	 * Turns on the specified flag in the metadata.
	 * @param flag - the flag to turn on.
	 */
	public void setFlagOn(BlockFlag flag)
	{
		this.meta |= 1 << flag.ordinal();
	}
	
	/**
	 * Turns off the specified flag in the metadata.
	 * @param flag - the flag to turn off.
	 */
	public void setFlagOff(BlockFlag flag)
	{
		this.meta &= ~(1 << flag.ordinal());
	}
	
	/**
	 * Toggles the specified flag in the metadata.
	 * @param flag - the flag to toggle.
	 */
	public void toggleFlag(BlockFlag flag)
	{
		this.meta ^= 1 << flag.ordinal();
	}
	
	/**
	 * Checks whether the specified flag is on or not in the metadata.
	 * @param flag - the flag to check.
	 * @return true if the given flag is present in the metadata.
	 */
	public boolean isFlagOn(BlockFlag flag)
	{
		final int mask = 1 << flag.ordinal();
		return (this.meta & mask) == mask;
	}
	
	/**
	 * Used with writing to get the integer metadata itself to use with minecraft method.
	 * @return the metadata.
	 */
	public int toMetadata()
	{
		return this.meta;
	}
	
	public static enum BlockFlag
	{
		ONE, TWO, THREE, FOUR;
	}
}
