package view.principal;

import acesso.AcessoPrincipal;
import util.Backup;
import util.DataHora;
import util.Frames;
import view.login.FrmLogin;
import view.recepcao.FrmEntradaAmostra;
import view.recepcao.FrmEntradaProduto;
import view.recepcao.FrmRetiradaProduto;
import view.cadastro.FrmAmostra;
import view.cadastro.FrmAnalise;
import view.cadastro.FrmColunaConfig;
import view.laboratorio.FrmCromatografo;
import view.cadastro.FrmMetodologia;
import view.cadastro.FrmProduto;
import view.cadastro.FrmSetor;
import view.config.FrmConfigUsuario;
import view.laboratorio.FrmColuna;
import view.laboratorio.FrmVidraria;
import view.programacao.FrmPrograCromatografo;
import view.cadastro.FrmMetodologiaAnalise;
import view.logbook.FrmAbrirCromatografo;
import java.awt.Cursor;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author rafael.lopes
 */
public class FrmPrincipal extends javax.swing.JFrame {

    FrmConfigUsuario ConfigUsuario;
    FrmProduto Produto;
    FrmCromatografo CadastroEquipamento;
    FrmMetodologia CadastroMetodologia;
    FrmAnalise CadastroAnalise;
    FrmSetor CadastroSetor;
    FrmPrograCromatografo LogBooK;
    FrmEntradaProduto EntradaProduto;
    FrmRetiradaProduto RetiradaProduto;
    FrmAmostra ConfigAmostra;
    FrmEntradaAmostra EntradaAmostra;
    FrmVidraria LaboratorioVidraria;
    FrmColuna LaboratorioColuna;
    FrmMetodologiaAnalise MetodologiaAnalise;
    FrmAbrirCromatografo Equipamento;
    FrmPrograCromatografo PrograCromatografo;
    FrmColunaConfig ColunaConfig;

