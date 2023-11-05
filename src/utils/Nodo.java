package utils;

public class Nodo <T>
{
	private T elemento;
	private Nodo siguiente;
	//
	public Nodo(T elemento)
	{
		this.elemento=elemento;
	}
	//
	public Nodo getSiguiente()
	{
		return siguiente;
	}
	public void setSiguiente(Nodo siguiente)
	{
		this.siguiente = siguiente;
	}
	public T getElemento()
	{
		return elemento;
	}
	public void setElemento(T elemento)
	{
		this.elemento = elemento;
	}
}
