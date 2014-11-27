package com.shieldbug1.lib.java;

import java.lang.reflect.Field;
import java.security.ProtectionDomain;

import net.minecraftforge.fml.relauncher.ReflectionHelper;
import sun.misc.Unsafe;

/**
 * A wrapper class that contains an instance of {@code Unsafe}.
 * This is a convenience wrapper, that delegates all its methods to the {@code Unsafe} singleton.
 * It is strongly preferred to use this class rather than the Unsafe instance itself.
 */
@SuppressWarnings("restriction")
public class UnsafeWrapper
{
	public static final UnsafeWrapper INSTANCE = new UnsafeWrapper();
	
	private final Unsafe unsafe = ReflectionHelper.getPrivateValue(Unsafe.class, null, "theUnsafe");
	
	private UnsafeWrapper(){}

	public int getInt(Object o, long offset)
	{
		return unsafe.getInt(o, offset);
	}

	public void putInt(Object o, long offset, int value)
	{
		unsafe.putInt(o, offset, value);
	}

	public Object getObject(Object o, long offset)
	{
		return unsafe.getObject(o, offset);
	}

	public void putObject(Object o, long offset, Object value)
	{
		unsafe.putObject(o, offset, value);
	}

	public boolean getBoolean(Object o, long offset)
	{
		return unsafe.getBoolean(o, offset);
	}

	public void putBoolean(Object o, long offset, boolean value)
	{
		unsafe.putBoolean(o, offset, value);
	}

	public byte getByte(Object o, long offset)
	{
		return unsafe.getByte(o, offset);
	}

	public void putByte(Object o, long offset, byte value)
	{
		unsafe.putByte(o, offset, value);
	}

	public short getShort(Object o, long offset)
	{
		return unsafe.getShort(o, offset);
	}

	public void putShort(Object o, long offset, short value)
	{
		unsafe.putShort(o, offset, value);
	}

	public char getChar(Object o, long offset)
	{
		return unsafe.getChar(o, offset);
	}

	public void putChar(Object o, long offset, char value)
	{
		unsafe.putChar(o, offset, value);
	}

	public long getLong(Object o, long offset)
	{
		return unsafe.getLong(o, offset);
	}

	public void putLong(Object o, long offset, long value)
	{
		unsafe.putLong(o, offset, value);
	}

	public float getFloat(Object o, long offset)
	{
		return unsafe.getFloat(o, offset);
	}

	public void putFloat(Object o, long offset, float value)
	{
		unsafe.putFloat(o, offset, value);
	}

	public double getDouble(Object o, long offset)
	{
		return unsafe.getDouble(o, offset);
	}

	public void putDouble(Object o, long offset, double value)
	{
		unsafe.putDouble(o, offset, value);
	}

	public byte getByte(long address)
	{
		return unsafe.getByte(address);
	}

	public void putByte(long address, byte value)
	{
		unsafe.putByte(address, value);
	}

	public short getShort(long address)
	{
		return unsafe.getShort(address);
	}

	public void putShort(long address, short value)
	{
		unsafe.putShort(address, value);
	}

	public char getChar(long address)
	{
		return unsafe.getChar(address);
	}

	public void putChar(long address, char value)
	{
		unsafe.putChar(address, value);
	}

	public int getInt(long address)
	{
		return unsafe.getInt(address);
	}

	public void putInt(long address, int value)
	{
		unsafe.putInt(address, value);
	}

	public long getLong(long address)
	{
		return unsafe.getLong(address);
	}

	public void putLong(long address, long value)
	{
		unsafe.putLong(address, value);
	}

	public float getFloat(long address)
	{
		return unsafe.getFloat(address);
	}

	public void putFloat(long address, float value)
	{
		unsafe.putFloat(address, value);
	}

	public double getDouble(long address)
	{
		return unsafe.getDouble(address);
	}

	public void putDouble(long address, double value)
	{
		unsafe.putDouble(address, value);
	}

	public long getAddress(long address)
	{
		return unsafe.getAddress(address);
	}

	public void putAddress(long address, long value)
	{
		unsafe.putAddress(address, value);
	}

	public long allocateMemory(long bytes)
	{
		return unsafe.allocateMemory(bytes);
	}

	public long reallocateMemory(long address, long bytes)
	{
		return unsafe.reallocateMemory(address, bytes);
	}

	public void setMemory(Object o, long offset, long bytes, byte value)
	{
		unsafe.setMemory(o, offset, bytes, value);
	}

	public void copyMemory(Object src, long srcOffset, Object dest, long destOffset, long bytes)
	{
		unsafe.copyMemory(src, srcOffset, dest, destOffset, bytes);
	}

	public void freeMemory(long address)
	{
		unsafe.freeMemory(address);
	}

	public long staticFieldOffset(Field f)
	{
		return unsafe.staticFieldOffset(f);
	}

	public long objectFieldOffset(Field f)
	{
		return unsafe.objectFieldOffset(f);
	}

	public Object staticFieldBase(Field f)
	{
		return unsafe.staticFieldBase(f);
	}

	public boolean shouldBeInitialized(Class<?> c)
	{
		return unsafe.shouldBeInitialized(c);
	}

	public void ensureClassInitialized(Class<?> c)
	{
		unsafe.ensureClassInitialized(c);
	}

	public int arrayBaseOffset(Class<?> arrayClass)
	{
		return unsafe.arrayBaseOffset(arrayClass);
	}

	public int arrayIndexScale(Class<?> c)
	{
		return unsafe.arrayIndexScale(c);
	}

