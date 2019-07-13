package helpers;

import java.util.concurrent.Semaphore;

public class WriterMatrix {

	private Semaphore sem;
	private Matrix map;

	public WriterMatrix(Semaphore semahpore, Matrix map2) {
		this.sem = semahpore;
		this.map = map2;
	}

	public void setValue(int x, int y, int value, int compare) {
		try {
			sem.acquire();
			if (map.getItemMatrix(x, y) == compare)
				map.setItemMatrix(x, y, value);
			map.setUpdate(true);
			sem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setValueOff(int x, int y, int value) {
		try {
			sem.acquire();
			map.setItemMatrix(x, y, value);
			map.setUpdate(true);
			sem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean setValueSegure(int x, int y, int value, int compare) {
		boolean isOk = false;
		try {
			sem.acquire();
			if (map.getItemMatrix(x, y) == compare) {
				map.setItemMatrix(x, y, value);
				isOk = true;
			}
			map.setUpdate(true);
			sem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return isOk;
	}
}
