package mar.mor.conexion;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Conexion {
	
	public String driver="com.mysql.cj.jdbc.Driver";
	public Connection getConnection() throws ClassNotFoundException
	{
		Connection conexion=null;
		try
		{
			Class.forName(driver);
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_la_web_del_celiaco","root","");
			
		}
		catch(SQLException e)
		{
			System.out.println("hay un error: "+e);
		}		
		return conexion;
	}	
	
	public static void main(String[] args) throws ClassNotFoundException ,SQLException
	{
		Connection conexion=null;
		Conexion con=new Conexion();
		conexion=con.getConnection();
		
		PreparedStatement ps;
		ResultSet rs;
		
		ps=conexion.prepareStatement("select * from personajes");		
		rs=ps.executeQuery();

		while(rs.next())
		{
			int id_personaje=rs.getInt("id_personaje" );    //
			String personaje=rs.getString("personaje" );
			String descripcion=rs.getString("descripcion");
			String fecha_nacimiento=rs.getString("fecha_nacimiento" );
			String url_foto=rs.getString("url_foto");
			System.out.println(id_personaje + " " + personaje + " " + descripcion + " " + fecha_nacimiento + " " + url_foto );			
		}
		
	}

}


