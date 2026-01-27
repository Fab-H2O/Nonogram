/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabTimAlex.nonogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
            private static final long serialVersionUID = 1L;
            // euh, tuto a mis cette valeur par defaut, je rectifirait apres
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
                // le nom de la fenetre
                super.paint(g);
                // cast graphics2d a g
                Graphics2D g2 = (Graphics2D)g;

                // 1.calcul la largeur et la hauteur de la grille
                int width = getWidth();
                int height = getHeight();

                gridWidth = (width / CellSize) - 1;
                gridHeight = (height / CellSize) - 1;
                
                initCells(gridWidth, gridHeight);

                // 2. Calcule l espace restant
                int xSpare = width - (gridWidth * CellSize);
                int ySpare = height - (gridHeight * CellSize);

                // 3. calcule les marges
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
                        
                        //System.out.println(state);
                        
                        BufferedImage bi = statesMap.get(state);
                        //System.out.println(bi);
                        g2.drawImage(bi, x + 1, y + 1, null);
                    }
                }

            }
            
            private void initCells(int gridWidth, int gridHeight){
                if(states != null){
                    return;
                }
                
                states = new Integer[gridHeight][gridHeight];
                Arrays.stream(states).forEach(a -> Arrays.fill(a,0));
            }

            public void addState(Integer state, Color background){
                BufferedImage bi = new BufferedImage(CellSize - 1,CellSize - 1, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bi.createGraphics();
                g.fillRect(0, 0, CellSize - 1, CellSize - 1);
                g.dispose();
                
                statesMap.put(state,bi);
            }
                    
        }


