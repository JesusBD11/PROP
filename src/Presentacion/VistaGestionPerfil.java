 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @file VistaGestionPerfil.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief Panel que gestiona la Vista de inicio de sesión y registro en el programa
 * 
 * Vista que solicita al usuario la información de inicio de sesión y registro y que pide a las capas superiores que comprueben la validez de los valores.
 */
 
 /**
 * @class VistaGestionPerfil
 * @brief Clase que inicializa, gestiona y modifica la Vista que controla el inicio de sesión y registro en el programa.
 */

public class VistaGestionPerfil {
    private CtrlPresentacion presentationCtrl;
    private JFrame frameVista = new JFrame("Vista Gestion Perfiles");
    private JPanel panelContenidos = new JPanel();
    private Dimension defaultDimension= new Dimension(400,400);
    VistaGestionPerfil(CtrlPresentacion ctrl) {
        presentationCtrl = ctrl;
        
    }

   private String Usuario;

    void hacerVisible() {
        frameVista.pack();
        inicializar_frameVista();
        frameVista.setVisible(true);
    }
    private void inicializar_frameVista() {
        
    frameVista.setMinimumSize(defaultDimension);
    frameVista.setPreferredSize(frameVista.getMinimumSize());
    frameVista.setResizable(false);
    frameVista.setLocationRelativeTo(null);
    frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panelContenidos = new JPanel(new CardLayout());
    
    JPanel login = new VistaLogin(this);
    JPanel signUp = new VistaSignUp(this);
    panelContenidos.add(login,"logIn");
    panelContenidos.add(signUp,"signUp");
    frameVista.add(panelContenidos);
    frameVista.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                presentationCtrl.setMap();
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
  }
     public void changeToSignUp() {
         
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"signUp");
    }
     
    public void changeToLogIn() {
        
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"logIn");
    }     

    void activar() {
        frameVista.setEnabled(true);
    }
    void desactivar() {
        frameVista.setEnabled(false);
    }

    void changeToMenu() {
        frameVista.dispose();
        presentationCtrl.sincronizacionPerfilesAPrincipal();
    }
    
    public boolean LogIn(String User, char[] Pass)
    {
        boolean res = presentationCtrl.LogIn(User,Pass);        
        return res;
    }
    public boolean SignUp(String User, char[] Pass)
    {
        boolean res = presentationCtrl.SignUp(User,Pass);        
        return res;
    }
    public void setUsuario(String User){presentationCtrl.setUsuario(User);}
    
    
    //boolean validarUserPass1(){getuser, getpass}
}
