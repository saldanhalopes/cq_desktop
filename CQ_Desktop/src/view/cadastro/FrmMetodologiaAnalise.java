/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastro;

import DAO.AcompanhamentoDAO;
import DAO.AnaliseDAO;
import DAO.MetodologiaDAO;
import DAO.SetorDAO;
import acesso.MetodologiaAcesso;
import model.Acompanhamento;
import model.Analise;
import model.Metodologia;
import model.Material;
import model.Setor;
import util.ComboBox;
import java.util.TreeMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael
 */
public class FrmMetodologiaAnalise extends javax.swing.JInternalFrame {

    private final TreeMap<Integer, String> categoryMapSetor = new TreeMap<>();
    private final TreeMap<Integer, String> categoryMapAcomp = new TreeMap<>();

    /**
     * Creates new form NovoJInternalFrame
     */
    public FrmMetodologiaAnalise() {
        initComponents();
        setTitle("Metodologias");
        readMetodologia();
        tblPaneMetodologia.setEnabledAt(1, false);
        //MetodologiaAcesso Acesso = new MetodologiaAcesso();
        //Acesso.verificarAcessoMetodologia();
    }

    public final void readMetodologia() {
        DefaultTableModel model = (DefaultTableModel) tblMetodologia.getModel();
        MetodologiaDAO mtdDao = new MetodologiaDAO();
        model.setNumRows(0);
        for (Metodologia mtd : mtdDao.read()) {
            model.addRow(new Object[]{
                mtd.getMetodo_id(),
                mtd.getCod_metodo(),
                mtd.getMetodo(),
                mtd.getVersao()
            });
        }
    }

    public final void readAnalises(Metodologia mtd) {
        DefaultTableModel model = (DefaultTableModel) tblAnaliseMetodo.getModel();
        AnaliseDAO equipDao = new AnaliseDAO();
        model.setNumRows(0);
        JComboBox comboSetor = new JComboBox();
        comboSetor.addItem("");
        categoryMapSetor.clear();
        SetorDAO setorDao = new SetorDAO();
        for (Setor setor : setorDao.readSetor()) {
            Integer id = setor.getSetor_id();
            String name = setor.getSigla_setor();
            comboSetor.addItem(name);
            categoryMapSetor.put(id, name);
        }
        JComboBox comboRA = new JComboBox();
        comboRA.addItem("");
        categoryMapAcomp.clear();
        AcompanhamentoDAO acompDao = new AcompanhamentoDAO();
        for (Acompanhamento acomp : acompDao.readAcompanhamento(mtd)) {
            Integer id = acomp.getRegistro_acompanhamento_id();
            String name = acomp.getSigla_registro_acompanhamento();
            comboRA.addItem(name);
            categoryMapAcomp.put(id, name);
        }
        TableColumn colSetor = tblAnaliseMetodo.getColumnModel().getColumn(5);
        colSetor.setCellEditor(new DefaultCellEditor(comboSetor));
        TableColumn colRA = tblAnaliseMetodo.getColumnModel().getColumn(6);
        colRA.setCellEditor(new DefaultCellEditor(comboRA));
        for (Analise anls : equipDao.readAnaliseMetodologia(mtd)) {
            model.addRow(new Object[]{
                anls.getMetodologia_analise_id(),
                anls.getAnalise_id(),
                anls.getAnalise(),
                anls.getSigla_analise(),
                anls.isMetodologia_analise_ativo(),
                anls.getSetor().getSigla_setor(),
                anls.getAcompanhamento().getSigla_registro_acompanhamento()
            });
        }
    }

