/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @file VistaPrincipal.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief Vista Principal del programa.
 * 
 * Vista que se muestra nada más acabar el inicio de sesión y que se encarga de mostrar todas las funcionalidades principales del programa
 */
 
 /**
 * @class VistaPrincipal
 * @brief Clase que inicializa todos los paneles de la vista, además de gestionar el cambio entre paneles y su correcto funcionamiento. también transmite y recibe las solicitudes
 *  de datos y sus resultados para los paneles que gestiona.
 */

public class VistaPrincipal {

    /**
     * Creates new form VistaPrincipal
     */
    private CtrlPresentacion presentationCtrl;
    private JFrame frameVista = new JFrame("Vista Principal");
    private JPanel panelContenidos = new JPanel();
    private JPanel juego,juego2,crearK,selectK;
    private boolean cambioVistaJuego = true;
    private boolean kakuroEditionMode = false;
    private boolean playAfterEdition = false;
    private Dimension defaultDimension= new Dimension(400,400);
    private String User;
    private HashMap<String,JPanel> listaVistas;
    private String [][] tablero;
    private int ultimoKakuroGuardado,idKaKuroEnJuego,tiempo;
    private JPanel cargarP;
    private JPanel ranking;
    VistaPrincipal(CtrlPresentacion ctrl,String Usuario) {
        presentationCtrl = ctrl;
        User = Usuario;
        tablero = new String[3][3];
        ultimoKakuroGuardado = -1;
        
        idKaKuroEnJuego = -1;
    }
   
    void hacerVisible() {
        frameVista.pack();
        inicializar_frameVista();
        frameVista.setVisible(true);
    }
    private void inicializar_frameVista() {
        
    listaVistas = new HashMap<String,JPanel>();
    frameVista.setMinimumSize(defaultDimension);
    frameVista.setPreferredSize(frameVista.getMinimumSize());
    frameVista.setResizable(true);
    frameVista.setLocationRelativeTo(null);
    frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    panelContenidos = new JPanel(new CardLayout());
    
    JPanel menuPrincipal = new VistaMenuPrincipal(this);
    JPanel partida = new VistaPartida(this);
    juego = new VistaKakuro(this);
    juego2 = new VistaKakuro(this);
    JPanel nuevaP = new VistaNuevaPartida(this);
    selectK = new VistaSeleccionarKakuro(this);
    crearK = new VistaCrearKakuro(this);
    cargarP = new VistaCargarPartida(this);
    ranking = new VistaRanking(this);
    panelContenidos.add(menuPrincipal,"menu");
    panelContenidos.add(partida,"partida");
    panelContenidos.add(juego,"juego");
    panelContenidos.add(juego2,"juego2");
    panelContenidos.add(nuevaP,"nuevaP");
    panelContenidos.add(cargarP,"cargarP");
    panelContenidos.add(selectK,"select");
    panelContenidos.add(crearK,"crear");
    panelContenidos.add(ranking,"ranking");
    listaVistas.put("menu",menuPrincipal);
    frameVista.add(panelContenidos);
    setUsuario(User);
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
    public void changeToMenu() {
        System.out.println("Changing to Menu");
        frameVista.setSize(defaultDimension);
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"menu");
    }
     
