package model;

import utility.Setting;

public class Matrice {

	public static Matrice instance = null;
	private Cellula[][] table = new Cellula[Setting.CELL_PER_LATO_X][Setting.CELL_PER_LATO_Y];

	public static Matrice getIstance() {
		if (instance == null) {
			return instance = new Matrice();
		} else {
			return instance;
		}
	}

	private Matrice() {
		for (int x = 0; x < Setting.CELL_PER_LATO_X; x++) {
			for (int y = 0; y < Setting.CELL_PER_LATO_Y; y++) {
				this.table[x][y] = new Cellula(x * Setting.LATO_CELL_X, y * Setting.CELL_PER_LATO_Y, Cellula.StatiCellula.MORTA, Cellula.StatiCellula.MORTA);
			}
		}
	}

	public void stampaMatriceIniziale() {
		for (int y = 0; y < Setting.CELL_PER_LATO_Y; y++) {
			for (int x = 0; x < Setting.CELL_PER_LATO_X; x++) {
				System.out.print(this.table[x][y].getStatoIniziale());
			}
			System.out.println("");
		}
	}

	public void stampaMatriceFuturo() {
		for (int y = 0; y < Setting.CELL_PER_LATO_Y; y++) {
			for (int x = 0; x < Setting.CELL_PER_LATO_X; x++) {
				System.out.print(this.table[x][y].getStatoFuturo());
			}
			System.out.println("");
		}
	}

	public Cellula[][] getTable() {
		return this.table;
	}

	public void setTable(Cellula[][] table) {
		this.table = table;
	}

	public void piantaCellula(Cellula c) {
		this.table[c.getPosizioneX()][c.getPosizioneY()].setPosizioneX(c.getPosizioneX());
		this.table[c.getPosizioneX()][c.getPosizioneY()].setPosizioneY(c.getPosizioneY());
		this.table[c.getPosizioneX()][c.getPosizioneY()].setStatoIniziale(c.getStatoIniziale());
		this.table[c.getPosizioneX()][c.getPosizioneY()].setStatoFuturo(c.getStatoFuturo());
	}

}
