package com.shieldbug1.lib.area;

import static com.shieldbug1.lib.math.MathUtils.max;
import static com.shieldbug1.lib.math.MathUtils.min;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.collect.Lists;
/**
 * An Immutable class that represents an area in 3 dimensional space defined by a max and min point.
 */
public final class AreaAABB
{
	private int minX, minY, minZ, maxX, maxY, maxZ;
	
	/**
	 * Constructs a new AreaAABB. Params are minX, minY, minZ, maxX, maxY and maxZ.
	 */
	public AreaAABB(int x1, int y1, int z1, int x2, int y2, int z2)
	{
		this.minX = min(x1, x2);
		this.maxX = max(x1, x2);
		this.minY = min(y1, y2);
		this.maxY = max(y1, y2);
		this.minZ = min(z1, z2);
		this.maxZ = max(z1, z2);
	}
	
	/**
	 * Construct a new AreaAABB by taking the max and min points.
	 */
	public AreaAABB(Point a, Point b)
	{
		this(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
	}
	
	/**
	 * Constructs a new AreaAABB by taking a centre point and adding/subtracting the x/z radius or y radius.
	 */
	public AreaAABB(Point centre, int xzRad, int yRad)
	{
		this(centre.x() - xzRad, centre.y() - yRad, centre.z() - xzRad, centre.x() + xzRad, centre.y() + yRad, centre.z() + xzRad);
	}
	
	/**
	 * @return the minimum point of this AreaAABB.
	 */
	public Point minPoint()
	{
		return new Point(this.minX, this.minY, this.minZ);
	}
	
	/**
	 * @return the maximum point of this AreaAABB.
	 */
	public Point maxPoint()
	{
		return new Point(this.maxX, this.maxY, this.maxZ);
	}
	
	/**
	 * @return a list of all points contained by this AreaAABB.
	 */
	public List<Point> getPoints()
	{
		List<Point> points = Lists.newLinkedList();
		for(int x = this.minX; x < this.maxX; x++)
		{
			for(int y = this.minY; y < this.maxY; y++)
			{
				for(int z = this.minZ; z < this.maxZ; z++)
				{
					points.add(new Point(x, y, z));
				}
			}
		}
		return points;
	}
	
	/**
	 * @return an AxisAlignedBB instance that represents the same area in world as this AreaAABB.
	 */
	public AxisAlignedBB toAABB()
	{
		return AxisAlignedBB.fromBounds(this.minX, this.minY, this.minZ, this.maxX + 1, this.maxY + 1, this.maxZ + 1);
	}
	
	/**
	 * @see Point#validate()
	 */
	public void validate()
	{
		Point min = this.minPoint();
		Point max = this.maxPoint();
		min.validate();
		max.validate();
		
		this.minX = min.x();
		this.minY = min.y();
		this.minZ = min.z();
		this.maxX = max.x();
		this.maxY = max.y();
		this.maxZ = max.z();
	}
	
	/**
	 * @return the size of this AreaAABB. Useful when you need to know how many points are contained but
	 * don't want to create a list of points with {@link #getPoints()}. 
	 */
	public int size()
	{
		return (this.maxX - this.minX) * (this.maxY - this.minY) * (this.minZ - this.maxZ);
	}
	
	/* NBT */
	/**
	 * Writes the AreaAABB to an NBTTagCompound.
	 * @param compound - the compound to write to.
	 */
	public void writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		this.minPoint().writeToNBT(nbt); //write details to new tag.
		compound.setTag("min", nbt); //write point to min.
		
		nbt = new NBTTagCompound(); // new tag.
		this.maxPoint().writeToNBT(nbt); //write max point to new tag.
		compound.setTag("max", nbt); //write point to min.
	}
	
	/**
	 * Loads an AreaAABB from an NBTTagCompound.
	 * @param compound - the compound to load from.
	 * @return the loaded AreaAABB.
	 * @see Point#loadFromNBT(NBTTagCompound)
	 */
	public AreaAABB loadFromNBT(NBTTagCompound compound)
	{
		return new AreaAABB(Point.loadFromNBT(compound.getCompoundTag("min")), Point.loadFromNBT(compound.getCompoundTag("max")));
	}
	
	
	
	/* HASHING AND EQUALS */
	
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof AreaAABB))
		{
			return false;
		}
		AreaAABB area = (AreaAABB) other;
		return this.minPoint().equals(area.minPoint()) && this.maxPoint().equals(area.maxPoint());
	}
	
	@Override
	public String toString()
	{
		return String.format("AreaAABB[min%s, max%s]", this.minPoint(), this.maxPoint());
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder()
		.append(this.minX)
		.append(this.minY)
		.append(this.minZ)
		.append(this.maxX)
		.append(this.maxY)
		.append(this.maxZ)
		.toHashCode();
	}
}
