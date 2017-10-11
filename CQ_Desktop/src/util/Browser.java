/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author rafael.lopes
 */
public class Browser {
    public static void openBrowser(String url) {
        Desktop desktop = null;
        //Primeiro verificamos se é possível a integração com o desktop   
        if (!Desktop.isDesktopSupported()) {
            throw new IllegalStateException("Recursos de área de trabalho não suportados!");
        }
        desktop = Desktop.getDesktop();
        //Agora vemos se é possível disparar o browser default.   
        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new IllegalStateException("Nenhum conjunto de navegador padrão!");
        }
        //Pega a URI de um componente de texto.   
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        //Dispara o browser default, que pode ser o Explorer, Firefox ou outro.   
        try {
            desktop.browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
