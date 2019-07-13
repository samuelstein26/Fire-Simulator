package data;

import static helpers.Window.*;

import helpers.Matrix;

public class TileGrid {

	public Tile[][] map;
	private Tile t;
	
	public TileGrid(Matrix map2) {
		Paint(map2);
	}
	
	public void SetTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * 64, yCoord * 64, 64, 64, type);
	}
	
	public Tile GetTile(int xCoord, int yCoord) {
		return map[xCoord][yCoord];
	}
	
	public void Draw() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				t = map[i][j];
				if (t != null) {
					DrawQuadTex(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
				}
			}
		}
	}
	
	public void Paint(Matrix map2) {
		map = new Tile[20][11];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (map2.getItemMatrix(j, i)) {
				//scenario
				case 0:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Floor);
					break;
				case 1:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Wall);
					break;
				case 2:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Fire);
					break;
				case 3:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Cross);
					break;
				case 4:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Ambulance);
					break;
				case 5:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.FloorHelped);
					break;
				case 6:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Fire);
					break;
				case 7:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Ambulance);
					break;
				case 8:
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Ambulance);
					break;
				//fireman
				case 11 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.F11);
					break;
				case 12 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.F12);
					break;
				case 13 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.F13);
					break;
				case 14 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.F14);
					break;
					//refugee
				case 21 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.R21);
					break;
				case 22 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.R22);
					break;
				case 23 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.R23);
					break;
				case 24 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.R24);
					break;
					//victim
				case 31 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.V75);
					break;
				case 32 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.V50);
					break;
				case 33 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.V25);
					break;
					//number
				case 40 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N0);
					break;
				case 41 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N1);
					break;
				case 42 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N2);
					break;
				case 43 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N3);
					break;
				case 44 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N4);
					break;
				case 45 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N5);
					break;
				case 46 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N6);
					break;
				case 47 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N7);
					break;
				case 48 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N8);
					break;
				case 49 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.N9);
					break;
				case 50 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Black);
					break;
					//phrases
				case 51 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lvi);
					break;
				case 52 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lti);
					break;
				case 53 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lma);
					break;
				case 54 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lmo);
					break;
				case 55 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lrt);
					break;
				case 56 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Les);
					break;
				case 57 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lfo);
					break;
				case 58 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lgo);
					break;
				case 59 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lss);
					break;
					//fireman moving
				case 61 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.F61);
					break;
				case 62 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.F62);
					break;
				case 63 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.F63);
					break;
					//phrases
				case 71 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lth);
					break;
				case 72 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lrea);
					break;
				case 73 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lds);
					break;
				case 74 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lte);
					break;
				case 75 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Lmp);
					break;
				case 76 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.Los);
					break;
					//number timer
				case 80 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H0);
					break;
				case 81 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H1);
					break;
				case 82 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H2);
					break;
				case 83 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H3);
					break;
				case 84 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H4);
					break;
				case 85 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H5);
					break;
				case 86 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H6);
					break;
				case 87 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H7);
					break;
				case 88 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H8);
					break;
				case 89 :
					map[i][j] = new Tile (i * 64, j * 64, 64, 64, TileType.H9);
					break;
				} 
			}
		}
	}
	
	public void RePaint(Matrix map2) {
		map = new Tile[20][11];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (map2.getItemMatrix(j, i)) {
				//scenario
				case 0:
					SetTile (i, j, TileType.Floor);
					break;
				case 1:
					SetTile (i, j, TileType.Wall);
					break;
				case 2:
					SetTile (i, j, TileType.Fire);
					break;
				case 3:
					SetTile (i, j, TileType.Cross);
					break;
				case 4:
					SetTile (i, j, TileType.Ambulance);
					break;
				case 5:
					SetTile (i, j, TileType.FloorHelped);
					break;
				case 6:
					SetTile (i, j, TileType.Fire);
					break;
				case 7:
					SetTile (i, j, TileType.Ambulance);
					break;
				case 8:
					SetTile (i, j, TileType.Ambulance);
					break;
				//fireman
				case 11 :
					SetTile (i, j, TileType.F11);
					break;
				case 12 :
					SetTile (i, j, TileType.F12);
					break;
				case 13 :
					SetTile (i, j, TileType.F13);
					break;
				case 14 :
					SetTile (i, j, TileType.F14);
					break;
					//refugee
				case 21 :
					SetTile (i, j, TileType.R21);
					break;
				case 22 :
					SetTile (i, j, TileType.R22);
					break;
				case 23 :
					SetTile (i, j, TileType.R23);
					break;
				case 24 :
					SetTile (i, j, TileType.R24);
					break;
					//victim
				case 31 :
					SetTile (i, j, TileType.V75);
					break;
				case 32 :
					SetTile (i, j, TileType.V50);
					break;
				case 33 :
					SetTile (i, j, TileType.V25);
					break;
					//number
				case 40 :
					SetTile (i, j, TileType.N0);
					break;
				case 41 :
					SetTile (i, j, TileType.N1);
					break;
				case 42 :
					SetTile (i, j, TileType.N2);
					break;
				case 43 :
					SetTile (i, j, TileType.N3);
					break;
				case 44 :
					SetTile (i, j, TileType.N4);
					break;
				case 45 :
					SetTile (i, j, TileType.N5);
					break;
				case 46 :
					SetTile (i, j, TileType.N6);
					break;
				case 47 :
					SetTile (i, j, TileType.N7);
					break;
				case 48 :
					SetTile (i, j, TileType.N8);
					break;
				case 49 :
					SetTile (i, j, TileType.N9);
					break;
				case 50 :
					SetTile (i, j, TileType.Black);
					break;
					//phrases
				case 51 :
					SetTile (i, j, TileType.Lvi);
					break;
				case 52 :
					SetTile (i, j, TileType.Lti);
					break;
				case 53 :
					SetTile (i, j, TileType.Lma);
					break;
				case 54 :
					SetTile (i, j, TileType.Lmo);
					break;
				case 55 :
					SetTile (i, j, TileType.Lrt);
					break;
				case 56 :
					SetTile (i, j, TileType.Les);
					break;
				case 57 :
					SetTile (i, j, TileType.Lfo);
					break;
				case 58 :
					SetTile (i, j, TileType.Lgo);
					break;
				case 59 :
					SetTile (i, j, TileType.Lss);
					break;
					//fireman moving
				case 61 :
					SetTile (i, j, TileType.F61);
					break;
				case 62 :
					SetTile (i, j, TileType.F62);
					break;
				case 63 :
					SetTile (i, j, TileType.F63);
					break;
					//phrases
				case 71 :
					SetTile (i, j, TileType.Lth);
					break;
				case 72 :
					SetTile (i, j, TileType.Lrea);
					break;
				case 73 :
					SetTile (i, j, TileType.Lds);
					break;
				case 74 :
					SetTile (i, j, TileType.Lte);
					break;
				case 75 :
					SetTile (i, j, TileType.Lmp);
					break;
				case 76 :
					SetTile (i, j, TileType.Los);
					break;
					//number timer
				case 80 :
					SetTile (i, j, TileType.H0);
					break;
				case 81 :
					SetTile (i, j, TileType.H1);
					break;
				case 82 :
					SetTile (i, j, TileType.H2);
					break;
				case 83 :
					SetTile (i, j, TileType.H3);
					break;
				case 84 :
					SetTile (i, j, TileType.H4);
					break;
				case 85 :
					SetTile (i, j, TileType.H5);
					break;
				case 86 :
					SetTile (i, j, TileType.H6);
					break;
				case 87 :
					SetTile (i, j, TileType.H7);
					break;
				case 88 :
					SetTile (i, j, TileType.H8);
					break;
				case 89 :
					SetTile (i, j, TileType.H9);
					break;
				}
			}
		}
	}
}
