package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.jfoenix.controls.JFXComboBox;
import dao.ClienteDAO;
import dao.interfaz.IClienteDAO;
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
import modelo.Cliente;
import utils.ListaEnlazada;
import utils.Nodo;

public class ClientesC implements Initializable
{
	@FXML
	private TableView<Cliente> tabla_cliente;
    @FXML
    private TextField field_buscar;
    @FXML
    private JFXComboBox<String> cbox_buscar;
	//
	private ObservableList<Cliente> lista_registros_tabla=FXCollections.observableArrayList();
	private ObservableList<String> cbox_opciones=FXCollections.observableArrayList("Documento", "Nombre", "Apellido paterno", "Apellido materno", "Teléfono", "Dirección");
	public static IClienteDAO dao=new ClienteDAO();
	public static boolean proceso_agregar=false;
	public static boolean proceso_modificar=false;
	public static Cliente cliente_seleccionado;
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		cbox_buscar.setItems(cbox_opciones);
		cbox_buscar.setValue("Documento");
		actualizarTabla(dao.obtenerLista());
	}
	public void ventanaCliente() throws IOException
	{
		Stage stage = new Stage();
		Parent fxml=FXMLLoader.load(getClass().getResource("/vista/AgregarClienteFX.fxml"));
		Scene scene = new Scene(fxml);
		stage.setScene(scene);
		//stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	public void agregarCliente(ActionEvent evento) throws IOException
	{
		proceso_agregar=true;
		ventanaCliente();
	}
	public void modificarCliente(ActionEvent evento) throws IOException
	{
		cliente_seleccionado=tabla_cliente.getSelectionModel().getSelectedItem();
		if(cliente_seleccionado!=null)
		{
			proceso_modificar=true;
			ventanaCliente();
		}
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla.", "Advertencia", 1);
	}
	public void buscarCliente(ActionEvent evento) throws IOException
	{
		
	}
	public void eliminarCliente(ActionEvent evento) throws IOException
	{
		
	}
	public void actualizarTabla(ListaEnlazada lista)
	{
		lista_registros_tabla.clear();
		tabla_cliente.getColumns().clear();
		TableColumn<Cliente, String> columna_documento = new TableColumn<>("Documento");
		columna_documento.setCellValueFactory(new PropertyValueFactory<>("documento"));
		TableColumn<Cliente, String> columna_nombre= new TableColumn<>("Nombre");
		columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		TableColumn<Cliente, String> columna_ap = new TableColumn<>("Apellido paterno");
		columna_ap.setCellValueFactory(new PropertyValueFactory<>("apellido_paterno"));
		TableColumn<Cliente, String> columna_am = new TableColumn<>("Apellido materno");
		columna_am.setCellValueFactory(new PropertyValueFactory<>("apellido_materno"));
		TableColumn<Cliente, String> columna_telefono = new TableColumn<>("Teléfono");
		columna_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		TableColumn<Cliente, String> columna_direccion = new TableColumn<>("Dirección");
		columna_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		tabla_cliente.getColumns().add(columna_documento);
		tabla_cliente.getColumns().add(columna_nombre);
		tabla_cliente.getColumns().add(columna_ap);
		tabla_cliente.getColumns().add(columna_am);
		tabla_cliente.getColumns().add(columna_telefono);
		tabla_cliente.getColumns().add(columna_direccion);
		Nodo<Cliente> clientes=lista.getCabecera();
		while(clientes!=null)
		{
			Cliente cliente=clientes.getElemento();
			lista_registros_tabla.add(cliente);
			clientes=clientes.getSiguiente();
		}
		tabla_cliente.setItems(lista_registros_tabla);
	}
}