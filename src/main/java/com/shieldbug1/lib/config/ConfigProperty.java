package com.shieldbug1.lib.config;

import static com.shieldbug1.lib.util.CoreFunctions.checkNotNull;
import net.minecraftforge.common.config.Configuration;

public abstract class ConfigProperty<T> //TODO javadoc, maybe move to lib?
{
	protected final Configuration config;
	public final String category, key, comment;
	protected final T defaultValue;
	
	protected ConfigProperty(Configuration config, String category, String key, String comment, T defaultValue)
	{
		this.config = checkNotNull(config, "Configuration file cannot be null!");
		this.category = category;
		this.key = key;
		this.comment = comment;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Retrieves the value of this configuration property, and if the value has changed, saves the config.
	 * Should be used when accessing properties individually.
	 * @return the value of this property.
	 */
	public final T get()
	{
		T t = this.get0();
		if(this.config.hasChanged())
		{
			this.config.save();
		}
		return t;
	}
	
	/**
	 * Retrieves the value of this configuration property without saving. Should be used only by
	 * 'ConfigurationHandler' classes.
	 * @return the value of this property.
	 */
	public abstract T get0();
	
	public static class BooleanConfigProperty extends ConfigProperty<Boolean>
	{
		public BooleanConfigProperty(Configuration config, String category, String key, String comment, boolean defaultValue)
		{
			super(config, category, key, comment, defaultValue);
		}
		@Override
		public Boolean get0()
		{
			return this.config.getBoolean(this.key, this.category, this.defaultValue, this.comment);
		}
	}
	
	public static class BooleanArrayConfigProperty extends ConfigProperty<boolean[]>
	{
		public BooleanArrayConfigProperty(Configuration config, String category, String key, String comment, boolean[] defaultValue)
		{
			super(config, category, key, comment, defaultValue);
		}
		@Override
		public boolean[] get0()
		{
			return this.config.get(this.category, this.key, this.defaultValue, this.comment).getBooleanList();
		}
	}
	
	public static class IntConfigProperty extends ConfigProperty<Integer>
	{
		private final int min, max;
		
		public IntConfigProperty(Configuration config, String category, String key, String comment, int defaultValue, int min, int max)
		{
			super(config, category, key, comment, defaultValue);
			this.min = min;
			this.max = max;
		}
		@Override
		public Integer get0()
		{
			return this.config.getInt(this.key, this.category, this.defaultValue, this.min, this.max, this.comment);
		}
	}
	
	public static class IntArrayConfigProperty extends ConfigProperty<int[]>
	{
		private final int min, max;
		
		public IntArrayConfigProperty(Configuration config, String category, String key, String comment, int[] defaultValue, int min, int max)
		{
			super(config, category, key, comment, defaultValue);
			this.min = min;
			this.max = max;
		}
		@Override
		public int[] get0()
		{
			return this.config.get(this.key, this.category, this.defaultValue, this.comment, this.min, this.max).getIntList();
		}
	}
	
	public static class StringConfigProperty extends ConfigProperty<String>
	{
		private final String[] validValues;
		
		public StringConfigProperty(Configuration config, String category, String key, String comment, String defaultValue, String... validValues)
		{
			super(config, category, key, comment, defaultValue);
			this.validValues = validValues;
		}
		@Override
		public String get0()
		{
			return this.config.getString(this.key, this.category, this.defaultValue, this.comment, this.validValues);
		}
	}
	
	public static class StringArrayConfigProperty extends ConfigProperty<String[]>
	{
		private final String[] validValues;
		
		public StringArrayConfigProperty(Configuration config, String category, String key, String comment, String[] defaultValue, String... validValues)
		{
			super(config, category, key, comment, defaultValue);
			this.validValues = validValues;
		}
		@Override
		public String[] get0()
		{
			return this.config.getStringList(this.key, this.category, this.defaultValue, this.comment, this.validValues);
		}
	}
	
	public static class DoubleConfigProperty extends ConfigProperty<Double>
	{
		private final double min, max;
		
		public DoubleConfigProperty(Configuration config, String category, String key, String comment, double defaultValue, double min, double max)
		{
			super(config, category, key, comment, defaultValue);
			this.min = min;
			this.max = max;
		}
		@Override
		public Double get0()
		{
			return this.config.get(this.category, this.key, this.defaultValue, this.comment, this.min, this.max).getDouble();
		}
	}
	
	public static class DoubleArrayConfigProperty extends ConfigProperty<double[]>
	{
		private final double min, max;
		public DoubleArrayConfigProperty(Configuration config, String category, String key, String comment, double[] defaultValue, double min, double max)
		{
			super(config, category, key, comment, defaultValue);
			this.min = min;
			this.max = max;
		}
		@Override
		public double[] get0()
		{
			return this.config.get(this.category, this.key, this.defaultValue, this.comment, this.min, this.max).getDoubleList();
		}
	}
}
