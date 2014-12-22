package com.shieldbug1.lib.java;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
/**
 * This class is a wrapper around the {@link SoftReference<T>} class.
 * The {@link #get()} method, will never return null, unless the supplier returns null.
 */
public final class SoftReferenceWrapper<T> //TODO Javadoc.
{
	private SoftReference<T> reference;
	private final Supplier<? extends T> supplier;
	private final ReferenceQueue queue;
	
	public SoftReferenceWrapper(Supplier<? extends T> supplier, ReferenceQueue queue)
	{
		Preconditions.checkNotNull(supplier, "Weak Reference Wrapper supplier can't be null!");
		this.supplier = supplier;
		this.queue = queue;
		this.reference = new SoftReference<T>(this.supplier.get(), this.queue);
	}
	
	public SoftReferenceWrapper(Supplier<? extends T> supplier)
	{
		this(supplier, null);
	}
	
	public T get()
	{
		T t = reference.get();
		if(t == null)
		{
			t = this.supplier.get(); // We need a strong reference here.
			this.reference = new SoftReference<T>(t, this.queue);
		}
		return this.reference.get(); //can't be null, since we still have a strong reference to 't'.
	}
	
}
