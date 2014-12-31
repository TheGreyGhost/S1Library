package com.shieldbug1.lib.java.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.collect.Maps;

public class TaskMap<K, V> //TODO javadoc
{
	private final Supplier<? extends Map<K, V>> supplier;
	private Map<K, V> map;
	
	public TaskMap()
	{
		this(new Supplier<Map<K, V>>(){

			@Override
			public Map<K, V> get() {
				return Maps.newHashMap();
			}} //XXX JAVA 8 method reference
		);
	}
	
	public TaskMap(Supplier<? extends Map<K, V>> supplier)
	{
		this.supplier = checkNotNull(supplier, "Supplier of TaskMap can't be null!");
		this.refreshMap();
	}
	
	public void add(K key, V value)
	{
		synchronized(this.map)
		{
			this.map.put(key, value);
		}
	}
	
	public void addAll(Map.Entry<K, V>...entries)
	{
		synchronized(this.map)
		{
			for(Map.Entry<K, V> entry : entries)
			{
				this.map.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public Map<K, V> getAll()
	{
		synchronized(this.map)
		{
			Map<K, V> ret = this.map;
			this.refreshMap();
			return ret;
		}
	}

	private void refreshMap()
	{
		this.map = this.supplier.get();
	}
}
