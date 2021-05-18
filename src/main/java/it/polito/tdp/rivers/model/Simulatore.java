package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Evento.EventType;

public class Simulatore {
	private double Q;
	private double C;
	private double fout_min;
	private List<Flow> flow;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	
	// Eventi
	PriorityQueue<Evento> eventi;
	
	// Misure in uscita
	int numGiorni;
	int numeroGiorniTotali;
	double c_media;
	
	public void init(double q, double c, double fout,List<Flow> flow, LocalDate dataInizio, LocalDate dataFine) {
		this.Q = q;
		this.C = c;
		this.fout_min = fout;
		this.flow=flow;
		this.dataInizio=dataInizio;
		this.dataFine = dataFine;
		
		this.numGiorni=0;
		this.numeroGiorniTotali=0;
		this.c_media=0.0;
		
		eventi = new PriorityQueue<>();
		
		for(Flow f : flow) {
			eventi.add(new Evento(f.getDay(),EventType.FLUSSO_IN_INGRESSO));
		}
		
		LocalDate ora = dataInizio;
		while(ora.isBefore(dataFine)) {
			eventi.add(new Evento(ora,EventType.FLUSSO_IN_USCITA));
			ora.plusDays(1);
		}
		
		System.out.println(eventi);
	}
	
	

}
