/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastro;

import DAO.AcompanhamentoDAO;
import DAO.MaterialDAO;
import DAO.MetodologiaDAO;
import acesso.MetodologiaAcesso;
import model.Acompanhamento;
import model.Metodologia;
import model.Material;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael
 */
public class FrmMetodologia extends javax.swing.JInternalFrame {

    private boolean novoMetodologia = false;

    /**
     * Creates new form NovoJInternalFrame
     */
    public FrmMetodologia() {
        initComponents();
        setTitle("Metodologias");
        readMetodologia();
        tblPaneMetodologia.setEnabledAt(1, false);
        MetodologiaAcesso Acesso = new MetodologiaAcesso();
        Acesso.verificarAcessoMetodologia();
    }

    public final void readMetodologia() {
        DefaultTableModel model = (DefaultTableModel) tblMetodologia.getModel();
        MetodologiaDAO equipDao = new MetodologiaDAO();
        model.setNumRows(0);
        for (Metodologia mtd : equipDao.read()) {
            model.addRow(new Object[]{
                mtd.getMetodo_id(),
                mtd.getCod_metodo(),
                mtd.getMetodo(),
                mtd.getVersao()
            });
        }
    }

    public final void readMetodologiaProduto(Metodologia mtd) {
        DefaultTableModel model = (DefaultTableModel) tblProdutoMetodologia.getModel();
        MaterialDAO matDao = new MaterialDAO();
        model.setNumRows(0);
        for (Material mat : matDao.readMaterial(mtd)) {
            model.addRow(new Object[]{
                mat.getMaterial_id(),
                mat.getCod_material(),
                mat.getMaterial(),
                mat.getTipo()
            });
        }
    }

    public final void readRegistroAcompanhamento(Metodologia mtd) {
        DefaultTableModel model = (DefaultTableModel) tblAcompanhamento.getModel();
        AcompanhamentoDAO acompDao = new AcompanhamentoDAO();
        model.setNumRows(0);
        for (Acompanhamento acomp : acompDao.readAcompanhamento(mtd)) {
            model.addRow(new Object[]{
                acomp.getRegistro_acompanhamento_id(),
                acomp.getRegistro_acompanhamento()
            });
        }
    }

    private void limparCampos() {
        txtCodMetodo.setText(null);
        txtMetodo.setText(null);
        txtVersao.setText(null);
        txtCategoria.setText(null);
        txtLink.setText(null);
        lblId.setText(null);
    }

