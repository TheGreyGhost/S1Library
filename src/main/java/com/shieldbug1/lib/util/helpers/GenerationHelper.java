package com.shieldbug1.lib.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import com.shieldbug1.lib.area.WorldPosition;

public final class GenerationHelper
{
	private GenerationHelper(){}
	
	public static void generateSphere(WorldPosition pos, Block block, double radius)
	{
		generateSphere(pos.getWorld(), pos.x, pos.y, pos.z, block, radius);
	}
	
	public static void generateSphere(World world, int x, int y, int z, Block block, double radius)
	{
		radius += 0.5D;
		double radiusSquared = radius * radius;

		int roundRadius = (int)Math.ceil(radius);
		for (int i = 0; i <= roundRadius; i++)
		{
			for (int j = 0; j <= roundRadius; j++)
			{
				for (int k = 0; k <= roundRadius; k++)
				{
					double distanceSquared = i*i + j*j + k*k;
					if (distanceSquared <= radiusSquared)
					{
						world.setBlock(x + i, y + j, z + k, block);
						world.setBlock(x - i, y + j, z + k, block);
						world.setBlock(x + i, y - j, z + k, block);
						world.setBlock(x + i, y + j, z - k, block);
						world.setBlock(x - i, y - j, z + k, block);
						world.setBlock(x + i, y - j, z - k, block);
						world.setBlock(x - i, y + j, z - k, block);
						world.setBlock(x - i, y - j, z - k, block);
					}
				}
			}
		}
	}
	
	public static void generateCube(WorldPosition centre, Block block, int radius)
	{
		for(int i = -radius; i <= radius; i++)
		{
			for(int j = -radius; j <= radius; j++)
			{
				for(int k = -radius; k <= radius; k++)
				{
					centre.getWorld().setBlock(centre.x + i, centre.y + j, centre.z + k, block);
				}
			}
		}
	}
}
