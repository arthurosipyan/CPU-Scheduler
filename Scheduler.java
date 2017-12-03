package cpu_scheduler;

import java.util.*;

// Source code
public class Scheduler {
	int timer;
	ArrayList<Process> listOfProcesses;
	ArrayList<Process> readyQueue;
	ArrayList<Process> endProcesses;
	int timeQuantum;
	int contextSwitch; // small number (less than half the timeQuantum).. ex:
						// 0.1 * timeQuantum
	Process cpu;
	int counter;

	public Scheduler(ArrayList<Process> listOfProcesses, int timeQuantum) {
		this.timeQuantum = timeQuantum;
		this.listOfProcesses = listOfProcesses;
	}

	public void rr() {
		timer = 0;
		contextSwitch = 0;
		cpu = null;
		readyQueue = new ArrayList<>();
		endProcesses = new ArrayList<>();

		while (!readyQueue.isEmpty() || !listOfProcesses.isEmpty() || cpu != null) {
			// add to readyQueue
			for (int i = 0; i < listOfProcesses.size(); i++) {
				if (listOfProcesses.get(i).arrivalTime == timer) {
					readyQueue.add(listOfProcesses.remove(i));
				}
			}

			// add to cpu
			if (cpu == null) {
				cpu = readyQueue.remove(0);
			}

			counter++;
			cpu.serviceTime++;

			if (cpu.burstTime == cpu.serviceTime) { // done
				cpu.completionTime = timer; // completion time is set
				endProcesses.add(cpu);
				cpu = null;
				contextSwitch++;
				counter = 0;
			} else if (counter == timeQuantum) { // exceeds timeQuantum
				readyQueue.add(cpu);
				cpu = null;
				contextSwitch++;
				counter = 0;
			}
			timer++; // real time

		}

		double sumTurnAroundTime = 0.0;
		double sumWaitingTime = 0.0;
		double sumUtil = 0.0;
		for (int i = 0; i < endProcesses.size(); i++) {
			sumTurnAroundTime += endProcesses.get(i).completionTime - endProcesses.get(i).arrivalTime;
			sumWaitingTime += (endProcesses.get(i).completionTime - endProcesses.get(i).arrivalTime)
					- endProcesses.get(i).burstTime;
			sumUtil += endProcesses.get(i).burstTime;
		}
		double avgTurnAroundTime = sumTurnAroundTime / endProcesses.size();
		double avgWaitingTime = sumWaitingTime / endProcesses.size();
		double cpuUtilization = (sumUtil - (contextSwitch * .01)) / timer;
		double throughput = (double) endProcesses.size() / timer;

		System.out.println("");
		System.out.println("CPU Utilization: " + cpuUtilization);
		System.out.println("Throughput: " + throughput);
		System.out.println("Average Waiting Time: " + avgWaitingTime);
		System.out.println("Average Turnaround Time: " + avgTurnAroundTime);

	}

}
