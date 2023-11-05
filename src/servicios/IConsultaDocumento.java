package servicios;

import modelo.Cliente;

public interface IConsultaDocumento
{
	public Cliente dni(String dni);
	public Cliente ruc(String ruc);
}
