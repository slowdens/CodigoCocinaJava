/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import BaseDatos.Resultados;
import java.sql.ResultSet;
import MasterPage.*;
/**
 *
 * @author neftaly
 */
@WebServlet(urlPatterns = {"/Admin"})
public class Admin extends HttpServlet {

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
                Logger.getLogger(LoginUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Tiene permiso el suario
             if(permiso.equals("Si")&& rol.equals("Administrador"))
             {
                 sesion.setAttribute("usuario", usu);
                 html = "<!DOCTYPE html>";
                 html+="<html>";
                 html = ms.cabecera();
                 html+=ms.menu(usu,pass);
                 
              
              String archivo="" +
"                            <script src=\"script/customisado/funciones.js\"></script>";
             
                 html +=archivo;
                 html+="<button id=\"addRow\">\n" +
"            <span class=\"glyphicon glyphicon-star \"></span>\n" +
"            Agregar</button>\n" +
"        <button id=\"DelleteRow\">\n" +
"            <span class=\"glyphicon glyphicon-remove\"></span>\n" +
"            Eliminar\n" +
"        </button>\n" +
"        <button id=\"btnupdate\">\n" +
"            <span class=\"glyphicon glyphicon-pencil\"></span>\n" +
"            Actualizar\n" +
"        </button>\n" +
"        <table id=\"example\" class=\"display\" width=\"100%\"></table>\n" +
"        \n" +
"        <div class=\"modal fade\"  id=\"myModal\">\n" +
"        <div class=\"modal-dialog\">\n" +
"          <div class=\"modal-content\">\n" +
"            <div class=\"modal-header\">\n" +
"              <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n" +
"              <h4 class=\"modal-title text-center\">Agregar datos</h4>\n" +
"            </div>\n" +
"            <div class=\"modal-body\">\n" +
"                <form id=\"frmDaost\"> \n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>\n" +
"                                Nombre\n" +
"                            </label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" class=\"validate[required] form-control\"  id=\"txtnombre\" />\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Paterno</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" id=\"txtapaterno\" class=\"validate[required] form-control\" />\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Materno</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" id=\"txtamaterno\" class=\"validate[required] form-control\" />\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Domicilio</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" id=\"txtdomicilio\" class=\"validate[required] form-control\" />\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Colonia</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" id=\"txtcolonia\" class=\"validate[required] form-control\" />\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Telefono</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" id=\"txttelefono\" class=\"validate[required] form-control\" />\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Correo</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" id=\"txtcorreo\" class=\"validate[required] form-control\" />\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Usuario</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" id=\"txtusuario\" class=\"validate[required] form-control\" />\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Contrasenia</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <input type=\"text\" id=\"txtcontrasenia\" class=\"validate[required] form-control\" />\n" +
"                        </div>\n" +
"                    </div>    \n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-2\">\n" +
"                            <label>Rol</label>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\">\n" +
"                            <select id=\"selecRol\" class=\"form-control validate[required]\">\n" +
"                                <option value=\"\">Seleccione</option>\n" +
"                                <option value=\"1\">Administrador</option>\n" +
"                                <option value=\"2\">Cliente</option>                                \n" +
"                            </select> \n" +
"                        </div>\n" +
"                    </div>        \n" +
"                </form>\n" +
"                                \n" +
"            </div>\n" +
"            <div class=\"modal-footer\">\n" +
"              <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n" +
"              <button type=\"button\" id=\"btnSave\" class=\"btn btn-primary\">Agregar</button>\n" +
"            </div>\n" +
"          </div><!-- /.modal-content -->\n" +
"        </div><!-- /.modal-dialog -->\n" +
"      </div><!-- /.modal -->\n" +
"      \n" +
"      \n" +
"      <!-------------------Modal update-------------------->\n" +
"      <div class=\"modal fade\" id=\"modalUpdate\">\n" +
"  <div class=\"modal-dialog\">\n" +
"    <div class=\"modal-content\">\n" +
"      <div class=\"modal-header\">\n" +
"        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n" +
"        <h4 class=\"modal-title\">Actalizar datos</h4>\n" +
"      </div>\n" +
"      <div class=\"modal-body\">\n" +
"        \n" +
"          <form id=\"frmUpdate\">\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Nombre</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdateNombre\" class=\"form-control\" />\n" +
"                      <input type=\"hidden\" id=\"txtid\"  />\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Paterno</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdatePaterno\" class=\"form-control\" />\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Materno</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdateMaterno\" class=\"form-control\" />\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Colonia</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdateColonia\" class=\"form-control\" />\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Telefono</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdateTelefono\" class=\"form-control\" />\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Correo</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdateCorreo\" class=\"form-control\" />\n" +
"                  </div>                  \n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Usuario</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdateUsuario\" class=\"form-control\" />\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Cotrasenia</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdateContrasenia\" class=\"form-control\" />\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Domicilio</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\">\n" +
"                      <input type=\"text\" id=\"txtupdateDomicilio\" class=\"form-control\" />\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"row\">\n" +
"                  <div class=\"col-md-2\">\n" +
"                      <label>Rol</label>\n" +
"                  </div>\n" +
"                  <div class=\"col-md-3\" >\n" +
"                      <select class=\"form-control\" id=\"slupdatesol\">\n" +
"                        <option value=\"\">Seleccione</option>\n" +
"                        <option value=\"1\">Administrador</option>\n" +
"                        <option value=\"2\">Cliente</option>\n" +
"                      </select> \n" +
"                  </div>\n" +
"              </div>\n" +
"          </form>\n" +
"      </div>\n" +
"      <div class=\"modal-footer\">\n" +
"        <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n" +
"        <button type=\"button\" id=\"btnSetUpdate\" class=\"btn btn-primary\">Actualizar</button>\n" +
"      </div>\n" +
"    </div><!-- /.modal-content -->\n" +
"  </div><!-- /.modal-dialog -->\n" +
"</div><!-- /.modal -->";
                 html+="</body>";
                 html+="</html>";
                  //Sout.println("<h4> permiso = "+permiso+" y  rol= "+rol+" </h4>");
                 out.println(html);
             }
             else
             {
               out.println(  dms.accesoDenegado());
             }
             
             
            /*t
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");  
            out.println("<title>Servlet Admin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
