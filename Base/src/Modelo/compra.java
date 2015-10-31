/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Angela
 */
import Modelo.Conexion.*;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import comprasmvc.vistas.JDCompra;
import java.sql.ResultSet;

public class compra {
    String NNM, NTM, NRV;
    Integer CM, CL, EU, NEU, NPM, NFM, NCL;
    Conexion conex = new Conexion();
    Connection conn;
    ResultSet consulta;
    
    public void crear(String NN, String NT, Integer NE, Integer NP, String NR, Integer NF, Integer NL){
        this.NNM = NN;
        this.NTM = NT;
        this.NRV = NR;
        this.NEU = NE;
        this.NPM = NP;
        this.NFM = NF;
        this.NCL = NL;
        
        try
            {
            JOptionPane.showMessageDialog(null, conex.conectar());
            conn = conex.getConexion();
            Statement comando = conn.createStatement();
            
            comando.executeUpdate("insert into medicamentos() values('null','"+NNM+"','"+NTM+"','"+NEU+"','"+NPM+"','"+NRV+"','"+NFM+"','"+NCL+"')"); 
            }  
            catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, "Error 100: "+ e);
            }
                
    }
    public void actualizar(Integer CMA, Integer CLA, Integer EUA){
        this.CM = CMA;
        this.CL = CLA;
        this.EU = EUA;
        
        try
        {
            JOptionPane.showMessageDialog(null, conex.conectar());
            conn = conex.getConexion();
            Statement comando  = conn.createStatement();
            
            consulta = comando.executeQuery("select * from medicamentos where CodMed = '"+CM+"'");
            if(consulta.next())
            {        
                comando.executeUpdate("update medicamentos set Existencia='"+EU+"' where CodMed = '"+CM+"'");
            }
            else
            JOptionPane.showMessageDialog(null, "REGISTRO no existente");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR 300"+e);
        }
    }
    
    public void salir(){
        System.exit(0);
    }
}
