package modelo;

public class Trabajador
{
	private String dni;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private Usuario usuario;
	private int tienda;
	//
	public Trabajador(String dni, String nombre, String apellido_paterno, String apellido_materno, Usuario usuario, int tienda)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.usuario = usuario;
		this.tienda = tienda;
	}
	public Trabajador(String dni, String nombre, String apellido_paterno, String apellido_materno, int tienda)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.tienda = tienda;
	}
	//
	public String getDni()
	{
		return dni;
	}
	public void setDni(String dni)
	{
		this.dni = dni;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getApellido_paterno()
	{
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno)
	{
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno()
	{
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno)
	{
		this.apellido_materno = apellido_materno;
	}
	public Usuario getUsuario()
	{
		return usuario;
	}
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
	public int getTienda()
	{
		return tienda;
	}
	public void setTienda(int tienda)
	{
		this.tienda = tienda;
	}

}
