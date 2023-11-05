package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import dao.interfaz.IVentaDAO;
import modelo.Venta;
import modelo.VentaDetalle;
import utils.BD;
import utils.ListaEnlazada;
import utils.Nodo;

public class VentaDAO implements IVentaDAO
{
	private VentaDetalleDAO dao_venta_detalle=new VentaDetalleDAO();
	//
	@Override
	public void registrar(Venta venta, ListaEnlazada lista_venta_detalle)
	{
		String sql="insert into venta values(default, ?, ?, ?) returning numero";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setObject(1, venta.getFecha(), Types.TIMESTAMP);
			pstm.setBigDecimal(2, venta.getTotal());
			pstm.setString(3, venta.getDni_trabajador());
            ResultSet rs=pstm.executeQuery();
            rs.next();
            int numero_venta=rs.getInt("numero");
            Nodo<VentaDetalle> venta_detalles=lista_venta_detalle.getCabecera();
            while(venta_detalles!=null)
            {
            	VentaDetalle venta_detalle=venta_detalles.getElemento();
            	venta_detalle.setNumero_venta(numero_venta);
            	dao_venta_detalle.registrar(venta_detalle);
            	venta_detalles=venta_detalles.getSiguiente();
            }
        }
        catch(SQLException excepcion)
        {
        	excepcion.getMessage();
        }
	}

}
