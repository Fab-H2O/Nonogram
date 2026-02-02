/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabTimAlex.nonogram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author fbnhe
 */
public class GridPanel extends JPanel {

    
            
            public interface GridListener {
                void gridReady();
            }
                
            private GridListener gridListener;


            public void setGridListener(GridListener gridListener) {
                this.gridListener = gridListener;
            }
            
            
            // obligatoire pour eviter les avertissement
            private static final long serialVersionUID = 1L;
            
            // ajout de police d ecriture et 28 c'est la taille
            private static final Font font = new Font("Courier", Font.BOLD, 28);
            
            
            // taille des carrer 30 par 30 pixels
            private static final int CellSize = 30;


            private int gridWidth;
            private int gridHeight;
            private int leftMargin;
            private int topMargin;
            private Map<Integer, BufferedImage> statesMap = new HashMap<>();
            private Integer [][] states;

            // panneau on choisit la couleur de l arriere plan
            public GridPanel(){
                setBackground(Color.GRAY);
                
                addState(0, Color.ORANGE);

            }
            @Override
            public void paint(Graphics g){
                // le nom de la fenetre definit par super
                super.paint(g);
                // cast graphics2d a g
                Graphics2D g2 = (Graphics2D)g;

                // 1.calcul la largeur et la hauteur de la grille
                int width = getWidth();
                int height = getHeight();
                // on rajoute -1 pour augmenter la marge
                gridWidth = (width / CellSize) - 1;
                gridHeight = (height / CellSize) - 1;
                
                initCells(gridWidth, gridHeight);

                // 2. Calcule l espace restant
                int xSpare = width - (gridWidth * CellSize);
                int ySpare = height - (gridHeight * CellSize);

                // 3. calcule les marges pas besoin de faire rightMargin ou BottomMargin
                leftMargin = xSpare / 2;
                topMargin = ySpare / 2;

                //4. dessine les carre et l arriere plan
                g2.setColor(Color.blue);
                g2.fillRect(leftMargin, topMargin, width + 1 - xSpare, height + 1 - ySpare);

                g2.setColor(Color.GRAY);

                for(int gridy = 0; gridy < gridHeight; gridy++){
                    for(int gridx = 0; gridx < gridHeight; gridx++){
                        int x = gridx * CellSize + leftMargin;
                        int y = gridy * CellSize + topMargin;
                        
                        Integer state = states[gridy][gridx];
                        
                        //System.out.println(state); // test unitaire
                        
                        BufferedImage bi = statesMap.get(state);
                        
                        //System.out.println(bi); //test unitaire
                        g2.drawImage(bi, x + 1, y + 1, null);
                    }
                }

            }
            
            private void initCells(int gridWidth, int gridHeight){
                if(states != null){
                    return;
                }
                
                states = new Integer[gridHeight][gridWidth];
                Arrays.stream(states).forEach(a -> Arrays.fill(a,0));
                
                if(gridListener != null){
                    gridListener.gridReady();
                }
            }
            
            public void addState(Integer state, Color background){
                addState(state, Color.white,background, "");
            }

            public void addState(Integer state, Color foreground, Color background, String character){
                BufferedImage bi = new BufferedImage(CellSize - 1,CellSize - 1, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bi.createGraphics();
                g.setColor(background);
                g.fillRect(0, 0, CellSize - 1, CellSize - 1);
                
                
                if(character.length() !=0){
                    g.setColor(foreground);
                    g.setFont(font);
                    
                    FontRenderContext frc = g.getFontRenderContext();
                    TextLayout textlayout = new TextLayout(character, font, frc);
                    Rectangle2D bounds = textlayout.getBounds();
                    
                    float x = CellSize/2 - (float)bounds.getCenterX();
                    float y = CellSize/2 - (float)bounds.getCenterY();
                    textlayout.draw(g,x,y);
                }
                g.dispose();
                statesMap.put(state,bi);
            }
            
            public void setCell(int state, int x, int y) {
                states[y][x] = state;
            }
                    
        }


