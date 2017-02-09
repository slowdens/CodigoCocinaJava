/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BaseDatos.Resultados;
import MasterPage.Dnegado;
import MasterPage.Maestra;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author neftaly
 */
@WebServlet(urlPatterns = {"/DescripcionVenta"})
public class DescripcionVenta extends HttpServlet {

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
            String concantenado="";
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
                Logger.getLogger(DescripcionVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
             if(permiso.equals("Si")&& rol.equals("Administrador"))
            {
                sesion.setAttribute("usuario", usu);
                html = "<!DOCTYPE html>";
                html+="<html>";
                html = ms.cabecera();
                html+=ms.menu(usu,pass);
                //Codigo donde va todo nuestro html
                //Codigo donde va todo nuestro html
                
                html+=""
                + " <!--Mostramo el scritp necesario para que funiones todo-->\n"
                + "         <script src=\"script/customisado/ventaDescripcion.js\"></script>"
                + "         <!--Mostramos los datos en la tabla-->\n"
                + "         <button id=\"btnAgragar\">\n"
                + "             <span class=\"glyphicon glyphicon-star \"></span>\n"
                + "                 Agregar\n"
                + "         </button>\n"
                + "         <button id=\"DelleteRow\">\n"
                + "             <span class=\"glyphicon glyphicon-remove\"></span>\n" 
                + "             Eliminar"
                + "         </button>\n"               
                + "         <table id=\"example\" class=\"display\" width=\"100%\"></table>  \n";
                /*Agregamos el modal para que se agregen los datos**/
                
                
                
        html+="<div class=\"modal fade\" id=\"modalAgregar\">\n" 
                + "        <div class=\"modal-dialog\">\n" 
                + "            <div class=\"modal-content\">\n" 
                + "                <div class=\"modal-header\">\n" 
                + "                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n" 
                + "                  <h4 class=\"modal-title text-center\">Agregar Venta Descripcion </h4>\n" 
                + "                </div>\n" 
                + "                <div class=\"modal-body\">\n" 
                + "                    <form id=\"frmdatosAgregar\">\n" 
                + "                        <div class=\"row\">\n" 
                + "                       <!--     <input type=\"hidden\" id=\"hidids\">-->\n" 
                + "                            <div class=\"col-md-2\">\n" 
                + "                                <label>\n" 
                + "                                    id Detalle\n" 
                + "                                </label>\n" 
                + "                            </div>\n" 
                + "                            <div class=\"col-md-3\">\n" 
                + "                                <input  type=\"text\" id=\"txtidDetalle\" class=\"form-control validate[required]\" />\n" 
                + "                            </div>\n" 
                + "                        </div>    \n" 
                + "                        <div class=\"row\">\n" 
                + "                            <div class=\"col-md-2\">\n" 
                + "                                <label>\n" 
                + "                                   Precio unitario\n" 
                + "                                </label>\n" 
                + "                            </div>\n" 
                + "                            <div class=\"col-md-3\">\n" 
                + "                                <input type=\"text\" id=\"txtprecioUnitario\" class=\"form-control validate[required]\" />                                \n" 
                + "                            </div>\n" 
                + "                        </div>\n" 
                + "                        <div class=\"row\">\n" 
                + "                            <div class=\"col-md-2\">\n" 
                + "                                <label>\n" 
                + "                                    Folio\n" 
                + "                                </label>\n" 
                + "                            </div>\n" 
                + "                            <div class=\"col-md-3\">\n" 
                + "                                <input type=\"text\" id=\"txtFolio\" class=\"form-control validate[required]\" />\n" 
                + "                            </div>\n" 
                + "                        </div>\n" 
                + "                    </form>              \n" 
                + "                </div>\n" 
                + "                <div class=\"modal-footer\">\n" 
                + "                  <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n" 
                + "                  <button type=\"button\" class=\"btn btn-primary\" id=\"btnAgregarDescrionVenta\">Guardar</button>\n" 
                + "                </div>\n" 
                + "            </div><!-- /.modal-content -->\n" 
                + "        </div><!-- /.modal-dialog -->\n" 
                + "    </div><!-- /.modal -->";
                
                
                html+="</body>";
                html+="</html>";
                out.println(html); 
            }
            else{
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
