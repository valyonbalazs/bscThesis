using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace JobObjectsApp
{
    /// <summary>
    /// To use this application, it has to be built first, then run by the .exe file,
    /// it will not run from Visual Studio, only by from the generated exe file. 
    /// </summary>
    class Program
    {
        static void Main(string[] args)
        {
            Job job = new Job();
            Process proc1 = new Process();

            //TESTING THE PROCESS TREE CREATOR APPLICATION
            //The path to the parentProcessCreator.py script file, such as
            //proc1.StartInfo.FileName = @"C:\Users\valyonbalazs\Documents\Thesis\ChildParentProcess_Python\parentProcessCreator.py"
            //proc1.StartInfo.FileName = @" ";
            //proc1.Start();
            //int procid1 = proc1.Id;
           // job.AddProcess(Process.GetProcessById(procid1).Handle);


            //TESTING THE JOB OBJECTS WITH 6 NOTEPADS
            //3 NOTEPADS WILL BE CLOSED
            Process proc2 = new Process();
            Process proc3 = new Process();
            Process proc4 = new Process();
            Process proc5 = new Process();
            Process proc6 = new Process();
            proc1.StartInfo.FileName = @"C:\Windows\System32\notepad.exe";
            proc2.StartInfo.FileName = @"C:\Windows\System32\notepad.exe";
            proc3.StartInfo.FileName = @"C:\Windows\System32\notepad.exe";
            proc4.StartInfo.FileName = @"C:\Windows\System32\notepad.exe";
            proc5.StartInfo.FileName = @"C:\Windows\System32\notepad.exe";
            proc6.StartInfo.FileName = @"C:\Windows\System32\notepad.exe";

            //Six notepad will be opened but only 3 will be added to the Job object
            proc1.Start();
            proc2.Start();
            proc3.Start();
            proc4.Start();
            proc5.Start();
            proc6.Start();

            int procid1 = proc1.Id;
            int procid2 = proc2.Id;
            int procid3 = proc3.Id;
            job.AddProcess(Process.GetProcessById(procid1).Handle);
            job.AddProcess(Process.GetProcessById(procid2).Handle);
            job.AddProcess(Process.GetProcessById(procid3).Handle);

            //JUST TEST WAITING TO SEE THE ACTION in a Process Explorer
            Thread.Sleep(15000);

            //Only these 3 will be closed, the others will keep running
            job.Close();

            Console.ReadLine();
        }
    }
}
