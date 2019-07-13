package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import helpers.GenerateThreads;
import helpers.Matrix;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Window.*;

public class Boot {
	
	private int nRefugee;
	private int nFireman;
	private int nFire;

	public Boot(int nF, int nFM, int nR) {
		this.nFire = nF;
		this.nFireman = nFM;
		this.nRefugee = nR;
	}
	
	public void beginAll() {
		
		BeginSession();

		Matrix map = new Matrix();

		TileGrid grid = new TileGrid(map);
		grid.Paint(map);

		GenerateThreads generate = new GenerateThreads(map, nRefugee, nFireman, nFire);
		generate.begin();
		
		while (!Display.isCloseRequested()) {
			grid.Draw();
			Display.update();
			Display.sync(10);
			
			if (map.getUpdate()) {
				grid.RePaint(map);
				map.setUpdate(false);
			}
			
			// exit display press ESC
			if (Keyboard.next()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					break;
				}
			}
		}

		Display.destroy();
		System.exit(0);
	}

}