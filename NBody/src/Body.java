public class Body {
    private double[] coords;
    private double[] vels;
    private double m;
    private double r;
    private int[] rgb={0, 0, 0};
    private double[] f={0, 0}; //has no effect to manually set

    public Body(double[] coords, double[] vels, double m, double r, int[] rgb) {
        this.coords=coords;
        this.vels=vels;
        this.m=m;
        this.r=r;
        this.rgb=rgb;
    }

    /*public Body(int x, int y, int xVel, int yVel, int m, int r, int red, int green, int blue) {
        this.x=x;
        this.y=y;
        this.xVel=xVel;
        this.yVel=yVel;
        this.m=m;
        this.r=r;
        this.rgb[0]=red;
        this.rgb[1]=green;
        this.rgb[2]=blue;
    }

    public Body(int x, int y, int xVel, int yVel, int m, int r) {
        this.x=x;
        this.y=y;
        this.xVel=xVel;
        this.yVel=yVel;
        this.m=m;
        this.r=r;
    }

    public Body() {
        this(0, 0, 0, 0, 1, 1);
    }*/

    public double[] getCoords() {
        return coords;
    }

    public double[] getVels() {
        return vels;
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

    public double[] getF() {
        return f;
    }

    // public void incCoords(double[] coords) {
    //     this.coords[0]+=coords[0];
    //     this.coords[1]+=coords[1];
    // }

    // public void incCoords(double x, double y) {
    //     this.coords[0]+=x;
    //     this.coords[1]+=y;
    // }

    // public void incVels(double[] vels) {
    //     this.vels[0]+=vels[0];
    //     this.vels[1]+=vels[1];
    // }

    // public void incVels(double x, double y) {
    //     this.vels[0]+=x;
    //     this.vels[1]+=y;
    // }

    public void updateCoords(double dTime) {
        coords[0]+=dTime*vels[0];
        coords[1]+=dTime*vels[1];
    }

    public void updateVels(double dTime) {
        vels[0]+=dTime*f[0]/m;
        vels[1]+=dTime*f[1]/m;
    }

    // public void multCoords(double multiplier) {
    //     coords[0]*=multiplier;
    //     coords[1]*=multiplier;
    // }

    public void multVels(double multiplier) {
        vels[0]*=multiplier;
        vels[1]*=multiplier;
    }

    public void resetF() {
        f[0]=0;
        f[1]=0;
    }

    public void incF(double[] f) {
        this.f[0]+=f[0];
        this.f[1]+=f[1];
    }

    public void incF(double x, double y) {
        this.f[0]+=x;
        this.f[1]+=y;
    }
}