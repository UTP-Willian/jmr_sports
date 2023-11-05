package controlador;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import dao.CamisetaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Camiseta;
import modelo.Indumentaria;

public class AgregarIndumentariaC implements Initializable
{
	@FXML
    private Label label_agregar_modificar;
	@FXML
    private TextField field_nombre;
    @FXML
    private TextField field_precio;
    @FXML
    private TextField field_stock;
	@FXML
    private TextField field_club;
    @FXML
    private TextField field_talla;
    @FXML
    private JFXButton btn_agregar_modificar;
    //
    
    //
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		establecerContenido();
	}
	public void agregarModificar(ActionEvent evento)
	{
		String nombre=field_nombre.getText();
		BigDecimal precio=new BigDecimal(field_precio.getText());
		int stock=Integer.parseInt(field_stock.getText());
		String club=field_club.getText();
		String talla=field_talla.getText();
		Indumentaria camiseta;
		if(StockC.proceso_agregar)
		{
			camiseta=new Camiseta(nombre, precio, stock, club, talla);
			StockC.dao.agregar(camiseta);
			StockC.proceso_agregar=false;
		}
		else if(StockC.proceso_modificar)
		{
			Indumentaria camiseta2=new Camiseta(StockC.indumentaria_seleccionada.getId(), nombre, precio, stock, club, talla);
			StockC.dao.modificar(camiseta2);
			StockC.proceso_modificar=false;
		}
		cerrarVentanaAgregarModificar();
	}
	public void establecerContenido()
	{
		if(StockC.proceso_agregar)
		{
			label_agregar_modificar.setText("Agregar camiseta");
			btn_agregar_modificar.setText("Agregar");
		}
		else if(StockC.proceso_modificar)
		{
			
			label_agregar_modificar.setText("Modificar camiseta");
			btn_agregar_modificar.setText("Modificar");
			field_nombre.setText(StockC.indumentaria_seleccionada.getNombre());
			field_precio.setText(StockC.indumentaria_seleccionada.getPrecio().toString());
			field_stock.setText(Integer.toString(StockC.indumentaria_seleccionada.getStock()));
			field_club.setText(((Camiseta)StockC.indumentaria_seleccionada).getClub());
			field_talla.setText(((Camiseta)StockC.indumentaria_seleccionada).getTalla());
		}
	}
	public void cerrarVentanaAgregarModificar()
	{
		Stage stage=(Stage)btn_agregar_modificar.getScene().getWindow();
	    stage.close();
	}
}
