package crud;

import crud.database.Banco;
import crud.visao.MenuPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;

/**
 *
 * @author Willian
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
         Connection conexao = Banco.abrirConexao();        
        
        Statement declaracao = conexao.createStatement();
        declaracao.execute("CREATE TABLE if not exists ESTADO(ID INT NOT NULL IDENTITY, NOME VARCHAR(255) NOT NULL UNIQUE, SIGLA CHAR(2) NOT NULL UNIQUE, STATUS CHAR(1) DEFAULT 'A' NOT NULL);");
        
        declaracao.execute("CREATE TABLE if not exists CIDADE(ID INT NOT NULL IDENTITY, NOME VARCHAR(255) NOT NULL UNIQUE, IDESTADO INTEGER NOT NULL);");
        
        declaracao.execute("CREATE TABLE if not exists PESSOA(ID INT NOT NULL IDENTITY, NOME VARCHAR(255) NOT NULL UNIQUE, IDCIDADE INTEGER NOT NULL, IDESTADO INTEGER NOT NULL);");
        
        //declaracao.execute("ALTER TABLE CIDADE ADD FOREIGN KEY (IDESTADO) REFERENCES ESTADO(ID)");
        
        conexao.close();
        
         /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                                           
                
                final MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setLocationRelativeTo(null);
                menuPrincipal.setExtendedState( menuPrincipal.getExtendedState()|JFrame.MAXIMIZED_BOTH );
                menuPrincipal.setVisible(true);
            }
        });
    }
    
}
