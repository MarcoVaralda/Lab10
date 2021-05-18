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
	
	
	public List<Flow> getFlows(River r) {
		return dao.getFlows(r);
	}

}
