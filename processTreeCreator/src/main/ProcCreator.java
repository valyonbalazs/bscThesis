package main;

import java.io.IOException;

public class ProcCreator implements Runnable {

	int actualLevel;
	int maxLevel;
	
	public ProcCreator(int actualLvl, int maxLvl) {
		actualLevel = actualLvl;
		maxLevel = maxLvl;
	}
	
	@Override
	public void run() {
		CreateProc(actualLevel, maxLevel);		
	}
	
	public void CreateProc(int actualLevel, int maxLevel) {
		
		try {
			while(true){
				Process proc = Runtime.getRuntime().exec("java -jar processTreeCreator.jar " + actualLevel + " " + maxLevel);
				proc.waitFor();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}		
}
