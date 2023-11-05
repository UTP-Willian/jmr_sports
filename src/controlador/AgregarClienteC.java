package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cliente;
import servicios.ConsultaDocumento;

public class AgregarClienteC implements Initializable
{
	@FXML
    private Label label_agregar_modificar;
    @FXML
    private JFXButton btn_consultar_documento;
	@FXML
    private TextField field_documento;
    @FXML
    private TextField field_nombre;
    @FXML
    private TextField field_apellido_paterno;
	@FXML
    private TextField field_apellido_materno;
    @FXML
    private TextField field_telefono;
    @FXML
    private TextField field_direccion;
    @FXML
    private JFXButton btn_agregar_modificar;
    //
    private ConsultaDocumento servicio_consulta=new ConsultaDocumento();
    //
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		establecerContenido();
	}
	public void agregarModificar(ActionEvent evento)
	{
		String documento=field_documento.getText();
		String nombre=field_nombre.getText();
		String apellido_paterno=field_apellido_paterno.getText();
		String apellido_materno=field_apellido_materno.getText();
		String telefono=field_telefono.getText();
		String direccion=field_direccion.getText();
		Cliente cliente;
		if(ClientesC.proceso_agregar)
		{
			cliente=new Cliente(documento, nombre, apellido_paterno, apellido_materno, telefono, direccion);
			ClientesC.dao.agregar(cliente);
			ClientesC.proceso_agregar=false;
		}
		else if(ClientesC.proceso_modificar)
		{
			Cliente cliente2=new Cliente(ClientesC.cliente_seleccionado.getDocumento(), nombre, apellido_paterno, apellido_materno, telefono, direccion);
			ClientesC.dao.modificar(cliente2);
			ClientesC.proceso_modificar=false;
		}
		cerrarVentanaAgregarModificar();
	}
	public void establecerContenido()
	{
		if(ClientesC.proceso_agregar)
		{
			label_agregar_modificar.setText("Agregar cliente");
			btn_agregar_modificar.setText("Agregar");
		}
		else if(ClientesC.proceso_modificar)
		{
			
			label_agregar_modificar.setText("Modificar cliente");
			btn_agregar_modificar.setText("Modificar");
			field_documento.setText(ClientesC.cliente_seleccionado.getDocumento());
			field_nombre.setText(ClientesC.cliente_seleccionado.getNombre());
			field_apellido_paterno.setText(ClientesC.cliente_seleccionado.getApellido_paterno());
			field_apellido_materno.setText(ClientesC.cliente_seleccionado.getApellido_materno());
			field_telefono.setText(ClientesC.cliente_seleccionado.getTelefono());
			field_direccion.setText(ClientesC.cliente_seleccionado.getDireccion());
		}
	}
	public void cerrarVentanaAgregarModificar()
	{
		Stage stage=(Stage)btn_agregar_modificar.getScene().getWindow();
	    stage.close();
	}
	public void consultarDocumento()
	{
		if(field_documento.getLength()==8)
		{
			Cliente cliente=servicio_consulta.dni(field_documento.getText());
			if(cliente!=null)
			{
				field_nombre.setText(cliente.getNombre());
				field_apellido_paterno.setText(cliente.getApellido_paterno());
				field_apellido_materno.setText(cliente.getApellido_materno());
			}
		}
		else if(field_documento.getLength()==11)
		{
			Cliente cliente=servicio_consulta.ruc(field_documento.getText());
			if(cliente!=null)
			{
				field_nombre.setText(cliente.getNombre());
				field_direccion.setText(cliente.getDireccion());
			}
		}
		else
			JOptionPane.showMessageDialog(null, "El número de documento debe tener 8 u 11 dígitos.", "Advertencia", 1);
	}
}
