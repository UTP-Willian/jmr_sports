package utils;

public class ListaEnlazada
{
	private Nodo cabecera;
	//
	private boolean estaVacia()
	{
		if(cabecera==null)
			return true;
		else
			return false;
	}
	private Nodo obtenerUltimoNodo()
	{
		Nodo iterador=cabecera;
        if(iterador!=null)
            while(iterador.getSiguiente()!=null)
                iterador=iterador.getSiguiente();
        return iterador;
	}
	//
	public void agregarNodo(Nodo nuevo_nodo)
	{
		if(estaVacia())
			this.cabecera=nuevo_nodo;
		else
			obtenerUltimoNodo().setSiguiente(nuevo_nodo);
	}
	//
	public Nodo getCabecera()
	{
		return this.cabecera;
	}
}
