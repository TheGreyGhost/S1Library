package com.shieldbug1.lib.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/**
 * A wrapper class around the Minecraft FontRenderer class made purely for convenience.
 */
public final class FontWrapper //TODO Font styles. Bold, Italic, Underlined, etc.
{
	private final FontRenderer fontRenderer;
	private int wordWrapValue;
	
	private FontWrapper(FontRenderer fontRenderer)
	{
		this.fontRenderer = fontRenderer;
	}
	
	/**
	 * Draws the string on-screen.
	 * @param string - the string to draw.
	 * @param x - x position
	 * @param y - y position
	 * @param colour - colour to draw string in (0xRRGGBB).
	 */
	public void drawString(String string, int x, int y, int colour)
	{
		this.drawString(string, x, y, colour, false);
	}
	
	/**
	 * Draws the string on-screen with a shadow.
	 * @param string - the string to draw.
	 * @param x - x position
	 * @param y - y position
	 * @param colour - colour to draw string in (0xRRGGBB).
	 */
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
	
	/**
	 * Draws a string on-screen with a specified font size.
	 * @param string - the string to draw
	 * @param x - x position
	 * @param y - y position
	 * @param colour - colour of string to draw (0xRRGGBB)
	 * @param shadow - whether or not to use shadow when drawing.
	 * @param fontSize - the size of the font to use for this string.
	 */
	public void drawString(String string, int x, int y, int colour, boolean shadow, int fontSize)
	{
		int tempSize = this.fontRenderer.FONT_HEIGHT;
		this.fontRenderer.FONT_HEIGHT = fontSize;
		this.drawString(string, x, y, colour, shadow);
		this.fontRenderer.FONT_HEIGHT = tempSize;
	}

	/**
	 * Sets the font renderer to wrap text. Note that this disables shadowing.
	 * @param wrap - the value to wrap. Value must not be equal to 0.
	 */
	public void setWordWrap(int wrap)
	{
		this.wordWrapValue = Math.max(-1, wrap);
	}
	
	/**
	 * @return a font wrapper on the default Minecraft FontRender object.
	 */
	public static FontWrapper getDefault()
	{
		return new FontWrapper(Minecraft.getMinecraft().fontRendererObj);
	}
	
	/**
	 * Wraps the FontRenderer object.
	 * @param fontRenderer - the FontRenderer to wrap.
	 * @return the wrapper.
	 */
	public static FontWrapper wrap(FontRenderer fontRenderer)
	{
		return new FontWrapper(fontRenderer);
	}
	
}
