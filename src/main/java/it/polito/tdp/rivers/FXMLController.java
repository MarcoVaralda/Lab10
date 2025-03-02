/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Misurazione;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Simulatore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class FXMLController {
	
	private Model model;
	private Simulatore s;
	
	Misurazione m;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void handleBoxRiver(ActionEvent event) {
    	River scelto = this.boxRiver.getValue();
    	m = model.getMisurazione(scelto);
    	
    	this.txtStartDate.setText(""+m.getPrimaData());
    	this.txtEndDate.setText(""+m.getUltimaData());
    	this.txtNumMeasurements.setText(""+m.getTot());
    	this.txtFMed.setText(""+m.getMedia());
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();
    	
    	String stringFattore = this.txtK.getText();
    	double k=-1;
    	try {
    		k = Double.parseDouble(stringFattore);
    	}
    	catch(NumberFormatException nbe) {
    		this.txtResult.setText("Errore! inserire un numero nel campo fattore di scala");
    		return;
    	}
    	
    	this.s = new Simulatore();
    	this.s.setParameters(k,Double.parseDouble(this.txtFMed.getText()),this.boxRiver.getValue());
    	this.s.run();
    	
    	this.txtResult.appendText("Numero giorni disservizio: "+s.getGiorniDisservizio() +"\n");
    	this.txtResult.appendText("Occupazione media: "+s.getOccupazioneMedia());
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxRiver.getItems().addAll(model.getAllRivers());
    }
}
