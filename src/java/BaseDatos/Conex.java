package BaseDatos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nef
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conex {
    public static Connection GetConnection()
    {
        Connection conexion=null;
     
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/cocinas";
            String usuarioDB="root";
            String passwordDB="";
            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            conexion=null;
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            
            conexion=null;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            conexion=null;
        }
        finally
        {
            return conexion;
        }
    }
}
