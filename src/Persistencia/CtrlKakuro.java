/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;
import Dominio.clases.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
/**
 * @file CtrlKakuro.java
 * @date 20-12-2020
 * @author Mauro Garcia
 * @brief Archivo que implementa la lectura y escritura de Kakuros en disco.
 * 
 *
 */

/**
 * @class CtrlKakuro
 * @brief Clase que implementa la lectura y escritura de Kakuros en disco.
 */
public class CtrlKakuro {

    
/**
 * @brief Creadora de CtrlKakuro
 * 
 * @post crea una instancia de CtrlKakuro
 * 
 * */
    
public CtrlKakuro()
{
    
}

/**
 * @brief busca todos los Ids de los kakuros guardados
 * 
 * @post almacena los Ids en vector de Strings y lo devuelve
 * 
 * */
private String facil = "(facil)";
private String normal = "(normal)";
private String dificil = "(dificil)";

public String[] get_lista_kakuro()
{
    String list = "";
    int id = 1;
    Kakuro k;
    String Path = new String("."+File.separator+"Data"+File.separator+"Biblioteca"+File.separator+"kakuro");
    while ((k = read_kakuro_file(id,Path)) != null)
    {
        double dif = k.getDif();
        if (dif >= 0.625) list += id +" "+ facil+",";
        else if (dif < 0.625 && dif > 0.55) list += id +" "+ normal+",";
        else list += id +" "+ dificil+",";
        ++id;        
    }
    String[] tokens = list.split(",");
    return tokens;
}

 /**
     * 
     * @brief Añade un kakuro a la biblioteca en formato .txt
     * 
     * @param k = Es el tablero del kakuro que se quiere guardar
     * 
     * @post devuelve el Id con el que se ha guardado el Kakuro
 
     * 
     * */ 


public int anadir_kakuro (Casilla[][] k) 
    {
    	
        //cambiarla para que devuelva el ID y pasarla a casillas. 
    	 	
    	try {
    		boolean Existe = true;
	
            
            String contenido = k.length+","+k[0].length+"\n";
            for (int i = 0; i < k.length; i++)
            {
            	for (int j = 0; j < k[0].length; j++) 
                {
                    if (j == k[0].length - 1) contenido += k[i][j].toString() + "\n";
                    else contenido += k[i][j].toString() + ",";
                }
            }
            
            int ID = 1;
            while(Existe) {
            	
            	
            	String id = String.valueOf(ID);

                String ruta = "."+File.separator+"Data"+File.separator+"Biblioteca"+File.separator+"kakuro" + id + ".txt";
                File file = new File(ruta);
                
                if (!file.exists()) {
                	file.createNewFile();
                    Existe = false;
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(contenido);
                    bw.close();
                    System.out.println("Kakuro Guardado en la Biblioteca");
                    return ID;
                }
                ++ID;
   
            }
            return ID;
          // Si el archivo no existe es creado

            
            
        } 
    	
    	catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
     /**
     * 
     * @brief Lee uun kakuro desde un archivo .txt 
     * 
     * @param ID_kakuro = es el Id del kakuro que queremos leer
     * @param Path = es la ruta donde se encuentra el kakuro
     * 
     * @post devuelve un kakuro
 
     * 
     * */ 
      
     private double CalcularDificultad(Kakuro k)
    {
        double dif = 0;
        int size = k.getFilas();
        //negras = (int)Math.floor((n-1)*(m-1)*dificultad);
        /*
        if (size >= 3 && size <= 7) dif = 0.6;
        else if (size >= 7 && size <= 11) dif = 0.55;
        else dif = 0.5;
        */
        int negras = k.getceldas_negras();
        dif = (double) negras/((size-1)*(size-1));
        if (dif > 1)
        {
            dif = (double) ((size*size)-negras)/(double)10;
            dif = (double) 1 - dif;
        }
        else if (size >= 7 && size <= 11) dif = (double) negras/((size)*(size));
        if (size == 3) dif = 0.65;
        return dif;
    }   
      
    public Kakuro read_kakuro_file(int ID_kakuro, String Path){
        FileReader my_kakuro = null;
        Kakuro k = null;
        int c_blancas = 0, c_negras = 0;
        try{  //david aaa
            //System.out.println(Path+ID_kakuro+".txt");
            File file = new File(Path+ID_kakuro+".txt");
            my_kakuro = new FileReader(file);
            BufferedReader br = new BufferedReader(my_kakuro);
            String line = br.readLine();
            String [] tokens = line.split(",");
            int v = Integer.parseInt(tokens[0]);
            int h = Integer.parseInt(tokens[1]);
            Casilla [][] kakuro_grid = new Casilla [v][h];
            int line_counter = 0;
            while ((line = br.readLine()) != null) {
                tokens = line.split(",");
                for(int i=0;i<h;i++)
                {
                    if (tokens[i].equals("*"))
                    {
                        kakuro_grid[line_counter][i] = new Casilla(false,false,false,0,0);
                        ++c_negras;
                    }
                    else if (tokens[i].equals("?"))
                    {
                        kakuro_grid[line_counter][i] = new Casilla(true,false,false,0,0);
                        ++c_blancas;
                    }
                    else if (tokens[i].substring(0,1).equals("F"))
                    {
                        int max = Integer.parseInt(tokens[i].substring(1));
                        kakuro_grid[line_counter][i] = new Casilla(false,true,false,0,max);
                        ++c_negras;
                    }
                    else if (tokens[i].substring(0,1).equals("C"))
                    {
                        if (tokens[i].length() > 3)
                        {
                            String[] valores = tokens[i].substring(1).split("F");
                            int maxf = Integer.parseInt(valores[1]);
                            int maxc = Integer.parseInt(valores[0]);
                            kakuro_grid[line_counter][i] = new Casilla(false,true,true,maxc,maxf);
                            ++c_negras;
                        }
                        else
                        {
                            int max = Integer.parseInt(tokens[i].substring(1));
                            kakuro_grid[line_counter][i] = new Casilla(false,false,true,max,0);
                            ++c_negras;
                        }
                    }
                }
                line_counter++;
            }
            k = new Kakuro(c_blancas, c_negras, kakuro_grid);
            k.setID_Kakuro(ID_kakuro);
            my_kakuro.close();
            
        }
        catch(Exception e){
            System.out.println("Reading File Failed");
            System.out.println(e.toString());
            return null;
        }
        int n = k.getFilas();
        int m = k.getColumnas();
        //System.out.println(n);
        //System.out.println(c_negras);
        double dificultad = CalcularDificultad(k);


        //System.out.println(dificultad);
        //System.out.printf(dificultad);
        //System.out.printf("%nEl valor de la variable cantidad es %f", dificultad); 
 
        k.setDif(dificultad);
        return k;
    }





}
