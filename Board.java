import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Board extends JPanel{
    private int[][] nivel;
    private final int d=40;
    private BufferedImage imgMuro, imgMeta;

    public Board(Nivel n){
        nivel = n.getNivel();
        InputStream muro = getClass().getResourceAsStream("Resources/muro.png");
		InputStream meta = getClass().getResourceAsStream("Resources/meta.png");
        try {
        imgMuro = ImageIO.read(muro);
		imgMeta = ImageIO.read(meta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void paint(Graphics grafico){
        for (int i = 0; i < nivel.length; i++) {
            for (int j = 0; j < nivel[0].length; j++) {
                switch (nivel[i][j]){
                    case 1:
                        grafico.drawImage(imgMuro, j*d, i*d, null);
                        break;
                    case 2:
                        grafico.drawImage(imgMeta, j*d, i*d, null);
                        break;
                }
            }
        }
    }
}