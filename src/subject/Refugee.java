package subject;

import java.util.Random;
import java.util.concurrent.Semaphore;

import helpers.Clock;
import helpers.Matrix;
import helpers.Scoreboard;
import helpers.WriterMatrix;

public class Refugee extends People {

	private int nR, nV, tid;
	boolean isDead, isSave;
	private int xFire, yFire;
	private int life, time;
	private boolean locomove;
	private Scoreboard score;
	private Matrix map;
	private Semaphore sem;
	private Random gerador = new Random();
	private Clock clock = new Clock();
	private WriterMatrix writer;

	public Refugee(int tid, Matrix map2, Semaphore semaforo) {
		this.tid = tid;
		this.map = map2;
		this.nR = 20;
		this.nV = 31;
		this.sem = semaforo;
	}

	public boolean getIsDead() {
		return isDead;
	}

	private void reduceLife() {
		this.life -= 25;
	}

	public void createRefugee() {
		while (true) {
			setX(gerador.nextInt(9) + 1);
			setY(gerador.nextInt(14) + 1);

			if (writer.setValueSegure(getX(), getY(), nR + 1, 0))
				break;
		}
		isDead = false;
		locomove = true;
		isSave = false;
		life = 100;
		System.out.printf("Create new Refugee: %d - Position: %d, %d\n", tid, getX(), getY());
	}

	public void locomotion() {
		// save the actual position
		int x = getX();
		int y = getY();

		int newMove = moveNow();

		switch (map.getItemMatrix(getX(), getY())) {
		case 0: // empty position
			if (writer.setValueSegure(getX(), getY(), nR + newMove, 0)) {
				writer.setValueOff(x, y, 0);
			}else {
				setX(x);
				setY(y);
			}
			break;
		case 2: // fire
			reduceLife();
			writer.setValueOff(x, y, nV);
			locomove = false;
			xFire = getX();
			yFire = getY();
			setX(x);
			setY(y);
			System.out.printf("Victime %d-%d found fire in %d-%d\n", getX(), getY(), xFire, yFire);
			break;
		default:
			setX(x);
			setY(y);
		}
	}

	public void victim(int i) {
		// save the actual position
		int x = getX();
		int y = getY();

		switch (i) {
		case 75:
			nV++;
			writer.setValueOff(x, y, nV);
			reduceLife();
			break;
		case 50:
			nV++;
			writer.setValueOff(x, y, nV);
			reduceLife();
			break;
		case 25:
			writer.setValueOff(x, y, 3);
			reduceLife();
			score.addDead();
			break;
		case -1:
			if (!writer.setValueSegure(x, y, 0, 3)) {
				System.out.printf("Error of the floor: %d\n", map.getItemMatrix(x, y));
			}
		}
	}

	public void run() {
		writer = new WriterMatrix(sem, map);
		score = new Scoreboard(map, sem);

		while (true) {
			createRefugee();
			time = 0;
			locomove = true;

			while (true) {
				while (locomove) {
					clock.waitSeconds(1);
					locomotion();
				}

				// is victime
				while (!locomove && !isDead && !isSave && (map.getItemMatrix(xFire, yFire) == 2 || map.getItemMatrix(xFire, yFire) == 6)) {
					clock.waitSeconds(1);

					if (map.getItemMatrix(getX(), getY()) == 5) {
						isSave = true;
						writer.setValueOff(getX(), getY(), 0);
						break;
					} else {
						switch (time) {
						case 5:
							victim(life); // life 50
							break;
						case 10:
							victim(life); // life 25
							break;
						case 15:
							victim(life); // cross
							isDead = true;
						}
					}
					time++;
				}
				if (isDead || isSave) {
					if (isDead){
						clock.waitSeconds(5);
						victim(-1);
					}
					break;
				} else {
					locomove = true;
				}
			}
		}
	}
}
