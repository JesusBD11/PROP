/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.clases;

import java.io.Serializable;

/**
 * @file Casilla.java
 * @date 20-12-2020
 * @author Jesus Benitez
 * @brief Archivo que implementa la clase Casilla
 * 
 *
 */

/**
 * @class Casilla
 * @brief Clase que representa una Casilla de un tablero de un Kakuro.
 */
public class Casilla implements Serializable{
    
    enum Tipo{
        BLANCA,
        NEGRA
    }
    
    private Tipo tipo;
    private int valor;
    private int valorAux; //este valor solo se usa para casillas negras con valor de fila y columna
    private boolean horizontal;
    private boolean vertical;
    private int X;
    private int Y;
    /** 
    * @brief Inicializa los atributos de la casilla
    * 
    * @post tipo=Blanca, horizontal y vertical = false, valor y valorAux = 0.
    */
    public Casilla(){
        tipo = Tipo.BLANCA;
        horizontal = false; 
        vertical = false;
        valor = 0;
        valorAux = 0;
    }
    /** 
    * @brief Inicializa los atributos de esta casilla con los atributos de la casilla pasada como parametro.
    * @param c = Una casilla no nula
    * @post La casilla actual es una copia de la casilla pasada como parametro
    */
    public Casilla(Casilla c)
    {
        tipo = c.tipo;
        horizontal = c.horizontal;
        vertical = c.vertical;
        valor = c.valor;
        valorAux = c.valorAux;
        X = c.X;
        Y = c.Y;
    }
    /** 
    * @brief Inicializa los atributos de esta casilla con los atributos pasados como parametro.
    * @param t = true si la casilla es blanca o falso si es negra.
    * @param h = indica si la casilla es horizontal.
    * @param v = indica si la casilla es vertical.
    * @param val = Indica el valor de la casilla.
    * @param valAux = Indica el valor auxiliar de la casilla.
    * @post Los atributos de esta casilla son iguales a los atributos pasados como parametro.
    */
    public Casilla(boolean t, boolean h, boolean v, int val, int valAux){
        if (t) tipo = Tipo.BLANCA;
        else tipo = Tipo.NEGRA;
        horizontal = h; 
        vertical = v;
        valor = val;
        valorAux = valAux;
    }
    
    /** 
    * @brief Modifica el tipo de la casilla.
    * @param t = true indica que la casilla es blanca y false que es negra. 
    * @post La casilla modifica el tipo de acuerdo al parametro pasado.
    */
    public void setTipo(boolean t){ tipo=(t)?Tipo.BLANCA:Tipo.NEGRA; }
    /** 
    * @brief Modifica el booleano horizontal de la casilla.
    * @param h = indica si la casilla es horizontal o no. 
    * @post La casilla modifica el booleano horizontal de acuerdo al parametro pasado.
    */
    public void setHorizontal(boolean h){ horizontal=h;}
    /** 
    * @brief Modifica el booleano vertical de la casilla.
    * @param v = indica si la casilla es vertical o no. 
    * @post La casilla modifica el booleano vertical de acuerdo al parametro pasado.
    */
    public void setVertical(boolean v){ vertical=v;}
    /** 
    * @brief Modifica el valor de la casilla.
    * @param val = indica el nuevo valor de la casilla. 
    * @post La casilla modifica el valor de acuerdo al parametro pasado.
    */
    public void setValor(int val){ valor=val; }
    /** 
    * @brief Modifica el valor auxiliar de la casilla.
    * @param val = indica el nuevo valor auxiliar de la casilla. 
    * @post La casilla modifica el valor auxiliar de acuerdo al parametro pasado.
    */
    public void setValorAux(int val){ valorAux=val; }
    /** 
    * @brief Modifica el atributo X de la casilla.
    * @param x = indica el nuevo valor de X de la casilla. 
    * @post La casilla modifica el valor de X de acuerdo al parametro pasado.
    */
    public void setX(int x){X = x;}
    /** 
    * @brief Modifica el atributo Y de la casilla.
    * @param y = indica el nuevo valor de Y de la casilla. 
    * @post La casilla modifica el valor de Y de acuerdo al parametro pasado.
    */
    public void setY(int y){Y = y;}
    
    
    /** 
    * @brief Consultora del tipo de la casilla.
    * @post Retorna true si la casilla es blanca y false si es negra.
    */
    public boolean getTipo(){return (tipo==Tipo.BLANCA);}
    /** 
    * @brief Consultora del valor de la casilla.
    * @post Retorna el valor de la casilla.
    */
    public int getValor(){return valor;}
    /** 
    * @brief Consultora del valor auxiliar de la casilla.
    * @post Retorna el valor auxiliar de la casilla.
    */
    public int getValorAux(){return valorAux;}
    /** 
    * @brief Consultora del valor horizontal de la casilla. 
    * @post Retorna si la casilla es horizontal o no.
    */
    public boolean getHorizontal(){return horizontal;}
    /** 
    * @brief Consultora del valor vertical de la casilla.  
    * @post Retorna si la casilla es vertical o no.
    */
    public boolean getVertical(){return vertical;}
    /** 
    * @brief Consultora del valor Y de la casilla. 
    * @post Retorna el atributo Y de la casilla.
    */
    public int getY(){return Y;}
    /** 
    * @brief Consultora del valor X de la casilla. 
    * @post Retorna el atributo X de la casilla.
    */
    public int getX(){return X;}
    
    /** 
    * @brief Transforma la casilla al formato del kakuro. 
    * @post Retorna un string con los datos de la casilla transformados al formato del Kakuro.
    */
    public String toString(){
        String casilla = "";
        if(valor>0 || valorAux>0){
            if(!vertical && !horizontal){
                casilla = String.valueOf(valor);
            }
            else{
                if(vertical){
                    casilla ="C"+ String.valueOf(valor);
                }
                if(horizontal){
                    casilla +="F"+ String.valueOf(valorAux);
                }
            }
        }
        else{
            casilla = (tipo==Tipo.NEGRA)?"*":"?"; 
        }
        return casilla;
    }
    public Casilla fromString(String cell)
    {
        Casilla x = null;
        
                    if (cell.equals("*"))
                    {
                        x = new Casilla(false,false,false,0,0);
                        
                    }
                    else if (cell.equals("?"))
                    {
                        x = new Casilla(true,false,false,0,0);
                        
                    }
                    else if (cell.substring(0,1).equals("F"))
                    {
                        int max = Integer.parseInt(cell.substring(1));
                        x = new Casilla(false,true,false,0,max);
                        
                    }
                    else if (cell.substring(0,1).equals("C"))
                    {
                        if (cell.length() > 3)
                        {
                            String[] valores = cell.substring(1).split("F");
                            int maxf = Integer.parseInt(valores[1]);
                            int maxc = Integer.parseInt(valores[0]);
                            x = new Casilla(false,true,true,maxc,maxf);
                            
                        }
                        else
                        {
                            int max = Integer.parseInt(cell.substring(1));
                            x = new Casilla(false,false,true,max,0);
                            
                        }
                    }
                    else
                    {
                        x = new Casilla(true,false,false,Integer.parseInt(cell),0);
                    }
        return x;
    }
}
