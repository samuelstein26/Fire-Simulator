package helpers;

import java.util.concurrent.Semaphore;

import helpers.Matrix;
import helpers.WriterMatrix;

public class Scoreboard {

	private Matrix map;
	private Semaphore sem;
	private WriterMatrix writer;
	private int first, second, third;
	private int n1, n2, n3;

	public Scoreboard(Matrix map2, Semaphore semaphore) {
		this.map = map2;
		this.sem = semaphore;
		this.n1 = 19;
		this.n2 = 18;
		this.n3 = 17;
		this.writer = new WriterMatrix(semaphore, map2);
	}

	private void calculate() {
		first++;
		if (first > 49) {
			second++;
			first = 40;
			if (second > 49) {
				third++;
				second = 40;
				first = 40;
			}
		}
	}
	
	private void calculateTimer() {
		first++;
		if (first > 49) {
			second++;
			first = 40;
			if (second > 45) {
				third++;
				second = 40;
				first = 40;
			}
		}	
	}

	public void addSave() {
		int x = 1;
		first = map.getItemMatrix(x, n1);
		second = map.getItemMatrix(x, n2);
		third = map.getItemMatrix(x, n3);
		int aux1 = first, aux2 = second, aux3 = third;
		calculate();
		writer.setValue(x, n1, first, aux1);
		if (second != aux2)
			writer.setValue(x, n2, second, aux2);
		if (third != aux3)
			writer.setValue(x, n3, third, aux3);
	}
	
	public void addDead() {
		int x = 3;
		first = map.getItemMatrix(x, n1);
		second = map.getItemMatrix(x, n2);
		third = map.getItemMatrix(x, n3);
		int aux1 = first, aux2 = second, aux3 = third;
		calculate();
		writer.setValue(x, n1, first, aux1);
		if (second != aux2)
			writer.setValue(x, n2, second, aux2);
		if (third != aux3)
			writer.setValue(x, n3, third, aux3);
	}

	public void addFire() {
		int x = 5;
		first = map.getItemMatrix(x, n1);
		second = map.getItemMatrix(x, n2);
		third = map.getItemMatrix(x, n3);
		int aux1 = first, aux2 = second, aux3 = third;
		calculate();
		writer.setValue(x, n1, first, aux1);
		if (second != aux2)
			writer.setValue(x, n2, second, aux2);
		if (third != aux3)
			writer.setValue(x, n3, third, aux3);
	}
	
	public void addThread() {
		int x = 7;
		first = map.getItemMatrix(7, 19);
		second = map.getItemMatrix(x, n2);
		third = map.getItemMatrix(x, n3);
		int aux1 = first, aux2 = second, aux3 = third;
		calculate();
		writer.setValue(x, n1, first, aux1);
		if (second != aux2)
			writer.setValue(x, n2, second, aux2);
		if (third != aux3)
			writer.setValue(x, n3, third, aux3);
	}
	
	public void addTimer() {
		int x = 9;
		first = map.getItemMatrix(x, n1);
		second = map.getItemMatrix(x, n2);
		third = map.getItemMatrix(x, n3);
		int aux1 = first, aux2 = second, aux3 = third;
		calculateTimer();
		writer.setValue(x, n1, first, aux1);
		if (second != aux2)
			writer.setValue(x, n2, second, aux2);
		if (third != aux3)
			writer.setValue(x, n3, third, aux3);
	}
}
