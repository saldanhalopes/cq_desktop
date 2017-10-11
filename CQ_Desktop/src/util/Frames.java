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
package util;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class Frames {

    public static void janela(JInternalFrame fr) throws PropertyVetoException {
        fr.setSelected(true);
        fr.setMaximizable(true);
        fr.setClosable(true);
        fr.setResizable(true);
        fr.setSelected(true);
    }

    public static void centralizar(JInternalFrame fr) throws PropertyVetoException {
        Dimension d = fr.getDesktopPane().getSize();
        fr.setLocation((d.width - fr.getSize().width) / 2, (d.height - fr.getSize().height) / 2);
        fr.setSelected(true);
        fr.setMaximizable(true);
        //fr.setMaximum(true);
        //fr.setIcon(true);
        fr.setClosable(true);
        fr.setResizable(true);
        fr.setSelected(true);
    }

    public static void cascade(JDesktopPane desk) {
        int separation = 25; // distancia entre as janelas
        // Pega todos os frames e organiza, o ultimo fica mais em baido e mais pra cima
        int i = desk.getAllFrames().length; // quantidade de frames
        for (JInternalFrame f : desk.getAllFrames()) {
            f.setLocation(i * separation, i * separation);
            i--; //mutiplicador
        }
    }

    public static void addTelaDesk(JDesktopPane desk, JInternalFrame tela, String value) {
        try {
            for (JInternalFrame frame : desk.getAllFrames()) {
                if (frame.getClass().toString().equalsIgnoreCase(tela.getClass().toString())) {
                    if (frame.getTitle().equals(value)) {
                        frame.moveToFront();
                        frame.setSelected(true);
                        frame.show();
                        desk.repaint();
                        return;
                    }
                }
            }
            desk.add(tela);
            tela.setTitle(value);
            centralizar(tela);
            tela.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
    }

}
