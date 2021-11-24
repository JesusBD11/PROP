/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.clases.partida;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @file CtrlPartida.java
 * @date 20-12-2020
 * @author Mauro Garcia
 * @brief Archivo que implementa la lectura y escritura de partidas en disco.
 * 
 *
 */

/**
 * @class CtrlPartida
 * @brief Clase que implementa la lectura y escritura de partidas en disco.
 */
public class CtrlPartida {
    private HashMap<String,ArrayList<partida>> listaPartidas;
    
    
     /** 
    * @brief Contructora vacía de CtrlPartida
	* 
	* @post Crea una instancia vacía de CtrlPartida.
	*/
    public CtrlPartida() {
        listaPartidas = new HashMap<String,ArrayList<partida>>();
       
    }
    
     /** 
    * @brief Obtiene el mapa de todas las partidas guardadas en el sistema
	* 
	* @post se devuelve el mapa.
	*/
    public HashMap<String,ArrayList<partida>> getMap() {
        try{
            
            listaPartidas = (HashMap<String,ArrayList<partida>>) deserializarPartida("."+File.separator+"Data"+File.separator+"DatosPartidas.ser");
            
        }
        
        
        catch(Exception e){
            System.out.println("Something went wrong while casting object");
        }
        return listaPartidas;
    }
    
     /** 
    * @brief Actualiza la base de datos de partidas con el mapa introducido por parámetro
	* @param partidas = Mapa con todas las partidas de todos los usuarios que será la nueva información de las partidas	
	* @post tenemos un archivo serializado DatosPartidas en la carpeta de persistencia del programa con la información de partidas.
	*/
    public void setMap(HashMap<String,ArrayList<partida>> partidas) {
    
        try {
        
            serializarPartida(partidas, "."+File.separator+"Data"+File.separator+"DatosPartidas.ser");
        }
        catch(Exception e){
            System.out.println("Something went wrong while serializing object");
        }
    }
    
     /** 
    * @brief Dados un objecto y un nombre de archivo, crea un archivo .ser con el nombre fileName y el objecto o  como contenido
	* @param o = Objeto a serializar y guardar
        * @param fileName = nombre del archivo donde se guardará el objeto
	* @post Existe un archivo con el nombre fileName en el almacenamiento del sistema con la información serializada de o.
	*/
    private static void serializarPartida(Object o,String fileName){
         try{    
                //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(fileName); 
            ObjectOutputStream out = new ObjectOutputStream(file); 

            // Method for serialization of object 
            out.writeObject(o); 

            out.close(); 
            file.close(); 
            System.out.println("Object has been serialized"); 
  
        } 
        catch(IOException ex) { 
            
            System.out.println("IOException is caught"); 
            ex.printStackTrace();
        } 
    }
    
    
  
    
    
    /** 
    * @brief Dado un nombre de archivo, lee el archivo serializado fileName, deserializa sus datos y crea el objeto correspondiente.	
        * @param fileName = nombre del archivo del que se extraerá el objeto
	* @post Se devuelve el objeto contenido por el archivo fileName ya deserializado.
	*/
    private static Object deserializarPartida(String fileName){
        Object objectFromFile = null;
         try{    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(fileName); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            objectFromFile = in.readObject(); 
              
            in.close(); 
            file.close(); 
            System.out.println("Object has been deserialized "); 
        } 
         
         catch(FileNotFoundException ex) 
        { 
            System.out.println("File not found"); 
        } 
         
         catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        }
          catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        }
        return objectFromFile;
    }
}
