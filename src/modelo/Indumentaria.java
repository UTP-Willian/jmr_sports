package modelo;

import java.math.BigDecimal;

public class Indumentaria 
{
	private int id;
	private String nombre;
	private BigDecimal precio;
	private int stock;
	
	public Indumentaria(int id, String nombre, BigDecimal precio, int stock)
	{
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	public Indumentaria(String nombre, BigDecimal precio, int stock)
	{
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	public Indumentaria(int id, String nombre, int stock)
	{
		this.id=id;
		this.nombre=nombre;
		this.stock=stock;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	public BigDecimal getPrecio()
	{
		return precio;
	}
	public void setPrecio(BigDecimal precio)
	{
		this.precio=precio;
	}
	public int getStock()
	{
		return stock;
	}
	public void setStock(int stock)
	{
		this.stock=stock;
	}
}
