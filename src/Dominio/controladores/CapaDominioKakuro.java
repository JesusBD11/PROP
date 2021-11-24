/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.controladores;
import Dominio.clases.*;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
/**
 * @file CapaDominioKakuro.java
 * @date 20-12-2020
 * @author Pere Masip
 * @brief archivo que contiene las funcionalidades principales del programa
 * 
 *
 */
/**
 * @class CapaDominioKakuro
 * @brief clase donde implementamos las funcionalidades principales del programa, siendo estas validar, generar y solucionar un Kakuro.
 */
public class CapaDominioKakuro {
    private static boolean columnallena;
    private static boolean filallena;

   
    
    private static ArrayList<Casilla [][]> result;
    public static CtrlCapaDominio ccd;
    
    /** 
    * @brief Constructora de CapaDominioKakuro
	* @param c = Controlador de capa de dominio con el que interactua la clase.
	* @post Existe una instancia nueva de CapaDominioKakuro con el controlador de capa de dominio correspondiente.
	*/
     public CapaDominioKakuro()
    {
        result = new ArrayList<>();
        ccd = new CtrlCapaDominio();
    }
    public CapaDominioKakuro(CtrlCapaDominio c)
    {
        result = new ArrayList<>();
        ccd = c;
    }
    /** 
    * @brief funcionalidad utilizada a la hora de jugar que comprueba la validez de un movimiento.
	* @param i = fila a comprobar
	* @param j = columna a comprobar
	* @param v = valor a comprobar
	* @param k = kakuro sobre el que realizar las comprobaciones
	* @post en caso de que el movimiento sea válido, devuelve 4 en caso de que el kakuro esté acabado y 5 en caso de que todavía queden casillas por rellenar.
	*   En caso de que el movimiento no sea válido, se devuelve un valor entero diferente dependiendo de la causa del error. 
	*/
    
