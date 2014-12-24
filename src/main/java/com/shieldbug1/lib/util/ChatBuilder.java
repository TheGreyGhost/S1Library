package com.shieldbug1.lib.util;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.*;

import org.apache.commons.lang3.builder.Builder;

public class ChatBuilder implements Builder<ChatComponentText> //TODO javadoc
{
	private ChatComponentText component;
	private ChatStyle style;
	
	public ChatBuilder()
	{
		this.component = new ChatComponentText("");
		this.style = new ChatStyle();
	}
	
	public ChatBuilder setText(String text)
	{
		this.component = new ChatComponentText(text);
		return this;
	}
	
	public ChatBuilder appendText(String text)
	{
		this.component.appendText(text);
		return this;
	}
	
	public ChatBuilder appendSibling(IChatComponent sibling)
	{
		this.component.appendSibling(sibling);
		return this;
	}
	
	public ChatBuilder setBold()
	{
		this.style.setBold(true);
		return this;
	}
	
	public ChatBuilder setItalic()
	{
		this.style.setItalic(true);
		return this;
	}
	
	public ChatBuilder setUnderlined()
	{
		this.style.setUnderlined(true);
		return this;
	}
	
	public ChatBuilder setColour(EnumChatFormatting colour)
	{
		this.style.setColor(colour);
		return this;
	}
	
	public ChatBuilder setStrikeThrough()
	{
		this.style.setStrikethrough(true);
		return this;
	}
	
	public ChatBuilder setObfuscated()
	{
		this.style.setObfuscated(true);
		return this;
	}
	
	public ChatBuilder setInsertion(String insertion)
	{
		this.style.setInsertion(insertion);
		return this;
	}
	
	public ChatBuilder setChatHoverEvent(HoverEvent event)
	{
		this.style.setChatHoverEvent(event);
		return this;
	}
	
	public ChatBuilder setChatClickEvent(ClickEvent event)
	{
		this.style.setChatClickEvent(event);
		return this;
	}
	
	public ChatBuilder resetStyle()
	{
		this.style = new ChatStyle();
		return this;
	}
	
	public ChatBuilder setStyle(ChatStyle style)
	{
		this.style = style;
		return this;
	}
	
	public ChatComponentText build()
	{
		this.component.setChatStyle(this.style);
		return this.component;
	}

}
