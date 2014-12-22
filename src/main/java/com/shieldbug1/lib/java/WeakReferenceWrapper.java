package com.shieldbug1.lib.java;

import static com.shieldbug1.lib.util.CoreFunctions.checkNotNull;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import com.google.common.base.Supplier;
/**
 * This class is a wrapper around the {@link WeakReference<T>} class.
 * The {@link #get()} method, will never return null, unless the supplier returns null.
 */
public final class WeakReferenceWrapper<T>
{
	private WeakReference<T> reference;
	private final Supplier<? extends T> supplier;
	private final ReferenceQueue queue;
	
	/**
	 * Constructs a new WeakReferenceWrapper
	 * @param supplier - the supplier to supply the reference in the case that gets
	 * garbage collected and needed again later. This shouldn't hold any strong references to the object, but rather be
	 * a method call, or something of the such. Cannot be null.
	 * @param queue - the reference queue to pass to the weak reference constructor. Can be null.
	 */
	public WeakReferenceWrapper(Supplier<? extends T> supplier, ReferenceQueue queue)
	{
		this.supplier = checkNotNull(supplier , "Weak Reference Wrapper supplier can't be null!");
		this.queue = queue;
		this.reference = new WeakReference<T>(this.supplier.get(), this.queue);
	}
	
	/**
	 * @see #WeakReferenceWrapper(Supplier, ReferenceQueue)
	 */
	public WeakReferenceWrapper(Supplier<? extends T> supplier)
	{
		this(supplier, null);
	}
	
	/**
	 * Gets the object contained by this WeakReferenceWrapper, and creates it if it is null.
	 */
	public T get()
	{
		T t = reference.get();
		if(t == null)
		{
			t = this.supplier.get(); // We need a strong reference here.
			this.reference = new WeakReference<T>(t, this.queue);
		}
		return this.reference.get(); //can't be null, since we still have a strong reference to 't'.
	}
	
}
