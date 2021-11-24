/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.controladores.Drivers;
import Dominio.clases.*;
import Dominio.controladores.*;
import Persistencia.CtrlPersistencia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
/**
 *
 * @author David Nogales
 */
public class DriverCapaDominioKakuro extends CapaDominioKakuro{
    
   DriverCapaDominioKakuro()
   {
       super();
   }
    public static void main(String [] args)
        {
            DriverCapaDominioKakuro dk = new DriverCapaDominioKakuro();
            try
            {
                
                dk.testSolve();
                System.out.println("_________");
                dk.testGenerar();
                System.out.println("_________");
                dk.testGenerarResolver();
                System.out.println("_________");
                dk.testValidarKakuro();
                System.out.println("_________");
                System.out.println("tests finalizados");
            }
            catch (Exception e)
            {
                 System.out.println("Error en los tests");
                e.printStackTrace();
            }
           
        }
    /** 
         * @brief Prueba el método generarSumas
	* @param k = matriz de casillas con casillas negras y blancas
	* 
        * @post k contiene el resultado de generarSumas;
	*/
    public static void testGenerarSumas(Casilla[][] k)
    {
         try{
             System.out.println("Inicio de test generarSumas");
           //generarSumas(k);
           printCellMatrix(k);
            System.out.println("testgenerarSumas Superado");            
        }
        catch(Exception e){
            System.out.println("Fallo en testgenerarSumas");
            e.printStackTrace();
            
        }
    }
    /** 
         * @brief Prueba el método generarcasillasBlancas
	* @param k = matriz de casillas con casillas negras
	* 
        * @post k contiene el resultado de generarCasillasBlancas;
	*/
    public static void testGenerarCasillasBlancas(Casilla[][] k)
    {
       
         try{
             System.out.println("Inicio de test generarCasillasBlancas");
             boolean res = generarCasillasBlancas(k);
             if (!res) throw new Exception();
           printCellMatrix(k);
           System.out.println("testGenerarCasillasBlancas Superado");
           testGenerarSumas(k);
        }
        catch(Exception e){
            System.out.println("Fallo en testGenerarCasillasBlancas");
            e.printStackTrace();
            
        }
    }
    /** 
         * @brief Prueba el método generarcasillasNegras
	* @param k = matriz de casillas vacía
	* @param negras = número de casillas negras a generar
        * @post k contiene el resultado de generarCasillasNegras;
	*/
    public static void testGenerarCasillasNegras(Casilla[][] k, int negras)
    {
         try{
             System.out.println("Inicio de test generarCasillasNegras");
           generarCasillasNegras(k,negras);
           printCellMatrix(k);
            System.out.println("testGenerarCasillasNegras Superado");
            testGenerarCasillasBlancas(k);
        }
        catch(Exception e){
            System.out.println("Fallo en testGenerarCasillasNegras");
            e.printStackTrace();
            
        }
    }
    /** 
         * @brief Prueba la funcion de generarKakuro y sus métodos
	* 
	*
	*/
    public static void testGenerarKakuro()
    {
        int filas,columnas;
        double dificultad;
         try{
           System.out.println("Inicio de test generarKakuro");
           String path = "."+File.separator+"Data"+File.separator+"tests"+File.separator+"testCapaDominioKakuro";
           path += File.separator+"testGenerarKakuro.txt";
           BufferedReader br = new BufferedReader(new FileReader(path));
           String line = "";                      
           while((line = br.readLine())!=null)
           {
               //prueba funcion principal generarKakuro
               String[] tokens = line.split(",");
               filas = Integer.parseInt(tokens[0]);
               int f1 = filas;
               columnas = Integer.parseInt(tokens[1]);
               int c1 = columnas;
               dificultad = Double.parseDouble(tokens[2]);
               Casilla[][] prueba = generarKakuro(filas,columnas,dificultad);              
               //prueba funcion1 generarKakuro
               Casilla[][] k = new Casilla[f1][c1];
               int negras = (int)Math.floor((filas-1)*(columnas-1)*dificultad);
               testGenerarCasillasNegras(k,negras);              
           }    
           System.out.println("test GenerarKakuro finalizado");
           
        }
        catch(Exception e){
            System.out.println("Fallo en testGenerarkakuro");
            e.printStackTrace();
            
        }
    }
     /** 
         * @brief Prueba los metodos takeFila y takeColumna
	* 
	* 
	*/
    public static void testtakeFilaColumna()
    {   
        Kakuro k = new Kakuro();
         try{
           String path = "";
           
           System.out.println("Inicio de test takeFila y takeColumna");
           try
           {
                //path = "."+File.separator+"Data"+File.separator+"tests"+File.separator+"testCapaDominioKakuro"+File.separator+"kakuro";
                k = ccd.read_kakuro_file(4,1);
           }
           catch (Exception e)
                   {
                       e.printStackTrace();
                   }
           path = "."+File.separator+"Data"+File.separator+"tests"+File.separator+"testCapaDominioKakuro"+File.separator+"testtakeFilaColumna.txt";
           BufferedReader br = new BufferedReader(new FileReader(path)); 
           String line = "";
           while ((line = br.readLine()) != null)
           {
               String[] tokens = line.split(",");
               int f = Integer.parseInt(tokens[0]);
               int c = Integer.parseInt(tokens[1]);              
               int vactual = Integer.parseInt(tokens[2]);
               Set<Integer> relleno = new HashSet<Integer>(0);
               int res = takeFila(k.getTablero(),vactual,f,c,relleno);
               System.out.println("resultado take fila: "+res);
               try
               {
                res = takeColumna(k.getTablero(),vactual,f,c,relleno);
                System.out.println("resultado takeColumna: "+res);
               }
                catch(Exception e){
                System.out.println("Fallo en takeColumna");
                e.printStackTrace();
                }
           }
         }
                      
        catch(Exception e){
            System.out.println("Fallo en takeFila");
            e.printStackTrace();
        }
       
    }
     /** 
         * @brief Prueba el metodo checkRun
	* 
	*
	*/
    public static void testCheckRun()
    {
        try{
           System.out.println("Inicio de test checkRun");
           String path = "."+File.separator+"Data"+File.separator+"tests"+File.separator+"testCapaDominioKakuro"+File.separator+"testCheckRun.txt";
           BufferedReader br = new BufferedReader(new FileReader(path));
           String line = "";
           while ((line = br.readLine()) != null)
           {
               String[] tokens = line.split(",");
               Boolean llena;
               if (tokens[2].equals("true")) llena = true;
               else llena = false;
               int vactual = Integer.parseInt(tokens[1]);
               int suma = Integer.parseInt(tokens[0]);
               Boolean res = checkRun(suma,vactual,llena);
               System.out.println("resultado checkRun: "+res);
           }
           
        }
        catch(Exception e){
            System.out.println("Fallo en checkRun");
            e.printStackTrace();
        }
    }
    /** 
         * @brief Prueba la función destinada a resolver un Kakuro mediante un juego de pruebas
	* 
	* @post Kakuro tiene su solución guardada en un atributo privado y la muestra por pantalla.
	*/
    
