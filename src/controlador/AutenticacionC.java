package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Trabajador;
import modelo.Usuario;

public class AutenticacionC implements Initializable
{
	@FXML private TextField field_usuario;
	@FXML private PasswordField field_contrasenna;
	//
	public static Trabajador trabajador_sesion=new Trabajador("28713160", "Pequeño", "Saltamontes", "Azul", new Usuario("salta", "123", "vendedor"), 1);
	//
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
	public void iniciarSesion(ActionEvent evento) throws IOException
	{
		if(field_usuario.getText().equals("admin") && field_contrasenna.getText().equals("123"))
		{
			Stage stage = new Stage();
			Parent fxml=FXMLLoader.load(getClass().getResource("/vista/PrincipalFX.fxml"));
			Scene scene = new Scene(fxml);
			stage.setScene(scene);
			stage.show();
			Stage stage_actual=(Stage)field_usuario.getScene().getWindow();
			stage_actual.close();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "El usuario y/o contraseña es incorrecto", "Error", 0);
		}
	}
}
