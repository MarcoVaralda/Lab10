package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Evento.EventType;

public class Simulatore {
	Model model ;
	
	// Parametri input
	private double k;
	private double fMed;
	private River river;
	
	public Simulatore() {
		this.model = new Model();
	}
	
	public void setParameters(double k, double fMed, River river) {		
		this.k = k;
		this.fMed = fMed*3600*24;
		this.river = river;
	}
	
	// Stato del mondo
	private double Q;
	private double C;
	private double fout_min;
	private double fout;
	private List<Flow> flows;
	
	// Eventi
	PriorityQueue<Evento> eventi;
	
	// Misure in uscita
	int numGiorniDiss;
	double c_media;
	
	public void run() {
		this.Q = this.k*(this.fMed)*30;
		this.C = Q/2;
		this.fout_min = 0.8*fMed;
		this.flows=model.getFlows(this.river);
		
		this.numGiorniDiss=0;
		this.c_media=0.0;
		
		this.eventi = new PriorityQueue<Evento>();
		
		// Inserisco gli eventi iniziali
		for(Flow f : flows) {
			this.eventi.add(new Evento(f.getDay(),EventType.INGRESSO,f));
		}
		
		// Processo gli eventi
		while(!this.eventi.isEmpty()) {
			Evento e = this.eventi.poll();
			processEvent(e);
		}
		
	}

	private void processEvent(Evento e) {
		
		switch(e.getType()) {
		case INGRESSO:
			// Aggiorno la capienza corrente
			this.C += e.getFlow().getFlow();
			
			if(this.C>this.Q) { // --> TRACIMAZIONE
				this.eventi.add(new Evento(e.getData(),EventType.TRACIMAZIONE,e.getFlow()));
			}
			
			int p = (int) (Math.random()*100);
			if(p<5) { // --> IRRIGAZIONE
				this.eventi.add(new Evento(e.getData(),EventType.IRRIGAZIONE,e.getFlow()));
			}
			else { // --> USCITA
				this.eventi.add(new Evento(e.getData(),EventType.USCITA,e.getFlow()));
			}
			break;
			
		case USCITA:
			if(this.C<this.fout_min) { // Uscita con fout_min impossibile
				this.C = 0;
				this.numGiorniDiss++;
			}
			else { // Uscita con fout_min possibile
				this.C -= this.fout_min;
				this.c_media += C;
			}
			break;
			
		case TRACIMAZIONE:
			this.C -= (this.C-this.Q);
			break;
			
		case IRRIGAZIONE:
			this.fout = 10*this.fout_min;
			if(this.C<this.fout) { // Irrigazione non possibile (o parzialmente possibile)
				this.numGiorniDiss++;
				this.C=0;
			}
			else { // Irrigazione possibile
				this.C -= this.fout;
				this.c_media += this.C;
			}
			break;
		}
		
	}
	
	// Metodi get per le misure in uscita
	public int getGiorniDisservizio() {
		return this.numGiorniDiss;
	}
	
	public double getOccupazioneMedia() {
		return (this.c_media/this.flows.size());
	}
	
	

}
