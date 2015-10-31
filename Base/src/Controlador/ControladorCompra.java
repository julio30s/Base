/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;
import comprasmvc.modelos.compra;
import comprasmvc.vistas.JDCompra;
import comprasmvc.vistas.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Angela
 */
public class ControladorCompra implements ActionListener{
    JDCompra comprasmvc;  
    
    public ControladorCompra(JDCompra comprasmvc){
        this.comprasmvc=comprasmvc;
        comprasmvc.cmdCrear.addActionListener(this);
        comprasmvc.cmdActualizar.addActionListener(this);
        comprasmvc.cmdLimpiar.addActionListener(this);
        comprasmvc.cmdSalir.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        compra compras = new compra();
        //if(comprasmvc.cmdCrear== e.getSource()) { 
        if(comprasmvc.cmdCrear==e.getSource()){
            compras.crear(comprasmvc.txtNNM.getText(), comprasmvc.txtNTM.getText(), Integer.parseInt(comprasmvc.txtNEU.getText()), Integer.parseInt(comprasmvc.txtNPM.getText()), comprasmvc.txtNRV.getText(), Integer.parseInt(comprasmvc.txtNFM.getText()), Integer.parseInt(comprasmvc.txtNCL.getText()));
        }
        else{
            if (comprasmvc.cmdActualizar== e.getSource()){
                compras.actualizar(Integer.parseInt(comprasmvc.txtCM.getText()), Integer.parseInt(comprasmvc.txtCL.getText()), Integer.parseInt(comprasmvc.txtEU.getText()));
            }
            else{
                if (comprasmvc.cmdLimpiar== e.getSource()){
                //compras.actualizar(Integer.parseInt(comprasmvc.txtCM.getText()), Integer.parseInt(comprasmvc.txtCL.getText()), Integer.parseInt(comprasmvc.txtEU.getText()));
                    comprasmvc.txtNNM.setText("");
                    comprasmvc.txtNTM.setText("");
                    comprasmvc.txtNEU.setText("");
                    comprasmvc.txtNRV.setText("");
                    comprasmvc.txtNPM.setText("");
                    comprasmvc.txtNFM.setText("");
                    comprasmvc.txtNCL.setText("");
                    comprasmvc.txtCM.setText("");
                    comprasmvc.txtCL.setText("");
                    comprasmvc.txtEU.setText("");
                 }
                else{
                    if (comprasmvc.cmdSalir== e.getSource()){
                        compras.salir();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error 100: "+ e);
                    }
                }
            }
        }
    }

}

