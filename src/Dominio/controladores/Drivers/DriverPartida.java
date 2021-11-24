/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.controladores.Drivers;

import Dominio.clases.Kakuro;
import Dominio.clases.partida;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author perem
 */
public class DriverPartida extends partida{
    private static partida testConstructorVacio()
    {
       
        return new partida();
    }
     private static partida testConstructor(String line)
    {
       String[] tokens = line.split(",");
       Kakuro k = new Kakuro();
       int time = Integer.parseInt(tokens[0]);
        return new partida(tokens[0],tokens[1],k,time);
    }
    private static void testConstructoras()
    {
         try
            {
                File f = new File("."+File.separator+"Data"+File.separator+"tests"+File.separator+"testPartida"+File.separator+"testBuild.txt");
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = "";
                while ((line = br.readLine()) != null)
                {
                    partida p = testConstructorVacio();
                    System.out.println("Constructor vacio comprobado");
                    partida algo = testConstructor(line);
                    System.out.println("Constructor lleno comprobado");
                    testGetters(algo);
                }

            }
            catch(Exception e)
            {
                System.out.println("Fallo en las Constructoras");
                e.printStackTrace();
            }
    }
    private static void testSetters(partida test)
    {
         try
            {
                File f = new File("."+File.separator+"Data"+File.separator+"tests"+File.separator+"testPartida"+File.separator+"testSetters.txt");
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = "";
                while ((line = br.readLine()) != null)
                {
                    String[] tokens = line.split(",");
                    test.setIdPartida(tokens[0]);
                    System.out.println("ID nuevo: "+tokens[0]);
                    test.setTime(Integer.parseInt(tokens[1]));
                    System.out.println("tiempo nuevo: "+tokens[1]);
                    System.out.println("Setters ejecutados correctamente");
                }

            }
            catch(Exception e)
            {
                System.out.println("Fallo en los setters");
                e.printStackTrace();
            }
    }
    private static void testGetters(partida test)
    {
        System.out.println("IDPartida: "+test.getIDPartida());
        System.out.println("IDUsuario: "+test.getID());
        System.out.println("tiempo: "+test.getTime());
        System.out.println("Kakuro: ");
        testSetters(test);
    }
    public static void main(String [] args)
    {
        System.out.println("iniciando prueba");        
        testConstructoras();
        
    }

    
}