    private void criarMetodologia() {
        Metodologia mtd = new Metodologia();
        MetodologiaDAO equipDAO = new MetodologiaDAO();
        try {
            mtd.setCod_metodo(txtCodMetodo.getText().toUpperCase());
            mtd.setVersao(txtVersao.getText());
            mtd.setMetodo(txtMetodo.getText().toUpperCase());
            mtd.setCategoria(txtCategoria.getText().toUpperCase());
            mtd.setLink(txtLink.getText());
            equipDAO.createMetodologia(mtd);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void fecharDados() {
        tblPaneMetodologia.setEnabledAt(0, true);
        tblPaneMetodologia.setEnabledAt(1, false);
        tblPaneMetodologia.setSelectedIndex(0);
        txtPesquisarMetodologia.setText(null);
        tblMetodologia.setRowSorter(null);
        limparCampos();
        MetodologiaAcesso Acesso = new MetodologiaAcesso();
        Acesso.verificarAcessoMetodologia();
        readMetodologia();
    }

    private void abrirDados() {
        MetodologiaAcesso Acesso = new MetodologiaAcesso();
        if (Acesso.verificarAbrirDadosMetodologia()) {
            tblPaneMetodologia.setEnabledAt(0, false);
            tblPaneMetodologia.setEnabledAt(1, true);
            tblPaneMetodologia.setSelectedIndex(1);
            mnuNovoMetodo.setEnabled(false);
            mnuEditMetodo.setEnabled(false);
            btnExcluirMetodo.setVisible(false);
            limparCampos();
            novoMetodologia = true;
            pnlProdutos.setVisible(false);
        }
    }

    private void carregarDados(int id) {
        MetodologiaAcesso Acesso = new MetodologiaAcesso();
        if (Acesso.verificarCarregarDadosMetodologia()) {
            Metodologia mtd = new Metodologia();
            mtd.setMetodo_id(id);
            MetodologiaDAO equipDao = new MetodologiaDAO();
            Material mat = new Material();
            equipDao.selectMetodologia(mtd);
            mat.setMaterial_id(id);
            lblId.setText(Integer.toString(id));
            txtCodMetodo.setText(mtd.getCod_metodo().toUpperCase());
            txtVersao.setText(mtd.getVersao());
            txtMetodo.setText(mtd.getMetodo().toUpperCase());
            txtCategoria.setText(mtd.getCategoria().toUpperCase());
            txtLink.setText(mtd.getLink());
            tblPaneMetodologia.setEnabledAt(0, false);
            tblPaneMetodologia.setEnabledAt(1, true);
            tblPaneMetodologia.setSelectedIndex(1);
            btnExcluirMetodo.setVisible(true);
            mnuEditMetodo.setEnabled(false);
            pnlProdutos.setVisible(true);
            readMetodologiaProduto(mtd);
            readRegistroAcompanhamento(mtd);
        }
    }

    private void atulizarMetodologia() {
        int metodo_id = (Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0);
        Metodologia mtd = new Metodologia();
        MetodologiaDAO equipDAO = new MetodologiaDAO();
        try {
            mtd.setMetodo_id(metodo_id);
            mtd.setCod_metodo(txtCodMetodo.getText().toUpperCase());
            mtd.setVersao(txtVersao.getText());
            mtd.setMetodo(txtMetodo.getText().toUpperCase());
            mtd.setCategoria(txtCategoria.getText().toUpperCase());
            mtd.setLink(txtLink.getText());
            equipDAO.updateMetodologia(mtd);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void deletarMetodologia(int id) {
        int metodo_id = (Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0);
        MetodologiaAcesso Acesso = new MetodologiaAcesso();
        if (Acesso.verificarDeletarDadosMetodologia()) {
            Metodologia mtd = new Metodologia();
            MetodologiaDAO equipDAO = new MetodologiaDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    mtd.setMetodo_id(metodo_id);
                    equipDAO.deleteMetodologia(mtd);
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

        tblPaneMetodologia = new javax.swing.JTabbedPane();
        pnlMetodologia = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMetodologia = new javax.swing.JTable();
        txtPesquisarMetodologia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnlDados = new javax.swing.JPanel();
        pnlMetodo = new javax.swing.JPanel();
        txtCodMetodo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtVersao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMetodo = new javax.swing.JTextField();
        txtCategoria = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAcompanhamento = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        pnlProdutos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProdutoMetodologia = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtLink = new javax.swing.JTextField();
        btnExcluirMetodo = new javax.swing.JButton();
        btnSalvarMetodo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoMetodo = new javax.swing.JMenu();
        itmNovoMetodo = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        mnuEditMetodo = new javax.swing.JMenu();
        itmEditarMetodo = new javax.swing.JMenuItem();
        itmExcluirMetodo = new javax.swing.JMenuItem();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Metodologias"));

        tblMetodologia.setAutoCreateRowSorter(true);
        tblMetodologia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Código", "Método", "Versão"
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
        tblMetodologia.setSurrendersFocusOnKeystroke(true);
        tblMetodologia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMetodologiaMouseClicked(evt);
            }
        });
        tblMetodologia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblMetodologiaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblMetodologia);
        if (tblMetodologia.getColumnModel().getColumnCount() > 0) {
            tblMetodologia.getColumnModel().getColumn(0).setMinWidth(50);
            tblMetodologia.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblMetodologia.getColumnModel().getColumn(0).setMaxWidth(50);
            tblMetodologia.getColumnModel().getColumn(1).setMinWidth(100);
            tblMetodologia.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblMetodologia.getColumnModel().getColumn(1).setMaxWidth(100);
            tblMetodologia.getColumnModel().getColumn(3).setMinWidth(50);
            tblMetodologia.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblMetodologia.getColumnModel().getColumn(3).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        txtPesquisarMetodologia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarMetodologiaKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar:");

        javax.swing.GroupLayout pnlMetodologiaLayout = new javax.swing.GroupLayout(pnlMetodologia);
        pnlMetodologia.setLayout(pnlMetodologiaLayout);
        pnlMetodologiaLayout.setHorizontalGroup(
            pnlMetodologiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMetodologiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMetodologiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMetodologiaLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarMetodologia))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMetodologiaLayout.setVerticalGroup(
            pnlMetodologiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMetodologiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMetodologiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarMetodologia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPaneMetodologia.addTab("Metodologias", pnlMetodologia);

        pnlDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlMetodo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Código:");

        jLabel2.setText("Versão");

        jLabel7.setText("Id:");

        jLabel5.setText("Método:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("RA's"));

        tblAcompanhamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Documento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblAcompanhamento);
        if (tblAcompanhamento.getColumnModel().getColumnCount() > 0) {
            tblAcompanhamento.getColumnModel().getColumn(0).setMinWidth(50);
            tblAcompanhamento.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblAcompanhamento.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
        );

        jLabel6.setText("Categoria:");

        pnlProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder("Materiais"));

        tblProdutoMetodologia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Código", "Material", "Tipo"
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
        tblProdutoMetodologia.setSurrendersFocusOnKeystroke(true);
        tblProdutoMetodologia.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblProdutoMetodologia);
        if (tblProdutoMetodologia.getColumnModel().getColumnCount() > 0) {
            tblProdutoMetodologia.getColumnModel().getColumn(0).setMinWidth(50);
            tblProdutoMetodologia.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProdutoMetodologia.getColumnModel().getColumn(0).setMaxWidth(50);
            tblProdutoMetodologia.getColumnModel().getColumn(1).setMinWidth(120);
            tblProdutoMetodologia.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblProdutoMetodologia.getColumnModel().getColumn(1).setMaxWidth(120);
            tblProdutoMetodologia.getColumnModel().getColumn(3).setMinWidth(100);
            tblProdutoMetodologia.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblProdutoMetodologia.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        javax.swing.GroupLayout pnlProdutosLayout = new javax.swing.GroupLayout(pnlProdutos);
        pnlProdutos.setLayout(pnlProdutosLayout);
        pnlProdutosLayout.setHorizontalGroup(
            pnlProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        pnlProdutosLayout.setVerticalGroup(
            pnlProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );

        jLabel8.setText("Link:");

        javax.swing.GroupLayout pnlMetodoLayout = new javax.swing.GroupLayout(pnlMetodo);
        pnlMetodo.setLayout(pnlMetodoLayout);
        pnlMetodoLayout.setHorizontalGroup(
            pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMetodoLayout.createSequentialGroup()
                .addGroup(pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMetodoLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(pnlMetodoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblId))
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLink)
                            .addGroup(pnlMetodoLayout.createSequentialGroup()
                                .addComponent(txtCodMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMetodo)))
                    .addGroup(pnlMetodoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlMetodoLayout.setVerticalGroup(
            pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMetodoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtVersao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMetodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnExcluirMetodo.setText("Excuir");
        btnExcluirMetodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirMetodoActionPerformed(evt);
            }
        });

        btnSalvarMetodo.setText("Ok");
        btnSalvarMetodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarMetodoActionPerformed(evt);
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
                .addComponent(btnExcluirMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 476, Short.MAX_VALUE)
                .addComponent(btnSalvarMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlMetodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap(454, Short.MAX_VALUE)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvarMetodo)
                    .addComponent(btnExcluirMetodo))
                .addContainerGap())
            .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDadosLayout.createSequentialGroup()
                    .addComponent(pnlMetodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(45, 45, 45)))
        );

        tblPaneMetodologia.addTab("Dados", pnlDados);

        mnuArquivo.setText("Arquivo");

        mnuNovoMetodo.setText("Novo");

        itmNovoMetodo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_INSERT, java.awt.event.InputEvent.ALT_MASK));
        itmNovoMetodo.setText("Metodologia");
        itmNovoMetodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoMetodoActionPerformed(evt);
            }
        });
        mnuNovoMetodo.add(itmNovoMetodo);

        mnuArquivo.add(mnuNovoMetodo);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuArquivo.add(mnuSair);

        jMenuBar1.add(mnuArquivo);

        mnuEditMetodo.setText("Editar");

        itmEditarMetodo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        itmEditarMetodo.setText("Editar");
        itmEditarMetodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmEditarMetodoActionPerformed(evt);
            }
        });
        mnuEditMetodo.add(itmEditarMetodo);

        itmExcluirMetodo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.ALT_MASK));
        itmExcluirMetodo.setText("Excluir");
        itmExcluirMetodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmExcluirMetodoActionPerformed(evt);
            }
        });
        mnuEditMetodo.add(itmExcluirMetodo);

        jMenuBar1.add(mnuEditMetodo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneMetodologia)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneMetodologia)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itmNovoMetodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoMetodoActionPerformed
        abrirDados();
    }//GEN-LAST:event_itmNovoMetodoActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmEditarMetodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmEditarMetodoActionPerformed
        if (tblMetodologia.getSelectedRow() != -1) {
            carregarDados((Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0));
            novoMetodologia = false;
        }
    }//GEN-LAST:event_itmEditarMetodoActionPerformed

    private void itmExcluirMetodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmExcluirMetodoActionPerformed
        if (tblMetodologia.getSelectedRow() != -1) {
            deletarMetodologia((Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_itmExcluirMetodoActionPerformed

    private void tblMetodologiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMetodologiaMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDados((Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0));
            novoMetodologia = false;
        }
    }//GEN-LAST:event_tblMetodologiaMouseClicked

    private void txtPesquisarMetodologiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarMetodologiaKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblMetodologia.getModel();
        sorter = new TableRowSorter<>(model);
        tblMetodologia.setRowSorter(sorter);
        String text = txtPesquisarMetodologia.getText().trim();
        String parametro = "*";
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else if (text.indexOf(parametro) > 0) {
            ArrayList<RowFilter<Object, Object>> andFilters = new ArrayList<>();
            andFilters.add(RowFilter.regexFilter("(?i)" + text.substring(0, text.indexOf(parametro)).trim().toUpperCase()));
            andFilters.add(RowFilter.regexFilter("(?i)" + text.substring(text.indexOf(parametro) + 1, text.length()).trim().toUpperCase()));
            sorter.setRowFilter(RowFilter.andFilter(andFilters));
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.toUpperCase()));
        }
    }//GEN-LAST:event_txtPesquisarMetodologiaKeyReleased

    private void btnExcluirMetodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirMetodoActionPerformed
        deletarMetodologia((Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirMetodoActionPerformed

    private void btnSalvarMetodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMetodoActionPerformed
        if (txtCodMetodo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código inválido");
            txtCodMetodo.requestFocus();
        } else if (txtVersao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Versão inválido");
            txtVersao.requestFocus();
        } else if (txtMetodo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Método inválido");
            txtMetodo.requestFocus();
        } else if (novoMetodologia) {
            criarMetodologia();
        } else {
            atulizarMetodologia();
        }
    }//GEN-LAST:event_btnSalvarMetodoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblMetodologiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblMetodologiaKeyReleased
        if (tblMetodologia.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarMetodologia((Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblMetodologiaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnExcluirMetodo;
    public static javax.swing.JButton btnSalvarMetodo;
    public static javax.swing.JMenuItem itmEditarMetodo;
    public static javax.swing.JMenuItem itmExcluirMetodo;
    public static javax.swing.JMenuItem itmNovoMetodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblId;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuEditMetodo;
    public static javax.swing.JMenu mnuNovoMetodo;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JPanel pnlMetodo;
    private javax.swing.JPanel pnlMetodologia;
    private javax.swing.JPanel pnlProdutos;
    private javax.swing.JTable tblAcompanhamento;
    private javax.swing.JTable tblMetodologia;
    private javax.swing.JTabbedPane tblPaneMetodologia;
    private javax.swing.JTable tblProdutoMetodologia;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtCodMetodo;
    private javax.swing.JTextField txtLink;
    private javax.swing.JTextField txtMetodo;
    private javax.swing.JTextField txtPesquisarMetodologia;
    private javax.swing.JTextField txtVersao;
    // End of variables declaration//GEN-END:variables
}
