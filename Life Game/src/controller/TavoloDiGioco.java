package controller;
import model.Cellula;
import model.GardenCellula;
import model.Matrice;
import processing.core.PApplet;
import utility.Setting;

public class TavoloDiGioco extends PApplet {


	public static void main(String[] args) { 
		PApplet.main("controller.TavoloDiGioco"); 
	}

	@Override
	public void settings() {
		size(Setting.WIDTH, Setting.HEIGHT);
	}
	@Override
	public void setup() {
		background(0);
		this.disegnaGrigliaGioco();
		genesi();

		// startVita();
		// avanzaGenerazione();
		// for (int y = 0; y < Setting.CELL_PER_LATO_Y; y++) {
		// for (int x = 0; x < Setting.CELL_PER_LATO_X; x++) {
		// calcoloStatoFuturo(m[x][y]);
		// }
		// }
		//
		// Matrice.getIstance().stampaMatriceFuturo();
		//
		// avanzaGenerazione();
		//
		// Matrice.getIstance().stampaMatriceIniziale();
		// System.out.println();
	}

	@Override
	public void draw() {
		background(0);
		this.disegnaGrigliaGioco();
		startVita();
		delay(1000);
		avanzaGenerazione();
	}	

	private void startVita() {
		Cellula[][] m = Matrice.getIstance().getTable();
		for (int y = 0; y < Setting.CELL_PER_LATO_Y; y++) {
			for (int x = 0; x < Setting.CELL_PER_LATO_X; x++) {
				calcoloStatoFuturo(m[x][y]);
			}
		}
	}
	private void genesi() {
		Cellula c = new Cellula();
		int start_x = (int) (random(0, Setting.CELL_PER_LATO_X - 1));
		int start_y = (int) (random(0, Setting.CELL_PER_LATO_Y - 1));
		c.setPosizioneX(start_x);
		c.setPosizioneY(start_y);
		c.setStatoIniziale(Cellula.StatiCellula.VIVA);
		Matrice.getIstance().piantaCellula(c);
		this.drawCellula(c);

		c.setPosizioneX(start_x - 1);
		c.setPosizioneY(start_y);
		c.setStatoIniziale(Cellula.StatiCellula.VIVA);
		Matrice.getIstance().piantaCellula(c);
		this.drawCellula(c);

		c.setPosizioneX(start_x);
		c.setPosizioneY(start_y + 1);
		c.setStatoIniziale(Cellula.StatiCellula.VIVA);
		Matrice.getIstance().piantaCellula(c);
		this.drawCellula(c);

		c.setPosizioneX(start_x);
		c.setPosizioneY(start_y - 1);
		c.setStatoIniziale(Cellula.StatiCellula.VIVA);
		Matrice.getIstance().piantaCellula(c);
		this.drawCellula(c);

		c.setPosizioneX(start_x + 1);
		c.setPosizioneY(start_y - 1);
		c.setStatoIniziale(Cellula.StatiCellula.VIVA);
		Matrice.getIstance().piantaCellula(c);
		this.drawCellula(c);
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
			fill(255, 150, 0);
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
}