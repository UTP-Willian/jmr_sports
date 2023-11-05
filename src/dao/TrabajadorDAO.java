package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.interfaz.ITrabajadorDAO;
import modelo.Camiseta;
import modelo.Trabajador;
import modelo.Usuario;
import utils.BD;
import utils.ListaEnlazada;
import utils.Nodo;

public class TrabajadorDAO implements ITrabajadorDAO
{
	@Override
	public void agregar(Trabajador trabajador)
	{
		String sql="insert into trabajador values(?, ?, ?, ?, ?, ?, ?, lower(?))";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, trabajador.getDni());
			pstm.setString(2, trabajador.getNombre());
			pstm.setString(3, trabajador.getApellido_paterno());
			pstm.setString(4, trabajador.getApellido_materno());
			pstm.setString(5, trabajador.getUsuario().getUsuario());
			pstm.setString(6, trabajador.getUsuario().getContrasenna());
			pstm.setInt(7, trabajador.getTienda());
			pstm.setString(8, trabajador.getUsuario().getCargo());
            pstm.executeUpdate();
        }
        catch(SQLException excepcion)
        {
        	excepcion.printStackTrace();
        }
	}
	@Override
	public ListaEnlazada buscar(String campo, String valor)
	{
		ListaEnlazada lista=new ListaEnlazada();
        String sql="select * from trabajador where lower("+campo+") like lower(?)";
        try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, "%"+valor+"%");
            ResultSet rs=pstm.executeQuery();
            while(rs.next())
            {
            	String dni=rs.getString("dni");
            	String nombre=rs.getString("nombre");
            	String apellido_paterno=rs.getString("apellido_paterno");
            	String apellido_materno=rs.getString("apellido_materno");
            	String user=rs.getString("usuario");
            	String contrasenna=rs.getString("contrasenna");
            	int tienda=rs.getInt("id_tienda");
            	String cargo=rs.getString("cargo");
            	Usuario usuario=new Usuario(user, contrasenna, cargo);
            	Trabajador trabajador=new Trabajador(dni, nombre, apellido_paterno, apellido_materno, usuario, tienda);
            	lista.agregarNodo(new Nodo<Trabajador>(trabajador));
            }
        }
        catch(SQLException excepcion)
        {
        	excepcion.printStackTrace();
        }
        return lista;
	}
	@Override
	public void eliminar(Trabajador trabajador)
	{
		String sql="update trabajador set estado='inactivo' where dni=?";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, trabajador.getDni());
			pstm.executeUpdate();
        }
        catch(SQLException excepcion)
        {
        	excepcion.printStackTrace();
        }
	}
	@Override
	public ListaEnlazada obtenerLista()
	{
		ListaEnlazada lista=new ListaEnlazada();
        String sql="select * from trabajador where estado='activo'";
        try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
            ResultSet rs=pstm.executeQuery();
            while(rs.next())
            {
            	String dni=rs.getString("dni");
            	String nombre=rs.getString("nombre");
            	String apellido_paterno=rs.getString("apellido_paterno");
            	String apellido_materno=rs.getString("apellido_materno");
            	String user=rs.getString("usuario");
            	String contrasenna=rs.getString("contrasenna");
            	String cargo=rs.getString("cargo");
            	Usuario usuario=new Usuario(user, contrasenna, cargo);
            	int tienda=rs.getInt("id_tienda");
            	Trabajador trabajador=new Trabajador(dni, nombre, apellido_paterno, apellido_materno, usuario, tienda);
            	lista.agregarNodo(new Nodo<Trabajador>(trabajador));
            }
        }
        catch(SQLException excepcion)
        {
        	excepcion.printStackTrace();
        }
        return lista;
	}
	@Override
	public void modificar(Trabajador trabajador)
	{
		String sql="update trabajador set nombre=?, apellido_paterno=?, apellido_materno=?, usuario=?, contrasenna=?, cargo=?, id_tienda=? where dni=?";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, trabajador.getNombre());
			pstm.setString(2, trabajador.getApellido_paterno());
			pstm.setString(3, trabajador.getApellido_materno());
			pstm.setString(4, trabajador.getUsuario().getUsuario());
			pstm.setString(5, trabajador.getUsuario().getContrasenna());
			pstm.setString(6, trabajador.getUsuario().getCargo());
			pstm.setInt(7, trabajador.getTienda());
			pstm.setString(8, trabajador.getDni());
			pstm.executeUpdate();
        }
        catch(SQLException excepcion)
        {
        	excepcion.printStackTrace();
        }
	}
}
