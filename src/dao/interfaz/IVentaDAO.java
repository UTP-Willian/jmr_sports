package dao.interfaz;

import modelo.Venta;
import utils.ListaEnlazada;

public interface IVentaDAO
{
	public void registrar(Venta venta, ListaEnlazada lista_venta_detalle);
}
