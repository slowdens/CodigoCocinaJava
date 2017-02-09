/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mostrar;

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
@WebServlet(name = "MostrarTexturas", urlPatterns = {"/MostrarTexturas"})
public class MostrarTexturas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private  Resultados db = new Resultados();
    private ResultSet rs ;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            String eke =request.getParameter("varis");
            String query ="{CALL sp_mostrar_textura()}";
            String idTextera,tipoTextura,precio,imagen;
            int contador,i=1;
            String json="[";
            try {
                rs = db.precedureQuery(query);
                while(rs.next())
                {
                    idTextera=  rs.getString(1);
                    tipoTextura = rs.getString(2);
                    precio=rs.getString(3);
                    imagen = rs.getString(4);
                    contador= rs.getInt(5);
                    
                    if(i==contador)
                    {
                        json +="[\""+idTextera+"\",\""+tipoTextura+"\",\""+precio+"\",\""+imagen+"\"]";
                    }
                    else
                    {
                        json +="[\""+idTextera+"\",\""+tipoTextura+"\",\""+precio+"\",\""+imagen+"\"],";
                    }
                    i++;        
                }
                
                json += "]";
                out.println(json);
                
            } catch (SQLException ex) {
                String  error = ex.getMessage();
                Logger.getLogger(MostrarTexturas.class.getName()).log(Level.SEVERE, null, ex);
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
