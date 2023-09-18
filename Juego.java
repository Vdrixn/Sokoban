import javax.swing.JPanel;
import java.awt.Graphics;

public class Juego extends JPanel {
    private Nivel nivel;

    public Juego(Nivel n){
        nivel = n;
    }
    public Nivel getNivel(){
        return nivel;
    }

    @Override
    public void paint(Graphics grafico){
        (new Board(nivel)).paint(grafico);
        nivel.getWarehouseMan().paint(grafico);
        for(Caja c: nivel.getCajas()){
            c.paint(grafico);
        }
    }
}
