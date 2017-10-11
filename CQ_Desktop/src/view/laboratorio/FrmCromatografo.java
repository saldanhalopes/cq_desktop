/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.laboratorio;

import DAO.CromatografoDAO;
import DAO.SetorDAO;
import acesso.CromatografoAcesso;
import model.Cromatografo;
import model.Setor;
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
 * @author rafael
 */
public class FrmCromatografo extends javax.swing.JInternalFrame {

    private boolean novoEquipamento = false;
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
     * Creates new form NovoJInternalFrame
     */
    public FrmCromatografo() {
        initComponents();
        setTitle("Cromatógrafos");
        readEquipamentos();
        tblPaneEquipamentos.setEnabledAt(1, false);
        CromatografoAcesso Acesso = new CromatografoAcesso();
        Acesso.verificarAcessoCromatografo();
    }

    public final void readEquipamentos() {
        DefaultTableModel model = (DefaultTableModel) tblEquipamentos.getModel();
        CromatografoDAO equipDao = new CromatografoDAO();
        model.setNumRows(0);
        for (Cromatografo equip : equipDao.read()) {
            model.addRow(new Object[]{
                equip.getCromatografo_id(),
                equip.getSystem_name(),
                equip.getNode(),
                equip.getTipo(),
                equip.getSetor().getSigla_setor()
            });
        }
    }

    public final void readSetor() {
        cmbSetor.removeAllItems();
        cmbSetor.addItem("");
        categoryMap.clear();
        SetorDAO setorDao = new SetorDAO();
        for (Setor setor : setorDao.readSetor()) {
            Integer id = setor.getSetor_id();
            String name = setor.getSigla_setor();
            cmbSetor.addItem(name);
            categoryMap.put(id, name);
        }
    }

    private void limparCampos() {
        txtSystemName.setText(null);
        txtModelo.setText(null);
        txtNode.setText(null);
        txtSystemName.setText(null);
        txtModelo.setText(null);
        txtTipo.setText(null);
        txtControladora.setText(null);
        txtDegaseificador.setText(null);
        txtBomba.setText(null);
        txtInjetor.setText(null);
        txtForno.setText(null);
        txtDetector.setText(null);
        lblId.setText(null);
    }

