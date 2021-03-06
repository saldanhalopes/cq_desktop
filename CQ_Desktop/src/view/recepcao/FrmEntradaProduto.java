package view.recepcao;

import DAO.AnaliseDAO;
import DAO.LoteDAO;
import DAO.MaterialDAO;
import model.Analise;
import model.Lote;
import model.Material;
import util.DataHora;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public final class FrmEntradaProduto extends javax.swing.JInternalFrame {

    private boolean novoEntradaProduto = false;
    private TreeMap<Integer, String> categoryMap = new TreeMap<>();

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
    public FrmEntradaProduto() {
        initComponents();
        readCadastroProduto();
        txtEntradaProduto.setMaxSelectableDate(new Date());
        pnlCadastroProduto.setEnabledAt(1, false);
        pnlProdutos.setVisible(false);
    }

    public final void readEditarCadastroProduto() {
        DefaultTableModel model = (DefaultTableModel) tblEditarCadastroProduto.getModel();
        MaterialDAO equipDao = new MaterialDAO();
        model.setNumRows(0);
        for (Material prod : equipDao.readMaterial()) {
            model.addRow(new Object[]{
                prod.getMaterial_id(),
                prod.getCod_material(),
                prod.getMaterial(),
                prod.getTipo(),
                prod.getCod_metodo(),
                prod.getMetodo().getMetodo_id()
            });
        }
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

    public final void readFinalidadeProduto() {
        cmbFinalidadeProduto.removeAllItems();
        cmbFinalidadeProduto.addItem("");
        categoryMap.clear();
        AnaliseDAO anlsDao = new AnaliseDAO();
        for (Analise anls : anlsDao.readAnaliseFinalidade()) {
            Integer id = anls.getAnalise_finalidade_id();
            String name = anls.getAnalise_finalidade();
            cmbFinalidadeProduto.addItem(name);
            categoryMap.put(id, name);
        }
    }

    private void carregarDadosCadastroProduto(int id) {
        novoEntradaProduto = false;
        readEditarCadastroProduto();
        readFinalidadeProduto();
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
        txtEntradaProduto.setDate(DataHora.getTimestampDate(lot.getData_entrada()));
        String[] finalidade = {lot.getAnalise().getAnalise_finalidade()};
        cmbFinalidadeProduto.setSelectedItem(finalidade[0]);
        String[] status = {lot.getLote_status()};
        cmbStatusCadastroProduto.setSelectedItem(status[0]);
        tblEditarCadastroProduto.setEnabled(false);
        txtPesquisarCadastroProduto.setEnabled(false);
        pnlCadastroProduto.setEnabledAt(0, false);
        pnlCadastroProduto.setEnabledAt(1, true);
        pnlCadastroProduto.setSelectedIndex(1);
        btnExcluirCadastroProduto.setVisible(true);
        mnuEditarLote.setEnabled(false);
        mnuNovoLote.setEnabled(false);
    }

    private void abrirDados() {
        pnlCadastroProduto.setEnabledAt(0, false);
        pnlCadastroProduto.setEnabledAt(1, true);
        pnlCadastroProduto.setSelectedIndex(1);
        mnuNovoLote.setEnabled(false);
        mnuEditarLote.setEnabled(false);
        btnExcluirCadastroProduto.setVisible(false);
        tblEditarCadastroProduto.setEnabled(true);
        limparCampos();
        novoEntradaProduto = true;
        txtEntradaProduto.setDate(new Date());
        readEditarCadastroProduto();
        readFinalidadeProduto();
    }

    private void fecharDados() {
        pnlCadastroProduto.setEnabledAt(0, true);
        pnlCadastroProduto.setEnabledAt(1, false);
        pnlCadastroProduto.setSelectedIndex(0);
        tblLotes.setRowSorter(null);
        mnuNovoLote.setEnabled(true);
        mnuEditarLote.setEnabled(true);
        novoEntradaProduto = false;
        limparCampos();
        readCadastroProduto();
    }

    private void limparCampos() {
        txtCodMaterial.setText(null);
        txtEntradaProduto.setDate(null);
        txtLoteProduto.setText(null);
        txtLoteProduto.setText(null);
        txtMaterial.setText(null);
        txtObsProduto.setText(null);
        txtPesquisarLotes.setText(null);
        txtPesquisarCadastroProduto.setText(null);
        txtTipo.setText(null);
        txtIdProduto.setText(null);
        tblLotes.setRowSorter(null);
        tblEditarCadastroProduto.setRowSorter(null);
    }

    private void criarCadastroProduto() {
        Lote lot = new Lote();
        LoteDAO lotDAO = new LoteDAO();
        try {
            Material prod = new Material();
            Analise alns = new Analise();
            prod.setMaterial_id(Integer.parseInt(txtIdProduto.getText()));
            alns.setAnalise_finalidade_id(getKeyForValue(cmbFinalidadeProduto.getSelectedItem().toString()));
            lot.setProduto(prod);
            lot.setAnalise(alns);
            lot.setLote_produto(txtLoteProduto.getText().toUpperCase());
            lot.setData_entrada(DataHora.getTimestampDate(txtEntradaProduto.getDate()));
            Calendar c = txtEntradaProduto.getCalendar();
            c.add(Calendar.DATE, +5);
            lot.setData_prevista(DataHora.getTimestampDate(c.getTime()));
            lot.setLote_obs(txtObsProduto.getText().toUpperCase());
            lot.setLote_status(cmbStatusCadastroProduto.getSelectedItem().toString());
            lotDAO.createLote(lot);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void atulizarCadastroProduto(int lote_id) {
        Lote lot = new Lote();
        LoteDAO lotDAO = new LoteDAO();
        try {
            Material prod = new Material();
            prod.setMaterial_id(Integer.parseInt(txtIdProduto.getText()));
            Analise alns = new Analise();
            alns.setAnalise_finalidade_id(getKeyForValue(cmbFinalidadeProduto.getSelectedItem().toString()));
            lot.setLote_id(lote_id);
            lot.setProduto(prod);
            lot.setAnalise(alns);
            lot.setLote_produto(txtLoteProduto.getText().toUpperCase());
            lot.setData_entrada(DataHora.getTimestampDate(txtEntradaProduto.getDate()));
            Calendar c = txtEntradaProduto.getCalendar();
            c.add(Calendar.DATE, +5);
            lot.setData_prevista(DataHora.getTimestampDate(c.getTime()));
            lot.setLote_status(cmbStatusCadastroProduto.getSelectedItem().toString());
            lot.setLote_obs(txtObsProduto.getText().toUpperCase());
            lotDAO.updateLote(lot);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
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

        pnlCadastroProduto = new javax.swing.JTabbedPane();
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
        pnlProdutos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEditarCadastroProduto = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisarCadastroProduto = new javax.swing.JTextField();
        pnlDados = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLoteProduto = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtEntradaProduto = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtMaterial = new javax.swing.JTextField();
        txtIdProduto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodMaterial = new javax.swing.JTextField();
        cmbStatusCadastroProduto = new javax.swing.JComboBox<String>();
        jLabel7 = new javax.swing.JLabel();
        cmbFinalidadeProduto = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObsProduto = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnExcluirCadastroProduto = new javax.swing.JButton();
        btnSalvarCadastroProduto = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoLote = new javax.swing.JMenu();
        itmNovoLote = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        mnuEditarLote = new javax.swing.JMenu();
        itmEditarLote = new javax.swing.JMenuItem();
        itmExcluirlote = new javax.swing.JMenuItem();

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
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
                        .addComponent(txtPesquisarLotes, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
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
            .addGap(0, 983, Short.MAX_VALUE)
            .addGroup(pnlLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jpane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLotesLayout.setVerticalGroup(
            pnlLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(pnlLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jpane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCadastroProduto.addTab("Lotes", pnlLotes);

        pnlProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        tblEditarCadastroProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Material", "Produto", "Tipo", "Método", "metodo_id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEditarCadastroProduto.setSurrendersFocusOnKeystroke(true);
        tblEditarCadastroProduto.getTableHeader().setReorderingAllowed(false);
        tblEditarCadastroProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEditarCadastroProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEditarCadastroProduto);
        if (tblEditarCadastroProduto.getColumnModel().getColumnCount() > 0) {
            tblEditarCadastroProduto.getColumnModel().getColumn(0).setMinWidth(80);
            tblEditarCadastroProduto.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblEditarCadastroProduto.getColumnModel().getColumn(0).setMaxWidth(80);
            tblEditarCadastroProduto.getColumnModel().getColumn(1).setMinWidth(120);
            tblEditarCadastroProduto.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblEditarCadastroProduto.getColumnModel().getColumn(1).setMaxWidth(120);
            tblEditarCadastroProduto.getColumnModel().getColumn(3).setMinWidth(80);
            tblEditarCadastroProduto.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblEditarCadastroProduto.getColumnModel().getColumn(3).setMaxWidth(80);
            tblEditarCadastroProduto.getColumnModel().getColumn(4).setMinWidth(100);
            tblEditarCadastroProduto.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblEditarCadastroProduto.getColumnModel().getColumn(4).setMaxWidth(100);
            tblEditarCadastroProduto.getColumnModel().getColumn(5).setMinWidth(0);
            tblEditarCadastroProduto.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblEditarCadastroProduto.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jLabel4.setText("Pesquisar:");

        txtPesquisarCadastroProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarCadastroProdutoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlProdutosLayout = new javax.swing.GroupLayout(pnlProdutos);
        pnlProdutos.setLayout(pnlProdutosLayout);
        pnlProdutosLayout.setHorizontalGroup(
            pnlProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
            .addGroup(pnlProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisarCadastroProduto))
        );
        pnlProdutosLayout.setVerticalGroup(
            pnlProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProdutosLayout.createSequentialGroup()
                .addGroup(pnlProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPesquisarCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
        );

        pnlDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Lote:");

        jLabel24.setText("Entrada:");

        jLabel5.setText("Produto:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1498097320_pin.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtMaterial.setEditable(false);
        txtMaterial.setBackground(new java.awt.Color(204, 204, 204));
        txtMaterial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtIdProduto.setEditable(false);
        txtIdProduto.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setText("Código:");

        jLabel3.setText("Tipo:");

        txtTipo.setEditable(false);
        txtTipo.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setText("Material:");

        txtCodMaterial.setEditable(false);
        txtCodMaterial.setBackground(new java.awt.Color(204, 204, 204));

        cmbStatusCadastroProduto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Em CQ", "Em Produção", "Finalizado" }));

        jLabel7.setText("Finalidade:");

        jLabel9.setText("Status:");

        txtObsProduto.setColumns(20);
        txtObsProduto.setRows(5);
        jScrollPane3.setViewportView(txtObsProduto);

        jLabel2.setText("Obs:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaterial))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtLoteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEntradaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbFinalidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbStatusCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLoteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel24))
                    .addComponent(txtEntradaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaterial)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCodMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmbFinalidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cmbStatusCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDadosLayout = new javax.swing.GroupLayout(pnlDados);
        pnlDados.setLayout(pnlDadosLayout);
        pnlDadosLayout.setHorizontalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnExcluirCadastroProduto.setText("Excuir");
        btnExcluirCadastroProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCadastroProdutoActionPerformed(evt);
            }
        });

        btnSalvarCadastroProduto.setText("Ok");
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

        javax.swing.GroupLayout pnlEntradaLayout = new javax.swing.GroupLayout(pnlEntrada);
        pnlEntrada.setLayout(pnlEntradaLayout);
        pnlEntradaLayout.setHorizontalGroup(
            pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradaLayout.createSequentialGroup()
                        .addComponent(btnExcluirCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(pnlEntradaLayout.createSequentialGroup()
                        .addComponent(pnlDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(pnlProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlEntradaLayout.setVerticalGroup(
            pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirCadastroProduto)
                    .addComponent(btnSalvarCadastroProduto)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pnlCadastroProduto.addTab("Entrada", pnlEntrada);

        mnuArquivo.setText("Arquivo");

        mnuNovoLote.setText("Novo");

        itmNovoLote.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_INSERT, java.awt.event.InputEvent.ALT_MASK));
        itmNovoLote.setText("Lote");
        itmNovoLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoLoteActionPerformed(evt);
            }
        });
        mnuNovoLote.add(itmNovoLote);

        mnuArquivo.add(mnuNovoLote);

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

        itmExcluirlote.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.ALT_MASK));
        itmExcluirlote.setText("Excluir");
        itmExcluirlote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmExcluirloteActionPerformed(evt);
            }
        });
        mnuEditarLote.add(itmExcluirlote);

        jMenuBar1.add(mnuEditarLote);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCadastroProduto)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCadastroProduto)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarCadastroProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarCadastroProdutoKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblEditarCadastroProduto.getModel();
        sorter = new TableRowSorter<>(model);
        tblEditarCadastroProduto.setRowSorter(sorter);
        String text = txtPesquisarCadastroProduto.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarCadastroProdutoKeyReleased

    private void tblEditarCadastroProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEditarCadastroProdutoMouseClicked
        if (evt.getClickCount() == 2) {
            if (!tblEditarCadastroProduto.isEnabled()) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar o produto?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    tblEditarCadastroProduto.setEnabled(true);
                    txtPesquisarCadastroProduto.setEnabled(true);
                }
            } else {
                txtIdProduto.setText(tblEditarCadastroProduto.getValueAt(tblEditarCadastroProduto.getSelectedRow(), 0).toString());
                txtCodMaterial.setText(tblEditarCadastroProduto.getValueAt(tblEditarCadastroProduto.getSelectedRow(), 1).toString());
                txtMaterial.setText(tblEditarCadastroProduto.getValueAt(tblEditarCadastroProduto.getSelectedRow(), 2).toString());
                txtTipo.setText(tblEditarCadastroProduto.getValueAt(tblEditarCadastroProduto.getSelectedRow(), 3).toString());
                pnlDados.setVisible(true);
                pnlProdutos.setVisible(false);
                btnExcluirCadastroProduto.setVisible(false);
                btnSalvarCadastroProduto.setVisible(true);
            }
        }
    }//GEN-LAST:event_tblEditarCadastroProdutoMouseClicked

    private void tblLotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLotesMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDadosCadastroProduto((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
            novoEntradaProduto = false;
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

    private void itmNovoLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoLoteActionPerformed
        abrirDados();
    }//GEN-LAST:event_itmNovoLoteActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmEditarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmEditarLoteActionPerformed
        if (tblLotes.getSelectedRow() != -1) {
            carregarDadosCadastroProduto((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
            novoEntradaProduto = false;
        }
    }//GEN-LAST:event_itmEditarLoteActionPerformed

    private void itmExcluirloteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmExcluirloteActionPerformed
        if (tblLotes.getSelectedRow() != -1) {
            deletarCadastroProduto((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_itmExcluirloteActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (pnlDados.isVisible()) {
            fecharDados();
        } else {
            pnlDados.setVisible(true);
            pnlProdutos.setVisible(false);
            btnExcluirCadastroProduto.setVisible(false);
            btnSalvarCadastroProduto.setVisible(true);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCadastroProdutoActionPerformed
        if (txtLoteProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lote inválido");
            txtLoteProduto.requestFocus();
        } else if (txtIdProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecionar Produto");
        } else if (cmbFinalidadeProduto.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Finalidade da Análise Inválida");
        } else if (txtEntradaProduto.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Data Inválida");
        } else if (novoEntradaProduto) {
            //criarAnaliseAmostra();
            criarCadastroProduto();
        } else {
            atulizarCadastroProduto(((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0)));
        }
    }//GEN-LAST:event_btnSalvarCadastroProdutoActionPerformed

    private void btnExcluirCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCadastroProdutoActionPerformed
        deletarCadastroProduto((Integer) tblLotes.getValueAt(tblLotes.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirCadastroProdutoActionPerformed

    private void cmbLimitPesquisarLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLimitPesquisarLotesActionPerformed
        readCadastroProduto();
    }//GEN-LAST:event_cmbLimitPesquisarLotesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pnlProdutos.setVisible(true);
        pnlDados.setVisible(false);
        btnExcluirCadastroProduto.setVisible(false);
        btnSalvarCadastroProduto.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpane8;
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnExcluirCadastroProduto;
    public static javax.swing.JButton btnSalvarCadastroProduto;
    private javax.swing.JComboBox cmbFinalidadeProduto;
    private javax.swing.JComboBox<String> cmbLimitPesquisarLotes;
    private javax.swing.JComboBox<String> cmbStatusCadastroProduto;
    public static javax.swing.JMenuItem itmEditarLote;
    public static javax.swing.JMenuItem itmExcluirlote;
    public static javax.swing.JMenuItem itmNovoLote;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuEditarLote;
    public static javax.swing.JMenu mnuNovoLote;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JTabbedPane pnlCadastroProduto;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JPanel pnlEntrada;
    private javax.swing.JPanel pnlLotes;
    private javax.swing.JPanel pnlProdutos;
    private javax.swing.JTable tblEditarCadastroProduto;
    private javax.swing.JTable tblLotes;
    private javax.swing.JTextField txtCodMaterial;
    private com.toedter.calendar.JDateChooser txtEntradaProduto;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtLoteProduto;
    private javax.swing.JTextField txtMaterial;
    private javax.swing.JTextArea txtObsProduto;
    private javax.swing.JTextField txtPesquisarCadastroProduto;
    private javax.swing.JTextField txtPesquisarLotes;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
