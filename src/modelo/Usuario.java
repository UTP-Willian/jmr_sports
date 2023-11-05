package modelo;

public class Usuario
{
	private String usuario;
	private String contrasenna;
	private String cargo;
	//
	public Usuario(String usuario, String contrasenna, String cargo)
	{
		this.usuario = usuario;
		this.contrasenna = contrasenna;
		this.cargo = cargo;
	}
	//
	public String getUsuario()
	{
		return usuario;
	}
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}
	public String getContrasenna() {
		return contrasenna;
	}
	public void setContrasenna(String contrasenna)
	{
		this.contrasenna = contrasenna;
	}
	public String getCargo()
	{
		return this.cargo;
	}
	public void setCargo(String cargo)
	{
		this.cargo = cargo;
	}
}
