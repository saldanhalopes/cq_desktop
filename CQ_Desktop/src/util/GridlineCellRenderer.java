package util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rafael.lopes
 */
public class GridlineCellRenderer extends DefaultTableCellRenderer {

    private int vermelho;
    private int verde;
    private int laranja;
    private int azul;
    private int amarelo;
    private int cinza;
    private int roxo;
    private int rosa;
    private int preto;
    private int coluna;

    public GridlineCellRenderer(int red, int green, int orange,
            int blue, int yellow, int gray, int magenta, int pink, int black) {
        vermelho = red;
        verde = green;
        laranja = orange;
        azul = blue;
        amarelo = yellow;
        cinza = gray;
        roxo = magenta;
        rosa = pink;
        preto = black;
    }

    public GridlineCellRenderer(int col) {
        coluna = col;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (table.getModel().getValueAt(row, coluna).equals("Vermelho")) {
            setBackground(Color.red.darker());
            setForeground(Color.white);
        } else if (table.getModel().getValueAt(row, coluna).equals("Verde")) {
            setBackground(Color.green.darker());
            setForeground(Color.white);
        } else if (table.getModel().getValueAt(row, coluna).equals("Laranja")) {
            setBackground(Color.orange.darker());
            setForeground(Color.white);
        } else if (table.getModel().getValueAt(row, coluna).equals("Azul")) {
            setBackground(Color.blue.darker());
            setForeground(Color.white);
        } else if (table.getModel().getValueAt(row, coluna).equals("Amarelo")) {
            setBackground(Color.yellow);
            setForeground(Color.black);
        } else if (table.getModel().getValueAt(row, coluna).equals("Cinza")) {
            setBackground(Color.gray.darker());
            setForeground(Color.white);
        } else if (table.getModel().getValueAt(row, coluna).equals("Roxo")) {
            setBackground(Color.magenta.darker());
            setForeground(Color.white);
        } else if (table.getModel().getValueAt(row, coluna).equals("Rosa")) {
            setBackground(Color.pink.darker());
            setForeground(Color.white);
        } else if (table.getModel().getValueAt(row, coluna).equals("Preto")) {
            setBackground(Color.black);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
