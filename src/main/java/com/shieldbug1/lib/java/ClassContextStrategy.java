package com.shieldbug1.lib.java;

import sun.reflect.Reflection;

interface ClassContextStrategy
{
	public abstract Class<?> getCallingClass(int index);
	
	static class AccessibilityManager extends SecurityManager implements ClassContextStrategy
	{
		@Override
		public Class<?>[] getClassContext()
		{
			return super.getClassContext();
		}

		@Override
		public Class<?> getCallingClass(int index)
		{
			return this.getClassContext()[index + 4];
		}
	}
	
	static class ReflectionStrategy implements ClassContextStrategy
	{
		@Override
		public Class<?> getCallingClass(int index)
		{
			return Reflection.getCallerClass(index + 4);
		}
	}
}
