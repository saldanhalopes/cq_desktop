package view.logbook;

import DAO.AnaliseDAO;
import DAO.ColunaDAO;
import DAO.CromatografoDAO;
import DAO.FaseMovelDAO;
import DAO.LoginDAO;
import util.DataHora;
import model.Cromatografo;
import model.Analise;
import model.Coluna;
import model.FaseMovel;
import model.Usuario;
import util.CriptMD5;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author rafael.lopes
 */
public final class FrmDialogSalvar extends javax.swing.JDialog {

    private static String sys_name;
    private static String analise_status_id;
    private static String analise_status;
    private static String analise_tipo;
    private static String campanha;
    private static String metodos;
    private static String obs;
    private TreeMap<Integer, String> categoryMapAnaliseAmostra = new TreeMap<>();

    /**
     * Creates new form frmDialogUsuario
     *
     * @param parent
     * @param modal
     * @param system_name
     * @param anls_status_id
     * @param anls_tipo
     */
    public FrmDialogSalvar(java.awt.Frame parent, boolean modal, String system_name,
            String anls_status_id, String anls_tipo) {
        super(parent, modal);
        initComponents();
        sys_name = system_name;
        analise_status_id = anls_status_id;
        analise_tipo = anls_tipo;
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Salvar Alterações - " + system_name);
        Analise anls = new Analise();
        AnaliseDAO anlsDAO = new AnaliseDAO();
        anls.setAnalise_status_id((anls_status_id == null) ? 0 : Integer.parseInt(anls_status_id));
        anlsDAO.selectAnaliseStatus(anls);
        anlsDAO.selectAnaliseAmostra(anls);
        analise_status = (anls.getAnalise_status() == null) ? "" : anls.getAnalise_status();
        readAnaliseAmostra();
        carregar();
    }

    public FrmDialogSalvar(java.awt.Frame parent, boolean modal, String system_name,
            String anls_status_id, String nome_campanha, String metodologias, String obs_log) {
        super(parent, modal);
        initComponents();
        sys_name = system_name;
        analise_status_id = anls_status_id;
        campanha = nome_campanha;
        metodos = metodologias;
        obs = obs_log;
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Salvar Alterações - " + system_name);
        Analise anls = new Analise();
        AnaliseDAO anlsDAO = new AnaliseDAO();
        anls.setAnalise_status_id((anls_status_id == null) ? 0 : Integer.parseInt(anls_status_id));
        anlsDAO.selectAnaliseStatus(anls);
        anlsDAO.selectAnaliseAmostra(anls);
        analise_status = (anls.getAnalise_status() == null) ? "" : anls.getAnalise_status();
        readAnaliseAmostra();
        carregar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtSenha = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        txtHoraAtualRegisto = new javax.swing.JSpinner();
        txtMinutoAtualRegisto = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDataAtualRegisto = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        txtUltimoRegisto = new javax.swing.JTextField();
        txtDataHoraUltimoRegisto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtRegistroAtual = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbAnaliseAmostra = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSalvar.setText("OK");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Senha"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSenha)
                .addGap(4, 4, 4))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data / Hora do Registro Atual"));

        jLabel1.setText("h");

        jLabel2.setText("min");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDataAtualRegisto, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoraAtualRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMinutoAtualRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHoraAtualRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinutoAtualRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(txtDataAtualRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Último Registro"));

        txtUltimoRegisto.setEditable(false);
        txtUltimoRegisto.setBackground(new java.awt.Color(204, 204, 204));

        txtDataHoraUltimoRegisto.setEditable(false);
        txtDataHoraUltimoRegisto.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setText("Data/Hora :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUltimoRegisto)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataHoraUltimoRegisto)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataHoraUltimoRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUltimoRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro Atual"));

        txtRegistroAtual.setEditable(false);
        txtRegistroAtual.setBackground(new java.awt.Color(204, 204, 204));

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        jLabel4.setText("Descrição");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Tipo:");

