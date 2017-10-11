/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastro;

import DAO.AmostraDAO;
import acesso.AnaliseAcesso;
import model.Amostra;
import model.Analise;
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
public final class FrmAmostra extends javax.swing.JInternalFrame {

    private boolean novoAmostra = false;

    /**
     * Creates new form NovoJInternalFrame
     */
    public FrmAmostra() {
        initComponents();
        setTitle("Amostra");
        fecharDados();
    }

    public void readStatusAmostra() {
        DefaultTableModel model = (DefaultTableModel) tblStatusAmostra.getModel();
        AmostraDAO amsDao = new AmostraDAO();
        model.setNumRows(0);
        for (Amostra ams : amsDao.readAmostraStatus()) {
            model.addRow(new Object[]{
                ams.getAmostra_status_id(),
                ams.getAmostra_status(),
                ams.getSigla_amostra_status(),
                ams.getDescricao_amostra_status()
            });
        }
    }

    private void criarStatusAmostra() {
        Amostra ams = new Amostra();
        AmostraDAO amsDAO = new AmostraDAO();
        try {
            ams.setSigla_amostra_status(txtSiglaStatusAmostra.getText().toUpperCase());
            ams.setAmostra_status(txtStatusAmostra.getText().toUpperCase());
            ams.setDescricao_amostra_status(txtDescricaoStatusAmostra.getText().toUpperCase());
            amsDAO.createAmostraStatus(ams);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void atulizarStatusAmostra(int id) {
        Amostra ams = new Amostra();
        AmostraDAO amsDAO = new AmostraDAO();
        try {
            ams.setAmostra_status_id(id);
            ams.setSigla_amostra_status(txtSiglaStatusAmostra.getText().toUpperCase());
            ams.setAmostra_status(txtStatusAmostra.getText().toUpperCase());
            ams.setDescricao_amostra_status(txtDescricaoStatusAmostra.getText().toUpperCase());
            amsDAO.updateAmostraStatus(ams);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void deletarStatusAmostra(int id) {
        Amostra ams = new Amostra();
        AmostraDAO amsDAO = new AmostraDAO();
        try {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
            if (dialogResult == 0) {
                ams.setAmostra_status_id(id);
                amsDAO.deleteAmostraStatus(ams);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void limparCampos() {
        //Limpa tabela Status
        txtSiglaStatusAmostra.setText(null);
        txtStatusAmostra.setText(null);
        txtDescricaoStatusAmostra.setText(null);
        txtPesquisarStatusAmostra.setText(null);
    }

    private void fecharDados() {
        //tabela Status Amostra
        txtPesquisarStatusAmostra.setText(null);
        tblStatusAmostra.setRowSorter(null);
        //componentes
        pnlAmostra.setEnabledAt(0, true);
        mnuNovoAmostra.setEnabled(true);
        itmNovoAmostra.setEnabled(true);
        limparCampos();
        readStatusAmostra();
    }

    private void abrirStatusAmostra() {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarAbrirDadosAnalise()) {
            pnlAmostra.setSelectedIndex(0);
            pnlAmostra.setEnabledAt(0, false);
            btnExcluirStatus.setVisible(false);
            btnCancelarStatus.setVisible(true);
            btnSalvarStatus.setVisible(true);
            pnlEditarStatus.setVisible(true);
            itmNovoAmostra.setEnabled(false);
            mnuNovoAmostra.setEnabled(false);
            limparCampos();
            novoAmostra = true;
        }
    }

    private void carregarStatusAmostra(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarCarregarDadosAnalise()) {
            Amostra ams = new Amostra();
            ams.setAmostra_status_id(id);
            AmostraDAO amsDao = new AmostraDAO();
            amsDao.selectAmostraStatus(ams);
            txtSiglaStatusAmostra.setText(ams.getSigla_amostra_status());
            txtStatusAmostra.setText(ams.getAmostra_status());
            txtDescricaoStatusAmostra.setText(ams.getDescricao_amostra_status());
            btnExcluirStatus.setVisible(true);
            btnCancelarStatus.setVisible(true);
            btnSalvarStatus.setVisible(true);
            pnlEditarStatus.setVisible(true);
            itmNovoAmostra.setEnabled(false);
            mnuNovoAmostra.setEnabled(false);
            pnlAmostra.setEnabledAt(0, false);

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

        pnlAmostra = new javax.swing.JTabbedPane();
        pnlAmostraStatus = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStatusAmostra = new javax.swing.JTable();
        txtPesquisarStatusAmostra = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        pnlDados = new javax.swing.JPanel();
        pnlEditarStatus = new javax.swing.JPanel();
        txtSiglaStatusAmostra = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtStatusAmostra = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDescricaoStatusAmostra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        btnExcluirStatus = new javax.swing.JButton();
        btnSalvarStatus = new javax.swing.JButton();
        btnCancelarStatus = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoAmostra = new javax.swing.JMenu();
        itmNovoAmostra = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        tblStatusAmostra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sigla", "Status da Amostra", "Descrição"
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
        tblStatusAmostra.setSurrendersFocusOnKeystroke(true);
        tblStatusAmostra.getTableHeader().setReorderingAllowed(false);
        tblStatusAmostra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStatusAmostraMouseClicked(evt);
            }
        });
        tblStatusAmostra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblStatusAmostraKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblStatusAmostra);
        if (tblStatusAmostra.getColumnModel().getColumnCount() > 0) {
            tblStatusAmostra.getColumnModel().getColumn(0).setMinWidth(50);
            tblStatusAmostra.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblStatusAmostra.getColumnModel().getColumn(0).setMaxWidth(50);
            tblStatusAmostra.getColumnModel().getColumn(1).setMinWidth(80);
            tblStatusAmostra.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblStatusAmostra.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        );

        txtPesquisarStatusAmostra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarStatusAmostraKeyReleased(evt);
            }
        });

