
package view.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author rafael.lopes
 */
public class NewClass {
    
    private Timer timer;
    private int currentSegundo = 0;
    private int currentMinuto = 0;
    private int currentHora = 0;
    private int velocidade = 1000;
    private String tempo;

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public NewClass(int Hora, int Minuto, int Segundo){
        currentHora = Hora;
        currentMinuto = Minuto;
        currentSegundo = Segundo;
        iniciarCintagem();
    }
    
    private void iniciarCintagem() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSegundo--;

                if (currentSegundo == -1) {
                    currentMinuto--;
                    currentSegundo = 59;
                }

                if (currentMinuto == -1) {
                    currentHora--;
                    currentMinuto = 59;
                }

                if (currentHora == 0 && currentMinuto == 0 && currentSegundo == 00) {
                    stopTime();
                }

                String hr = currentHora <= 9 ? "0" + currentHora : currentHora + "";
                String min = currentMinuto <= 9 ? "0" + currentMinuto : currentMinuto + "";
                String seg = currentSegundo <= 9 ? "0" + currentSegundo : currentSegundo + "";

                setTempo(hr + ":" + min + ":" + seg);
            }
        };
        this.timer = new Timer(velocidade, action);
        this.timer.start();
    }

    public void stopTime() {
        timer.stop();
        currentHora = 0;
        currentMinuto = 0;
        currentSegundo = 0;
        setTempo("00:00:00");
    }
    
    public void restartTime() {
        timer.restart();
    }
}
