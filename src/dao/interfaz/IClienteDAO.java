package dao.interfaz;

import modelo.Cliente;
import utils.ListaEnlazada;

public interface IClienteDAO extends CRUD<Cliente>
{
	public ListaEnlazada obtenerLista();
}
