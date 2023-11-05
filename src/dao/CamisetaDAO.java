package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.interfaz.IIndumentariaDAO;
import modelo.Camiseta;
import modelo.Indumentaria;
import utils.BD;
import utils.ListaEnlazada;
import utils.Nodo;

public class CamisetaDAO implements IIndumentariaDAO
{
	@Override
	public ListaEnlazada obtenerLista()
	{
		ListaEnlazada lista=new ListaEnlazada();
        String sql="select * from indumentaria where id_tipo_indumentaria=1";
        try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
            ResultSet rs=pstm.executeQuery();
            while(rs.next())
            {
            	int id=rs.getInt("id");
            	String nombre=rs.getString("nombre");
            	BigDecimal precio=rs.getBigDecimal("precio");
            	int stock=rs.getInt("stock");
            	String club=rs.getString("club");
            	String talla=rs.getString("talla");
            	Camiseta camiseta=new Camiseta(id, nombre, precio, stock, club, talla);
            	lista.agregarNodo(new Nodo<Camiseta>(camiseta));
            }
        }
        catch(SQLException excepcion)
        {
        	excepcion.getMessage();
        }
        return lista;
	}
	@Override
	public void agregar(Indumentaria camiseta)
	{
		String sql="insert into indumentaria values(default, ?, ?, ?, ?, ?, 1)";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, camiseta.getNombre());
			pstm.setBigDecimal(2, camiseta.getPrecio());
			pstm.setInt(3, camiseta.getStock());
			pstm.setString(4, ((Camiseta)camiseta).getClub());
			pstm.setString(5, ((Camiseta)camiseta).getTalla());
            pstm.executeUpdate();
        }
        catch(SQLException excepcion)
        {
        	excepcion.getMessage();
        }
	}
	@Override
	public ListaEnlazada buscar(String campo, String valor)
	{	
		ListaEnlazada lista=new ListaEnlazada();
        String sql="select * from indumentaria where lower("+campo+") like lower(?) and id_tipo_indumentaria=1";
        try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, "%"+valor+"%");
            ResultSet rs=pstm.executeQuery();
            while(rs.next())
            {
            	int id=rs.getInt("id");
            	String nombre=rs.getString("nombre");
            	BigDecimal precio=rs.getBigDecimal("precio");
            	int stock=rs.getInt("stock");
            	String club=rs.getString("club");
            	String talla=rs.getString("talla");
            	Camiseta camiseta=new Camiseta(id, nombre, precio, stock, club, talla);
            	lista.agregarNodo(new Nodo<Camiseta>(camiseta));
            }
        }
        catch(SQLException excepcion)
        {
        	excepcion.getMessage();
        }
        return lista;
	}
	public void modificar(Indumentaria camiseta)
	{
		String sql="update indumentaria set nombre=?, precio=?, stock=?, club=?, talla=? where id=?";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setString(1, camiseta.getNombre());
			pstm.setBigDecimal(2, camiseta.getPrecio());
			pstm.setInt(3, camiseta.getStock());
			pstm.setString(4, ((Camiseta)camiseta).getClub());
			pstm.setString(5, ((Camiseta)camiseta).getTalla());
			pstm.setInt(6, camiseta.getId());
			pstm.executeUpdate();
        }
        catch(SQLException excepcion)
        {
        	excepcion.printStackTrace();
        }
	}
	@Override
	public void eliminar(Indumentaria camiseta)
	{
		String sql="delete from indumentaria where id=?";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setInt(1, camiseta.getId());
			pstm.executeUpdate();
        }
        catch(SQLException excepcion)
        {
        	excepcion.getMessage();
        }
	}
	@Override
	public ListaEnlazada bajoStock()
	{
		ListaEnlazada lista=new ListaEnlazada();
		String sql="select id, nombre, stock from indumentaria order by stock asc limit 10";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
		{
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
            {
            	int id=rs.getInt("id");
            	String nombre=rs.getString("nombre");
            	int stock=rs.getInt("stock");
            	Camiseta camiseta=new Camiseta(id, nombre, stock);
            	lista.agregarNodo(new Nodo<Camiseta>(camiseta));
            }
		}
		catch(SQLException excepcion)
		{
			excepcion.printStackTrace();
		}
		return lista;
	}
}