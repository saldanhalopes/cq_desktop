/*
 * Copyright (C) 2017 rafael.lopes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package view.recepcao;

import DAO.AmostraDAO;
import DAO.AnaliseDAO;
import DAO.LoteDAO;
import model.Amostra;
import model.Analise;
import model.Lote;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael.lopes
 */
public final class FrmEntradaAmostra extends javax.swing.JInternalFrame {

    private boolean novoEntradaAmostra = false;
    private TreeMap<Integer, String> categoryMap = new TreeMap<Integer, String>();

    private int getKeyForValue(String value) {
        for (Map.Entry<Integer, String> entry : categoryMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * Creates new form FrmCadastroLote
     */
    public FrmEntradaAmostra() {
        initComponents();
        readCadastroProduto();
        pnlCadastroAmostra.setEnabledAt(1, false);
    }

    public final void readCadastroProduto() {
        DefaultTableModel model = (DefaultTableModel) tblLotes.getModel();
        LoteDAO equipDao = new LoteDAO();
        model.setNumRows(0);
        for (Lote lot : equipDao.readCadastroProduto(cmbLimitPesquisarLotes.getSelectedItem().toString())) {
            model.addRow(new Object[]{
                lot.getLote_id(),
                lot.getAnalise().getSigla_analise_finalidade() + " - "
                + lot.getProduto().getMaterial(),
                lot.getLote_produto(),
                lot.getData_entrada(),
                lot.getLote_status()
            });
        }
    }

    public final void readAnalises(int id) {
        DefaultTableModel model = (DefaultTableModel) tblAnaliseMetodo.getModel();
        AnaliseDAO anlsDao = new AnaliseDAO();
        model.setNumRows(0);
        for (Analise anls : anlsDao.readAnaliseEntrada(id)) {
            model.addRow(new Object[]{
                anls.getMetodologia_analise_id(),
                anls.getAnalise_id(),
                anls.getAnalise(),
                anls.getSigla_analise(),
                anls.getSetor().getSigla_setor(),
                anls.getAcompanhamento().getSigla_registro_acompanhamento(),
                anls.isAnalise_criada()
            });
        }
    }

    public final void readStatusAmostra() {
        cmbStatusAmostra.removeAllItems();
        cmbStatusAmostra.addItem("");
        categoryMap.clear();
        AmostraDAO amsDao = new AmostraDAO();
        for (Amostra ams : amsDao.readAmostraStatus()) {
            Integer id = ams.getAmostra_status_id();
            String name = ams.getAmostra_status();
            cmbStatusAmostra.addItem(name);
            categoryMap.put(id, name);
        }
    }

    private void carregarDadosCadastroAmostra(int id) {
        Lote lot = new Lote();
        lot.setLote_id(id);
        LoteDAO lotDAO = new LoteDAO();
        lotDAO.selectLote(lot);
        txtLoteProduto.setText(lot.getLote_produto());
        txtIdProduto.setText(lot.getLote_id().toString());
        txtObsProduto.setText(lot.getLote_obs());
        txtCodMaterial.setText(lot.getProduto().getCod_material());
        txtMaterial.setText(lot.getProduto().getMaterial());
        txtTipo.setText(lot.getProduto().getTipo());
        mnuEditarLote.setEnabled(false);
        pnlCadastroAmostra.setEnabledAt(0, false);
        pnlCadastroAmostra.setEnabledAt(1, true);
        pnlCadastroAmostra.setSelectedIndex(1);
        pnlDados.setVisible(false);
        pnlAnalises.setVisible(true);
        readAnalises(lot.getMetodo().getMetodo_id());
        readStatusAmostra();
    }
    
    private void carregarDadosAnaliseAmostra(int id) {
        novoEntradaAmostra = !(Boolean) tblAnaliseMetodo.getValueAt(tblAnaliseMetodo.getSelectedRow(), 5);
        readStatusAmostra();
        Amostra ams = new Amostra();
        AmostraDAO amsDao = new AmostraDAO();
        amsDao.selectAmostra(ams);
        txtQuantidade.setText(ams.getQuantidade_amostra());
        String[] status = {ams.getAmostra_status()};
        cmbStatusAmostra.setSelectedItem(status[0]);
        mnuEditarLote.setEnabled(false);
        pnlDados.setVisible(true);
        pnlAnalises.setVisible(false);
    }

    private void fecharDados() {
        pnlCadastroAmostra.setEnabledAt(0, true);
        pnlCadastroAmostra.setEnabledAt(1, false);
        pnlCadastroAmostra.setSelectedIndex(0);
        tblLotes.setRowSorter(null);
        mnuEditarLote.setEnabled(true);
        novoEntradaAmostra = false;
        limparCampos();
        readCadastroProduto();
    }

    private void fecharDadosAnalises() {
        pnlDados.setVisible(false);
        pnlAnalises.setVisible(true);
        limparCampos();
        carregarDadosCadastroAmostra((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
    }

    private void limparCampos() {
        txtObsProduto.setText(null);
        tblLotes.setRowSorter(null);
        txtQuantidade.setText(null);
    }

    private void criarAnaliseAmostra() {
        try {
            Amostra ams = new Amostra();
            AmostraDAO amsDAO = new AmostraDAO();
            Lote lot = new Lote();
            Analise anls = new Analise();
            lot.setLote_id((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
            anls.setMetodologia_analise_id((Integer) tblAnaliseMetodo.getValueAt(tblAnaliseMetodo.getSelectedRow(), 4));
            ams.setLote(lot);
            ams.setAnalise(anls);
            ams.setAmostra_status_id(getKeyForValue(cmbStatusAmostra.getSelectedItem().toString()));
            ams.setQuantidade_amostra(txtQuantidade.getText().toUpperCase());
            ams.setObs_amostra(txtObsProduto.getText().toUpperCase());
            JOptionPane.showMessageDialog(null, ams.getLote().getLote_id());
            JOptionPane.showMessageDialog(null, ams.getAnalise().getMetodologia_analise_id());
            JOptionPane.showMessageDialog(null, ams.getAmostra_status_id());
            JOptionPane.showMessageDialog(null, ams.getQuantidade_amostra());
            JOptionPane.showMessageDialog(null, ams.getObs_amostra());
            amsDAO.createAmostra(ams);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDadosAnalises();
        }
    }

    private void atulizarAnaliseAmostra(int lote_id) {
        Lote lot = new Lote();
        LoteDAO lotDAO = new LoteDAO();
        try {
            JOptionPane.showMessageDialog(null, "atualizar");
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDadosAnalises();
        }
    }

    private void deletarCadastroProduto(int lote_id) {
        Lote lot = new Lote();
        LoteDAO lotDAO = new LoteDAO();
        try {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
            if (dialogResult == 0) {
                lot.setLote_id(lote_id);
                lotDAO.deleteLote(lot);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCadastroAmostra = new javax.swing.JTabbedPane();
        pnlLotes = new javax.swing.JPanel();
        Jpane8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLotes = new javax.swing.JTable();
        txtPesquisarLotes = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbLimitPesquisarLotes = new javax.swing.JComboBox<String>();
        jLabel11 = new javax.swing.JLabel();
        pnlEntrada = new javax.swing.JPanel();
        pnlDados = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObsProduto = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmbStatusAmostra = new javax.swing.JComboBox<String>();
        btnSalvarCadastroProduto = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlAnalises = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAnaliseMetodo = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtMaterial = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodMaterial = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtLoteProduto = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuSair = new javax.swing.JMenuItem();
        mnuEditarLote = new javax.swing.JMenu();
        itmEditarLote = new javax.swing.JMenuItem();

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Lotes"));

        tblLotes.setAutoCreateRowSorter(true);
        tblLotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Produto", "Lote", "Data Entrada", "Status"
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
        tblLotes.setSurrendersFocusOnKeystroke(true);
        tblLotes.getTableHeader().setReorderingAllowed(false);
        tblLotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLotesMouseClicked(evt);
            }
        });
        tblLotes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblLotesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblLotes);
        if (tblLotes.getColumnModel().getColumnCount() > 0) {
            tblLotes.getColumnModel().getColumn(0).setMinWidth(60);
            tblLotes.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblLotes.getColumnModel().getColumn(0).setMaxWidth(60);
            tblLotes.getColumnModel().getColumn(2).setMinWidth(80);
            tblLotes.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblLotes.getColumnModel().getColumn(2).setMaxWidth(80);
            tblLotes.getColumnModel().getColumn(3).setMinWidth(120);
            tblLotes.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblLotes.getColumnModel().getColumn(3).setMaxWidth(120);
            tblLotes.getColumnModel().getColumn(4).setMinWidth(100);
            tblLotes.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblLotes.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        txtPesquisarLotes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarLotesKeyReleased(evt);
            }
        });

        jLabel6.setText("Pesquisar:");

        cmbLimitPesquisarLotes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100", "1000", "10000" }));
        cmbLimitPesquisarLotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLimitPesquisarLotesActionPerformed(evt);
            }
        });

