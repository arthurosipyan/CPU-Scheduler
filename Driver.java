package cpu_scheduler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] arg) throws IOException {
		Scanner kb = new Scanner(System.in);
		System.out.print("Please enter filename: ");
		String fileName = kb.nextLine();
		System.out.print("Please enter Time Quantum: ");
		int tq = kb.nextInt();
		Scanner inputStream = new Scanner(new File(fileName));
		inputStream.useDelimiter("(,|\\v+)");
		inputStream.nextLine();
		ArrayList<Process> csvFile = new ArrayList<>();
		while (inputStream.hasNext()) {
			Process process = new Process(inputStream.nextInt(), inputStream.nextInt(), inputStream.nextInt());
			csvFile.add(process);
		}

		Scheduler myScheduler = new Scheduler(csvFile, tq);
		myScheduler.rr();
		kb.close();
		inputStream.close();
	}
}
