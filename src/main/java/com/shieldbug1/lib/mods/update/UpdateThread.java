package com.shieldbug1.lib.mods.update;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.shieldbug1.lib.java.Java;
import com.shieldbug1.lib.mods.api.ModBase;
import com.shieldbug1.lib.util.Sides;

/**
 * This class is instantiated and created automatically for all of my mods.
 * If you wish your mod to use this class, create an implementation of {@link ModBase},
 * instantiate this class with an instance of your ModBase and simply start the thread.
 */
public final class UpdateThread extends Thread
{
	private final URL url;
	private final ModBase mod;
	
	public UpdateThread(ModBase mod)
	{
		super(mod.getClass().getSimpleName() + " Update Thread");
		try
		{
			this.url = new URL(mod.getUpdater().getUpdateURL());
		}
		catch(MalformedURLException exception)
		{
			throw Java.<RuntimeException>throwUnchecked(exception);
		}
		this.mod = mod;
	}
	
	@Override
	public void run()
	{
		try(Scanner scanner = new Scanner(this.url.openStream()))
		{
			if(this.mod.getSerialID() < scanner.nextInt())
			{
				this.mod.getUpdater().handleUpdateFound(Sides.environment());
			}
		}
		catch (IOException e)
		{
			Java.<RuntimeException>throwUnchecked(e);
		}
	}
	
}
