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
 * @file CtrlRegistro.java
 * @date 20-12-2020
 * @author Mauro Garcia
 * @brief Archivo que implementa la lectura y escritura del registro de usuarios en disco.
 * 
 *
 */

/**
 * @class CtrlRegistro
 * @brief Clase que implementa la lectura y escritura del registro de usuarios en disco.
 */
 
public class CtrlRegistro {
    
    
    //private HashMap <String, String> DatosUsers;
    String Contenido;
    
    /**
     * @brief Creadora de CtrlRegsitro
     * 
     * @post Crea una instancia de CtrlRegistro
     * 
     */
     
    public CtrlRegistro()
    {
      
    }
    
    /**
     * @brief actualiza los datos de registro de Usuarios de la aplicacion
     * 
     * @param p = guarda los datos de los Usuarios
     * 
     * @post se guardaran los datos de p en un txt
     * 
     * */
    
   public void guardar_registro(HashMap <String, String> p) {
   
       String contenido = "";
       
       for (Map.Entry<String, String> entry : p.entrySet()) {
            
            contenido += entry.getKey() + "," + entry.getValue() + "\n";
            
           
        }
       
        try {
			
			String ruta = "."+File.separator+"Data"+File.separator+"DatosUsers.txt";
			File file = new File(ruta);
			FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(contenido);
                        bw.close();   
			
		}
		catch(Exception e){
            System.out.println("writing File Failed");
            System.out.println(e.toString());
     }
   
   }
   
   
   /**
    * @brief busca los datos de registro y se los dara a la capa de dominio
    * 
    * @post devuelve un HashMap con todos los datos de resgistro
    * 
    * */
    
    public HashMap <String, String> cargar_registro() {
		
		
		
		HashMap<String, String> datos = new HashMap<String, String>();
		
		
		String ruta = "."+File.separator+"Data"+File.separator+"DatosUsers.txt";
		File file = new File(ruta);
		
		 
		
			 FileReader my_map;
			 try{
				 my_map = new FileReader(file);
				 BufferedReader br = new BufferedReader(my_map);

		         String line;
		         while (!(line = br.readLine()).isEmpty()) {
		        	 Contenido += line + "\n";  
		        	 String [] tokens = line.split(",");
                                 System.out.println(tokens[0]+" "+tokens[1]);
		        	 datos.put(tokens[0],tokens[1]);
		         }
		         
		         
			 }
			 
			 catch(Exception e){
		            System.out.println("Reading File Failed");
		            System.out.println(e.toString());
		     }
		
		 
		 System.out.println("Contenido: ");
		return datos;
				 
			 
	
   }
    
    /**public static boolean LogIn(String User, char[] Pass) 
    {
      String Password = new String(Pass);
                               
                     System.out.println("Password: "+Password+"\nUsuario: "+User);
                     //Contrasenya = in.nextLine();
                     boolean valido = r.comprobar(1, User, Password);
                     if (valido) {
                    	
                    	 return true;
                    	 
                    	 
                     }
                     else {
                    	 
                    	 return false;
                     }               
    }
    
     public static boolean SignUp(String User, char[] Pass) 
    {
      String Password = new String(Pass);
                     //System.out.println("Introduzca su contrasenya");
                     //Contrasenya = in.nextLine();
                     boolean contiene = r.comprobar(0, User, Password);
                     if (!contiene) {
                    	 r.nuevo_usuario(User,Password);
                    	 return true;
                    	 
                    	 
                     }
                     else {
                    	 System.out.println("Ya existe");
                    	 return false;
                     }                            
    }	**/
    	
    
}
