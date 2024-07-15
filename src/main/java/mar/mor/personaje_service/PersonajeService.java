package mar.mor.personaje_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mar.mor.conexion.Conexion;
import mar.mor.personaje.Personaje;

public class PersonajeService {
	private Conexion conexion;
	
	public PersonajeService()
	{
		this.conexion=new Conexion();
	}
	
	public List<Personaje> getAllpersonajes() throws SQLException,ClassNotFoundException
	{
		List<Personaje> personajes=new ArrayList<>();
		Connection con=conexion.getConnection();
		String sql="SELECT * FROM personajes";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
		{
			Personaje personaje=new Personaje(
					rs.getInt("id_personaje"),   //
					rs.getString("personaje"),
					rs.getString("descripcion"),
					rs.getString("fecha_nacimiento"),
					rs.getString("url_foto")
									
					);		
			personajes.add(personaje);
		}
		rs.close();
		ps.close();		
		return personajes;
	}
	
	
	public Personaje getpersonajeById(int id_personaje) throws SQLException,ClassNotFoundException  //
	{
		Personaje personaje=null;
		Connection con=conexion.getConnection();
		String sql="SELECT * FROM personajes WHERE id_personaje=?";		//
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id_personaje);    //
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
		{
			        personaje=new Personaje(
					rs.getInt("id_personaje"),
					rs.getString("personaje"),
					rs.getString("descripcion"),
					rs.getString("fecha_nacimiento"),
					rs.getString("url_foto")
									
					);			
		}
		rs.close();
		ps.close();	
		return personaje;
		
	}
	
	public void addPersonaje(Personaje personaje) throws SQLException,ClassNotFoundException
	{
		Connection con=conexion.getConnection();
		String sql="INSERT INTO personajes (personaje,descripcion,fecha_nacimiento,url_foto) "
				+ "VALUES (?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, personaje.getPersonaje());
		ps.setString(2, personaje.getDescripcion());
		ps.setString(3, personaje.getFecha_Nacimiento());
		ps.setString(4, personaje.getUrl_Foto());
		ps.executeUpdate();
		ps.close();	
	}
	
	   public void updatePersonaje(Personaje personaje) throws SQLException, ClassNotFoundException 
	    {
	        Connection con = conexion.getConnection();
	        String sql = "UPDATE personajes SET personaje = ?,  descripcion = ?, fecha_nacimiento = ?, url_foto = ? WHERE id = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, personaje.getPersonaje());      
	        ps.setString(2, personaje.getDescripcion());
	        ps.setString(3, personaje.getFecha_Nacimiento());
	        ps.setString(4, personaje.getUrl_Foto());
	        ps.setInt(5, personaje.getId_Personaje());
	        ps.executeUpdate();
	        ps.close();
	        con.close();
	    }
	
	public void deletePersonaje(int id_personaje) throws SQLException,ClassNotFoundException
	{
		Connection con=conexion.getConnection();
		String sql="DELETE FROM personajes WHERE id_personaje=?";		//
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id_personaje);   //
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	

}

