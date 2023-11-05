package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dao.CamisetaDAO;
import dao.interfaz.IIndumentariaDAO;
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
import modelo.Camiseta;
import modelo.Indumentaria;
import utils.Nodo;
import utils.ListaEnlazada;

public class StockC implements Initializable
{
	@FXML
	private TableView<Indumentaria> tabla_indumentaria;
    @FXML
    private JFXButton btn_agregar;
    @FXML
    private JFXButton btn_eliminar;
    @FXML
    private JFXButton btn_modificar;
    @FXML
    private JFXComboBox<String> cbox_buscar;
    @FXML
    private TextField field_buscar;
	//
	private ObservableList<Indumentaria> lista_registros_tabla=FXCollections.observableArrayList();
	private ObservableList<String> cbox_opciones=FXCollections.observableArrayList("Nombre", "Club", "Talla");
	public static IIndumentariaDAO dao=new CamisetaDAO();
	public static boolean proceso_agregar=false;
	public static boolean proceso_modificar=false;
	public static Indumentaria indumentaria_seleccionada;
	//
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		cbox_buscar.setItems(cbox_opciones);
		cbox_buscar.setValue("Nombre");
		actualizarTabla(StockC.dao.obtenerLista());
	}
	public void agregarIndumentaria(ActionEvent evento) throws IOException
	{
		proceso_agregar=true;
		ventanaIndumentaria();
	}
	public void buscarIndumentaria(ActionEvent evento) throws IOException
	{
		String campo=cbox_buscar.getValue();
		String valor=field_buscar.getText();
		if(campo!=null && !valor.isEmpty())
		{
			ListaEnlazada lista_buscar=StockC.dao.buscar(campo, valor);
			actualizarTabla(lista_buscar);
		}
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar un campo e ingresar un valor.", "Advertencia", 1);
	}
	public void modificarIndumentaria(ActionEvent evento) throws IOException
	{
		indumentaria_seleccionada=tabla_indumentaria.getSelectionModel().getSelectedItem();
		if(indumentaria_seleccionada!=null)
		{
			proceso_modificar=true;
			ventanaIndumentaria();
		}
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla.", "Advertencia", 1);
	}
	public void eliminarIndumentaria(ActionEvent evento) throws IOException
	{
		indumentaria_seleccionada=tabla_indumentaria.getSelectionModel().getSelectedItem();
		if(indumentaria_seleccionada!=null)
			dao.eliminar(indumentaria_seleccionada);
		else
			JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla.", "Advertencia", 1);
	}
	//
	public void ventanaIndumentaria() throws IOException
	{
		Stage stage = new Stage();
		Parent fxml=FXMLLoader.load(getClass().getResource("/vista/AgregarIndumentariaFX.fxml"));
		Scene scene = new Scene(fxml);
		stage.setScene(scene);
		stage.show();
	}
	public void actualizarTabla(ListaEnlazada lista)
	{
		lista_registros_tabla.clear();
		tabla_indumentaria.getColumns().clear();
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
		tabla_indumentaria.getColumns().add(columna_id);
		tabla_indumentaria.getColumns().add(columna_nombre);
		tabla_indumentaria.getColumns().add(columna_precio);
		tabla_indumentaria.getColumns().add(columna_stock);
		tabla_indumentaria.getColumns().add(columna_club);
		tabla_indumentaria.getColumns().add(columna_talla);
		Nodo<Camiseta> indumentarias=lista.getCabecera();
		while(indumentarias!=null)
		{
			Camiseta camiseta=indumentarias.getElemento();
			lista_registros_tabla.add(camiseta);
			indumentarias=indumentarias.getSiguiente();
		}
		tabla_indumentaria.setItems(lista_registros_tabla);
	}
}