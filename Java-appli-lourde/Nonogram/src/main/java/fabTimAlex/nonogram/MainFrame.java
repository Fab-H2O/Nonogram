/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabTimAlex.nonogram;

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
        
        setContentPane(new GridPanel());
        
        // permet de creer une fenetre avec une resolution donner, rendre visible
        // et la fermer
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setVisible(true);
    }
}
