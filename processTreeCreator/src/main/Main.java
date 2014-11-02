package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		
		int actualLevel = 0;
		int maxLevel = 0;
		
		//Example to create a 3 level process tree
		//java -jar processExplorer 0 3
		if(args[0] != null && args[1] != null) {
			if(Integer.parseInt(args[0]) < Integer.parseInt(args[1])){
				actualLevel = Integer.parseInt(args[0]) + 1;
				maxLevel = Integer.parseInt(args[1]);
				try {
					CreateProcesses(actualLevel, maxLevel);
				} catch (Exception e) {
	
				}			
			}
			else {
				//DO NOTHING
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void CreateProcesses(int actualLvl, int maxLvl) {
		
		ProcCreator proc1 = new ProcCreator(actualLvl, maxLvl);
		ProcCreator proc2 = new ProcCreator(actualLvl, maxLvl);
		Thread t1 = new Thread(proc1);
		Thread t2 = new Thread(proc2);
		t1.start();
		t2.start();
	}

}