    public static int comprobar_movimiento(int i, int j, int v, Kakuro k){
      boolean valido = true;
      
       Casilla[][] kakuro = k.getTablero();
       int filas = k.getFilas();
       int columnas = k.getColumnas();
       int celdas_blancas = 0;
       for (int fil = 0; fil < filas; ++fil)
       {
           for (int col = 0; col < columnas; ++col)
           {
               if (kakuro[fil][col].getTipo())
               {
                   if (kakuro[fil][col].getValor() == 0) celdas_blancas++;
               }
           }
       }
       
        if (i >= filas || j >= columnas || i < 0 || j < 0) {
            valido = false;
            System.out.println("fuera de rango, dimensiones: "+filas+" "+columnas+" "+i+" "+j);
            return 0;
        }
        if (v <= 1 && v >= 9) {
            valido = false;
            System.out.println("segundo if");
            return 7;
        }
        if (!kakuro[i][j].getTipo()){
        	System.out.println(kakuro[i][j]);
            valido = false;
            System.out.println("tercer if");
            return 1;
        }
   
       Set<Integer> relleno = new HashSet<>(0);
       int valor_anterior = kakuro[i][j].getValor();
       int resfila = takeFila(kakuro, v, i,j,relleno) + valor_anterior;  //comprobamos suma total de la fila, el resultado a alcanzar, si se producirán repetidos y si está llena o no.
       int rescolumna = takeColumna(kakuro, v, i,j,relleno) + valor_anterior;      
       kakuro[i][j].setValor(v);
       if (valor_anterior == 0) {           
        System.out.println("cuarto if");
       }
       
       if (valor_anterior == v) {
           System.out.println("quinto if");
          
           printCellMatrix(kakuro);
           return 2;
       } 
       if (!(checkRun(resfila, v, filallena)) || !(checkRun(rescolumna, v, columnallena))) {
           System.out.println("me");
            valido = false;
            if (!checkRun(resfila, v, filallena)) //System.out.println(motivo2);
            if (!checkRun(rescolumna, v, columnallena)) //System.out.println(motivo3);
            kakuro[i][j].setValor(valor_anterior);
            if (valor_anterior == 0) {               
                 System.out.println("sumo");
            }
           
            return 3;
       
       }
              
        System.out.println("celdas =" + celdas_blancas);
        if (celdas_blancas == 1)  {System.out.println("Kakuro resulto"); return 4;}
        else return 5;
        
       
   }
    /** 
    * @brief Comprueba la validez de una fila o columna con la sumatoria suma si se le añade el valor valor_actual
	* @param suma = sumatoria de los valores actuales de una fila o columna
	* @param valor_actual = valor a comprobar
	* @param llena = indica si añadir valor_actual llenaría la fila o columna con la sumatoria suma.
	* @post devuelve cierto o falso dependiendo de la validez de valor_actual respecto a la sumatoria suma y el parámetro llena.
	*/
    public static boolean checkRun(int suma, int valor_actual, boolean llena) //
         //C
   {
       
       if (!llena) return suma - valor_actual > 0;
       else return suma - valor_actual == 0;
   }
    /** 
    * @brief filtra el conjunto de valores posibles de la celda para una fila o columna con sumatorias que solo admiten combinaciones determinadas de valores
	* @param max = sumatoria de la fila o columna filtrada cuándo ésta esté completa.
	* @param length = longitud de la fila o columna
	* @param possible = conjunto con los valores posibles de la celda
	* @post possible contiene los valores posibles de la celda después de pasar por el filtro.
	*/
    public static void filtrarSumas(int max, int length, Set<Integer> possible)
    {
        int i = 1;
        switch(length)
        {
            case 1:
                possible = new HashSet<Integer>();
                possible.add(max);
                break;
            case 2:
                if (max == 17)
                {
                    for (i = 1; i <= 7; i++)
                    {
                        if (possible.contains(i)) possible.remove(i);
                    }
                }
                else if (max == 16)
                {
                    for (i = 1; i <= 8; i++)
                    {
                        if (possible.contains(i) && i != 7) possible.remove(i);
                    }
                }
                break;
            case 3:
                if (max == 24)
                {
                     for (i = 1; i <= 6; i++)
                    {
                        if (possible.contains(i)) possible.remove(i);
                    }
                }
                break;
            case 4:
                if (max == 30)
                {
                     for (i = 1; i <= 5; i++)
                    {
                        if (possible.contains(i)) possible.remove(i);
                    }
                }
                break;
            case 5:
                if (max == 35)
                {
                     for (i = 1; i <= 4; i++)
                    {
                        if (possible.contains(i)) possible.remove(i);
                    }
                }                
                break;
            case 6:
                if (max == 39)
                {
                     for (i = 1; i <= 3; i++)
                    {
                        if (possible.contains(i)) possible.remove(i);
                    }
                }
                break;
            case 7:
                if (max == 42)
                {
                     for (i = 1; i <= 2; i++)
                    {
                        if (possible.contains(i)) possible.remove(i);
                    }
                }
                break;
            case 8:
                if (max == 44)
                {                   
                   if (possible.contains(1)) possible.remove(1);                   
                }
                break;            
        }
    }
    /** 
    * @brief comprueba las restricciones de fila de la casilla consultada en el solve. Ademas comprueba si la fila esta llena y filtra los valores posibles para esa fila.
	* @param state = estado actual de la matriz de casillas en el solve
	* @param valor_actual = valor a probar en el solver
	* @param celdaf = fila en la que se encuentra la casilla consultada
	* @param celdac = columna en la que se encuentra la casilla consultada
	* @param possible = conjunto con los valores posibles de la casilla
	* @post devuelve el resultado de la comprobación, siendo este -1 en caso de valor repetido o (valor a alcanzar - total de la fila) en cualquier otro. filallena = true si la columna esta llena.
	* también modificamos possible en caso de que sea necesario.
	*/
    public static int takeFila(Casilla[][] state, int valor_actual, int celdaf,int celdac,Set<Integer> possible)
   {
       //int blancaCounter = 0;
       filallena = true;
       int length = 0;
       int sumarun = 0;
       int j = celdac;
       int max;
       int value;
       while (state[celdaf][j].getTipo())
       {    //F14,?,4,5,?,2
            //
           value = state[celdaf][j].getValor();
           sumarun += value;
           if (value == 0 && j != celdac) filallena = false;
           if (value == valor_actual) return -1;
           if (value != 0 && possible.contains(value)) possible.remove(value);
           ++length;
           --j;
       }//Mientras sea blanca, nos movemos
       //Cogemos el maximo
       max = state[celdaf][j].getValorAux();
       
       int i = celdac;
       i++;
       while (i < state[0].length && state[celdaf][i].getTipo())
       {
           value = state[celdaf][i].getValor();
           sumarun += value;
           if (value == 0) filallena = false;
           if (value == valor_actual) return -1;
           if (value != 0 && possible.contains(value)) possible.remove(value);
           ++length;
           ++i;
       }
      //System.out.println(filallena);
      //System.out.println(max); 
      //System.out.println(sumarun);
      filtrarSumas(max,length,possible);
      return max - sumarun;
   }
   /** 
    * @brief comprueba las restricciones de columna de la casilla consultada en el solve. Ademas comprueba si la columna esta llena y filtra los valores posibles para esa columna.
	* @param state = estado actual de la matriz de casillas en el solve
	* @param valor_actual = valor a probar en el solver
	* @param celdaf = fila en la que se encuentra la casilla consultada
	* @param celdac = columna en la que se encuentra la casilla consultada
	* @param possible = conjunto con los valores posibles de la casilla
	* @post devuelve el resultado de la comprobación, siendo este -1 en caso de valor repetido o (valor a alcanzar - total de la columna) en cualquier otro. columnallena = true si la columna esta llena.
	* también modificamos possible en caso de que sea necesario.
	*/
    public static int takeColumna(Casilla[][] state, int valor_actual, int celdaf,int celdac, Set<Integer> possible)
   {
      //int blancaCounter = 0;
       
       columnallena = true;
       int sumarun = 0;
       int length = 0;
       int j = celdaf;
       int max;
       int value;
       while (j >= 0 && state[j][celdac].getTipo())
       {
           value = state[j][celdac].getValor();
           sumarun += value;
           if (value == 0 && j != celdaf) columnallena = false;
           if (value == valor_actual) return -1;
           if (value != 0 && possible.contains(value)) possible.remove(value);
           ++length;
           --j;
       }//Mientras sea blanca, nos movemos
       //Cogemos el maximo
       max = state[j][celdac].getValor();
       int i = celdaf;
       i++;
       while (i < state.length && state[i][celdac].getTipo())
       {
           value = state[i][celdac].getValor();
           sumarun += value;
           if (value == 0) columnallena = false;
           if (value == valor_actual) return -1;
           if (value != 0 && possible.contains(value)) possible.remove(value);
           ++length;
           ++i;
       }
       filtrarSumas(max,length,possible);    
       return max - sumarun;
   }
   
