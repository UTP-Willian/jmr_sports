package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class PrincipalC implements Initializable
{
	@FXML
	private StackPane sPane;
	//
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			Parent fxml=FXMLLoader.load(getClass().getResource("/vista/ResumenFX.fxml"));
			sPane.getChildren().removeAll();
			sPane.getChildren().setAll(fxml);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void resumen(ActionEvent ev) throws IOException
	{
		Parent fxml=FXMLLoader.load(getClass().getResource("/vista/ResumenFX.fxml"));
		sPane.getChildren().removeAll();
		sPane.getChildren().setAll(fxml);
	}
	public void stock(ActionEvent ev) throws IOException
	{
		/*
		FXMLLoader fxml=new FXMLLoader(getClass().getResource("/vista/StockFX.fxml"));
		Parent pfxml=fxml.load();
		sPane.getChildren().removeAll();
		sPane.getChildren().setAll(pfxml);
		StockC stockc=fxml.getController();
		stockc.actualizarTabla();
		*/
		Parent fxml=FXMLLoader.load(getClass().getResource("/vista/StockFX.fxml"));
		sPane.getChildren().removeAll();
		sPane.getChildren().setAll(fxml);
	}
	public void venta(ActionEvent ev) throws IOException
	{
		Parent fxml=FXMLLoader.load(getClass().getResource("/vista/VentaFX.fxml"));
		sPane.getChildren().removeAll();
		sPane.getChildren().setAll(fxml);
	}
	public void clientes(ActionEvent evento) throws IOException
	{
		Parent fxml=FXMLLoader.load(getClass().getResource("/vista/ClientesFX.fxml"));
		sPane.getChildren().removeAll();
		sPane.getChildren().setAll(fxml);
	}
	public void trabajadores(ActionEvent ev) throws IOException
	{
		Parent fxml=FXMLLoader.load(getClass().getResource("/vista/TrabajadoresFX.fxml"));
		sPane.getChildren().removeAll();
		sPane.getChildren().setAll(fxml);
	}
}
