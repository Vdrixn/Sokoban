import java.io.Serializable;

public class Coordenadas implements Serializable {
    int x,y;
    boolean enMeta;
    public Coordenadas(int x,int y,boolean b){
        this.x=x;
        this.y=y;
        enMeta=b;
    }
}
