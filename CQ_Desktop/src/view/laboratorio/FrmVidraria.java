/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.laboratorio;

import DAO.SetorDAO;
import DAO.UnidadeDAO;
import DAO.VidrariaDAO;
import acesso.VidrariaAcesso;
import model.Setor;
import model.Unidade;
import model.Vidraria;
import util.ComboBox;
import util.DataHora;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael
 */
public class FrmVidraria extends javax.swing.JInternalFrame {

    private boolean novoVidraria = false;
    private TreeMap<Integer, String> categoryMapVidrariaTipo = new TreeMap<>();
    private TreeMap<Integer, String> categoryMapSetorPesquisar = new TreeMap<>();
    private TreeMap<Integer, String> categoryMapSetor = new TreeMap<>();
    private TreeMap<Integer, String> categoryMapUnidade = new TreeMap<>();

    /**
     * Creates new form NovoJInternalFrame
     */
    public FrmVidraria() {
        initComponents();
        setTitle("Vidrarias");
        readSetorPesquisar();
        txtEntradaVidraria.setMaxSelectableDate(new Date());
        pnlVidraria.setEnabledAt(1, false);
        VidrariaAcesso Acesso = new VidrariaAcesso();
        Acesso.verificarAcessoVidraria();
    }

    public final void readVidrarias() {
        DefaultTableModel model = (DefaultTableModel) tblVidraria.getModel();
        VidrariaDAO vidrDao = new VidrariaDAO();
        model.setNumRows(0);
        int status = cmbPesquisarStatus.getSelectedIndex();
        String limit = cmbLimitPesquisarVidraria.getSelectedItem().toString();
        String setor = cmbPesquisarSetor.getSelectedItem().toString();
        for (Vidraria vidr : vidrDao.readVidraria(limit, setor, status)) {
            model.addRow(new Object[]{
                vidr.getVidraria_id(),
                vidr.getVidraria_tipo(),
                vidr.getVolume() + " "
                + vidr.getUnidade().getSigla_unidade(),
                vidr.getCertificado(),
                vidr.getData_entrada(),
                vidr.getData_saida(),
                vidr.getSetor().getSigla_setor(),
                vidr.getObs_vidraria()
            });
        }
    }

    public final void readVidrariaTipo() {
        cmbVidrariaTipo.removeAllItems();
        cmbVidrariaTipo.addItem("");
        categoryMapVidrariaTipo.clear();
        VidrariaDAO vidrDao = new VidrariaDAO();
        for (Vidraria vidr : vidrDao.readVidrariaTipo()) {
            Integer id = vidr.getVidraria_tipo_id();
            String name = vidr.getVidraria_tipo();
            cmbVidrariaTipo.addItem(name);
            categoryMapVidrariaTipo.put(id, name);
        }
    }

    public final void readSetor() {
        cmbSetor.removeAllItems();
        cmbSetor.addItem("");
        categoryMapSetor.clear();
        SetorDAO setorDao = new SetorDAO();
        for (Setor setor : setorDao.readSetor()) {
            Integer id = setor.getSetor_id();
            String name = setor.getSigla_setor();
            cmbSetor.addItem(name);
            categoryMapSetor.put(id, name);
        }
    }

    public final void readSetorPesquisar() {
        cmbPesquisarSetor.removeAllItems();
        cmbPesquisarSetor.addItem("");
        categoryMapSetorPesquisar.clear();
        SetorDAO setorDao = new SetorDAO();
        for (Setor setor : setorDao.readSetor()) {
            Integer id = setor.getSetor_id();
            String name = setor.getSigla_setor();
            cmbPesquisarSetor.addItem(name);
            categoryMapSetorPesquisar.put(id, name);
        }
    }

    public final void readUnidade() {
        cmbUnidade.removeAllItems();
        cmbUnidade.addItem("");
        categoryMapUnidade.clear();
        UnidadeDAO undDao = new UnidadeDAO();
        for (Unidade und : undDao.readUnidade()) {
            Integer id = und.getUnidade_id();
            String name = und.getSigla_unidade();
            cmbUnidade.addItem(name);
            categoryMapUnidade.put(id, name);
        }
    }

