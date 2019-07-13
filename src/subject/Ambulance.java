package subject;

import java.util.concurrent.Semaphore;

import helpers.Clock;
import helpers.Matrix;
import helpers.WriterMatrix;

public class Ambulance extends Thread {

	private int tid;
	private int time;
	private int x, y;
	private Matrix map;
	private Semaphore sem;
	private WriterMatrix writer;
	private Clock clock = new Clock();

	public Ambulance(int tid, int y, Matrix map2, Semaphore semaforo) {
		this.tid = tid;
		this.x = 10;
		this.y = y;
		this.map = map2;
		this.time = 0;
		this.sem = semaforo;
	}

	public void run() {
		writer = new WriterMatrix(sem, map);
		System.out.println("Create new ambulance: " + tid);
		while (true) {
			clock.waitSeconds(1);	
			int pos = map.getItemMatrix(x, y);
			if (pos == 7) {
				time++;
			} else if (pos == 8 || time == 15) {
				if (writer.setValueSegure(x, y, 0, 8)) {
					clock.waitSeconds(15);
					time = 0;
					writer.setValueOff(x, y, 4);
				}
			}
		}
	}
}
