package com.shieldbug1.lib.java;

import java.lang.reflect.*;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.common.base.Supplier;
import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * Class to keep all reflective fields and classes that require being accessed at times.
 * For mcp -> srg names, look at C:\Users\{REDACTED}\.gradle\caches\minecraft\net\minecraftforge\forge\{VERSION}\srgs\mcp-srg.srg
 */
public final class ReflectionCache //TODO rewrite as soft-reference cache.
{
	private ReflectionCache(){}
	
	private static final Map<ImmutablePair<Class<?>, String>, SoftReferenceWrapper<Field>> fieldMap = Maps.newHashMap();
	private static final Map<String, SoftReferenceWrapper<Class<?>>> nameToClassMap = Maps.newHashMap();
	
	/**
	 * Returns a field from the reflection cache, and places it in there if necessary.
	 * @param clazz - the class the field is declared in.
	 * @param fieldNames - a list of all the field names that can belong to this field (usually mcp and srg).
	 * The first string must be kept consistent through getField calls, as it is what the reflection cache stores it under.
	 * @return the field from the given class under the given name(s).
	 */
	public static Field getField(final Class<?> clazz, final String... fieldNames)
	{
		final ImmutablePair<Class<?>, String> key = ImmutablePair.<Class<?>, String>of(clazz, fieldNames[0]); //XXX Java 8 explicit typing.
		SoftReferenceWrapper<Field> field = fieldMap.get(key);
		if(field == null)
		{
			field = new SoftReferenceWrapper<Field>(new Supplier<Field>()
			{
				@Override
				public Field get()
				{
					return ReflectionHelper.findField(clazz, fieldNames);
				}
			}); //XXX JAVA 8
			fieldMap.put(key, field);
		}
		return field.get();
	}
	
	/**
	 * Retrieves a class under the given class names from the reflection cache, and places it in there if necessary.
	 * @param classNames - the names of the class to retrieve. 
	 * @return a class if one exists in the reflection cache under this name.
	 */
	public static Class<?> getClass(final String... classNames)
	{
		SoftReferenceWrapper<Class<?>> clazz = nameToClassMap.get(classNames[0]);
		if(clazz == null)
		{
			clazz = new SoftReferenceWrapper<Class<?>>(new Supplier<Class<?>>()
					{
						@Override
						public Class<?> get()
						{
							return ReflectionHelper.getClass(null, classNames);
						}
					}); //XXX JAVA 8
			nameToClassMap.put(classNames[0], clazz);
		}
		return clazz.get();
	}
	
	/**
	 * Gets the value of a field in the reflection cache.
	 * @param clazz - the class the field is declared in.
	 * @param instance - the instance to get the value of this field from, or {@code null} if it is a static field.
	 * @param fieldNames - the names of the field
	 * @return the field's value.
	 */
	public static <T, F> T getFieldValue(Class<? super F> clazz, F instance, String... fieldNames)
	{
		try
		{
			return (T) getField(clazz, fieldNames).get(instance);
		}
		catch (IllegalAccessException e)
		{
			throw Java.<RuntimeException>throwUnchecked(e);//Impossible
		}
	}
	
	/**
	 * Gets the value of a field in the reflection cache.
	 * @param clazz - the class the field is declared in.
	 * @param instance - the instance to set the value of this field from, or {@code null} if it is a static field.
	 * @param value - the value to set the field.
	 * @param fieldNames - the names of the field
	 */
	public static <T, F> void setFieldValue(Class<? super F> clazz, F instance, T value, String... fieldNames)
	{
		try
		{
			getField(clazz, fieldNames).set(instance, value);
		}
		catch (IllegalAccessException e)
		{
			Java.<RuntimeException>throwUnchecked(e);//Impossible
		}
	}
	
	public static <T> T invokeUnchecked(Object instance, Method method, Object... args)
	{
		try
		{
			method.setAccessible(true);
			return (T) method.invoke(instance, args);
		}
		catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			throw Java.<RuntimeException>throwUnchecked(e);
		}
	}
}