	public int addressSize()
	{
		return unsafe.addressSize();
	}

	public int pageSize()
	{
		return unsafe.pageSize();
	}

	public Class<?> defineClass(String name, byte[] b, int off, int len, ClassLoader loader, ProtectionDomain protectionDomain)
	{
		return unsafe.defineClass(name, b, off,	len, loader, protectionDomain);
	}

	public Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches)
	{
		return unsafe.defineAnonymousClass(hostClass, data, cpPatches);
	}

	public Object allocateInstance(Class<?> cls) throws InstantiationException
	{
		return unsafe.allocateInstance(cls);
	}

	public void monitorEnter(Object o)
	{
		unsafe.monitorEnter(o);
	}

	public void monitorExit(Object o)
	{
		unsafe.monitorExit(o);
	}

	public boolean tryMonitorEnter(Object o)
	{
		return unsafe.tryMonitorEnter(o);
	}

	public void throwException(Throwable throwable)
	{
		unsafe.throwException(throwable);
	}

	public final boolean compareAndSwapObject(Object o, long offset, Object expected, Object value)
	{
		return unsafe.compareAndSwapObject(o, offset, expected, value);
	}

	public final boolean compareAndSwapInt(Object o, long offset, int expected, int value)
	{
		return unsafe.compareAndSwapInt(o, offset, expected, value);
	}

	public final boolean compareAndSwapLong(Object o, long offset, long expected, long value)
	{
		return unsafe.compareAndSwapLong(o, offset, expected, value);
	}

	public Object getObjectVolatile(Object o, long offset)
	{
		return unsafe.getObjectVolatile(o, offset);
	}

	public void putObjectVolatile(Object o, long offset, Object value)
	{
		unsafe.putObjectVolatile(o, offset, value);
	}

	public int getIntVolatile(Object o, long offset)
	{
		return unsafe.getIntVolatile(o, offset);
	}

	public void putIntVolatile(Object o, long offset, int value)
	{
		unsafe.putIntVolatile(o, offset, value);
	}

	public boolean getBooleanVolatile(Object o, long offset)
	{
		return unsafe.getBooleanVolatile(o, offset);
	}

	public void putBooleanVolatile(Object o, long offset, boolean value)
	{
		unsafe.putBooleanVolatile(o, offset, value);
	}

	public byte getByteVolatile(Object o, long offset)
	{
		return unsafe.getByteVolatile(o, offset);
	}

	public void putByteVolatile(Object o, long offset, byte value)
	{
		unsafe.putByteVolatile(o, offset, value);
	}

	public short getShortVolatile(Object o, long offset)
	{
		return unsafe.getShortVolatile(o, offset);
	}

	public void putShortVolatile(Object o, long offset, short value)
	{
		unsafe.putShortVolatile(o, offset, value);
	}

	public char getCharVolatile(Object o, long offset)
	{
		return unsafe.getCharVolatile(o, offset);
	}

	public void putCharVolatile(Object o, long offset, char value)
	{
		unsafe.putCharVolatile(o, offset, value);
	}

	public long getLongVolatile(Object o, long offset)
	{
		return unsafe.getLongVolatile(o, offset);
	}

	public void putLongVolatile(Object o, long offset, long value)
	{
		unsafe.putLongVolatile(o, offset, value);
	}

	public float getFloatVolatile(Object o, long offset)
	{
		return unsafe.getFloatVolatile(o, offset);
	}

	public void putFloatVolatile(Object o, long offset, float value)
	{
		unsafe.putFloatVolatile(o, offset, value);
	}

	public double getDoubleVolatile(Object o, long offset)
	{
		return unsafe.getDoubleVolatile(o, offset);
	}

	public void putDoubleVolatile(Object o, long offset, double value)
	{
		unsafe.putDoubleVolatile(o, offset, value);
	}

	public void putOrderedObject(Object o, long offset, Object value)
	{
		unsafe.putOrderedObject(o, offset, value);
	}

	public void putOrderedInt(Object o, long offset, int value)
	{
		unsafe.putOrderedInt(o, offset, value);
	}

	public void putOrderedLong(Object o, long offset, long value)
	{
		unsafe.putOrderedLong(o, offset, value);
	}

	public void unpark(Object thread)
	{
		unsafe.unpark(thread);
	}

	public void park(boolean isAbsolute, long time)
	{
		unsafe.park(isAbsolute, time);
	}

	public int getLoadAverage(double[] loadavg, int nelems)
	{
		return unsafe.getLoadAverage(loadavg, nelems);
	}

	/*public final int getAndAddInt(Object o, long offset, int delta)
	{
		return unsafe.getAndAddInt(o, offset, delta);
	}

	public final long getAndAddLong(Object o, long offset, long delta)
	{
		return unsafe.getAndAddLong(o, offset, delta);
	}

	public final int getAndSetInt(Object o, long offset, int newValue)
	{
		return unsafe.getAndSetInt(o, offset, newValue);
	}

	public final long getAndSetLong(Object o, long offset, long newValue)
	{
		return unsafe.getAndSetLong(o, offset, newValue);
	}

	public final Object getAndSetObject(Object o, long offset, Object newValue)
	{
		return unsafe.getAndSetObject(o, offset, newValue);
	}

	public void loadFence()
	{
		unsafe.loadFence();
	}

	public void storeFence()
	{
		unsafe.storeFence();
	}

	public void fullFence()
	{
		unsafe.fullFence();
	}*///XXX JAVA 8
}