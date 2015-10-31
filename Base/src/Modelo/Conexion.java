/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Angela
 */
public class Conexion {
    Connection conn;
    String resp;
    
    public String conectar()
       {
        try
            {
    Class.forName("com.mysql.jdbc.Driver");
    conn =DriverManager.getConnection("jdbc:mysql://localhost/farmacia","root","");
    resp = "Se ha conectado a la base de datos";
    return resp;
            }
        catch (Exception e)
            {
                resp = "Error:"+e;
                return resp;
            }
    
}
      public Connection getConexion()
    {
        return conn; 
    }
    
}
