package calendariocss;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalendarioMensual extends GridPane implements Initializable {

	//model
	private IntegerProperty mes = new SimpleIntegerProperty();
	private IntegerProperty año = new SimpleIntegerProperty();
	
	int primerDia;
	int ultimoDia;

	//view
	@FXML
	private Label mesLabel;
	@FXML
	private List<Label> diasList;

	
	public CalendarioMensual() throws IOException{
		super();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalendarioMensual.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.mes.addListener((o, ov, nv) -> cambiarFecha(o, ov, nv));
		this.año.addListener((o, ov, nv) -> cambiarFecha(o, ov, nv));
	}

	
	private void cambiarFecha(ObservableValue<? extends Number> o, Number ov, Number nv) {
		primerDia = primerDia(año.get(), mes.get()) - 1;
		ultimoDia = ultimoDia(año.get(), mes.get());
		todayStyleClass();	

		mesLabel.setText(getMes(mes.get()));
	}
	
	
	private String getMes(int mes) {
		String nombreMes = "";
		if(mes == 1)
			nombreMes = "enero";
		else if(mes == 2)
			nombreMes = "febrero";
		else if(mes == 3)
			nombreMes = "marzo";
		else if(mes == 4)
			nombreMes = "abril";
		else if(mes == 5)
			nombreMes = "mayo";
		else if(mes == 6)
			nombreMes = "junio";
		else if(mes == 7)
			nombreMes = "julio";
		else if(mes == 8)
			nombreMes = "agosto";
		else if(mes == 9)
			nombreMes = "septiembre";
		else if(mes == 10)
			nombreMes = "octubre";
		else if(mes == 11)
			nombreMes = "noviembre";
		else if(mes == 12)
			nombreMes = "diciembre";
		return nombreMes;		
	}
	
	public void todayStyleClass() {
		for (int i = 0; i < primerDia; i++) {
			diasList.get(i).setText("");
			diasList.get(i).getStyleClass().remove("today");
		}

		for (int i = primerDia, j = 1; i < primerDia + ultimoDia; i++, j++) {
			diasList.get(i).setText("" + j);
			if (LocalDate.of(año.get(), mes.get(), j).equals(LocalDate.now())) {
				diasList.get(i).getStyleClass().add("today");				
			} else {
				diasList.get(i).getStyleClass().remove("today");				
			}
		}

		for (int i = primerDia + ultimoDia; i < diasList.size(); i++) {
			diasList.get(i).setText("");
			diasList.get(i).getStyleClass().remove("today");
		}
	}
	

	private int primerDia(int año, int mes) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(año, mes - 1, 1);
		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		if(diaSemana == 1)
			return 7;
		else
			return diaSemana - 1;
	}

	
	private int ultimoDia(int año, int mes) {
		LocalDate primero = LocalDate.of(año, mes, 1);
		return primero.plusMonths(1).minusDays(1).getDayOfMonth();
	}


	public final IntegerProperty mesProperty() {
		return this.mes;
	}
	public final int getMes() {
		return this.mesProperty().get();
	}
	public final void setMes(final int mes) {
		this.mesProperty().set(mes);
	}

	
	public final IntegerProperty añoProperty() {
		return this.año;
	}
	public final int getAño() {
		return this.añoProperty().get();
	}
	public final void setAño(final int año) {
		this.añoProperty().set(año);
	}

}
