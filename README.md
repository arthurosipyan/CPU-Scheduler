# CPU-Scheduler and Round Robin:

Instructions:
1.	Open “src”.
2.	Open “cpu_scheduler” (this is the resource package).
3.	You should see 3 java classes
4.	Open the 3 classes (and include the csv file inside the zip file) on your preferred java IDE.
5.	Run “Driver.java”
6.	The program will ask the user to enter the filename and then the desired Time Quantum.
7.	After entering the information correctly, the program should run and return the CPU Utilization, Throughput, Average Waiting Time, and Average Turnaround Time.

# Project and Program Explained:

The purpose of the CPU Scheduler is to have a list of processes that are ready, of which are to be chosen to run on the CPU. This and the Round Robin algorithm can be best explained through the analysis of the Java code itself.

In the project folder, you will see that there is a Driver, Process, and Scheduler class. The Process class is dedicated to each individual process that is originally read from the csv file. In this class, you will notice that there are variables related from the csv table (pid, arrivalTime, and burstTime). Moving on to the Scheduler class, this is where the Round Robin algorithm is performed, as it is a crucial piece to the CPU Scheduler. Here you will notice that there are some key variables. Basically, we have a listOfProcesses, readyQueue, timer (the real time of the running program, can be seen as a clock), timeQuantum (identified by the user), cpu (which can only hold one process at a time), contextSwitch (letting us track the switches of each process), and a counter.

Let’s take a look at the Round Robin algorithm now that we understand what variables we have. Inside the rr() method, there is a while loop that checks to see if our readQueue, listOfProcesses, or the cpu is not empty. This loop will keep running until we are completely done with the list of processes. Bellow the conditionals of the while loop, there is a for loop that goes through the listOfProcesses and checks to see which processes’ arrivalTime matches the timer (clock). Once a process meets the check in the if statement, it will be loaded onto the readQueue. The cpu is then checked to see if it is empty, and if so, the readyQueue will load the process onto the cpu. Moving down from there, we have the counter starting to keep track of the cpu’s current process and that process’ serviceTime. Next, we have an if statement that checks if the current process’ burstTime and serviceTime match. If this condition is true, then the process is “done” and can be removed from the cpu. But before removing this process from the cpu, the program will take note of its completion time. It will also add that process to our endProcesses list, for which we can have a way of tracking the processes that have been completed. Then the cpu is set to null (so it can accept the next waiting process), contextSwitch is updated, and the counter is reset by setting it equal to 0. There is another part to the if statement… the else if. This else if statement checks if the process exceeds the timeQuantum. If that’s the case, it will load the process back to the readyQueue, update the cpu to null again, update the contextSwitch, and finally reset the counter back to 0.

This process is how the cpu is actively busy and the Round Robin makes sure of that. Further down, the program is able to calculate the statists of the CPU Utilization, Throughput, Average Waiting Time, and Average Turnaround Time. The calculations can be seen as simple equations from the variables that the program has been keeping track of:


CPU Utilization = (burst time - (contextTime * 0.1)) / processes

Throughput = processes / timer

Average Waiting Time = sum of all wait / processes

Average Turnaround Time = sun of all turn / processes

These statistics are then printed to the console, where the program is officially done with its CPU Scheduling.

Just to reiterate, the Driver class is started to prompt the user for the filename and desired Time Quantum. From there, the csv table transfers the list of processes to the ArrayList. Next, the Scheduler class is used for its Round Robin algorithm. Finally, once all the processes have been completed, the calculations are done and printed to the console for the user to read.
