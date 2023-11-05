package modelo;

import java.math.BigDecimal;

public class VentaDetalle
{
	private int numero_venta;
	private int id_indumentaria;
	private int cantidad;
	private BigDecimal subtotal;
	//
	public VentaDetalle(int numero_venta, int id_indumentaria, int cantidad, BigDecimal subtotal)
	{
		this.numero_venta = numero_venta;
		this.id_indumentaria = id_indumentaria;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}
	public VentaDetalle(int id_indumentaria, int cantidad, BigDecimal subtotal)
	{
		this.id_indumentaria = id_indumentaria;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}
	//
	public int getNumero_venta() {
		return numero_venta;
	}
	public void setNumero_venta(int numero_venta) {
		this.numero_venta = numero_venta;
	}
	public int getId_indumentaria() {
		return id_indumentaria;
	}
	public void setId_indumentaria(int id_indumentaria) {
		this.id_indumentaria = id_indumentaria;
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
