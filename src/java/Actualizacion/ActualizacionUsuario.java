/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizacion;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import BaseDatos.Resultados;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author neftaly
 */
@WebServlet(name = "ActualizacionUsuario", urlPatterns = {"/ActualizacionUsuario"})
public class ActualizacionUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
   private Resultados db = new Resultados();
   private ResultSet rs ;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String paterno = request.getParameter("paterno");
            String materno = request.getParameter("materno");
            String domicilio = request.getParameter("domicilio");
            String colonia = request.getParameter("colonia");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String usuario = request.getParameter("usuario");
            String contrasenia = request.getParameter("contrasenia");
            String tipo = request.getParameter("tipo");
            
            String query="{CALL sp_actualiza_usuario("+id+",'"+nombre+"','"+paterno+"','"+materno+"','"+domicilio+"','"+colonia+"','"+telefono+"','"+correo+"','"+usuario+"','"+contrasenia+"',"+tipo+") }";
            String resultado = null;
            try {
                rs = db.precedureQuery(query);
                while(rs.next())
                {
                   resultado ="{\"valor\":\""+rs.getString(1)+"\"}";
                }         
                
                out.println(resultado);
            } catch (SQLException ex) {
                Logger.getLogger(ActualizacionUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
