package mar.mor.personaje_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import mar.mor.personaje.Personaje;
import mar.mor.personaje_service.PersonajeService;

@WebServlet("/personajes/*")


public class PersonajeServlet extends HttpServlet 
{

    private static final long serialVersionUID = 0;
	private PersonajeService personajeService;
	private ObjectMapper objectMapper;
	
     

    @Override
    public void init() throws ServletException 
    {
        personajeService = new PersonajeService();
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String pathInfo = req.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) 
            {
                List<Personaje> personajes = personajeService.getAllpersonajes();
                String json = objectMapper.writeValueAsString(personajes);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
            } 
            else 
            {
                String[] pathParts = pathInfo.split("/");
                int id_personaje = Integer.parseInt(pathParts[1]);
                Personaje personaje = personajeService.getpersonajeById(id_personaje);   //
                if (personaje != null) 
                {
                    String json = objectMapper.writeValueAsString(personaje);
                    resp.setContentType("application/json");
                    resp.getWriter().write(json);
                } 
                else 
                {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } 
        catch (SQLException | ClassNotFoundException e) 
        {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try 
        {
            Personaje personaje = objectMapper.readValue(req.getReader(), Personaje.class);
            personajeService.addPersonaje(personaje);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } 
        catch (SQLException | ClassNotFoundException e) 
        {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try 
        {
            Personaje personaje = objectMapper.readValue(req.getReader(), Personaje.class);
            personajeService.updatePersonaje(personaje);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } 
        catch (SQLException | ClassNotFoundException e) 
        {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String pathInfo = req.getPathInfo();
        try {
            	if (pathInfo != null && pathInfo.split("/").length > 1) 
            	{
	                int id_personaje = Integer.parseInt(pathInfo.split("/")[1]);   //
	                personajeService.deletePersonaje(id_personaje);
	                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            	} 
            	else 
            	{
            		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            	}
        	} 
        	catch (SQLException | ClassNotFoundException e) 
        	{
        			throw new ServletException(e);
        	}
    }
}



