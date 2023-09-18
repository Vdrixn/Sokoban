import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import javax.imageio.ImageIO;

public class WarehouseMan extends JPanel {
    private Stack<Coordenadas> pila = new Stack<Coordenadas>();
    private int x;
    private int y;
    private final int d=40;
    private transient BufferedImage img;
    private transient InputStream is;

    @Override
    public void paint(Graphics grafico) {
        grafico.drawImage(img, x*d, y*d, null);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void undo(){
        if(!pila.empty()){
            Coordenadas c = pila.pop();
            x=c.x;
            y=c.y;
            Interfaz.undo();
        }
	}

    public void imageInit(){
        is = getClass().getResourceAsStream("Resources/player.png");
        try {
            img = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public WarehouseMan(int x, int y) {
        this.x = x;
        this.y = y;
        imageInit();
    }

    public void move(int x, int y) {
		Coordenadas c = new Coordenadas(this.x, this.y,false);
		pila.add(c);
		this.x += x;
        this.y += y;
	}
}