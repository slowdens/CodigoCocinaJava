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
import java.sql.*;
import BaseDatos.Resultados;
        
/**
 *
 * @author neftaly
 */
@WebServlet(urlPatterns = {"/Dos"})
public class Dos extends HttpServlet {

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
             response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
             String query="{CALL sp_mostrar_datos_usuarios(null,null)}";
            //query="{CALL sp_mostrar_datos_usuarios('"+usario+"','"+nombre+"')}";
            String id,nombres,apaterno,amaterno,colonia,telefono,correo,usuario,pasword,rol,domicilio;
            int contador,i=1;
            String jsonSimple = "[";
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
                    domicilio=rs.getString(12);
                    if(i==contador)
                    {
                       
                      
                        jsonSimple +="[\""+id+"\",\""+nombres+"\",\""+apaterno+"\",\""+amaterno+"\",\""+colonia+"\",\""+telefono+"\",\""+correo+"\",\""+usuario+"\",\""+pasword+"\",\""+rol+"\",\""+domicilio+"\"]"; 
                       
                    }
                    else
                    {
                                       
                           jsonSimple +="[\""+id+"\",\""+nombres+"\",\""+apaterno+"\",\""+amaterno+"\",\""+colonia+"\",\""+telefono+"\",\""+correo+"\",\""+usuario+"\",\""+pasword+"\",\""+rol+"\",\""+domicilio+"\" ],";  
                    }
                    i++;       
                }
                
                //JSONObject jns= new JSONObject(jsondato);
                //JsonObject jsonss = new JsonObject(jsondato);
                jsonSimple += "]";
                //String dato =  // jsondato;
               //FileManager file = new FileManager();
                //file.borrarArchivo();
                
                out.println(jsonSimple);
      
            } catch (SQLException ex) {
                //Logger.getLogger(Dos.class.getName()).log(Level.SEVERE, null, ex);
            }
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
