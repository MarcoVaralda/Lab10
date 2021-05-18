package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Misurazione;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
    public Misurazione getMisurazione(River river) {
		
		final String sql = "SELECT MIN(f.day) AS primaData, MAX(f.day) AS ultimaData, COUNT(*) AS tot, AVG(f.flow) AS media "
				+ "FROM flow f "
				+ "WHERE f.river=? "
				+ "GROUP BY f.river";
		
		Misurazione m = null;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			if (res.next()) {
				LocalDate data1 = res.getDate("primaData").toLocalDate();
				LocalDate data2 = res.getDate("ultimaData").toLocalDate();
				
				m = new Misurazione(data1,data2,res.getInt("tot"),res.getDouble("media"),river);
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return m;

	}

	public List<Flow> getFlows(River river) {
		final String sql = "SELECT f.id, f.day AS data, f.flow AS flow "
				+ "FROM flow f "
				+ "WHERE f.river=? "
				+ "ORDER BY f.day";
		
		List<Flow> result = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				LocalDate data = res.getDate("data").toLocalDate();
				
				result.add(new Flow(data,res.getDouble("flow"),river));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return result;
	}
}
