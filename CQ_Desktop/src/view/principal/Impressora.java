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
package view.principal;

import javax.swing.JTextArea;

/**
 *
 * @author rafael.lopes
 */
public class Impressora {

    private JTextArea txtDestino = null;

    private long linha = 0;

    private boolean pausado = false;

    public Impressora(JTextArea txtDestino) {
        if (txtDestino == null) {
            throw new NullPointerException("Destino não pode ser nulo!");
        }

        this.txtDestino = txtDestino;

        //Disparamos a thread da impressora.
        Thread t = new Thread(new ImpressoraRun(),
                "Thread da impressora");
        t.setDaemon(true);
        t.start();
    }

    /**
     * Nesse método, verificamos a condição que desejamos. Se a variável pausada
     * valer true, isso nos indica que a thread deve dormir. Portanto, damos um
     * wait() nela. Caso contrário, ela deve continuar.
     */
    private synchronized void verificaPausa()
            throws InterruptedException {
            // Esse while é necessário pois threads estão sujeitas a spurious
        // wakeups, ou seja, elas podem acordar mesmo que nenhum notify       
        // tenha sido dado.

        // Whiles diferentes podem ser usados para descrever condições
        // diferentes. Você também pode ter mais de uma condição no while
        // associada com um e. Por exemplo, no caso de um
        //  produtor/consumidor, poderia ser while 
        // (!pausado && !fila.cheia()).
        // Nesse caso só temos uma condição, que é dormir quando pausado.
        while (pausado) {
            wait();
        }
    }

    /**
     * Nesse método, permitimos a quem quer que use a impressora que controle
     * sua thread. Definindo pausado como true, essa thread irá parar e esperar
     * indefinidamente. Caso pausado seja definido como false, a impressora
     * volta a imprimir.
     */
    public synchronized void setPausado(boolean pausado) {
        this.pausado = pausado;
        // Caso pausado seja definido como false, acordamos a thread e pedimos
        // para ela verificar sua condição. Nesse caso, sabemos que a thread
        // acordará, mas no caso de uma condição com várias alternativas, nem
        // sempre isso seria verdadeiro.
        if (!this.pausado) {
            notifyAll();
        }
    }

    private void imprime() {
        StringBuilder msg = new StringBuilder("Linha ");
        msg.append(Long.toString(linha++ % Long.MAX_VALUE));
        msg.append("\n");
        txtDestino.append(msg.toString());
    }

    /**
     * Este é o runnable com a thread da impressora.
     *
     */
    private class ImpressoraRun implements Runnable {

        public void run() {
            try {
                while (true) {
                    verificaPausa();
                    imprime();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                txtDestino.append(
                        "Processamentoda impressora interrompido.");
            }
        }
    }
}
