/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @file CtrlRanking.java
 * @date 20-12-2020
 * @author Jesus Benitez
 * @brief Archivo que implementa la lectura y escritura del Ranking en disco.
 * 
 *
 */

/**
 * @class CtrlRanking
 * @brief Clase que implementa la lectura y escritura del Ranking en disco.
 */
public class CtrlRanking {
    //private ranking rk;
    
    /** 
    * @brief Constructora vacía de CtrlRanking 	    
	* @post tenemos un objeto vacío CtrlRanking
	*/
    public CtrlRanking()
    {
        //rk = new ranking();
        //rk.cargar_ranking();
    }
    
    
    
    /** 
    * @brief cambiamos el ranking de la carpeta de persistencia por el mapa pasado por parámetro	
        * @param rank = ranking a guardar en persistencia
	* @post El ranking de persistencia pasa a ser rank.
	*/
    public void setRanking(HashMap<String,Double>rank) {
        
        
        String contenido = "";
        
        for (Map.Entry<String, Double> entry : rank.entrySet()) {
            
            contenido += entry.getKey() + "," + entry.getValue() + "\n";
            
           
        }
        
        try {
            String ruta = "."+File.separator+"Data"+File.separator+"ranking.txt";
            File file = new File(ruta);
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);



            bw.write(contenido);
            bw.close();
        }
        
        catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }
    
    /** 
    * @brief Devuelve el mapa que representa el ranking guardado en persistencia	        
	* @post Se devuelve el mapa que representa el ranking guardado en persistencia
	*/
    public HashMap<String, Double> cargar_ranking() {
    
        
        HashMap<String, Double> rank = new HashMap<String, Double>();
        
        FileReader my_kakuro = null;
        //contenido = "";
        try{
        
            File file = new File("."+File.separator+"Data"+File.separator+"ranking.txt");
            my_kakuro = new FileReader(file);
            BufferedReader br = new BufferedReader(my_kakuro);
            String line;
            String [] tokens;  
            
            while ((line = br.readLine()) != null) {
            
                 tokens = line.split(",");
                 double d = Double.parseDouble(tokens[1]);
                 
                 rank.put(tokens[0], d);
                 
            }
            my_kakuro.close();
            //sortByScore();
        }
         catch(Exception e){
            System.out.println("Reading File Failed");
            System.out.println(e.toString());
        }
        
       return rank; 
    
    }
    
    
}
