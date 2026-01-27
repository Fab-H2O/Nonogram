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
 */
public class NonogramSolver {

    private final List<List<Integer>> ligneIndice;
    private final List<List<Integer>> colonneIndice;
    private final int ligne;
    private final int colonne;

    private boolean[][] solution;

    public NonogramSolver(List<List<Integer>> ligneIndice, List<List<Integer>> colonneIndice) {
        this.ligneIndice = ligneIndice;
        this.colonneIndice = colonneIndice;
        this.ligne = ligneIndice.size();
        this.colonne = colonneIndice.size();
        this.solution = new boolean[ligne][colonne];
    }

    public boolean[][] solve() {
        List<List<boolean[]>> rowPossibilities = new ArrayList<>();

        // genere toutes les lignes possibles pour chaque ligne
        for (List<Integer> clues : ligneIndice) {
            rowPossibilities.add(generateLinePossibilities(colonne, clues));
        }

        boolean success = backtrack(0, rowPossibilities);
        return success ? solution : null;
    }

    private boolean backtrack(int row, List<List<boolean[]>> rowPossibilities) {
        if (row == ligne) {
            return checkAllColumns();
        }

        for (boolean[] candidate : rowPossibilities.get(row)) {
            solution[row] = candidate;

            if (checkColumnsUpTo(row)) {
                if (backtrack(row + 1, rowPossibilities)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumnsUpTo(int lastRow) {
        for (int c = 0; c < colonne; c++) {
            List<Integer> clues = colonneIndice.get(c);
            List<Integer> seq = new ArrayList<>();

            int count = 0;
            for (int r = 0; r <= lastRow; r++) {
                if (solution[r][c]) count++;
                else {
                    if (count > 0) seq.add(count);
                    count = 0;
                }
            }
            if (count > 0) seq.add(count);

            // verification partielle
            for (int i = 0; i < seq.size(); i++) {
                if (i >= clues.size()) return false;
                if (seq.get(i) > clues.get(i)) return false;
            }
        }
        return true;
    }

    private boolean checkAllColumns() {
        for (int c = 0; c < colonne; c++) {
            if (!CalculeIndiceForColumn(c).equals(colonneIndice.get(c))) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> CalculeIndiceForColumn(int col) {
        List<Integer> clues = new ArrayList<>();
        int count = 0;
        for (int r = 0; r < ligne; r++) {
            if (solution[r][col]) count++;
            else {
                if (count > 0) clues.add(count);
                count = 0;
            }
        }
        if (count > 0) clues.add(count);
        if (clues.isEmpty()) clues.add(0);
        return clues;
    }

    private List<boolean[]> generateLinePossibilities(int length, List<Integer> clues) {
        List<boolean[]> results = new ArrayList<>();
        generateRec(results, new boolean[length], 0, clues, 0);
        return results;
    }

    private void generateRec(List<boolean[]> results, boolean[] line, int index,
                             List<Integer> clues, int clueIndex) {

        if (clueIndex == clues.size()) {
            // remplir le reste en blanc
            for (int i = index; i < line.length; i++) line[i] = false;
            results.add(line.clone());
            return;
        }

        int block = clues.get(clueIndex);

        for (int start = index; start + block <= line.length; start++) {
            // remplir blancs jusqu'au debut du bloc
            for (int i = index; i < start; i++) line[i] = false;

            // remplir le bloc
            for (int i = start; i < start + block; i++) line[i] = true;

            // case blanche obligatoire apres un bloc (sauf si dernier)
            if (start + block < line.length) {
                line[start + block] = false;
            }

            generateRec(results, line, start + block + 1, clues, clueIndex + 1);
        }
    }
}