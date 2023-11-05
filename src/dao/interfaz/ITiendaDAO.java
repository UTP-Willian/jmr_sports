package dao.interfaz;

import modelo.Tienda;
import utils.ListaEnlazada;

public interface ITiendaDAO
{
	public ListaEnlazada obtenerLista();
	public Tienda obtenerTienda(int id);
}
