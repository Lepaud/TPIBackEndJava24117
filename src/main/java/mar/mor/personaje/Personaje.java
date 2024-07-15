package mar.mor.personaje;

public class Personaje {
		private int id_personaje;
	    private String personaje;
	    private String descripcion;
	    private String fecha_nacimiento;
	    private String url_foto;
	   
	    

	    // Constructor con todos los atributos
	    
	    public Personaje(int id_personaje, String personaje, String descripcion, String fecha_nacimiento, String url_foto) {
	        this.id_personaje = id_personaje;
	        this.personaje= personaje;     
	        this.descripcion = descripcion;
	        this.fecha_nacimiento = fecha_nacimiento;
	        this.url_foto = url_foto;
	        
	    }

	    // Getters y setters para todos los atributos
	    
	    public int getId_Personaje() {
	        return id_personaje;
	    }

	    public void setId_Personaje(int id_personaje) {
	        this.id_personaje = id_personaje;
	    }
	    
	    public String getPersonaje() {
			return personaje;
		}

		public void setPersonaje(String personaje) {
			this.personaje = personaje;
		}

	    public String getDescripcion() {
	        return descripcion;
	    }

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }
	    

	    public String getFecha_Nacimiento() {
	        return fecha_nacimiento;
	    }

	    public void setFecha_Nacimiento(String fecha_nacimiento) {
	        this.fecha_nacimiento = fecha_nacimiento;
	    }

	    public String getUrl_Foto() {
	        return url_foto;
	    }

	    public void setUrl_Foto(String url_foto) {
	        this.url_foto = url_foto;
	    }

		
	}



