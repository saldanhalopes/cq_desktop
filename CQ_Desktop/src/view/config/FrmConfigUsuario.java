/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.config;

import DAO.UsuarioDAO;
import model.Usuario;
import util.CriptMD5;
import util.Validate;
import java.awt.HeadlessException;
import java.util.Arrays;
import java.util.Locale;
import java.util.TreeMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael.lopes
 */
public class FrmConfigUsuario extends javax.swing.JInternalFrame {

    private boolean novoUsuario = false;

    /**
     * Creates new form frmConfigGrupo
     */
    public FrmConfigUsuario() {
        initComponents();
        setTitle("Usuários");
        readUsuarios();
        tblPaneUsuarios.setEnabledAt(1, false);
    }

    public final void readUsuarios() {
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        UsuarioDAO userDao = new UsuarioDAO();
        model.setNumRows(0);
        for (Usuario u : userDao.read()) {
            model.addRow(new Object[]{
                u.getId(),
                u.getUser(),
                u.getName(),
                u.isLock()
            });
        }
    }

    private void limparCampos() {
        txtUsuario.setText(null);
        txtNome.setText(null);
        txtEmail.setText(null);
        txtCracha.setText(null);
        txtNovaSenha.setText(null);
        txtConfirmaSenha.setText(null);
        cmbStatus.setSelectedIndex(0);
        cmbAcesso.setSelectedIndex(0);
        ckbAlterarSenha.setSelected(false);
        ckbAcessoCriar.setSelected(false);
        ckbAcessoEditar.setSelected(false);
        ckbAcessoDeletar.setSelected(false);
        ckbAcessoAdmin.setSelected(false);
        ckbAcessoCriar.setSelected(false);
    }

