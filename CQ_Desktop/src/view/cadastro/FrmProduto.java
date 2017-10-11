/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastro;

import DAO.MetodologiaDAO;
import DAO.MaterialDAO;
import acesso.ProdutoAcesso;
import model.Metodologia;
import model.Material;
import util.ComboBox;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael
 */
public class FrmProduto extends javax.swing.JInternalFrame {

    private TreeMap<Integer, String> categoryMapMetodo = new TreeMap<>();
    private boolean novoProduto = false;

    /**
     * Creates new form NovoJInternalFrame
     */
    public FrmProduto() {
        initComponents();
        setTitle("Produtos");
        readProduto();
        tblPaneProduto.setEnabledAt(1, false);
        ProdutoAcesso Acesso = new ProdutoAcesso();
        Acesso.verificarAcessoProduto();
    }

    public final void readProduto() {
        DefaultTableModel model = (DefaultTableModel) tblProduto.getModel();
        MaterialDAO equipDao = new MaterialDAO();
        model.setNumRows(0);
        for (Material prod : equipDao.readMaterial()) {
            model.addRow(new Object[]{
                prod.getMaterial_id(),
                prod.getCod_material(),
                prod.getMaterial(),
                prod.getTipo(),
                prod.getCod_metodo()
            });
        }
    }

    public void readMetodo() {
        cmbMetodo.removeAllItems();
        cmbMetodo.addItem("");
        categoryMapMetodo.clear();
        MetodologiaDAO mtdDao = new MetodologiaDAO();
        for (Metodologia mtd : mtdDao.read()) {
            Integer id = mtd.getMetodo_id();
            String name = mtd.getCod_metodo();
            cmbMetodo.addItem(name);
            categoryMapMetodo.put(id, name);
        }
    }

    private void limparCampos() {
        txtCodMaterial.setText(null);
        txtMaterial.setText(null);
        cmbTipo.setSelectedIndex(0);
        lblId.setText(null);
    }

