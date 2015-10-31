/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sergio
 */
public class ControladorCrud implements ActionListener {
    
    JFCrud vistaCRUD = new JFCrud();
    ClienteDAO modeloCRUD = new ClienteDAO();
    
    public ControladorCrud(JFCrud vistaCRUD, ClienteDAO modeloCRUD){
        this.modeloCRUD = modeloCRUD;
        this.vistaCRUD = vistaCRUD;
        this.vistaCRUD.btnRegistrar.addActionListener(this);
        this.vistaCRUD.btnListar.addActionListener(this);
        this.vistaCRUD.btnEditar.addActionListener(this);
        this.vistaCRUD.btnEliminar.addActionListener(this);
        this.vistaCRUD.btnGEdit.addActionListener(this);
        this.vistaCRUD.txtIdcliente.addActionListener(this);
        this.vistaCRUD.txtNombre.addActionListener(this);
        this.vistaCRUD.txtApellido.addActionListener(this);;
        this.vistaCRUD.txtNumerocuenta.addActionListener(this);;
        this.vistaCRUD.txtBanco.addActionListener(this);;
    }
public void InicializarCrud(){
        
    }
    
    public void LLenarTabla(JTable tablaD){
        DefaultTableModel  modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        
        modeloT.addColumn("IDCLIENTE");
        modeloT.addColumn("NOMBRE");
        modeloT.addColumn("APELLIDO");
        modeloT.addColumn("NUMEROCUENTA");
        modeloT.addColumn("BANCO");
        
        Object[] columna = new Object[5];

        int numRegistros = modeloCRUD.listCliente().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCRUD.listCliente().get(i).getIdcliente();
            columna[1] = modeloCRUD.listCliente().get(i).getNombre();
            columna[2] = modeloCRUD.listCliente().get(i).getApellido();
            columna[3] = modeloCRUD.listCliente().get(i).getNumerocuenta();
            columna[4] = modeloCRUD.listCliente().get(i).getBanco();
            modeloT.addRow(columna);
        }
    }
    
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == vistaCRUD.btnRegistrar){
            String idcliente = vistaCRUD.txtIdcliente.getText();
            String nombre = vistaCRUD.txtNombre.getText();
            String apellido = vistaCRUD.txtApellido.getText();
            String numerocuenta = vistaCRUD.txtNumerocuenta.getText();
            String banco = vistaCRUD.txtBanco.getText();
            
            String rptaRegistro = modeloCRUD.insertCliente(idcliente, nombre, apellido, numerocuenta, banco);
            
            if(rptaRegistro!=null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
                LimpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Registro Erroneo.");
            }
        }
        
        if(e.getSource() == vistaCRUD.btnListar){
            LLenarTabla(vistaCRUD.jtDatos);
            JOptionPane.showMessageDialog(null, "Lista de registros.");
        }
        
        if(e.getSource() == vistaCRUD.btnEditar){
            int filaEditar = vistaCRUD.jtDatos.getSelectedRow();
            int numfilas = vistaCRUD.jtDatos.getSelectedRowCount();
            
            if(filaEditar>=0 && numfilas==1){
                vistaCRUD.txtIdcliente.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar,0)));

                vistaCRUD.txtIdcliente.setEditable(false);
                vistaCRUD.btnGEdit.setEnabled(true);
                vistaCRUD.btnEditar.setEnabled(false);
                vistaCRUD.btnEliminar.setEnabled(false);
                vistaCRUD.btnRegistrar.setEnabled(false);
                vistaCRUD.btnListar.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione registro a editar");
            }    
        }
        
        if(e.getSource() == vistaCRUD.btnGEdit){
            String idcliente = vistaCRUD.txtIdcliente.getText();
            String nombre = vistaCRUD.txtNombre.getText();
            String apellido = vistaCRUD.txtApellido.getText();
            String numerocuenta = vistaCRUD.txtNumerocuenta.getText();
            String banco = vistaCRUD.txtBanco.getText();
            
            int rptEdit = modeloCRUD.editCliente(idcliente, nombre, apellido, numerocuenta, banco);
            if(rptEdit>0){
                LimpiarCampos();
                JOptionPane.showMessageDialog(null, "Edicion exitosa.");
                LLenarTabla(vistaCRUD.jtDatos);
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo realizar edicion.");
            }
            vistaCRUD.txtIdcliente.setEditable(true);
            vistaCRUD.btnGEdit.setEnabled(false);
            vistaCRUD.btnEditar.setEnabled(true);
            vistaCRUD.btnEliminar.setEnabled(true);
            vistaCRUD.btnRegistrar.setEnabled(true);
            vistaCRUD.btnListar.setEnabled(true);
        }
       
        if(e.getSource() == vistaCRUD.btnEliminar){
            int filInicio = vistaCRUD.jtDatos.getSelectedRow();
            int numfilas = vistaCRUD.jtDatos.getSelectedRowCount();
            ArrayList<String> listaIdcliente = new ArrayList<>();
            String idcliente;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    idcliente = String.valueOf(vistaCRUD.jtDatos.getValueAt(i+filInicio, 0));
                    listaIdcliente.add(i, idcliente);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con idcliente: "+listaIdcliente.get(j)+"? ");
                    if(rpta==0){
                        modeloCRUD.deleteCliente(listaIdcliente.get(j));
                    }
                }
                LLenarTabla(vistaCRUD.jtDatos);
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
    }
    public void keyPressed(KeyEvent e){
        
    }

    
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == vistaCRUD.txtIdcliente){
            char c = e.getKeyChar();
            if(c<'0' || c>'9'){
                e.consume();
            }
        }
        
        if(e.getSource() == vistaCRUD.txtNombre || e.getSource() == vistaCRUD.txtNombre || e.getSource() == vistaCRUD.txtNumerocuenta ){
            char c = e.getKeyChar();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c!=(char)KeyEvent.VK_SPACE)){
                e.consume();
            }
        }
    }

    private void LimpiarCampos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    