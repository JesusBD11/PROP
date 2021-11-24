/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.clases.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @file CtrlPersistencia.java
 * @date 20-12-2020
 * @author Jesus Benitez
 * @brief Archivo que implementa el controlador de la capa de persistencia.
 * 
 *
 */

/**
 * @class CtrlPersistencia
 * @brief Clase que se encarga de recibir consultas y enviar datos a las demás capas de proyecto. éstas consultas las llevará a la clase indicada y devolverá su resultado.
 */

public class CtrlPersistencia {
    
    private CtrlKakuro ck;
    private CtrlPartida cp;
    private CtrlRanking cran;
    private CtrlRegistro creg;
    
    /**
     * @brief Creadora de CtrlPersistencia
     * 
     * @post Crea una instancia de CtrlPersistencia y de todos los controladores de persistencia que éste utiliza para guardar y cargar datos.
     * 
     */

    public CtrlPersistencia () {
    
        ck = new CtrlKakuro();
        cp = new CtrlPartida();
        cran = new CtrlRanking();
        creg = new CtrlRegistro();
    }
    //funciones para registro
    /**
    * @brief Pide al controlador de persistencia de Registro que busque los datos de registro para pasarlos a la capa de dominio
    * 
    * @post devuelve un HashMap con todos los datos de resgistro
    * 
    * */

    public HashMap <String, String> getRegistro() {
    
        return creg.cargar_registro();
    }
    /**
     * @brief Pide al controlador de persistencia de Registro que actualice los datos de registro de Usuarios de la aplicacion
     * 
     * @param p = guarda los datos de los Usuarios
     * 
     * @post se guardaran los datos de p en un txt
     * 
     * */
    public void setRegistro(HashMap <String, String> r) {
    
        creg.guardar_registro(r);
    }
    
    //funciones para ranking
    /** 
    * @brief Pide al controlador de persistencia de Ranking que devuelva el mapa con la información del ranking guardado en persistencia.
	* @post Se devuelve el ranking guardado en persistencia.
	*/
    public HashMap <String, Double> getRanking() {
    
        return cran.cargar_ranking();
    }
    /** 
    * @brief Se pide al controlador de persistencia de ranking que cambie el ranking de la carpeta de persistencia por el mapa pasado por parámetro	
        * @param rank = ranking a guardar en persistencia
	* @post El ranking de persistencia pasa a ser rank.
	*/
    public void setRanking(HashMap <String, Double> r) {
    
        cran.setRanking(r);
    }
    
    
    //funciones para kakuro
    /**
    * @brief Se pide al controlador de persistencia de Kakuros que busque todos los Ids de los kakuros guardados
    * 
    * @post almacena los Ids en vector de Strings y lo devuelve
    * 
    * */

    public String[] getlistaKakuro() {
    
        return ck.get_lista_kakuro();
    }
     /**
     * 
     * @brief Se pide al controlador de persistencia de Kakuros que añade un kakuro a la biblioteca en formato .txt
     * 
     * @param k = Es el tablero del kakuro que se quiere guardar
     * 
     * @post devuelve el Id con el que se ha guardado el Kakuro
 
     * 
     * */

    public int anadir_kakuro(Casilla [][] k) {
        
        return ck.anadir_kakuro(k);   
                
    }
     /**
     * 
     * @brief Se pide al controlador de persistencia de Kakuros que Lea un kakuro desde un archivo .txt 
     * 
     * @param id = es el Id del kakuro que queremos leer
     * @post devuelve el Kakuro correspondiente al id en caso de que exista.
    
     * */ 

    public Kakuro read_kakuro_file(int id, int option) {
    
        if (option == 0) return ck.read_kakuro_file(id, "."+File.separator+"Data"+File.separator+"Biblioteca"+File.separator+"kakuro");
        else return ck.read_kakuro_file(id, "."+File.separator+"Data"+File.separator+"tests"+File.separator+"testCapaDominioKakuro"+File.separator+"kakuro");
    }
    
    //funciones para partida
    /** 
    * @brief Se pide al controlador de persistencia de partida que devuelva el mapa con la información de todas las partidas guardadas del programa.
	* 
	* @post se devuelve el mapa con la información de las partidas del programa.
	*/
    public HashMap<String,ArrayList<partida>> getMap() {
    
        return cp.getMap();
    }
    /** 
    * @brief Se pide al controlador de persistencia de partida que actualice la información de todas las partidas guardadas del programa con el mapa pasado por parámetro.
	* @param p = mapa con la información que se usará para actualizar la base de datos.
	* @post tenemos actualizada la información de persistencia correspondiente a las partidas guardadas por usuarios.
	*/
    public void setMap( HashMap<String,ArrayList<partida>> p) {
    
        cp.setMap(p);
    }
}
