package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BD
{
	private static final String usuario="postgres";
    private static final String contrasenna="123";
    private static final String url="jdbc:postgresql://localhost:5432/jmr_sports";
	
	public static Connection obtenerConexion()
	{
		try
        {
            Connection conexion=DriverManager.getConnection(url, usuario, contrasenna);
            return conexion;
        }
        catch(SQLException excepcion)
        {
            JOptionPane.showMessageDialog(null, "No se pudo obtener la conexión a la base de datos:\n"+excepcion.getMessage(), "Error", 0);
        }
        return null;
	}
}
