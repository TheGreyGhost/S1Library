package com.shieldbug1.lib.java.collect;

import static com.shieldbug1.lib.util.CoreFunctions.checkNotNull;

import java.util.*;

import com.google.common.base.*;
import com.google.common.base.Equivalence.Wrapper;

public class EquivalenceList<T> extends AbstractList<T> //TODO test
{
	private final List<Wrapper<T>> list;
	private final Equivalence<T> equivalence;
	private final Class<T> classType;
	
	public EquivalenceList(Supplier<? extends List<Wrapper<T>>> supplier, Equivalence<T> equivalence, Class<T> clazz)
	{
		this.list = checkNotNull(supplier, "Supplier cannot be null!").get();
		this.classType = checkNotNull(clazz, "Class cannot be null!");
		this.equivalence = checkNotNull(equivalence, "Equivalence cannot be null!");
	}

	@Override
	public int size()
	{
		return this.list.size();
	}

	@Override
	public boolean contains(Object o) //TODO javadoc on this one, need to specify class must be the same.
	{
		if(this.classType.isInstance(o))
		{
			return this.list.contains(this.equivalence.wrap((T)o)); //Safe
		}
		return false;
	}

	@Override
	public Object[] toArray()
	{
		int size = this.list.size();
		Object[] array = new Object[size];
		for(int i = 0; i < size; i++)
		{
			array[i] = this.list.get(i).get();
		}
		return array;
	}

	@Override
	public <E> E[] toArray(E[] a)
	{
		int size = this.list.size();
		if(a.length < size)
		{
			return (E[]) Arrays.copyOf(this.toArray(), size, a.getClass()); // XXX Is this efficient? The call to this.toArray() seems necessary. 
		}
        System.arraycopy(this.toArray(), 0, a, 0, size);
        if (a.length > size)
        {
            a[size] = null;
        }
        return a;
	}

	@Override
	public boolean add(T e)
	{
		return this.list.add(this.equivalence.wrap(e));
	}

	@Override
	public boolean remove(Object o)
	{
		if(this.classType.isInstance(o))
		{
			return this.list.remove(this.equivalence.wrap((T)o));
		}
		else
		{
			return false;	
		}
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		for(Object o : c)
		{
			if(!this.contains(o))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		boolean changed = false;
		for(T t : c)
		{
			changed = changed & this.list.add(this.equivalence.wrap(t));
			// Use & instead of && because otherwise not all elements will get added.
		}
		return changed;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c)
	{
		int i = index;
		for(T t : c)
		{
			this.list.add(i++, this.equivalence.wrap(t));
		}
		return c.size() > 0;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		boolean changed = false;
		for(Object o : c)
		{
			changed = changed & this.remove(o);
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		throw new UnsupportedOperationException(); //TODO implement
	}

	@Override
	public void clear()
	{
		this.list.clear();
	}

	@Override
	public T get(int index)
	{
		Wrapper<T> wrapper = this.list.get(index);
		return wrapper != null ? wrapper.get() : null;
	}

	@Override
	public T set(int index, T element)
	{
		Wrapper<T> wrapper = this.list.set(index, this.equivalence.wrap(element));
		return wrapper != null ? wrapper.get() : null;
	}

	@Override
	public void add(int index, T element)
	{
		this.list.add(index, this.equivalence.wrap(element));
	}

	@Override
	public T remove(int index)
	{
		Wrapper<T> wrapper = this.list.remove(index);
		return wrapper != null ? wrapper.get() : null;
	}

	@Override
	public int indexOf(Object o)
	{
		if(this.classType.isInstance(o))
		{
			this.list.indexOf(this.equivalence.wrap((T)o));
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o)
	{
		if(this.classType.isInstance(o))
		{
			this.list.lastIndexOf(this.equivalence.wrap((T)o));
		}
		return -1;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex)
	{
		throw new UnsupportedOperationException(); //TODO implement
	}
}
