/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.controladores;
import Dominio.clases.*;
import Persistencia.CtrlPersistencia;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/**
 * @file CtrlCapaDominio.java
 * @date 20-12-2020
 * @author Pere Masip
 * @brief Controlador que interactúa con la capa de dominio y se encarga de pasar datos con las demás capas.
 * 
 *
 */
 
 /**
  * @class CtrlCapaDominio
  * 
  * @brief Clase que se utiliza como intermediaria entre presentacion y persistencia. Transforma los datos que recibe de persistencia y se los pasa  
  * a presentacion  
  * 
  * */
public class CtrlCapaDominio {
    
    
    
    CapaDominioKakuro k;
    CtrlPersistencia cp;
    private HashMap<String,ArrayList<partida>>listaP;
    
    /** 
    * @brief Constructora de CtrlCapaDominio
	* @post se crea una nueva instancia de CtrlCapaDominio
	*/
    public CtrlCapaDominio ()
    {
        k = new CapaDominioKakuro(this);          
        cp = new CtrlPersistencia();
        listaP = cp.getMap();
        if (listaP == null) listaP = new HashMap<>();
    }
    
    
   
    
    //FUNCIONES DE REGISTRO E INICIO DE SESION DE USUARIOS
    
 /**
	 * @brief Recibe los datos de un Usuario que debe mandar a persistencia y que esta se encargue de guardarlos
	 *
	 * @param Usuario = Es el nombre de usuario que ha elegido para el registro
	 * @param Password = Es la contraseña que el usuario ha elegido para el registro
	 * 
	 * @post Se ha guaradado un nuevo Usuario con "Usuario" y "Password" 
	 * */
	 
    public void anadir_usuario(String usuario, String password) {
    
        HashMap <String, String> reg = cp.getRegistro();
 
        reg.put(usuario, password);
        cp.setRegistro(reg);
    }
    
    
    /**
	 * @brief Comrpueba si el Usuario que pretende hacer inicio de sesion, ha introcucido sus datos correctamente. En el caso que quiera regsitrarse, 
	 * comprobará que el nombre de Usuario que elija no exista.
	 *
	 * 
	 * @param n = Parámetro que determina si hara una funcionalidad u otra.
	 * @param Usuario = Es el nombre de usuario que ha elegido para el registro o con el que quiere iniciar sesion
	 * @param Password = Es la contraseña que el usuario ha elegido para el registro o con la que quiere iniciar sesion
	 * 
	 * @post Devuelve un booleano que indicará al sistema lo que debe hacer segun lo que ha pedido el usuario
	 * */
    
    public boolean comprobar(int n, String Usuario, String Password) {
		
		HashMap <String, String> DatosUsers = cp.getRegistro();
		if (n == 0) { //comprobar nuevo usuario
			
			if (DatosUsers.containsKey(Usuario)) {
				
				return false;
			}
                        else return true;
			
		}
		if (n == 1) { //inicio de sesion
			
			if (DatosUsers.containsKey(Usuario)) {
				
				if (Password.equals(DatosUsers.get(Usuario))) return true;
			}
                        
			
		}
		
		return false;
	}
    
    /**
     * @brief recibe un usuario password de la capa de presentacion y deberá comprobar si el login es correcto
     * 
     * @param User = String que corresponde al nombre de usuario
     * @param Pass = password del User
     * 
     * @post true si el login es es correcto, false si es no es correcto
     * 
     * */
    
    public boolean LogIn(String User, char[] Pass)
    {
        String pass = new String(Pass);
        boolean res = comprobar(1, User,pass);
        return res;
    }
    
    
    /**
     * @brief recibe un usuario password de la capa de presentacion y deberá comprobar si el registro será posible
     * 
     * @param User = String que corresponde al nombre de usuario
     * @param Pass = password del User
     * 
     * @post si el registro es posible, la funcion añadirá al usuario al sistema e informará a la capa de presentacion con un true. En cualquier otro caso, no se añadirá el usuario
     * y devolverá false.
     * 
     * */
    
