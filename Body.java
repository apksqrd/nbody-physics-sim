public class Body {
    public Vector2 pos;
    public Vector2 vel;
    public Vector2 f=new Vector2(); //has no effect at start
    public double m;
    public double r;
    public int[] rgb;

    public Body(Vector2 pos, Vector2 vel, double m, double r, int[] rgb) {
        this.pos=pos;
        this.vel=vel;
        this.m=m;
        this.r=r;
        this.rgb=rgb;
    }

    //no need for get set because public but useful (bc old code)
    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getVel() {
        return vel;
    }

    public Vector2 getF() {
        return f;
    }

    public double getM() {
        return m;
    }

    public double getR() {
        return r;
    }

    public int[] getRGB() {
        return rgb;
    }

    public void updatePos(double dTime) {
        pos.inc(vel.prod(dTime));
    }

    public void updateVel(double dTime) {
        vel.inc(f.prod(dTime/m));
    }

    public void resetF() {
        f=new Vector2();
    }

    public void incF(Vector2 other) {
        f.inc(other);
    }

    public void incF(double x, double y) {
        f.inc(x, y);
    }

    // public void incF(double[] xy) {
    //     f.inc(xy);
    // }

    public Vector2 velAfterCollision(Body other) {
        return this.vel.dif(this.pos.dif(other.pos).prod((2*other.m/(this.m+other.m))*(this.vel.dif(other.vel).dotProd(this.pos.dif(other.pos))/this.pos.dif(other.pos).distSquare())));
    }

    public static void collision(Body b1, Body b2) {
        Vector2 newV1, newV2;
        newV1=b1.velAfterCollision(b2);
        newV2=b2.velAfterCollision(b1);
        b1.vel=newV1;
        b2.vel=newV2;
    }
}