        jLabel11.setText("Visualizar:");

        javax.swing.GroupLayout Jpane8Layout = new javax.swing.GroupLayout(Jpane8);
        Jpane8.setLayout(Jpane8Layout);
        Jpane8Layout.setHorizontalGroup(
            Jpane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpane8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpane8Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarLotes, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLimitPesquisarLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Jpane8Layout.setVerticalGroup(
            Jpane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpane8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarLotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmbLimitPesquisarLotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlLotesLayout = new javax.swing.GroupLayout(pnlLotes);
        pnlLotes.setLayout(pnlLotesLayout);
        pnlLotesLayout.setHorizontalGroup(
            pnlLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 979, Short.MAX_VALUE)
            .addGroup(pnlLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jpane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLotesLayout.setVerticalGroup(
            pnlLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
            .addGroup(pnlLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jpane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCadastroAmostra.addTab("Lotes", pnlLotes);

        pnlDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        jLabel2.setText("Obs:");

        txtObsProduto.setColumns(20);
        txtObsProduto.setRows(5);
        jScrollPane3.setViewportView(txtObsProduto);

        jLabel12.setText("Qtd:");

        jLabel9.setText("Status:");

        btnSalvarCadastroProduto.setText("Salvar");
        btnSalvarCadastroProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCadastroProdutoActionPerformed(evt);
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
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDadosLayout.createSequentialGroup()
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbStatusAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 529, Short.MAX_VALUE))
                            .addComponent(jScrollPane3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDadosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cmbStatusAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarCadastroProduto)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pnlAnalises.setBorder(javax.swing.BorderFactory.createTitledBorder("Análises"));

        tblAnaliseMetodo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Metodo", "Id", "Análise", "Sigla", "Setor", "RA", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
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
        jScrollPane5.setViewportView(tblAnaliseMetodo);

        javax.swing.GroupLayout pnlAnalisesLayout = new javax.swing.GroupLayout(pnlAnalises);
        pnlAnalises.setLayout(pnlAnalisesLayout);
        pnlAnalisesLayout.setHorizontalGroup(
            pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        pnlAnalisesLayout.setVerticalGroup(
            pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        jLabel5.setText("Produto:");

        txtMaterial.setEditable(false);
        txtMaterial.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setText("Código:");

        txtIdProduto.setEditable(false);
        txtIdProduto.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setText("Tipo:");

        txtTipo.setEditable(false);
        txtTipo.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setText("Material:");

        txtCodMaterial.setEditable(false);
        txtCodMaterial.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Lote:");

        txtLoteProduto.setEditable(false);
        txtLoteProduto.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout pnlEntradaLayout = new javax.swing.GroupLayout(pnlEntrada);
        pnlEntrada.setLayout(pnlEntradaLayout);
        pnlEntradaLayout.setHorizontalGroup(
            pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEntradaLayout.createSequentialGroup()
                        .addComponent(pnlAnalises, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(pnlDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEntradaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodMaterial)
                        .addContainerGap())))
        );
        pnlEntradaLayout.setVerticalGroup(
            pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCodMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtLoteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAnalises, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFechar)
                .addContainerGap())
        );

        pnlCadastroAmostra.addTab("Cadastro Amostras", pnlEntrada);

        mnuArquivo.setText("Arquivo");

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuArquivo.add(mnuSair);

        jMenuBar1.add(mnuArquivo);

        mnuEditarLote.setText("Editar");

        itmEditarLote.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        itmEditarLote.setText("Editar");
        itmEditarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmEditarLoteActionPerformed(evt);
            }
        });
        mnuEditarLote.add(itmEditarLote);

        jMenuBar1.add(mnuEditarLote);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCadastroAmostra)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCadastroAmostra)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblLotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLotesMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDadosCadastroAmostra((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_tblLotesMouseClicked

    private void tblLotesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblLotesKeyReleased
        if (tblLotes.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarCadastroProduto((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblLotesKeyReleased

    private void txtPesquisarLotesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarLotesKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblLotes.getModel();
        sorter = new TableRowSorter<>(model);
        tblLotes.setRowSorter(sorter);
        String text = txtPesquisarLotes.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarLotesKeyReleased

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmEditarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmEditarLoteActionPerformed
        if (tblLotes.getSelectedRow() != -1) {
            carregarDadosCadastroAmostra((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_itmEditarLoteActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSalvarCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCadastroProdutoActionPerformed
        if (txtQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantidade de amostra inválida");
            txtQuantidade.requestFocus();
        } else if (cmbStatusAmostra.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Status inválido");
            cmbStatusAmostra.requestFocus();
        } else if (novoEntradaAmostra) {
            criarAnaliseAmostra();
        } else {
            atulizarAnaliseAmostra(((Integer) tblAnaliseMetodo.getValueAt(tblAnaliseMetodo.getSelectedRow(), 4)));
        }
    }//GEN-LAST:event_btnSalvarCadastroProdutoActionPerformed

    private void cmbLimitPesquisarLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLimitPesquisarLotesActionPerformed
        readCadastroProduto();
    }//GEN-LAST:event_cmbLimitPesquisarLotesActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        fecharDadosAnalises();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpane8;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFechar;
    public static javax.swing.JButton btnSalvarCadastroProduto;
    private javax.swing.JComboBox<String> cmbLimitPesquisarLotes;
    private javax.swing.JComboBox<String> cmbStatusAmostra;
    public static javax.swing.JMenuItem itmEditarLote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuEditarLote;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JPanel pnlAnalises;
    private javax.swing.JTabbedPane pnlCadastroAmostra;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JPanel pnlEntrada;
    private javax.swing.JPanel pnlLotes;
    private javax.swing.JTable tblAnaliseMetodo;
    private javax.swing.JTable tblLotes;
    private javax.swing.JTextField txtCodMaterial;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtLoteProduto;
    private javax.swing.JTextField txtMaterial;
    private javax.swing.JTextArea txtObsProduto;
    private javax.swing.JTextField txtPesquisarLotes;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
