/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MasterPage;

/**
 *
 * @author neftaly
 */
public class Maestra {
    
    private String html=null;
    public String abreHtml()
    {
        html="<!DOCTYPE html>\n" +
        "<!--\n" +
        "To change this license header, choose License Headers in Project Properties.\n" +
        "To change this template file, choose Tools | Templates\n" +
        "and open the template in the editor.\n" +
        "-->\n" +
        "<html>";
        return html;
    }
   public String cabecera()
   {
      html="<head>\n" +
"        <title>Con&com</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
      + "<!--DATATABLE-->\n" +
"        <link rel=\"stylesheet\" href=\"script/datatable/css/jquery.dataTables.min.css\" type=\"text/css\"/>        \n" +
"        <script src=\"script/datatable/js/jquery-1.11.3.min.js\" type=\"text/javascript\"> </script>\n" +
"        <script src=\"script/datatable/js/jquery.dataTables.min.js\" type=\"text/javascript\"> </script>\n" +
"        \n" +
"        <!--BOOSTRAP--->\n" +
"        \n" +
"        <link rel=\"stylesheet\" href=\"script/bootstrap/css/bootstrap.css\" type=\"text/css\"/>\n" +
"        <link rel=\"stylesheet\" href=\"script/bootstrap/css/bootstrap.min.css\" type=\"text/css\"/>\n" +
"        <link rel=\"stylesheet\" href=\"script/bootstrap/css/styles.css\" type=\"text/css\"/>\n" +
"        <script src=\"script/bootstrap/js/bootstrap.js\"></script> \n" +
"        <script src=\"script/bootstrap/js/bootstrap.min.js\"></script>\n" +
"        \n" +
"        \n" +
"        \n" +
"        \n" +
"        <!--ENGINEE VALIDATION-->\n" +
"        <link rel=\"stylesheet\" href=\"script/enginne/css/validationEngine.jquery.css\" type=\"text/css\"/>\n" +
"        <script src=\"script/enginne/js/jquery.validationEngine.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\n" +
"        <script src=\"script/enginne/js/languages/jquery.validationEngine-es.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\n" +          
"    </head>" ; 
       return html;
   }
   
   public String menu(String usuario , String pass)
   {
                html="<body>\n" +
         "        <div class=\"row\">\n" +
         "            <div class=\"col-md-12\">\n" +
         "                <img src=\"imagenes/gato.jpg\" height=\"100\" class=\"img-responsive\">\n" +
         "            </div>\n" +
         "        </div>\n" +
         "        <div class=\"row\" style=\"background-color: #93c47d\">\n" +
         "            <div class=\"col-md-2\">\n"       
                        + "<a id=\"btnOk\" href=\"Admin?forkarstrio="+usuario+"&wpriet="+pass+"\" class=\"btn btn-primary\">\n"
                        + "<span class=\"glyphicon glyphicon-user\"></span> usuarios</a>" +
         "            </div>\n" +
         "            <div class=\"col-md-2\">\n"
                        + 
         "                <button id=\"btnRoles\" class=\"btn btn-primary\">Roles</button>\n" +
         "            </div>\n" +
         "            <div class=\"col-md-2\">\n"
    +    "                  <a id=\"btncubierta\" href=\"Cubiertas?forkarstrio="+usuario+"&wpriet="+pass+"\" class=\"btn btn-primary\">\n"
    +    "                      <span class=\" glyphicon glyphicon-cloud\"></span> "
    +    "                          Cubiertas"
    +    "                  </a>\n"
                         
    +    "            </div>\n" +
         "            <div class=\"col-md-2\">\n"
    +    "                 <a id=\"btncubierta\" href=\"Productos?forkarstrio="+usuario+"&wpriet="+pass+"\" class=\"btn btn-primary\">\n"
    +    "                      <span class=\" glyphicon glyphicon-phone\"></span> "
    +    "                          Productos\n"
    +    "                  </a>\n" +         
         "            </div>    \n" +
         "            <div class=\"col-md-2\">\n"
    +    "                  <a id=\"btnDestalleProducto\" href=\"DetalleProducto?forkarstrio="+usuario+"&wpriet="+pass+"\" class=\"btn btn-primary\">\n"
    +    "                      <span class=\" glyphicon glyphicon-search\"></span> "
    +    "                          Detalle producto\n"
    +    "                  </a>\n"
    +    "            </div>\n"
    +    "        </div>\n" +
         "        <br>\n" +
         "        <div class=\"row\" style=\"background-color: #93c47d\">\n" +
         "            <div class=\"col-md-2\">\n"+
                        //agrego 
         "                <a id=\"btnOk\" href=\"Textura?forkarstrio="+usuario+"&wpriet="+pass+"\" class=\"btn btn-primary\">      \n"+
         "                 <span class=\" glyphicon glyphicon-tree-conifer\"></span> Textura</a>" +                
         "            </div>\n" +
         "            <div class=\"col-md-2\">\n" 
         
                        + "<a id=\"btnVenta\" href=\"Ventas?forkarstrio="+usuario+"&wpriet="+pass+"\" class=\"btn btn-primary\">      \n"
                        + "<span class=\" glyphicon glyphicon-eur\"></span> Ventas</a>" +
         "            </div>\n" +
         "            <div class=\"col-md-2\">\n"          
       + "                      <a id=\"btnDescripcionVenta\" href=\"DescripcionVenta?forkarstrio="+usuario+"&wpriet="+pass+"\" class=\"btn btn-primary\">      \n"
                        + "     <span class=\" glyphicon glyphicon-eur\"></span>Descripcion Ventas</a> " +
         "            </div>\n" +
         "            <div class=\"col-md-2\">\n" +      
         "                <a id=\"btnListaPrecio\" href=\"ListaPrecio?forkarstrio="+usuario+"&wpriet="+pass+"\" class=\"btn btn-primary\">      \n"+
         "                 <span class=\" glyphicon glyphicon-eur\"></span> Lista preocio</a>" +                         
         "            </div>\n"
       + "           <div class=\"col-md-2\">\n"
                        + "<a id=\"btnOk\" href=\"Login.html\" class=\"btn btn-primary\">Login  </a>"
                   + "</div>\n" +
         "\n" +
         "            </div>\n" +
         "                ";
       return html;
   }
   
   
   public String cierraHtml()
   {
       html="    </body>\n" +
            "</html>\n" +
            "";
       return html;
   }
}
