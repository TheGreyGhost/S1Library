package com.shieldbug1.lib.render;

import static org.lwjgl.opengl.GL11.*;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.hash.TIntHashSet;

/**
 * TODO this is a draft for usuage so I don't forget until I write the JavaDoc
 * 
 * In a render class:
 * 
 * private static final GLState state = new GLState()
 *                                                  .enabled(GL_WHATEVER, GL_I_WANT_THIS_ONE, GL_THIS_ONE_TOO)
 *                                                  .disabled(GL_SOMETHING, GL_THIS_NEEDS_TO_BE_OFF, GL_GO_AWAY);
 *                                                  
 *                                                  
 * public void render(someargs)
 * {
 *  //setup stuff
 *  state.enableState();
 *  //drawing
 *  state.disableState();
 *  //cleanup
 * }
 * 
 * 
 * This allows for easier managment of which things are enabled in GL11, and which aren't.
 * 
 * 
 */
public class GLState //TODO javadoc
{
	private static final TIntProcedure ENABLE = new TIntProcedure()
	{
		@Override	public boolean execute(int value)	{	glEnable(value);	return true;	}
	}; //XXX Java 8, use method references
	
	private static final TIntProcedure DISABLE = new TIntProcedure()
	{
		@Override	public boolean execute(int value)	{	glDisable(value);	return true;	}
	};//XXX Java 8, use method references
	
	
	private final TIntHashSet ENABLED, DISABLED;
	
	public GLState()
	{
		this.ENABLED = this.DISABLED = new TIntHashSet();
	}
	
	/**
	 * Sets all states given as enabled.
	 * @param states - the states to enable
	 * @return an instance of this (follows the Builder pattern).
	 */
	public GLState enabled(int... states)
	{
		this.ENABLED.addAll(states);
		return this;
	}
	
	/**
	 * Sets all states given as enabled.
	 * @param states - the states to enable
	 * @return an instance of this (follows the Builder pattern).
	 */
	public GLState disabled(int... states)
	{
		this.DISABLED.addAll(states);
		return this;
	}
	
	/**
	 * Enables the state. Call before rendering.
	 */
	public void enableState()
	{
		this.ENABLED.forEach(ENABLE);//XXX Java 8
		this.DISABLED.forEach(DISABLE);//XXX Java 8
	}
	
	/**
	 * Disables the state. Call after rendering.
	 */
	public void disableState()
	{
		this.ENABLED.forEach(DISABLE);//XXX Java 8
		this.DISABLED.forEach(ENABLE);//XXX Java 8
	}
}
