import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Nivel implements Serializable{

	private int nNivel;
	private String nombre;
	private int levelPunct;
	private int punct;
	private int nFilas;
	private int nColumnas;
	private int nCajas;
	private int nMetas;
	private int nPlayers;
	private int[][] nivel;
	private ArrayList<Caja> cajas;
	private WarehouseMan player;

	public int getNFilas(){
		return nFilas;
	}

	public int getNColumnas(){
		return nColumnas;
	}

	public void setNNivel(int nNivel) {
		this.nNivel = nNivel;
	}
	
	public int getNNivel() {
		return nNivel;
	}

	public void setPuct(int p){
		this.punct=p;
	}

	public int getPunct(){
		return punct;
	}

	public void setLevelPunct(int p){
		this.levelPunct=p;
	}

	public int getLevelPunct(){
		return levelPunct;
	}

	public int getNCajas() {
		return nCajas;
	}

	public int getNMetas() {
		return nMetas;
	}

	public int getNPlayers() {
		return nPlayers;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int[][] getNivel() {
		return nivel;
	}

	public ArrayList<Caja> getCajas() {
		return cajas;
	}

	public WarehouseMan getWarehouseMan() {
		return player;
	}

	public void saveGame(){
		try{
			FileOutputStream fileOut = new FileOutputStream("Resources/savedGame");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}catch(IOException i){
			i.printStackTrace();
		}
	}

	public void loadGame(){
		try{
			FileInputStream fileIn = new FileInputStream("Resources/savedGame");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Nivel n = (Nivel) in.readObject();
			this.nombre=n.getNombre();
			this.levelPunct=n.getLevelPunct();
			this.punct=n.getPunct();
			this.nFilas=n.getNFilas();
			this.nColumnas=n.getNColumnas();
			this.nCajas=n.getNCajas();
			this.nMetas=n.getNMetas();
			this.nPlayers=n.getNPlayers();
			this.nNivel=n.getNNivel();
			this.nivel=n.getNivel();
			this.cajas=n.getCajas();
			cajasImageInit();
			this.player=n.getWarehouseMan();
			player.imageInit();
			in.close();
			fileIn.close();
		}catch(IOException i){
			i.printStackTrace();
		}catch(ClassNotFoundException c){
			System.out.println("Nivel class not found");
			c.printStackTrace();
		}
	}

	private void cajasImageInit(){
		for(Caja c: cajas){
			c.imageInit();;
		}
	}

	public void undoMovement() {
		player.undo();
		for(Caja c: cajas){
			c.undo();
		}
	}

	public void movePlayer(int keyCode) {
		int y = player.getY();
		int x = player.getX();
		Caja c;
		if (keyCode == 38) {
			c=getCaja(x, y-1);
			// Código a ejecutar cuando se presiona la flecha hacia arriba.
			if(nivel[y-1][x]!=1){
				if(c==null){
					player.move(0, -1);
					Interfaz.move();
					
				}else{
					moveCaja(keyCode, c);
				}
			}
		} else if (keyCode == 40) {
			c=getCaja(x, y+1);
			// Código a ejecutar cuando se presiona la flecha hacia abajo.
			if(nivel[y+1][x]!=1){
				if(c==null){
					player.move(0, 1);
					Interfaz.move();
					
				}else{
					moveCaja(keyCode, c);
				}
			}
		} else if (keyCode == 37) {
			c=getCaja(x-1, y);
			// Código a ejecutar cuando se presiona la flecha hacia la izquierda.
			if(nivel[y][x-1]!=1){
				if(c==null){
					player.move(-1, 0);
					Interfaz.move();
					
				}else{
					moveCaja(keyCode, c);
				}
			}
		} else if (keyCode == 39) {
			c=getCaja(x+1, y);
			// Código a ejecutar cuando se presiona la flecha hacia la derecha.
			if(nivel[y][x+1]!=1){
				if(c==null){
					player.move(1, 0);
					Interfaz.move();
					
				}else{
					moveCaja(keyCode, c);
				}
			}
		}
	}

	private void finNivel(){
		for(Caja c: cajas){
			if(!c.getEnMeta()){
				return;
			}
		}
		Interfaz.siguienteNivel();
	}

	public void moveCaja(int keyCode,Caja c) {
		int x=c.getX();
		int y=c.getY();
		if (keyCode == 38) {
			// Código a ejecutar cuando se presiona la flecha hacia arriba.
			if((nivel[y-1][x]!=1)&&getCaja(x, y-1)==null){
				if(nivel[y-1][x]==2){
					player.move(0, -1);
					Interfaz.move();
					
					c.setEnMeta(true);
					c.move(0, -1);
				}else {
					player.move(0, -1);
					Interfaz.move();
					
					c.setEnMeta(false);
					c.move(0, -1);
				}
				finNivel();
			}
		} else if (keyCode == 40) {
			// Código a ejecutar cuando se presiona la flecha hacia abajo.
			if((nivel[y+1][x]!=1)&&getCaja(x, y+1)==null){
				if(nivel[y+1][x]==2){
					player.move(0, 1);
					Interfaz.move();
					
					c.setEnMeta(true);
					c.move(0, 1);
				}else{
					player.move(0, 1);
					Interfaz.move();
					
					c.setEnMeta(false);
					c.move(0, 1);
				} 
				
				finNivel();
			}
		} else if (keyCode == 37) {
			// Código a ejecutar cuando se presiona la flecha hacia la izquierda.
			if((nivel[y][x-1]!=1)&&getCaja(x-1, y)==null){
				if(nivel[y][x-1]==2){
					player.move(-1, 0);
					Interfaz.move();
					
					c.setEnMeta(true);
					c.move(-1, 0);
				}else{
					player.move(-1, 0);
					Interfaz.move();
					
					c.setEnMeta(false);
					c.move(-1, 0);
				}
				finNivel();
			}
			
		} else if (keyCode == 39) {
			// Código a ejecutar cuando se presiona la flecha hacia la derecha.
			if((nivel[y][x+1]!=1)&&getCaja(x+1, y)==null){
				if(nivel[y][x+1]==2){
					player.move(1, 0);
					Interfaz.move();
					
					c.setEnMeta(true);
					c.move(1, 0);
				}else{
					player.move(1, 0);
					Interfaz.move();
					
					c.setEnMeta(false);
					c.move(1, 0);
				}
				finNivel();
			}
		}
	}

	public Caja getCaja(int x, int y){
		for(Caja c: cajas){
			if(c.getX()==x&&c.getY()==y){
				return c;
			}
		}
		return null;
	}

	/**
	 * @param n
	 */
	public int cargarNivel(int n) {
		cajas = new ArrayList<>();
		try {
			// crea un FileReader para leer el archivo de texto
			FileReader fr = new FileReader("Resources/nivel_" + n + ".txt");

			// crea un BufferedReader para leer el archivo línea por línea
			BufferedReader br = new BufferedReader(fr);

			// lee cada línea del archivo de texto
			String linea;
			nombre = br.readLine();
			linea = br.readLine();
			String[] partes = linea.split(" ");
			nFilas = Integer.parseInt(partes[0]);
			nColumnas = Integer.parseInt(partes[1]);

			if (nFilas == 0 || nColumnas == 0) {
				br.close();
				fr.close();
				return -1;
			} else {
				nivel = new int[nFilas][nColumnas];
			}

			int i = 0;
			while ((linea = br.readLine()) != null) {
				// agrega los valores a la matriz laberintoTexto
				for (int j = 0; j < linea.length(); j++) {
					if (String.valueOf(linea.charAt(j)).equals("+")) {
						nivel[i][j] = 1;
					} else
						nivel[i][j] = 0;

					switch (String.valueOf(linea.charAt(j))) {
						case "+":
							nivel[i][j] = 1;
							break;
						case "W":
							player = new WarehouseMan(j, i);
							nPlayers++;
							break;
						case "#":
							cajas.add(new Caja(j, i));
							nCajas++;
							break;
						case "*":
							nivel[i][j] = 2;
							nMetas++;
							break;
						default:
							nivel[i][j] = 0;
							break;
					}
				}
				i++;
			}

			// cierra los objetos FileReader y BufferedReader
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			Sokoban.mensajeFin();
		} catch (IOException e) {
			// manejar la excepción si hay un error al leer el archivo
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
		return 1;
	}

	
}