package controller;

import model.Cellula;
import processing.core.PApplet;

public class TavoloDiGioco extends PApplet {
	static int width = 800;
	static int height = 800;
	static int cellPerLato = 10;
	static int grandezzaLatoCellula = width/cellPerLato;
	static Cellula[][] table = new Cellula[cellPerLato][cellPerLato];


	public static void main(String[] args) { 
		PApplet.main("controller.TavoloDiGioco"); 
		
		
		
		
	}

	public void settings() {
		size(width,height);
	}
	public void setup() {
		background(0);
		startMatrice();
		disegnaGrigliaGioco();
		accendiCellula(6, 1);
	}

	public void draw() {
		
	}	
	
	private void disegnaGrigliaGioco() {
		stroke(255);
		for(int x = 0; x < width; x+= grandezzaLatoCellula) {
			line(x,0,x,height);
		}
		for(int y = 0; y < height; y+= grandezzaLatoCellula) {
			line(0,y,width,y);
		}
	}
	
	public void startMatrice() {
		for(int x = 0; x < table.length-1; x++) {
			for(int y = 0; y < table.length-1; y++) {
				table[x][y] = new Cellula(x+grandezzaLatoCellula,y+grandezzaLatoCellula,"SPENTO","SPENTO");
			}
		}
	}
	
	public void accendiCellula(int x, int y) {
		fill(0,255,255);
		rect(table[x][y].getPosizioneX(), table[x][y].getPosizioneY(),grandezzaLatoCellula,grandezzaLatoCellula);
	}
}