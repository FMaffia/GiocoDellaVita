package model;

public class Cellula {
	
	private int posizioneX;
	private int posizioneY;
	private String statoIniziale;
	private String statoFuturo;
	
	public Cellula() {
	}

	public Cellula(int posizioneX, int posizioneY, String statoIniziale, String statoFuturo) {
		super();
		this.posizioneX = posizioneX;
		this.posizioneY = posizioneY;
		this.statoIniziale = statoIniziale;
		this.statoFuturo = statoFuturo;
	}
	
	
	public int getPosizioneX() {
		return posizioneX;
	}
	public void setPosizioneX(int posizioneX) {
		this.posizioneX = posizioneX;
	}
	public int getPosizioneY() {
		return posizioneY;
	}
	public void setPosizioneY(int posizioneY) {
		this.posizioneY = posizioneY;
	}
	public String getStatoIniziale() {
		return statoIniziale;
	}
	public void setStatoIniziale(String statoIniziale) {
		this.statoIniziale = statoIniziale;
	}
	public String getStatoFuturo() {
		return statoFuturo;
	}
	public void setStatoFuturo(String statoFuturo) {
		this.statoFuturo = statoFuturo;
	}



}
