/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Willian
 */
public class Banco {
    public static Connection abrirConexao() {        
        try {
            String url = "jdbc:hsqldb:file:exemplo";
            Properties propriedades = new Properties();
            propriedades.setProperty("user","SA");
            propriedades.setProperty("password","");
            return DriverManager.getConnection(url, propriedades);
        } catch (SQLException ex) {
            System.out.println("Erro ao abrir conexão \n" + ex);
            return null;
        }
    }
}
