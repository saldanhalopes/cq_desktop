/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastro;

import DAO.AnaliseDAO;
import acesso.AnaliseAcesso;
import model.Analise;
import util.ComboBox;
import util.GridlineCellRenderer;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael
 */
public final class FrmAnalise extends javax.swing.JInternalFrame {

    private boolean novoAnalise = false;
    private boolean novoFinalidade = false;
    private boolean novoTipo = false;
    private boolean novoProdutividade = false;
    private boolean novoStatus = false;
    private TreeMap<Integer, String> categoryProdutividadeStatus = new TreeMap<>();

    /**
     * Creates new form NovoJInternalFrame
     */
    public FrmAnalise() {
        initComponents();
        setTitle("Analises");
        fecharDados();
    }

    public void readAnalises() {
        DefaultTableModel model = (DefaultTableModel) tblAnalise.getModel();
        AnaliseDAO anseDao = new AnaliseDAO();
        model.setNumRows(0);
        for (Analise anse : anseDao.readAnalise()) {
            model.addRow(new Object[]{
                anse.getAnalise_id(),
                anse.getSigla_analise(),
                anse.getAnalise(),
                anse.getDescricao_analise()
            });
        }
    }

    public void readFinalidade() {
        DefaultTableModel model = (DefaultTableModel) tblFinalidade.getModel();
        AnaliseDAO anseDao = new AnaliseDAO();
        model.setNumRows(0);
        for (Analise anse : anseDao.readAnaliseFinalidade()) {
            model.addRow(new Object[]{
                anse.getAnalise_finalidade_id(),
                anse.getSigla_analise_finalidade(),
                anse.getAnalise_finalidade(),
                anse.getDescricao_analise_finalidade()
            });
        }
    }

    public void readTipo() {
        DefaultTableModel model = (DefaultTableModel) tblTipo.getModel();
        AnaliseDAO anseDao = new AnaliseDAO();
        model.setNumRows(0);
        for (Analise anse : anseDao.readAnaliseAmostra()) {
            model.addRow(new Object[]{
                anse.getAnalise_amostra_id(),
                anse.getSigla_analise_amostra(),
                anse.getAnalise_amostra(),
                anse.getDescricao_analise_amostra()
            });
        }
    }

    public void readProdutividade() {
        GridlineCellRenderer r = new GridlineCellRenderer(4);
        DefaultTableModel model = (DefaultTableModel) tblProdutividade.getModel();
        tblProdutividade.setDefaultRenderer(Object.class, r);
        AnaliseDAO anseDao = new AnaliseDAO();
        model.setNumRows(0);
        for (Analise anse : anseDao.readAnaliseProdutividade()) {
            model.addRow(new Object[]{
                anse.getAnalise_produtividade_id(),
                anse.getSigla_analise_produtividade(),
                anse.getAnalise_produtividade(),
                anse.getDescricao_analise_produtividade(),
                anse.getCor()
            });
        }
    }

    public void readProdutividadeStatus() {
        cmbProdutividade.removeAllItems();
        cmbProdutividade.addItem("");
        categoryProdutividadeStatus.clear();
        AnaliseDAO anseDao = new AnaliseDAO();
        for (Analise anse : anseDao.readAnaliseProdutividade()) {
            Integer id = anse.getAnalise_produtividade_id();
            String name = anse.getAnalise_produtividade();
            cmbProdutividade.addItem(name);
            categoryProdutividadeStatus.put(id, name);
        }
    }

    public void readStatus() {
        DefaultTableModel model = (DefaultTableModel) tblStatus.getModel();
        AnaliseDAO anseDao = new AnaliseDAO();
        model.setNumRows(0);
        for (Analise anse : anseDao.readAnaliseStatus()) {
            model.addRow(new Object[]{
                anse.getAnalise_status_id(),
                anse.getSigla_analise_status(),
                anse.getAnalise_status(),
                anse.getDescricao_analise_status(),
                anse.getAnalise_produtividade()
            });
        }
    }

    private void limparCampos() {
        //Limpa tabela Analise
        txtSiglaAnalise.setText(null);
        txtAnalise.setText(null);
        txtDescricaoAnalise.setText(null);
        txtSiglaAnalise.setText(null);
        //Limpa tabela Finalidade
        txtSiglaFinalidade.setText(null);
        txtFinalidade.setText(null);
        txtDescricaoFinalidade.setText(null);
        txtSiglaFinalidade.setText(null);
        //Limpa tabela Tipo
        txtSiglaTipo.setText(null);
        txtTipo.setText(null);
        txtDescricaoTipo.setText(null);
        txtSiglaTipo.setText(null);
        //Limpa tabela Produtividade
        txtSiglaProdutividade.setText(null);
        txtProdutividade.setText(null);
        txtDescricaoProdutividade.setText(null);
        txtSiglaProdutividade.setText(null);
        //Limpa tabela Status
        txtSiglaStatus.setText(null);
        txtStatus.setText(null);
        txtDescricaoStatus.setText(null);
        txtSiglaStatus.setText(null);
    }