    private void criarProduto() {
        Material prod = new Material();
        MaterialDAO equipDAO = new MaterialDAO();
        Metodologia mtd = new Metodologia();
        try {
            prod.setCod_material(txtCodMaterial.getText().toUpperCase());
            prod.setMaterial(txtMaterial.getText().toUpperCase());
            prod.setTipo(cmbTipo.getSelectedItem().toString());
            mtd.setMetodo_id(ComboBox.getKeyForValue(cmbMetodo.getSelectedItem().toString(), categoryMapMetodo));
            prod.setMetodo(mtd);
            equipDAO.createProduto(prod);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void fecharDados() {
        tblPaneProduto.setEnabledAt(0, true);
        tblPaneProduto.setEnabledAt(1, false);
        tblPaneProduto.setSelectedIndex(0);
        txtPesquisarProduto.setText(null);
        tblProduto.setRowSorter(null);
        ProdutoAcesso Acesso = new ProdutoAcesso();
        Acesso.verificarAcessoProduto();
        limparCampos();
        readProduto();
    }

    private void abrirDados() {
        ProdutoAcesso Acesso = new ProdutoAcesso();
        if (Acesso.verificarAbrirDadosProduto()) {
            tblPaneProduto.setEnabledAt(0, false);
            tblPaneProduto.setEnabledAt(1, true);
            tblPaneProduto.setSelectedIndex(1);
            mnuNovoProduto.setEnabled(false);
            mnuEditProduto.setEnabled(false);
            btnExcluirProduto.setVisible(false);
            limparCampos();
            novoProduto = true;
            readMetodo();
        }
    }

    private void carregarDados(int id) {
        ProdutoAcesso Acesso = new ProdutoAcesso();
        if (Acesso.verificarCarregarDadosProduto()) {
            readMetodo();
            Material prod = new Material();
            prod.setMaterial_id(id);
            MaterialDAO equipDao = new MaterialDAO();
            equipDao.selectProduto(prod);
            lblId.setText(Integer.toString(id));
            txtCodMaterial.setText(prod.getCod_material());
            txtMaterial.setText(prod.getMaterial());
            String[] tipo = {prod.getTipo()};
            cmbTipo.setSelectedItem(tipo[0]);
            String[] metodo = {prod.getCod_metodo()};
            cmbMetodo.setSelectedItem(metodo[0]);
            tblPaneProduto.setEnabledAt(0, false);
            tblPaneProduto.setEnabledAt(1, true);
            tblPaneProduto.setSelectedIndex(1);
            btnExcluirProduto.setVisible(true);
            mnuEditProduto.setEnabled(false);
        }
    }

    private void atulizarProduto() {
        Material prod = new Material();
        MaterialDAO equipDAO = new MaterialDAO();
        Metodologia mtd = new Metodologia();
        try {
            prod.setMaterial_id(Integer.parseInt(lblId.getText()));
            prod.setCod_material(txtCodMaterial.getText().toUpperCase());
            prod.setMaterial(txtMaterial.getText().toUpperCase());
            prod.setTipo(cmbTipo.getSelectedItem().toString());
            mtd.setMetodo_id(ComboBox.getKeyForValue(cmbMetodo.getSelectedItem().toString(), categoryMapMetodo));
            prod.setMetodo(mtd);
            equipDAO.updateProduto(prod);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void deletarProduto(int id) {
        ProdutoAcesso Acesso = new ProdutoAcesso();
        if (Acesso.verificarDeletarDadosProduto()) {
            Material prod = new Material();
            MaterialDAO equipDAO = new MaterialDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente Deseja Excluir Esse Produto?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    prod.setMaterial_id(id);
                    equipDAO.deleteProduto(prod);
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

        tblPaneProduto = new javax.swing.JTabbedPane();
        pnProduto = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        txtPesquisarProduto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnlDados = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtCodMaterial = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtMaterial = new javax.swing.JTextField();
        cmbMetodo = new javax.swing.JComboBox();
        btnProcurarMetodo = new javax.swing.JButton();
        btnExcluirProduto = new javax.swing.JButton();
        btnSalvarProduto = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoProduto = new javax.swing.JMenu();
        itmNovoProduto = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        mnuEditProduto = new javax.swing.JMenu();
        itmEditarProduto = new javax.swing.JMenuItem();
        itmExcluirProduto = new javax.swing.JMenuItem();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Material", "Produto", "Tipo", "Método"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduto.setSurrendersFocusOnKeystroke(true);
        tblProduto.getTableHeader().setReorderingAllowed(false);
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        tblProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProdutoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);
        if (tblProduto.getColumnModel().getColumnCount() > 0) {
            tblProduto.getColumnModel().getColumn(0).setMinWidth(50);
            tblProduto.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProduto.getColumnModel().getColumn(0).setMaxWidth(80);
            tblProduto.getColumnModel().getColumn(1).setMinWidth(100);
            tblProduto.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblProduto.getColumnModel().getColumn(1).setMaxWidth(100);
            tblProduto.getColumnModel().getColumn(3).setMinWidth(80);
            tblProduto.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblProduto.getColumnModel().getColumn(3).setMaxWidth(80);
            tblProduto.getColumnModel().getColumn(4).setMinWidth(120);
            tblProduto.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblProduto.getColumnModel().getColumn(4).setMaxWidth(120);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
        );

        txtPesquisarProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarProdutoKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar:");

        javax.swing.GroupLayout pnProdutoLayout = new javax.swing.GroupLayout(pnProduto);
        pnProduto.setLayout(pnProdutoLayout);
        pnProdutoLayout.setHorizontalGroup(
            pnProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnProdutoLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarProduto))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnProdutoLayout.setVerticalGroup(
            pnProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPaneProduto.addTab("Produtos", pnProduto);

        pnlDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Produto"));

        jLabel1.setText("Material:");

        jLabel7.setText("Id:");

        jLabel3.setText("Tipo:");

        jLabel22.setText("Método:");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PA", "SA", "MP", "CP", "EE", "PD", "VL" }));

        jLabel5.setText("Produto:");

        btnProcurarMetodo.setText("Buscar");
        btnProcurarMetodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarMetodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5))
                    .addComponent(jLabel22))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtCodMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(cmbMetodo, 0, 606, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnProcurarMetodo))
                                    .addComponent(txtMaterial))))
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblId)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProcurarMetodo)
                    .addComponent(jLabel22))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirProduto.setText("Excuir");
        btnExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProdutoActionPerformed(evt);
            }
        });

        btnSalvarProduto.setText("Ok");
        btnSalvarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarProdutoActionPerformed(evt);
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
                        .addComponent(btnExcluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 417, Short.MAX_VALUE)
                        .addComponent(btnSalvarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvarProduto)
                    .addComponent(btnExcluirProduto))
                .addContainerGap())
        );

        tblPaneProduto.addTab("Dados", pnlDados);

        mnuArquivo.setText("Arquivo");

        mnuNovoProduto.setText("Novo");

        itmNovoProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_INSERT, java.awt.event.InputEvent.ALT_MASK));
        itmNovoProduto.setText("Produto");
        itmNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoProdutoActionPerformed(evt);
            }
        });
        mnuNovoProduto.add(itmNovoProduto);

        mnuArquivo.add(mnuNovoProduto);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuArquivo.add(mnuSair);

        jMenuBar1.add(mnuArquivo);

        mnuEditProduto.setText("Editar");

        itmEditarProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        itmEditarProduto.setText("Editar");
        itmEditarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmEditarProdutoActionPerformed(evt);
            }
        });
        mnuEditProduto.add(itmEditarProduto);

        itmExcluirProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.ALT_MASK));
        itmExcluirProduto.setText("Excluir");
        itmExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmExcluirProdutoActionPerformed(evt);
            }
        });
        mnuEditProduto.add(itmExcluirProduto);

        jMenuBar1.add(mnuEditProduto);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneProduto, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itmNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoProdutoActionPerformed
        abrirDados();
    }//GEN-LAST:event_itmNovoProdutoActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmEditarProdutoActionPerformed
        if (tblProduto.getSelectedRow() != -1) {
            carregarDados((Integer) tblProduto.getValueAt(tblProduto.getSelectedRow(), 0));
            novoProduto = false;
        }
    }//GEN-LAST:event_itmEditarProdutoActionPerformed

    private void itmExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmExcluirProdutoActionPerformed
        if (tblProduto.getSelectedRow() != -1) {
            deletarProduto((Integer) tblProduto.getValueAt(tblProduto.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_itmExcluirProdutoActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDados((Integer) tblProduto.getValueAt(tblProduto.getSelectedRow(), 0));
            novoProduto = false;
        }
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void tblProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProdutoKeyReleased
        if (tblProduto.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarProduto((Integer) tblProduto.getValueAt(tblProduto.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblProdutoKeyReleased

    private void txtPesquisarProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarProdutoKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblProduto.getModel();
        sorter = new TableRowSorter<>(model);
        tblProduto.setRowSorter(sorter);
        String text = txtPesquisarProduto.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarProdutoKeyReleased

    private void btnExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutoActionPerformed
        deletarProduto((Integer) tblProduto.getValueAt(tblProduto.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirProdutoActionPerformed

    private void btnSalvarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarProdutoActionPerformed
        if (txtCodMaterial.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Material inválido");
            txtCodMaterial.requestFocus();
        } else if (txtMaterial.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome de Produto inválido");
            txtMaterial.requestFocus();
        } else if (cmbMetodo.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Metodologia inválida");
            cmbMetodo.requestFocus();
        } else if (novoProduto) {
            criarProduto();
        } else {
            atulizarProduto();
        }
    }//GEN-LAST:event_btnSalvarProdutoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnProcurarMetodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarMetodoActionPerformed
        Metodologia mtd = new Metodologia();
        FrmProcurarMetodologia frm = new FrmProcurarMetodologia(null, true, mtd);
        frm.setVisible(true);
        readMetodo();
        String[] cod_mtd = {mtd.getCod_metodo()};
        cmbMetodo.setSelectedItem(cod_mtd[0]);
    }//GEN-LAST:event_btnProcurarMetodoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnExcluirProduto;
    private javax.swing.JButton btnProcurarMetodo;
    public static javax.swing.JButton btnSalvarProduto;
    private javax.swing.JComboBox cmbMetodo;
    private javax.swing.JComboBox cmbTipo;
    public static javax.swing.JMenuItem itmEditarProduto;
    public static javax.swing.JMenuItem itmExcluirProduto;
    public static javax.swing.JMenuItem itmNovoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblId;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuEditProduto;
    public static javax.swing.JMenu mnuNovoProduto;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JPanel pnProduto;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JTabbedPane tblPaneProduto;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtCodMaterial;
    private javax.swing.JTextField txtMaterial;
    private javax.swing.JTextField txtPesquisarProduto;
    // End of variables declaration//GEN-END:variables
}
