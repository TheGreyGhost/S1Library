package com.shieldbug1.lib.area;

import static com.shieldbug1.lib.math.MathUtils.floor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.shieldbug1.lib.area.WorldPosition.Validator;
import com.shieldbug1.lib.math.MathUtils;

/**
 * A simple point class - note that this class is immutable. All of its methods return new instances.
 */
public final class Point implements Comparable<Point>
{
	private int x, y, z;
	
	public Point(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int x()
	{
		return this.x;
	}
	
	public int y()
	{
		return this.y;
	}
	
	public int z()
	{
		return this.z;
	}
	
	public Point add(int d)
	{
		return new Point(this.x + d, this.y + d, this.z + d);
	}
	
	public Point add(Point p)
	{
		return new Point(this.x + p.x, this.y + p.y, this.z + p.z);
	}
	
	public double distanceSquared(Point p)
	{
		return (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y) + (this.z - p.z) * (this.z - p.z);
	}
	
	public double distance(Point p)
	{
		return Math.sqrt(distanceSquared(p));
	}
	
	public Point goInDirection(ForgeDirection direction, int steps)
	{
		return new Point(this.x + (direction.offsetX * steps), this.y + (direction.offsetY * steps), this.z + (direction.offsetZ * steps));
	}
	
	public Point moveTowards(Point p, int steps)
	{
		int newX = this.x > p.x ? -steps : this.x < p.x ? steps : 0;
		int newY = this.y > p.y ? -steps : this.y < p.y ? steps : 0;
		int newZ = this.z > p.z ? -steps : this.z < p.z ? steps : 0;
		return new Point(this.x + newX, this.y + newY, this.z + newZ);
	}
	
	public WorldPosition asPosition(World world, Validator validator)
	{
		return new WorldPosition(world, this.x, this.y, this.z, validator);
	}
	
	public static Point midPoint(Point a, Point b)
	{
		return new Point(floor((a.x + b.x) / 2D), floor((a.y + b.y) / 2D), floor((a.z + b.z) / 2D));
	}
	
	public static Point centrePoint(Point... points)
	{
		int x, y, z;
		x = y = z = 0;
		
		for(Point point : points)
		{
			x += point.x;
			y += point.y;
			z += point.z;
		}
		
		return new Point(floor(x / points.length), floor(y / points.length), floor(z / points.length));
	}
	
	public void validate()
	{
		this.x = MathUtils.clamp(this.x, -30000000, 30000000);//magic numbers are copied from World#setBlock
		this.y = MathUtils.clamp(this.y, 0, 256);
		this.z = MathUtils.clamp(this.z, -30000000, 30000000);
	}
	
	
	/*				NBT					*/
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("x", this.x);
		compound.setInteger("y", this.y);
		compound.setInteger("z", this.z);
	}
	
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
		Point other = (Point) o;
		return new EqualsBuilder().append(this.x, other.x).append(this.y, other.y).append(this.z, other.z).isEquals();
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
