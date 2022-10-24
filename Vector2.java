public class Vector2 { //was tempted to add <T> but that would make it annoying to write every time
    public double x, y;
    
    public Vector2(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public Vector2() {
        this.x=0;
        this.y=0;
    }

    public Vector2(double[] xy) {
        //no for loop bc only 2
        this.x=xy[0];
        this.y=xy[1];
    }

    public double[] get() { //might be useful
        return new double[] {this.x, this.y};
    }

    public void set(Vector2 other) {
        this.x=other.x;
        this.y=other.y;
    }

    public void set(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public void setX(double x) {
        this.x=x;
    }

    public void setY(double y) {
        this.y=y;
    }

    // should have (Vector2) and (x, y) inputs for everything

    public void inc(Vector2 other) {
        this.x+=other.x;
        this.y+=other.y;
    }

    public void inc(double otherX, double otherY) {
        this.x+=otherX;
        this.y+=otherY;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x+other.x, this.y+other.y);
    }

    public Vector2 add(double otherX, double otherY) {
        return new Vector2(this.x+otherX, this.y+otherY);
    }

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x+v2.x, v1.y+v2.y);
    }

    public Vector2 dif(Vector2 other) {
        return new Vector2(this.x-other.x, this.y-other.y);
    }

    public Vector2 dif(double otherX, double otherY) {
        return new Vector2(this.x-otherX, this.y-otherY);
    }

    public static Vector2 dif(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x-v2.x, v1.y-v2.y);
    }

    public Vector2 prod(double scalar) {
        return new Vector2(this.x*scalar, this.y*scalar);
    }

    public void mult(double scalar) {
        this.x*=scalar;
        this.y*=scalar;
    }

    public double dotProd(Vector2 other) {
        return this.x*other.x+this.y*other.y;
    }

    public double dotProd(double otherX, double otherY) {
        return this.x*otherX+ this.y*otherY;
    }

    public double dotProd(Vector2 v1, Vector2 v2) {
        return v1.x*v2.x+v1.y*v2.y;
    }

    public double distSquare() {
        return Math.pow(this.x, 2)+Math.pow(this.y, 2);
    }

    public static double distSquare(Vector2 v) {
        return Math.pow(v.x, 2)+Math.pow(v.y, 2);
    }
}
