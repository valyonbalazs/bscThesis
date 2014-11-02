package main;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.W32APIOptions;

public class Main {
	
	//PARENT
	public static void main(String[] args) {
		
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println(processName);
		int pid = Integer.parseInt(processName.split("@")[0]);
		System.out.println(pid);
		
		if(args.length == 0) {
			try {
				int i = 0;
				while(i < 5) {
					
			        Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
			        Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();    

			        WinNT.HANDLE snapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
			        
			    	while (kernel32.Process32Next(snapshot, processEntry)) {  			       
			        	System.out.println((processEntry.th32ProcessID).toString() + " " +  Native.toString(processEntry.szExeFile));
			        }
					
					Process process = Runtime.getRuntime().exec("java -jar childProcess.jar " + pid);
					process.waitFor();
					System.out.println("process was restarted!");
					i++;
				}			
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			} 
		}
		else {
			while(true) {
				Map<String, String> pidAndName = new HashMap<String, String>();
				
		        Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
		        Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();          

		        WinNT.HANDLE snapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
		        
		    	while (kernel32.Process32Next(snapshot, processEntry)) {  
		        	pidAndName.put((processEntry.th32ProcessID).toString(), Native.toString(processEntry.szExeFile));
		        }
		    	
		    	if(pidAndName.get(args[0]) == null) {
		    		int i = 0;
					while(i < 5) {
						Process process;
						try {
							process = Runtime.getRuntime().exec("java -jar childProcess.jar " + pid);
							process.waitFor();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
							
						}
						i++;

					}
		    	}
		    	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    	//ELSE DO NOTHING
			}

			
		}
	}

}