    private void criarEquipamento() {
        Cromatografo equip = new Cromatografo();
        CromatografoDAO equipDAO = new CromatografoDAO();
        try {
            equip.setSystem_name(txtSystemName.getText().toUpperCase());
            equip.setModelo(txtModelo.getText());
            equip.setTipo(txtTipo.getText());
            equip.setNode(txtNode.getText());
            Setor setor = new Setor();
            setor.setSetor_id(getKeyForValue(cmbSetor.getSelectedItem().toString()));
            equip.setSetor(setor);
            equip.setControladora(txtControladora.getText());
            equip.setBomba(txtBomba.getText());
            equip.setDegaseificador(txtDegaseificador.getText());
            equip.setDetector(txtDetector.getText());
            equip.setForno(txtForno.getText());
            equip.setInjetor(txtInjetor.getText());
            equipDAO.createEquipamento(equip);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void fecharDados() {
        tblPaneEquipamentos.setEnabledAt(0, true);
        tblPaneEquipamentos.setEnabledAt(1, false);
        tblPaneEquipamentos.setSelectedIndex(0);
        txtPesquisarEquipamentos.setText(null);
        tblEquipamentos.setRowSorter(null);
        CromatografoAcesso Acesso = new CromatografoAcesso();
        Acesso.verificarAcessoCromatografo();
        limparCampos();
        readEquipamentos();
    }

    private void abrirDados() {
        CromatografoAcesso Acesso = new CromatografoAcesso();
        if (Acesso.verificarAbrirDadosCromatografo()) {
            tblPaneEquipamentos.setEnabledAt(0, false);
            tblPaneEquipamentos.setEnabledAt(1, true);
            tblPaneEquipamentos.setSelectedIndex(1);
            mnuNovoEquipamento.setEnabled(false);
            btnExcluirEquipamento.setVisible(false);
            limparCampos();
            novoEquipamento = true;
            readSetor();
        }
    }

    private void carregarDados(int id) {
        CromatografoAcesso Acesso = new CromatografoAcesso();
        if (Acesso.verificarCarregarDadosCromatografo()) {
            readSetor();
            Cromatografo equip = new Cromatografo();
            equip.setCromatografo_id(id);
            CromatografoDAO equipDao = new CromatografoDAO();
            equipDao.selectEquipamento(equip);
            lblId.setText(Integer.toString(id));
            txtSystemName.setText(equip.getSystem_name());
            txtModelo.setText(equip.getModelo());
            txtTipo.setText(equip.getTipo());
            txtNode.setText(equip.getNode());
            String[] setor = {equip.getSetor().getSigla_setor()};
            cmbSetor.setSelectedItem(setor[0]);
            txtControladora.setText(equip.getControladora());
            txtBomba.setText(equip.getBomba());
            txtDegaseificador.setText(equip.getDegaseificador());
            txtDetector.setText(equip.getDetector());
            txtForno.setText(equip.getForno());
            txtInjetor.setText(equip.getInjetor());
            tblPaneEquipamentos.setEnabledAt(0, false);
            tblPaneEquipamentos.setEnabledAt(1, true);
            tblPaneEquipamentos.setSelectedIndex(1);
            btnExcluirEquipamento.setVisible(true);
            mnuEditEquipamento.setEnabled(false);
        }
    }

    private void atulizarEquipamento() {
        Cromatografo equip = new Cromatografo();
        CromatografoDAO equipDAO = new CromatografoDAO();
        try {
            equip.setCromatografo_id(Integer.parseInt(lblId.getText()));
            equip.setSystem_name(txtSystemName.getText().toUpperCase());
            equip.setModelo(txtModelo.getText());
            equip.setTipo(txtTipo.getText());
            equip.setNode(txtNode.getText());
            Setor setor = new Setor();
            setor.setSetor_id(getKeyForValue(cmbSetor.getSelectedItem().toString()));
            equip.setSetor(setor);
            equip.setControladora(txtControladora.getText());
            equip.setBomba(txtBomba.getText());
            equip.setDegaseificador(txtDegaseificador.getText());
            equip.setDetector(txtDetector.getText());
            equip.setForno(txtForno.getText());
            equip.setInjetor(txtInjetor.getText());
            equipDAO.updateEquipamento(equip);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void deletarDados(int id) {
        CromatografoAcesso Acesso = new CromatografoAcesso();
        if (Acesso.verificarDeletarDadosCromatografo()) {
            Cromatografo equip = new Cromatografo();
            CromatografoDAO equipDAO = new CromatografoDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente Deseja Excluir Esse Produto?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    equip.setCromatografo_id(id);
                    equipDAO.deleteEquipamento(equip);
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

        tblPaneEquipamentos = new javax.swing.JTabbedPane();
        pnlUsuarios = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEquipamentos = new javax.swing.JTable();
        txtPesquisarEquipamentos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnlDados = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtSystemName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cmbSetor = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtNode = new javax.swing.JTextField();
        btnExcluirEquipamento = new javax.swing.JButton();
        btnSalvarEquipamento = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtControladora = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDegaseificador = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBomba = new javax.swing.JTextField();
        txtInjetor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtForno = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtDetector = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoEquipamento = new javax.swing.JMenu();
        itmNovoEquipamento = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        mnuEditEquipamento = new javax.swing.JMenu();
        itmEditarEquipamento = new javax.swing.JMenuItem();
        itmExcluirEquipamento = new javax.swing.JMenuItem();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamentos"));

        tblEquipamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tag", "LAC/E", "Tipo", "Setor"
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
        tblEquipamentos.setSurrendersFocusOnKeystroke(true);
        tblEquipamentos.getTableHeader().setReorderingAllowed(false);
        tblEquipamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEquipamentosMouseClicked(evt);
            }
        });
        tblEquipamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEquipamentosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblEquipamentos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        txtPesquisarEquipamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarEquipamentosKeyReleased(evt);
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
                        .addComponent(txtPesquisarEquipamentos))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPaneEquipamentos.addTab("Equipamentos", pnlUsuarios);

        pnlDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamento"));

        jLabel1.setText("Tag:");

        jLabel2.setText("Modelo:");

        jLabel7.setText("Id:");

        jLabel3.setText("Tipo:");

        jLabel22.setText("Setor:");

        jLabel5.setText("LAC/E:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(lblId)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNode)
                                    .addComponent(txtSystemName, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModelo)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
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
                    .addComponent(txtSystemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cmbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirEquipamento.setText("Excuir");
        btnExcluirEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirEquipamentoActionPerformed(evt);
            }
        });

        btnSalvarEquipamento.setText("Ok");
        btnSalvarEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarEquipamentoActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuração"));

        jLabel6.setText("Controladora:");

        jLabel11.setText("Degaseificador:");

        jLabel12.setText("Bomba:");

        jLabel16.setText("Injetor:");

        jLabel19.setText("Forno:");

        jLabel21.setText("Detector:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtControladora, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtDegaseificador)
                    .addComponent(txtBomba))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtForno)
                    .addComponent(txtDetector)
                    .addComponent(txtInjetor))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInjetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtForno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDetector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtControladora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDegaseificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBomba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDadosLayout = new javax.swing.GroupLayout(pnlDados);
        pnlDados.setLayout(pnlDadosLayout);
        pnlDadosLayout.setHorizontalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(btnExcluirEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvarEquipamento)
                    .addComponent(btnExcluirEquipamento))
                .addContainerGap())
        );

        tblPaneEquipamentos.addTab("Dados", pnlDados);

        mnuArquivo.setText("Arquivo");

        mnuNovoEquipamento.setText("Novo");

        itmNovoEquipamento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_INSERT, java.awt.event.InputEvent.ALT_MASK));
        itmNovoEquipamento.setText("Produto");
        itmNovoEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoEquipamentoActionPerformed(evt);
            }
        });
        mnuNovoEquipamento.add(itmNovoEquipamento);

        mnuArquivo.add(mnuNovoEquipamento);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuArquivo.add(mnuSair);

        jMenuBar1.add(mnuArquivo);

        mnuEditEquipamento.setText("Editar");

        itmEditarEquipamento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        itmEditarEquipamento.setText("Editar");
        itmEditarEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmEditarEquipamentoActionPerformed(evt);
            }
        });
        mnuEditEquipamento.add(itmEditarEquipamento);

        itmExcluirEquipamento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.ALT_MASK));
        itmExcluirEquipamento.setText("Excluir");
        itmExcluirEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmExcluirEquipamentoActionPerformed(evt);
            }
        });
        mnuEditEquipamento.add(itmExcluirEquipamento);

        jMenuBar1.add(mnuEditEquipamento);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneEquipamentos, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneEquipamentos, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblEquipamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEquipamentosMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDados((Integer) tblEquipamentos.getValueAt(tblEquipamentos.getSelectedRow(), 0));
            novoEquipamento = false;
        }
    }//GEN-LAST:event_tblEquipamentosMouseClicked

    private void txtPesquisarEquipamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarEquipamentosKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblEquipamentos.getModel();
        sorter = new TableRowSorter<>(model);
        tblEquipamentos.setRowSorter(sorter);
        String text = txtPesquisarEquipamentos.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarEquipamentosKeyReleased

    private void btnExcluirEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirEquipamentoActionPerformed
        deletarDados((Integer) tblEquipamentos.getValueAt(tblEquipamentos.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirEquipamentoActionPerformed

    private void btnSalvarEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarEquipamentoActionPerformed
        if (txtSystemName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tag inválida");
            txtSystemName.requestFocus();
        } else if (txtModelo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Modelo inválido");
            txtModelo.requestFocus();
        } else if (txtTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tipo inválido");
            txtTipo.requestFocus();
        } else if (txtNode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "LAC/E inválida");
            txtNode.requestFocus();
        } else if (cmbSetor.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Setor inválido");
            txtNode.requestFocus();
        } else if (novoEquipamento) {
            criarEquipamento();
        } else {
            atulizarEquipamento();
        }
    }//GEN-LAST:event_btnSalvarEquipamentoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmEditarEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmEditarEquipamentoActionPerformed
        if (tblEquipamentos.getSelectedRow() != -1) {
            carregarDados((Integer) tblEquipamentos.getValueAt(tblEquipamentos.getSelectedRow(), 0));
            novoEquipamento = false;
        }
    }//GEN-LAST:event_itmEditarEquipamentoActionPerformed

    private void itmExcluirEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmExcluirEquipamentoActionPerformed
        if (tblEquipamentos.getSelectedRow() != -1) {
            deletarDados((Integer) tblEquipamentos.getValueAt(tblEquipamentos.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_itmExcluirEquipamentoActionPerformed

    private void itmNovoEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoEquipamentoActionPerformed
        abrirDados();
    }//GEN-LAST:event_itmNovoEquipamentoActionPerformed

    private void tblEquipamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEquipamentosKeyReleased
        if (tblEquipamentos.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarDados((Integer) tblEquipamentos.getValueAt(tblEquipamentos.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblEquipamentosKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnExcluirEquipamento;
    public static javax.swing.JButton btnSalvarEquipamento;
    private javax.swing.JComboBox cmbSetor;
    public static javax.swing.JMenuItem itmEditarEquipamento;
    public static javax.swing.JMenuItem itmExcluirEquipamento;
    public static javax.swing.JMenuItem itmNovoEquipamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblId;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuEditEquipamento;
    public static javax.swing.JMenu mnuNovoEquipamento;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JTable tblEquipamentos;
    private javax.swing.JTabbedPane tblPaneEquipamentos;
    private javax.swing.JTextField txtBomba;
    private javax.swing.JTextField txtControladora;
    private javax.swing.JTextField txtDegaseificador;
    private javax.swing.JTextField txtDetector;
    private javax.swing.JTextField txtForno;
    private javax.swing.JTextField txtInjetor;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNode;
    private javax.swing.JTextField txtPesquisarEquipamentos;
    private javax.swing.JTextField txtSystemName;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
