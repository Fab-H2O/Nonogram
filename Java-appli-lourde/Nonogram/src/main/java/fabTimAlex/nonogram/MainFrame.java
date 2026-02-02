/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabTimAlex.nonogram;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author fbnhe
 */
public class MainFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    
    public MainFrame() throws HeadlessException {
        
        // le nom de la fenetre
        super("Le super genial nonogram");
        
        var gridPanel = new GridPanel();
        
        /** on donne une ID pour que le pixel tourne a une certaine couleur
         *  ici en exemple * prendre une couleur exemple:
         * addState(3, Color.GREEN, Color.pink, "*");
         * 3 -> ID pour la selection
         * Color.GREEN -> l'object "*" prendra cette couleur
         * Color.pink -> la case prendra cette couleur
         * 
         */
        gridPanel.addState(1, Color.blue);
        gridPanel.addState(2, Color.WHITE, Color.red, "X");
        gridPanel.addState(3, Color.GREEN, Color.pink, "X");
        
        
        // force 1 pixel a des cordonnees de prendre une certaine couleur
        gridPanel.setGridListener(()->{
//            System.out.println("test");
            gridPanel.setCell(1,1,1);
            gridPanel.setCell(2,3,4);
            gridPanel.setCell(3,5,7);
        });
        
        setContentPane(gridPanel);
        
        // permet de creer une fenetre avec une resolution donner, rendre visible
        // et la fermer
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // resolution de 800 par 800 pixels
        setSize(800,800);
        setVisible(true);
    }
}
