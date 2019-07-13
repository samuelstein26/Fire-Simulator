package subject;

import java.util.Random;
import java.util.concurrent.Semaphore;

import helpers.Clock;
import helpers.Matrix;
import helpers.Scoreboard;
import helpers.WriterMatrix;

public class Fire extends Thread {

	private int tid;
	private int x, y;
	private Matrix map;
	private Semaphore sem;
	private Scoreboard score;
	private WriterMatrix writer;
	private Clock clock = new Clock();
	private Random gerador = new Random();

	public Fire(int tid, Matrix map2, Semaphore semaphore) {
		this.tid = tid;
		this.map = map2;
		this.sem = semaphore;
	}

	public void createFire() {
		while (true) {
			x = gerador.nextInt(9) + 1;
			y = gerador.nextInt(14) + 1;
			int c = map.getItemMatrix(x, y);
			if (c == 0) {
				if (writer.setValueSegure(x, y, 2, c))
					break;
			}
		}
		System.out.printf("Create new Fire: %d - Position: %d, %d\n", tid, x, y);
	}

	public long getId() {
		return tid;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setId(int id) {
		this.tid = id;
	}

	@Override
	public void run() {
		score = new Scoreboard(map, sem);
		writer = new WriterMatrix(sem, map);
		while (true) {
			createFire();
			while (map.getItemMatrix(x, y) != 0) {
				clock.waitSeconds(1);
			}
			score.addFire();
			clock.waitSeconds(1);
		}
	}
}
