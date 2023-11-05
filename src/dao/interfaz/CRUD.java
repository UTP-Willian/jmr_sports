package dao.interfaz;

import utils.ListaEnlazada;

public interface CRUD<E>
{
	public void agregar(E objeto);
	public ListaEnlazada buscar(String campo, String valor);
	public void modificar(E objeto);
	public void eliminar(E objeto);
}
