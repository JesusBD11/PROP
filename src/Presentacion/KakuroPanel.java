/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @file KakuroPanel.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief panel correspondiente a una casilla del Kakuro en Juego.
 * 
 * Panel que contiene una casilla del Kakuro en juego, desde la que se piden comprobaciones de movimientos a Dominio y cambia su comportamiento dependiendo del resultado.
 */
 
 /**
 * @class KakuroPnael
 * @brief Clase que extiende JPanel que inicializa, gestiona y modifica una casilla del kakuro en juego.
 */
class KakuroPanel extends JPanel {
        
        private String value; //the number it would display
        private int x, y; //the x,y position on the grid
        private int fontSize;
        private JLabel blackSquaresLabel;
        private JTextField whiteSquaresField;
        private boolean editionMode;
        private boolean isWhiteCell;
        private KakuroGrid grid;
        KakuroPanel(KakuroGrid kg, int x, int y,String value,boolean color,boolean editionMode) {
            super();
            this.editionMode = editionMode;
            grid = kg;
            setBorder(BorderFactory.createLineBorder(Color.black));
            setLayout(new BorderLayout());     
            setPreferredSize(new Dimension(10,10));
            
            blackSquaresLabel = new JLabel("1");
            //ImageIcon ii = new ImageIcon(this.getClass().getResource("pics.gif"));
            this.x = x;
            this.y = y;
            this.value = value;
            if (value.length() == 1)
            {
                if (!value.equals("*")) color = false;
            }
            //blackSquaresLabel.setText("Black");
            
            fontSize = 20;
            //System.out.println(fontSize);
            
            if(!editionMode){
                if(color){
                    setBlack();
                }
                else{
                    setWhite();
                }
            }
            else{
                setWhite();
            }
            
        }
        
        
        private void setBlack(){
            isWhiteCell = false;
            setBackground(Color.DARK_GRAY);
            blackSquaresLabel.setHorizontalAlignment(JLabel.CENTER);
            blackSquaresLabel.setForeground(Color.LIGHT_GRAY);
            blackSquaresLabel.setFont(new Font("Serif", Font.PLAIN, fontSize));
            blackSquaresLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                blackSquaresLabelMouseClicked(me);
               }
            });
            blackSquaresLabel.setText(value);
            add(blackSquaresLabel,java.awt.BorderLayout.CENTER);
       
        }
        
        private void setWhite(){
            isWhiteCell = true;
            setBackground(Color.LIGHT_GRAY);
            whiteSquaresField = new JTextField("?");
            whiteSquaresField.setBackground(Color.LIGHT_GRAY);
            whiteSquaresField.setHorizontalAlignment(JTextField.CENTER);
            whiteSquaresField.setFont(new Font("Serif", Font.PLAIN, fontSize));
            whiteSquaresField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                    whiteSquaresFieldActionPerformed(event);
                }
            });
            /*if(editionMode){
                whiteSquaresField.addMouseListener(new MouseAdapter()  {
                    public void mouseClicked(MouseEvent me) {
                        changeBackground();
                    }
                 });
            }*/
            whiteSquaresField.setText(value);
            add(whiteSquaresField,java.awt.BorderLayout.CENTER);
            
        }
        /*private void changeBackground(){
            System.out.println("Clicked on tile at"+x+","+y );
            //removeAll();
            //repaint();
            //revalidate();
            if(whiteSquaresField.getBackground().equals(Color.LIGHT_GRAY)){
                whiteSquaresField.setBackground(Color.DARK_GRAY);
                whiteSquaresField.setForeground(Color.LIGHT_GRAY);
                System.out.println("changing color to dark ");
                whiteSquaresField.setCaretColor(Color.WHITE);
                System.out.println(whiteSquaresField.getCaretColor());
                //this.setBackground(Color.DARK_GRAY);
            } 
            else{
                //setWhite();
                System.out.println("changing color to light ");
                whiteSquaresField.setBackground(Color.LIGHT_GRAY);
                whiteSquaresField.setForeground(Color.DARK_GRAY);
                whiteSquaresField.setCaretColor(new Color(51, 51, 51));
                System.out.println(whiteSquaresField.getCaretColor());
                // this.setBackground(Color.LIGHT_GRAY);
            }
         
            //afegint JPanel
            //add(new WelcomeToKenken());
            //repaint();
            //revalidate();
        } */
        private void blackSquaresLabelMouseClicked(MouseEvent me) {
            System.out.println("Label pressed at "+ x+","+y);
        }
        private void whiteSquaresFieldActionPerformed(ActionEvent evt) {
          String fieldText = whiteSquaresField.getText();
          int result;
          if(!editionMode)
          {
              String valorAnterior = fieldText;
              whiteSquaresField.setText("?");
              result = grid.comprobarMovimiento(x,y,fieldText);
              if (result == 3 || result == 6 || result ==7){
                  whiteSquaresField.setBackground(new Color(226, 11, 0));
                  //whiteSquaresField.setForeground(Color.LIGHT_GRAY);
                  //whiteSquaresField.setCaretColor(Color.LIGHT_GRAY);
              }
              else{
                  whiteSquaresField.setBackground(Color.LIGHT_GRAY);
                  //whiteSquaresField.setForeground(Color.DARK_GRAY);
                  //whiteSquaresField.setCaretColor(Color.DARK_GRAY);
              }
              whiteSquaresField.setText(valorAnterior);
          }
          if(fieldText.equals("")) System.out.println("Text empty at " + x+","+y);
          else System.out.println("Text "+fieldText+" at " + x+","+y);     
        }
        
        @Override
        public String toString(){
            return (isWhiteCell)?whiteSquaresField.getText():blackSquaresLabel.getText();
        }
        public String getValue() { return whiteSquaresField.getText(); }

        //getters for x and y
        public int getfila()
        {
                return x;
        }
        public int getColumna()
        {
                return y;
        }
        public void setValue(String val) { 
            if(isWhiteCell) {
                whiteSquaresField.setText(val);
                whiteSquaresField.setEditable(false);
            }
            else{ 
                blackSquaresLabel.setText(val);
            }
        }

        void setEditable(boolean editable) {
            if(isWhiteCell) {
                whiteSquaresField.setEditable(editable);
            }
        }
        @Override
        public void setEnabled(boolean enabled) {
            if(isWhiteCell){
                for( ActionListener al : whiteSquaresField.getActionListeners() ) {
                    whiteSquaresField.removeActionListener( al );
                }
                whiteSquaresField.setEditable(enabled);
            }
        }
    }