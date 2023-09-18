import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Interfaz extends JFrame {
	private static int nivel=1;
	private static int levelPunct=0;
	private static int punct=0;
	private static JPanel menu;
	private JButton newGameButton,restartButton,undoButton,saveButton,opeButton,closeButton;
	private static JPanel areaJuego;
	private static Juego juego;
	private static JLabel titulo,levelPunctuation,punctuation;
	private static GridBagConstraints constraints;
	static Nivel niv;

	public Interfaz() {
		niv=new Nivel();
		niv.cargarNivel(nivel);
		juego = new Juego(niv);
	}

	public int getPunct(){
		return punct;
	}

	public static void undo(){
		levelPunct--;
		menu.remove(levelPunctuation);
		levelPunctuation = new JLabel("Level Punctuation: "+levelPunct, SwingConstants.CENTER);
        constraints.gridy = 1;
        menu.add(levelPunctuation, constraints);
		menu.revalidate();
		menu.repaint();
	}

	public static void move(){
		levelPunct++;
		menu.remove(levelPunctuation);
		levelPunctuation = new JLabel("Level Punctuation: "+levelPunct, SwingConstants.CENTER);
        constraints.gridy = 1;
        menu.add(levelPunctuation, constraints);
		menu.revalidate();
		menu.repaint();
		ArrayList<Caja> cajas = niv.getCajas();
		for(Caja c: cajas){
			Stack<Coordenadas> pila = c.getPila();
			if(pila.size()<levelPunct){
				Coordenadas coord = new Coordenadas(c.getX(), c.getY(), c.getEnMeta());
				pila.add(coord);
				c.move(0, 0);
			}
		}
	}

	public static void restartLevel(){
		levelPunct=0;
		niv.cargarNivel(nivel);

		menu.remove(punctuation);
		punctuation = new JLabel("Total Puntuation: "+punct, SwingConstants.CENTER);
        constraints.gridy = 2;
        menu.add(punctuation, constraints);

		menu.remove(levelPunctuation);
		levelPunctuation = new JLabel("Level Punctuation: "+levelPunct, SwingConstants.CENTER);
        constraints.gridy = 1;
        menu.add(levelPunctuation, constraints);
		menu.revalidate();
		menu.repaint();

		// Eliminar el panel Juego existente
		areaJuego.remove(juego);

		// Crear un nuevo panel Juego con el nivel actualizado
		juego = new Juego(niv);
	
		// Actualizar las dimensiones y la posición del nuevo panel Juego
		int tamX = juego.getNivel().getNivel()[0].length * 40;
		int tamY = juego.getNivel().getNivel().length * 40;
		juego.setBounds((areaJuego.getWidth() - tamX) / 2, (areaJuego.getHeight() - tamY) / 2, tamX, tamY);
	
		// Agregar el nuevo panel Juego al área de juego
		areaJuego.add(juego);
	
		// Actualizar el componente visual
		areaJuego.revalidate();
		areaJuego.repaint();
	}

	public static void restartGame(){
		levelPunct=0;
		punct=0;
		nivel=1;
		niv.cargarNivel(nivel);
		
		menu.remove(titulo);
		titulo = new JLabel("Level "+nivel, SwingConstants.CENTER);
        constraints.gridy = 0;
        menu.add(titulo, constraints);

		menu.remove(punctuation);
		punctuation = new JLabel("Total Puntuation: "+punct, SwingConstants.CENTER);
        constraints.gridy = 2;
        menu.add(punctuation, constraints);

		menu.remove(levelPunctuation);
		levelPunctuation = new JLabel("Level Punctuation: "+levelPunct, SwingConstants.CENTER);
        constraints.gridy = 1;
        menu.add(levelPunctuation, constraints);
		menu.revalidate();
		menu.repaint();
		// Eliminar el panel Juego existente
		areaJuego.remove(juego);

		// Crear un nuevo panel Juego con el nivel actualizado
		juego = new Juego(niv);
	
		// Actualizar las dimensiones y la posición del nuevo panel Juego
		int tamX = juego.getNivel().getNivel()[0].length * 40;
		int tamY = juego.getNivel().getNivel().length * 40;
		juego.setBounds((areaJuego.getWidth() - tamX) / 2, (areaJuego.getHeight() - tamY) / 2, tamX, tamY);
	
		// Agregar el nuevo panel Juego al área de juego
		areaJuego.add(juego);
	
		// Actualizar el componente visual
		areaJuego.revalidate();
		areaJuego.repaint();
	}

	public static void siguienteNivel(){
		punct+=levelPunct;
		levelPunct=0;
		nivel++;
		niv.cargarNivel(nivel);
		// Eliminar el panel Juego existente
		areaJuego.remove(juego);

		menu.remove(titulo);
		titulo = new JLabel("Level "+nivel, SwingConstants.CENTER);
        constraints.gridy = 0;
        menu.add(titulo, constraints);

		menu.remove(punctuation);
		punctuation = new JLabel("Total Puntuation: "+punct, SwingConstants.CENTER);
        constraints.gridy = 2;
        menu.add(punctuation, constraints);

		menu.remove(levelPunctuation);
		levelPunctuation = new JLabel("Level Punctuation: "+levelPunct, SwingConstants.CENTER);
        constraints.gridy = 1;
        menu.add(levelPunctuation, constraints);
		menu.revalidate();
		menu.repaint();
		// Crear un nuevo panel Juego con el nivel actualizado
		juego = new Juego(niv);
	
		// Actualizar las dimensiones y la posición del nuevo panel Juego
		int tamX = juego.getNivel().getNivel()[0].length * 40;
		int tamY = juego.getNivel().getNivel().length * 40;
		juego.setBounds((areaJuego.getWidth() - tamX) / 2, (areaJuego.getHeight() - tamY) / 2, tamX, tamY);
	
		// Agregar el nuevo panel Juego al área de juego
		areaJuego.add(juego);
	
		// Actualizar el componente visual
		areaJuego.revalidate();
		areaJuego.repaint();
	}

	public JPanel getJuego() {
		return Interfaz.juego;
	}

	public void paint() {
		setSize(700, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Sokoban");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		menu = new JPanel(new GridBagLayout());
		menu.setBounds(getWidth() * 3 / 4, 0, getWidth() * 1 / 4, getHeight());
		menu.setBackground(Color.lightGray);
		constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		add(menu);

		titulo = new JLabel("Level "+nivel, SwingConstants.CENTER);
        constraints.gridy = 0;
        menu.add(titulo, constraints);

		levelPunctuation = new JLabel("Level Punctuation: "+levelPunct, SwingConstants.CENTER);
        constraints.gridy = 1;
        menu.add(levelPunctuation, constraints);

		punctuation = new JLabel("Total Puntuation: "+punct, SwingConstants.CENTER);
        constraints.gridy = 2;
        menu.add(punctuation, constraints);

		newGameButton = new JButton("Start new Game");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
				requestFocusInWindow();
            }
        });
        constraints.gridy = 3;
        menu.add(newGameButton, constraints);

		restartButton = new JButton("Restart Level");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				restartLevel();
				requestFocusInWindow();
			}
        });
        constraints.gridy = 4;
        menu.add(restartButton, constraints);

		undoButton = new JButton("Undo Movement");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				niv.undoMovement();
				requestFocusInWindow();
			}
        });
        constraints.gridy = 5;
        menu.add(undoButton, constraints);

		saveButton = new JButton("Save Game");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				niv.setLevelPunct(levelPunct);
				niv.setPuct(punct);
				niv.setNNivel(nivel);
				niv.saveGame();
				requestFocusInWindow();
			}
        });
        constraints.gridy = 6;
        menu.add(saveButton, constraints);

		opeButton = new JButton("Open Saved Game");
        opeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				niv.loadGame();
				levelPunct = niv.getLevelPunct();
				punct = niv.getPunct();
				nivel = niv.getNNivel();
				// Eliminar el panel Juego existente
				areaJuego.remove(juego);

				menu.remove(titulo);
				titulo = new JLabel("Level "+nivel, SwingConstants.CENTER);
				constraints.gridy = 0;
				menu.add(titulo, constraints);

				menu.remove(punctuation);
				punctuation = new JLabel("Total Puntuation: "+punct, SwingConstants.CENTER);
				constraints.gridy = 2;
				menu.add(punctuation, constraints);

				menu.remove(levelPunctuation);
				levelPunctuation = new JLabel("Level Punctuation: "+levelPunct, SwingConstants.CENTER);
				constraints.gridy = 1;
				menu.add(levelPunctuation, constraints);
				menu.revalidate();
				menu.repaint();
				// Crear un nuevo panel Juego con el nivel actualizado
				juego = new Juego(niv);
			
				// Actualizar las dimensiones y la posición del nuevo panel Juego
				int tamX = juego.getNivel().getNivel()[0].length * 40;
				int tamY = juego.getNivel().getNivel().length * 40;
				juego.setBounds((areaJuego.getWidth() - tamX) / 2, (areaJuego.getHeight() - tamY) / 2, tamX, tamY);
			
				// Agregar el nuevo panel Juego al área de juego
				areaJuego.add(juego);
			
				// Actualizar el componente visual
				areaJuego.revalidate();
				areaJuego.repaint();
				requestFocusInWindow();
			}
        });
        constraints.gridy = 7;
        menu.add(opeButton, constraints);

		closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        });
        constraints.gridy = 8;
        menu.add(closeButton, constraints);

		areaJuego = new JPanel();
		areaJuego.setLayout(null);
		areaJuego.setBounds(0, 0, getWidth() * 3 / 4, getHeight());
		areaJuego.setBackground(Color.darkGray);
		add(areaJuego);

		juego = new Juego(niv);
		int tamX = juego.getNivel().getNivel()[0].length * 40;
		int tamY = juego.getNivel().getNivel().length * 40;
		juego.setBounds((areaJuego.getWidth() - tamX) / 2, (areaJuego.getHeight() - tamY) / 2, tamX, tamY);
		juego.setBackground(Color.lightGray);
		areaJuego.add(juego);
	}
}