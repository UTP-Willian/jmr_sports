package modelo;

import java.math.BigDecimal;

public class Camiseta extends Indumentaria
{
	private String club;
	private String talla;
	
	public Camiseta(int id, String nombre, BigDecimal precio, int stock, String club, String talla)
	{
		super(id, nombre, precio, stock);
		this.club=club;
		this.talla=talla;
	}
	public Camiseta(String nombre, BigDecimal precio, int stock, String club, String talla)
	{
		super(nombre, precio, stock);
		this.club=club;
		this.talla=talla;
	}
	public Camiseta(int id, String nombre, int stock)
	{
		super(id, nombre, stock);
	}

	public String getClub()
	{
		return club;
	}
	public void setClub(String club)
	{
		this.club = club;
	}
	public String getTalla()
	{
		return talla;
	}
	public void setTalla(String talla)
	{
		this.talla = talla;
	}
}