    public FrmPrincipal() {
        initComponents();
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setAlwaysOnTop(true);
        ImageIcon icone = new ImageIcon(getClass().getResource("/img/png"));
        setIconImage(icone.getImage());
        setTitle("Controle de Qualidade - Modulo CQ_Desktop - " + System.getProperty("user"));
        AcessoPrincipal Acesso = new AcessoPrincipal();
        Acesso.verificarAcessoPrincipal();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuLogout = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        mnuRecepcao = new javax.swing.JMenu();
        mnuEntradaProduto = new javax.swing.JMenu();
        itmMenuEntradaProduto = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnuRetiradaProduto = new javax.swing.JMenu();
        itmMenuRetiradaProduto = new javax.swing.JMenuItem();
        mnuProgramacao = new javax.swing.JMenu();
        mnuPrograCromatografo = new javax.swing.JMenuItem();
        mnuLogBook = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnuCadastro = new javax.swing.JMenu();
        mnuProduto = new javax.swing.JMenuItem();
        mnuMetodologia = new javax.swing.JMenuItem();
        mnuAnalise = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnuSetor = new javax.swing.JMenuItem();
        mnuStatus = new javax.swing.JMenuItem();
        mnuColunaConfig = new javax.swing.JMenuItem();
        mnuCromatografos = new javax.swing.JMenuItem();
        mnuLaboratorio = new javax.swing.JMenu();
        mnuVidrarias = new javax.swing.JMenuItem();
        mnuColunas = new javax.swing.JMenuItem();
        mnuConfig = new javax.swing.JMenu();
        itmMenuUsuarios = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itmBackUp = new javax.swing.JMenuItem();
        mnuJanela = new javax.swing.JMenu();
        mnuCascata = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Arquivo");
        fileMenu.add(jSeparator1);

        mnuLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        mnuLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_logoff.png"))); // NOI18N
        mnuLogout.setMnemonic('L');
        mnuLogout.setText("Logout");
        mnuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLogoutActionPerformed(evt);
            }
        });
        fileMenu.add(mnuLogout);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_sair.png"))); // NOI18N
        exitMenuItem.setMnemonic('S');
        exitMenuItem.setText("Sair");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        mnuRecepcao.setMnemonic('e');
        mnuRecepcao.setText("Recepção");

        mnuEntradaProduto.setText("Entrada");

        itmMenuEntradaProduto.setText("Produto");
        itmMenuEntradaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmMenuEntradaProdutoActionPerformed(evt);
            }
        });
        mnuEntradaProduto.add(itmMenuEntradaProduto);

        jMenuItem3.setText("Amostras");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuEntradaProduto.add(jMenuItem3);

        mnuRecepcao.add(mnuEntradaProduto);

        mnuRetiradaProduto.setText("Retirada");

        itmMenuRetiradaProduto.setText("Produto");
        itmMenuRetiradaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmMenuRetiradaProdutoActionPerformed(evt);
            }
        });
        mnuRetiradaProduto.add(itmMenuRetiradaProduto);

        mnuRecepcao.add(mnuRetiradaProduto);

        menuBar.add(mnuRecepcao);

        mnuProgramacao.setText("Programação");

        mnuPrograCromatografo.setText("Cromatágrafos");
        mnuPrograCromatografo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPrograCromatografoActionPerformed(evt);
            }
        });
        mnuProgramacao.add(mnuPrograCromatografo);

        menuBar.add(mnuProgramacao);

        mnuLogBook.setText("LogBook");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_clipboard.png"))); // NOI18N
        jMenuItem1.setText("Resumo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuLogBook.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_computer.png"))); // NOI18N
        jMenuItem2.setText("Equipamento");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnuLogBook.add(jMenuItem2);

        menuBar.add(mnuLogBook);

        mnuCadastro.setText("Cadastro");

        mnuProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_produto.png"))); // NOI18N
        mnuProduto.setText("Produtos");
        mnuProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProdutoActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuProduto);

        mnuMetodologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_metodologia.png"))); // NOI18N
        mnuMetodologia.setText("Metodologias");
        mnuMetodologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMetodologiaActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuMetodologia);

        mnuAnalise.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_analises.png"))); // NOI18N
        mnuAnalise.setText("Analises");
        mnuAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAnaliseActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuAnalise);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_metodo_analise.png"))); // NOI18N
        jMenuItem4.setText("Analise por Metodologia");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mnuCadastro.add(jMenuItem4);

        mnuSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_setores.png"))); // NOI18N
        mnuSetor.setText("Setores");
        mnuSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSetorActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuSetor);

        mnuStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_status.png"))); // NOI18N
        mnuStatus.setText("Status");
        mnuStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuStatusActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuStatus);

        mnuColunaConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_config_coluna.png"))); // NOI18N
        mnuColunaConfig.setText("Configurações de Coluna");
        mnuColunaConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuColunaConfigActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuColunaConfig);

        mnuCromatografos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_pc.png"))); // NOI18N
        mnuCromatografos.setText("Cromatógrafos");
        mnuCromatografos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCromatografosActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuCromatografos);

        menuBar.add(mnuCadastro);

        mnuLaboratorio.setText("Laboratório");

        mnuVidrarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_vidraria.png"))); // NOI18N
        mnuVidrarias.setText("Vidrarias");
        mnuVidrarias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVidrariasActionPerformed(evt);
            }
        });
        mnuLaboratorio.add(mnuVidrarias);

        mnuColunas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_coluna.png"))); // NOI18N
        mnuColunas.setText("Colunas");
        mnuColunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuColunasActionPerformed(evt);
            }
        });
        mnuLaboratorio.add(mnuColunas);

        menuBar.add(mnuLaboratorio);

        mnuConfig.setText("Configurações");

        itmMenuUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_usuarios.png"))); // NOI18N
        itmMenuUsuarios.setText("Usuários");
        itmMenuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmMenuUsuariosActionPerformed(evt);
            }
        });
        mnuConfig.add(itmMenuUsuarios);
        mnuConfig.add(jSeparator2);

        itmBackUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_backup.png"))); // NOI18N
        itmBackUp.setText("Backup");
        itmBackUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmBackUpActionPerformed(evt);
            }
        });
        mnuConfig.add(itmBackUp);

        menuBar.add(mnuConfig);

        mnuJanela.setText("Janelas");

        mnuCascata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_cascade.png"))); // NOI18N
        mnuCascata.setText("Cascata");
        mnuCascata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCascataActionPerformed(evt);
            }
        });
        mnuJanela.add(mnuCascata);

        menuBar.add(mnuJanela);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void itmMenuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmMenuUsuariosActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (ConfigUsuario == null || ConfigUsuario.isClosed()) {
                ConfigUsuario = new FrmConfigUsuario();
                desktopPane.add(ConfigUsuario);
                Frames.centralizar(ConfigUsuario);
                ConfigUsuario.setVisible(true);
            } else if (ConfigUsuario.isVisible() == true) {
                ConfigUsuario.moveToFront();
                ConfigUsuario.setSelected(true);
                ConfigUsuario.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_itmMenuUsuariosActionPerformed

    private void mnuCromatografosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCromatografosActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (CadastroEquipamento == null || CadastroEquipamento.isClosed()) {
                CadastroEquipamento = new FrmCromatografo();
                desktopPane.add(CadastroEquipamento);
                Frames.centralizar(CadastroEquipamento);
                CadastroEquipamento.setVisible(true);
            } else if (CadastroEquipamento.isVisible() == true) {
                CadastroEquipamento.moveToFront();
                CadastroEquipamento.setSelected(true);
                CadastroEquipamento.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuCromatografosActionPerformed

    private void mnuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProdutoActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (Produto == null || Produto.isClosed()) {
                Produto = new FrmProduto();
                desktopPane.add(Produto);
                Frames.centralizar(Produto);
                Produto.setVisible(true);
            } else if (Produto.isVisible() == true) {
                Produto.moveToFront();
                Produto.setSelected(true);
                Produto.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuProdutoActionPerformed

    private void mnuMetodologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMetodologiaActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (CadastroMetodologia == null || CadastroMetodologia.isClosed()) {
                CadastroMetodologia = new FrmMetodologia();
                desktopPane.add(CadastroMetodologia);
                Frames.centralizar(CadastroMetodologia);
                CadastroMetodologia.setVisible(true);
            } else if (CadastroMetodologia.isVisible() == true) {
                CadastroMetodologia.moveToFront();
                CadastroMetodologia.setSelected(true);
                CadastroMetodologia.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuMetodologiaActionPerformed

    private void mnuAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAnaliseActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (CadastroAnalise == null || CadastroAnalise.isClosed()) {
                CadastroAnalise = new FrmAnalise();
                desktopPane.add(CadastroAnalise);
                Frames.centralizar(CadastroAnalise);
                CadastroAnalise.setVisible(true);
            } else if (CadastroAnalise.isVisible() == true) {
                CadastroAnalise.moveToFront();
                CadastroAnalise.setSelected(true);
                CadastroAnalise.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuAnaliseActionPerformed

    private void mnuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLogoutActionPerformed
        FrmLogin frm = new FrmLogin();
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuLogoutActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (LogBooK == null || LogBooK.isClosed()) {
                LogBooK = new FrmPrograCromatografo();
                desktopPane.add(LogBooK);
                Frames.centralizar(LogBooK);
                LogBooK.setVisible(true);
            } else if (LogBooK.isVisible() == true) {
                LogBooK.moveToFront();
                LogBooK.setSelected(true);
                LogBooK.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void itmMenuEntradaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmMenuEntradaProdutoActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (EntradaProduto == null || EntradaProduto.isClosed()) {
                EntradaProduto = new FrmEntradaProduto();
                desktopPane.add(EntradaProduto);
                Frames.centralizar(EntradaProduto);
                EntradaProduto.setVisible(true);
            } else if (EntradaProduto.isVisible() == true) {
                EntradaProduto.moveToFront();
                EntradaProduto.setSelected(true);
                EntradaProduto.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_itmMenuEntradaProdutoActionPerformed

    private void mnuSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSetorActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (CadastroSetor == null || CadastroSetor.isClosed()) {
                CadastroSetor = new FrmSetor();
                desktopPane.add(CadastroSetor);
                Frames.centralizar(CadastroSetor);
                CadastroSetor.setVisible(true);
            } else if (CadastroSetor.isVisible() == true) {
                CadastroSetor.moveToFront();
                CadastroSetor.setSelected(true);
                CadastroSetor.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuSetorActionPerformed

    private void itmMenuRetiradaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmMenuRetiradaProdutoActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (RetiradaProduto == null || RetiradaProduto.isClosed()) {
                RetiradaProduto = new FrmRetiradaProduto();
                desktopPane.add(RetiradaProduto);
                Frames.centralizar(RetiradaProduto);
                RetiradaProduto.setVisible(true);
            } else if (RetiradaProduto.isVisible() == true) {
                RetiradaProduto.moveToFront();
                RetiradaProduto.setSelected(true);
                RetiradaProduto.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_itmMenuRetiradaProdutoActionPerformed

    private void mnuStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuStatusActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (ConfigAmostra == null || ConfigAmostra.isClosed()) {
                ConfigAmostra = new FrmAmostra();
                desktopPane.add(ConfigAmostra);
                Frames.centralizar(ConfigAmostra);
                ConfigAmostra.setVisible(true);
            } else if (ConfigAmostra.isVisible() == true) {
                ConfigAmostra.moveToFront();
                ConfigAmostra.setSelected(true);
                ConfigAmostra.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuStatusActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (EntradaAmostra == null || EntradaAmostra.isClosed()) {
                EntradaAmostra = new FrmEntradaAmostra();
                desktopPane.add(EntradaAmostra);
                Frames.centralizar(EntradaAmostra);
                EntradaAmostra.setVisible(true);
            } else if (EntradaAmostra.isVisible() == true) {
                EntradaAmostra.moveToFront();
                EntradaAmostra.setSelected(true);
                EntradaAmostra.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mnuVidrariasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVidrariasActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (LaboratorioVidraria == null || LaboratorioVidraria.isClosed()) {
                LaboratorioVidraria = new FrmVidraria();
                desktopPane.add(LaboratorioVidraria);
                Frames.centralizar(LaboratorioVidraria);
                LaboratorioVidraria.setVisible(true);
            } else if (LaboratorioVidraria.isVisible() == true) {
                LaboratorioVidraria.moveToFront();
                LaboratorioVidraria.setSelected(true);
                LaboratorioVidraria.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuVidrariasActionPerformed

    private void mnuColunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuColunasActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (LaboratorioColuna == null || LaboratorioColuna.isClosed()) {
                LaboratorioColuna = new FrmColuna();
                desktopPane.add(LaboratorioColuna);
                Frames.centralizar(LaboratorioColuna);
                LaboratorioColuna.setVisible(true);
            } else if (LaboratorioColuna.isVisible() == true) {
                LaboratorioColuna.moveToFront();
                LaboratorioColuna.setSelected(true);
                LaboratorioColuna.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuColunasActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (MetodologiaAnalise == null || MetodologiaAnalise.isClosed()) {
                MetodologiaAnalise = new FrmMetodologiaAnalise();
                desktopPane.add(MetodologiaAnalise);
                Frames.centralizar(MetodologiaAnalise);
                MetodologiaAnalise.setVisible(true);
            } else if (MetodologiaAnalise.isVisible() == true) {
                MetodologiaAnalise.moveToFront();
                MetodologiaAnalise.setSelected(true);
                MetodologiaAnalise.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (Equipamento == null) {
                Equipamento = new FrmAbrirCromatografo(this, true);
                Equipamento.setVisible(true);
            } else {
                Equipamento.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mnuCascataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCascataActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Frames.cascade(desktopPane);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuCascataActionPerformed

    private void mnuPrograCromatografoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPrograCromatografoActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (PrograCromatografo == null || PrograCromatografo.isClosed()) {
                PrograCromatografo = new FrmPrograCromatografo();
                desktopPane.add(PrograCromatografo);
                Frames.centralizar(PrograCromatografo);
                PrograCromatografo.setVisible(true);
            } else if (PrograCromatografo.isVisible() == true) {
                PrograCromatografo.moveToFront();
                PrograCromatografo.setSelected(true);
                PrograCromatografo.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuPrograCromatografoActionPerformed

    private void mnuColunaConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuColunaConfigActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (ColunaConfig == null || ColunaConfig.isClosed()) {
                ColunaConfig = new FrmColunaConfig();
                desktopPane.add(ColunaConfig);
                Frames.centralizar(ColunaConfig);
                ColunaConfig.setVisible(true);
            } else if (ColunaConfig.isVisible() == true) {
                ColunaConfig.moveToFront();
                ColunaConfig.setSelected(true);
                ColunaConfig.show();
                desktopPane.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_mnuColunaConfigActionPerformed

    private void itmBackUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmBackUpActionPerformed
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Especifique um local para salvar");
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File("db_controle_" + DataHora.DateFileNow() + ".db"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo de Banco de Dados", "db"));

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            Backup.copyFile(fileToSave.getAbsoluteFile());
        }
    }//GEN-LAST:event_itmBackUpActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem itmBackUp;
    private javax.swing.JMenuItem itmMenuEntradaProduto;
    private javax.swing.JMenuItem itmMenuRetiradaProduto;
    private javax.swing.JMenuItem itmMenuUsuarios;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuAnalise;
    public static javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenuItem mnuCascata;
    private javax.swing.JMenuItem mnuColunaConfig;
    private javax.swing.JMenuItem mnuColunas;
    public static javax.swing.JMenu mnuConfig;
    private javax.swing.JMenuItem mnuCromatografos;
    private javax.swing.JMenu mnuEntradaProduto;
    private javax.swing.JMenu mnuJanela;
    public static javax.swing.JMenu mnuLaboratorio;
    private javax.swing.JMenu mnuLogBook;
    private javax.swing.JMenuItem mnuLogout;
    private javax.swing.JMenuItem mnuMetodologia;
    private javax.swing.JMenuItem mnuProduto;
    private javax.swing.JMenuItem mnuPrograCromatografo;
    public static javax.swing.JMenu mnuProgramacao;
    public static javax.swing.JMenu mnuRecepcao;
    private javax.swing.JMenu mnuRetiradaProduto;
    private javax.swing.JMenuItem mnuSetor;
    private javax.swing.JMenuItem mnuStatus;
    private javax.swing.JMenuItem mnuVidrarias;
    // End of variables declaration//GEN-END:variables

}
