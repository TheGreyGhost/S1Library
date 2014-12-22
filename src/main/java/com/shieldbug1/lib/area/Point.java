package com.shieldbug1.lib.area;

import static com.shieldbug1.lib.math.MathUtils.floor;

import java.util.Collection;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.shieldbug1.lib.math.MathUtils;

/**
 * A simple point class - note that this class is immutable. All of its methods return new instances.
 */
public final class Point implements Comparable<Point>
{
	private int x, y, z;
	
	/**
	 * Instantiate a new ImmutablePoint
	 * @param x - the x position of this point
	 * @param y - the y position of this point
	 * @param z - the z position of this point.
	 */
	public Point(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @return the x position of this point.
	 */
	public int x()
	{
		return this.x;
	}
	
	/**
	 * @return the y position of this point.
	 */
	public int y()
	{
		return this.y;
	}
	
	/**
	 * @return the z position of this point.
	 */
	public int z()
	{
		return this.z;
	}
	
	/**
	 * Constructs a new point that has coorinates (x+d, y+d, z+d).
	 * @param d - the amount to add to this point.
	 * @return the new Point.
	 */
	public Point add(int d)
	{
		return new Point(this.x + d, this.y + d, this.z + d);
	}
	
	/**
	 * Constructs a new point that has coordinates (x+p.x, y+p.y, z+p.z)
	 * @param p - the Point with the coordinates that should be added to this one.
	 * @return the new Point.
	 */
	public Point add(Point p)
	{
		return new Point(this.x + p.x, this.y + p.y, this.z + p.z);
	}
	
	/**
	 * Calculates the distance squared between this point and the specified point.
	 * @param p - the other point.
	 * @return the squared distance.
	 */
	public double distanceSquared(Point p)
	{
		return (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y) + (this.z - p.z) * (this.z - p.z);
	}
	
	/**
	 * Calculates the distance between this point and the specified point.
	 * Note that if only distance comparison is needed, {@link distanceSquared(Point)} should be used instead as
	 * it is more accurate.
	 * @param p - the other point.
	 * @return the distance.
	 */
	public double distance(Point p)
	{
		return Math.sqrt(distanceSquared(p));
	}
	
	/**
	 * Moves the point in the direction the amount of steps specified.
	 * 
	 * @param direction - the direction to move towards.
	 * @param steps - the amount of steps to move towards the direction. If negative, moves away from direction.
	 * @return the new point.
	 */
	public Point goInDirection(EnumFacing direction, int steps)
	{
		return new Point(this.x + (direction.getFrontOffsetX() * steps), this.y + (direction.getFrontOffsetY() * steps), this.z + (direction.getFrontOffsetZ() * steps));
	}
	
	/**
	 * Moves this point towards the specified point that many amount of steps. Note that if steps is any number
	 * which is not 1, the point may never reach it's target point, just move around it.
	 * 
	 * @param p - the point to move towards.
	 * @param steps - how many steps to move towards. If negative, moves away from point.
	 * @return the new point.
	 */
	public Point moveTowards(Point p, int steps)
	{
		int newX = this.x > p.x ? -steps : this.x < p.x ? steps : 0;
		int newY = this.y > p.y ? -steps : this.y < p.y ? steps : 0;
		int newZ = this.z > p.z ? -steps : this.z < p.z ? steps : 0;
		return new Point(this.x + newX, this.y + newY, this.z + newZ);
	}
	
	/**
	 * Finds the midpoint between the two points.
	 * 
	 * @return a Point half way between the two points.
	 */
	public static Point midPoint(Point a, Point b)
	{
		return new Point(floor((a.x + b.x) / 2D), floor((a.y + b.y) / 2D), floor((a.z + b.z) / 2D));
	}
	
	/**
	 * Finds a point in the centre of all given points.
	 * 
	 * @param points - a collection of all points to find centre of.
	 * @return the centre point.
	 */
	public static Point centrePoint(Collection<Point> points)
	{
		if(points.size() == 1) //Optimises for 1 or 2 points.
		{
			return (Point) points.toArray()[0]; //No need to construct a new point, as Point is Immutable.
		}
		if(points.size() == 2)
		{
			Object[] array = points.toArray();
			return midPoint((Point)array[0], (Point)array[1]);
		}
			
		int x, y, z;
		x = y = z = 0;
		
		for(Point point : points)
		{
			x += point.x;
			y += point.y;
			z += point.z;
		}
		
		return new Point(floor(x / points.size()), floor(y / points.size()), floor(z / points.size()));
	}
	
	/**
	 * Clamps the coordinates of this point to be 'valid', as specified by {@link World#isValid(BlockPos)}.
	 */
	public void validate()
	{
		this.x = MathUtils.clamp(this.x, -30000000, 30000000);//magic numbers are copied from World#setBlock
		this.y = MathUtils.clamp(this.y, 0, 256);
		this.z = MathUtils.clamp(this.z, -30000000, 30000000);
	}
	
	
	/*				NBT					*/
	/**
	 * Writes this point to an NBTTagCompound.
	 * @param compound - the compound to write to.
	 */
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("x", this.x);
		compound.setInteger("y", this.y);
		compound.setInteger("z", this.z);
	}
	
	/**
	 * Loads a Point from NBT. Note that if any of the coordinates are missing in the NBTTagCompound, the misssing
	 * coord is set to 0.
	 * @param compound - the compound to load from.
	 * @return the Point kept by this NBTTagCompound.
	 */
	public static Point loadFromNBT(NBTTagCompound compound)
	{
		return new Point(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
	}
	
	/*		Hashing & Equals			*/
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Point))
		{
			return false;
		}
		Point point = (Point) o;
		return point.x == this.x && point.z == this.z && point.y == this.y;
	}
	
	@Override
	public String toString()
	{
		return String.format("Point(%s, %s, %s)", this.x, this.y, this.z);
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder().append(this.x).append(this.y).append(this.z).toHashCode();
	}

	@Override
	public int compareTo(Point o)
	{
		if(o != null)
		{
			if(this.x < o.x)
			{
				return -1;
			}
			else if(this.x > o.x)
			{
				return 1;
			}
			else // x is equal
			{
				if(this.z < o.z)
				{
					return -1;
				}
				else if(this.z > o.z)
				{
					return 1;
				}
				else
				{
					if(this.y < o.y)
					{
						return -1;
					}
					else if(this.y > o.y)
					{
						return 1;
					}
					else
					{
						return 0;
					}
				}
			}
		}
		else
		{
			return 1;
		}
	}
}
