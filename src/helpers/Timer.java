package helpers;

import java.util.concurrent.Semaphore;

import helpers.Clock;
import helpers.Matrix;
import helpers.Scoreboard;

public class Timer extends Thread {

	private Clock clock = new Clock();
	private Semaphore sem;
	private Scoreboard score;
	private Matrix map;

	public Timer(Matrix map2, Semaphore semaphore) {
		this.map = map2;
		this.sem = semaphore;
	}

	public void run() {
		score = new Scoreboard(map, sem);
		while (true) {
			clock.waitSeconds(1);
			score.addTimer();
		}
	}
}
