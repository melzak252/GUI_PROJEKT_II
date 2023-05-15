package projekt.util;

public class MoveVector {
    public double x = 0.0;
    public double y = 0.0;
    public double z = 0.0;

    public MoveVector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MoveVector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public MoveVector(){
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
}
