package com.shieldbug1.lib.asm;

import static org.apache.logging.log4j.Level.TRACE;
import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.ASM5;
import static org.objectweb.asm.Type.VOID_TYPE;

import java.util.Iterator;

import net.minecraftforge.fml.common.FMLLog;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/**
 * A class to help with the use of ASM, and particularly the ASM Tree API.
 */
public class ASMHelper
{
	private ASMHelper(){} // No constructor for you!
	
	/**
	 * Creates a ClassNode out of the basic bytes of the Class.
	 * @param basicClass - the byte array to construct the ClassNode from.
	 * @return the ClassNode.
	 */
	public static ClassNode createClassNode(byte[] basicClass)
	{
		ClassReader reader = new ClassReader(basicClass);
		ClassNode classNode = new ClassNode(ASM5);
		reader.accept(classNode, EXPAND_FRAMES);
		FMLLog.log("S1LIB", TRACE, "Beginning to patch %s", classNode.name);
		return classNode;
	}
	
	/**
	 * Turns a ClassNode back to a byte array.
	 * @return the new byte array.
	 */
	public static byte[] makeBytes(ClassNode classNode)
	{
		return makeBytes(classNode, COMPUTE_FRAMES);
	}
	
	/**
	 * Flag sensitive version of {@link #makeBytes(ClassNode)}.
	 */
	public static byte[] makeBytes(ClassNode classNode, int flags)
	{
		ClassWriter writer = new ClassWriter(flags);
		classNode.accept(writer);
		FMLLog.log("S1LIB", TRACE, "Completed patching %s", classNode.name);
		return writer.toByteArray();
	}
	
	/**
	 * Finds a MethodNode in a ClassNode.
	 * @param clazz - the ClassNode to find the method in.
	 * @param obf - the obfuscated name of the method
	 * @param deobf - the deobfuscated name of the method
	 * @param returnType - the return type of the method.
	 * @param argumentTypes - the argument types. If none, use an empty Type array.
	 * @return the MethodNode that matches the input, or null if none was found.
	 */
	public static MethodNode findMethod(ClassNode clazz, String obf, String deobf, Type returnType, Type... argumentTypes)
	{
		final String desc = Type.getMethodDescriptor(returnType, argumentTypes);
		for(MethodNode node : clazz.methods)
		{
			if((node.name.equals(obf) || node.name.equals(deobf)) && node.desc.equals(desc))
			{
				return node;
			}
		}
		return null;
	}
	
	/**
	 * Finds a MethodNode in a ClassNode. Use this version if the method doesn't have two names (obf and deobf). 
	 * @param clazz - the ClassNode to find the method in.
	 * @param name - the name of the method.
	 * @param returnType - the return type of the method.
	 * @param argumentTypes - the argument types. If none, use an empty Type array.
	 * @return the MethodNode that matches the input, or null if none was found.
	 */
	public static MethodNode findMethod(ClassNode clazz, String name, Type returnType, Type... argumentTypes)
	{
		final String desc = Type.getMethodDescriptor(returnType, argumentTypes);
		for(MethodNode node : clazz.methods)
		{
			if(node.name.equals(name) && node.desc.equals(desc))
			{
				return node;
			}
		}
		return null;
	}
	
	/**
	 * Finds a constructor MethodNode in a ClassNode.
	 * @param clazz - the ClassNode to find the constructor in.
	 * @param argumentTypes - the argument types. If none, use an empty Type array.
	 * @return the constructor MethodNode that matches the input, or null if none as found.
	 */
	public static MethodNode findConstructor(ClassNode clazz, Type... argumentTypes)
	{
		return findMethod(clazz, "<init>", VOID_TYPE, argumentTypes);
	}
	
	/**
	 * Finds the first occurrence of an AbstractInsnNode in an InsnList.
	 * @param opcode - the Opcode of the instruction. {@link Opcodes}
	 * @param instructions - this list of instructions to find the instruction in.
	 * @return the first AbstractInsnNode in the list of instructions, or null if none was found.
	 */
	public static AbstractInsnNode findFirst(int opcode, InsnList instructions)
	{
		Iterator<AbstractInsnNode> it = instructions.iterator();
		while(it.hasNext())
		{
			AbstractInsnNode node = it.next();
			if(node.getOpcode() == opcode)
			{
				return node;
			}
		}
		return null;
	}
	
	/**
	 * Finds the last occurrence of an AbstractInsnNode in an InsnList.
	 * @param opcode - the Opcode of the instruction. {@link Opcodes}
	 * @param instructions - this list of instructions to find the instruction in.
	 * @return the last AbstractInsnNode in the list of instructions, or null if none was found.
	 */
	public static AbstractInsnNode findLast(int opcode, InsnList instructions)
	{
		Iterator<AbstractInsnNode> it = instructions.iterator();
		AbstractInsnNode last = null;
		while(it.hasNext())
		{
			AbstractInsnNode node = it.next();
			if(node.getOpcode() == opcode)
			{
				last = node;
			}
		}
		return last;
	}
	
	/**
	 * Prints out all instructions of a method in the form of Node, [Opcode, OpcodeInHex] to {@code System.out}.
	 * @param method - the method who's instructions should be printed out.
	 */
	public static void printOpcodes(MethodNode method)
	{
		Iterator<AbstractInsnNode> it = method.instructions.iterator();
		while(it.hasNext())
		{
			AbstractInsnNode node = it.next();
			System.out.println(String.format("%s, [%s - %s]", node, node.getOpcode(), Integer.toHexString(node.getOpcode())));
		}
	}
}
