package com.shieldbug1.lib.inventory;

import static com.shieldbug1.lib.util.CoreFunctions.checkNotNull;
import net.minecraft.tileentity.TileEntity;

public interface MarkedDirtyListener //TODO javadoc
{
	public static final MarkedDirtyListener NONE = new MarkedDirtyListener()
	{
		@Override public void markDirty()	{	}
	};
	
	
	public abstract void markDirty();
	
	public static class Adapter //XXX static interface method Java 8
	{
		public static MarkedDirtyListener of(TileEntity tileEntity)
		{
			return new TileEntityAdapter(tileEntity);
		}
		
		private static class TileEntityAdapter implements MarkedDirtyListener
		{
			private final TileEntity tileEntity;
			
			private TileEntityAdapter(TileEntity entity)
			{
				this.tileEntity = checkNotNull(entity, "TileEntity cannot be null!");
			}
			
			@Override
			public void markDirty()
			{
				this.tileEntity.markDirty();
			}
			
		}
		
		public static MarkedDirtyListener of(MarkedDirtyListener a, MarkedDirtyListener b)
		{
			return new AndThen(a, b);
		}
		
		private static class AndThen implements MarkedDirtyListener //XXX Java 8 default method
		{
			private final MarkedDirtyListener a, b;
			private AndThen(MarkedDirtyListener a, MarkedDirtyListener b)
			{
				this.a = checkNotNull(a, "MarkedDirtyListener cannot be null!");
				this.b = checkNotNull(b, "MarkedDirtyListener cannot be null!");
			}
			
			@Override
			public void markDirty()
			{
				this.a.markDirty();
				this.b.markDirty();
			}
			
		}
	}
}
