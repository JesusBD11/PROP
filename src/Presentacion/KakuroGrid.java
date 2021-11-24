/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @file KakuroGrid.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief panel que contiene el Kakuro en Juego
 * 
 * Panel que contiene el Kakuro en Juego, además de gestionar cada una de las casillas que contiene.
 */
 
 /**
 * @class KakuroGrid
 * @brief Clase que extiende JPanel que inicializa, gestiona y modifica el kakuro en Juego y sus casillas.
 */

 class KakuroGrid extends JPanel {
        private int gridWidth,gridHeight;
        private VistaKakuro vk;
        private HashMap<String,KakuroPanel> cells;
        KakuroGrid(VistaKakuro vk, int w, int h,boolean editionMode, String [][] tablero) {
            super(new GridBagLayout());
            this.vk = vk;
            gridWidth = w;
            gridHeight = h;
            cells = new HashMap<String,KakuroPanel>();
            GridBagConstraints c = new GridBagConstraints();
            /** construct the grid */
            for (int i=0; i<w; i++) {
                for (int j=0; j<h; j++) {
                    c.weightx = 1.0;
                    c.weighty = 1.0;
                    c.fill = GridBagConstraints.BOTH;
                    c.gridx = j;
                    c.gridy = i;
                    String key = i+" "+j;
                    boolean color  = false;
                    String value = "";
                    if(!editionMode){
                        color = !tablero[i][j].equals("?");
                        value = tablero[i][j];
                    }
                    cells.put(key,new KakuroPanel(this,i, j,value,color,editionMode) );
                    add(cells.get(key), c);
                }
            }
            /*for (Component cell : this.getComponents()) {
                if (cell instanceof KakuroPanel) { 
                   String content = ((KakuroPanel)cell).toString();
                   System.out.println(content);
                }
            }*/
            /** create a black border */ 
            setBorder(BorderFactory.createLineBorder(Color.black)); 
            getGridContents();
        }
        
        public int getGridWidth(){return gridWidth;}
        public int getGridHeight(){return gridHeight;}
        
        public String getGridContents(){
            String contents ="";
            for (int i=0; i<gridWidth; i++) {
                for (int j=0; j<gridHeight; j++) {
                    String key = i+" "+j;
                    if(j>0)contents +=" "+(cells.get(key)).toString();
                    else contents +=(cells.get(key)).toString();
                }
                contents = contents+"\n";
            }
            System.out.println("imprimiendo kakuro grid");
            System.out.println(contents);
            
            return contents;
        }
        public String[][] getGridContentsMatrix(){
            String[][] contents = new String[gridWidth][gridHeight];
            for (int i=0; i<gridWidth; i++) {
                for (int j=0; j<gridHeight; j++) {
                    String key = i+" "+j;
                    contents[i][j]=(cells.get(key)).toString();
                }
            }
            return contents;
        }
        public int comprobarMovimiento(int x, int y, String value)
        {           
            return vk.comprobarMovimiento(x,y,value,getGridContentsMatrix());
        }
        void setContents(String[][] contents) {
            for (int i=0; i<gridWidth; i++) {
                for (int j=0; j<gridHeight; j++) {
                    String key = i+" "+j;
                    (cells.get(key)).setValue(contents[i][j]);
                }
            }
        }
        
        void setEditable(boolean editable){
            for (int i=0; i<gridWidth; i++) {
                for (int j=0; j<gridHeight; j++) {
                    String key = i+" "+j;
                    (cells.get(key)).setEditable(editable);
                }
            }
        
        }
        @Override
        public void setEnabled(boolean enabled) {
            System.out.println("Disabling panels in grid");
            for (int i=0; i<gridWidth; i++) {
                for (int j=0; j<gridHeight; j++) {
                    String key = i+" "+j;
                    (cells.get(key)).setEnabled(enabled);
                }
            }

        }
    }
