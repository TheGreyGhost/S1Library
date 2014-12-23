package com.shieldbug1.lib.util.helpers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * A class to help with generation.
 */
public final class GenerationHelper //XXX more helper methods.
{
	private GenerationHelper(){}
	
	/**
	 * Generates a sphere based on given parameters.
	 * @param world - the world to spawn the sphere in.
	 * @param centre - the centre of the sphere.
	 * @param state - the state to generate the sphere out of.
	 * @param radius - the radius of the sphere.
	 */
	public static void generateSphere(World world, BlockPos centre, IBlockState state, double radius)
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
						world.setBlockState(centre.add(i, j, k), state);
						world.setBlockState(centre.add(-i, j, k), state);
						world.setBlockState(centre.add(i, -j, k), state);
						world.setBlockState(centre.add(i, j, -k), state);
						world.setBlockState(centre.add(-i, -j, k), state);
						world.setBlockState(centre.add(i, -j, -k), state);
						world.setBlockState(centre.add(-i, j, -k), state);
						world.setBlockState(centre.add(-i, -j, -k), state);
					}
				}
			}
		}
	}
	
	/**
	 * Generates a cube using the given parameters.
	 * @param world - the world to spawn the cube in.
	 * @param centre - the centre position of the cube.
	 * @param state - the state to generate the cube out of.
	 * @param radius - the radius of the cube.
	 */
	public static void generateCube(World world, BlockPos centre, IBlockState state, int radius)
	{
		for(int i = -radius; i <= radius; i++)
		{
			for(int j = -radius; j <= radius; j++)
			{
				for(int k = -radius; k <= radius; k++)
				{
					world.setBlockState(centre.add(i, j, k), state);
				}
			}
		}
	}
}
