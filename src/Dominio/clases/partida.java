/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.clases;


import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * @file partida.java
 * @date 20-12-2020
 * @author Pere Masip
 * @brief Archivo que implementa la clase partida
 * 
 *
 */

/**
 * @class partida
 * @brief Clase que representa una partida. Cada partida consiste en el id del usuario que la guarda, un identificador de partida, el tiempo transcurrido y el Kakuro en juego.
 */
public class partida implements Serializable{
    
    private int tiempo;
    private String ID_partida;
    private String ID_usuario;
    private Kakuro k;
    //HashMap<String,ArrayList<partida>> listaPartidas = new HashMap<String,ArrayList<partida>>();
    //private HashMap<String, List <partida> > listaPartidas;
    
    
    public partida() {
    
        k = null;
        ID_partida = "";
        ID_usuario = "";
        tiempo = 0;
    }
    public partida (Kakuro x, String Usuario) {  
        k = x;
        ID_usuario = Usuario; 
        
    }
    public partida (String Usuario, String ID, Kakuro x, int time) {  
        k = x;
        ID_usuario = Usuario; 
        ID_partida = ID;
        tiempo = time;
    }
    
    public Kakuro getKakuro() {
        return k;
    }
    public void setKakuro(Kakuro kak) {
    k = kak;
    }
    public String getID() {
        return ID_usuario;
    }
    
    public String getIDPartida() {
        return ID_partida;
    }
    
        
    public void setIdPartida(String nom) {
        ID_partida = nom;
    }
    
    public void setNewKakuro(Kakuro kak) {
        k = kak;
    }
    
    public void setTime(int time) {
        tiempo = time;
    }
    public int getTime() {
        return tiempo;
    }
    public void escribir(){
    
        System.out.println(tiempo);
        System.out.println(ID_usuario);
        System.out.println(ID_partida); 
    }
    
    
    
    
    
}
