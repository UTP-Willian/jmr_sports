package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import dao.CamisetaDAO;
import dao.TiendaDAO;
import dao.interfaz.IIndumentariaDAO;
import dao.interfaz.ITiendaDAO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import modelo.Camiseta;
import modelo.Indumentaria;
import modelo.Tienda;
import utils.ListaEnlazada;
import utils.Nodo;

public class ResumenC implements Initializable
{
	@FXML
    private JFXComboBox<String> cbox_tienda;
    @FXML
    private DatePicker dp_fecha;
    @FXML
    private Label label_ingresos;
    @FXML
    private Label label_ventas;
    @FXML
    private PieChart pc_venta;
    @FXML
    private TableView<Indumentaria> tabla_bajo_stock;
    @FXML
    private Pane pane3;
    //
    private IIndumentariaDAO dao=new CamisetaDAO();
    private ITiendaDAO dao_tienda=new TiendaDAO();
    private ObservableList<Indumentaria> lista_registros_tabla=FXCollections.observableArrayList();
    private ObservableList<String> cbox_opciones=FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pc_datos=FXCollections.observableArrayList();
    //
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		actualizarTabla(dao.bajoStock());
		agregarItemsComboBox();
		actualizarGraficoCircular();
	}
	public void actualizarTabla(ListaEnlazada lista)
	{
		lista_registros_tabla.clear();
		tabla_bajo_stock.getColumns().clear();
		TableColumn<Indumentaria, String> columna_id = new TableColumn<>("ID");
		columna_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Indumentaria, String> columna_nombre = new TableColumn<>("Nombre");
		columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		TableColumn<Indumentaria, String> columna_stock = new TableColumn<>("Stock");
		columna_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		tabla_bajo_stock.getColumns().add(columna_id);
		tabla_bajo_stock.getColumns().add(columna_nombre);
		tabla_bajo_stock.getColumns().add(columna_stock);
		tabla_bajo_stock.getColumns().forEach(column -> column.setReorderable(false));
		tabla_bajo_stock.setMouseTransparent(true);
		tabla_bajo_stock.setFocusTraversable(false);
		Nodo<Camiseta> indumentarias=lista.getCabecera();
		while(indumentarias!=null)
		{
			Camiseta camiseta=indumentarias.getElemento();
			lista_registros_tabla.add(camiseta);
			indumentarias=indumentarias.getSiguiente();
		}
		tabla_bajo_stock.setItems(lista_registros_tabla);
	}
	public void agregarItemsComboBox()
	{
		Nodo<Tienda> tiendas=dao_tienda.obtenerLista().getCabecera();
		while(tiendas!=null)
		{
			Tienda tienda=tiendas.getElemento();
			String item=tienda.getId()+", "+tienda.getGaleria()+", "+tienda.getPiso()+", "+tienda.getStand();
			cbox_opciones.add(item);
			tiendas=tiendas.getSiguiente();
		}
		cbox_tienda.setItems(cbox_opciones);
	}
	public void actualizarGraficoCircular()
	{
		ObservableList<PieChart.Data> pc_datos = FXCollections.observableArrayList();
	    pc_datos.add(new PieChart.Data("Camiseta 1", 54));
	    pc_datos.add(new PieChart.Data("Camiseta 2", 87));
	    pc_datos.add(new PieChart.Data("Camiseta 8", 155));
	    pc_datos.add(new PieChart.Data("Camiseta 5", 132));
	    pc_datos.forEach(data->data.nameProperty().bind(Bindings.concat(data.getName(), ": ", data.pieValueProperty())));
	    pc_venta.setData(pc_datos);
	    pc_venta.setLabelsVisible(false);
	}
	
	
}