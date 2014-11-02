import psutil
import os
import subprocess
import sys
import time

currentProcessId = os.getpid()
argument = int(sys.argv[1])
print("child started")
while True:
    processList = psutil.get_pid_list()
    contains = argument in processList
    if(contains == False):
        try:
            #This version is needed because of the UNIX terminal
            cmd = ["python", "parentProcessCreator.py", str(currentProcessId)]
            proc = subprocess.Popen(cmd)
            proc.wait()
        except:
            print('process creation error')
    time.sleep(1)