    public boolean SignUp(String User, char[] Pass)
    {
        String pass = new String(Pass);
        boolean res = comprobar(0, User,pass);
        if (res)
        {
            anadir_usuario(User, pass);
            System.out.println("Usuario registrado");
        }
        else System.out.println("Usuario ya existente");
        return res;
    }
    
    //FUNCIONES DE PARTIDAS CREADAS
    
    /**
     * @brief funcion que se encarga de buscar los ids de la partidas que tiene guardadas el usuario con nombre "User" que se pasa como parámetro
     * 
     * @param User = nombre de Usuario para el cual se quiere buscar las partidas guardadas
     * 
     * @post se devuelve un vector de Strings con los nombres de sus partidas
     * */
    
    public String[] CargarIDs(String User)
    {
        ArrayList<partida> misPartidas;
        String[] res;
        if (listaP != null && listaP.containsKey(User))
        {
            misPartidas = listaP.get(User);            
            Iterator it = misPartidas.iterator();
            res = new String[misPartidas.size()];
            int i = 0;
            //System.out.println();
            while (i < res.length)
            {
                partida p = (partida) it.next();
                res[i] = p.getIDPartida();               
                
                ++i;
            }
            return res;
        }
        return null;
    }      
    
    /**
     * @brief funcion que se encarga de recibir los datos de una partida en juego que se quiere guardar y almacenarlos
     * 
     * @param IDUser = nombre de usuario del jugador
     * @param IDPartida = nombre con el cual ha guardado la partida
     * @param tablero = tablero de la partida
     * @param  IDKakuro = id del kakuro en juego
     * @param tiempo = tiempo de juego de la partida
     * @param finished = booleano que solo será true en el caso de que la partida este acabada, false en cualquier otro caso
     * 
     * @post se transformarán los datos y se almacenarán en forma de partida y se pasará a persistencia para que lo guarde, en el caso de que la partida esté acabada,
     * se actualizarán el ranking
     * 
     **/
    
    
    public boolean guardar_partida(String IDUser, String IDPartida, String[][] tablero,int IDKakuro, int tiempo, boolean finished)
    {
        //transformar el String[][] en kakuro para que se pueda crear un nuevo objeto partida y enviar a serializar
        Kakuro ka = buildKakuro(tablero);
        ka.setID_Kakuro(IDKakuro);
        ArrayList<partida> misPartidas;        
        if (listaP != null && listaP.containsKey(IDUser))
        {
            if (finished)
            {
                misPartidas = listaP.get(IDUser);
                partida p;
                Iterator it = misPartidas.iterator();
                while (it.hasNext())
                {
                    p = (partida) it.next();
                    if (p.getIDPartida().equals(IDPartida))
                    {
                        Kakuro k = p.getKakuro();
                        double dificultad = k.getDif();
                        double definitiva = (1-dificultad)/(tiempo+p.getTime()) * 100;
                        updateRanking(IDUser,definitiva);
                        return true;

                    }               
                }
                Kakuro k = ka;
                double dificultad = k.getDif();
                double definitiva = (1-dificultad)/(tiempo) * 100;
                updateRanking(IDUser,definitiva);
                return true;              
            }
            int c = 0;
            misPartidas = listaP.get(IDUser);
            Iterator it = misPartidas.iterator();            
            partida p;
            while (it.hasNext())
            {
                
                p = (partida) it.next();
                System.out.println(IDPartida);
                System.out.println(p.getIDPartida());
                if (p.getIDPartida().equals(IDPartida))
                {                   
                    p.setTime(tiempo);
                    p.setKakuro(ka);
                    misPartidas.remove(c);
                    misPartidas.add(p);
                    listaP.put(IDUser, misPartidas);
                    cp.setMap(listaP);
                    return true;
                }
                ++c;
            }
            p = new partida(IDUser,IDPartida,ka,tiempo);
            misPartidas.add(p);
           
        }
        else
        {
            if (finished)
            {
                Kakuro k = ka;
                double dificultad = k.getDif();
                double definitiva = (1-dificultad)/(tiempo) * 100;
                updateRanking(IDUser,definitiva);
                return true;
            }
            misPartidas = new ArrayList<>();
            partida p = new partida(IDUser,IDPartida,ka,tiempo);
            misPartidas.add(p);
        }
       
        listaP.put(IDUser, misPartidas);        
        cp.setMap(listaP);
        
        return true;
    }
    
