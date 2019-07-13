package data;

public enum TileType {
	
	Floor("floor", false), Wall("Wall", false), Fire("fire", false), Cross("cross", false), Ambulance("ambulance", false), 
	F11("f_11", false), F12("f_12", false), F13("f_13", false), F14("f_14", false),
	R21("r_21", false), R22("r_22", false), R23("r_23", false), R24("r_24", false),
	V25("v_25", false), V50("v_50", false), V75("v_75", false),
	N1("n1", false), N2("n2", false), N3("n3", false), N4("n4", false), N5("n5", false), 
	N6("n6", false), N7("n7", false), N8("n8", false), N9("n9", false), N0("n0", false),
	H1("h1", false), H2("h2", false), H3("h3", false), H4("h4", false), H5("h5", false), 
	H6("h6", false), H7("h7", false), H8("h8", false), H9("h9", false), H0("h0", false),
	Lvi("Lvi", false), Lti("Lti", false), Lma("Lma", false), 
	Lmo("Lmo", false), Lrt("Lrt", false), Les("Les", false),
	Lfo("Lfo", false), Lgo("Lgo", false), Lss("Lss", false),
	Lth("Lth", false), Lrea("Lrea", false), Lds("Lds", false),
	Lte("Lte", false), Lmp("Lmp", false), Los("Los", false),
	Black("black", false), FloorHelped("floor", false),
	F61("f_61", false), F62("f_62", false), F63("f_63", false);
	
	String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable){
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
