/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastro;

import DAO.SetorDAO;
import acesso.SetorAcesso;
import model.Setor;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael
 */
public class FrmSetor extends javax.swing.JInternalFrame {

    private boolean novoSetor = false;

    /**
     * Creates new form NovoJInternalFrame
     */
    public FrmSetor() {
        initComponents();
        setTitle("Setor");
        readSetors();
        pnlSetor.setEnabledAt(1, false);
        SetorAcesso Acesso = new SetorAcesso();
        Acesso.verificarAcessoSetor();
    }

    public void readSetors() {
        DefaultTableModel model = (DefaultTableModel) tblSetor.getModel();
        SetorDAO setorDao = new SetorDAO();
        model.setNumRows(0);
        for (Setor setor : setorDao.readSetor()) {
            model.addRow(new Object[]{
                setor.getSetor_id(),
                setor.getSetor(),
                setor.getSigla_setor(),
                setor.getDescricao_setor()
            });
        }
    }

    private void limparCampos() {
        txtSetor.setText(null);
        txtSigla.setText(null);
        txtSetor.setText(null);
        txtSigla.setText(null);
        txtDescricao.setText(null);
    }

    private void criarSetor() {
        Setor setor = new Setor();
        SetorDAO setorDAO = new SetorDAO();
        try {
            setor.setSetor(txtSetor.getText().toUpperCase());
            setor.setSigla_setor(txtSigla.getText());
            setor.setDescricao_setor(txtDescricao.getText().toUpperCase());
            setorDAO.createSetor(setor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void fecharDados() {
        pnlSetor.setEnabledAt(0, true);
        pnlSetor.setEnabledAt(1, false);
        pnlSetor.setSelectedIndex(0);
        txtPesquisarSetor.setText(null);
        tblSetor.setRowSorter(null);
        SetorAcesso Acesso = new SetorAcesso();
        Acesso.verificarAcessoSetor();
        limparCampos();
        readSetors();
    }

    private void abrirDados() {
        SetorAcesso Acesso = new SetorAcesso();
        if (Acesso.verificarAbrirDadosSetor()) {
            pnlSetor.setEnabledAt(0, false);
            pnlSetor.setEnabledAt(1, true);
            pnlSetor.setSelectedIndex(1);
            mnuNovoSetor.setEnabled(false);
            btnExcluirSetor.setVisible(false);
            limparCampos();
            novoSetor = true;
        }
    }

    private void carregarDados(int id) {
        SetorAcesso Acesso = new SetorAcesso();
        if (Acesso.verificarCarregarDadosSetor()) {
            Setor setor = new Setor();
            setor.setSetor_id(id);
            SetorDAO setorDao = new SetorDAO();
            setorDao.selectSetor(setor);
            txtSetor.setText(setor.getSetor());
            txtSigla.setText(setor.getSigla_setor());
            txtDescricao.setText(setor.getDescricao_setor().toUpperCase());
            pnlSetor.setEnabledAt(0, false);
            pnlSetor.setEnabledAt(1, true);
            pnlSetor.setSelectedIndex(1);
            btnExcluirSetor.setVisible(true);
            mnuEditarSetor.setEnabled(false);
        }
    }

    private void atulizarSetor() {
        Setor setor = new Setor();
        SetorDAO setorDAO = new SetorDAO();
        try {
            setor.setSetor_id((Integer) tblSetor.getValueAt(tblSetor.getSelectedRow(), 0));
            setor.setSetor(txtSetor.getText().toUpperCase());
            setor.setSigla_setor(txtSigla.getText());
            setor.setDescricao_setor(txtDescricao.getText().toUpperCase());
            setorDAO.updateSetor(setor);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void deletarDados(int id) {
        SetorAcesso Acesso = new SetorAcesso();
        if (Acesso.verificarDeletarDadosSetor()) {
            Setor setor = new Setor();
            SetorDAO setorDAO = new SetorDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente Deseja Excluir Esse Produto?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    setor.setSetor_id(id);
                    setorDAO.deleteSetor(setor);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
            } finally {
                fecharDados();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSetor = new javax.swing.JTabbedPane();
        pnlUsuarios = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSetor = new javax.swing.JTable();
        txtPesquisarSetor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnlDados = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtSetor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSigla = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnExcluirSetor = new javax.swing.JButton();
        btnSalvarSetor = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoSetor = new javax.swing.JMenu();
        itmNovoSetor = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        mnuEditarSetor = new javax.swing.JMenu();
        itmEditarSetor = new javax.swing.JMenuItem();
        itmExcluirSetor = new javax.swing.JMenuItem();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Setores"));

        tblSetor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Setor", "Sigla", "Descrição"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSetor.setSurrendersFocusOnKeystroke(true);
        tblSetor.getTableHeader().setReorderingAllowed(false);
        tblSetor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSetorMouseClicked(evt);
            }
        });
        tblSetor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSetorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblSetor);
        if (tblSetor.getColumnModel().getColumnCount() > 0) {
            tblSetor.getColumnModel().getColumn(0).setMinWidth(80);
            tblSetor.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblSetor.getColumnModel().getColumn(0).setMaxWidth(80);
            tblSetor.getColumnModel().getColumn(1).setMinWidth(200);
            tblSetor.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblSetor.getColumnModel().getColumn(1).setMaxWidth(200);
            tblSetor.getColumnModel().getColumn(2).setMinWidth(80);
            tblSetor.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblSetor.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        );

        txtPesquisarSetor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarSetorKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar:");

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarSetor))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlSetor.addTab("Setor", pnlUsuarios);

        pnlDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Setor"));

        jLabel2.setText("Sigla:");

        jLabel3.setText("Descrição:");

        jLabel22.setText("Setor:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDescricao)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel22)
                        .addGap(553, 553, 553)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtSigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirSetor.setText("Excuir");
        btnExcluirSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirSetorActionPerformed(evt);
            }
        });

        btnSalvarSetor.setText("Ok");
        btnSalvarSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarSetorActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDadosLayout = new javax.swing.GroupLayout(pnlDados);
        pnlDados.setLayout(pnlDadosLayout);
        pnlDadosLayout.setHorizontalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(btnExcluirSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvarSetor)
                    .addComponent(btnExcluirSetor))
                .addContainerGap())
        );

        pnlSetor.addTab("Dados", pnlDados);

        mnuArquivo.setText("Arquivo");

        mnuNovoSetor.setText("Novo");

        itmNovoSetor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_INSERT, java.awt.event.InputEvent.ALT_MASK));
        itmNovoSetor.setText("Setor");
        itmNovoSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoSetorActionPerformed(evt);
            }
        });
        mnuNovoSetor.add(itmNovoSetor);

        mnuArquivo.add(mnuNovoSetor);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuArquivo.add(mnuSair);

        jMenuBar1.add(mnuArquivo);

        mnuEditarSetor.setText("Editar");

        itmEditarSetor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        itmEditarSetor.setText("Editar");
        itmEditarSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmEditarSetorActionPerformed(evt);
            }
        });
        mnuEditarSetor.add(itmEditarSetor);

        itmExcluirSetor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.ALT_MASK));
        itmExcluirSetor.setText("Excluir");
        itmExcluirSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmExcluirSetorActionPerformed(evt);
            }
        });
        mnuEditarSetor.add(itmExcluirSetor);

        jMenuBar1.add(mnuEditarSetor);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSetor, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSetor, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSetorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSetorMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDados((Integer) tblSetor.getValueAt(tblSetor.getSelectedRow(), 0));
            novoSetor = false;
        }
    }//GEN-LAST:event_tblSetorMouseClicked

    private void txtPesquisarSetorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarSetorKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblSetor.getModel();
        sorter = new TableRowSorter<>(model);
        tblSetor.setRowSorter(sorter);
        String text = txtPesquisarSetor.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarSetorKeyReleased

    private void btnExcluirSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirSetorActionPerformed
        deletarDados((Integer) tblSetor.getValueAt(tblSetor.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirSetorActionPerformed

    private void btnSalvarSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarSetorActionPerformed
        if (txtSetor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Setor inválido");
            txtSetor.requestFocus();
        } else if (txtSigla.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sigla inválida");
            txtSigla.requestFocus();
        } else if (txtDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição inválida");
            txtDescricao.requestFocus();
        } else if (novoSetor) {
            criarSetor();
        } else {
            atulizarSetor();
        }
    }//GEN-LAST:event_btnSalvarSetorActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmEditarSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmEditarSetorActionPerformed
        if (tblSetor.getSelectedRow() != -1) {
            carregarDados((Integer) tblSetor.getValueAt(tblSetor.getSelectedRow(), 0));
            novoSetor = false;
        }
    }//GEN-LAST:event_itmEditarSetorActionPerformed

    private void itmExcluirSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmExcluirSetorActionPerformed
        if (tblSetor.getSelectedRow() != -1) {
            deletarDados((Integer) tblSetor.getValueAt(tblSetor.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_itmExcluirSetorActionPerformed

    private void itmNovoSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoSetorActionPerformed
        abrirDados();
    }//GEN-LAST:event_itmNovoSetorActionPerformed

    private void tblSetorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSetorKeyReleased
        if (tblSetor.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarDados((Integer) tblSetor.getValueAt(tblSetor.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblSetorKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnExcluirSetor;
    public static javax.swing.JButton btnSalvarSetor;
    public static javax.swing.JMenuItem itmEditarSetor;
    public static javax.swing.JMenuItem itmExcluirSetor;
    public static javax.swing.JMenuItem itmNovoSetor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuEditarSetor;
    public static javax.swing.JMenu mnuNovoSetor;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JTabbedPane pnlSetor;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JTable tblSetor;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtPesquisarSetor;
    private javax.swing.JTextField txtSetor;
    private javax.swing.JTextField txtSigla;
    // End of variables declaration//GEN-END:variables
}