    private void limparCampos() {
        txtEntradaVidraria.setDate(null);
        txtVolume.setText(null);
        txtObsVidraria.setText(null);
        lblId.setText(null);
        txtCertificado.setText(null);
    }

    private void criarVidraria() {
        Vidraria vidr = new Vidraria();
        Setor setor = new Setor();
        Unidade und = new Unidade();
        VidrariaDAO vidrDAO = new VidrariaDAO();
        try {
            vidr.setVidraria_tipo_id(ComboBox.getKeyForValue(cmbVidrariaTipo.getSelectedItem().toString(), categoryMapVidrariaTipo));
            setor.setSetor_id(ComboBox.getKeyForValue(cmbSetor.getSelectedItem().toString(), categoryMapSetor));
            vidr.setSetor(setor);
            und.setUnidade_id(ComboBox.getKeyForValue(cmbUnidade.getSelectedItem().toString(), categoryMapUnidade));
            vidr.setUnidade(und);
            vidr.setVolume(txtVolume.getText());
            vidr.setCertificado(txtCertificado.getText().toUpperCase());
            vidr.setData_entrada(DataHora.getTimestampDate(txtEntradaVidraria.getDate()));
            vidr.setObs_vidraria(txtObsVidraria.getText().toUpperCase());
            vidrDAO.createVidraria(vidr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void fecharDados() {
        pnlVidraria.setEnabledAt(0, true);
        pnlVidraria.setEnabledAt(1, false);
        pnlVidraria.setSelectedIndex(0);
        txtPesquisarVidraria.setText(null);
        tblVidraria.setRowSorter(null);
        VidrariaAcesso Acesso = new VidrariaAcesso();
        Acesso.verificarAcessoVidraria();
        limparCampos();
        readVidrarias();
    }

    private void abrirDados() {
        VidrariaAcesso Acesso = new VidrariaAcesso();
        if (Acesso.verificarAbrirDadosVidraria()) {
            pnlVidraria.setEnabledAt(0, false);
            pnlVidraria.setEnabledAt(1, true);
            pnlVidraria.setSelectedIndex(1);
            btnBaixaVidraria.setVisible(false);
            limparCampos();
            novoVidraria = true;
            readSetor();
            readVidrariaTipo();
            readUnidade();
        }
    }

    private void carregarDados(int id) {
        VidrariaAcesso Acesso = new VidrariaAcesso();
        if (Acesso.verificarCarregarDadosVidraria()) {
            readSetor();
            readVidrariaTipo();
            readUnidade();
            Vidraria vidr = new Vidraria();
            vidr.setVidraria_id(id);
            VidrariaDAO vidrDao = new VidrariaDAO();
            vidrDao.selectVidraria(vidr);
            lblId.setText(Integer.toString(id));
            String[] tipo = {vidr.getVidraria_tipo()};
            cmbVidrariaTipo.setSelectedItem(tipo[0]);
            String[] setor = {vidr.getSetor().getSigla_setor()};
            cmbSetor.setSelectedItem(setor[0]);
            String[] unidade = {vidr.getUnidade().getSigla_unidade()};
            cmbUnidade.setSelectedItem(unidade[0]);
            txtEntradaVidraria.setDate(DataHora.getTimestampDate(vidr.getData_entrada()));
            txtVolume.setText(vidr.getVolume());
            txtCertificado.setText(vidr.getCertificado());
            txtObsVidraria.setText(vidr.getObs_vidraria());
            pnlVidraria.setEnabledAt(0, false);
            pnlVidraria.setEnabledAt(1, true);
            pnlVidraria.setSelectedIndex(1);
            btnBaixaVidraria.setVisible(true);
        }
    }

    private void atulizarVidraria(int id) {
        Vidraria vidr = new Vidraria();
        Setor setor = new Setor();
        Unidade und = new Unidade();
        VidrariaDAO vidrDAO = new VidrariaDAO();
        try {
            vidr.setVidraria_id(id);
            vidr.setVidraria_tipo_id(ComboBox.getKeyForValue(cmbVidrariaTipo.getSelectedItem().toString(), categoryMapVidrariaTipo));
            setor.setSetor_id(ComboBox.getKeyForValue(cmbSetor.getSelectedItem().toString(), categoryMapSetor));
            vidr.setSetor(setor);
            und.setUnidade_id(ComboBox.getKeyForValue(cmbUnidade.getSelectedItem().toString(), categoryMapUnidade));
            vidr.setUnidade(und);
            vidr.setVolume(txtVolume.getText());
            vidr.setCertificado(txtCertificado.getText().toUpperCase());
            vidr.setData_entrada(DataHora.getTimestampDate(txtEntradaVidraria.getDate()));
            vidr.setObs_vidraria(txtObsVidraria.getText().toUpperCase());
            vidrDAO.updateVidraria(vidr);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void baixaVidraria(int id) {
        VidrariaAcesso Acesso = new VidrariaAcesso();
        if (Acesso.verificarDeletarDadosVidraria()) {
            Vidraria vidr = new Vidraria();
            VidrariaDAO vidrDAO = new VidrariaDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente Deseja dar Baixa Nessa Vidraria?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    vidr.setVidraria_id(id);
                    vidr.setData_saida(DataHora.getTimestampDate(new Date()));
                    vidrDAO.updateBaixaVidraria(vidr);
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

        pnlVidraria = new javax.swing.JTabbedPane();
        pnlVidrarias = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVidraria = new javax.swing.JTable();
        txtPesquisarVidraria = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbLimitPesquisarVidraria = new javax.swing.JComboBox<String>();
        jLabel13 = new javax.swing.JLabel();
        cmbPesquisarStatus = new javax.swing.JComboBox<String>();
        jLabel25 = new javax.swing.JLabel();
        cmbPesquisarSetor = new javax.swing.JComboBox();
        btnImprimirRelatorio = new javax.swing.JButton();
        pnlDados = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtVolume = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtObsVidraria = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cmbSetor = new javax.swing.JComboBox();
        cmbUnidade = new javax.swing.JComboBox();
        cmbVidrariaTipo = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        txtEntradaVidraria = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtCertificado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnBaixaVidraria = new javax.swing.JButton();
        btnSalvarVidraria = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoVidraria = new javax.swing.JMenu();
        itmNovoVidraria = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        mnuEditarVidraria = new javax.swing.JMenu();
        itmEditarVidraria = new javax.swing.JMenuItem();
        itmBaixaVidraria = new javax.swing.JMenuItem();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Vidrarias"));

        tblVidraria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo", "Volume", "Certificado", "Entrada", "Baixa", "Setor", "Obs"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVidraria.setSurrendersFocusOnKeystroke(true);
        tblVidraria.getTableHeader().setReorderingAllowed(false);
        tblVidraria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVidrariaMouseClicked(evt);
            }
        });
        tblVidraria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblVidrariaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblVidraria);
        if (tblVidraria.getColumnModel().getColumnCount() > 0) {
            tblVidraria.getColumnModel().getColumn(0).setMinWidth(50);
            tblVidraria.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblVidraria.getColumnModel().getColumn(0).setMaxWidth(50);
            tblVidraria.getColumnModel().getColumn(1).setMinWidth(150);
            tblVidraria.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblVidraria.getColumnModel().getColumn(1).setMaxWidth(150);
            tblVidraria.getColumnModel().getColumn(2).setMinWidth(80);
            tblVidraria.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblVidraria.getColumnModel().getColumn(2).setMaxWidth(80);
            tblVidraria.getColumnModel().getColumn(3).setMinWidth(100);
            tblVidraria.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblVidraria.getColumnModel().getColumn(3).setMaxWidth(100);
            tblVidraria.getColumnModel().getColumn(4).setMinWidth(100);
            tblVidraria.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblVidraria.getColumnModel().getColumn(4).setMaxWidth(100);
            tblVidraria.getColumnModel().getColumn(5).setMinWidth(100);
            tblVidraria.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblVidraria.getColumnModel().getColumn(5).setMaxWidth(100);
            tblVidraria.getColumnModel().getColumn(6).setMinWidth(80);
            tblVidraria.getColumnModel().getColumn(6).setPreferredWidth(80);
            tblVidraria.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        txtPesquisarVidraria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarVidrariaKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar:");

        jLabel11.setText("Visualizar:");

        cmbLimitPesquisarVidraria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100", "1000", "10000" }));
        cmbLimitPesquisarVidraria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbLimitPesquisarVidrariaItemStateChanged(evt);
            }
        });

        jLabel13.setText("Status:");

        cmbPesquisarStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Ativos", "Inativos" }));
        cmbPesquisarStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPesquisarStatusItemStateChanged(evt);
            }
        });

        jLabel25.setText("Setor:");

        cmbPesquisarSetor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPesquisarSetorItemStateChanged(evt);
            }
        });

        btnImprimirRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print.png"))); // NOI18N
        btnImprimirRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlVidrariasLayout = new javax.swing.GroupLayout(pnlVidrarias);
        pnlVidrarias.setLayout(pnlVidrariasLayout);
        pnlVidrariasLayout.setHorizontalGroup(
            pnlVidrariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVidrariasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVidrariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVidrariasLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarVidraria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPesquisarSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPesquisarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLimitPesquisarVidraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimirRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlVidrariasLayout.setVerticalGroup(
            pnlVidrariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVidrariasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVidrariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimirRelatorio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlVidrariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPesquisarVidraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cmbLimitPesquisarVidraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(cmbPesquisarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(cmbPesquisarSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlVidraria.addTab("Vidrarias", pnlVidrarias);

        pnlDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Vidraria"));

        jLabel2.setText("Volume:");

        jLabel7.setText("Id:");

        jLabel3.setText("Obs:");

        jLabel22.setText("Tipo:");

        jLabel23.setText("Setor:");

        jLabel24.setText("Entrada:");

        jLabel1.setText("Certificado:");

        jLabel5.setText("Unidade:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel22))
                        .addGap(4, 4, 4)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbVidrariaTipo, 0, 234, Short.MAX_VALUE)
                            .addComponent(txtVolume))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEntradaVidraria, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtObsVidraria, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cmbVidrariaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(cmbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtObsVidraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtEntradaVidraria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        btnBaixaVidraria.setText("Baixa");
        btnBaixaVidraria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaixaVidrariaActionPerformed(evt);
            }
        });

        btnSalvarVidraria.setText("Ok");
        btnSalvarVidraria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarVidrariaActionPerformed(evt);
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
                        .addComponent(btnBaixaVidraria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarVidraria, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvarVidraria)
                    .addComponent(btnBaixaVidraria))
                .addContainerGap())
        );

        pnlVidraria.addTab("Dados", pnlDados);

        mnuArquivo.setText("Arquivo");

        mnuNovoVidraria.setText("Novo");

        itmNovoVidraria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_INSERT, java.awt.event.InputEvent.ALT_MASK));
        itmNovoVidraria.setText("Vidraria");
        itmNovoVidraria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoVidrariaActionPerformed(evt);
            }
        });
        mnuNovoVidraria.add(itmNovoVidraria);

        mnuArquivo.add(mnuNovoVidraria);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuArquivo.add(mnuSair);

        jMenuBar1.add(mnuArquivo);

        mnuEditarVidraria.setText("Editar");

        itmEditarVidraria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        itmEditarVidraria.setText("Editar");
        itmEditarVidraria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmEditarVidrariaActionPerformed(evt);
            }
        });
        mnuEditarVidraria.add(itmEditarVidraria);

        itmBaixaVidraria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.ALT_MASK));
        itmBaixaVidraria.setText("Baixa");
        itmBaixaVidraria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmBaixaVidrariaActionPerformed(evt);
            }
        });
        mnuEditarVidraria.add(itmBaixaVidraria);

        jMenuBar1.add(mnuEditarVidraria);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVidraria, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVidraria, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblVidrariaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVidrariaMouseClicked
        if (evt.getClickCount() == 2) {
            novoVidraria = false;
            carregarDados((Integer) tblVidraria.getValueAt(tblVidraria.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_tblVidrariaMouseClicked

    private void txtPesquisarVidrariaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarVidrariaKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblVidraria.getModel();
        sorter = new TableRowSorter<>(model);
        tblVidraria.setRowSorter(sorter);
        String text = txtPesquisarVidraria.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarVidrariaKeyReleased

    private void btnBaixaVidrariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaixaVidrariaActionPerformed
        baixaVidraria((Integer) tblVidraria.getValueAt(tblVidraria.getSelectedRow(), 0));
    }//GEN-LAST:event_btnBaixaVidrariaActionPerformed

    private void btnSalvarVidrariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarVidrariaActionPerformed
        if (cmbVidrariaTipo.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tipo de Vidraria inválido");
            cmbVidrariaTipo.requestFocus();
        } else if (((JTextField) txtEntradaVidraria.getDateEditor().getUiComponent()).getText().isEmpty()
                || txtEntradaVidraria.getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Inválida");
        } else if (txtVolume.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Volume da vidraria inválido");
            txtVolume.requestFocus();
        } else if (cmbUnidade.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Unidade inválida");
            cmbUnidade.requestFocus();
        } else if (cmbSetor.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Setor inválido");
            cmbSetor.requestFocus();
        } else if (novoVidraria) {
            criarVidraria();
        } else {
            atulizarVidraria((Integer) tblVidraria.getValueAt(tblVidraria.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_btnSalvarVidrariaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmEditarVidrariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmEditarVidrariaActionPerformed
        if (tblVidraria.getSelectedRow() != -1) {
            novoVidraria = false;
            carregarDados((Integer) tblVidraria.getValueAt(tblVidraria.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_itmEditarVidrariaActionPerformed

    private void itmBaixaVidrariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmBaixaVidrariaActionPerformed
        if (tblVidraria.getSelectedRow() != -1) {
            baixaVidraria((Integer) tblVidraria.getValueAt(tblVidraria.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_itmBaixaVidrariaActionPerformed

    private void itmNovoVidrariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoVidrariaActionPerformed
        abrirDados();
    }//GEN-LAST:event_itmNovoVidrariaActionPerformed

    private void tblVidrariaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVidrariaKeyReleased
        if (tblVidraria.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                baixaVidraria((Integer) tblVidraria.getValueAt(tblVidraria.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblVidrariaKeyReleased

    private void cmbPesquisarSetorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPesquisarSetorItemStateChanged
        readVidrarias();
    }//GEN-LAST:event_cmbPesquisarSetorItemStateChanged

    private void cmbLimitPesquisarVidrariaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbLimitPesquisarVidrariaItemStateChanged
        readVidrarias();
    }//GEN-LAST:event_cmbLimitPesquisarVidrariaItemStateChanged

    private void cmbPesquisarStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPesquisarStatusItemStateChanged
        readVidrarias();
    }//GEN-LAST:event_cmbPesquisarStatusItemStateChanged

    private void btnImprimirRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirRelatorioActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Deseja Imprimir Relatorio de Vidrarias?\nSetor: "
                + ("".equals(cmbPesquisarSetor.getSelectedItem().toString()) ? "Todos" : cmbPesquisarSetor.getSelectedItem().toString())
                + "\nStatus: " + cmbPesquisarStatus.getSelectedItem().toString(), "Aviso", JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0) {
            VidrariaDAO.printVidraria(cmbPesquisarSetor.getSelectedItem().toString(), (Integer) cmbPesquisarStatus.getSelectedIndex());
        }
    }//GEN-LAST:event_btnImprimirRelatorioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnBaixaVidraria;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImprimirRelatorio;
    public static javax.swing.JButton btnSalvarVidraria;
    private javax.swing.JComboBox<String> cmbLimitPesquisarVidraria;
    private javax.swing.JComboBox cmbPesquisarSetor;
    private javax.swing.JComboBox<String> cmbPesquisarStatus;
    private javax.swing.JComboBox cmbSetor;
    private javax.swing.JComboBox cmbUnidade;
    private javax.swing.JComboBox cmbVidrariaTipo;
    public static javax.swing.JMenuItem itmBaixaVidraria;
    public static javax.swing.JMenuItem itmEditarVidraria;
    public static javax.swing.JMenuItem itmNovoVidraria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    public static javax.swing.JMenu mnuEditarVidraria;
    public static javax.swing.JMenu mnuNovoVidraria;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JTabbedPane pnlVidraria;
    private javax.swing.JPanel pnlVidrarias;
    private javax.swing.JTable tblVidraria;
    private javax.swing.JTextField txtCertificado;
    private com.toedter.calendar.JDateChooser txtEntradaVidraria;
    private javax.swing.JTextField txtObsVidraria;
    private javax.swing.JTextField txtPesquisarVidraria;
    private javax.swing.JTextField txtVolume;
    // End of variables declaration//GEN-END:variables
}
