package model;

public class Cellula {
	
	private int posizioneX;
	private int posizioneY;
	private int statoIniziale;
	private int statoFuturo;

	public static class StatiCellula {

		public static final int MORTA = 0;
		public static final int VIVA = 1;
		public static final int VIVRA = 2;
		public static final int NASCERA = 3;
		public static final int MORIRA = 4;

	}
	
	public Cellula() {
	}


	public Cellula(int posizioneX, int posizioneY, int statoIniziale, int statoFuturo) {
		this.posizioneX = posizioneX;
		this.posizioneY = posizioneY;
		this.statoIniziale = statoIniziale;
		this.statoFuturo = statoFuturo;
	}
	
	
	public Cellula(Cellula c) {
		this.posizioneX = c.getPosizioneX();
		this.posizioneY = c.getPosizioneY();
		this.statoIniziale = c.getStatoIniziale();
		this.statoFuturo = c.getStatoFuturo();
	}

	public int getPosizioneX() {
		return this.posizioneX;
	}
	public void setPosizioneX(int posizioneX) {
		this.posizioneX = posizioneX;
	}
	public int getPosizioneY() {
		return this.posizioneY;
	}
	public void setPosizioneY(int posizioneY) {
		this.posizioneY = posizioneY;
	}

	public int getStatoIniziale() {
		return this.statoIniziale;
	}

	public void setStatoIniziale(int statoIniziale) {
		this.statoIniziale = statoIniziale;
	}

	public int getStatoFuturo() {
		return this.statoFuturo;
	}

	public void setStatoFuturo(int statoFuturo) {
		this.statoFuturo = statoFuturo;
	}

	@Override
	public String toString() {
		return "Cellula [posizioneX=" + this.posizioneX + ", posizioneY=" + this.posizioneY + ", statoIniziale=" + this.statoIniziale + ", statoFuturo=" + this.statoFuturo + "]";
	}


}
