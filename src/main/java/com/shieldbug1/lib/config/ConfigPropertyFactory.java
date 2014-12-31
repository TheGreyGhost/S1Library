package com.shieldbug1.lib.config;

import net.minecraftforge.common.config.Configuration;

import com.shieldbug1.lib.config.ConfigProperty.BooleanArrayConfigProperty;
import com.shieldbug1.lib.config.ConfigProperty.BooleanConfigProperty;
import com.shieldbug1.lib.config.ConfigProperty.DoubleArrayConfigProperty;
import com.shieldbug1.lib.config.ConfigProperty.DoubleConfigProperty;
import com.shieldbug1.lib.config.ConfigProperty.IntArrayConfigProperty;
import com.shieldbug1.lib.config.ConfigProperty.IntConfigProperty;
import com.shieldbug1.lib.config.ConfigProperty.StringArrayConfigProperty;
import com.shieldbug1.lib.config.ConfigProperty.StringConfigProperty;

public interface ConfigPropertyFactory //TODO javadoc
{
	BooleanConfigProperty createProperty(Configuration config, String category, String name, boolean defaultValue);
	BooleanConfigProperty createProperty(Configuration config, String category, String name, String comment, boolean defaultValue);
	
	BooleanArrayConfigProperty createProperty(Configuration config, String category, String name, boolean[] defaultValue);
	BooleanArrayConfigProperty createProperty(Configuration config, String category, String name, String comment, boolean[] defaultValue);
	
	IntConfigProperty createProperty(Configuration config, String category, String name, int defaultValue, int min, int max);
	IntConfigProperty createProperty(Configuration config, String category, String name, String comment, int defaultValue, int min, int max);
	
	IntArrayConfigProperty createProperty(Configuration config, String category, String name, int[] defaultValue, int min, int max);
	IntArrayConfigProperty createProperty(Configuration config, String category, String name, String comment, int[] defaultValue, int min, int max);
	
	DoubleConfigProperty createProperty(Configuration config, String category, String name, double defaultValue, double min, double max);
	DoubleConfigProperty createProperty(Configuration config, String category, String name, String comment, double defaultValue, double min, double max);
	
	DoubleArrayConfigProperty createProperty(Configuration config, String category, String name, double[] defaultValue, double min, double max);
	DoubleArrayConfigProperty createProperty(Configuration config, String category, String name, String comment, double[] defaultValue, double min, double max);

	StringConfigProperty createProperty(Configuration config, String category, String name, String defaultValue, String... validValues);
	StringConfigProperty createProperty(Configuration config, String category, String name, String comment, String defaultValue, String... validValues);
	
	StringArrayConfigProperty createProperty(Configuration config, String category, String name, String[] defaultValue, String... validValues);
	StringArrayConfigProperty createProperty(Configuration config, String category, String name, String comment, String[] defaultValue, String... validValues);

	public static class Implementation implements ConfigPropertyFactory
	{

		@Override
		public BooleanConfigProperty createProperty(Configuration config, String category, String name,
				boolean defaultValue)
		{
			return new BooleanConfigProperty(config, category, name, null, defaultValue);
		}

		@Override
		public BooleanConfigProperty createProperty(Configuration config, String category,
				String name, String comment, boolean defaultValue)
		{
			return new BooleanConfigProperty(config, category, name, comment, defaultValue);
		}

		@Override
		public BooleanArrayConfigProperty createProperty(Configuration config, String category,
				String name, boolean[] defaultValue)
		{
			return new BooleanArrayConfigProperty(config, category, name, null, defaultValue);
		}

		@Override
		public BooleanArrayConfigProperty createProperty(Configuration config, String category,
				String name, String comment, boolean[] defaultValue) 
		{
			return new BooleanArrayConfigProperty(config, category, name, comment, defaultValue);
		}

		@Override
		public IntConfigProperty createProperty(Configuration config, String category,
				String name, int defaultValue, int min, int max)
		{
			return new IntConfigProperty(config, category, name, null, defaultValue, min, max);
		}

		@Override
		public IntConfigProperty createProperty(Configuration config, String category, 
				String name, String comment, int defaultValue, int min, int max)
		{
			return new IntConfigProperty(config, category, name, comment, defaultValue, min, max);
		}

		@Override
		public IntArrayConfigProperty createProperty(Configuration config, String category,
				String name, int[] defaultValue, int min, int max)
		{
			return new IntArrayConfigProperty(config, category, name, null, defaultValue, min, max);
		}

		@Override
		public IntArrayConfigProperty createProperty(Configuration config, String category,
				String name, String comment, int[] defaultValue, int min, int max)
		{
			return new IntArrayConfigProperty(config, category, name, comment, defaultValue, min, max);
		}

		@Override
		public DoubleConfigProperty createProperty(Configuration config, String category, String name,
				double defaultValue, double min, double max)
		{
			return new DoubleConfigProperty(config, category, name, null, defaultValue, min, max);
		}

		@Override
		public DoubleConfigProperty createProperty(Configuration config, String category, String name,
				String comment, double defaultValue, double min, double max)
		{
			return new DoubleConfigProperty(config, category, name, comment, defaultValue, min, max);
		}

		@Override
		public DoubleArrayConfigProperty createProperty(Configuration config, String category,
				String name, double[] defaultValue, double min, double max)
		{
			return new DoubleArrayConfigProperty(config, category, name, null, defaultValue, min, max);
		}

		@Override
		public DoubleArrayConfigProperty createProperty(Configuration config, String category,
				String name, String comment, double[] defaultValue, double min, double max)
		{
			return new DoubleArrayConfigProperty(config, category, name, comment, defaultValue, min, max);
		}

		@Override
		public StringConfigProperty createProperty(Configuration config, String category, String name,
				String defaultValue, String... validValues)
		{
			return new StringConfigProperty(config, category, name, null, defaultValue, validValues);
		}

		@Override
		public StringConfigProperty createProperty(Configuration config, String category,
				String name, String comment, String defaultValue, String... validValues)
		{
			return new StringConfigProperty(config, category, name, comment, defaultValue, validValues);
		}

		@Override
		public StringArrayConfigProperty createProperty(Configuration config, String category,
				String name, String[] defaultValue, String... validValues)
		{
			return new StringArrayConfigProperty(config, category, name, null, defaultValue, validValues);
		}

		@Override
		public StringArrayConfigProperty createProperty(Configuration config, String category,
				String name, String comment, String[] defaultValue, String... validValues)
		{
			return new StringArrayConfigProperty(config, category, name, comment, defaultValue, validValues);
		}
	}
}
