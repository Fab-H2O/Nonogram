/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabTimAlex.nonogram;
import java.sql.*;

/**
 *
 * @author fbnhe
 */
public class JDBC {
    static final String DB_URL = "jdbc:mysql://localhost/nonogram"; // URL de la BDD
    static final String USER = "root"; // nom d'utilisateur
    static final String PASS = "root"; // mot de passe

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connexion à la base ..."); // Etape 1 : Connexion à la base
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Création d'un état..."); // Etape 2 : Exécution d'une requête
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, nom, prenom, age FROM personnes";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) { // Etape 3: Extraction des données depuis le ResultSet
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", Nom: " + nom);
                System.out.println(",Prenom: " + prenom);
            }
            rs.close(); // Etape 4 : Nettoyage du contexte
            stmt.close();
            conn.close();
        } catch (SQLException se) { // Gestion des exceptions
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
}
