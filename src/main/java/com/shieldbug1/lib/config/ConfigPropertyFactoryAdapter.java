package com.shieldbug1.lib.config;

import net.minecraftforge.common.config.Configuration;

import com.shieldbug1.lib.config.ConfigProperty.*;

public interface ConfigPropertyFactoryAdapter //TODO JavaDoc
{
	BooleanConfigProperty createProperty(String name, boolean defaultValue);
	BooleanConfigProperty createProperty(String name, String comment, boolean defaultValue);
	
	BooleanArrayConfigProperty createProperty(String name, boolean[] defaultValue);
	BooleanArrayConfigProperty createProperty(String name, String comment, boolean[] defaultValue);
	
	IntConfigProperty createProperty(String name, int defaultValue, int min, int max);
	IntConfigProperty createProperty(String name, String comment, int defaultValue, int min, int max);
	
	IntArrayConfigProperty createProperty(String name, int[] defaultValue, int min, int max);
	IntArrayConfigProperty createProperty(String name, String comment, int[] defaultValue, int min, int max);
	
	DoubleConfigProperty createProperty(String name, double defaultValue, double min, double max);
	DoubleConfigProperty createProperty(String name, String comment, double defaultValue, double min, double max);
	
	DoubleArrayConfigProperty createProperty(String name, double[] defaultValue, double min, double max);
	DoubleArrayConfigProperty createProperty(String name, String comment, double[] defaultValue, double min, double max);

	StringConfigProperty createProperty(String name, String defaultValue, String... validValues);
	StringConfigProperty createProperty(String name, String comment, String defaultValue, String... validValues);
	
	StringArrayConfigProperty createProperty(String name, String[] defaultValue, String... validValues);
	StringArrayConfigProperty createProperty(String name, String comment, String[] defaultValue, String... validValues);

	public static class Implementation implements ConfigPropertyFactoryAdapter
	{
		private final Configuration config;
		private final String category;
		private final ConfigPropertyFactory factory;
		
		public Implementation(ConfigPropertyFactory factory, Configuration config, String category)
		{
			this.config = config;
			this.category = category;
			this.factory = factory;
		}
		
		@Override
		public BooleanConfigProperty createProperty(String name, boolean defaultValue)
		{
			return this.factory.createProperty(this.config, this.category, name, defaultValue);
		}

		@Override
		public BooleanConfigProperty createProperty(String name, String comment, boolean defaultValue)
		{
			return this.factory.createProperty(this.config, this.category, name, comment, defaultValue);
		}

		@Override
		public BooleanArrayConfigProperty createProperty(String name, boolean[] defaultValue)
		{
			return this.factory.createProperty(this.config, this.category, name, defaultValue);
		}

		@Override
		public BooleanArrayConfigProperty createProperty(String name, String comment, boolean[] defaultValue)
		{
			return this.factory.createProperty(this.config, this.category, name, comment, defaultValue);
		}

		@Override
		public IntConfigProperty createProperty(String name, int defaultValue, int max, int min)
		{
			return this.factory.createProperty(this.config, this.category, name, defaultValue, max, min);
		}

		@Override
		public IntConfigProperty createProperty(String name, String comment, int defaultValue, int min, int max)
		{
			return this.factory.createProperty(this.config, this.category, name, comment, defaultValue, min, max);
		}

		@Override
		public IntArrayConfigProperty createProperty(String name, int[] defaultValue, int min, int max)
		{
			return this.factory.createProperty(this.config, this.category, name, defaultValue, max, min);
		}

		@Override
		public IntArrayConfigProperty createProperty(String name, String comment, int[] defaultValue, int min, int max)
		{
			return this.factory.createProperty(this.config, this.category, name, comment, defaultValue, min, max);
		}

		@Override
		public DoubleConfigProperty createProperty(String name, double defaultValue, double min, double max)
		{
			return this.factory.createProperty(this.config, this.category, name, defaultValue, max, min);
		}

		@Override
		public DoubleConfigProperty createProperty(String name, String comment, double defaultValue, double min, double max)
		{
			return this.factory.createProperty(this.config, this.category, name, comment, defaultValue, min, max);
		}

		@Override
		public DoubleArrayConfigProperty createProperty(String name, double[] defaultValue, double min, double max)
		{
			return this.factory.createProperty(this.config, this.category, name, defaultValue, max, min);
		}

		@Override
		public DoubleArrayConfigProperty createProperty(String name, String comment, double[] defaultValue, double min, double max)
		{
			return this.factory.createProperty(this.config, this.category, name, comment, defaultValue, min, max);
		}

		@Override
		public StringConfigProperty createProperty(String name, String defaultValue, String... validValues)
		{
			return this.factory.createProperty(this.config, this.category, name, defaultValue, validValues);
		}

		@Override
		public StringConfigProperty createProperty(String name, String comment, String defaultValue, String... validValues)
		{
			return this.factory.createProperty(this.config, this.category, name, comment, defaultValue, validValues);
		}

		@Override
		public StringArrayConfigProperty createProperty(String name, String[] defaultValue, String... validValues)
		{
			return this.factory.createProperty(this.config, this.category, name, defaultValue, validValues);
		}

		@Override
		public StringArrayConfigProperty createProperty(String name, String comment, String[] defaultValue, String... validValues)
		{
			return this.factory.createProperty(this.config, this.category, name, comment, defaultValue, validValues);
		}
		
	}
	
}