    public static void testSolve()
    {
        Kakuro k;
        
        for (int id = 1; id < 4; ++id)
        {
         try{
           System.out.println("Inicio de test Solver");           
           //BufferedReader br = new BufferedReader(new FileReader(path));
           
           k = ccd.read_kakuro_file(id,1);         
           try{
               boolean result = solve(k, 0);
               if (result) printCellMatrix(getResult());
               System.out.println("test Solver finalizado");
           }
           catch(Exception e)
           {
               System.out.println("Fallo en testSolve");
               testtakeFilaColumna();
               testCheckRun();
           }
           
           
        }
            catch(Exception e){
                System.out.println("Fallo al leer archivo");
                e.printStackTrace();
            }
        }
       
    }
    /** 
         * @brief Prueba la función destinada a generar un Kakuro mediante una dificultad
	* 
	* @post Kakuro tiene su solución guardada en un atributo privado y la muestra por pantalla.
	*/
    public static void testGenerar()
    {
        Kakuro k;
        
         try{
           System.out.println("Inicio de test Generar");
           String path = "."+File.separator+"Data"+File.separator+"tests"+File.separator+"testCapaDominioKakuro"+File.separator+"testGenerar.txt";
           BufferedReader br = new BufferedReader(new FileReader(path));
           String line = "";
           while ((line = br.readLine()) != null)
           {
               generar(Integer.parseInt(line));
           }
           System.out.println("testGenerar superado");
           
        }
        catch(Exception e){
            System.out.println("Fallo en testGenerar");
            testGenerarKakuro();
            e.printStackTrace();
            
        }
       
    }
    /** 
         * @brief Prueba la función destinada a generar un Kakuro mediante una dificultad y resolverlo justo despues
	* 
	* @post se muestra el resultado de generar y resolver por pantalla
	*/
    public static void testGenerarResolver()
    {
        Kakuro k;
        
         try{
           System.out.println("Inicio de test GenerarResolver");
           String path = "."+File.separator+"Data"+File.separator+"tests"+File.separator+"testCapaDominioKakuro"+File.separator+"testGenerarResolver.txt";
           BufferedReader br = new BufferedReader(new FileReader(path));
           String line = "";
           while ((line = br.readLine()) != null)
           {
               generarResolver(Integer.parseInt(line));
           }
           System.out.println("testGenerarResolver superado");
           
        }
        catch(Exception e){
            System.out.println("Fallo en testGenerarResolver");
            e.printStackTrace();
            
        }
       
    }
    /** 
         * @brief Prueba la funcion validarKakuro
	* 
	* @post se muestra el resultado de validar por pantalla en caso de que funcione correctamente
	*/
    public static void testValidarKakuro()
    {
        Kakuro k;
        
         try{
           System.out.println("Inicio de testValidarKakuro");
           //String path = "."+File.separator+"Data"+File.separator+"tests"+File.separator+"testCapaDominioKakuro"+File.separator+"testValidarKakuro";         
           int id = 1;
           k = ccd.read_kakuro_file(id,1);
           boolean res = validar_kakuro(k);
                
           System.out.println("testValidarKakuro superado\nresultado: "+res);
           
        }
        catch(Exception e){
            System.out.println("Fallo en testValidarKakuro");
            e.printStackTrace();
            
        }
    }

}

