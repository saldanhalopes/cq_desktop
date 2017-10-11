package view.laboratorio;

import DAO.ColunaDAO;
import model.Coluna;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael.lopes
 */
public class FrmVagaColuna extends javax.swing.JDialog {

    final String zeros = "0000";

    /**
     * Creates new form FrmVagaColuna
     *
     * @param parent
     * @param modal
     */
    public FrmVagaColuna(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Vaga Coluna");
        this.setResizable(false);
        readVagaColuna();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblVagaColuna = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cmbGaveta = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblVagaColuna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaga", "Código"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVagaColuna.setColumnSelectionAllowed(true);
        tblVagaColuna.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblVagaColuna.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblVagaColuna);
        tblVagaColuna.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblVagaColuna.getColumnModel().getColumnCount() > 0) {
            tblVagaColuna.getColumnModel().getColumn(0).setMinWidth(50);
            tblVagaColuna.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblVagaColuna.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jButton2.setText("Selecionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cmbGaveta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        cmbGaveta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGavetaActionPerformed(evt);
            }
        });

        jLabel1.setText("Gaveta:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbGaveta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGaveta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tblVagaColuna.getSelectedRow() != -1) {
            if ("-".equals(tblVagaColuna.getValueAt(tblVagaColuna.getSelectedRow(), 1))) {
                FrmColuna.txtVaga.setText(tblVagaColuna.getValueAt(tblVagaColuna.getSelectedRow(), 0).toString());
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Vaga indisponível");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    private void cmbGavetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGavetaActionPerformed
        TableRowSorter sorter = null;
        DefaultTableModel model = (DefaultTableModel) tblVagaColuna.getModel();
        sorter = new TableRowSorter<>(model);
        tblVagaColuna.setRowSorter(sorter);
        String text = cmbGaveta.getSelectedItem().toString();
        if ("Todos".equals(text)) {
            sorter.setRowFilter(null);
        } else {
            List<RowFilter<Object, Object>> filters = new ArrayList<>(3);
            filters.add(RowFilter.numberFilter(ComparisonType.AFTER,
                    ((Integer.parseInt(cmbGaveta.getSelectedItem().toString())) * 19) - 19, 0));

            filters.add(RowFilter.numberFilter(ComparisonType.BEFORE,
                    ((Integer.parseInt(cmbGaveta.getSelectedItem().toString())) * 19) + 1, 0));

            sorter.setRowFilter(RowFilter.andFilter(filters));
        }
    }//GEN-LAST:event_cmbGavetaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVagaColuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmVagaColuna dialog = new FrmVagaColuna(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbGaveta;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVagaColuna;
    // End of variables declaration//GEN-END:variables

public final void readVagaColuna() {
        DefaultTableModel model = (DefaultTableModel) tblVagaColuna.getModel();
        ColunaDAO colDao = new ColunaDAO();
        model.setNumRows(0);
        for (Coluna col : colDao.readVagaColuna()) {
            model.addRow(new Object[]{
                col.getVaga_coluna(),
                ("".equals(col.getTipo_coluna())
                || col.getTipo_coluna() == null)
                ? "-" : "CQ-"
                + col.getTipo_coluna()
                + "-" + zeros.substring(String.valueOf(col.getCodigo_coluna()).length())
                + String.valueOf(col.getCodigo_coluna())
            });
        }
    }
}
