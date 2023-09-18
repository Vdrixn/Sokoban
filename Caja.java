import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import javax.imageio.ImageIO;

public class Caja extends JPanel{
	private int x;
    private int y;
    private final int d=40;
	private boolean enMeta = false;
	private transient BufferedImage img, imgEnMeta;
	private Stack<Coordenadas> pila = new Stack<Coordenadas>();
	private transient InputStream is, isEnMeta;

	public void setEnMeta(boolean b){
		enMeta=b;
	}

	public Stack<Coordenadas> getPila(){
		return pila;
	}

	public boolean getEnMeta(){
		return enMeta;
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
			enMeta=c.enMeta;
		}
	}

	@Override
	public void paint(Graphics grafico) {
		if(enMeta){
			grafico.drawImage(imgEnMeta, x*d, y*d, null);
		}else grafico.drawImage(img, x*d, y*d, null);
	}

	public void imageInit(){
        is = getClass().getResourceAsStream("Resources/caja.png");
		isEnMeta = getClass().getResourceAsStream("Resources/cajaMeta.png");
        try {
        img = ImageIO.read(is);
		imgEnMeta = ImageIO.read(isEnMeta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * @param x
	 * @param y
	 */
	public Caja(int x, int y) {
		this.x = x;
        this.y = y;
		imageInit();
	}

	public void move(int x, int y) {
		this.x += x;
        this.y += y;
	}
}
