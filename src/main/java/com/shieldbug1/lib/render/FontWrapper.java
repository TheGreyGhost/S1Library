package com.shieldbug1.lib.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public final class FontWrapper
{
	private final FontRenderer fontRenderer;
	private int wordWrapValue;
	
	private FontWrapper(FontRenderer fontRenderer)
	{
		this.fontRenderer = fontRenderer;
	}
	
	public void drawString(String string, int x, int y, int colour)
	{
		this.drawString(string, x, y, colour, false);
	}
	
	public void drawStringWithShadow(String string, int x, int y, int colour)
	{
		this.drawString(string, x, y, colour, true);
	}
	
	private void drawString(String string, int x, int y, int colour, boolean shadow)
	{
		if(this.wordWrapValue > 0)
		{
			this.fontRenderer.drawSplitString(string, x, y, colour, this.wordWrapValue);
		}
		else
		{
			this.fontRenderer.drawString(string, x, y, colour, shadow);
		}
	}
	
	public void drawString(String string, int x, int y, int colour, boolean shadow, int fontSize)
	{
		int tempSize = this.fontRenderer.FONT_HEIGHT;
		this.fontRenderer.FONT_HEIGHT = fontSize;
		this.drawString(string, x, y, colour, shadow);
		this.fontRenderer.FONT_HEIGHT = tempSize;
	}

	public void setWordWrap(int wrap)
	{
		this.wordWrapValue = Math.max(-1, wrap);
	}
	
	public static FontWrapper getDefault()
	{
		return new FontWrapper(Minecraft.getMinecraft().fontRenderer);
	}
	
	public static FontWrapper wrap(FontRenderer fontRenderer)
	{
		return new FontWrapper(fontRenderer);
	}
	
}
