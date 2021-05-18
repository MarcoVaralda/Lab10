package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Evento implements Comparable<Evento> {
	
	public enum EventType {
		INGRESSO,
		USCITA,
		TRACIMAZIONE,
		IRRIGAZIONE
	}
	
	private LocalDate data;
	private Flow flow;
	private EventType type;
	
	public Evento(LocalDate data, EventType type, Flow flow) {
		this.data = data;
		this.type = type;
		this.flow=flow;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
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
	public int compareTo(Evento o) {
		if(this.data.equals(o.data)) {
			if(this.type == EventType.INGRESSO)
				return -1;
			else
				return 1;
		}
		else
			return this.data.compareTo(o.data);
	}

}
