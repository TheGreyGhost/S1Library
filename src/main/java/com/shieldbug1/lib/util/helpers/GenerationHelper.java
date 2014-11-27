package com.shieldbug1.lib.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public final class GenerationHelper
{
	private GenerationHelper(){}
	
	public static void generateSphere(World world, BlockPos pos, Block block, double radius)
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
						world.setBlockState(pos.add(i, j, k), block.getDefaultState());
						world.setBlockState(pos.add(-i, j, k), block.getDefaultState());
						world.setBlockState(pos.add(i, -j, k), block.getDefaultState());
						world.setBlockState(pos.add(i, j, -k), block.getDefaultState());
						world.setBlockState(pos.add(-i, -j, k), block.getDefaultState());
						world.setBlockState(pos.add(i, -j, -k), block.getDefaultState());
						world.setBlockState(pos.add(-i, j, -k), block.getDefaultState());
						world.setBlockState(pos.add(-i, -j, -k), block.getDefaultState());
					}
				}
			}
		}
	}
	
	public static void generateCube(World world, BlockPos centre, Block block, int radius)
	{
		for(int i = -radius; i <= radius; i++)
		{
			for(int j = -radius; j <= radius; j++)
			{
				for(int k = -radius; k <= radius; k++)
				{
					world.setBlockState(centre.add(i, j, k), block.getDefaultState());
				}
			}
		}
	}
}
