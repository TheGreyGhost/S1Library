package com.shieldbug1.lib.java;

import java.util.*;

import com.google.common.collect.ImmutableMap;

public class Registry<K, V> implements Map<K, V>
{
	private Map<K, V> delegate;
	
	public Registry(Map<K, V> map)
	{
		if(map == null)
		{
			throw new IllegalArgumentException("Delegate map cannot be null.");
		}
		this.delegate = map;
	}

	public int size()
	{
		return delegate.size();
	}

	public boolean isEmpty()
	{
		return delegate.isEmpty();
	}

	public boolean containsKey(Object key)
	{
		return delegate.containsKey(key);
	}

	public boolean containsValue(Object value)
	{
		return delegate.containsValue(value);
	}

	public V get(Object key)
	{
		return delegate.get(key);
	}

	public V put(K key, V value)
	{
		return delegate.put(key, value);
	}

	public V remove(Object key)
	{
		return delegate.remove(key);
	}

	public void putAll(Map<? extends K, ? extends V> m)
	{
		delegate.putAll(m);
	}

	public void clear()
	{
		delegate.clear();
	}

	public Set<K> keySet()
	{
		return delegate.keySet();
	}

	public Collection<V> values()
	{
		return delegate.values();
	}

	public Set<Map.Entry<K, V>> entrySet()
	{
		return delegate.entrySet();
	}

	public boolean equals(Object o)
	{
		return delegate.equals(o);
	}

	public int hashCode()
	{
		return delegate.hashCode();
	}
	
	/**
	 * Makes the underlying map immutable, and stops further modifications to the Registry.
	 */
	public void lockRegistry()
	{
		this.delegate = ImmutableMap.copyOf(this.delegate);
	}
}