    /**
     * @brief función que se encarga de buscar y transformar una partida solicitada por un usuario, para que se pueda retomar por donde la dejó
     * 
     * @param User = nombre de usuario del jugador
     * @param IDPartida = nombre con el cual guardó la partida anteriormente
     * 
     * @post Se devuelve una matriz de Strning la cual contiene el tablero del kakuro tal y como se quedó la ultima vez que jugó
     *
     * */
    
    public String[][] cargar_partida(String User, String IDPartida)
    {
        if (listaP != null && listaP.containsKey(User))
        {
            
            ArrayList<partida> misPartidas = listaP.get(User);
            Iterator it = misPartidas.iterator();
            String[][] result;
            while (it.hasNext())
            {
                
                partida p = (partida) it.next();
                System.out.println(p.getIDPartida());               
                if (p.getIDPartida().equals(IDPartida))
                {
                    System.out.println("llega a cargar");
                    Kakuro par = p.getKakuro();
                    Casilla[][] res = par.getTablero();
                    result = new String[res.length][res[0].length];
                    for (int i = 0; i < res.length; i++)
                    {
                        for (int j = 0; j < res[0].length; j++)
                        {
                            result[i][j] = res[i][j].toString();
                        }
                    }                   
                    System.out.println("cargado correctamente");
                    return result;
                }
            }
        }
        return null;
    }
   
    //FUNCIONES DE KAKUROS
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
    /**
     * @brief se encarga de transformar una matriz de Strings en un objeto Kakuro
     * 
     * @param cells = representa un kakuro
     * 
     * @post se devuelve un objeto kakuro
     * 
     * */
    
    private Kakuro buildKakuro(String[][] cells)
    {
        Casilla[][] k = new Casilla[cells.length][cells[0].length];
        
        int cblancas = 0, cnegras = 0;
        for (int i = 0; i < k.length; i++)
        {
            for (int j = 0; j < k[0].length; j++)
            {
                k[i][j] = new Casilla();
                k[i][j] = k[i][j].fromString(cells[i][j]);
                if (k[i][j].getTipo()) ++cblancas;
                else ++cnegras;
            }
        }
        Kakuro ka = new Kakuro(cblancas,cnegras,k);
        ka.setDif(CalcularDificultad(ka));
        return ka;
    }
    
    
    /**
     * @brief busca todos los Ids que hay almacenados en la Biblioteca de Kakuros
     * 
     * @post devuelve un vector de Strings con los IDs de los Kakuro existentes en la biblioteca
     * 
     * */
     
     
    public String[] get_lista_kakuro()
    {
        String[] res = cp.getlistaKakuro();
        
        return res;
    }
    
    /**
     * @brief Calcula la solución del kakuro que está en juego
     * 
     * @param ID = identificador del kakuro que se quiere resolver
     * 
     * @post se alamcena la solucion del kakuro en una matriz de Strings y se retorna
     * 
     * */
    
    
     public String[][] ShowSolution(String ID)
    {
        Kakuro x = cp.read_kakuro_file(Integer.parseInt(ID),0);
        boolean result = k.solve(x,0);      
        if (result)
        {
            Casilla[][] res = k.getResult();
            String[][] line = new String[res.length][res[0].length];
            for (int i = 0; i < res.length; i++)
            {
                for (int j = 0; j < res[0].length; j++)
                {
                    line[i][j] = res[i][j].toString(); 
                }
            }
            return line;
        }
        return null;
    }
    //FUNCIONES DE RANKINGS
    
