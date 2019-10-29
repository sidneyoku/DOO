/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpr.paranavai.crudbasicoatividadeii.visao;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Cidade;
import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Estado;
import br.ifpr.paranavai.crudbasicoatividadeii.exceptions.PersistenceException;
import br.ifpr.paranavai.crudbasicoatividadeii.interfaces.ICidade;
import br.ifpr.paranavai.crudbasicoatividadeii.interfaces.IEstado;
import br.ifpr.paranavai.crudbasicoatividadeii.persistencia.CidadeDAO;
import br.ifpr.paranavai.crudbasicoatividadeii.persistencia.EstadoDAO;
import br.ifpr.paranavai.crudbasicoatividadeii.utils.Utils;
import br.ifpr.paranavai.crudbasicoatividadeii.validacao.Validacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Willian
 */
public class CadastroCidade extends javax.swing.JFrame {
    
    private ArrayList<Integer> idEstados = new ArrayList<>();
    private Cidade cidade;

    /**
     * Creates new form CadastroCidade
     */
    public CadastroCidade() {
        initComponents();
        
//        SwingUtilities.invokeLater(preencherComboEstadoAction());
        preencherComboEstado();
        habilitarDesabilitarCampos(null);
        SwingUtilities.invokeLater(newAtualizaCidadesAction());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        painelCidades = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        botaoIncluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        campoId = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoDeletar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cidade");

        jLabel1.setText("Nome:");

        painelCidades.setEditable(false);
        jScrollPane1.setViewportView(painelCidades);

        jLabel2.setText("Estado:");

        comboEstado.setSelectedItem(1);

        botaoIncluir.setText("Incluir");
        botaoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirActionPerformed(evt);
            }
        });

        jLabel3.setText("Id:");

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoDeletar.setText("Deletar");
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoIncluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoExcluir))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botaoBuscar))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoDeletar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoIncluir)
                    .addComponent(botaoAlterar)
                    .addComponent(botaoExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar)
                    .addComponent(botaoDeletar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed
        habilitarDesabilitarCampos(TipoBotao.incluir);
    }//GEN-LAST:event_botaoIncluirActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        if (Utils.isNumber(campoId.getText())) {
           try {
                ICidade iCidade = new CidadeDAO();
                Cidade c = iCidade.findById(Integer.parseInt(campoId.getText()));
                if (c != null) {
                    campoNome.setText(c.getNome());
                    comboEstado.setSelectedItem(c.getIdEstado().getNome());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                        ex.getMessage(), "Erro ao pesquisar Cidade.", JOptionPane.ERROR_MESSAGE);
            } 
        } else {
            JOptionPane.showMessageDialog(this, "Digite número do Id válido.", 
                    "Campo Id", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        habilitarDesabilitarCampos(TipoBotao.editar);
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        habilitarDesabilitarCampos(TipoBotao.excluir);
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        String estadoStr = comboEstado.getSelectedItem().toString();
        List<String> listaObrigatorio = Validacao.ValidaCamposCidade(campoNome.getText(), estadoStr);
        
        if (listaObrigatorio.size() > 0) {
            String msg = "";
            msg = listaObrigatorio.stream().map((str) -> str + "\n").reduce(msg, String::concat);
            JOptionPane.showMessageDialog(this, msg, "Valida��o", JOptionPane.WARNING_MESSAGE);
        } else {
            if (campoId.isEnabled()) {
                if(!Utils.isNumber(campoId.getText())) {
                    JOptionPane.showMessageDialog(this, "Preencher o campo Id v�lido.", 
                            "Valida��o", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
            IEstado iEstado = new EstadoDAO();
            Estado e = iEstado.getEstadoByNome(estadoStr);
            
            Cidade c = new Cidade(
                    campoId.getText().isBlank() ? null : Integer.parseInt(campoId.getText()), 
                    campoNome.getText().trim(), e);
            ICidade iCidade = new CidadeDAO();
            iCidade.save(c);
            
            SwingUtilities.invokeLater(newAtualizaCidadesAction());
            habilitarDesabilitarCampos(null);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        if(Utils.isNumber(campoId.getText())) {
            try {
                ICidade iCidade = new CidadeDAO();
                Cidade c = iCidade.findById(Integer.parseInt(campoId.getText()));
                
                if (c != null)
                    iCidade.remove(c);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                        ex.getMessage(), "Erro ao excluir Cidade.", JOptionPane.ERROR_MESSAGE);
            }
            
            SwingUtilities.invokeLater(newAtualizaCidadesAction());
            habilitarDesabilitarCampos(null);
        } else {
            JOptionPane.showMessageDialog(this, "Preencher o campo Id válido.", 
                    "Validação", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botaoDeletarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(CadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroCidade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoIncluir;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField campoId;
    private javax.swing.JTextField campoNome;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane painelCidades;
    // End of variables declaration//GEN-END:variables

    public Runnable newAtualizaCidadesAction() {
        return new Runnable() {
            public void run() {
                try {
                    ICidade iCidade = new CidadeDAO();
                    List<Object[]> rows = iCidade.getAll();
                    String strCidade = "";
                    
                    List<Cidade> result = new ArrayList<>(rows.size());
                    
                    for (Object[] row : rows) {                
                        result.add(new Cidade((Integer) row[0], (String) row[2], (Estado) row[1]));
                    }
                    
                    for (Cidade cidade : result) {
                        strCidade += cidade.getId() + " - ";
                        strCidade += cidade.getNome() + "\n";
                    }
                    
                    painelCidades.setText(strCidade);
                } catch (PersistenceException ex) {
                    JOptionPane.showMessageDialog(CadastroCidade.this,
                        ex.getMessage(), "Erro ao consultar Cidade(s)", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }
    
    public Runnable preencherComboEstadoAction() {
        return new Runnable() {
            public void run() {
                try {
                    IEstado iEstado = new EstadoDAO();
                    List<Object[]> rows = iEstado.getAll();
                    
                    List<Estado> result = new ArrayList<>(rows.size());
            
                    for (Object[] row : rows) {
                        result.add(new Estado(null, (String) row[0], (String) row[1]));
                    }

                    for (Estado estado : result) {
                        idEstados.add(estado.getId());
                        comboEstado.addItem(estado.getNome());
                    }
                    
//                    listEstados.stream().map((estado) -> {
//                        idEstados.add(estado.getId());
//                        return estado;
//                    }).forEachOrdered((estado) -> {
//                        comboEstado.addItem(estado.getNome());
//                    });
                    
                } catch (PersistenceException ex) {
                    JOptionPane.showMessageDialog(CadastroCidade.this,
                        ex.getMessage(), "Erro ao consultar Cidade(s)", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }
    
    private void preencherComboEstado() {
        try {
            IEstado iEstado = new EstadoDAO();
            List<Object[]> rows = iEstado.getAll();
            List<Estado> result = new ArrayList<>(rows.size());
            
            for (Object[] row : rows) {
                result.add(new Estado(null, (String) row[0], (String) row[1]));
            }
            
            for (Estado estado : result) {
                comboEstado.addItem(estado.getNome());
            }

//            listEstados.stream().map((estado) -> {
//                idEstados.add(estado.getId());
//                return estado;
//            }).forEachOrdered((estado) -> {
//                comboEstado.addItem(estado.getNome());
//            });

        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(CadastroCidade.this,
                ex.getMessage(), "Erro ao consultar Estado(s)", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    enum TipoBotao {
        incluir,
        editar,
        excluir
    }
    
    private void habilitarDesabilitarCampos(TipoBotao tipo) {
        limpaCampos();

        if (null == tipo) {
            campoId.setEnabled(false);
            campoNome.setEnabled(false);
            comboEstado.setEnabled(false);
            botaoBuscar.setEnabled(false);
            botaoSalvar.setEnabled(false);
            botaoDeletar.setEnabled(false);
        } else switch (tipo) {
            case incluir:
                campoId.setEnabled(false);
                comboEstado.setEnabled(true);
                campoNome.setEnabled(true);
                botaoBuscar.setEnabled(false);
                botaoSalvar.setEnabled(true);
                botaoDeletar.setEnabled(false);
                break;
            case editar:
                campoId.setEnabled(true);
                comboEstado.setEnabled(true);
                campoNome.setEnabled(true);
                botaoBuscar.setEnabled(true);
                botaoSalvar.setEnabled(true);
                botaoDeletar.setEnabled(false);
                break;
            case excluir:
                campoId.setEnabled(true);
                comboEstado.setEnabled(false);
                campoNome.setEnabled(false);
                botaoBuscar.setEnabled(true);
                botaoSalvar.setEnabled(false);
                botaoDeletar.setEnabled(true);
                break;
            default:
                campoId.setEnabled(false);
                comboEstado.setEnabled(false);
                campoNome.setEnabled(false);
                botaoBuscar.setEnabled(false);
                botaoSalvar.setEnabled(false);
                botaoDeletar.setEnabled(false);
                break;
        }
    }
    
    private void limpaCampos() {
        campoId.setText("");
        comboEstado.setSelectedIndex(0);
        campoNome.setText("");
    }
}
