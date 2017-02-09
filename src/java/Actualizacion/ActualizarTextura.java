/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizacion;

import Agregar.Textu;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import BaseDatos.Resultados;
import java.io.BufferedReader;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 *
 * @author neftaly
 */
@WebServlet(name = "ActualizarTextura", urlPatterns = {"/ActualizarTextura"})
public class ActualizarTextura extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean isMultipart;
   private String filePath="/home/neftaly/NetBeansProjects/Conycom/web/imagenes/Texturas/",res=null;
   private int maxFileSize = 50 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;
   private Textu tex = new Textu(); 
   private String dato=null,completa=null;
   private Resultados db = new Resultados();
   private ResultSet rs ;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //out.println("<!DOCTYPE html>");
           String precio=null, textura=null,nombreImagen=null,urlCompleta=null,id=null;
            String ruta="",resultado="";
            String query ="";
            isMultipart = ServletFileUpload.isMultipartContent(request);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("/home/neftaly/NetBeansProjects/Conycom/web/imagenes/Texturas"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax( maxFileSize );
            try{ 
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);    
     
                // Process the uploaded file items
                Iterator i = fileItems.iterator();     
                int count=0;
                while ( i.hasNext () ) 
                {
                    FileItem fi = (FileItem)i.next();
                    String NombreCampo= fi.getFieldName();
                    String nombre = fi.getName();         
         
                    switch(count)
                    {
                        case 0:
                            nombreImagen=fi.getName();
                            
                            break;
                        case 1:
                            textura = fi.getFieldName();
                            tex.setTextura(textura);
                            break;
                        case 2 :
                            precio = fi.getFieldName();
                            tex.setPrecio(precio);
                            break;
                        case 3:
                            id= fi.getFieldName();
                            break;
                                
                    }
                    if(count==3)
                    {
                        query="{CALL sp_actualizar_textura('"+textura+"',"+precio+",'"+completa+"',"+id+")}";
                        rs = db.precedureQuery(query);
                        while (rs.next())
                        {
                            //resultado ="{\"valor\":\""+rs.getString(1)+"\",\"liga\":\""+completa+"\",\"ids\":\""+rs.getString(2)+"\"}";
                            
                            //Este es el bueno
                            resultado ="{\"idCampo\":\""+rs.getString(1)+"\",\"textura\":\""+rs.getString(2)+"\",\"imagens\":\""+rs.getString(3)+"\",\"preciosTextura\":\""+rs.getString(4)+"\",\"valor\":\""+rs.getString(5)+"\"}";
                            
                            this.res = resultado;
                          //out.println(resultado);
                        }
                        
                    }
                    // String dato =   fi.getString(NombreCampo);
                    if ( !fi.isFormField () )	
                    {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        String contentType = fi.getContentType();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 )
                        {
                            file = new File( filePath + fileName);
                            //fileName.substring( fileName.lastIndexOf("\\"))) ;
                        }
                        else
                        {
                            urlCompleta = filePath + fileName;
                            completa=urlCompleta;
                            tex.setUrl(urlCompleta);
                            file = new File( filePath + fileName);
                            
                            //fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                        }
                        fi.write( file ) ;
                        
                    }
                    count++;
                }
      
            }catch(Exception ex) {
                System.out.println(ex);
            }
     
            out.println(this.res);
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
