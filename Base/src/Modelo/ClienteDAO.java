/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Sergio
 */
public class ClienteDAO {
    Conexion conexion;
    
    public ClienteDAO () {
    conexion = new Conexion();
   }
    
    public String insertCliente(String idcliente, String nombre, String apellido, String numerocuenta, String banco){
        String rptaRegistro=null;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_insertcliente(?,?,?,?,?)}");
            cs.setString(1, idcliente);
            cs.setString(2, nombre);
            cs.setString(3, apellido);
            cs.setString(4, numerocuenta);
            cs.setString(5, banco);
            
            int numFAfectas = cs.executeUpdate();
            
            if(numFAfectas>0){
                rptaRegistro="Registro exitoso.";
            }
        } catch (Exception e) {
        }
        return rptaRegistro;
    }
    
        public ArrayList<Cliente> listCliente(){
        ArrayList listaCliente = new ArrayList();
        Cliente cliente;
        try {
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("select * from Cliente");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cliente = new Cliente();
                cliente.setIdcliente(rs.getString(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellido(rs.getString(3));
                cliente.setNumerocuenta(rs.getString(4));
                cliente.setBanco(rs.getString(5));
                listaCliente.add(cliente);
            }
        } catch (Exception e) {
        }
        return listaCliente;
    }
         public int deleteCliente(String idcliente){
        int filAfectadas= 0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_deleteCliente(?)}");
            cs.setString(1, idcliente);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        
        return filAfectadas;
    }
    
    public int editCliente(String idcliente, String nombre, String apellido, String numerocuenta, String banco){
        int filAfectadas=0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_editPersona(?,?,?,?,?)}");
            cs.setString(1, idcliente);
            cs.setString(2, nombre);
            cs.setString(3, apellido);
            cs.setString(4, numerocuenta);
            cs.setString(5, banco);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        return filAfectadas;  
    }
    
    public ArrayList<Cliente> buscaCliente(String nombre){
        ArrayList listaCliente = new ArrayList();
        Cliente cliente;
        try {
            Connection acceDB = conexion.getConexion();
            CallableStatement cs = acceDB.prepareCall("{call sp_buscanombre(?)}");
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                cliente = new Cliente();
                cliente.setIdcliente(rs.getString(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellido(rs.getString(3));
                cliente.setNumerocuenta(rs.getString(4));
                cliente.setBanco(rs.getString(5));
                listaCliente.add(cliente);
            }
        } catch (Exception e) {
        }
        return listaCliente;
    }
}
 //To change body of generated methods, choose Tools | Templates.}