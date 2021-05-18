package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Evento implements Comparable<Evento> {
	
	public enum EventType {
		FLUSSO_IN_INGRESSO,
		FLUSSO_IN_USCITA,
		TRACIMAZIONE
	}
	
	private LocalDate data;
	private EventType type;
	
	public Evento(LocalDate data, EventType type) {
		this.data = data;
		this.type = type;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Evento [data=" + data + ", type=" + type + "]";
	}

	@Override
	public int compareTo(Evento altro) {
		if(this.data.compareTo(altro.getData())<0) {
			return -1;
		}	
		return 1;
	}

}