    private void criarAnalise() {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setSigla_analise(txtSiglaAnalise.getText().toUpperCase());
            anse.setAnalise(txtAnalise.getText().toUpperCase());
            anse.setDescricao_analise(txtDescricaoAnalise.getText().toUpperCase());
            anseDAO.createAnalise(anse);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void criarFinalidade() {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setSigla_analise_finalidade(txtSiglaFinalidade.getText().toUpperCase());
            anse.setAnalise_finalidade(txtFinalidade.getText().toUpperCase());
            anse.setDescricao_analise_finalidade(txtDescricaoFinalidade.getText().toUpperCase());
            anseDAO.createAnaliseFinalidade(anse);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void criarTipo() {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setSigla_analise_amostra(txtSiglaTipo.getText().toUpperCase());
            anse.setAnalise_amostra(txtTipo.getText().toUpperCase());
            anse.setDescricao_analise_amostra(txtDescricaoTipo.getText().toUpperCase());
            anseDAO.createAnaliseAmostra(anse);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void criarProdutividade() {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setSigla_analise_produtividade(txtSiglaProdutividade.getText().toUpperCase());
            anse.setAnalise_produtividade(txtProdutividade.getText().toUpperCase());
            anse.setDescricao_analise_produtividade(txtDescricaoProdutividade.getText().toUpperCase());
            anse.setCor(cmbCor.getSelectedItem().toString());
            anseDAO.createAnaliseProdutividade(anse);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void criarStatus() {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setSigla_analise_status(txtSiglaStatus.getText().toUpperCase());
            anse.setAnalise_status(txtStatus.getText().toUpperCase());
            anse.setDescricao_analise_status(txtDescricaoStatus.getText().toUpperCase());
            anse.setAnalise_produtividade_id(ComboBox.getKeyForValue(cmbProdutividade.getSelectedItem().toString(), categoryProdutividadeStatus));
            anseDAO.createAnaliseStatus(anse);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void fecharDados() {
        //tabela Analise
        txtPesquisarAnalise.setText(null);
        pnlEditarAnalise.setVisible(false);
        btnExcluirAnalise.setVisible(false);
        btnCancelarAnalise.setVisible(false);
        btnSalvarAnalise.setVisible(false);
        tblAnalise.setRowSorter(null);
        //tabela Finalidade
        txtPesquisarFinalidade.setText(null);
        pnlEditarFinalidade.setVisible(false);
        btnExcluirFinalidade.setVisible(false);
        btnCancelarFinalidade.setVisible(false);
        btnSalvarFinalidade.setVisible(false);
        tblFinalidade.setRowSorter(null);
        //tabela Tipo
        txtPesquisarTipo.setText(null);
        pnlEditarTipo.setVisible(false);
        btnExcluirTipo.setVisible(false);
        btnCancelarTipo.setVisible(false);
        btnSalvarTipo.setVisible(false);
        tblTipo.setRowSorter(null);
        //tabela Produtividade
        txtPesquisarProdutividade.setText(null);
        pnlEditarProdutividade.setVisible(false);
        btnExcluirProdutividade.setVisible(false);
        btnCancelarProdutividade.setVisible(false);
        btnSalvarProdutividade.setVisible(false);
        tblProdutividade.setRowSorter(null);
        //tabela Status
        txtPesquisarStatus.setText(null);
        pnlEditarStatus.setVisible(false);
        btnExcluirStatus.setVisible(false);
        btnCancelarStatus.setVisible(false);
        btnSalvarStatus.setVisible(false);
        tblStatus.setRowSorter(null);
        //componentes
        tblPaneAnalises.setEnabledAt(0, true);
        tblPaneAnalises.setEnabledAt(1, true);
        tblPaneAnalises.setEnabledAt(2, true);
        tblPaneAnalises.setEnabledAt(3, true);
        tblPaneAnalises.setEnabledAt(4, true);
        itmNovoAnalise.setEnabled(true);
        itmNovoFinalidade.setEnabled(true);
        itmNovoTipo.setEnabled(true);
        itmNovoProdutividade.setEnabled(true);
        itmNovoStatus.setEnabled(true);
        AnaliseAcesso Acesso = new AnaliseAcesso();
        Acesso.verificarAcessoAnalise();
        limparCampos();
        readAnalises();
        readFinalidade();
        readTipo();
        readProdutividade();
        readStatus();
        readProdutividadeStatus();
    }

    private void abrirDadosAnalise() {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarAbrirDadosAnalise()) {
            tblPaneAnalises.setSelectedIndex(0);
            tblPaneAnalises.setEnabledAt(1, false);
            tblPaneAnalises.setEnabledAt(2, false);
            tblPaneAnalises.setEnabledAt(3, false);
            tblPaneAnalises.setEnabledAt(4, false);
            btnExcluirAnalise.setVisible(false);
            btnCancelarAnalise.setVisible(true);
            btnSalvarAnalise.setVisible(true);
            pnlEditarAnalise.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            itmNovoAnalise.setEnabled(false);
            limparCampos();
            novoAnalise = true;
        }
    }

    private void abrirDadosFinalidade() {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarAbrirDadosAnalise()) {
            tblPaneAnalises.setSelectedIndex(1);
            tblPaneAnalises.setEnabledAt(0, false);
            tblPaneAnalises.setEnabledAt(2, false);
            tblPaneAnalises.setEnabledAt(3, false);
            tblPaneAnalises.setEnabledAt(4, false);
            btnExcluirFinalidade.setVisible(false);
            btnCancelarFinalidade.setVisible(true);
            btnSalvarFinalidade.setVisible(true);
            pnlEditarFinalidade.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            limparCampos();
            novoFinalidade = true;
        }
    }

    private void abrirDadosTipo() {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarAbrirDadosAnalise()) {
            tblPaneAnalises.setSelectedIndex(2);
            tblPaneAnalises.setEnabledAt(0, false);
            tblPaneAnalises.setEnabledAt(1, false);
            tblPaneAnalises.setEnabledAt(3, false);
            tblPaneAnalises.setEnabledAt(4, false);
            btnExcluirTipo.setVisible(false);
            btnCancelarTipo.setVisible(true);
            btnSalvarTipo.setVisible(true);
            pnlEditarTipo.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            limparCampos();
            novoTipo = true;
        }
    }

    private void abrirDadosProdutividade() {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarAbrirDadosAnalise()) {
            tblPaneAnalises.setSelectedIndex(3);
            tblPaneAnalises.setEnabledAt(0, false);
            tblPaneAnalises.setEnabledAt(1, false);
            tblPaneAnalises.setEnabledAt(2, false);
            tblPaneAnalises.setEnabledAt(4, false);
            btnExcluirProdutividade.setVisible(false);
            btnCancelarProdutividade.setVisible(true);
            btnSalvarProdutividade.setVisible(true);
            pnlEditarProdutividade.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            limparCampos();
            novoProdutividade = true;
        }
    }

    private void abrirDadosStatus() {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarAbrirDadosAnalise()) {
            tblPaneAnalises.setSelectedIndex(4);
            tblPaneAnalises.setEnabledAt(0, false);
            tblPaneAnalises.setEnabledAt(1, false);
            tblPaneAnalises.setEnabledAt(2, false);
            tblPaneAnalises.setEnabledAt(3, false);
            btnExcluirStatus.setVisible(false);
            btnCancelarStatus.setVisible(true);
            btnSalvarStatus.setVisible(true);
            pnlEditarStatus.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            limparCampos();
            novoStatus = true;
        }
    }

