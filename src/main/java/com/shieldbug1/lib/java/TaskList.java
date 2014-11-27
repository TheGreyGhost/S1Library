package com.shieldbug1.lib.java;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
/**
 * This class was designed in order to allow adding tasks (or any other non-primitive object) to a list in a synchronised way.
 * All objects contained by this class are kept in an internal list.
 * All methods synchronise on this list.
 */
public class TaskList<T>
{
	private final Supplier<? extends List<T>> supplier;
	private List<T> list;
	
	public TaskList()
	{
		this(new Supplier<List<T>>()
				{
			@Override
			public List<T> get()
			{
				return Lists.newArrayList();
			}
		}); //XXX JAVA 8
	}
	
	public TaskList(Supplier<? extends List<T>> supplier)
	{
		this.supplier = checkNotNull(supplier, "Supplier of TaskList must never be null!");
		this.refreshList();
	}
	
	/**
	 * Adds the given object to the list.
	 * @param t - the object to add.
	 */
	public void add(T thing)
	{
		synchronized(this.list)
		{
			this.list.add(thing);
		}
	}
	
	/**
	 * Adds all the given objects to the list.
	 * This is useful in a case where you might be adding more than one object before retrieving all of them.
	 * @param things - the objects to add.
	 */
	public void addAll(T... things)
	{
		synchronized(this.list)
		{
			for (T t : things)
			{
				this.list.add(t);
			}
		}
	}
	
	/**
	 * Adds all members of a collection to this list, removing them from the collection in the process.]
	 * @param collection - the collection to get all items from.
	 */
	public void addAll(Collection<T> collection)
	{
		synchronized(this.list)
		{
			collection.removeAll(this.list);
		}
	}
	
	/**
	 * @return all the objects contained by this list, and clears the list.
	 */
	public List<T> getAll()
	{
		synchronized(this.list)
		{
			List<T> temp = this.list;
			this.refreshList();
			return temp;
		}
	}
	
	/**
	 * @return and removes the first (oldest) object inserted into the list.
	 */
	public T getFirst()
	{
		synchronized(this.list)
		{
			return this.list.remove(0);
		}
	}
	
	/**
	 * @return and removes the last (most recent) object inserted into the list.
	 */
	public T getLast()
	{
		synchronized(this.list)
		{
			return this.list.remove(this.list.size() - 1);
		}
	}
	
	
	
	private void refreshList()
	{
		this.list = this.supplier.get();
	}
}
