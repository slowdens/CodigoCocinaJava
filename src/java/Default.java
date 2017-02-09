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
import javax.servlet.http.HttpSession;
import MasterPage.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neftaly
 */
@WebServlet(urlPatterns = {"/Default"})
public class Default extends HttpServlet {

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
    private ResultSet rs;
    private Maestra ms = new Maestra();
    private Dnegado dms= new Dnegado();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             HttpSession sesion = request.getSession();
             String usu, pass;
             usu = request.getParameter("forkarstrio");
             pass = request.getParameter("wpriet");
             if(usu.charAt(0)=='=')
             {
                usu = usu.substring(1,usu.length());
             }
             if(pass.charAt(0)=='=')
             {
                pass = pass.substring(1, pass.length());
             }
                  
            String query =  "{CALL sp_verifica_usuario('"+usu+"','"+pass+"')}";
            String permiso=null;
            String rol = null;
            String html=null;
            try {
                rs=bd.precedureQuery(query);
                while(rs.next())
                {
                    permiso = rs.getString(1);
                    rol=rs.getString(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Default.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(permiso.equals("Si")&& rol.equals("Administrador"))
            {
                sesion.setAttribute("usuario", usu);
                html = "<!DOCTYPE html>";
                html+="<html>";
                html = ms.cabecera();
                html+=ms.menu(usu,pass);
                html+="<div class=\"row\">\n" +
"                           <div class=\"col-md-6 img-responsive\">\n" +
"                               <img src=\"imagenes/1.jpeg\">\n" +
"                           </div>\n" +
"                           <div class=\"col-md-6 img-responsive\">\n" +
"                               <img src=\"imagenes/2.png\">\n" +
"                           </div>\n" +
"                     </div>\n" +
"                     <div class=\"row\">\n" +
"                           <div class=\"col-md-6\">\n" +
"                               <img src=\"imagenes/3.jpg\">\n" +
"                           </div>\n" +
"                           <div class=\"col-md-6\">\n" +
"                               <img src=\"imagenes/4.jpg\">\n" +
"                           </div>\n" +
"                     </div>";
                html+="</body>";
                html+="</html>";
                out.println(html); 
            }
            else
            {
                //manda un acceso denegado.
                out.println(  dms.accesoDenegado());
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
