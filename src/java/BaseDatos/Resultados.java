package BaseDatos;


import BaseDatos.Conex;
import com.mysql.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author neftaly
 */
public class Resultados {
  private  Connection miConexion = Conex.GetConnection();
   private ResultSet rs;
   private Statement st;
   private CallableStatement stms;
   
    public ResultSet precedureQuery(String query) throws SQLException
    {
        rs = null;
            stms = (CallableStatement) miConexion.prepareCall(query);
            /*int nu =0;
            int ne=2;
            stms.setInt(1,nu);
            stms.setInt(2,ne);*/
            rs =stms.executeQuery();
       return rs;
    }

    

   //Solamae para introduccir informacion ala base de datos
   public boolean setQuery(String query)
   {
       boolean bandera = false;
       try {
           st=miConexion.createStatement();
           st.executeUpdate(query);
           bandera=true;
         
       } catch (SQLException ex) {
           Logger.getLogger(Resultados.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return bandera;
   }
   
   
   public ResultSet getQuery(String query)
   {
       
       try {
           rs=null;
           st=miConexion.createStatement();
           rs=st.executeQuery(query);
          
       } catch (SQLException ex) {
           Logger.getLogger(Resultados.class.getName()).log(Level.SEVERE, null, ex);
       }
        return rs;
   }
   
   
  
   
   
}
