package modelo.dto;

import java.math.BigDecimal;

public class CamisetaVentaDetalleDTO
{
	private String nombre;
	private String talla;
	private int cantidad;
	private BigDecimal subtotal;
	//
	public CamisetaVentaDetalleDTO(String nombre, String talla, int cantidad, BigDecimal subtotal)
	{
		this.nombre = nombre;
		this.talla = talla;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}
	//
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
}
