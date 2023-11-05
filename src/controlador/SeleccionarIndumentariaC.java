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
import modelo.Indumentaria;
import utils.ListaEnlazada;
import utils.Nodo;

public class SeleccionarIndumentariaC implements Initializable
{
	@FXML
    private JFXComboBox<String> cbx_buscar;
    @FXML
    private TextField field_buscar;
    @FXML
    private TableView<Indumentaria> tabla_seleccionar_indumentaria;
    //
    private VentaC controlador_venta=new VentaC();
    private ObservableList<Indumentaria> lista_registros_tabla=FXCollections.observableArrayList();
    //
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		actualizarTabla(VentaC.dao_indumentaria.obtenerLista());
	}
	//
	public void actualizarTabla(ListaEnlazada lista)
	{
		lista_registros_tabla.clear();
		tabla_seleccionar_indumentaria.getColumns().clear();
		TableColumn<Indumentaria, String> columna_id = new TableColumn<>("ID");
		columna_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Indumentaria, String> columna_nombre = new TableColumn<>("Nombre");
		columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		TableColumn<Indumentaria, String> columna_precio = new TableColumn<>("Precio");
		columna_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		TableColumn<Indumentaria, String> columna_stock = new TableColumn<>("Stock");
		columna_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		TableColumn<Indumentaria, String> columna_club = new TableColumn<>("Club");
		columna_club.setCellValueFactory(new PropertyValueFactory<>("club"));
		TableColumn<Indumentaria, String> columna_talla = new TableColumn<>("Talla");
		columna_talla.setCellValueFactory(new PropertyValueFactory<>("talla"));
		tabla_seleccionar_indumentaria.getColumns().add(columna_id);
		tabla_seleccionar_indumentaria.getColumns().add(columna_nombre);
		tabla_seleccionar_indumentaria.getColumns().add(columna_precio);
		tabla_seleccionar_indumentaria.getColumns().add(columna_stock);
		tabla_seleccionar_indumentaria.getColumns().add(columna_club);
		tabla_seleccionar_indumentaria.getColumns().add(columna_talla);
		Nodo<Camiseta> indumentarias=lista.getCabecera();
		while(indumentarias!=null)
		{
			Camiseta camiseta=indumentarias.getElemento();
			lista_registros_tabla.add(camiseta);
			indumentarias=indumentarias.getSiguiente();
		}
		tabla_seleccionar_indumentaria.setItems(lista_registros_tabla);
	}
	public void seleccionarIndumentaria(MouseEvent evento) throws IOException
	{
		VentaC.indumentaria_seleccionada=tabla_seleccionar_indumentaria.getSelectionModel().getSelectedItem();
		if(VentaC.indumentaria_seleccionada!=null)
		{
			controlador_venta.actualizarFieldCamisetaSelecionada();
			tabla_seleccionar_indumentaria.getScene().getWindow().hide();
		}
	}
	public void setControlador(VentaC controlador)
	{
		controlador_venta=controlador;
	}
}
