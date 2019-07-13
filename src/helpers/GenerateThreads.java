package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import subject.*;

public class GenerateThreads extends Thread {

	private int nRefugee, nFire, nFireman;
	private int id;
	private Semaphore semaforo = new Semaphore(1);
	private Semaphore semNum = new Semaphore(1);

	Matrix map;
	private Scoreboard score;
	private Timer timer;

	private static List<Fireman> firemen = new ArrayList<Fireman>();
	private static List<Fire> fires = new ArrayList<Fire>();
	private static List<Refugee> refugees = new ArrayList<Refugee>();
	private static List<Ambulance> ambulances = new ArrayList<Ambulance>();

	public GenerateThreads(Matrix map, int nRefugee, int nFireman, int nFire) {
		this.nRefugee = nRefugee;
		this.nFireman = nFireman;
		this.nFire = nFire;
		this.map = map;
		this.id = 0;
	}

	private int getNextId() {
		try {
			semNum.acquire();
			id++;
			semNum.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void begin() {

		score = new Scoreboard(map, semaforo);

		/************************************************************/
		// begin timer
		timer = new Timer(map, semaforo);
		timer.start();
		score.addThread();

		/************************************************************/
		// control the array fires
		while (fires.size() < nFire) {
			fires.add(new Fire(getNextId(), map, semaforo));
		}

		// create the threads fire
		for (Fire fire : fires) {
			fire.start();
			score.addThread();
		}

		/************************************************************/
		// control the array refugee
		while (refugees.size() < nRefugee) {
			refugees.add(new Refugee(getNextId(), map, semaforo));
		}

		// create the threads fire
		for (Refugee r : refugees) {
			r.start();
			score.addThread();
		}

		/************************************************************/
		// control the array fireman
		while (firemen.size() < nFireman) {
			firemen.add(new Fireman(getNextId(), map, semaforo));
		}

		// create the threads fire
		for (Fireman f : firemen) {
			f.start();
			score.addThread();
		}

		/************************************************************/
		// control the array ambulance
		for (int i=12; i<=15; i++) {
			ambulances.add(new Ambulance(getNextId(), i, map, semaforo));
		}

		// create the threads ambulance
		for (Ambulance amb : ambulances) {
			amb.start();
			score.addThread();
		}

	}	
}
