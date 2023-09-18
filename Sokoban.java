import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Sokoban {
    private static Interfaz ventana;

    public static void mensajeFin(){
        JOptionPane.showMessageDialog(null, "Has ganado.\nTu puntuación final es: " + ventana.getPunct()+ " \n :)");
        System.exit(0);
    }
    public static void main(String[] args) {
        ventana = new Interfaz();
        ventana.paint();
        ventana.setVisible(true);
        ventana.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if((e.getKeyChar() == 0x1a)) {
                    Interfaz.niv.undoMovement();
                }
            }


            @Override
            public void keyPressed(KeyEvent e) {
                // Este método se llama cuando se presiona una tecla.
                int keyCode = e.getKeyCode();
                System.out.println();
                Interfaz.niv.movePlayer(keyCode);
            }

            public void keyReleased(KeyEvent e) {
            }
        });
        ventana.setFocusable(true);

        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            ventana.repaint();
        }
    }
}
