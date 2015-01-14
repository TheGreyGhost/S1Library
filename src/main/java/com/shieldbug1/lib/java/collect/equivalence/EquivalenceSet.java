package com.shieldbug1.lib.java.collect.equivalence;

import static com.shieldbug1.lib.util.CoreFunctions.checkNotNull;

import java.util.*;

import com.google.common.base.*;
import com.google.common.base.Equivalence.Wrapper;

public class EquivalenceSet<T> extends AbstractSet<T> //TODO test and JavaDoc
{
	private final Set<Wrapper<T>> set;
	private final Equivalence<T> equivalence;
	private final Class<T> classType;
	
	public EquivalenceSet(Supplier<? extends Set<Wrapper<T>>> supplier, Equivalence<T> equivalence, Class<T> clazz)
	{
		this.set = checkNotNull(supplier, "Supplier cannot be null!").get();
		this.classType = checkNotNull(clazz, "Class cannot be null!");
		this.equivalence = checkNotNull(equivalence, "Equivalence cannot be null!");
	}
	
	@Override
	public int size()
	{
		return this.set.size();
	}

	@Override
	public boolean isEmpty()
	{
		return this.set.isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		if(this.classType.isInstance(o))
		{
			return this.set.contains(this.equivalence.wrap((T)o));
		}
		return false;
	}

	@Override
	public Object[] toArray()
	{
		Wrapper<T>[] wrappers = (Wrapper<T>[]) this.set.toArray();
		int size = wrappers.length;
		Object[] array = new Object[size];
		for(int i = 0; i < size; i++)
		{
			array[i] = wrappers[i].get();
		}
		return array;
	}

	@Override
	public <E> E[] toArray(E[] a)
	{
		int size = this.set.size();
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
		return this.set.add(this.equivalence.wrap(e));
	}

	@Override
	public boolean remove(Object o)
	{
		if(this.classType.isInstance(o))
		{
			return this.set.remove(this.equivalence.wrap((T)o));
		}
		return false;	
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
			changed = changed | this.set.add(this.equivalence.wrap(t));
			// Use & instead of && because otherwise not all elements will get added.
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		throw new UnsupportedOperationException(); //TODO implement
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		boolean changed = false;
		for(Object o : c)
		{
			changed = changed | this.remove(o);
		}
		return changed;
	}

	@Override
	public void clear()
	{
		this.set.clear();
	}

	@Override
	public Iterator<T> iterator()
	{
		return new Itr();
	}
	
	private class Itr implements Iterator<T>
	{
		private Iterator<Wrapper<T>> iterator = EquivalenceSet.this.set.iterator();
		
		@Override
		public boolean hasNext()
		{
			return this.iterator.hasNext();
		}

		@Override
		public T next()
		{
			return this.iterator.next().get();
		}

		@Override
		public void remove()
		{
			this.iterator.remove();
		}
		
	}

}
