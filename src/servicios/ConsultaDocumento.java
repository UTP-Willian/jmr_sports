package servicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import modelo.Cliente;

public class ConsultaDocumento implements IConsultaDocumento
{
	private static final String TOKEN="apis-token-6196.XM5edEi-1Kux9OvwfBKoQfBc8k3fT2hx";
	private static final String URL = "https://api.apis.net.pe/v2/";
	@Override
	public Cliente dni(String dni)
	{
		try
		{
			URL url=new URL(URL+"reniec/dni?numero="+dni);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestProperty("Authorization", "Bearer "+TOKEN);
            int response_code=conexion.getResponseCode();
            if(response_code==200)
            {
                BufferedReader br=new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String line;
                StringBuffer response=new StringBuffer();
                while((line=br.readLine())!=null)
                {
                    response.append(line);
                }
                br.close();
                JSONObject json=new JSONObject(response.toString());
                String nombre=json.getString("nombres");
                String apellido_paterno=json.getString("apellidoPaterno");
                String apellido_materno=json.getString("apellidoMaterno");
                Cliente cliente=new Cliente(dni, nombre, apellido_paterno, apellido_materno);
                return cliente;
            }
            else
            {
                BufferedReader br=new BufferedReader(new InputStreamReader(conexion.getErrorStream()));
                String line;
                StringBuffer response=new StringBuffer();
                while((line=br.readLine())!=null)
                {
                    response.append(line);
                }
                br.close();
                JSONObject json=new JSONObject(response.toString());
                String error=json.getString("message");
                JOptionPane.showMessageDialog(null, error.toUpperCase(), "Advertencia", 1);
            }
        }
		catch(Exception excepcion)
		{

			excepcion.printStackTrace();
        }
		return null;
	}

	@Override
	public Cliente ruc(String ruc)
	{
		try
		{
			URL url=new URL(URL+"sunat/ruc?numero="+ruc);
            HttpURLConnection conexion=(HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestProperty("Authorization", "Bearer "+TOKEN);
            int response_code=conexion.getResponseCode();
            if(response_code==200)
            {
                BufferedReader br=new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String line;
                StringBuffer response=new StringBuffer();
                while((line=br.readLine())!=null)
                {
                    response.append(line);
                }
                br.close();
                JSONObject json=new JSONObject(response.toString());
                String nombre=json.getString("razonSocial");
                String direccion=json.getString("direccion");
                Cliente cliente=new Cliente(ruc, nombre, direccion);
                return cliente;
            }
            else
            {
                BufferedReader br=new BufferedReader(new InputStreamReader(conexion.getErrorStream()));
                String line;
                StringBuffer response=new StringBuffer();
                while((line=br.readLine()) != null)
                {
                    response.append(line);
                }
                br.close();
                JSONObject json=new JSONObject(response.toString());
                String error=json.getString("message");
                JOptionPane.showMessageDialog(null, error.toUpperCase(), "Advertencia", 1);
            }
		}
		catch(Exception excepcion)
		{
			excepcion.printStackTrace();
		}
		return null;
	}

}
