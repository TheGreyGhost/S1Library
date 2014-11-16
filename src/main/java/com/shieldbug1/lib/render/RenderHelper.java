package com.shieldbug1.lib.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;

import com.shieldbug1.lib.java.ReflectionCache;
import com.shieldbug1.lib.repackage.speedytools.common.utilities.OpenGLdebugging;

public final class RenderHelper
{
	private RenderHelper(){}
	
	public static void dumpGLInformation()
	{
		OpenGLdebugging.dumpAllIsEnabled();
	}
	
	public static void drawQuad(int x, int y, int width, int height, int colour)
	{
		drawQuad(x, y, width, height, colour, 0xFF, getGuiZLevel());
	}

	public static void drawQuad(int x, int y, int width, int height, int colour, int alpha)
	{
		drawQuad(x, y, width, height, colour, alpha, getGuiZLevel());
	}

	public static void drawQuad(int x, int y, int width, int height, int colour, int alpha, float zLevel)
	{
		if(alpha == 0) //Alpha is 0, we don't need to draw anything.
		{
			return;
		}

		float r = (colour >> 16 & 0xFF) / 255F;
		float g = (colour >> 8 & 0xFF) / 255F;
		float b = (colour & 0xFF) / 255F;

		glDisable(GL_TEXTURE_2D); //We're not drawing an image, we're drawing a colour.

		if (alpha != 255)
		{
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		}

		glColor4f(r, g, b, alpha / 255F);

		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		{
			t.addVertex(x, y + height, zLevel);
			t.addVertex(x + width, y + height, zLevel);
			t.addVertex(x + width, y, zLevel);
			t.addVertex(x, y, zLevel);
		}
		t.draw();

		glEnable(GL_TEXTURE_2D); //Re-enable everything.
		if (alpha != 255)
		{
			glDisable(GL_BLEND);
		}
	}

	public static void drawTexturedSquare(int x, int y, int width, int height, int u, int v, int uSize, int vSize, int texSize)
	{
		drawTexturedQuad(x, y, width, height, u, v, uSize, vSize, texSize, texSize, getGuiZLevel());
	}
	
	public static void drawTexturedSquare(int x, int y, int width, int height, int u, int v, int uSize, int vSize, int texSize, float zLevel)
	{
		drawTexturedQuad(x, y, width, height, u, v, uSize, vSize, texSize, texSize, zLevel);
	}


	public static void drawTexturedQuad(int x, int y, int width, int height, int u, int v, int uSize, int vSize, int texWidth, int texHeight)
	{
		drawTexturedQuad(x, y, width, height, u, v, uSize, vSize, texWidth, texHeight, getGuiZLevel());
	}

	public static void drawTexturedQuad(int x, int y, int width, int height, int u, int v, int uSize, int vSize, int texWidth, int texHeight, float zLevel)
	{
		float uFact = 1f / texWidth;
		float vFact = 1f / texHeight;

		int uEnd = u + uSize;
		int vEnd = v + vSize;

		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		{
			t.addVertexWithUV(x, y + height, zLevel, u * uFact, vEnd * vFact);
			t.addVertexWithUV(x + width, y + height, zLevel, uEnd * uFact, vEnd * vFact);
			t.addVertexWithUV(x + width, y, zLevel, uEnd * uFact, v * vFact);
			t.addVertexWithUV(x, y, zLevel, u * uFact, v * vFact);
		}
		t.draw();
	}

	public static void drawTexturedQuadFit(int x, int y, int width, int height)
	{
		drawTexturedQuadFit(x, y, width, height, getGuiZLevel());
	}

	public static void drawTexturedQuadFit(int x, int y, int width, int height, float zLevel)
	{
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		t.addVertexWithUV(x, y + height, zLevel, 0, 1);
		t.addVertexWithUV(x + width, y + height, zLevel, 1, 1);
		t.addVertexWithUV(x + width, y, zLevel, 1, 0);
		t.addVertexWithUV(x, y, zLevel, 0, 0);
		t.draw();
	}

	private static float getGuiZLevel()
	{
		return ReflectionCache.getFieldValue(Gui.class, Minecraft.getMinecraft().currentScreen, "zLevel", "field_73735_i");
	}
}