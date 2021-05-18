package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Misurazione {
	
	private LocalDate primaData;
	private LocalDate ultimaData;
	private int tot;
	private double media;
	private River river;
	
	public Misurazione(LocalDate primaData, LocalDate ultimaData, int tot, double media, River river) {
		this.primaData = primaData;
		this.ultimaData = ultimaData;
		this.tot = tot;
		this.media = media;
		this.river=river;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	public LocalDate getPrimaData() {
		return primaData;
	}

	public void setPrimaData(LocalDate primaData) {
		this.primaData = primaData;
	}

	public LocalDate getUltimaData() {
		return ultimaData;
	}

	public void setUltimaData(LocalDate ultimaData) {
		this.ultimaData = ultimaData;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

}
