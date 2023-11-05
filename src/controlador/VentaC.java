package controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import dao.CamisetaDAO;
import dao.ClienteDAO;
import dao.TiendaDAO;
import dao.VentaDAO;
import dao.interfaz.IClienteDAO;
import dao.interfaz.IIndumentariaDAO;
import dao.interfaz.ITiendaDAO;
import dao.interfaz.IVentaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Camiseta;
import modelo.Cliente;
import modelo.Indumentaria;
import modelo.Tienda;
import modelo.VentaDetalle;
import modelo.Venta;
import modelo.dto.CamisetaVentaDetalleDTO;
import utils.ListaEnlazada;
import utils.Nodo;

public class VentaC implements Initializable
{
    @FXML
    private JFXButton btn_agregar_indumentaria;
    @FXML
    private JFXButton btn_procesar_venta;
    @FXML
    private JFXButton btn_quitar_indumentaria;
    @FXML
    private JFXButton btn_seleccionar_cliente;
    @FXML
    private JFXButton btn_seleccionar_indumentaria;
    @FXML
    private TextField field_indumentaria_seleccionada;
    @FXML
    private TextField field_cantidad;
    @FXML
    private TextField field_cliente_seleccionado;
    @FXML
    private JFXCheckBox check_comprobante;
    @FXML
    private Label label_tienda_galeria;
    @FXML
    private Label label_tienda_piso;
    @FXML
    private Label label_tienda_stand;
    @FXML
    private Label label_trabajador_apellido;
    @FXML
    private Label label_trabajador_nombre;
    @FXML
    private Pane contenedor_tienda_trabajador;
    @FXML
    private TableView<CamisetaVentaDetalleDTO> tabla_venta_detalle;
    //
    private ObservableList<CamisetaVentaDetalleDTO> lista_registros_tabla=FXCollections.observableArrayList();
    private HashMap<CamisetaVentaDetalleDTO, VentaDetalle> map_venta=new LinkedHashMap<>();
    private IVentaDAO dao=new VentaDAO();
    private ITiendaDAO dao_tienda=new TiendaDAO();
	public static IIndumentariaDAO dao_indumentaria=new CamisetaDAO();
	public static IClienteDAO dao_cliente=new ClienteDAO();
	public static Indumentaria indumentaria_seleccionada;
	public static Cliente cliente_seleccionado;
	//
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		field_indumentaria_seleccionada.setDisable(true);
		field_cliente_seleccionado.setDisable(true);
		btn_seleccionar_cliente.setDisable(true);
		establecerContenido();
		actualizarTabla();
	}
	public void ventanaSeleccionarIndumentaria(ActionEvent evento) throws IOException
	{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/SeleccionarIndumentariaFX.fxml"));
		Parent root=loader.load();
		SeleccionarIndumentariaC controlador=loader.getController();
		controlador.setControlador(this);
		Stage stage=new Stage();
		stage.setScene(new Scene(root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	public void ventanaSeleccionarCliente(ActionEvent evento) throws IOException
	{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/SeleccionarClienteFX.fxml"));
		Parent root=loader.load();
		SeleccionarClienteC controlador=loader.getController();
		controlador.setControlador(this);
		Stage stage=new Stage();
		stage.setScene(new Scene(root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	public void actualizarFieldCamisetaSelecionada()
	{
		field_indumentaria_seleccionada.setText(indumentaria_seleccionada.getNombre());
	}
	public void actualizarFieldClienteSelecionado()
	{
		field_cliente_seleccionado.setText(cliente_seleccionado.getNombre()+" "+cliente_seleccionado.getApellido_paterno()+" "+cliente_seleccionado.getApellido_materno());
	}
	public void agregarVentaDetalle(ActionEvent evento)
	{
		if(!field_indumentaria_seleccionada.getText().isEmpty() && !field_cantidad.getText().isEmpty())
		{
			int id_indumentaria=indumentaria_seleccionada.getId();
			int cantidad=Integer.parseInt(field_cantidad.getText());
			BigDecimal subtotal=indumentaria_seleccionada.getPrecio().multiply(BigDecimal.valueOf(cantidad));
			VentaDetalle venta_detalle=new VentaDetalle(id_indumentaria, cantidad, subtotal);
			String nombre=indumentaria_seleccionada.getNombre();
			String talla=((Camiseta)indumentaria_seleccionada).getTalla();
			CamisetaVentaDetalleDTO dto=new CamisetaVentaDetalleDTO(nombre, talla, cantidad, subtotal);
			map_venta.put(dto, venta_detalle);
			actualizarTabla();
			field_indumentaria_seleccionada.clear();
			field_cantidad.clear();
		}
		else
			JOptionPane.showMessageDialog(null, "Tiene que seleccionar una camiseta e ingresar una cantidad.", "Advertencia", 1);
	}
	public void quitarVentaDetalle(ActionEvent evento)
	{
		CamisetaVentaDetalleDTO dto=tabla_venta_detalle.getSelectionModel().getSelectedItem();
		if(dto!=null)
		{
			map_venta.remove(dto);
			actualizarTabla();
		}
		else
			JOptionPane.showMessageDialog(null, "Tiene que seleccionar una fila de la tabla.", "Advertencia", 1);
	}
	public void conComprobante(ActionEvent evento)
	{
		if(check_comprobante.isSelected())
		{
			btn_seleccionar_cliente.setDisable(false);
		}
		else
		{
			btn_seleccionar_cliente.setDisable(true);
			field_cliente_seleccionado.clear();
			cliente_seleccionado=null;
		}
	}
	public void validarRegistrarVenta(ActionEvent evento)
	{
		if(!map_venta.isEmpty())
		{
			if(check_comprobante.isSelected())
			{
				if(cliente_seleccionado!=null)
				{
					int respuesta=JOptionPane.showConfirmDialog(null, "¿Registrar venta y generar comprobante de pago?", "Pregunta", JOptionPane.YES_NO_OPTION, 3);
					switch(respuesta)
					{
						case JOptionPane.YES_OPTION:
							//Registar venta y generar comprobante de pago...
							break;
						case JOptionPane.NO_OPTION:
						case JOptionPane.CLOSED_OPTION:
							break;
						default:
							break;
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Tiene que seleccionar un cliente para generar el comprobante de pago.", "Advertencia", 2);
			}
			else
			{
				int respuesta=JOptionPane.showConfirmDialog(null, "¿Registrar venta?", "Pregunta", JOptionPane.YES_NO_OPTION, 3);
				switch(respuesta)
				{
					case JOptionPane.YES_OPTION:
						registrarVenta();
						break;
					case JOptionPane.NO_OPTION:
					case JOptionPane.CLOSED_OPTION:
						break;
					default:
						break;
				}
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Tiene que agregar al menos un producto.", "Advertencia", 1);
	}
	public void registrarVenta()
	{
		ListaEnlazada lista_venta_detalle=new ListaEnlazada();
		BigDecimal total=new BigDecimal(0);
		for(CamisetaVentaDetalleDTO dto: map_venta.keySet())
		{
			VentaDetalle venta_detalle=map_venta.get(dto);
			lista_venta_detalle.agregarNodo(new Nodo<VentaDetalle>(venta_detalle));
			total=total.add(venta_detalle.getSubtotal());
		}
		Venta venta=new Venta(LocalDateTime.now(), total, AutenticacionC.trabajador_sesion.getDni());
		dao.registrar(venta, lista_venta_detalle);
		map_venta.clear();
		actualizarTabla();
		field_indumentaria_seleccionada.clear();
		field_cliente_seleccionado.clear();
	}
	public void generarComprobantePago()
	{
		
	}
	//BigDecimal.valueOf(int, double, float) y new BigDecimal(String)
	public void actualizarTabla()
	{
		lista_registros_tabla.clear();
		tabla_venta_detalle.getColumns().clear();
		TableColumn<CamisetaVentaDetalleDTO, String> columna_nombre=new TableColumn<>("Nombre");
		columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		TableColumn<CamisetaVentaDetalleDTO, String> columna_talla=new TableColumn<>("Talla");
		columna_talla.setCellValueFactory(new PropertyValueFactory<>("talla"));
		TableColumn<CamisetaVentaDetalleDTO, String> columna_cantidad=new TableColumn<>("Cantidad");
		columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		TableColumn<CamisetaVentaDetalleDTO, String> columna_subtotal=new TableColumn<>("Subtotal");
		columna_subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
		tabla_venta_detalle.getColumns().add(columna_nombre);
		tabla_venta_detalle.getColumns().add(columna_talla);
		tabla_venta_detalle.getColumns().add(columna_cantidad);
		tabla_venta_detalle.getColumns().add(columna_subtotal);
		for(CamisetaVentaDetalleDTO dto: map_venta.keySet())
		{
			lista_registros_tabla.add(dto);
		}
		tabla_venta_detalle.setItems(lista_registros_tabla);
	}
	public void establecerContenido()
	{
		label_trabajador_nombre.setText(AutenticacionC.trabajador_sesion.getNombre());
		label_trabajador_apellido.setText(AutenticacionC.trabajador_sesion.getApellido_paterno()+" "+AutenticacionC.trabajador_sesion.getApellido_materno());
		Tienda tienda=dao_tienda.obtenerTienda(AutenticacionC.trabajador_sesion.getTienda());
		label_tienda_galeria.setText(tienda.getGaleria());
		label_tienda_piso.setText(tienda.getPiso());
		label_tienda_stand.setText(tienda.getStand());
	}
	
}
