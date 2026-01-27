/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fabTimAlex.nonogram;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author fbnhe
 */



public class Nonogram {

    private final int ligne;
    private final int colonne;
    private final boolean[][] grille;
    private final List<List<Integer>> ligneIndice;
    private final List<List<Integer>> colonneIndice;
    

    /**
     * Cree un nonogram avec une grille aléatoire.
     *
     * @param ligne nombre de lignes (entre 2 et 100)
     * @param colonne nombre de colonnes (entre 2 et 100)
     */
    public Nonogram(int ligne, int colonne) {
        if (ligne < 2 || colonne < 2 || ligne > 100 || colonne > 100) {
            throw new IllegalArgumentException("Taille invalide : 2 <= ligne, colonne <= 100");
        }
        this.ligne = ligne;
        this.colonne = colonne;
        this.grille = new boolean[ligne][colonne];
        this.ligneIndice = new ArrayList<>();
        this.colonneIndice = new ArrayList<>();

        genereGrilleAleatoire();
        CalculeIndice();
    }

    private void genereGrilleAleatoire() {
        Random random = new Random();
        // Probabilite simple 50% case noire / 50% case vide
        for (int r = 0; r < ligne; r++) {
            for (int c = 0; c < colonne; c++) {
                grille[r][c] = random.nextBoolean();
            }
        }
    }

    private void CalculeIndice() {
        // Indices pour les lignes
        for (int r = 0; r < ligne; r++) {
            ligneIndice.add(calculeLineIndice(grille[r]));
        }

        // Indices pour les colonnes
        for (int c = 0; c < colonne; c++) {
            boolean[] column = new boolean[ligne];
            for (int r = 0; r < ligne; r++) {
                column[r] = grille[r][c];
            }
            colonneIndice.add(calculeLineIndice(column));
        }
    }

    private List<Integer> calculeLineIndice(boolean[] line) {
        List<Integer> clues = new ArrayList<>();
        int count = 0;
        for (boolean cellule : line) {
            if (cellule) {
                count++;
            } else {
                if (count > 0) {
                    clues.add(count);
                    count = 0;
                }
            }
        }
        if (count > 0) {
            clues.add(count);
        }
        if (clues.isEmpty()) {
            // Convention : une ligne/colonne vide -> indice "0"
            clues.add(0);
        }
        return clues;
    }

    public int getligne() {
        return ligne;
    }

    public int getcolonne() {
        return colonne;
    }

    public boolean[][] getgrille() {
        return grille;
    }

    public List<List<Integer>> getligneIndice() {
        return ligneIndice;
    }

    public List<List<Integer>> getcolonneIndice() {
        return colonneIndice;
    }

    public void printgrille() {
        System.out.println("Grille (X = noir, . = vide) :");
        for (int r = 0; r < ligne; r++) {
            for (int c = 0; c < colonne; c++) {
                System.out.print(grille[r][c] ? "X " : ". ");
            }
            System.out.println();
        }
    }

    public void printClues() {
        System.out.println("Indices lignes :");
        for (int r = 0; r < ligne; r++) {
            System.out.print("L" + (r + 1) + " : ");
            System.out.println(ligneIndice.get(r));
        }

        System.out.println("Indices colonnes :");
        for (int c = 0; c < colonne; c++) {
            System.out.print("C" + (c + 1) + " : ");
            System.out.println(colonneIndice.get(c));
        }
    }

    public static void main(String[] args) {
        // Ici, on creer un popup qui demande le nombre de ligne et colonne pour generer le nonogram
        int nbLigne = Integer.parseInt(JOptionPane.showInputDialog("Entrer la taille de la grille, ici le nombre de ligne:"));
        int nbColonne = Integer.parseInt(JOptionPane.showInputDialog("Entrer la taille de la grille, ici le nombre de colonne:"));
        
        
        Nonogram nonogram = new Nonogram(nbLigne, nbColonne);
        nonogram.printgrille();
        System.out.println();
        nonogram.printClues();
    }
}