   /** 
    * @brief Calcula  y llena los vectores de enteros con los indices de todas las casillas blancas del kakuro k
	* @param k = kakuro a solucionar
	* @param startF = fila en la que empieza
	* @param startC = columna en la que empieza
	* @param fila = indices de las casillas blancas (fila)
	* @param columna = indices de las casillas blancas (columna)
	* @post fila y columna contienen los indices de todas las casillas blancas del kakuro k 
	*/
    public static void calcPosiciones(Kakuro k, int startF, int startC, int[] fila, int[] columna)
    {
    
       Casilla [][] solution = k.getTablero();
       int indice = 0;
       if (startF == 0 && startC == 0)
       {
        for (int i = 0; i < k.getFilas(); ++i)
        {
            for (int j = 0; j < k.getColumnas(); ++j)
            {
                if (solution[i][j].getTipo())
                {
                    fila[indice] = i;
                    columna[indice] = j;
                    ++indice;
                }
            }
        }
       }
       else
       {
          for (int i = startF; i >= 0; i--)
        {
            for (int j = startC; j >= 0; j--)
            {
                if (solution[i][j].getTipo())
                {
                    fila[indice] = i;
                    columna[indice] = j;
                    ++indice;
                }
            }
        } 
       }
    }
    /*
   private static boolean isEqual(Casilla[][] state, Casilla[][] sol)
   {
       for (int i = 1; i < state.length; i++)
       {
           for (int j = 1; j < state[0].length; j++)
           {
               if (state[i][j].getValor() != sol[i][j].getValor()) return false;
           }
       }
       return true;
   }
   */
   /** 
    * @brief funcion de inmersion de solve(). en caso de que opcion = 0, recorre recursivamente todas las casillas blancas de state probando valores y realizando comprobaciones
    *   hasta alcanzar una solucion. En caso de que opcion = 1, buscamos todas las soluciones posibles de la matriz de casillas y paramos cuando encontramos 2.
	* @param state = estado actual de la matriz de casillas en el solve
	* @param valor_actual = valor a probar en el solver
	* @param fila = array de enteros que indica la fila en la que se encuentra cada casilla blanca
	* @param columna = array de enteros que indica la columna en la que se encuentra cada casilla blanca
	* @param opcion = modifica el comportamiento de la función dependiendo de si es 0 o 1.
	* @post opcion = 0: en caso de encontrar solucion, guarda el resultado en result y devuelve true. En otro caso devuelve false.
	* opcion = 1: En caso de encontrar 2 soluciones, devuelve false. En caso de encontrar 1 única solución, devuelve true.
	*/
    private static boolean solve_kakuro(Casilla[][] state, int[] fila, int[] columna, int indice, int opcion)
   {
       //estados:
       //visited[celdaf][celdac] = true;
       boolean acabat = false;
       //System.out.println("indice: "+indice);
       if (indice == fila.length)
       {
           result.add(state);
           return true;
       }
       int celdaf = fila[indice];
       int celdac = columna[indice];
       //ArrayList<Integer> val = possibleValues(state,celdaf,celdac);
       Set<Integer> possible = new HashSet<Integer>();
       for (int i = 1; i <= 9; i++) possible.add(i);
       int resfila = takeFila(state, -1, celdaf,celdac,possible);  //comprobamos suma total de la fila, el resultado a alcanzar, si se producirán repetidos y si está llena o no.
       int rescolumna = takeColumna(state, -1, celdaf,celdac,possible);
        boolean f = filallena;
        boolean c = columnallena;
        Iterator valor_actual = possible.iterator();
        //System.out.println("numero de valores posibles: "+possible.size());
         //System.out.println(celdaf+" "+celdac);
        while(valor_actual.hasNext())
        {
             int valor;
             valor = (int)valor_actual.next();           
             if (checkRun(resfila, valor, f) && checkRun(rescolumna, valor, c))  //comprobamos validez del valor.
                {
                    //System.out.println(valor);
                    state[celdaf][celdac].setValor(valor);
                    int id = indice;
                    acabat = solve_kakuro(state,fila,columna,indice+1,opcion);
                    if (acabat)
                    {
                        if (opcion == 0) return true;
                        else
                        {
                           if (result.size() == 2) return true;
                           state[celdaf][celdac].setValor(0);
                        }
                    }
                    else {state[celdaf][celdac].setValor(0);}  //si no borramos los cambios, al sumar fila / columna nos da un resultado erróneo.
                   
                }
             
             
          }
              
       return acabat;
   }
     /** 
    * @brief opcion = 0: comprueba que k tiene solucion y, de ser asi, la guarda en result
    *       opcion = 1: busca todas las posibles soluciones del Kakuro para comprobar si éste tiene 1 solución o más.
	* @param k = Kakuro a solucionar
	* @param opcion = indica en que modo trabaja la funcion
	* @post opcion = 0: en caso de encontrar solución, guarda el resultado en result. En caso contrario se informará a la función que solicita la solución.
	*  opcion = 1: en caso de encontrar 1 solución, devuelve true indicando que el kakuro es válido. En caso contrario devuelve false.
	*/
    
