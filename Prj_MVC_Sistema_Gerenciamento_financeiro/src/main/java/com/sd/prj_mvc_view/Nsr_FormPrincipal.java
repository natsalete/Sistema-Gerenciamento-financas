package com.sd.prj_mvc_view;

public class Nsr_FormPrincipal extends javax.swing.JFrame {

    public Nsr_FormPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniCadCategorias = new javax.swing.JMenuItem();
        mniCadOrcamentos = new javax.swing.JMenuItem();
        mniCadVendas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniRelatProdutos = new javax.swing.JMenuItem();
        mniRelatVendas = new javax.swing.JMenuItem();
        mniRelatQtdValorVendProduto = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mniSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRINCIPAL");

        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabel1.setText("Sistema de Gerenciamento Financeiro");
        jLabel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jMenu1.setText("Cadastro");

        mniCadCategorias.setText("Cadastro de Categorias");
        mniCadCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCadClienteActionPerformed(evt);
            }
        });
        jMenu1.add(mniCadCategorias);

        mniCadOrcamentos.setText("Cadastro de Orcamentos");
        mniCadOrcamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCadOrcamentosActionPerformed(evt);
            }
        });
        jMenu1.add(mniCadOrcamentos);

        mniCadVendas.setText("Cadastro de Vendas");
        mniCadVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCadVendasActionPerformed(evt);
            }
        });
        jMenu1.add(mniCadVendas);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Relatórios");

        mniRelatProdutos.setText("Relatório de Produtos");
        mniRelatProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRelatProdutosActionPerformed(evt);
            }
        });
        jMenu2.add(mniRelatProdutos);

        mniRelatVendas.setText("Relatório de Vendas");
        mniRelatVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRelatVendasActionPerformed(evt);
            }
        });
        jMenu2.add(mniRelatVendas);

        mniRelatQtdValorVendProduto.setText("Relatório de Qtd e Valor Vend. Produto");
        mniRelatQtdValorVendProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRelatQtdValorVendProdutoActionPerformed(evt);
            }
        });
        jMenu2.add(mniRelatQtdValorVendProduto);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Opções");

        mniSair.setText("Sair");
        mniSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSairActionPerformed(evt);
            }
        });
        jMenu3.add(mniSair);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel1)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mniCadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCadClienteActionPerformed
        // TODO add your handling code here:
        new FormCadastroCategoria().setVisible(true);
    }//GEN-LAST:event_mniCadClienteActionPerformed

    private void mniSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mniSairActionPerformed

    private void mniRelatProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRelatProdutosActionPerformed
        // TODO add your handling code here:
        //new Nsr_FormRelatProdutos().setVisible(true);
    }//GEN-LAST:event_mniRelatProdutosActionPerformed

    private void mniCadOrcamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCadOrcamentosActionPerformed
        // TODO add your handling code here:
        new FormCadastroOrcamentos().setVisible(true);
    }//GEN-LAST:event_mniCadOrcamentosActionPerformed

    private void mniRelatVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRelatVendasActionPerformed
        // TODO add your handling code here:
       //new Nsr_FormRelatVendas().setVisible(true);
    }//GEN-LAST:event_mniRelatVendasActionPerformed

    private void mniCadVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCadVendasActionPerformed
        // TODO add your handling code here:
        //new Nsr_FormCadastroVendas().setVisible(true);
    }//GEN-LAST:event_mniCadVendasActionPerformed

    private void mniRelatQtdValorVendProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRelatQtdValorVendProdutoActionPerformed
        // TODO add your handling code here:
        //new Nsr_RelatDeQtdValorVendProduto().setVisible(true);
    }//GEN-LAST:event_mniRelatQtdValorVendProdutoActionPerformed

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
            java.util.logging.Logger.getLogger(Nsr_FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nsr_FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nsr_FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nsr_FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nsr_FormPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mniCadCategorias;
    private javax.swing.JMenuItem mniCadOrcamentos;
    private javax.swing.JMenuItem mniCadVendas;
    private javax.swing.JMenuItem mniRelatProdutos;
    private javax.swing.JMenuItem mniRelatQtdValorVendProduto;
    private javax.swing.JMenuItem mniRelatVendas;
    private javax.swing.JMenuItem mniSair;
    // End of variables declaration//GEN-END:variables

}