    public void changeToPartida() {
        System.out.println("Changing to partida");
        frameVista.setSize(defaultDimension);
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"partida");
        
    }   
    public void changeToJuego() {
        System.out.println("Changing to juego");
        int dimensiones = 9;
        //int frameSize = (int)((dimensiones+1)*100)/2;
        frameVista.setSize(new Dimension(800,500));
        if(cambioVistaJuego){
            panelContenidos.remove(juego2);
            juego2 = new VistaKakuro(this);
            panelContenidos.add(juego2,"juego2");
            VistaKakuro vJuego = (VistaKakuro) juego;  
            vJuego.displayKakuro(dimensiones,kakuroEditionMode,playAfterEdition,tablero);
            vJuego.setIniTime(tiempo);
            CardLayout cl = (CardLayout)(panelContenidos.getLayout());
            cl.show(panelContenidos,"juego");
            cambioVistaJuego = false;
        }
        else{
            panelContenidos.remove(juego);
            juego = new VistaKakuro(this);
            panelContenidos.add(juego,"juego");
            VistaKakuro vJuego = (VistaKakuro) juego2;  
            vJuego.displayKakuro(dimensiones,kakuroEditionMode,playAfterEdition,tablero);
            vJuego.setIniTime(tiempo);
            CardLayout cl = (CardLayout)(panelContenidos.getLayout());
            cl.show(panelContenidos,"juego2");
            cambioVistaJuego = true;
        }
        tiempo = 0;
    }

    public void changeToCargar() {
        System.out.println("Changing to cargar partida");
        panelContenidos.remove(cargarP);
        cargarP = new VistaCargarPartida(this);
        panelContenidos.add(cargarP,"cargarP");
        frameVista.setSize(defaultDimension);
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"cargarP");
    }

    public void changeToPerfiles() {
        System.out.println("Cambiand a vista log in (solo por el momento...)");
        frameVista.dispose();
        presentationCtrl.sincronizacionVistaPrincipalAPerfiles();
    }

    public void changeToNuevaPartida() {
        System.out.println("Cambiando a vista Nueva Partida");
        frameVista.setSize(defaultDimension);
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"nuevaP");
    }

    public void changeToSeleccionarKakuro() {
        System.out.println("Cambiando a vista seleccionar Kakuro");
        frameVista.setSize(defaultDimension);
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"select");
    }
    public void changeToCrearKakuro() {
        System.out.println("Cambiando a vista crear Kakuro");
        frameVista.setSize(defaultDimension);
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"crear");
    }

    public void changeToRanking() {
        System.out.println("Cambiando a vista Ranking");
        frameVista.setSize(defaultDimension);
        panelContenidos.remove(ranking);
        ranking = new VistaRanking(this);
        panelContenidos.add(ranking,"ranking");
        CardLayout cl = (CardLayout)(panelContenidos.getLayout());
        cl.show(panelContenidos,"ranking");
    }
    
    public void desactivar() {
        frameVista.setEnabled(false);
    }
    
    public void activar() {
        frameVista.setEnabled(true);
    }
    
    public CtrlPresentacion getCtrlPresentacion(){
        return presentationCtrl;
    }

    void changeToCrearKakuroManual() {
        System.out.println("Cambiando a vista juego con modo edicion");
        kakuroEditionMode = true;
        changeToJuego();
        kakuroEditionMode = false;
    }

    void playAfterEdition(boolean b) {
        playAfterEdition = b;
    }
    public String getUsuario(){return User;}
    public void setUsuario(String Usuario)
    {
        User = Usuario;
        VistaMenuPrincipal v = (VistaMenuPrincipal)(listaVistas.get("menu"));
        v.setUsuario(User);
    }
    public String[] CargarIDs() {       
        String[] IDs = presentationCtrl.CargarIDs(User);
        return IDs;
        
    }
    public String[] get_lista_kakuro()
    {
        String[] res = presentationCtrl.get_lista_kakuro();
        return res;
    }
    public String getRanking()
    {
        String res = presentationCtrl.ShowRanking();
        return res;
    }
    
    public void setKakuroenjuego(int id) {
        idKaKuroEnJuego =id;
        tablero = presentationCtrl.getkakuroenjuego(id);
        System.out.println("imprimiendo kakuro");
        for (int i = 0; i < tablero.length; ++i) {
            for (int j = 0; j < tablero.length; ++j) {
                
                System.out.print(tablero [i][j]+",");
            }
            System.out.print("\n");
        }
        
    }

    public void setTableroWithDimensions(int dim){
        tablero = new String [dim][dim];
    }
    
    int generarKakuro(int dificultad) {
        int id = presentationCtrl.generarKakuro(dificultad);
        resetListaKakuro();
        return id;
    }
    
    int generarKakuro(int dimensiones, int numCasillasNegras) {
        int id = presentationCtrl.generarKakuro(dimensiones,numCasillasNegras);
        resetListaKakuro();
        return id;
    }

    
    String generarKakuroManual(String[][] gridContentsMatrix) {
        String id = presentationCtrl.generarKakuroManual(gridContentsMatrix);
        resetListaKakuro();
        return id;
    }

    void setUltimoKakuroGuardado(int idKakuroGuardado) {
        ultimoKakuroGuardado = idKakuroGuardado;
        VistaCrearKakuro ck = (VistaCrearKakuro)crearK;
        ck.setUltimoKakuroLabel(idKakuroGuardado);
    }
    int getUltimoKakuroGuardado() {
        return ultimoKakuroGuardado;
    }
    private void resetListaKakuro() {
        panelContenidos.remove(selectK);
        selectK = new VistaSeleccionarKakuro(this);
        panelContenidos.add(selectK,"select");
    }

    void guardarPartida(String IDPartida, String[][] contents, int time, boolean finished) {
        presentationCtrl.guardarPartida(IDPartida,contents,time,idKaKuroEnJuego, finished);
    }

    String[][] resolverKakuroEnJuego() {
        return presentationCtrl.resolverKakuroEnJuego(idKaKuroEnJuego);
    }

    int comprobarMovimiento(int x, int y, String value, String[][] gridContentsMatrix) {
        return presentationCtrl.comprobarMovimiento(x,y,value,gridContentsMatrix);
    }

    void setKakuroDePartida(String IDPartida) {
        int id = presentationCtrl.getKakuroDePartida(IDPartida);
        idKaKuroEnJuego = id;
    }

    void setTableroDePartida(String IDPartida) {
        String[][] grid = presentationCtrl.getTableroDePartida(IDPartida);       
        tablero = grid;
        System.out.println("setTableroPartida: "+grid.length);
    }
    void setTimeDePartida(String IDPartida) {
        tiempo = presentationCtrl.setTimeDePartida(IDPartida);      
        
    }

    void setDificultadCrearKakuro(String dif) {
         VistaCrearKakuro ck = (VistaCrearKakuro)crearK;
         ck.setDificultadLabel(dif);
    }
}
