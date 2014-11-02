import psutil
import os
import sys
import subprocess
import time

currentProcessId = os.getpid()
arguments = 0
argCount = 0
try:
    arguments = sys.argv[1]
    argCount = len(arguments)
except:
    print('arg exception')
print(argCount)
print(currentProcessId)
print("Parent started")
if(argCount == 0):
    while True:
        print("parent first branch")
        #First version works only on Windows
        #cmd = "python childProcessCreator.py %i" % (currentProcessId)

        #Second version works perfectly both on Windows and UNIX
        cmd = ["python", "childProcessCreator.py", str(currentProcessId)]
        proc = subprocess.Popen(cmd)
        proc.wait()
else:
    while True:
        print("parent else branch")
        processList = psutil.get_pid_list()
        argus = int(arguments)
        contains = argus in processList
        if(contains == False):
            try:
                cmd = "python childProcessCreator.py %i" % (currentProcessId)
                proc = subprocess.Popen(cmd)
                proc.wait()
            except:
                print('process creation error2')
        time.sleep(1)

