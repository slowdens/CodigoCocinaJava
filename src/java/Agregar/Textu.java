/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agregar;

/**
 *
 * @author neftaly
 */
public class Textu {
    private String textura;
    private String precio;
    private String urlAdsoluta;
     
    public void setTextura(String tex)
    {
        this.textura=tex;
    }
    public void setPrecio(String prec)
    {
        this.precio=prec;
    }
    public void setUrl(String url)
    {
        this.urlAdsoluta = url;
    }
    
    public String getTextura()
    {
        return this.textura;
    }
    public String getPrecio()
    {
        return this.precio;
    }
    
    public String getrul()
    {
        return this.urlAdsoluta;
    }
    
}