    public static boolean solve(Kakuro k, int opcion)
   {      
       result = new ArrayList<>();
       filallena = false;
       columnallena = false;
       int cblancas = k.getceldas_blancas();
       int[] fila = new int[cblancas];
       int[] columna = new int [cblancas];
       calcPosiciones(k,0,0,fila,columna);
       Casilla[][] solution =k.getTablero();
       long startTime = System.nanoTime();
       boolean res = solve_kakuro(solution,fila,columna,0,opcion);
       long stopTime = System.nanoTime();
       System.out.println("Time: "+(stopTime - startTime));
       long totalTime =  stopTime - startTime;
       double timeS = (((double)totalTime)/Math.pow(10,9));
       
       System.out.println("Execution time: "+String.valueOf(timeS)+" s");
       if (opcion == 0){
            if (res)
            {
                System.out.println("Kakuro Solucionado");
                printCellMatrix(result.get(0));
                return true;
            }
            else
            {
                System.out.println("fallo al solucionar");
                return false;
            }
            
       }
       else{
           if (result.size() == 1)
           {
                System.out.println("Kakuro valido, solucion unica");
                
                return true;
           }
           else {
                File borrar = new File("."+File.separator+"Data"+File.separator+"Biblioteca"+File.separator+"kakuro"+ opcion +".txt");
                borrar.delete();
                k = null;
                if (result.size() > 1) System.out.println("Solucion multiple\nn.Soluciones = "+result.size());
                else System.out.println("fallo al solucionar\n");
                System.out.println("El Kakuro introducido no es valido, introduzca otro");
                return false;
           }
           
       }
       

   }
    
    
    //MOVER
    /** 
    * @brief comprueba que la solucion alcanzada despues de jugar un kakuro es valida
	* @param k = Kakuro a comprobar
	* @post devuelve true o false dependiendo del resultado de la comprobacion
	*/
    public static boolean comprobarSolucion(Kakuro kakuro1) {
        //cuando el kakuro está lleno, comprueba que las sumatorias son válidas
        int n,m;
        Casilla[][] k1 = kakuro1.getTablero();
        Casilla[][] k2 = kakuro1.getTablero();
        n = k1.length;
        m = k1[0].length;
        Queue<Casilla> casillasNegras = new LinkedList<Casilla>();
        //System.out.println("Comprobando Estado--------------");
        //System.out.println("Estado Actual");
        //printCellMatrix(k1);
        for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
               //Si es casilla negra
               if(!k1[i][j].getTipo() && (k1[i][j].getValor()>0 ||k1[i][j].getValorAux()>0) ){
                   k1[i][j].setX(i);
                   k1[i][j].setY(j);
                   casillasNegras.add(new Casilla(k1[i][j]));
                   k1[i][j].setValor(0);
                   k1[i][j].setValorAux(0);
               }
               
           }
        }
        //System.out.println("Estado sin casillas negras");
        //printCellMatrix(k1);
        generarSumas(k1);
        //System.out.println("Estado con casillas negras");
        //printCellMatrix(k1);
        boolean sumasIguales = true;
        Casilla anterior,actual;
        int x,y,valAntX,valAntY,valActX,valActY;
        while(!casillasNegras.isEmpty() && sumasIguales){
           anterior = casillasNegras.remove();
           valAntX = anterior.getValor();
           valAntY = anterior.getValorAux();
           x = anterior.getX();
           y = anterior.getY();
           actual = k2[x][y];
           valActX = actual.getValor();
           valActY = actual.getValorAux();
           sumasIguales = valActX==valAntX && valActY==valAntY;
        }
        //System.out.println("Fin Comprobar Estado--------------");
        return sumasIguales;
    }
    /** 
    * @brief Genera una matriz de casillas nueva que equivale a la de un kakuro valido
	* @param n = numero de filas
	* @param m = numero de columnas
	* @param dificultad = constante de dificultad
	* @post devuelve una matriz de casillas y la imprime por pantalla.
	*/
    
    public static Casilla[][] generarKakuro(int n, int m, double dificultad){
       long startTime = System.nanoTime();
      
       int n1=n;
       int m1=m;
       int negras;
       if (dificultad == 0.6)
       {
           negras = negras = (int)Math.floor((n)*(m)*dificultad);
       }
       else if (dificultad < 1) negras = (int)Math.floor((n-1)*(m-1)*dificultad);      
       else negras = (int) dificultad;
       System.out.println("celdas negras: "+negras);
       int intentar =  1000;
       Casilla k[][] = new Casilla[n][m];
       boolean unic = false;
       int intentos = 0;
       while (!unic)
       {          
           int cnegras;
           if (negras < n+m) cnegras = n+m-1;
           else cnegras = negras;
          ++intentos;
            try{
                while(!generarK(n1,m1,negras,k)){

                    intentar--; 
                    if(intentar<0){
                        throw new Exception();
                    }
                }
            }
            catch(Exception e){
                    return null;
             }
            
            int cblancas = k.length*k[0].length - cnegras;
            System.out.println("nblancas: "+cblancas+"nNegras: "+cnegras);
            Casilla[][] k1 = k;
            
            //unic = solve(new Kakuro(cblancas,cnegras,k1),1);
            unic = true;
            //printCellMatrix(result.get(0));
            System.out.println("Intentos: "+intentos);
       }                  
       long stopTime = System.nanoTime();
       System.out.println("Time: "+(stopTime - startTime));
       long totalTime =  stopTime - startTime;
       double timeS = (((double)totalTime)/Math.pow(10,9));
       System.out.println("Execution time: "+String.valueOf(timeS)+" s");
       printCellMatrix(k);       
       
       return k;
   }
    /** 
    * @brief funcionalidad de generarKakuro encargada de generar la matriz de casillas
	* @param n = numero de filas
	* @param m = numero de columnas
	* @param negras = indicador de la cantidad de casillas negras necesarias. varia segun la dificultad pasada a generarKakuro
	* @param k = matriz de casillas a rellenar
	* @post devuelve true en caso de generar una matriz de casillas validas y false en caso contrario. El resultado se guarda en k.
	*/
    public static boolean generarK (int n,int m,int negras, Casilla [][]k ) {
       //inicializa la matriz de casillas
       for(int i=0;i<k.length;i++){
           for(int j=0;j<k[0].length;j++){
               k[i][j]=new Casilla();
           }
       }
       
       /*//test black vertical and horizontal values
       for(int i=1;i<k.length;i++){
           k[i][0].setValor(i);
           k[i][0].setVertical(true);
           k[0][i].setValorAux(i);
           k[0][i].setHorizontal(true);
       }
       
       //test white cells
       for(int i=1;i<k.length;i++){
           for(int j=1;j<k[1].length;j++){
               k[i][j].setTipo(true);
           }
       }
       
       //test black vertical and horizontal values same cell
       k[2][2].setValor(1);
       k[2][2].setValorAux(2);
       k[2][2].setTipo(false);
       k[2][2].setVertical(true);
       k[2][2].setHorizontal(true);
       
       //test white cell number
       k[3][3].setValor(7);*/
              
       
       //System.out.println("generando negras.....");
       
       //Comienza a generar el kakuro
       boolean success = true; 
       generarCasillasNegras(k,negras);
       //printCellMatrix(k);
       //System.out.println("llenando casillas blancas.....");
       success = generarCasillasBlancas(k);
       printCellMatrix(k);
       //System.out.println("genrando sumas en casillas negras.....");
       generarSumas(k);
       //printCellMatrix(k);
       
       return success;
   }
   
   /*
    A partir de una tablero de casillas k lleno de numeros, genera las sumas
    horizontales y verticales en las casillas negras correspondientes y elimina 
    los valores en las casillas blancas 
   */
   /** 
    * @brief funcionalidad de generarK encargada de generar las sumatorias de las filas y columnas
	* @param k = matriz de casillas a rellenar
	* @post k contiene el resultado de generar las sumatorias ademas de lo que ya contenia
	*/
   public static void generarSumas(Casilla[][] k){
       int n,m;
       n = k.length;
       m = k[0].length;
       
       //Recorremos la matriz Horizontalmente desde la esquina inferior derecha
       int sumHorizontal,sumVertical;
       for(int i=n-1;i>0;i--){
           sumHorizontal = 0;
           for(int j=m-1;j>0;j--){
               //Si la casilla es blanca
               if(k[i][j].getTipo()){
                   sumHorizontal += k[i][j].getValor();
                   if(k[i][j-1].getTipo()==false){
                        k[i][j-1].setHorizontal(true);
                        k[i][j-1].setValorAux(sumHorizontal);
                        sumHorizontal = 0;
                      
                   }
               }
               
           }
       }
       //Recorremos la matriz verticalmente desde la esquina inferior derecha
       for(int i=n-1;i>0;i--){
           sumVertical = 0;
           for(int j=m-1;j>0;j--){
               //Si la casilla es blanca
               if(k[j][i].getTipo()){
                   sumVertical += k[j][i].getValor();
                   //eliminamos el valor para limpiar el tablero
                   k[j][i].setValor(0);
                   if(k[j-1][i].getTipo()==false){
                        k[j-1][i].setVertical(true);
                        k[j-1][i].setValor(sumVertical);
                        sumVertical = 0;
                      
                   }
               }
               
           }
       }
   }
   /*
    Genera numeros al azar en las casillas blancas siguiendo las reglas del kakuro
    y si se estanca lanza una excepcion
   */
   /** 
    * @brief funcionalidad de generarK encargada de generar las casillas blancas y colocarlas
	* @param k = matriz de casillas a rellenar
	* @post k contiene el resultado de generar las casillas blancas ademas de lo que ya contenia
	*/
   public static boolean generarCasillasBlancas(Casilla[][] k) {
       int contadorEstancamiento;
       int value;
       try{
           for(int i=0;i<k.length;i++){
                for(int j=0;j<k[0].length;j++){
                    if(k[i][j].getTipo()){
                        contadorEstancamiento = 1000;
                        do{
                            value = generarRandomEnRango(1,10);
                            k[i][j].setValor(value);
                            contadorEstancamiento--;
                            if(contadorEstancamiento<0){
                                throw new Exception();
                            }
                        }while(!valorRepetido(k,i,j,value));
                   
                    }

                }
            }
       }
       catch(Exception e){
           System.out.println("stuck");
           return false;
       }
        
        return true;
    }
   
   /*
   
    Verifica que value en la coordenada (x,y) sea valido el tablero de casillas k
   */
   /** 
    * @brief Verifica que value en la coordenada (x,y) no esta repetido para la matriz de casillas
	* @param k = matriz de casillas a comprobar
	* @param x = fila en la que se situa la casilla
	* @param y = columna en la que se situa la casilla
	* @param value = valor a comprobar
	* @post en caso de no estar repetido devuelve true, en caso contrario devuelve false.
	*/
	
   public static boolean valorRepetido(Casilla [][]k,int x, int y, int value){
       int left,right,up,down;
       left = y-1;
       right = y+1;
       up = x-1;
       down = x+1;
       boolean finLinea = false;
       boolean valido = true;
       while(left>0 && !finLinea && valido){
           //Si es blanca verificamos que el valor es distinto
           //Si es negra termina de buscar en esa direccion
          
           if(k[x][left].getTipo()){
               valido = k[x][left].getValor() != value;
               left--;
           }
           else {
               finLinea=true;
           }
       }
       //System.out.println("left bool: "+String.valueOf(valido));
       finLinea = false;
       while(right<k[0].length && !finLinea && valido){
           //Si es blanca verificamos que el valor es distinto
           //Si es negra termina de buscar en esa direccion
           if(k[x][right].getTipo()){
               valido = k[x][right].getValor() != value;
               right++;
           }
           else {
               finLinea=true;
           }
       }
       //System.out.println("right bool: "+String.valueOf(valido));
       finLinea = false;
       while(up>0 && !finLinea && valido){
           //Si es blanca verificamos que el valor es distinto
           //Si es negra termina de buscar en esa direccion
           if(k[up][y].getTipo()){
               valido = k[up][y].getValor() != value;
               up--;
           }
           else {
               finLinea=true;
           }
       }
       //System.out.println("up bool: "+String.valueOf(valido));
       finLinea = false;
       while(down<k.length && !finLinea && valido){
           //Si es blanca verificamos que el valor es distinto
           //Si es negra termina de buscar en esa direccion
           if(k[down][y].getTipo()){
               valido = k[down][y].getValor() != value;
               down++;
           }
           else {
               finLinea=true;
           }
       }
       //System.out.println("down bool: "+String.valueOf(valido));
       
       return valido;
   }
   
   /*
    Genera "nCasillas" casillas negras en coordenadas al azar
   */
   /** 
    * @brief funcionalidad de generarK encargada de generar las casillas negras y colocarlas
	* @param k = matriz de casillas a rellenar
	* @param nCasillas = numero de casillas negras a colocar
	* @post k contiene el resultado de generar las casillas negras ademas de lo que ya contenia
	*/
   public static void generarCasillasNegras(Casilla [][]k,int nCasillas){
       int restantes = nCasillas;
       k[0][0].setTipo(false);
       restantes--;
       
       for(int i=1;i<k.length;i++){
           k[i][0].setTipo(false);
           restantes--;
       }
       for(int i=1;i<k[0].length;i++){
           k[0][i].setTipo(false);
           restantes--;
       }
       
       int i,j,horizontalRange,verticalRange;
       horizontalRange = k.length;
       verticalRange = k[0].length;
       while(restantes>0){
           //genera una coordenada al azar
           i = generarRandomEnRango(1,horizontalRange);
           j = generarRandomEnRango(1,verticalRange);
           //Si es blanca pone una negra
           if(k[i][j].getTipo()){
               k[i][j].setTipo(false);
               restantes--;
           }
       }
   }
   /** 
    * @brief Dado un rango, devuelve un valor entero aleatorio
	* @param ini = inicio del rango
	* @param fin = final del rango
	* @post Devuelve un valor entero aleatorio entre ini y fin
	*/
   //Devuelve un entero al azar entre los parametros ini(inclusivo),fin (exclusivo)
   private static int generarRandomEnRango(int ini, int fin){
       return ThreadLocalRandom.current().nextInt(ini, fin);
   }
   /** 
    * @brief Muestra la matriz de casillas k por pantalla
	* @param k = matriz de casillas a imprimir
	* @post -
	*/
   //instrumentacion para verificar visualmente la generacion del kakuro
   public static void printCellMatrix(Casilla [][]k){
       System.out.println("printing Cells!!");
       for(int i=0;i<k.length;i++){
           String line="";
           for(int j=0;j<k[0].length;j++){
               line+=k[i][j].toString();
               if(j<(k[0].length-1)) line+=","; 
           }
           System.out.println(line);
       }
   }
   /** 
    * @brief segun una dificultad, generamos un kakuro y posteriormente llamamos a la funcion solve para que lo resuelva
	* @param dificultad = indicador de dificultad: 1-facil, 2-normal, 3-dificil
	* @post muestra el resultado por pantalla
	*/
   public static void generarResolver(int dificultad) 
   {
       int n = 0;
       double dif = 0;
       Kakuro k;
       Casilla[][] gen;
      switch (dificultad) 
      {
          case 1:
              dif = 0.65;
              n = ThreadLocalRandom.current().nextInt(3,7);
              break;
          case 2:
              dif = 0.6;
              n = ThreadLocalRandom.current().nextInt(7, 11);
              break;
          case 3:
              dif = 0.55;
              n = ThreadLocalRandom.current().nextInt(11, 20);
              break;
      }
      gen = generarKakuro(n,n,dif);       
      int ID = ccd.addkakuro(gen);
      k = ccd.read_kakuro_file(ID,0);
      boolean res = solve(k,0);
      System.out.println("Solucion: ");
      printCellMatrix(result.get(0));

   }
   /** 
    * @brief segun una dificultad, generamos un kakuro
	* @param dificultad = indicador de dificultad: 1-facil, 2-normal, 3-dificil
	* @post muestra el resultado por pantalla
	*/
   public static int generar(int dificultad) //1-facil, 2-normal, 3-dificil
   {
       int n = 0;
       double dif = 0;
       Kakuro k;
       Casilla[][] gen;
      switch (dificultad)
      {
          case 1:
              dif = 0.65;
              n = ThreadLocalRandom.current().nextInt(3,7);
              break;
          case 2:
              dif = 0.6;
              n = ThreadLocalRandom.current().nextInt(7, 11);
              break;
          case 3:
              dif = 0.55;
              n = ThreadLocalRandom.current().nextInt(11, 20);
              break;
      }
      gen = generarKakuro(n,n,dif);
      int id = ccd.addkakuro(gen);
      return id;
   }
   /** 
    * @brief segun unas dimensiones y el numero de casillas negras generamos un kakuro
	* @param dimensiones = dimensiones del kakuro en n*n
        * @param numCasillasNegras = numero de casillas negras aproximadas que habra en el kakuro
	* @post muestra el resultado por pantalla
	*/
   public static int generar(int dimensiones,int numCasillasNegras) //1-facil, 2-normal, 3-dificil
   {
       int n = dimensiones;
       double dif = 0;
       Kakuro k;
       Casilla[][] gen;
       
       gen = generarKakuro(n,n,numCasillasNegras);     
      int id = ccd.addkakuro(gen);
      return id;
   }
   
   /** 
    * @brief Dado un kakuro k, comprobamos que este sea valido
	* @param k = kakuro a validar
	* @post devuelve true en caso de que el kakuro sea valido y devuelve false en caso contrario
	*/
   public static boolean validar_kakuro(Kakuro k) {
       try
       {
            return solve(k,0);
       }
       catch(Exception e)
       {
           return false;
       }
       /*
       Casilla[][] kakuro = k.getTablero();
       
       int nblancas = k.getceldas_blancas();
       int[] fila = new int[nblancas];
       int[] columna = new int [nblancas];
       int indice = 0;
       for (int i = 0; i < k.getFilas(); ++i) {
           for (int j = 0; j < k.getColumnas(); ++j) {
           
               if (kakuro[i][j].getTipo()) {
               
                   fila[indice] = i;
                   columna[indice] = j;
                   ++indice;
               }
           }
       }
       boolean correctoFila = true;
       boolean correctoColumna = true;
       for (int i = 0; i < fila.length; ++i) {
            int auxj = columna[i];
            int auxi = fila[i];
            while (kakuro[auxi][auxj].getTipo() && auxj >= 0) {
               --auxj;
            }
            if (auxj == -1) correctoFila = false; 
            
            auxj = columna[i];
            auxi = fila[i];
            
            while (kakuro[auxi][auxj].getTipo() && auxi >= 0){
                --auxi;
            } 
            
            if (auxi == -1) correctoColumna = false; 
            
       }
       
       
       return correctoColumna ||  correctoFila;
       */
   }
   
   /**
    * @brief devuelve el resultado de la solucion
	* 
	* @return resultado del solver
	*/
   public static Casilla[][] getResult()
   {
       Casilla[][] x = result.get(0);
       return x;
   }

   
}
