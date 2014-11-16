package com.shieldbug1.lib.util;

import com.google.common.base.Function;
import com.google.common.base.Supplier;


/**
 * This class contains some Function wrapper methods, as well as a few methods that can be used as functions.
 * It is a miscellaneous collection of methods and functions.
 */
public final class CoreFunctions
{
	private CoreFunctions(){}
	
	/**
	 * A method to wrap a function so that it will never return null.
	 * @param function - the function to wrap.
	 * @param supplier - the supplier to return the value that should be returned in case the given function returns null.
	 * @return an identical function to the given one, which will return the given supplier.get() instead of null.
	 */
	public static <F, T> Function<F, T> nullDefaultFunction(Function<F, T> function, Supplier<? extends T> supplier)
	{
		return new NullDefaultFunction(function, supplier);
	}
	
	private static class NullDefaultFunction<F, T> implements Function<F, T>
	{
		private final Function<F, T> function;
		private final Supplier<? extends T> supplier;
		
		public NullDefaultFunction(Function<F, T> function, Supplier<? extends T> supplier)
		{
			this.function = function;
			this.supplier = supplier;
		}
		
		@Override
		public T apply(F input)
		{
			T value = this.function.apply(input);
			return value != null ? value : this.supplier.get();
		}
	}
	
	/**
	 * A method to wrap a function so that null is returned when an exception is thrown.
	 * This is useful with safe-parsing, for example, when you don't want a formatting exception thrown.
	 * @param function the function to wrap.
	 * @return the wrapped function that will never throw an exception.
	 */
	public static <F, T> Function<F, T> exceptionSwallowFunction(Function<F, T> function)
	{
		return new NoExceptionFunction(function);
	}
	
	private static class NoExceptionFunction<F, T> implements Function<F, T>
	{
		private final Function<F, T> function;
		
		public NoExceptionFunction(Function<F, T> function)
		{
			this.function = function;
		}

		@Override
		public T apply(F input)
		{
			try
			{
				return this.function.apply(input);
			}
			catch(Exception e)
			{
				return null;
			}
		}
	}
	
	/**
	 * A method to wrap a function so that it synchronises on the input, whenever it is applied.
	 * @param function - the function to wrap as a SynchronisedFunction.
	 * @return the wrapped function that will synchronise on the input.
	 */
	public static <F, T> Function<F, T> synchronised(Function<F, T> function)
	{
		return new SynchronisedFunction(function);
	}
	
	private static class SynchronisedFunction<F, T> implements Function<F, T>
	{
		private final Function<F, T> function;

		public SynchronisedFunction(Function<F, T> function)
		{
			this.function = function;
		}

		@Override
		public T apply(F input)
		{
			synchronized(input)
			{
				return this.function.apply(input);
			}
		}
	}
	
	public static <T> T checkArgument(boolean condition, T reference, String message)
	{
		if(!condition)
		{
			throw new IllegalArgumentException(message);
		}
		else
		{
			return reference;
		}
	}
	
	public static <T> T checkState(boolean state, T reference, String message)
	{
		if(!state)
		{
			throw new IllegalStateException(message);
		}
		else
		{
			return reference;
		}
	}
}