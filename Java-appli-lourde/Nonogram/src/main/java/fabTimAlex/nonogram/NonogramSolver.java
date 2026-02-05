/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabTimAlex.nonogram;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fbnhe
 * Solveur par brute force qui commence par le bas.
 */
public class NonogramSolver {

    private final List<List<Integer>> ligneIndice;
    private final List<List<Integer>> colonneIndice;
    private final int ligne;
    private final int colonne;

    private boolean[][] solution;

    // initialize le solveur de nono gram a partir des indice dans les colonnes et lignes
    // this.solution permet de creer une grille a partir des lignes et colonnes
    public NonogramSolver(List<List<Integer>> ligneIndice, List<List<Integer>> colonneIndice) {
        this.ligneIndice = ligneIndice;
        this.colonneIndice = colonneIndice;
        this.ligne = ligneIndice.size();
        this.colonne = colonneIndice.size();
        this.solution = new boolean[ligne][colonne];
    }
    
    
    // permet de generer toutes les possibilites pour chaque ligne, si rien est trouver il retourne rien
    public boolean[][] solve() {
        List<List<boolean[]>> linePossiblilites = new ArrayList<>();

        // genere toutes les lignes possibles pour chaque ligne
        for (List<Integer> indices : ligneIndice) {
            linePossiblilites.add(generateLinePossibilities(colonne, indices));
        }

        boolean succes = retour(0, linePossiblilites);
        return succes ? solution : null;
    }
    
    
    // il essaie toute les combination possibles jusqu'a une reponse valide est trouver, c'est du brute force, soit un test des candidats.
    private boolean retour(int line, List<List<boolean[]>> linePossiblilites) {
        if (line == ligne) {
            return verifToutColonnes();
        }

        for (boolean[] candidate : linePossiblilites.get(line)) {
            solution[line] = candidate;

            if (VerifColonnes(line)) {
                if (retour(line + 1, linePossiblilites)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    // 
    private boolean VerifColonnes(int derniereLigne) {
        for (int c = 0; c < colonne; c++) {
            List<Integer> indices = colonneIndice.get(c);
            List<Integer> seq = new ArrayList<>();

            int compte = 0;
            for (int r = 0; r <= derniereLigne; r++) {
                if (solution[r][c]) compte++;
                else {
                    if (compte > 0) seq.add(compte);
                    compte = 0;
                }
            }
            if (compte > 0) seq.add(compte);

            // verification partielle
            for (int i = 0; i < seq.size(); i++) {
                if (i >= indices.size()) return false;
                if (seq.get(i) > indices.get(i)) return false;
            }
        }
        return true;
    }

    private boolean verifToutColonnes() {
        for (int c = 0; c < colonne; c++) {
            if (!CalculeIndiceForColumn(c).equals(colonneIndice.get(c))) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> CalculeIndiceForColumn(int col) {
        List<Integer> indices = new ArrayList<>();
        int compte = 0;
        for (int r = 0; r < ligne; r++) {
            if (solution[r][col]) compte++;
            else {
                if (compte > 0) indices.add(compte);
                compte = 0;
            }
        }
        if (compte > 0) indices.add(compte);
        if (indices.isEmpty()) indices.add(0);
        return indices;
    }

    private List<boolean[]> generateLinePossibilities(int length, List<Integer> indices) {
        List<boolean[]> results = new ArrayList<>();
        generateRec(results, new boolean[length], 0, indices, 0);
        return results;
    }

    private void generateRec(List<boolean[]> results, boolean[] line, int index,
                             List<Integer> indices, int clueIndex) {

        if (clueIndex == indices.size()) {
            // remplir le reste en blanc
            for (int i = index; i < line.length; i++) line[i] = false;
            results.add(line.clone());
            return;
        }

        int block = indices.get(clueIndex);

        for (int start = index; start + block <= line.length; start++) {
            // remplir blancs jusqu'au debut du bloc
            for (int i = index; i < start; i++) line[i] = false;

            // remplir le bloc
            for (int i = start; i < start + block; i++) line[i] = true;

            // case blanche obligatoire apres un bloc (sauf si dernier)
            if (start + block < line.length) {
                line[start + block] = false;
            }

            generateRec(results, line, start + block + 1, indices, clueIndex + 1);
        }
    }
}