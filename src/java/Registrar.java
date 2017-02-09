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
import java.sql.ResultSet;
import BaseDatos.Resultados;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author neftaly
 */
@WebServlet(urlPatterns = {"/Registrar"})
public class Registrar extends HttpServlet {

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
    private ResultSet rs;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String nombre=request.getParameter("nombre");
            String apaterno=request.getParameter("apaterno");
            String amaterno=request.getParameter("amaterno");
            String domicilio=request.getParameter("domicilio");
            String colonia=request.getParameter("colonia");
            String telefono=request.getParameter("telefono");
            String correo=request.getParameter("correo");
            String usuario=request.getParameter("usuario");
            String pass=request.getParameter("pass");
            
            //String query = "INSERT INTO usuario(nombre,apaterno,amaterno,Domicilio,Colonia,Telefono,correo,usuario,password,id_rol)\n" +
//"VALUES('"+nombre+"','"+apaterno+"','"+amaterno+"','"+domicilio+"','"+colonia+"','"+telefono+"','"+correo+"','"+usuario+"','"+pass+"',2)";
            String query="{CALL sp_almacena_usuario('"+nombre+"','"+apaterno+"','"+amaterno+"','"+usuario+"','"+pass+"','"+domicilio+"','"+colonia+"','"+telefono+"','"+correo+"')}";
            String res =null;
            try{
                rs = db.precedureQuery(query);
                while(rs.next())
                {
                    res= rs.getString(1);
                }
                
                out.println("<h4> "+res+"</h4>");
            }catch(Exception es )
            {
                
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
