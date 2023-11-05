package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import dao.TiendaDAO;
import dao.interfaz.ITiendaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Camiseta;
import modelo.Indumentaria;
import modelo.Tienda;
import modelo.Trabajador;
import modelo.Usuario;
import utils.Nodo;

public class AgregarTrabajadorC implements Initializable
{
	@FXML
    private JFXButton btn_agregar_modificar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private JFXComboBox<String> cbx_cargo;
    @FXML
    private JFXComboBox<String> cbx_tienda;
    @FXML
    private TextField field_apellido_materno;
    @FXML
    private TextField field_apellido_paterno;
    @FXML
    private TextField field_contrasenna;
    @FXML
    private TextField field_dni;
    @FXML
    private TextField field_nombre;
    @FXML
    private TextField field_usuario;
    @FXML
    private Label label_agregar_modificar;
    //
    private ObservableList<String> cbx_cargo_opciones=FXCollections.observableArrayList("Vendedor", "Inventario");
    private ObservableList<String> cbx_tienda_opciones=FXCollections.observableArrayList();
    private ITiendaDAO dao=new TiendaDAO();
    //
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		establecerContenido();
	}
	public void agregarModificar(ActionEvent evento)
	{
		String dni=field_dni.getText();
		String nombre=field_nombre.getText();
		String apellido_paterno=field_apellido_paterno.getText();
		String apellido_materno=field_apellido_materno.getText();
		String user=field_usuario.getText();
		String contrasenna=field_contrasenna.getText();
		String cargo=cbx_cargo.getValue();
		int tienda=Integer.parseInt(cbx_tienda.getValue());
		Usuario usuario=new Usuario(user, contrasenna, cargo);
		Trabajador trabajador;
		if(TrabajadoresC.proceso_agregar)
		{
			trabajador=new Trabajador(dni, nombre, apellido_paterno, apellido_materno, usuario, tienda);
			TrabajadoresC.dao.agregar(trabajador);
			TrabajadoresC.proceso_agregar=false;
		}
		else if(TrabajadoresC.proceso_modificar)
		{
			trabajador=new Trabajador(dni, nombre, apellido_paterno, apellido_materno, usuario, tienda);
			TrabajadoresC.dao.modificar(trabajador);
			TrabajadoresC.proceso_modificar=false;
		}
		cerrarVentanaAgregarModificar();
	}
	public void establecerContenido()
	{
		if(TrabajadoresC.proceso_agregar)
		{
			label_agregar_modificar.setText("Agregar trabajador");
			btn_agregar_modificar.setText("Agregar");
			cbx_cargo.setItems(cbx_cargo_opciones);
			cargarCbxTiendas();
		}
		else if(TrabajadoresC.proceso_modificar)
		{
			label_agregar_modificar.setText("Modificar trabajador");
			btn_agregar_modificar.setText("Modificar");
			field_dni.setText(TrabajadoresC.trabajador_seleccionado.getDni());
			field_dni.setEditable(false);
			field_nombre.setText(TrabajadoresC.trabajador_seleccionado.getNombre());
			field_apellido_paterno.setText(TrabajadoresC.trabajador_seleccionado.getApellido_paterno());
			field_apellido_materno.setText(TrabajadoresC.trabajador_seleccionado.getApellido_materno());
			field_usuario.setText(TrabajadoresC.trabajador_seleccionado.getUsuario().getUsuario());
			field_contrasenna.setText(TrabajadoresC.trabajador_seleccionado.getUsuario().getContrasenna());
			cbx_cargo.setItems(cbx_cargo_opciones);
			cbx_cargo.setValue(TrabajadoresC.trabajador_seleccionado.getUsuario().getCargo());
			cargarCbxTiendas();
			cbx_tienda.setValue(String.valueOf(TrabajadoresC.trabajador_seleccionado.getTienda()));
		}
	}
	public void cargarCbxTiendas()
	{
		Nodo<Tienda> tiendas=dao.obtenerLista().getCabecera();
		while(tiendas!=null)
		{
			cbx_tienda_opciones.add(String.valueOf(tiendas.getElemento().getId()));
			tiendas=tiendas.getSiguiente();
		}
		cbx_tienda.setItems(cbx_tienda_opciones);
	}
	public void cerrarVentanaAgregarModificar()
	{
		Stage stage=(Stage)btn_agregar_modificar.getScene().getWindow();
	    stage.close();
	}
}
