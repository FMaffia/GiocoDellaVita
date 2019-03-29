package controller;

import model.Cellula;
import model.GardenCellula;
import model.Matrice;
import processing.core.PApplet;
import processing.core.PImage;
import utility.FormazioniGioco;
import utility.Setting;

public class TavoloDiGioco extends PApplet {
	public boolean start = false;
	public int imgWidth=50;
	public int imgHeight=50;
	public int partenzaImgX = 750;
	public int partenzaImgY = 700;
	PImage img;

	public static void main(String[] args) { 
		PApplet.main("controller.TavoloDiGioco"); 
	}

	@Override
	public void settings() {
		size(Setting.WIDTH, Setting.HEIGHT+50);
	}
	@Override
	public void setup() {
		background(0);
		this.disegnaGrigliaGioco();
		//key_life(FormazioniGioco.LIFE_KEY_TONY, 4, 4);
		img = loadImage("start.png");

	}

	@Override
	public void draw() {
		this.disegnaGrigliaGioco();
		image(img,partenzaImgX,partenzaImgY, imgWidth,imgHeight);
		
		if(mousePressed==true && mouseX > partenzaImgX && mouseX < partenzaImgX +imgWidth && mouseY > partenzaImgY && mouseY <700 + partenzaImgY){
			start = true;
		}
		if(start == true) {
			avanzaGenerazione();
			delay(100);
			startVita();
		}
	}	

	@Override
	public void mouseDragged() {

		Cellula c = new Cellula();
		if (this.mouseButton == LEFT) {
			c.setPosizioneX(this.mouseX / Setting.LATO_CELL_X);
			c.setPosizioneY(this.mouseY / Setting.LATO_CELL_Y);
			c.setStatoIniziale(Cellula.StatiCellula.VIVA);
			drawCellula(c);
			Matrice.getIstance().piantaCellula(c);
		} else {
			c.setPosizioneX(this.mouseX / Setting.LATO_CELL_X);
			c.setPosizioneY(this.mouseY / Setting.LATO_CELL_Y);
			c.setStatoIniziale(Cellula.StatiCellula.MORTA);
			drawCellula(c);
			Matrice.getIstance().piantaCellula(c);	
		}
	}




	private void startVita() {
		Cellula[][] m = Matrice.getIstance().getTable();
		for (int y = 0; y < Setting.CELL_PER_LATO_Y; y++) {
			for (int x = 0; x < Setting.CELL_PER_LATO_X; x++) {
				calcoloStatoFuturo(m[x][y]);
			}
		}
	}

	private void disegnaGrigliaGioco() {
		stroke(255, 150);
		for (int x = 0; x < Setting.WIDTH; x += Setting.LATO_CELL_X) {
			line(x,0,x,this.height);
		}
		for (int y = 0; y < Setting.HEIGHT; y += Setting.LATO_CELL_Y) {
			line(0,y,this.width,y);
		}
	}

	public void drawCellula(Cellula c) {
		if (c.getStatoIniziale() == Cellula.StatiCellula.MORTA) {
			fill(0);
		} else {
			fill(33, 209, 0);
		}
		rect(c.getPosizioneX() * Setting.LATO_CELL_X, c.getPosizioneY() * Setting.LATO_CELL_Y, Setting.LATO_CELL_X, Setting.LATO_CELL_Y);
	}

	public void calcoloStatoFuturo(Cellula c) {
		Cellula[][] m = Matrice.getIstance().getTable();
		GardenCellula gc = new GardenCellula(c);
		int celluleVive = 0;
		for (int x = gc.getX_sinistro(); x <= gc.getX_destro(); x++) {
			for (int y = gc.getY_alto(); y <= gc.getY_basso(); y++) {
				celluleVive = celluleVive + m[x][y].getStatoIniziale();
			}
		}

		if (c.getStatoIniziale() == Cellula.StatiCellula.VIVA) {
			celluleVive -= 1;
		}
		if (c.getStatoIniziale() == Cellula.StatiCellula.VIVA) {
			if (celluleVive < 2 || celluleVive > 3) {
				c.setStatoFuturo(Cellula.StatiCellula.MORIRA);
			} else {
				c.setStatoFuturo(Cellula.StatiCellula.VIVRA);
			}
		} else if (c.getStatoIniziale() == Cellula.StatiCellula.MORTA) {

			if (celluleVive == 3) {
				c.setStatoFuturo(Cellula.StatiCellula.NASCERA);
			} else {
				c.setStatoFuturo(Cellula.StatiCellula.MORIRA);
			}
		}
	}

	public void avanzaGenerazione() {
		Cellula[][] m = Matrice.getIstance().getTable();

		for (int y = 0; y < Setting.CELL_PER_LATO_Y; y++) {
			for (int x = 0; x < Setting.CELL_PER_LATO_X; x++) {
				int statoFuturo = m[x][y].getStatoFuturo();
				if (statoFuturo == Cellula.StatiCellula.VIVRA) {
					m[x][y].setStatoIniziale(Cellula.StatiCellula.VIVA);
				}
				else if (statoFuturo == Cellula.StatiCellula.MORIRA) {
					m[x][y].setStatoIniziale(Cellula.StatiCellula.MORTA);
				} else if (statoFuturo == Cellula.StatiCellula.NASCERA) {
					m[x][y].setStatoIniziale(Cellula.StatiCellula.VIVA);
				}
				m[x][y].setStatoFuturo(Cellula.StatiCellula.MORIRA);
				drawCellula(m[x][y]);
			}

		}
	}

	public void key_life(String life_key, int limiteX, int limiteY) {

		int i = 0;
		int randomX = (int) random(0 + limiteX, Setting.CELL_PER_LATO_X - limiteX);
		int randomY = (int) random(0 + limiteY, Setting.CELL_PER_LATO_Y - limiteY);
		for (int y = 0; y < limiteY; y++) {
			for (int x = 0; x < limiteX; x++) {
				char lettera = life_key.charAt(i);
				int dna = Character.getNumericValue(lettera);
				if (dna == 1) {
					Cellula c = new Cellula();
					c.setPosizioneX(x + randomX);
					c.setPosizioneY(y + randomY);
					c.setStatoIniziale(Cellula.StatiCellula.VIVA);
					Matrice.getIstance().piantaCellula(c);
				}
				i++;
			}
		}
	}


}