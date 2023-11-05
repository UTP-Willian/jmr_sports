package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venta
{
	private int numero;
	private LocalDateTime fecha;
	private BigDecimal total;
	private String dni_trabajador;
	//
	public Venta(int numero, LocalDateTime fecha, BigDecimal total, String dni_trabajador)
	{
		this.numero = numero;
		this.fecha = fecha;
		this.total = total;
		this.dni_trabajador = dni_trabajador;
	}
	public Venta(LocalDateTime fecha, BigDecimal total, String dni_trabajador)
	{
		this.fecha = fecha;
		this.total = total;
		this.dni_trabajador = dni_trabajador;
	}
	//
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getDni_trabajador() {
		return dni_trabajador;
	}
	public void setDni_trabajador(String dni_trabajador) {
		this.dni_trabajador = dni_trabajador;
	}
}
