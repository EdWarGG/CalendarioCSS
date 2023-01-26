package calendariocss;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Controller implements Initializable {

	//model
	private IntegerProperty año = new SimpleIntegerProperty();

	//view
	@FXML
	private List<CalendarioMensual> mesesCalendarioList;
	@FXML
	private BorderPane view;
	@FXML
	private Label añoLabel;

	
	public Controller() throws IOException{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Calendario.fxml"));
			loader.setController(this);
			loader.load();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		año.set(LocalDate.now().getYear());
		añoLabel.textProperty().bind(año.asString());
		int numeroMes = 0;
		for (CalendarioMensual mes : mesesCalendarioList) {
			mes.setMes(numeroMes + 1);
			mes.añoProperty().bind(año);
			numeroMes++;
		}
	}

	
	public BorderPane getView() {
		return view;
	}
	

	@FXML
	private void onAnteriorAction(ActionEvent e) {
		año.set(año.get() - 1);
	}

	
	@FXML
	private void onSiguienteAction(ActionEvent e) {
		año.set(año.get() + 1);
	}

}
