package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao; 
	List<River> allRivers;
	
	public Model() {
		dao = new RiversDAO();
		allRivers = new LinkedList<>();
	}
	
	public List<River> getAllRivers() {
		allRivers = dao.getAllRivers();
		return allRivers;
	}
	
	public Misurazione getMisurazione(River river) {
		return dao.getMisurazione(river);
	}
	
	
	// Simulatore
	
	public void simula(double k, Misurazione m) {
		
		// Parametri di simulazione
		double Q = k*m.getMedia()*30;
		double C = Q/2;
		double fout = 0.8*m.getMedia();
		List<Flow> flows = dao.getFlows(m.getRiver());
		
		Simulatore s = new Simulatore();
		s.init(Q,C,fout,flows, m.getPrimaData(), m.getUltimaData());
		
	}

}
