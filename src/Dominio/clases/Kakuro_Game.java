/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.clases;

import Dominio.controladores.CtrlCapaDominio;
import Presentacion.CtrlPresentacion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @file Kakuro_Game.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief Archivo principal del programa
 * 
 *
 */

public class Kakuro_Game {

    /**
     * @param args the command line arguments
     */
    private static Scanner in; 
    private static int option;
    
    public static void main(String[] args){
       
        javax.swing.SwingUtilities.invokeLater (
            new Runnable() {
             public void run() {
               CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
               ctrlPresentacion.inicializarVistaPerfiles();
        }}); 
       
        
        
       
        /*
    InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);	
        String dif = "";
        CtrlCapaDominio c = new CtrlCapaDominio();
        int ID = 0;
        String ini = "Bienvenido " + "Introduzca la opcion que desea ejecutar"
         			+"\n1 - Generar Kakuro \n2 - Generar kakuro y resolver\n3 - Introducir Kakuro para validar\n" +
                                "4 - Crear Kakuro con restricciones\n5 - Jugar\n6 - Resolver Kakuro del Juego de Pruebas";
        
        in = new Scanner(System.in);
        option = 1;
        
        while (option != 0)
        {
            System.out.println(ini);
            option = in.nextInt();
            switch(option)
            {
                case 1://Generar kakuro
                    
                    System.out.println("introduzca la dificultad (1 = facil, 2= normal, 3 = dificil");
                    try {
             			dif = br.readLine();
             		} catch (IOException e) {
             			
             			e.printStackTrace();
             		}
                    c.crearDominioKakuro(option,Integer.parseInt(dif));
                    break;
                case 2:
                    
                    System.out.println("introduzca la dificultad (1 = facil, 2= normal, 3 = dificil");
                    try {
             			dif = br.readLine();
             		} catch (IOException e) {
             			
             			e.printStackTrace();
             		}
                    c.crearDominioKakuro(option,Integer.parseInt(dif));
                    break;
                   
                case 3://Introducir Manualmente Kakuro y validar
                   
                    c.crearDominioKakuro(option, -1);
                    break;
                case 4://Crear con restricciones
                     c.crearDominioKakuro(option, -1);
                     break;             
                case 5://Jugar partida
                    System.out.println("introduzca un identificador (debe ser mayor o igual a 1 y menor o igual a 5)");
                    try {
             			dif = br.readLine();
             		} catch (IOException e) {
             			
             			e.printStackTrace();
             		}
                    ID = Integer.parseInt(dif);
                    c.crearDominioJugar(ID,"Jesus");
                    break;
                case 6://Kakuros del juego de pruebas
                    System.out.println("introduzca un identificador del juego de pruebas (debe ser mayor o igual a 1 y menor o igual a 5)");
                    try {
             			dif = br.readLine();
             		} catch (IOException e) {
             			
             			e.printStackTrace();
             		}
                    ID = Integer.parseInt(dif); 
                    c.crearDominioKakuro(option,ID);
                    break;
                default: option = 0;

            }
        }
        
        
         */
    }
    
    
    
    
    
    
    
   
}
