/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.clases;


import java.io.Serializable;
import java.util.Stack;
import java.util.Vector;

import java.util.concurrent.ThreadLocalRandom;
/**
 * @file Kakuro.java
 * @date 20-12-2020
 * @author Mauro Garcia
 * @brief Archivo que implementa la clase Kakuro
 * 
 *
 */

/**
 * @class Kakuro
 * @brief Clase que representa un kakuro. Contiene su tablero tanto información sobre el mismo.
 */
public class Kakuro implements Serializable{
   private int filas;
   private int columnas;
   private int celdas_blancas;
   private int celdas_negras;   
   //Dificultad dificultad;
//   HOLA     hola
   private int ID_Kakuro;
   private Casilla [][]kakuro;   
   private double dificultad;
   /*ESTADOS
    CELDA BLANCA VACIA = ?
    CELDA BLANCA LLENA = numero
    CELDA NEGRA = *
    CELDA NEGRA CON SUMA = (C/F)numero || CnumeroFnumero
    
   
    que valores en una celda pueden violar las restricciones del puzle?
        -Si LimiteFila < SumaFila + ValorCelda o si LimiteColumna < SumaColumna + ValorCelda
        -Si ValorCelda ya existe en la fila o la columna
   */
   /**
    * @brief Constructora de kakuro
    * 
    * @post crea un kakuro null
    * */
   
   public Kakuro(){}
   
   /**@brief Constructora de Kakuro
    * @param c_blancas = numero de casillas blancas del kakuro
    *  @param c_megras = numero de casillas negras del kakuro
    *  @param k = matriz de casillas que represnta el tablero
    * 
    * 
    * @post Se creará un kakuro con esos parámetros
    * */
   
   public Kakuro(int c_blancas,int c_negras,Casilla [][] k){
       celdas_blancas = c_blancas;
       celdas_negras = c_negras;
       kakuro = k;
       filas = kakuro.length;
       columnas = kakuro[0].length;     
       
   }
   
    /**
    * @brief Modifica el valor de celdas_blancas
    * @param b = nuevo valor de celdas_blancas
    * @post celdas_blancas = b
    * 
    * */
   public void setBlancas (int b){celdas_blancas = b;}
   //Consultoras
   
   /**
    * @brief Consultora del las filas del kakuro
    * 
    * @post Devuelve el numero de filas del kakuro
    * 
    * */
   public int getFilas(){return filas;}
   
   /**
    * @brief Consultora del las columnas del kakuro
    * 
    * @post Devuelve el numero de columnas del kakuro
    * 
    * */
   
   public int getColumnas(){return columnas;}
   
   
   /**
    * @brief Consultora del tablero del kakuro
    * 
    * @post Devuelve una matriz de casillas que representa el  kakuro
    * 
    * */
   public Casilla[][] getTablero(){return kakuro;}
   
    /**
    * @brief Consultora del Identificador del kakuro
    * 
    * @post Devuelve el ID del kakuro
    * 
    * */
   
   public int getID_Kakuro(){return ID_Kakuro;}
   /**
    * @brief Modificadora del ID de Kakuro
    * @param id = nuevo identificador
    * @post ID modificado
    * 
    * */
   public void setID_Kakuro(int id){ID_Kakuro = id;}
    /**
    * @brief Consultora de las soluciones del kakuro
    * 
    * @post Devuelve un vector con las soluciones del kakuro
    * 
    * */
  
   
   /**
    * @brief Consultora del numero de celdas blancas del kakuro
    * 
    * @post Devuelve el numero de celdas blancas del kakuro
    * 
    * */
   public int getceldas_blancas(){return celdas_blancas;}
   
   /**
    * @brief Consultora del numero de celdas negras del kakuro
    * 
    * @post Devuelve el numero de celdas negras del kakuro
    * 
    * */
   public int getceldas_negras(){return celdas_negras;}
   public void printGrid()
   {
       String[][] info = new String[filas][columnas];
       String size = filas+","+columnas;
       System.out.println(size+"\n");
       for (int i = 0; i < filas; i++)
       {
           for (int j = 0; j < columnas; j++)
           {
               if (j < columnas - 1) System.out.println(kakuro[i][j].toString()+",");
               System.out.println(kakuro[i][j].toString()+"\n");
           }          
        }
      
    }
   
    public void StringtoGrid(String k)
    {
        String[] tokens = k.split("\n");
        
        for (int i = 0; i < tokens.length; i++)
        {
                String[] linea = tokens[i].split(",");
                for(int j=0;j<tokens[i].length();j++)
                {
                    if (linea[j].equals("*"))
                    {
                        kakuro[i][j] = new Casilla(false,false,false,0,0);
                        
                    }
                    else if (linea[j].equals("?"))
                    {
                        kakuro[i][j] = new Casilla(true,false,false,0,0);
                       
                    }
                    else if (linea[j].substring(0,1).equals("F"))
                    {
                        int max = Integer.parseInt(tokens[i].substring(1));
                        kakuro[i][j] = new Casilla(false,true,false,0,max);
                        
                    }
                    else if (linea[j].substring(0,1).equals("C"))
                    {
                        if (linea[j].length() > 3)
                        {
                            String[] valores = tokens[i].substring(1).split("F");
                            int maxf = Integer.parseInt(valores[1]);
                            int maxc = Integer.parseInt(valores[0]);
                            kakuro[i][j] = new Casilla(false,true,true,maxc,maxf);
                           
                        }
                        else
                        {
                            int max = Integer.parseInt(tokens[i].substring(1));
                            kakuro[i][j] = new Casilla(false,false,true,max,0);
                           
                        }
                    }
                    else
                    {
                        int value = Integer.parseInt(tokens[i]);
                        kakuro[i][j] = new Casilla(true,false,false,value,0);
                        
                    }
                }
               
        }
       
    }
     public void setDif(double dif){dificultad = dif;}
     public double getDif(){return dificultad;}
}