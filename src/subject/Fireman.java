package subject;

import java.util.Random;
import java.util.concurrent.Semaphore;

import helpers.Clock;
import helpers.Matrix;
import helpers.Scoreboard;
import helpers.WriterMatrix;

public class Fireman extends People {

	private boolean foundVictime, foundFire;
	private int x, xPref;
	private int y, yPref;
	private int id, nF, pos, pref;
	private Matrix map;
	private Random gerador = new Random();
	private Clock clock = new Clock();
	private Semaphore sem;
	private Scoreboard score;
	private WriterMatrix writer;

	public Fireman(int tid, Matrix map2, Semaphore semaphore) {
		this.id = tid;
		this.map = map2;
		this.foundVictime = false;
		this.foundFire = false;
		this.nF = 10;
		this.sem = semaphore;
	}

	public void createFireman() {
		while (true) {
			setX(gerador.nextInt(9) + 1);
			setY(gerador.nextInt(14) + 1);

			if (map.getItemMatrix(getX(), getY()) == 0) {
				if (writer.setValueSegure(getX(), getY(), nF + 1, 0))
					break;
			}
		}
		System.out.printf("Create new Fireman: %d - Position: %d, %d\n", id, getX(), getY());
	}

	public boolean findThing() {
		int back, front, left, right;
		pref = 0;

		if (x + 1 <= 9) { // down
			front = map.getItemMatrix(x + 1, y);
			if (front > 30 && front < 35) {
				pref = 2;
				pos = 1;
				xPref = x + 1;
				yPref = y;
				return true;
			} else if (front == 2) {
				pref = 1;
				pos = 1;
				xPref = x + 1;
				yPref = y;
			}
		}
		if (y + 1 <= 15) { // right
			right = map.getItemMatrix(x, y + 1);
			if (right > 30 && right < 35) {
				pref = 2;
				pos = 2;
				xPref = x;
				yPref = y + 1;
				return true;
			} else if (right == 2) {
				pref = 1;
				pos = 2;
				xPref = x;
				yPref = y + 1;
			}
		}
		if (x - 1 >= 1) { // up
			back = map.getItemMatrix(x - 1, y);
			if (back > 30 && back < 35) {
				pref = 2;
				pos = 3;
				xPref = x - 1;
				yPref = y;
				return true;
			} else if (back == 2) {
				pref = 1;
				pos = 3;
				xPref = x - 1;
				yPref = y;
			}
		}
		if (y - 1 >= 1) { // left
			left = map.getItemMatrix(x, y - 1);
			if (left > 30 && left < 35) {
				pref = 2;
				pos = 4;
				xPref = x;
				yPref = y - 1;
				return true;
			} else if (left == 2) {
				pref = 1;
				pos = 4;
				xPref = x;
				yPref = y - 1;
			}
		}

		if (pref == 1) // found fire
			return true;
		else
			return false;
	}

	public void locomotion() {
		// save the actual position
		x = getX();
		y = getY();

		if (findThing()) {
			if (pref == 2) {
				foundVictime = true;
				System.out.printf("Fireman %d find victime in position (%d-%d)\n", id, xPref, yPref);
			} else {
				foundFire = true;
				System.out.printf("Fireman %d find fire in position (%d-%d)\n", id, xPref, yPref);
			}
			switch (pos) {
			case 1:
				writer.setValueOff(x, y, nF + pos);
				break;
			case 2:
				writer.setValueOff(x, y, nF + pos);
				break;
			case 3:
				writer.setValueOff(x, y, nF + pos);
				break;
			case 4:
				writer.setValueOff(x, y, nF + pos);
			}
		} else {
			int newMove = moveNow();
			if (writer.setValueSegure(getX(), getY(), nF + newMove, 0)) {
				writer.setValueOff(x, y, 0);
			}else {
				setX(x);
				setY(y);
			}
		}
	}

	public boolean findAmbulance() {
		System.out.printf("Fireman %d find the ambulance.\n", id);
		int p;

		while (true) {
			// save the actual position
			x = getX();
			y = getY();

			p = gerador.nextInt(2) + 1;

			if (p == 1 && (x + 1 <= 9)) { // for down
				int m = map.getItemMatrix(x + 1, y);
				if (m == 2) { // found fire
					writer.setValueOff(x + 1, y, 6);
					clock.waitSeconds(5);

					if (writer.setValueSegure(x + 1, y, 61, 6)) {
						writer.setValueOff(x, y, 0);
						setX(x + 1);
						setY(y);
					}
				} else if (m == 0) {
					if (writer.setValueSegure(x + 1, y, 61, m)) {
						map.setItemMatrix(x, y, m);
						setX(x + 1);
						setY(y);
					}
				}

			} else if (y + 1 <= 15) { // for right
				int m = map.getItemMatrix(x, y + 1);

				if (m == 2) { // found fire
					writer.setValueOff(x, y + 1, 6);
					clock.waitSeconds(5);

					if (writer.setValueSegure(x, y + 1, 62, 6)) {
						writer.setValueOff(x, y, 0);
						setX(x);
						setY(y + 1);
					}
				} else if (m == 0) {
					if (writer.setValueSegure(x, y + 1, 62, m)) {
						writer.setValueOff(x, y, m);
						setX(x);
						setY(y + 1);
					}
				}
			}

			clock.waitSeconds(1);

			// found ambulance
			switch(map.getItemMatrix(getX() + 1, getY())) {
			case 4:
				if (writer.setValueSegure(getX() + 1, getY(), 7, 4)) {
					System.out.printf("Fireman %d found the ambulance.\n", id);
					score.addSave();
					writer.setValueOff(getX(), getY(), nF + 1);
					foundVictime = false;
					return true;
				}
				break;
			case 7:
				if (writer.setValueSegure(getX() + 1, getY(), 8, 7)) {
					System.out.printf("Fireman %d found the ambulance.\n", id);
					score.addSave();
					writer.setValueOff(getX(), getY(), nF + 1);
					foundVictime = false;
					return true;
				}
			}
		}
	}

	public void run() {
		writer = new WriterMatrix(sem, map);
		createFireman();
		score = new Scoreboard(map, sem);
		while (true) {
			while (!foundVictime && !foundFire) {
				clock.waitSeconds(1);
				locomotion();
			}
			if (foundFire) {
				if (writer.setValueSegure(xPref, yPref, 6, 2)) {
					clock.waitSeconds(5);
				}
				writer.setValueOff(xPref, yPref, 0);
				foundFire = false;
			}
			if (foundVictime) {
				clock.waitSeconds(1);
				writer.setValueOff(xPref, yPref, 0);
				writer.setValueOff(getX(), getY(), 61);
				if (findAmbulance()) {
					System.out.printf("Fireman %d go angain!\n", id);
				}
			}
		}
	}
}
