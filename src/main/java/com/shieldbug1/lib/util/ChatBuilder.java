package com.shieldbug1.lib.util;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.*;

import org.apache.commons.lang3.builder.Builder;

/**
 * A Builder class to create instances of {@link ChatComponentText}, because I'm too lazy.
 * <br></br>
 * Builders are not meant to be reused, or kept.
 * <br>
 * Example usage: <br>
 * {@code ChatComponenetText text = new ChatBuilder().setText("stuff").setBold().build();}</br>
 * </br>
 */
public class ChatBuilder implements Builder<ChatComponentText> //XXX finish javadoc
{
	private ChatComponentText component;
	private ChatStyle style;
	
	/**
	 * Instantiates a new builder instance.
	 */
	public ChatBuilder()
	{
		this.component = new ChatComponentText("");
		this.style = new ChatStyle();
	}
	
	/**
	 * Sets the text of the chat component. Should only be called once, ideally.
	 * @param text - the text to set.
	 * @return {@code this}
	 */
	public ChatBuilder setText(String text)
	{
		this.component = new ChatComponentText(text);
		return this;
	}
	
	/**
	 * Appends the given text to the end of the ChatComponentText instance being built.
	 * @param text - the text to set.
	 * @return {@code this}
	 */
	public ChatBuilder appendText(String text)
	{
		this.component.appendText(text);
		return this;
	}
	
	/**
	 * 
	 * @param sibling
	 * @return {@code this}
	 */
	public ChatBuilder appendSibling(IChatComponent sibling)
	{
		this.component.appendSibling(sibling);
		return this;
	}
	
	/**
	 * 
	 * @return {@code this}
	 */
	public ChatBuilder setBold()
	{
		this.style.setBold(true);
		return this;
	}
	
	/**
	 * 
	 * @return {@code this}
	 */
	public ChatBuilder setItalic()
	{
		this.style.setItalic(true);
		return this;
	}
	
	/**
	 * 
	 * @return {@code this}
	 */
	public ChatBuilder setUnderlined()
	{
		this.style.setUnderlined(true);
		return this;
	}
	
	/**
	 * 
	 * @param colour
	 * @return {@code this}
	 */
	public ChatBuilder setColour(EnumChatFormatting colour)
	{
		this.style.setColor(colour);
		return this;
	}
	
	/**
	 * 
	 * @return {@code this}
	 */
	public ChatBuilder setStrikeThrough()
	{
		this.style.setStrikethrough(true);
		return this;
	}
	
	/**
	 * 
	 * @return {@code this}
	 */
	public ChatBuilder setObfuscated()
	{
		this.style.setObfuscated(true);
		return this;
	}
	
	/**
	 * 
	 * @param insertion
	 * @return {@code this}
	 */
	public ChatBuilder setInsertion(String insertion)
	{
		this.style.setInsertion(insertion);
		return this;
	}
	
	/**
	 * 
	 * @param event
	 * @return {@code this}
	 */
	public ChatBuilder setChatHoverEvent(HoverEvent event)
	{
		this.style.setChatHoverEvent(event);
		return this;
	}
	
	/**
	 * 
	 * @param event
	 * @return {@code this}
	 */
	public ChatBuilder setChatClickEvent(ClickEvent event)
	{
		this.style.setChatClickEvent(event);
		return this;
	}
	
	/**
	 * 
	 * @return {@code this}
	 */
	public ChatBuilder resetStyle()
	{
		this.style = new ChatStyle();
		return this;
	}
	
	/**
	 * 
	 * @param style
	 * @return {@code this}
	 */
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
