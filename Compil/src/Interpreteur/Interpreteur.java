package Interpreteur;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpreteur {
	
	public static void interprete(String file)
	{
		int SP = -1;
		FileReader fr;
		String instruction = null;
		ArrayList<Integer> mem = new ArrayList<>(); 
		ArrayList<String> stack = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			Scanner sc = new Scanner(fr);
			Scanner sc2 = new Scanner(System.in);
			int operand = 0;
			int pos = 0;
			String ins;
			while(sc.hasNextLine())
			{
				stack.add(sc.nextLine());
			}
			for(String st: stack)
			{
				System.out.println(st);
			}
			
			for(int i =0; i < stack.size();i++)
			{
				instruction = stack.get(i);
				
				if((pos = instruction.indexOf(' ')) != -1)
				{
					operand = Integer.valueOf(instruction.substring(pos + 1));
					
				}
				ins = "" + instruction.charAt(0) + instruction.charAt(1) + instruction.charAt(2);
	
				switch (ins) {
				case "ADD":
					mem.set(SP-1, mem.get(SP) + mem.get(SP-1));
					mem.remove(SP);
					SP--;
					break;
				case "SUB":
					mem.set(SP-1, mem.get(SP) - mem.get(SP-1));
					mem.remove(SP);
					SP--;
					break;
				case "MUL":
					mem.set(SP-1, mem.get(SP) * mem.get(SP-1));
					mem.remove(SP);
					SP--;
					break;
				case "DIV":
					mem.set(SP-1, mem.get(SP) / mem.get(SP-1));
					mem.remove(SP);
					SP--;
					break;
				case "EQL":
					if(mem.get(SP-1) == mem.get(SP))
						mem.set(SP-1, 1);
					else
						mem.set(SP-1, 0);
					mem.remove(SP);
					SP--;
					break;
				case "NEQ":
					if(mem.get(SP-1) != mem.get(SP))
						mem.set(SP-1, 1);
					else
						mem.set(SP-1, 0);
					mem.remove(SP);
					SP--;
					break;
				case "GTR":
					if(mem.get(SP-1) > mem.get(SP))
						mem.set(SP-1, 1);
					else
						mem.set(SP-1, 0);
					mem.remove(SP);
					SP--;
					break;
				case "LSS":
					if(mem.get(SP-1) < mem.get(SP))
						mem.set(SP-1, 1);
					else
						mem.set(SP-1, 0);
					mem.remove(SP);
					SP--;
					break;
				case "GEQ":
					if(mem.get(SP-1) >= mem.get(SP))
						mem.set(SP-1, 1);
					else
						mem.set(SP-1, 0);
					mem.remove(SP);
					SP--;
					break;
				case "SEQ":
					if(mem.get(SP-1) <= mem.get(SP))
						mem.set(SP-1, 1);
					else
						mem.set(SP-1, 0);
					mem.remove(SP);
					SP--;
					break;
				case "PRN":
					System.out.println(mem.get(SP));
					break;
				case "INN":
					mem.set(mem.get(SP), sc2.nextInt());
					mem.remove(SP);
					SP--;
					break;
				case "INT":
					for(int j = 0; j < operand; j++)
					{
						mem.add(0);
					}
					SP+=operand;
					break;
				case "LDI":
					mem.add(operand);
					SP++;
					break;
				case "LDA":
					SP++;
					mem.add(operand);
					break;
				case "LDV":
					mem.set(SP, mem.get(mem.get(SP)));
					break;
				case "STO":
					mem.set(mem.get(SP-1), mem.get(SP));
					mem.remove(SP);
					SP--;
					mem.remove(SP);
					SP--;
					break;
				case "BRN":
					i = operand - 1;
					break;
				case "BZE":
					if(mem.get(SP) == 0)
					{
						i = operand - 1;
						mem.remove(SP);
						SP--;
					}
					break;
				case "HLT":
					System.exit(0);
					break;
				default:
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
