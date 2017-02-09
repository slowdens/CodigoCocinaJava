/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BaseDatos.Resultados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Archivos.FileManager;
/**
 *
 * @author neftaly
 */
@WebServlet(urlPatterns = {"/Usuarios"})
public class Usuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Resultados bd = new Resultados();
    private ResultSet rs ;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //out.println("<!DOCTYPE html>");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            
           // String usario =   request.getParameter("user");
            //String nombre = request.getParameter("bress");
            String query =null;
           /*if(!usario.equals("") && nombre.equals(""))
           {
               query="{CALL sp_mostrar_datos_usuarios('"+usario+"',null)}";
           }
           if(usario.equals("") && !nombre.equals(""))
           {
               query="{CALL sp_mostrar_datos_usuarios(null,'"+nombre+"')}";
           }
           if(usario.equals("") && nombre.equals(""))
           {
               query="{CALL sp_mostrar_datos_usuarios(null,null)}";
           }*/
           
           query="{CALL sp_mostrar_datos_usuarios(null,null)}";
            //query="{CALL sp_mostrar_datos_usuarios('"+usario+"','"+nombre+"')}";
            String id,nombres,apaterno,amaterno,colonia,telefono,correo,usuario,pasword,rol;
            int contador,i=1;
            String jsondato="[";
            String json = "{ \"data\" : [";
            
            try {
                rs = bd.precedureQuery(query);
                //int N_Registros = rs.getInt ("count(*)"); 
                while(rs.next())
                {
                    id= rs.getString(1);
                    nombres=rs.getString(2);
                    apaterno=rs.getString(3);
                    amaterno = rs.getString(4);
                    colonia=rs.getString(5);
                    telefono = rs.getString(6);
                    correo =rs.getString(7);
                    usuario =rs.getString(8);
                    pasword=rs.getString(9);
                    rol = rs.getString(10);
                    contador=rs.getInt(11);
                    if(i==contador)
                    {
                        //esto es cuando la consulta ya no tiene mas en el ultimo registro y cerramos 
                      jsondato+="{\"id\":"+id+",\"nombre\":\""+nombres+"\",\"apaterno\":\""+apaterno+"\",\"amaterno\":\""+amaterno+"\",\"colonia\":\""+colonia+"\",\"telefono\":\""+telefono+"\","
                            + "\"correo\":\""+correo+"\",\"usuario\":\""+usuario+"\",\"password\":\""+pasword+"\",\"rol\":\""+rol+"\"}";
                      
                       /* json +="[\""+id+"\",\n"
                                + "\""+nombres+"\",\n"
                                + "\""+apaterno+"\",\n"
                                + "\""+amaterno+"\",\n"
                                + "\""+colonia+"\",\n"
                                + "\""+telefono+"\",\n"
                                + "\""+correo+"\",\n"
                                + "\""+usuario+"\",\n"
                                + "\""+pasword+"\",\n"
                                + "\""+rol+"\"]"; 
                        */
                    }
                    else
                    {
                        //Separamos por lineas el objeto json
                        jsondato+="{\"id\":"+id+",\"nombre\":\""+nombres+"\",\"apaterno\":\""+apaterno+"\",\"amaterno\":\""+amaterno+"\",\"colonia\":\""+colonia+"\",\"telefono\":\""+telefono+"\","
                            + "\"correo\":\""+correo+"\",\"usuario\":\""+usuario+"\",\"password\":\""+pasword+"\",\"rol\":\""+rol+"\"},";
                        
                     /*   json +="[\""+id+"\",\n"
                                + "\""+nombres+"\",\n"
                                + "\""+apaterno+"\",\n"
                                + "\""+amaterno+"\",\n"
                                + "\""+colonia+"\",\n"
                                + "\""+telefono+"\",\n"
                                + "\""+correo+"\",\n"
                                + "\""+usuario+"\",\n"
                                + "\""+pasword+"\",\n"
                                + "\""+rol+"\"],"; 
                       */ 
                    }
                    i++;       
                }
                jsondato+="]";
                //JSONObject jns= new JSONObject(jsondato);
                //JsonObject jsonss = new JsonObject(jsondato);
                json += "]}";
                //String dato =  // jsondato;
               //FileManager file = new FileManager();
                //file.borrarArchivo();
                
                out.println(jsondato);
      
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        catch(Exception es)
        {
            String ess=es.getMessage();
            System.out.println(es.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
