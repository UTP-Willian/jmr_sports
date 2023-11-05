package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.jfoenix.controls.JFXComboBox;
import dao.TrabajadorDAO;
import dao.interfaz.ITrabajadorDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Trabajador;
import utils.ListaEnlazada;
import utils.Nodo;

public class TrabajadoresC implements Initializable
{
	@FXML
    private TableView<Trabajador> tabla_trabajador;
    @FXML
    private JFXComboBox<String> cbx_buscar;
    @FXML
    private TextField field_buscar;
	//
	private ObservableList<Trabajador> lista_registros_tabla=FXCollections.observableArrayList();
	private ObservableList<String> cbx_opciones=FXCollections.observableArrayList("DNI", "Nombre");
	public static ITrabajadorDAO dao=new TrabajadorDAO();
	public static boolean proceso_agregar=false;
	public static boolean proceso_modificar=false;
	public static Trabajador trabajador_seleccionado;
	//
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		cbx_buscar.setItems(cbx_opciones);
		cbx_buscar.setValue("DNI");
		actualizarTabla(dao.obtenerLista());
	}
	public void ventanaTrabajador() throws IOException
	{
		Stage stage = new Stage();
		Parent fxml=FXMLLoader.load(getClass().getResource("/vista/AgregarTrabajadorFX.fxml"));
		Scene scene = new Scene(fxml);
		stage.setScene(scene);
		//stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	public void agregarTrabajador(ActionEvent evento) throws IOException
	{
		proceso_agregar=true;
		ventanaTrabajador();
	}
	public void buscarTrabajador(ActionEvent evento) throws IOException
	{
		String campo=cbx_buscar.getValue();
		String valor=field_buscar.getText();
		if(campo!=null && !valor.isEmpty())
		{
			ListaEnlazada lista_buscar=dao.buscar(campo, valor);
			actualizarTabla(lista_buscar);
		}
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar un campo e ingresar un valor.", "Advertencia", 1);
	}
	public void modificarTrabajador(ActionEvent evento) throws IOException
	{
		trabajador_seleccionado=tabla_trabajador.getSelectionModel().getSelectedItem();
		if(trabajador_seleccionado!=null)
		{
			proceso_modificar=true;
			ventanaTrabajador();
		}
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla.", "Advertencia", 1);
	}
	public void eliminarTrabajador(ActionEvent evento) throws IOException
	{
		trabajador_seleccionado=tabla_trabajador.getSelectionModel().getSelectedItem();
		if(trabajador_seleccionado!=null)
			dao.eliminar(trabajador_seleccionado);
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla.", "Advertencia", 1);
	}
	//
	public void actualizarTabla(ListaEnlazada lista)
	{
		lista_registros_tabla.clear();
		tabla_trabajador.getColumns().clear();
		TableColumn<Trabajador, String> columna_dni = new TableColumn<>("DNI");
		columna_dni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		TableColumn<Trabajador, String> columna_nombre = new TableColumn<>("Nombre");
		columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		TableColumn<Trabajador, String> columna_ap = new TableColumn<>("Apellido paterno");
		columna_ap.setCellValueFactory(new PropertyValueFactory<>("apellido_paterno"));
		TableColumn<Trabajador, String> columna_am = new TableColumn<>("Apellido materno");
		columna_am.setCellValueFactory(new PropertyValueFactory<>("apellido_materno"));
		TableColumn<Trabajador, String> columna_tienda=new TableColumn<>("Tienda");
		columna_tienda.setCellValueFactory(new PropertyValueFactory<>("tienda"));
		TableColumn<Trabajador, String> columna_usuario = new TableColumn<>("Usuario");
		columna_usuario.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getUsuario().getUsuario()));
		TableColumn<Trabajador, String> columna_contrasenna=new TableColumn<>("Contraseña");
		columna_contrasenna.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getUsuario().getContrasenna()));
		TableColumn<Trabajador, String> columna_cargo=new TableColumn<>("Cargo");
		columna_cargo.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getUsuario().getCargo()));
		tabla_trabajador.getColumns().add(columna_dni);
		tabla_trabajador.getColumns().add(columna_nombre);
		tabla_trabajador.getColumns().add(columna_ap);
		tabla_trabajador.getColumns().add(columna_am);
		tabla_trabajador.getColumns().add(columna_tienda);
		tabla_trabajador.getColumns().add(columna_usuario);
		tabla_trabajador.getColumns().add(columna_contrasenna);
		tabla_trabajador.getColumns().add(columna_cargo);
		Nodo<Trabajador> trabajadores=lista.getCabecera();
		while(trabajadores!=null)
		{
			Trabajador trabajador=trabajadores.getElemento();
			lista_registros_tabla.add(trabajador);
			trabajadores=trabajadores.getSiguiente();
		}
		tabla_trabajador.setItems(lista_registros_tabla);
	}
}