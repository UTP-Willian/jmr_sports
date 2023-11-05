package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.interfaz.IVentaDetalleDAO;
import modelo.VentaDetalle;
import utils.BD;

public class VentaDetalleDAO implements IVentaDetalleDAO
{

	@Override
	public void registrar(VentaDetalle venta_detalle)
	{
		String sql="insert into venta_detalle values(?, ?, ?, ?)";
		try(PreparedStatement pstm=BD.obtenerConexion().prepareStatement(sql))
        {
			pstm.setInt(1, venta_detalle.getNumero_venta());
			pstm.setInt(2, venta_detalle.getId_indumentaria());
			pstm.setInt(3, venta_detalle.getCantidad());
			pstm.setBigDecimal(4, venta_detalle.getSubtotal());
            pstm.executeUpdate();
        }
        catch(SQLException excepcion)
        {
        	excepcion.getMessage();
        }
	}
}