    /**
     * @brief ordena el ranking en funcion de ña puntuacion
     * 
     * @param rank = HashMap que representa el ranking de jugadores
     * 
     * @post rank estará ordenador de mayor a menor por puntuacion
     * 
     * */
    private String sortByScore(HashMap<String, Double> rank)
    {
        HashMap<String,Double> res = rank.entrySet().stream().sorted(Map.Entry.<String,Double>comparingByValue().reversed()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        rank = res;
        String contenido = "";
        for (Map.Entry<String, Double> entry : rank.entrySet()) {
            
            contenido += entry.getKey() + "," + entry.getValue() + "\n";
            
           
        }
        return contenido;
    }
    
    /**
     * @brief obtendra el ranking de jugadores
     * 
     * @post devolverá el ranking en formato String
     * */
    
    public String ShowRanking()
    {
        HashMap<String, Double> res = cp.getRanking();
        
        return sortByScore(res);
    }
    
    /**
     * @brief se encarga de actulizar el ranking cuando se le pasa un jugador y una puntuacion. Si el usuario no habia terminado ninguna partid antes, se le añadirá nuevo.
     * En otro caso se le actulizará la puntuación
     * 
     * @param Key = nombre de Usario del jugador
     * @param value = puntuación que ha obtenido en la partida
     * 
     * @post el ranking está actulizado despues de pasarselo a peristencia para que lo guarde
     * */
    
    public void updateRanking(String Key, double Value)
    {
        HashMap<String, Double> res = cp.getRanking();
        if (res.containsKey(Key))
        {
            Value += res.get(Key);            
        }       
        res.put(Key, Value);
        cp.setRanking(res);
    }
    
    /**
     * @brief buscará el kakuro que tiene como identificador el id que se pasa como parámetro y lo teranformará a una matriz de String
     * 
     * @param id = identificador del kakuro
     * 
     * @post devuelve una matriz de String a la capa de presentación
     * */
    
    public String [][] getkakuroenjuego(int id) {
    
        Kakuro k = cp.read_kakuro_file(id,0);
        Casilla [][] tablero = k.getTablero();
        int n = k.getColumnas();
        String [][] def = new String [n][n];
        
        for (int i = 0; i <n; ++i) {
            for (int j = 0; j < n; ++j) {
                def[i][j] = tablero[i][j].toString();
                
            }
        }
        return def;
    }
    
    /**
     * @brief generará un kakuro con la dificultad indicada
     * 
     * @param dificultad = es la dificultad con la que se generará el kakuro
     * 
     * @post devolverá un entero que representa el id que tiene el nuevo kakuro que se ha generado
     * */

    public int generarKakuro(int dificultad) {
        return k.generar(dificultad);
    }
    
    /**
     * @brie generará un kakuro con las caracteristicas que se pasan como parámetros
     * 
     * @param dimensiones = dimensiones con las cuales se generará el kakuro
     * @param numCasillasNegras = numero de casillas negras que tendrá el kakuro
     * 
     * @post devolverá un entero que representa el id que tiene el nuevo kakuro que se ha generado
     * */

    public int generarKakuro(int dimensiones, int numCasillasNegras) {
        return k.generar(dimensiones, numCasillasNegras);
    }



    public String generarKakuroManual(String[][] gridContentsMatrix) {
        int idKakuroGenerado = -1;
        Kakuro ka = null;
        try{
            ka = buildKakuro(gridContentsMatrix);
            Kakuro copy = buildKakuro(gridContentsMatrix);
             
            boolean res = k.validar_kakuro(copy);
            if (!res) return String.valueOf(-1);
            //TODO: validar kakuro despues de construirlo!!!!
        }catch(Exception e){
            System.out.println("Fallo en funcion buildKakuro, kakuro invalido!!");    
        }
        if(ka!=null){
            idKakuroGenerado = cp.anadir_kakuro(ka.getTablero());
            double dif = ka.getDif();
            return String.valueOf(idKakuroGenerado)+" "+String.valueOf(dif);
        }      
        
        return String.valueOf(idKakuroGenerado);
    }
    
    /**
     * @brief se encarga de pasarle un kakuro a la capa de persistencia para que lo guarde
     * 
     * @param tablero = matriz de casillas que representa el kakuro
     * 
     * @post devuelve un entero que representa el id que se la ha asignado al nuevo kakuro
     * */
    
   public int addkakuro(Casilla[][] tablero)
   {
       return cp.anadir_kakuro(tablero);
   }
   
   /**
    * @brief buscará un kakuro que tenga el id que se pasa como paramétro
    * 
    * @param ID = id del kakuro 
    * 
    * @post se devuelve un objeto kakuro
    * 
    * */
    
    
   public Kakuro read_kakuro_file(int ID, int option)
   {
       return cp.read_kakuro_file(ID,option);
   }
   
    /**
    * @brief se encarga de comprobar si el valor "value" en el kakuro recibido es valido en la posicion "x,y"
    * 
    * @param x = valor de la posicion modificada en el eje x
    * @param y = valor de la posicion modificada en el eje y 
    * @param value = valor modificado 
    * @param gridContentsMatrix = tablero sobre el cual se hizo la modificacion
    * 
    * @post se ha devuelto el resultado de llamar a la funcion con el mismo nombre de la clase Kakuro.
    * */
    
   
    public int comprobarMovimiento(int x, int y, String value, String[][] gridContentsMatrix) {
        Kakuro kakuro = buildKakuro(gridContentsMatrix);
        System.out.println(value.length());
        try
        {
            int valor = Integer.parseInt(value);                  
            return k.comprobar_movimiento(x,y,valor,kakuro); //To change body of generated methods, choose Tools | Templates.
        }
        catch (Exception e)
        {
           
            return 6;
        }
    }

    /**
    * @brief se encarga de obtener, si existe, el kakuro del usuario IDUsuario identificado por IDPartida
    * 
    * @param IDUsuario = identificador del usuario
    * @param IDPartida = identificador de la partida 
    * 
    * @post si se ha encontrado el Kakuro solicitado lo devuelve. En caso contrario devuelve -1
    * */
    
    public int getKakuroDePartida(String IDUsuario,String IDPartida) {
        ArrayList<partida> misPartidas = listaP.get(IDUsuario);
        Iterator it = misPartidas.iterator();
        while (it.hasNext())
        {
            partida p = (partida) it.next();
            if (p.getIDPartida() == IDPartida)
            {
                return p.getKakuro().getID_Kakuro();
            }        
        }
        return -1;        
    }
    /*
    public String[][] getTableroDePartida(String IDUsuario,String IDPartida) {
        ArrayList<partida> misPartidas = listaP.get(IDUsuario);
        Iterator it = misPartidas.iterator();
        while (it.hasNext())
        {
            partida p = (partida) it.next();
            if (p.getIDPartida() == IDPartida)
            {
                return p.getKakuro().GridToString();
            }        
        }
        return null;
    }
    */
    
    /**
    * @brief llama a la funcion setMap de la clase CtrlPersistencia pasandole el atributo listaP
    * 
    * @post
    * */
    
    public void setMap() {
        cp.setMap(listaP);
    }

/**
    * @brief obtiene el tiempo transcurrido de la partida identificada con "IDPartida" asociado al usuario "IDUsuario"(si existe)
    * 
    * @param IDUsuario = identificador del usuario
    * @param IDPartida = identificador de la partida 
    * 
    * @post si se ha encontrado el Kakuro solicitado se devuelve el tiempo. En caso contrario devuelve -1
    * */

    public int getTimeDePartida(String IDUsuario,String IDPartida) {
        ArrayList<partida> misPartidas = listaP.get(IDUsuario);
        Iterator it = misPartidas.iterator();
        while (it.hasNext())
        {
            partida p = (partida) it.next();
            if (p.getIDPartida() == IDPartida)
            {
                return p.getTime();
            }        
        }
        return -1;
    }
}