        jLabel17.setText("Pesquisar:");

        javax.swing.GroupLayout pnlAmostraStatusLayout = new javax.swing.GroupLayout(pnlAmostraStatus);
        pnlAmostraStatus.setLayout(pnlAmostraStatusLayout);
        pnlAmostraStatusLayout.setHorizontalGroup(
            pnlAmostraStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAmostraStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAmostraStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAmostraStatusLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarStatusAmostra))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAmostraStatusLayout.setVerticalGroup(
            pnlAmostraStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAmostraStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAmostraStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarStatusAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        pnlAmostra.addTab("Status Amostra", pnlAmostraStatus);

        pnlDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlEditarStatus.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        jLabel18.setText("Sigla:");

        jLabel19.setText("Status:");

        jLabel20.setText("Descrição:");

        jLabel7.setText("Id:");

        javax.swing.GroupLayout pnlEditarStatusLayout = new javax.swing.GroupLayout(pnlEditarStatus);
        pnlEditarStatus.setLayout(pnlEditarStatusLayout);
        pnlEditarStatusLayout.setHorizontalGroup(
            pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel7)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescricaoStatusAmostra, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEditarStatusLayout.createSequentialGroup()
                        .addComponent(lblId)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlEditarStatusLayout.createSequentialGroup()
                        .addComponent(txtSiglaStatusAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatusAmostra, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEditarStatusLayout.setVerticalGroup(
            pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditarStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSiglaStatusAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(txtStatusAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescricaoStatusAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirStatus.setText("Excuir");
        btnExcluirStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirStatusActionPerformed(evt);
            }
        });

        btnSalvarStatus.setText("Ok");
        btnSalvarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarStatusActionPerformed(evt);
            }
        });

        btnCancelarStatus.setText("Cancelar");
        btnCancelarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDadosLayout = new javax.swing.GroupLayout(pnlDados);
        pnlDados.setLayout(pnlDadosLayout);
        pnlDadosLayout.setHorizontalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlEditarStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(btnExcluirStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEditarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirStatus)
                    .addComponent(btnSalvarStatus)
                    .addComponent(btnCancelarStatus))
                .addContainerGap())
        );

        pnlAmostra.addTab("Dados", pnlDados);

        mnuArquivo.setText("Arquivo");

        mnuNovoAmostra.setText("Novo");

        itmNovoAmostra.setText("Amostra");
        itmNovoAmostra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoAmostraActionPerformed(evt);
            }
        });
        mnuNovoAmostra.add(itmNovoAmostra);

        mnuArquivo.add(mnuNovoAmostra);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuArquivo.add(mnuSair);

        jMenuBar1.add(mnuArquivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAmostra, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAmostra)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmNovoAmostraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoAmostraActionPerformed
        abrirStatusAmostra();
    }//GEN-LAST:event_itmNovoAmostraActionPerformed

    private void tblStatusAmostraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStatusAmostraMouseClicked
        if (evt.getClickCount() == 2) {
            carregarStatusAmostra((Integer) tblStatusAmostra.getValueAt(tblStatusAmostra.getSelectedRow(), 0));
            novoAmostra = false;
        }
    }//GEN-LAST:event_tblStatusAmostraMouseClicked

    private void tblStatusAmostraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblStatusAmostraKeyReleased
        if (tblStatusAmostra.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarStatusAmostra((Integer) tblStatusAmostra.getValueAt(tblStatusAmostra.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblStatusAmostraKeyReleased

    private void txtPesquisarStatusAmostraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarStatusAmostraKeyReleased
        TableRowSorter sorter = null;
        sorter = new TableRowSorter<>(tblStatusAmostra.getModel());
        tblStatusAmostra.setRowSorter(sorter);
        String text = txtPesquisarStatusAmostra.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarStatusAmostraKeyReleased

    private void btnExcluirStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirStatusActionPerformed
        deletarStatusAmostra((Integer) tblStatusAmostra.getValueAt(tblStatusAmostra.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirStatusActionPerformed

    private void btnSalvarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarStatusActionPerformed
        if (txtSiglaStatusAmostra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sigla inválida");
            txtSiglaStatusAmostra.requestFocus();
        } else if (txtStatusAmostra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Status da Amostra inválida");
            txtStatusAmostra.requestFocus();
        } else if (txtDescricaoStatusAmostra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição inválida");
            txtDescricaoStatusAmostra.requestFocus();
        } else if (novoAmostra) {
            criarStatusAmostra();
        } else {
            atulizarStatusAmostra((Integer) tblStatusAmostra.getValueAt(tblStatusAmostra.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_btnSalvarStatusActionPerformed

    private void btnCancelarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarStatusActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarStatus;
    public static javax.swing.JButton btnExcluirStatus;
    public static javax.swing.JButton btnSalvarStatus;
    public static javax.swing.JMenuItem itmNovoAmostra;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblId;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuNovoAmostra;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JTabbedPane pnlAmostra;
    private javax.swing.JPanel pnlAmostraStatus;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JPanel pnlEditarStatus;
    private javax.swing.JTable tblStatusAmostra;
    private javax.swing.JTextField txtDescricaoStatusAmostra;
    private javax.swing.JTextField txtPesquisarStatusAmostra;
    private javax.swing.JTextField txtSiglaStatusAmostra;
    private javax.swing.JTextField txtStatusAmostra;
    // End of variables declaration//GEN-END:variables
}
