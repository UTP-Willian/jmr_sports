package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.interfaz.IClienteDAO;
import modelo.Camiseta;
import modelo.Cliente;
import utils.BD;
import utils.ListaEnlazada;
import utils.Nodo;

public class ClienteDAO implements IClienteDAO
{
	@Override
	public void agregar(Cliente cliente)
	{
		String sql="insert into cliente values(?, ?, ?, ?, ?, ?)";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, cliente.getDocumento());
			pstm.setString(2, cliente.getNombre());
			pstm.setString(3, cliente.getApellido_paterno());
			pstm.setString(4, cliente.getApellido_materno());
			pstm.setString(5, cliente.getTelefono());
			pstm.setString(6, cliente.getDireccion());
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
		
		return null;
	}

	@Override
	public void eliminar(Cliente cliente)
	{
		
	}

	@Override
	public ListaEnlazada obtenerLista()
	{
		ListaEnlazada lista=new ListaEnlazada();
        String sql="select * from cliente";
        try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
            ResultSet rs=pstm.executeQuery();
            while(rs.next())
            {
            	String documento=rs.getString("documento");
            	String nombre=rs.getString("nombre");
            	String apellido_paterno=rs.getString("apellido_paterno");
            	String apellido_materno=rs.getString("apellido_materno");
            	String telefono=rs.getString("telefono");
            	String direccion=rs.getString("direccion");
            	Cliente cliente=new Cliente(documento, nombre, apellido_paterno, apellido_materno, telefono, direccion);
            	lista.agregarNodo(new Nodo<Cliente>(cliente));
            }
        }
        catch(SQLException excepcion)
        {
        	excepcion.printStackTrace();
        }
        return lista;
	}
	@Override
	public void modificar(Cliente cliente)
	{
		String sql="update cliente set nombre=?, apellido_paterno=?, apellido_materno=?, telefono=?, direccion=? where documento=?";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, cliente.getNombre());
			pstm.setString(2, cliente.getApellido_paterno());
			pstm.setString(3, cliente.getApellido_materno());
			pstm.setString(4, cliente.getTelefono());
			pstm.setString(5, cliente.getDireccion());
			pstm.setString(6, cliente.getDocumento());
			pstm.executeUpdate();
        }
        catch(SQLException excepcion)
        {
        	excepcion.printStackTrace();
        }
	}
}
