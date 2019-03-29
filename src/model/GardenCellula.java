package model;

import utility.Setting;

public class GardenCellula extends Cellula {
	 
	public int x_sinistro;
	public int x_destro;
	public int y_alto;
	public int y_basso;


	public GardenCellula(Cellula c) {
		super(c);
		creaGiardino(c);
	}

	private void creaGiardino(Cellula c) {
		this.x_destro = c.getPosizioneX() + 1;
		this.x_sinistro = c.getPosizioneX() - 1;
		this.y_alto = c.getPosizioneY() - 1;
		this.y_basso = c.getPosizioneY() + 1;

		if (this.x_sinistro < 0) {
			this.x_sinistro = 0;
		}
		if (this.x_destro >= Setting.CELL_PER_LATO_X - 1) {
			this.x_destro = Setting.CELL_PER_LATO_X - 1;
		}
		if (this.y_alto < 0) {
			this.y_alto = 0;
		}
		if (this.y_basso >= Setting.CELL_PER_LATO_Y - 1) {
			this.y_basso = Setting.CELL_PER_LATO_Y - 1;
		}

	}

	@Override
	public String toString() {
		return "GardenCellula [x_sinistro=" + this.x_sinistro + ", x_destro=" + this.x_destro + ", y_alto=" + this.y_alto + ", y_basso=" + this.y_basso + "]";
	}

	public int getX_sinistro() {
		return this.x_sinistro;
	}

	public void setX_sinistro(int x_sinistro) {
		this.x_sinistro = x_sinistro;
	}

	public int getX_destro() {
		return this.x_destro;
	}

	public void setX_destro(int x_destro) {
		this.x_destro = x_destro;
	}

	public int getY_alto() {
		return this.y_alto;
	}

	public void setY_alto(int y_alto) {
		this.y_alto = y_alto;
	}

	public int getY_basso() {
		return this.y_basso;
	}

	public void setY_basso(int y_basso) {
		this.y_basso = y_basso;
	}

}
