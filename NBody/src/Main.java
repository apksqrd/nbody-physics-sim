// problem from: https://www.cs.princeton.edu/courses/archive/spr22/cos126/assignments/nbody/

public class Main {
    public static Body[] bodies={null, null, null, null, null, null, null, null, null, null};
    public static int i=0;
    public static double G=1;

    public static Body createBody(double[] coords, double[] vels, double m, double r, int[] rgb) {
        Body b = new Body(coords, vels, m, r, rgb);
        bodies[i++]=b;
        return b;
    }

    /*public static Body createBody(int[] coords, int xVel, int yVel, int m, int r) {
        Body b = new Body(coords, xVel, yVel, m, r);
        bodies[i++]=b;
        return b;
    }

    public static Body createBody() {
        Body b = new Body();
        bodies[i++]=b;
        return b;
    }*/

    public static void mainLoop() {
        double dTime=50;

        // calculate forces
        //  - reset forces
        for (int j = 0; j < i; j++) {
            bodies[j].resetF();
        }

        //  - newton then superposition
        for (int j = 0; j < i - 1; j++) { //on the last planet everything is alr calculated bc we update both at the same time
            for (int k = j+1; k < i; k++) {
                double r=Math.sqrt(Math.pow(bodies[k].getCoords()[0]-bodies[j].getCoords()[0], 2) + Math.pow(bodies[k].getCoords()[1]-bodies[j].getCoords()[1], 2));
                double F=(G*bodies[j].getM()*bodies[k].getM())/Math.pow(r, 2);

                // increase f for j
                bodies[j].incF(F*(bodies[k].getCoords()[0]-bodies[j].getCoords()[0])/r, F*(bodies[k].getCoords()[1]-bodies[j].getCoords()[1])/r);

                // increase f for k 
                // calculating F*(bodies[k].getCoords()[0]-bodies[j].getCoords()[0])/r twice is redundant cus we can just make it -
                // but we can save storage this way
                bodies[k].incF(F*(bodies[j].getCoords()[0]-bodies[k].getCoords()[0])/r, F*(bodies[j].getCoords()[1]-bodies[k].getCoords()[1])/r);

                if(r<=bodies[j].getR()+bodies[k].getR()){
                    bodies[j].multVels(-1);
                    bodies[k].multVels(-1);
                }
            }
        }

        // update vels and coords
        for (int j = 0; j < i; j++) {
            // calc acc & increase vel by acc*dTime
            bodies[j].updateVels(dTime);

            // calc pos, should i rename coords to pos?
            bodies[j].updateCoords(dTime);
        }

        // draw
        StdDraw.clear();
        for (int j = 0; j < i; j++) {
            StdDraw.setPenColor(bodies[j].getRGB()[0], bodies[j].getRGB()[1], bodies[j].getRGB()[2]);
            StdDraw.filledCircle(bodies[j].getCoords()[0], bodies[j].getCoords()[1], bodies[j].getR());
        }
        StdDraw.show();
    }

    public static void printAll(){
        System.out.println("\n\n\n");
        for (int j = 0; j < i; j++) {
            System.out.println(j+":");
            System.out.println("Force: (" + bodies[j].getF()[0] + ", " + bodies[j].getF()[1] + ") ");
            System.out.println("Velocity: (" + bodies[j].getVels()[0] + ", " + bodies[j].getVels()[1] + ") ");
            System.out.println("Coordinates: (" + bodies[j].getCoords()[0] + ", " + bodies[j].getCoords()[1] + ") ");
        }
    }

    public static void main(String[] args) {
        // createBody(new double[] {1.4960e+11, 0.0000e+00}, new double[] {0.0000e+00, 2.9800e+04}, 5.9740e+24, 1e10, new int[] {0, 0, 0});
        // createBody(new double[] {2.2790e+11, 0.0000e+00}, new double[] {0.0000e+00, 2.4100e+04}, 6.4190e+23, 1e10, new int[] {0, 0, 0});
        // createBody(new double[] {5.7900e+10, 0.0000e+00}, new double[] {0.0000e+00, 4.7900e+04}, 3.3020e+23, 1e10, new int[] {0, 0, 0});
        // createBody(new double[] {0.0000e+00, 0.0000e+00}, new double[] {0.0000e+00, 0.0000e+00}, 1.9890e+30, 3e10, new int[] {100, 0, 0});
        // createBody(new double[] {1.0820e+11, 0.0000e+00}, new double[] {0.0000e+00, 3.5000e+04}, 4.8690e+24, 1e10, new int[] {0, 0, 0});

        createBody(new double[] {0, 500}, new double[] {-0.5, 0}, 200, 200, new int[] {100, 0, 0});
        createBody(new double[] {500, 0}, new double[] {0, 0.5}, 100, 200, new int[] {0, 100, 0});
        createBody(new double[] {-500, 0}, new double[] {0, -0.5}, 100, 200, new int[] {0, 0, 100});
        createBody(new double[] {0, -500}, new double[] {0.5, 0}, 100, 200, new int[] {0, 0, 0});

        double scale=1000;
        StdDraw.setScale(-scale, scale);
        StdDraw.enableDoubleBuffering();

        printAll();

        for (int i = 0; i < 4; ) {
            mainLoop();
            printAll();
        }
    }
}