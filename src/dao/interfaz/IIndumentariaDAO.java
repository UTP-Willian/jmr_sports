package dao.interfaz;

import modelo.Indumentaria;
import utils.ListaEnlazada;

public interface IIndumentariaDAO extends CRUD<Indumentaria>
{
	public ListaEnlazada obtenerLista();
	public ListaEnlazada bajoStock();
}