    private void criarUsuario() {
        Usuario u = new Usuario();
        UsuarioDAO userDAO = new UsuarioDAO();

        try {
            u.setUser(txtUsuario.getText().toLowerCase());
            u.setPass(("").equals(Arrays.toString(txtNovaSenha.getPassword()))
                    ? CriptMD5.md5(new String(txtNovaSenha.getPassword()))
                    : CriptMD5.md5("123456"));
            u.setName(txtNome.getText().toUpperCase(Locale.ENGLISH));
            u.setEmail(txtEmail.getText().toLowerCase());
            u.setLock((Integer) cmbStatus.getSelectedIndex() == 1);
            u.setAcesso(cmbAcesso.getSelectedItem().toString());
            u.setCracha(txtCracha.getText());
            if (ckbAlterarSenha.isSelected()) {
                u.setChange_pass(1);
            } else {
                u.setChange_pass(0);
            }
            userDAO.createUser(u);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void fecharDados() {
        tblPaneUsuarios.setEnabledAt(0, true);
        tblPaneUsuarios.setEnabledAt(1, false);
        tblPaneUsuarios.setSelectedIndex(0);
        txtPesquisarUsuario.setText(null);
        tblUsuarios.setRowSorter(null);
        limparCampos();
        readUsuarios();
    }

    private void abrirDados() {
        tblPaneUsuarios.setEnabledAt(0, false);
        tblPaneUsuarios.setEnabledAt(1, true);
        tblPaneUsuarios.setSelectedIndex(1);
        txtUsuario.setEditable(true);
        mnuNovo.setEnabled(false);
        btnExcluir.setVisible(false);
        limparCampos();
        novoUsuario = true;
        ckbAlterarSenha.setSelected(true);
    }

    private void carregarDados() {
        Usuario u = new Usuario();
        u.setId((Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
        UsuarioDAO userDao = new UsuarioDAO();
        userDao.selectUser(u);
        txtUsuario.setText(u.getUser().toLowerCase());
        txtNome.setText(u.getName());
        txtEmail.setText(u.getEmail());
        cmbStatus.setSelectedIndex(u.isLock() ? 1 : 0);
        txtCracha.setText(u.getCracha());
        switch (u.getAcesso()) {
            case "0":
                cmbAcesso.setSelectedIndex(0);
                ckbAcessoLer.setSelected(true);
                ckbAcessoCriar.setSelected(false);
                ckbAcessoEditar.setSelected(false);
                ckbAcessoDeletar.setSelected(false);
                ckbAcessoAdmin.setSelected(false);
                break;
            case "Ler":
                cmbAcesso.setSelectedIndex(0);
                ckbAcessoLer.setSelected(true);
                ckbAcessoCriar.setSelected(false);
                ckbAcessoEditar.setSelected(false);
                ckbAcessoDeletar.setSelected(false);
                ckbAcessoAdmin.setSelected(false);
                break;
            case "Criar":
                cmbAcesso.setSelectedIndex(1);
                ckbAcessoLer.setSelected(true);
                ckbAcessoCriar.setSelected(true);
                ckbAcessoEditar.setSelected(false);
                ckbAcessoDeletar.setSelected(false);
                ckbAcessoAdmin.setSelected(false);
                break;
            case "Editar":
                cmbAcesso.setSelectedIndex(2);
                ckbAcessoLer.setSelected(true);
                ckbAcessoCriar.setSelected(true);
                ckbAcessoEditar.setSelected(true);
                ckbAcessoDeletar.setSelected(false);
                ckbAcessoAdmin.setSelected(false);
                break;
            case "Excluir":
                cmbAcesso.setSelectedIndex(3);
                ckbAcessoLer.setSelected(true);
                ckbAcessoCriar.setSelected(true);
                ckbAcessoEditar.setSelected(true);
                ckbAcessoDeletar.setSelected(true);
                ckbAcessoAdmin.setSelected(false);
                break;
            case "Admin":
                cmbAcesso.setSelectedIndex(4);
                ckbAcessoLer.setSelected(true);
                ckbAcessoCriar.setSelected(true);
                ckbAcessoEditar.setSelected(true);
                ckbAcessoDeletar.setSelected(true);
                ckbAcessoAdmin.setSelected(true);
                break;
        }
        tblPaneUsuarios.setEnabledAt(0, false);
        tblPaneUsuarios.setEnabledAt(1, true);
        tblPaneUsuarios.setSelectedIndex(1);
        btnExcluir.setVisible(true);
    }

    private void atulizarUsuario() {
        Usuario u = new Usuario();
        UsuarioDAO userDAO = new UsuarioDAO();
        try {
            u.setId((Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
            u.setUser(txtUsuario.getText().toLowerCase());
            u.setName(txtNome.getText().toUpperCase());
            u.setEmail(txtEmail.getText());
            u.setLock((Integer) cmbStatus.getSelectedIndex() == 1);
            u.setAcesso(cmbAcesso.getSelectedItem().toString());
            u.setCracha(txtCracha.getText());
            if (ckbAlterarSenha.isSelected()) {
                u.setChange_pass(1);
            } else {
                u.setChange_pass(0);
            }
            if (!new String(txtNovaSenha.getPassword()).trim().isEmpty()) {
                if (!new String(txtNovaSenha.getPassword()).equals(new String(txtConfirmaSenha.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Senhas não conferem: ");
                    txtNovaSenha.setText(null);
                    txtConfirmaSenha.setText(null);
                } else {
                    u.setPass(CriptMD5.md5(new String(txtNovaSenha.getPassword())));
                    userDAO.updateUserPassword(u);
                    userDAO.updateUser(u);
                    fecharDados();
                }
            } else {
                userDAO.updateUser(u);
                fecharDados();
            }
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        }
    }

    private void deletarUsuario() {
        Usuario u = new Usuario();
        UsuarioDAO userDAO = new UsuarioDAO();
        try {
            u.setId((Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
            userDAO.deleteUser(u);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
        } finally {
            fecharDados();
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuNovo = new javax.swing.JMenu();
        mnuNovoUsuario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        mnuNovo1 = new javax.swing.JMenu();
        mnuNovoUsuario1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        tblPaneUsuarios = new javax.swing.JTabbedPane();
        pnlUsuarios = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtPesquisarUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnlDados = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ckbAlterarSenha = new javax.swing.JCheckBox();
        txtNovaSenha = new javax.swing.JPasswordField();
        txtConfirmaSenha = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        cmbStatus = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbAcesso = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        ckbAcessoCriar = new javax.swing.JCheckBox();
        ckbAcessoEditar = new javax.swing.JCheckBox();
        ckbAcessoDeletar = new javax.swing.JCheckBox();
        ckbAcessoAdmin = new javax.swing.JCheckBox();
        ckbAcessoLer = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCracha = new javax.swing.JFormattedTextField();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        mnuNovo2 = new javax.swing.JMenu();
        mnuNovoUsuario2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();

        jMenu1.setText("Arquivo");

        mnuNovo.setText("Novo");

        mnuNovoUsuario.setText("Usuário");
        mnuNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNovoUsuarioActionPerformed(evt);
            }
        });
        mnuNovo.add(mnuNovoUsuario);

        jMenu1.add(mnuNovo);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Arquivo");

        mnuNovo1.setText("Novo");

        mnuNovoUsuario1.setText("Usuário");
        mnuNovoUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNovoUsuario1ActionPerformed(evt);
            }
        });
        mnuNovo1.add(mnuNovoUsuario1);

        jMenu3.add(mnuNovo1);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Editar");
        jMenuBar2.add(jMenu4);

        setClosable(true);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuários"));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Usuário", "Nome Completo", "Bloqueado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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
        tblUsuarios.setSurrendersFocusOnKeystroke(true);
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);
        if (tblUsuarios.getColumnModel().getColumnCount() > 0) {
            tblUsuarios.getColumnModel().getColumn(0).setMinWidth(50);
            tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblUsuarios.getColumnModel().getColumn(0).setMaxWidth(50);
            tblUsuarios.getColumnModel().getColumn(1).setMinWidth(200);
            tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblUsuarios.getColumnModel().getColumn(1).setMaxWidth(200);
            tblUsuarios.getColumnModel().getColumn(3).setMinWidth(80);
            tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblUsuarios.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );

        txtPesquisarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarUsuarioKeyReleased(evt);
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
                        .addComponent(txtPesquisarUsuario))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPaneUsuarios.addTab("Usuários", pnlUsuarios);

        pnlDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Senha"));

        jLabel5.setText("Nova Senha:");

        jLabel6.setText("Confirmar Nova Senha:");

        ckbAlterarSenha.setText("Alterar senha no próximo login");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmaSenha)
                            .addComponent(txtNovaSenha)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ckbAlterarSenha)
                        .addGap(0, 130, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbAlterarSenha))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ativo", "Desbilitado" }));
        cmbStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel8.setText("Status:");

        jLabel9.setText("Tipo de Acesso:");

        cmbAcesso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ler", "Criar", "Editar", "Excluir", "Admin" }));

        jLabel12.setText("Permissões:");

        ckbAcessoCriar.setText("Criar");
        ckbAcessoCriar.setEnabled(false);

        ckbAcessoEditar.setText("Editar");
        ckbAcessoEditar.setEnabled(false);

        ckbAcessoDeletar.setText("Deletar");
        ckbAcessoDeletar.setEnabled(false);

        ckbAcessoAdmin.setText("Admin");
        ckbAcessoAdmin.setEnabled(false);

        ckbAcessoLer.setText("Ler");
        ckbAcessoLer.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStatus, 0, 157, Short.MAX_VALUE)
                            .addComponent(cmbAcesso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbAcessoCriar)
                            .addComponent(ckbAcessoEditar)
                            .addComponent(ckbAcessoDeletar)
                            .addComponent(ckbAcessoAdmin)
                            .addComponent(ckbAcessoLer))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbAcessoLer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbAcessoCriar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbAcessoEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbAcessoDeletar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbAcessoAdmin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUsuario.setEditable(false);

        jLabel1.setText("Usuário:");

        jLabel2.setText("Nome Completo:");

        jLabel3.setText("E-mail:");

        jLabel10.setText("Crachá:");

        try {
            txtCracha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(txtEmail)
                    .addComponent(txtUsuario)
                    .addComponent(txtCracha))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCracha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnExcluir.setText("Excuir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setText("Ok");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
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
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDadosLayout.createSequentialGroup()
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDadosLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir))
                .addContainerGap())
        );

        tblPaneUsuarios.addTab("Dados", pnlDados);

        jMenu5.setText("Arquivo");

        mnuNovo2.setText("Novo");

        mnuNovoUsuario2.setText("Usuário");
        mnuNovoUsuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNovoUsuario2ActionPerformed(evt);
            }
        });
        mnuNovo2.add(mnuNovoUsuario2);

        jMenu5.add(mnuNovo2);

        jMenuBar3.add(jMenu5);

        jMenu6.setText("Editar");
        jMenuBar3.add(jMenu6);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneUsuarios)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDados();
            novoUsuario = false;
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void txtPesquisarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarUsuarioKeyReleased
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        sorter = new TableRowSorter<>(model);
        tblUsuarios.setRowSorter(sorter);
        String text = txtPesquisarUsuario.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarUsuarioKeyReleased

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                "Realmente Deseja Excluir Esse Usuário?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0) {
            deletarUsuario();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Validate val = new Validate();
        if (txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuário inválido");
            txtUsuario.requestFocus();
        } else if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido");
            txtNome.requestFocus();
        } else if (!val.isEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(null, "Email inválido");
            txtEmail.requestFocus();
        } else if (txtCracha.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Crachá inválido");
            txtCracha.requestFocus();
        } else if (!new String(txtNovaSenha.getPassword()).equals(new String(txtConfirmaSenha.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Senhas não conferem: ");
            txtNovaSenha.setText(null);
            txtConfirmaSenha.setText(null);
            txtNovaSenha.requestFocus();
        } else if (novoUsuario) {
            criarUsuario();
        } else {
            atulizarUsuario();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        tblPaneUsuarios.setEnabledAt(0, true);
        tblPaneUsuarios.setEnabledAt(1, false);
        tblPaneUsuarios.setSelectedIndex(0);
        limparCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void mnuNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNovoUsuarioActionPerformed
        abrirDados();
    }//GEN-LAST:event_mnuNovoUsuarioActionPerformed

    private void mnuNovoUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNovoUsuario1ActionPerformed
        abrirDados();
    }//GEN-LAST:event_mnuNovoUsuario1ActionPerformed

    private void mnuNovoUsuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNovoUsuario2ActionPerformed
        abrirDados();
    }//GEN-LAST:event_mnuNovoUsuario2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox ckbAcessoAdmin;
    private javax.swing.JCheckBox ckbAcessoCriar;
    private javax.swing.JCheckBox ckbAcessoDeletar;
    private javax.swing.JCheckBox ckbAcessoEditar;
    private javax.swing.JCheckBox ckbAcessoLer;
    private javax.swing.JCheckBox ckbAlterarSenha;
    private javax.swing.JComboBox cmbAcesso;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mnuNovo;
    private javax.swing.JMenu mnuNovo1;
    private javax.swing.JMenu mnuNovo2;
    private javax.swing.JMenuItem mnuNovoUsuario;
    private javax.swing.JMenuItem mnuNovoUsuario1;
    private javax.swing.JMenuItem mnuNovoUsuario2;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JTabbedPane tblPaneUsuarios;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JPasswordField txtConfirmaSenha;
    private javax.swing.JFormattedTextField txtCracha;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtNovaSenha;
    private javax.swing.JTextField txtPesquisarUsuario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
