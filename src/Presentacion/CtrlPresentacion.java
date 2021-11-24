/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.controladores.CtrlCapaDominio;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @file CtrlPresentacion.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief Controlador de la capa de presentación.
 * 
 * Controlador que se encarga de gestionar las peticiones de datos de las capas de presentación, mandarlas a la capa de dominio,
 * recoger los resultados y llevarlos de vuelta a la vista que hace la solicitud.
 */
 
 /**
 * @class CtrlPresentacion
 * @brief Clase que inicializa todos los controladores de presentación y contiene las funcionalidades necesarias para llevar a cabo la función explicada anteriormente.
 */
public class CtrlPresentacion {
    
    private CtrlCapaDominio ccd;
    private VistaPrincipal vistaPrincipal;
    private VistaGestionPerfil vistaGP;
    private String Usuario;
    public CtrlPresentacion(){
        ccd = new CtrlCapaDominio();
        //list1 = ctrlcapadominio.getListPartidas();
        //list2 = ctrlcapadominio.getListUsuarios();
        //list3 = ctrlcapadominio.getListKakuros();
        //list4 = ctrlcapadominio.getRanking();
        //boolean res = ccd.validarUserPass(user,pass);
    }

    public void inicializarVistaPerfiles() {
        VistaGestionPerfil vistaGP = new VistaGestionPerfil(this);
        vistaGP.hacerVisible();      
    }
    
    public void inicializarVistaPrincipal() {
        vistaPrincipal = new VistaPrincipal(this,Usuario);
        //vistaPrincipal.setUsuario(Usuario);
        vistaPrincipal.hacerVisible();
    }
    
    public void sincronizacionVistaPrincipalAPerfiles() {
        vistaPrincipal.desactivar();
        // Solo se crea una vista secundaria (podria crearse una nueva cada vez)
        if (vistaGP == null){
            inicializarVistaPerfiles();
        }
    }
    
    public void sincronizacionPerfilesAPrincipal() {
        inicializarVistaPrincipal();
    }
    
    public boolean LogIn(String User, char[] Pass)
    {
        boolean res = ccd.LogIn(User,Pass);
        return res;
    }
    public boolean SignUp(String User, char[] Pass)
    {
        boolean res = ccd.SignUp(User,Pass);
        return res;
    }
    public void setUsuario(String User){Usuario = User;}
    //setters y getters
    //boolean validaruserpass(Usuario,Pass)

    String[] CargarIDs(String User) {
        
        String[] IDs = ccd.CargarIDs(User);
        return IDs;
    }
    
    public String[] get_lista_kakuro()
    {
        String[] res = ccd.get_lista_kakuro();
        return res;
    }
    public String ShowRanking()
    {
        String res = ccd.ShowRanking();
        return res;
    }
    
    public String [][] getkakuroenjuego(int id) {
    
        return ccd.getkakuroenjuego(id);
    }

    int generarKakuro(int dificultad) {
        return ccd.generarKakuro(dificultad);
    }

    int generarKakuro(int dimensiones, int numCasillasNegras) {
        return ccd.generarKakuro(dimensiones,numCasillasNegras);
    }

    String generarKakuroManual(String[][] gridContentsMatrix) {
        return ccd.generarKakuroManual(gridContentsMatrix);
    }

    void guardarPartida(String IDPartida, String[][] contents,int time, int idKakuroEnJuego,boolean finished) {
        //TODO arreglar parametros....idKakuro??
        ccd.guardar_partida(Usuario,IDPartida,contents,idKakuroEnJuego, time,finished);
       
    }

    String[][] resolverKakuroEnJuego(int idKaKuroEnJuego) {
        return ccd.ShowSolution(String.valueOf(idKaKuroEnJuego));
    }

    int comprobarMovimiento(int x, int y, String value, String[][] gridContentsMatrix) {
        return ccd.comprobarMovimiento(x,y,value,gridContentsMatrix); //To change body of generated methods, choose Tools | Templates.
    }

    int getKakuroDePartida(String IDPartida) {
        return ccd.getKakuroDePartida(Usuario,IDPartida);
    }

    String[][] getTableroDePartida(String IDPartida) {
        return ccd.cargar_partida(Usuario,IDPartida);
    }   

    void setMap() {
        ccd.setMap();
    }

    int setTimeDePartida(String IDPartida) {
        return ccd.getTimeDePartida(Usuario,IDPartida);
    }
    
}
