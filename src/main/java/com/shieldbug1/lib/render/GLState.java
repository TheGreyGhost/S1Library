package com.shieldbug1.lib.render;

import static com.shieldbug1.lib.render.GLState.ActivationState.DISABLED;
import static com.shieldbug1.lib.render.GLState.ActivationState.ENABLED;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.Collection;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.collect.*;

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
	private final Multimap<ActivationState, Integer> stateMap;
	
	public GLState()
	{
		this.stateMap = Multimaps.newSetMultimap(Maps.<ActivationState, Collection<Integer>>newEnumMap(ActivationState.class),
				new Supplier<Set<Integer>>()
				{
			@Override
			public Set<Integer> get()
			{
				return Sets.newIdentityHashSet();
			}} //XXX java 8 use method reference + kill stupid non-implicit typing (because Set<Integer> isn't Collection<Integer> ????????????
		);
	}
	
	/**
	 * Sets all states given as enabled.
	 * @param states - the states to enable
	 * @return an instance of this (follows the Builder pattern).
	 */
	public GLState enabled(int... states)
	{
		for(int state : states)
		{
			this.stateMap.put(ENABLED, state);
		}
		return this;
	}
	
	/**
	 * Sets all states given as enabled.
	 * @param states - the states to enable
	 * @return an instance of this (follows the Builder pattern).
	 */
	public GLState disabled(int... states)
	{
		for(int state : states)
		{
			this.stateMap.put(DISABLED, state);
		}
		return this;
	}
	
	public void enableState()
	{
		for(ActivationState activationState : ActivationState.values())
		{
			for(int state : this.stateMap.get(activationState))
			{
				activationState.apply(state);
			}
		}
	}
	
	public void disableState()
	{
		for(ActivationState activationState : ActivationState.values())
		{
			for(int state : this.stateMap.get(activationState))
			{
				activationState.unapply(state);
			}
		}
	}
	
	
	
	public static enum ActivationState //XXX Java 8 method references over abstract implementation
	{
		ENABLED
		{
			@Override
			public void apply(int state)	{	glEnable(state);	}
			
			@Override
			public void unapply(int state)	{	glDisable(state);	}
		},
		DISABLED
		{
			@Override
			public void apply(int state)	{	glDisable(state);	}
			
			@Override
			public void unapply(int state)	{	glEnable(state);	}
		};
		
		public abstract void apply(int state);
		public abstract void unapply(int state);
	}
}
