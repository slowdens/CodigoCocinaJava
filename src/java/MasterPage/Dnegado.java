
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
public class Dnegado {
    private String html=null;
    private Maestra ms = new Maestra();
    public String  accesoDenegado()
    {
        html =  ms.abreHtml();
        html  += ms.cabecera();
        html +=   "<body>"
                + "<div class=\"row\"> "
                + "     <div class=\" col-md-12\">"
                + "        <img src=\"imagenes/acceso_denegado.jpg\" class=\"img-responsive\"> "
                + "     </div>    "                        
                + "</div>";
                
        html += ms.cierraHtml();
        
        return html ;
    }
}
