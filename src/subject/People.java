package subject;

import java.util.Random;

public class People extends Thread{

	private boolean move;
	private int x;
	private int y;
	
	public People() {
		this.move = true;
	}
	
	public int moveNow() {
		int pos = 0;
		if (move == true) {
			Random gerador = new Random();
			pos = gerador.nextInt(4) + 1;
		}
		
		switch(pos) {
		case 1:
			if (x < 9)
				x++;
			break;
		case 2:
			if (y < 15)
				y++;
			break;
		case 3:
			if (x > 1)
				x--;
			break;
		case 4:
			if (y > 1)
				y--;
			break;
		}
		return pos;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
