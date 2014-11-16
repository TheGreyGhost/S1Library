package com.shieldbug1.lib.asm;

import static org.apache.logging.log4j.Level.TRACE;
import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.ASM5;
import static org.objectweb.asm.Type.VOID_TYPE;

import java.util.Iterator;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import cpw.mods.fml.common.FMLLog;

public class ASMHelper
{
	private ASMHelper(){}
	
	public static ClassNode createClassNode(byte[] basicClass)
	{
		ClassReader reader = new ClassReader(basicClass);
		ClassNode classNode = new ClassNode(ASM5);
		reader.accept(classNode, EXPAND_FRAMES);
		FMLLog.log("S1LIB", TRACE, "Beginning to patch %s", classNode.name);
		return classNode;
	}
	
	public static byte[] makeBytes(ClassNode classNode)
	{
		return makeBytes(classNode, COMPUTE_FRAMES);
	}
	
	public static byte[] makeBytes(ClassNode classNode, int flags)
	{
		ClassWriter writer = new ClassWriter(flags);
		classNode.accept(writer);
		FMLLog.log("S1LIB", TRACE, "Completed patching %s", classNode.name);
		return writer.toByteArray();
	}
	
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
	
	public static MethodNode findConstructor(ClassNode clazz, Type... argumentTypes)
	{
		return findMethod(clazz, "<init>", VOID_TYPE, argumentTypes);
	}
	
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