    private void carregarDadosAnalise(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarCarregarDadosAnalise()) {
            Analise anse = new Analise();
            anse.setAnalise_id(id);
            AnaliseDAO anseDao = new AnaliseDAO();
            anseDao.selectAnalise(anse);
            txtSiglaAnalise.setText(anse.getSigla_analise());
            txtAnalise.setText(anse.getAnalise());
            txtDescricaoAnalise.setText(anse.getDescricao_analise());
            btnExcluirAnalise.setVisible(true);
            btnCancelarAnalise.setVisible(true);
            btnSalvarAnalise.setVisible(true);
            pnlEditarAnalise.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            tblPaneAnalises.setEnabledAt(1, false);
            tblPaneAnalises.setEnabledAt(2, false);
            tblPaneAnalises.setEnabledAt(3, false);
            tblPaneAnalises.setEnabledAt(4, false);
        }
    }

    private void carregarDadosFinalidade(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarCarregarDadosAnalise()) {
            Analise anse = new Analise();
            anse.setAnalise_finalidade_id(id);
            AnaliseDAO anseDao = new AnaliseDAO();
            anseDao.selectAnaliseFinalidade(anse);
            txtSiglaFinalidade.setText(anse.getSigla_analise_finalidade());
            txtFinalidade.setText(anse.getAnalise_finalidade());
            txtDescricaoFinalidade.setText(anse.getDescricao_analise_finalidade());
            btnExcluirFinalidade.setVisible(true);
            btnCancelarFinalidade.setVisible(true);
            btnSalvarFinalidade.setVisible(true);
            pnlEditarFinalidade.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            tblPaneAnalises.setEnabledAt(0, false);
            tblPaneAnalises.setEnabledAt(2, false);
            tblPaneAnalises.setEnabledAt(3, false);
            tblPaneAnalises.setEnabledAt(4, false);
        }
    }

    private void carregarDadosTipo(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarCarregarDadosAnalise()) {
            Analise anse = new Analise();
            anse.setAnalise_amostra_id(id);
            AnaliseDAO anseDao = new AnaliseDAO();
            anseDao.selectAnaliseAmostra(anse);
            txtSiglaTipo.setText(anse.getSigla_analise_amostra());
            txtTipo.setText(anse.getAnalise_amostra());
            txtDescricaoTipo.setText(anse.getDescricao_analise_amostra());
            btnExcluirTipo.setVisible(true);
            btnCancelarTipo.setVisible(true);
            btnSalvarTipo.setVisible(true);
            pnlEditarTipo.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            tblPaneAnalises.setEnabledAt(0, false);
            tblPaneAnalises.setEnabledAt(1, false);
            tblPaneAnalises.setEnabledAt(3, false);
            tblPaneAnalises.setEnabledAt(4, false);
        }
    }

    private void carregarDadosProdutividade(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarCarregarDadosAnalise()) {
            Analise anse = new Analise();
            anse.setAnalise_produtividade_id(id);
            AnaliseDAO anseDao = new AnaliseDAO();
            anseDao.selectAnaliseProdutividade(anse);
            txtSiglaProdutividade.setText(anse.getSigla_analise_produtividade());
            txtProdutividade.setText(anse.getAnalise_produtividade());
            txtDescricaoProdutividade.setText(anse.getDescricao_analise_produtividade());
            String[] cor = {anse.getCor()};
            cmbCor.setSelectedItem(cor[0]);
            btnExcluirProdutividade.setVisible(true);
            btnCancelarProdutividade.setVisible(true);
            btnSalvarProdutividade.setVisible(true);
            pnlEditarProdutividade.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            tblPaneAnalises.setEnabledAt(0, false);
            tblPaneAnalises.setEnabledAt(1, false);
            tblPaneAnalises.setEnabledAt(2, false);
            tblPaneAnalises.setEnabledAt(4, false);
        }
    }

    private void carregarDadosStatus(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarCarregarDadosAnalise()) {
            Analise anse = new Analise();
            anse.setAnalise_status_id(id);
            AnaliseDAO anseDao = new AnaliseDAO();
            anseDao.selectAnaliseStatus(anse);
            txtSiglaStatus.setText(anse.getSigla_analise_status());
            txtStatus.setText(anse.getAnalise_status());
            txtDescricaoStatus.setText(anse.getDescricao_analise_status());
            String[] produtividade = {anse.getAnalise_produtividade()};
            cmbProdutividade.setSelectedItem(produtividade[0]);
            btnExcluirStatus.setVisible(true);
            btnCancelarStatus.setVisible(true);
            btnSalvarStatus.setVisible(true);
            pnlEditarStatus.setVisible(true);
            itmNovoAnalise.setEnabled(false);
            itmNovoFinalidade.setEnabled(false);
            itmNovoTipo.setEnabled(false);
            itmNovoProdutividade.setEnabled(false);
            itmNovoStatus.setEnabled(false);
            tblPaneAnalises.setEnabledAt(0, false);
            tblPaneAnalises.setEnabledAt(1, false);
            tblPaneAnalises.setEnabledAt(2, false);
            tblPaneAnalises.setEnabledAt(3, false);
        }
    }

    private void atulizarAnalise(int id) {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setAnalise_id(id);
            anse.setSigla_analise(txtSiglaAnalise.getText().toUpperCase());
            anse.setAnalise(txtAnalise.getText().toUpperCase());
            anse.setDescricao_analise(txtDescricaoAnalise.getText().toUpperCase());
            anseDAO.updateAnalise(anse);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void atulizarFinalidade(int id) {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setAnalise_finalidade_id(id);
            anse.setSigla_analise_finalidade(txtSiglaFinalidade.getText().toUpperCase());
            anse.setAnalise_finalidade(txtFinalidade.getText().toUpperCase());
            anse.setDescricao_analise_finalidade(txtDescricaoFinalidade.getText().toUpperCase());
            anseDAO.updateAnaliseFinalidade(anse);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void atulizarTipo(int id) {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setAnalise_amostra_id(id);
            anse.setSigla_analise_amostra(txtSiglaTipo.getText().toUpperCase());
            anse.setAnalise_amostra(txtTipo.getText().toUpperCase());
            anse.setDescricao_analise_amostra(txtDescricaoTipo.getText().toUpperCase());
            anseDAO.updateAnaliseAmostra(anse);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void atulizarProdutividade(int id) {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setAnalise_produtividade_id(id);
            anse.setSigla_analise_produtividade(txtSiglaProdutividade.getText().toUpperCase());
            anse.setAnalise_produtividade(txtProdutividade.getText().toUpperCase());
            anse.setDescricao_analise_produtividade(txtDescricaoProdutividade.getText().toUpperCase());
            anse.setCor(cmbCor.getSelectedItem().toString());
            anseDAO.updateAnaliseProdutividade(anse);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void atulizarStatus(int id) {
        Analise anse = new Analise();
        AnaliseDAO anseDAO = new AnaliseDAO();
        try {
            anse.setAnalise_status_id(id);
            anse.setSigla_analise_status(txtSiglaStatus.getText().toUpperCase());
            anse.setAnalise_status(txtStatus.getText().toUpperCase());
            anse.setDescricao_analise_status(txtDescricaoStatus.getText().toUpperCase());
            anse.setAnalise_produtividade_id(ComboBox.getKeyForValue(cmbProdutividade.getSelectedItem().toString(), categoryProdutividadeStatus));
            anseDAO.updateAnaliseStatus(anse);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        } finally {
            fecharDados();
        }
    }

    private void deletarDadosAnalise(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarDeletarDadosAnalise()) {
            Analise anse = new Analise();
            AnaliseDAO anseDAO = new AnaliseDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    anse.setAnalise_id(id);
                    anseDAO.deleteAnalise(anse);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
            } finally {
                fecharDados();
            }
        }
    }

    private void deletarDadosFinalidade(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarDeletarDadosAnalise()) {
            Analise anse = new Analise();
            AnaliseDAO anseDAO = new AnaliseDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    anse.setAnalise_finalidade_id(id);
                    anseDAO.deleteAnaliseFinalidade(anse);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
            } finally {
                fecharDados();
            }
        }
    }

    private void deletarDadosTipo(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarDeletarDadosAnalise()) {
            Analise anse = new Analise();
            AnaliseDAO anseDAO = new AnaliseDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    anse.setAnalise_amostra_id(id);
                    anseDAO.deleteAnaliseAmostra(anse);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
            } finally {
                fecharDados();
            }
        }
    }

    private void deletarDadosProdutividade(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarDeletarDadosAnalise()) {
            Analise anse = new Analise();
            AnaliseDAO anseDAO = new AnaliseDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    anse.setAnalise_produtividade_id(id);
                    anseDAO.deleteAnaliseProdutividade(anse);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
            } finally {
                fecharDados();
            }
        }
    }

    private void deletarDadosStatus(int id) {
        AnaliseAcesso Acesso = new AnaliseAcesso();
        if (Acesso.verificarDeletarDadosAnalise()) {
            Analise anse = new Analise();
            AnaliseDAO anseDAO = new AnaliseDAO();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    anse.setAnalise_status_id(id);
                    anseDAO.deleteAnaliseStatus(anse);
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

        tblPaneAnalises = new javax.swing.JTabbedPane();
        pnlAnalises = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnalise = new javax.swing.JTable();
        txtPesquisarAnalise = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnlEditarAnalise = new javax.swing.JPanel();
        txtSiglaAnalise = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtAnalise = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDescricaoAnalise = new javax.swing.JTextField();
        btnExcluirAnalise = new javax.swing.JButton();
        btnSalvarAnalise = new javax.swing.JButton();
        btnCancelarAnalise = new javax.swing.JButton();
        pnlFinalidade = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFinalidade = new javax.swing.JTable();
        txtPesquisarFinalidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pnlEditarFinalidade = new javax.swing.JPanel();
        txtSiglaFinalidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFinalidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDescricaoFinalidade = new javax.swing.JTextField();
        btnExcluirFinalidade = new javax.swing.JButton();
        btnSalvarFinalidade = new javax.swing.JButton();
        btnCancelarFinalidade = new javax.swing.JButton();
        pnlTipo = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTipo = new javax.swing.JTable();
        txtPesquisarTipo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pnlEditarTipo = new javax.swing.JPanel();
        txtSiglaTipo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDescricaoTipo = new javax.swing.JTextField();
        btnExcluirTipo = new javax.swing.JButton();
        btnSalvarTipo = new javax.swing.JButton();
        btnCancelarTipo = new javax.swing.JButton();
        pnlProdutividade = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProdutividade = new javax.swing.JTable();
        txtPesquisarProdutividade = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        pnlEditarProdutividade = new javax.swing.JPanel();
        txtSiglaProdutividade = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtProdutividade = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDescricaoProdutividade = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cmbCor = new javax.swing.JComboBox();
        btnExcluirProdutividade = new javax.swing.JButton();
        btnSalvarProdutividade = new javax.swing.JButton();
        btnCancelarProdutividade = new javax.swing.JButton();
        pnlStatus = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStatus = new javax.swing.JTable();
        txtPesquisarStatus = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        pnlEditarStatus = new javax.swing.JPanel();
        txtSiglaStatus = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDescricaoStatus = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cmbProdutividade = new javax.swing.JComboBox();
        btnExcluirStatus = new javax.swing.JButton();
        btnSalvarStatus = new javax.swing.JButton();
        btnCancelarStatus = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mnuNovoEquipamento = new javax.swing.JMenu();
        itmNovoAnalise = new javax.swing.JMenuItem();
        itmNovoFinalidade = new javax.swing.JMenuItem();
        itmNovoTipo = new javax.swing.JMenuItem();
        itmNovoProdutividade = new javax.swing.JMenuItem();
        itmNovoStatus = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Análises"));

        tblAnalise.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sigla", "Análise", "Descrição"
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
        tblAnalise.setSurrendersFocusOnKeystroke(true);
        tblAnalise.getTableHeader().setReorderingAllowed(false);
        tblAnalise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAnaliseMouseClicked(evt);
            }
        });
        tblAnalise.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAnaliseKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblAnalise);
        if (tblAnalise.getColumnModel().getColumnCount() > 0) {
            tblAnalise.getColumnModel().getColumn(0).setMinWidth(50);
            tblAnalise.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblAnalise.getColumnModel().getColumn(0).setMaxWidth(50);
            tblAnalise.getColumnModel().getColumn(1).setMinWidth(80);
            tblAnalise.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblAnalise.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        txtPesquisarAnalise.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarAnaliseKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar:");

        pnlEditarAnalise.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Análise"));

        jLabel1.setText("Sigla:");

        jLabel2.setText("Análise:");

        jLabel5.setText("Descrição:");

        javax.swing.GroupLayout pnlEditarAnaliseLayout = new javax.swing.GroupLayout(pnlEditarAnalise);
        pnlEditarAnalise.setLayout(pnlEditarAnaliseLayout);
        pnlEditarAnaliseLayout.setHorizontalGroup(
            pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditarAnaliseLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSiglaAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnalise))
                    .addGroup(pnlEditarAnaliseLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricaoAnalise)))
                .addContainerGap())
        );
        pnlEditarAnaliseLayout.setVerticalGroup(
            pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditarAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSiglaAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDescricaoAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirAnalise.setText("Excuir");
        btnExcluirAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAnaliseActionPerformed(evt);
            }
        });

        btnSalvarAnalise.setText("Ok");
        btnSalvarAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAnaliseActionPerformed(evt);
            }
        });

        btnCancelarAnalise.setText("Cancelar");
        btnCancelarAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAnaliseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAnalisesLayout = new javax.swing.GroupLayout(pnlAnalises);
        pnlAnalises.setLayout(pnlAnalisesLayout);
        pnlAnalisesLayout.setHorizontalGroup(
            pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnalisesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAnalisesLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarAnalise))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEditarAnalise, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAnalisesLayout.createSequentialGroup()
                        .addComponent(btnExcluirAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlAnalisesLayout.setVerticalGroup(
            pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnalisesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEditarAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAnalisesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirAnalise)
                    .addComponent(btnSalvarAnalise)
                    .addComponent(btnCancelarAnalise))
                .addContainerGap())
        );

        tblPaneAnalises.addTab("Análises", pnlAnalises);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Finalidade"));

        tblFinalidade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sigla", "Finalidade da Análise", "Descrição"
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
        tblFinalidade.setSurrendersFocusOnKeystroke(true);
        tblFinalidade.getTableHeader().setReorderingAllowed(false);
        tblFinalidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFinalidadeMouseClicked(evt);
            }
        });
        tblFinalidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblFinalidadeKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblFinalidade);
        if (tblFinalidade.getColumnModel().getColumnCount() > 0) {
            tblFinalidade.getColumnModel().getColumn(0).setMinWidth(50);
            tblFinalidade.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblFinalidade.getColumnModel().getColumn(0).setMaxWidth(50);
            tblFinalidade.getColumnModel().getColumn(1).setMinWidth(80);
            tblFinalidade.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblFinalidade.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        txtPesquisarFinalidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarFinalidadeKeyReleased(evt);
            }
        });

        jLabel6.setText("Pesquisar:");

        pnlEditarFinalidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Finalidade"));

        jLabel3.setText("Sigla:");

        jLabel7.setText("Análise:");

        jLabel8.setText("Descrição:");

        javax.swing.GroupLayout pnlEditarFinalidadeLayout = new javax.swing.GroupLayout(pnlEditarFinalidade);
        pnlEditarFinalidade.setLayout(pnlEditarFinalidadeLayout);
        pnlEditarFinalidadeLayout.setHorizontalGroup(
            pnlEditarFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarFinalidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditarFinalidadeLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSiglaFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFinalidade))
                    .addGroup(pnlEditarFinalidadeLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricaoFinalidade)))
                .addContainerGap())
        );
        pnlEditarFinalidadeLayout.setVerticalGroup(
            pnlEditarFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditarFinalidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSiglaFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditarFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDescricaoFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirFinalidade.setText("Excuir");
        btnExcluirFinalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirFinalidadeActionPerformed(evt);
            }
        });

        btnSalvarFinalidade.setText("Ok");
        btnSalvarFinalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarFinalidadeActionPerformed(evt);
            }
        });

        btnCancelarFinalidade.setText("Cancelar");
        btnCancelarFinalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFinalidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFinalidadeLayout = new javax.swing.GroupLayout(pnlFinalidade);
        pnlFinalidade.setLayout(pnlFinalidadeLayout);
        pnlFinalidadeLayout.setHorizontalGroup(
            pnlFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFinalidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFinalidadeLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarFinalidade))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEditarFinalidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFinalidadeLayout.createSequentialGroup()
                        .addComponent(btnExcluirFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlFinalidadeLayout.setVerticalGroup(
            pnlFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFinalidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEditarFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFinalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirFinalidade)
                    .addComponent(btnSalvarFinalidade)
                    .addComponent(btnCancelarFinalidade))
                .addContainerGap())
        );

        tblPaneAnalises.addTab("Finalidade", pnlFinalidade);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Análise"));

        tblTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sigla", "Tipo de Análise", "Descrição"
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
        tblTipo.setSurrendersFocusOnKeystroke(true);
        tblTipo.getTableHeader().setReorderingAllowed(false);
        tblTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoMouseClicked(evt);
            }
        });
        tblTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblTipoKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblTipo);
        if (tblTipo.getColumnModel().getColumnCount() > 0) {
            tblTipo.getColumnModel().getColumn(0).setMinWidth(50);
            tblTipo.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblTipo.getColumnModel().getColumn(0).setMaxWidth(50);
            tblTipo.getColumnModel().getColumn(1).setMinWidth(80);
            tblTipo.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblTipo.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        txtPesquisarTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarTipoKeyReleased(evt);
            }
        });

        jLabel9.setText("Pesquisar:");

        pnlEditarTipo.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Tipo de Análise"));

        jLabel10.setText("Sigla:");

        jLabel11.setText("Análise:");

        jLabel12.setText("Descrição:");

        javax.swing.GroupLayout pnlEditarTipoLayout = new javax.swing.GroupLayout(pnlEditarTipo);
        pnlEditarTipo.setLayout(pnlEditarTipoLayout);
        pnlEditarTipoLayout.setHorizontalGroup(
            pnlEditarTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditarTipoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSiglaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipo))
                    .addGroup(pnlEditarTipoLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricaoTipo)))
                .addContainerGap())
        );
        pnlEditarTipoLayout.setVerticalGroup(
            pnlEditarTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditarTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSiglaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditarTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDescricaoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirTipo.setText("Excuir");
        btnExcluirTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirTipoActionPerformed(evt);
            }
        });

        btnSalvarTipo.setText("Ok");
        btnSalvarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarTipoActionPerformed(evt);
            }
        });

        btnCancelarTipo.setText("Cancelar");
        btnCancelarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTipoLayout = new javax.swing.GroupLayout(pnlTipo);
        pnlTipo.setLayout(pnlTipoLayout);
        pnlTipoLayout.setHorizontalGroup(
            pnlTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTipoLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarTipo))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEditarTipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTipoLayout.createSequentialGroup()
                        .addComponent(btnExcluirTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTipoLayout.setVerticalGroup(
            pnlTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEditarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirTipo)
                    .addComponent(btnSalvarTipo)
                    .addComponent(btnCancelarTipo))
                .addContainerGap())
        );

        tblPaneAnalises.addTab("Tipo", pnlTipo);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtividade"));

        tblProdutividade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sigla", "Produtividade da Análise ", "Descrição", "Cor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        tblProdutividade.setSurrendersFocusOnKeystroke(true);
        tblProdutividade.getTableHeader().setReorderingAllowed(false);
        tblProdutividade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutividadeMouseClicked(evt);
            }
        });
        tblProdutividade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProdutividadeKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblProdutividade);
        if (tblProdutividade.getColumnModel().getColumnCount() > 0) {
            tblProdutividade.getColumnModel().getColumn(0).setMinWidth(50);
            tblProdutividade.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProdutividade.getColumnModel().getColumn(0).setMaxWidth(50);
            tblProdutividade.getColumnModel().getColumn(1).setMinWidth(80);
            tblProdutividade.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblProdutividade.getColumnModel().getColumn(1).setMaxWidth(80);
            tblProdutividade.getColumnModel().getColumn(4).setMinWidth(0);
            tblProdutividade.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblProdutividade.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );

        txtPesquisarProdutividade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarProdutividadeKeyReleased(evt);
            }
        });

        jLabel13.setText("Pesquisar:");

        pnlEditarProdutividade.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Produtividade"));

        jLabel14.setText("Sigla:");

        jLabel15.setText("Produtividade:");

        jLabel16.setText("Descrição:");

        jLabel22.setText("Cor:");

        cmbCor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Branco", "Vermelho", "Verde", "Laranja", "Azul", "Amarelo", "Cinza", "Roxo", "Rosa", "Preto" }));

        javax.swing.GroupLayout pnlEditarProdutividadeLayout = new javax.swing.GroupLayout(pnlEditarProdutividade);
        pnlEditarProdutividade.setLayout(pnlEditarProdutividadeLayout);
        pnlEditarProdutividadeLayout.setHorizontalGroup(
            pnlEditarProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarProdutividadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditarProdutividadeLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSiglaProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProdutividade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEditarProdutividadeLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricaoProdutividade)))
                .addContainerGap())
        );
        pnlEditarProdutividadeLayout.setVerticalGroup(
            pnlEditarProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditarProdutividadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtSiglaProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cmbCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditarProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtDescricaoProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirProdutividade.setText("Excuir");
        btnExcluirProdutividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProdutividadeActionPerformed(evt);
            }
        });

        btnSalvarProdutividade.setText("Ok");
        btnSalvarProdutividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarProdutividadeActionPerformed(evt);
            }
        });

        btnCancelarProdutividade.setText("Cancelar");
        btnCancelarProdutividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProdutividadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProdutividadeLayout = new javax.swing.GroupLayout(pnlProdutividade);
        pnlProdutividade.setLayout(pnlProdutividadeLayout);
        pnlProdutividadeLayout.setHorizontalGroup(
            pnlProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdutividadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProdutividadeLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarProdutividade))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEditarProdutividade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlProdutividadeLayout.createSequentialGroup()
                        .addComponent(btnExcluirProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlProdutividadeLayout.setVerticalGroup(
            pnlProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdutividadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEditarProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProdutividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirProdutividade)
                    .addComponent(btnSalvarProdutividade)
                    .addComponent(btnCancelarProdutividade))
                .addContainerGap())
        );

        tblPaneAnalises.addTab("Produtividade", pnlProdutividade);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        tblStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sigla", "Status da Análise", "Descrição", "Produtividade"
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
        tblStatus.setSurrendersFocusOnKeystroke(true);
        tblStatus.getTableHeader().setReorderingAllowed(false);
        tblStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStatusMouseClicked(evt);
            }
        });
        tblStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblStatusKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblStatus);
        if (tblStatus.getColumnModel().getColumnCount() > 0) {
            tblStatus.getColumnModel().getColumn(0).setMinWidth(50);
            tblStatus.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblStatus.getColumnModel().getColumn(0).setMaxWidth(50);
            tblStatus.getColumnModel().getColumn(1).setMinWidth(80);
            tblStatus.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblStatus.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        txtPesquisarStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarStatusKeyReleased(evt);
            }
        });

        jLabel17.setText("Pesquisar:");

        pnlEditarStatus.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Status"));

        jLabel18.setText("Sigla:");

        jLabel19.setText("Status:");

        jLabel20.setText("Descrição:");

        jLabel21.setText("Produtividade:");

        javax.swing.GroupLayout pnlEditarStatusLayout = new javax.swing.GroupLayout(pnlEditarStatus);
        pnlEditarStatus.setLayout(pnlEditarStatusLayout);
        pnlEditarStatusLayout.setHorizontalGroup(
            pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEditarStatusLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSiglaStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEditarStatusLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricaoStatus)))
                .addContainerGap())
        );
        pnlEditarStatusLayout.setVerticalGroup(
            pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditarStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSiglaStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(cmbProdutividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtDescricaoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcluirStatus.setText("Excuir");
        btnExcluirStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirStatusActionPerformed(evt);
            }
        });

        btnSalvarStatus.setText("Ok");
        btnSalvarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarStatusActionPerformed(evt);
            }
        });

        btnCancelarStatus.setText("Cancelar");
        btnCancelarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStatusLayout = new javax.swing.GroupLayout(pnlStatus);
        pnlStatus.setLayout(pnlStatusLayout);
        pnlStatusLayout.setHorizontalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStatusLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarStatus))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEditarStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlStatusLayout.createSequentialGroup()
                        .addComponent(btnExcluirStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlStatusLayout.setVerticalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEditarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirStatus)
                    .addComponent(btnSalvarStatus)
                    .addComponent(btnCancelarStatus))
                .addContainerGap())
        );

        tblPaneAnalises.addTab("Status", pnlStatus);

        mnuArquivo.setText("Arquivo");

        mnuNovoEquipamento.setText("Novo");

        itmNovoAnalise.setText("Análise");
        itmNovoAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoAnaliseActionPerformed(evt);
            }
        });
        mnuNovoEquipamento.add(itmNovoAnalise);

        itmNovoFinalidade.setText("Finalidade");
        itmNovoFinalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoFinalidadeActionPerformed(evt);
            }
        });
        mnuNovoEquipamento.add(itmNovoFinalidade);

        itmNovoTipo.setText("Tipo");
        itmNovoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoTipoActionPerformed(evt);
            }
        });
        mnuNovoEquipamento.add(itmNovoTipo);

        itmNovoProdutividade.setText("Produtividade");
        itmNovoProdutividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoProdutividadeActionPerformed(evt);
            }
        });
        mnuNovoEquipamento.add(itmNovoProdutividade);

        itmNovoStatus.setText("Status");
        itmNovoStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNovoStatusActionPerformed(evt);
            }
        });
        mnuNovoEquipamento.add(itmNovoStatus);

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneAnalises, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblPaneAnalises)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        dispose();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void itmNovoAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoAnaliseActionPerformed
        abrirDadosAnalise();
    }//GEN-LAST:event_itmNovoAnaliseActionPerformed

    private void btnCancelarAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAnaliseActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarAnaliseActionPerformed

    private void btnSalvarAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAnaliseActionPerformed
        if (txtSiglaAnalise.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sigla inválida");
            txtSiglaAnalise.requestFocus();
        } else if (txtAnalise.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Análise inválido");
            txtAnalise.requestFocus();
        } else if (txtDescricaoAnalise.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição inválida");
            txtDescricaoAnalise.requestFocus();
        } else if (novoAnalise) {
            criarAnalise();
        } else {
            atulizarAnalise((Integer) tblAnalise.getValueAt(tblAnalise.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_btnSalvarAnaliseActionPerformed

    private void btnExcluirAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAnaliseActionPerformed
        deletarDadosAnalise((Integer) tblAnalise.getValueAt(tblAnalise.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirAnaliseActionPerformed

    private void txtPesquisarAnaliseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarAnaliseKeyReleased
        TableRowSorter sorter = null;
        sorter = new TableRowSorter<>(tblAnalise.getModel());
        tblAnalise.setRowSorter(sorter);
        String text = txtPesquisarAnalise.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarAnaliseKeyReleased

    private void tblAnaliseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAnaliseKeyReleased
        if (tblAnalise.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarDadosAnalise((Integer) tblAnalise.getValueAt(tblAnalise.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblAnaliseKeyReleased

    private void tblAnaliseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAnaliseMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDadosAnalise((Integer) tblAnalise.getValueAt(tblAnalise.getSelectedRow(), 0));
            novoAnalise = false;
        }
    }//GEN-LAST:event_tblAnaliseMouseClicked

    private void tblFinalidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFinalidadeMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDadosFinalidade((Integer) tblFinalidade.getValueAt(tblFinalidade.getSelectedRow(), 0));
            novoFinalidade = false;
        }
    }//GEN-LAST:event_tblFinalidadeMouseClicked

    private void tblFinalidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblFinalidadeKeyReleased
        if (tblFinalidade.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarDadosFinalidade((Integer) tblFinalidade.getValueAt(tblFinalidade.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblFinalidadeKeyReleased

    private void txtPesquisarFinalidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarFinalidadeKeyReleased
        TableRowSorter sorter = null;
        sorter = new TableRowSorter<>(tblFinalidade.getModel());
        tblFinalidade.setRowSorter(sorter);
        String text = txtPesquisarFinalidade.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarFinalidadeKeyReleased

    private void btnExcluirFinalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirFinalidadeActionPerformed
        deletarDadosFinalidade((Integer) tblFinalidade.getValueAt(tblFinalidade.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirFinalidadeActionPerformed

    private void btnSalvarFinalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarFinalidadeActionPerformed
        if (txtSiglaFinalidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sigla inválida");
            txtSiglaFinalidade.requestFocus();
        } else if (txtFinalidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Finalidade da Análise inválida");
            txtFinalidade.requestFocus();
        } else if (txtDescricaoFinalidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição inválida");
            txtDescricaoFinalidade.requestFocus();
        } else if (novoFinalidade) {
            criarFinalidade();
        } else {
            atulizarFinalidade((Integer) tblFinalidade.getValueAt(tblFinalidade.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_btnSalvarFinalidadeActionPerformed

    private void btnCancelarFinalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFinalidadeActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarFinalidadeActionPerformed

    private void tblTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDadosTipo((Integer) tblTipo.getValueAt(tblTipo.getSelectedRow(), 0));
            novoTipo = false;
        }
    }//GEN-LAST:event_tblTipoMouseClicked

    private void tblTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTipoKeyReleased
        if (tblTipo.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarDadosTipo((Integer) tblTipo.getValueAt(tblTipo.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblTipoKeyReleased

    private void txtPesquisarTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarTipoKeyReleased
        TableRowSorter sorter = null;
        sorter = new TableRowSorter<>(tblTipo.getModel());
        tblTipo.setRowSorter(sorter);
        String text = txtPesquisarTipo.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarTipoKeyReleased

    private void btnExcluirTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirTipoActionPerformed
        deletarDadosTipo((Integer) tblTipo.getValueAt(tblTipo.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirTipoActionPerformed

    private void btnSalvarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarTipoActionPerformed
        if (txtSiglaTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sigla inválida");
            txtSiglaTipo.requestFocus();
        } else if (txtTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tipo de Análise inválida");
            txtTipo.requestFocus();
        } else if (txtDescricaoTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição inválida");
            txtDescricaoTipo.requestFocus();
        } else if (novoTipo) {
            criarTipo();
        } else {
            atulizarTipo((Integer) tblTipo.getValueAt(tblTipo.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_btnSalvarTipoActionPerformed

    private void btnCancelarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTipoActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarTipoActionPerformed

    private void itmNovoFinalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoFinalidadeActionPerformed
        abrirDadosFinalidade();
    }//GEN-LAST:event_itmNovoFinalidadeActionPerformed

    private void itmNovoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoTipoActionPerformed
        abrirDadosTipo();
    }//GEN-LAST:event_itmNovoTipoActionPerformed

    private void tblProdutividadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutividadeMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDadosProdutividade((Integer) tblProdutividade.getValueAt(tblProdutividade.getSelectedRow(), 0));
            novoProdutividade = false;
        }
    }//GEN-LAST:event_tblProdutividadeMouseClicked

    private void tblProdutividadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProdutividadeKeyReleased
        if (tblProdutividade.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarDadosProdutividade((Integer) tblProdutividade.getValueAt(tblProdutividade.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblProdutividadeKeyReleased

    private void txtPesquisarProdutividadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarProdutividadeKeyReleased
        TableRowSorter sorter = null;
        sorter = new TableRowSorter<>(tblProdutividade.getModel());
        tblProdutividade.setRowSorter(sorter);
        String text = txtPesquisarProdutividade.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarProdutividadeKeyReleased

    private void btnExcluirProdutividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutividadeActionPerformed
        deletarDadosProdutividade((Integer) tblProdutividade.getValueAt(tblProdutividade.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirProdutividadeActionPerformed

    private void btnSalvarProdutividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarProdutividadeActionPerformed
        if (txtSiglaProdutividade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sigla inválida");
            txtSiglaProdutividade.requestFocus();
        } else if (txtProdutividade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Produtividade de Análise inválida");
            txtProdutividade.requestFocus();
        } else if (txtDescricaoProdutividade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição inválida");
            txtDescricaoProdutividade.requestFocus();
        } else if (cmbCor.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cor inválida");
            txtDescricaoProdutividade.requestFocus();
        } else if (novoProdutividade) {
            criarProdutividade();
        } else {
            atulizarProdutividade((Integer) tblProdutividade.getValueAt(tblProdutividade.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_btnSalvarProdutividadeActionPerformed

    private void btnCancelarProdutividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProdutividadeActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarProdutividadeActionPerformed

    private void tblStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStatusMouseClicked
        if (evt.getClickCount() == 2) {
            carregarDadosStatus((Integer) tblStatus.getValueAt(tblStatus.getSelectedRow(), 0));
            novoStatus = false;
        }
    }//GEN-LAST:event_tblStatusMouseClicked

    private void tblStatusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblStatusKeyReleased
        if (tblStatus.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarDadosStatus((Integer) tblStatus.getValueAt(tblStatus.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_tblStatusKeyReleased

    private void txtPesquisarStatusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarStatusKeyReleased
        TableRowSorter sorter = null;
        sorter = new TableRowSorter<>(tblStatus.getModel());
        tblStatus.setRowSorter(sorter);
        String text = txtPesquisarStatus.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarStatusKeyReleased

    private void btnExcluirStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirStatusActionPerformed
        deletarDadosStatus((Integer) tblStatus.getValueAt(tblStatus.getSelectedRow(), 0));
    }//GEN-LAST:event_btnExcluirStatusActionPerformed

    private void btnSalvarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarStatusActionPerformed
        if (txtSiglaStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sigla inválida");
            txtSiglaStatus.requestFocus();
        } else if (txtStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Status da Análise inválido");
            txtStatus.requestFocus();
        } else if (txtDescricaoStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição inválida");
            txtDescricaoStatus.requestFocus();
        } else if (cmbProdutividade.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Produtividade inválida");
        } else if (novoStatus) {
            criarStatus();
        } else {
            atulizarStatus((Integer) tblStatus.getValueAt(tblStatus.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_btnSalvarStatusActionPerformed

    private void btnCancelarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarStatusActionPerformed
        fecharDados();
    }//GEN-LAST:event_btnCancelarStatusActionPerformed

    private void itmNovoProdutividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoProdutividadeActionPerformed
        abrirDadosProdutividade();
    }//GEN-LAST:event_itmNovoProdutividadeActionPerformed

    private void itmNovoStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNovoStatusActionPerformed
        abrirDadosStatus();
    }//GEN-LAST:event_itmNovoStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarAnalise;
    private javax.swing.JButton btnCancelarFinalidade;
    private javax.swing.JButton btnCancelarProdutividade;
    private javax.swing.JButton btnCancelarStatus;
    private javax.swing.JButton btnCancelarTipo;
    public static javax.swing.JButton btnExcluirAnalise;
    public static javax.swing.JButton btnExcluirFinalidade;
    public static javax.swing.JButton btnExcluirProdutividade;
    public static javax.swing.JButton btnExcluirStatus;
    public static javax.swing.JButton btnExcluirTipo;
    public static javax.swing.JButton btnSalvarAnalise;
    public static javax.swing.JButton btnSalvarFinalidade;
    public static javax.swing.JButton btnSalvarProdutividade;
    public static javax.swing.JButton btnSalvarStatus;
    public static javax.swing.JButton btnSalvarTipo;
    private javax.swing.JComboBox cmbCor;
    private javax.swing.JComboBox cmbProdutividade;
    public static javax.swing.JMenuItem itmNovoAnalise;
    private javax.swing.JMenuItem itmNovoFinalidade;
    private javax.swing.JMenuItem itmNovoProdutividade;
    private javax.swing.JMenuItem itmNovoStatus;
    private javax.swing.JMenuItem itmNovoTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JMenu mnuArquivo;
    public static javax.swing.JMenu mnuNovoEquipamento;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JPanel pnlAnalises;
    private javax.swing.JPanel pnlEditarAnalise;
    private javax.swing.JPanel pnlEditarFinalidade;
    private javax.swing.JPanel pnlEditarProdutividade;
    private javax.swing.JPanel pnlEditarStatus;
    private javax.swing.JPanel pnlEditarTipo;
    private javax.swing.JPanel pnlFinalidade;
    private javax.swing.JPanel pnlProdutividade;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlTipo;
    private javax.swing.JTable tblAnalise;
    private javax.swing.JTable tblFinalidade;
    private javax.swing.JTabbedPane tblPaneAnalises;
    private javax.swing.JTable tblProdutividade;
    private javax.swing.JTable tblStatus;
    private javax.swing.JTable tblTipo;
    private javax.swing.JTextField txtAnalise;
    private javax.swing.JTextField txtDescricaoAnalise;
    private javax.swing.JTextField txtDescricaoFinalidade;
    private javax.swing.JTextField txtDescricaoProdutividade;
    private javax.swing.JTextField txtDescricaoStatus;
    private javax.swing.JTextField txtDescricaoTipo;
    private javax.swing.JTextField txtFinalidade;
    private javax.swing.JTextField txtPesquisarAnalise;
    private javax.swing.JTextField txtPesquisarFinalidade;
    private javax.swing.JTextField txtPesquisarProdutividade;
    private javax.swing.JTextField txtPesquisarStatus;
    private javax.swing.JTextField txtPesquisarTipo;
    private javax.swing.JTextField txtProdutividade;
    private javax.swing.JTextField txtSiglaAnalise;
    private javax.swing.JTextField txtSiglaFinalidade;
    private javax.swing.JTextField txtSiglaProdutividade;
    private javax.swing.JTextField txtSiglaStatus;
    private javax.swing.JTextField txtSiglaTipo;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