    private void fecharDados() {
        tblPaneMetodologia.setEnabledAt(0, true);
        tblPaneMetodologia.setEnabledAt(1, false);
        tblPaneMetodologia.setSelectedIndex(0);
        txtPesquisarMetodologia.setText(null);
        tblMetodologia.setRowSorter(null);
        //MetodologiaAcesso Acesso = new MetodologiaAcesso();
        //Acesso.verificarAcessoMetodologia();
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
            pnlAnalises.setVisible(false);
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
            tblPaneMetodologia.setEnabledAt(0, false);
            tblPaneMetodologia.setEnabledAt(1, true);
            tblPaneMetodologia.setSelectedIndex(1);
            mnuEditMetodo.setEnabled(false);
            pnlAnalises.setVisible(true);
            readAnalises(mtd);
        }
    }

    private void atulizarMetodologia(int metodo_id) {
        try {
            int rows = tblAnaliseMetodo.getRowCount();
            for (int row = 0; row < rows; row++) {
                if ((Integer) tblAnaliseMetodo.getValueAt(row, 0) > 0) {
                    Analise anlse = new Analise();
                    AnaliseDAO anlseDAO = new AnaliseDAO();
                    Setor setor = new Setor();
                    Acompanhamento acomp = new Acompanhamento(); 
                    anlse.setMetodologia_analise_id((Integer) tblAnaliseMetodo.getValueAt(row, 0));
                    anlse.setMetodologia_analise_ativo((Boolean) tblAnaliseMetodo.getValueAt(row, 4));
                    setor.setSetor_id(ComboBox.getKeyForValue((String) tblAnaliseMetodo.getModel().getValueAt(row, 5), categoryMapSetor));
                    anlse.setSetor(setor);
                    acomp.setRegistro_acompanhamento_id(ComboBox.getKeyForValue((String) tblAnaliseMetodo.getModel().getValueAt(row, 6), categoryMapAcomp));
                    anlse.setAcompanhamento(acomp);
                    anlseDAO.updateAnaliseMetodologia(anlse);
                } else if ((Boolean) tblAnaliseMetodo.getValueAt(row, 4)
                        || ComboBox.getKeyForValue((String) tblAnaliseMetodo.getModel().getValueAt(row, 5), categoryMapSetor) > 0
                        || ComboBox.getKeyForValue((String) tblAnaliseMetodo.getModel().getValueAt(row, 6), categoryMapAcomp) > 0) {
                    Analise anlse = new Analise();
                    AnaliseDAO anlseDAO = new AnaliseDAO();
                    Setor setor = new Setor();
                    Acompanhamento acomp = new Acompanhamento(); 
                    anlse.setMetodo_id_metodologia_analise_id(metodo_id);
                    anlse.setAnalise_id((Integer) tblAnaliseMetodo.getValueAt(row, 1));
                    anlse.setMetodologia_analise_ativo((Boolean) tblAnaliseMetodo.getValueAt(row, 4));
                    setor.setSetor_id(ComboBox.getKeyForValue((String) tblAnaliseMetodo.getModel().getValueAt(row, 5), categoryMapSetor));
                    anlse.setSetor(setor);
                    acomp.setRegistro_acompanhamento_id(ComboBox.getKeyForValue((String) tblAnaliseMetodo.getModel().getValueAt(row, 6), categoryMapAcomp));
                    anlse.setAcompanhamento(acomp);
                    anlseDAO.createAnaliseMetodologia(anlse);
                }
            }
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
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
        pnlAnalises = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAnaliseMetodo = new javax.swing.JTable();
        btnSalvarMetodo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoMetodo = new javax.swing.JMenu();
        itmNovoMetodo = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        mnuEditMetodo = new javax.swing.JMenu();
        itmEditarMetodo = new javax.swing.JMenuItem();

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
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

        pnlAnalises.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblAnaliseMetodo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Metodo", "Id", "Análise", "Sigla", "Check", "Setor", "RA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAnaliseMetodo.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblAnaliseMetodo);
        if (tblAnaliseMetodo.getColumnModel().getColumnCount() > 0) {
            tblAnaliseMetodo.getColumnModel().getColumn(0).setMinWidth(0);
            tblAnaliseMetodo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblAnaliseMetodo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblAnaliseMetodo.getColumnModel().getColumn(1).setMinWidth(50);
            tblAnaliseMetodo.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblAnaliseMetodo.getColumnModel().getColumn(1).setMaxWidth(50);
            tblAnaliseMetodo.getColumnModel().getColumn(3).setMinWidth(80);
            tblAnaliseMetodo.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblAnaliseMetodo.getColumnModel().getColumn(3).setMaxWidth(80);
            tblAnaliseMetodo.getColumnModel().getColumn(4).setMinWidth(80);
            tblAnaliseMetodo.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblAnaliseMetodo.getColumnModel().getColumn(4).setMaxWidth(80);
            tblAnaliseMetodo.getColumnModel().getColumn(5).setMinWidth(120);
            tblAnaliseMetodo.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblAnaliseMetodo.getColumnModel().getColumn(5).setMaxWidth(120);
            tblAnaliseMetodo.getColumnModel().getColumn(6).setMinWidth(200);
            tblAnaliseMetodo.getColumnModel().getColumn(6).setPreferredWidth(200);
            tblAnaliseMetodo.getColumnModel().getColumn(6).setMaxWidth(200);
        }

        javax.swing.GroupLayout pnlAnalisesLayout = new javax.swing.GroupLayout(pnlAnalises);
        pnlAnalises.setLayout(pnlAnalisesLayout);
        pnlAnalisesLayout.setHorizontalGroup(
            pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnalisesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        pnlAnalisesLayout.setVerticalGroup(
            pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnalisesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDadosLayout.createSequentialGroup()
                .addContainerGap(570, Short.MAX_VALUE)
                .addComponent(btnSalvarMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(pnlAnalises, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addComponent(pnlAnalises, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvarMetodo))
                .addGap(15, 15, 15))
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
        }
    }//GEN-LAST:event_itmEditarMetodoActionPerformed

    private void tblMetodologiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMetodologiaMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDados((Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_tblMetodologiaMouseClicked

    private void txtPesquisarMetodologiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarMetodologiaKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblMetodologia.getModel();
        sorter = new TableRowSorter<>(model);
        tblMetodologia.setRowSorter(sorter);
        String text = txtPesquisarMetodologia.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarMetodologiaKeyReleased

    private void btnSalvarMetodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMetodoActionPerformed
        atulizarMetodologia((Integer) tblMetodologia.getValueAt(tblMetodologia.getSelectedRow(), 0));
        fecharDados();
    }//GEN-LAST:event_btnSalvarMetodoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnSalvarMetodo;
    public static javax.swing.JMenuItem itmEditarMetodo;
    public static javax.swing.JMenuItem itmNovoMetodo;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuEditMetodo;
    public static javax.swing.JMenu mnuNovoMetodo;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JPanel pnlAnalises;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JPanel pnlMetodologia;
    private javax.swing.JTable tblAnaliseMetodo;
    private javax.swing.JTable tblMetodologia;
    private javax.swing.JTabbedPane tblPaneMetodologia;
    private javax.swing.JTextField txtPesquisarMetodologia;
    // End of variables declaration//GEN-END:variables
}
