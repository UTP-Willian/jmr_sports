package dao.interfaz;

import modelo.Trabajador;
import utils.ListaEnlazada;

public interface ITrabajadorDAO extends CRUD<Trabajador>
{
	public ListaEnlazada obtenerLista();
}
