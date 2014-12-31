package com.shieldbug1.lib.java.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.collect.Sets;
/**
 * This class was designed in order to allow adding tasks (or any other non-primitive object) to a set in a synchronised way.
 * All objects contained by this class are kept in an internal set.
 * All methods synchronise on this set.
 */
public class TaskSet<T>
{
	private final Supplier<? extends Set<T>> supplier;
	private Set<T> set;
	
	public TaskSet()
	{
		this(new Supplier<Set<T>>()
				{
			@Override
			public Set<T> get()
			{
				return Sets.newHashSet();
			}
		}); //XXX JAVA 8
	}
	
	public TaskSet(Supplier<? extends Set<T>> supplier)
	{
		this.supplier = checkNotNull(supplier, "Supplier of TaskSet must never be null!");
		this.refreshSet();
	}
	
	/**
	 * Adds the given object to the set.
	 * @param t - the object to add.
	 */
	public void add(T thing)
	{
		synchronized(this.set)
		{
			this.set.add(thing);
		}
	}
	
	/**
	 * Adds all the given objects to the set.
	 * This is useful in a case where you might be adding more than one object before retrieving all of them.
	 * @param things - the objects to add.
	 */
	public void addAll(T... things)
	{
		synchronized(this.set)
		{
			for (T t : things)
			{
				this.set.add(t);
			}
		}
	}
	
	/**
	 * Adds all members of a collection to this set, removing them from the collection in the process.
	 * @param collection - the collection to get all items from.
	 */
	public void addAll(Collection<T> collection)
	{
		synchronized(this.set)
		{
			collection.removeAll(this.set);
		}
	}
	
	/**
	 * @return all the objects contained by this set, and clears the set.
	 */
	public Set<T> getAll()
	{
		synchronized(this.set)
		{
			Set<T> temp = this.set;
			this.refreshSet();
			return temp;
		}
	}
	
	private void refreshSet()
	{
		this.set = this.supplier.get();
	}
}