        cmbAnaliseAmostra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(txtRegistroAtual, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbAnaliseAmostra, 0, 248, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRegistroAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbAnaliseAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (cmbAnaliseAmostra.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione o Tipo");
            cmbAnaliseAmostra.requestFocus();
        } else if ("".equals(new String(txtSenha.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Senha inválida");
            txtSenha.requestFocus();
        } else if (senha()) {
            salvarDados();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Senha inválida");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDialogSalvar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmDialogSalvar dialog = new FrmDialogSalvar(new javax.swing.JFrame(), true, null, null, null, null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnSalvar;
    public static javax.swing.JComboBox cmbAnaliseAmostra;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    public static javax.swing.JScrollPane jScrollPane1;
    public static com.toedter.calendar.JDateChooser txtDataAtualRegisto;
    public static javax.swing.JTextField txtDataHoraUltimoRegisto;
    public static javax.swing.JTextArea txtDescricao;
    public static javax.swing.JSpinner txtHoraAtualRegisto;
    public static javax.swing.JSpinner txtMinutoAtualRegisto;
    public static javax.swing.JTextField txtRegistroAtual;
    public static javax.swing.JPasswordField txtSenha;
    public static javax.swing.JTextField txtUltimoRegisto;
    // End of variables declaration//GEN-END:variables

    public final void readAnaliseAmostra() {
        try {
            cmbAnaliseAmostra.removeAllItems();
            cmbAnaliseAmostra.addItem("");
            categoryMapAnaliseAmostra.clear();
            AnaliseDAO anseDao = new AnaliseDAO();
            for (Analise anls : anseDao.readAnaliseAmostraGeral()) {
                Integer id = anls.getAnalise_amostra_id();
                String name = anls.getAnalise_amostra();
                cmbAnaliseAmostra.addItem(name);
                categoryMapAnaliseAmostra.put(id, name);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    private void carregar() {
        Cromatografo equip = new Cromatografo();
        CromatografoDAO equipDAO = new CromatografoDAO();
        equip.setSystem_name(sys_name);
        equipDAO.selectLogCromatografo(equip);

        SpinnerNumberModel modelHora = new SpinnerNumberModel(0, 0, 23, 1);
        txtHoraAtualRegisto.setModel(modelHora);
        JSpinner.NumberEditor editorHora = new JSpinner.NumberEditor(txtHoraAtualRegisto);
        txtHoraAtualRegisto.setEditor(editorHora);
        SpinnerNumberModel modelMinuto = new SpinnerNumberModel(0, 0, 59, 1);
        txtMinutoAtualRegisto.setModel(modelMinuto);
        JSpinner.NumberEditor editorMinuto = new JSpinner.NumberEditor(txtMinutoAtualRegisto);
        txtMinutoAtualRegisto.setEditor(editorMinuto);
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        try {
            Date date = DataHora.getTimestampDate(equip.getData_inicio());
            cal.setTime(date);
            txtDataHoraUltimoRegisto.setText(DataHora.getStringDateTime(date));
            txtDataAtualRegisto.setDate(date);
            txtDataAtualRegisto.setMinSelectableDate(date);
        } catch (Exception e) {
            cal.setTime(currentDate);
            txtDataAtualRegisto.setDate(currentDate);
            txtDataAtualRegisto.setDate(currentDate);
            txtDataAtualRegisto.setMinSelectableDate(currentDate);
        }
        cal.setTime(currentDate);
        txtHoraAtualRegisto.setValue((Integer) cal.get(Calendar.HOUR_OF_DAY));
        txtMinutoAtualRegisto.setValue((Integer) cal.get(Calendar.MINUTE));
        txtDataAtualRegisto.setMaxSelectableDate(currentDate);
        txtRegistroAtual.setText(analise_status);
        cmbAnaliseAmostra.setSelectedItem(String.valueOf(analise_tipo));
        try {
            txtUltimoRegisto.setText(equip.getAnalise().getAnalise_status());
        } catch (Exception e) {
            txtUltimoRegisto.setText("Novo Registro");
        }
        try {
            if ("".equals(analise_tipo) || analise_tipo == null) {
                cmbAnaliseAmostra.setEnabled(true);
            } else {
                cmbAnaliseAmostra.setEnabled(false);
            }
        } catch (Exception e) {
            cmbAnaliseAmostra.setEnabled(false);
        }

    }

    private void salvarDados() {
        //cria modelos
        Cromatografo equip = new Cromatografo();
        CromatografoDAO equipDAO = new CromatografoDAO();
        Usuario user = new Usuario();
        FaseMovel fase = new FaseMovel();
        Coluna col = new Coluna();
        FaseMovelDAO faseDAO = new FaseMovelDAO();
        ColunaDAO colDAO = new ColunaDAO();
        //busca pelo nome do equipamento
        equip.setSystem_name(sys_name);
        equipDAO.selectLogCromatografo(equip);
        fase.setCromatografo(equip);
        faseDAO.selectUltimaFaseMovelBySystem(fase);
        col.setCromatografo(equip);
        colDAO.selectUltimaColunaBySystem(col);
        //formata data e hora
        Timestamp datahora = DataHora.getTimestampDateTime(DataHora.getStringDate(txtDataAtualRegisto.getDate()) 
                + " " + txtHoraAtualRegisto.getValue().toString() 
                + ":" + txtMinutoAtualRegisto.getValue().toString()+ ":00");
        equip.setData_fim(datahora);
        equip.setData_registro(DataHora.getTimestampDate(new Date()));
        //verifica se o registro é uma NOVA ANÁLISE
        //set FaseMovel e Coluna
        if (equip.getAnalise().getAnalise_status_id() == 1) {
            fase.setFase_movel_id(0);
            col.setColuna_id(0);
        }
        equip.setFasemovel(fase);
        equip.setColuna(col);
        //Atualiza no registro anterior
        equipDAO.updateLogCromatografo(equip);
        //Cria uma nova Analise
        Analise anls = new Analise();
        anls.setAnalise_status_id((analise_status_id == null) ? 0 : Integer.parseInt(analise_status_id));
        equip.setAnalise(anls);
        equip.setSystem_name(sys_name);
        equip.setData_inicio(datahora);
        equip.setDescricao(("".equals(cmbAnaliseAmostra.getSelectedItem().toString()) ? "" : cmbAnaliseAmostra.getSelectedItem().toString() + " - ") + txtDescricao.getText());
        user.setUser(System.getProperty("user"));
        equip.setUsuario(user);
        equip.setCampanha(campanha);
        equip.setMetodos(metodos);
        equip.setObs_log(obs);
        //Grava dados no banco
        equipDAO.createLogCromatografo(equip);
    }

    // Metodo para verificar senha do usuário
    public static boolean senha() {
        Usuario user = new Usuario();
        user.setUser(System.getProperty("user"));
        user.setPass(CriptMD5.md5(new String(txtSenha.getPassword())));
        LoginDAO loginDAO = new LoginDAO();
        if (loginDAO.selectUser(user)) {
            return true;
        } else {
            txtSenha.requestFocus();
            txtSenha.setText("");
            return false;
        }
    }

}
