package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Camiseta;
import modelo.Cliente;
import modelo.Indumentaria;
import utils.ListaEnlazada;
import utils.Nodo;

public class SeleccionarClienteC implements Initializable
{
	@FXML
    private JFXComboBox<String> cbx_buscar;
    @FXML
    private TextField field_buscar;
    @FXML
    private TableView<Cliente> tabla_seleccionar_cliente;
    //
    private VentaC controlador_venta=new VentaC();
    private ObservableList<Cliente> lista_registros_tabla=FXCollections.observableArrayList();
    //
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		actualizarTabla(VentaC.dao_cliente.obtenerLista());
	}
	//
	public void actualizarTabla(ListaEnlazada lista)
	{
		lista_registros_tabla.clear();
		tabla_seleccionar_cliente.getColumns().clear();
		TableColumn<Cliente, String> columna_documento=new TableColumn<>("Documento");
		columna_documento.setCellValueFactory(new PropertyValueFactory<>("documento"));
		TableColumn<Cliente, String> columna_nombre=new TableColumn<>("Nombre");
		columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		TableColumn<Cliente, String> columna_apellido_paterno=new TableColumn<>("Apellido paterno");
		columna_apellido_paterno.setCellValueFactory(new PropertyValueFactory<>("apellido_paterno"));
		TableColumn<Cliente, String> columna_apellido_materno=new TableColumn<>("Apellido materno");
		columna_apellido_materno.setCellValueFactory(new PropertyValueFactory<>("apellido_materno"));
		TableColumn<Cliente, String> columna_telefono=new TableColumn<>("Teléfono");
		columna_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		TableColumn<Cliente, String> columna_direccion=new TableColumn<>("Dirección");
		columna_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		tabla_seleccionar_cliente.getColumns().add(columna_documento);
		tabla_seleccionar_cliente.getColumns().add(columna_nombre);
		tabla_seleccionar_cliente.getColumns().add(columna_apellido_paterno);
		tabla_seleccionar_cliente.getColumns().add(columna_apellido_materno);
		tabla_seleccionar_cliente.getColumns().add(columna_telefono);
		tabla_seleccionar_cliente.getColumns().add(columna_direccion);
		Nodo<Cliente> clientes=lista.getCabecera();
		while(clientes!=null)
		{
			Cliente cliente=clientes.getElemento();
			lista_registros_tabla.add(cliente);
			clientes=clientes.getSiguiente();
		}
		tabla_seleccionar_cliente.setItems(lista_registros_tabla);
	}
	public void seleccionarCliente(MouseEvent evento) throws IOException
	{
		VentaC.cliente_seleccionado=tabla_seleccionar_cliente.getSelectionModel().getSelectedItem();
		if(VentaC.cliente_seleccionado!=null)
		{
			controlador_venta.actualizarFieldClienteSelecionado();
			tabla_seleccionar_cliente.getScene().getWindow().hide();
		}
	}
	public void setControlador(VentaC controlador)
	{
		controlador_venta=controlador;
	}
}
