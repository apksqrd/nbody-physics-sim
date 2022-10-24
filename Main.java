// problem from: https://www.cs.princeton.edu/courses/archive/spr22/cos126/assignments/nbody/
// collision formulas: https://en.wikipedia.org/wiki/Elastic_collision


public class Main {
    public static Body[] bodies={null, null, null, null, null, null, null, null, null, null};
    public static int i=0;
    // public static double G=6.67e-11; //real world 
    public static double G=1;
    // public static double scale=3e11;
    public static double scale=2000;

    public static Body createBody(Vector2 pos, Vector2 vel, double m, double r, int[] rgb) {
        Body b = new Body(pos, vel, m, r, rgb);
        bodies[i++]=b;
        return b;
    }

    public static Body createBody(double[] pos, double[] vel, double m, double r, int[] rgb) {
        Body b = new Body(new Vector2(pos), new Vector2(vel), m, r, rgb);
        bodies[i++]=b;
        return b;
    }

    public static void mainLoop() {
        double dTime=2;

        // calculate forces
        //  - reset forces
        for (int j = 0; j < i; j++) {
            bodies[j].resetF();
        }

        //  - newton then superposition
        for (int j = 0; j < i - 1; j++) { //on the last planet everything is alr calculated bc we update both at the same time
            for (int k = j+1; k < i; k++) {
                double r=Math.sqrt(Math.pow(bodies[k].getPos().x-bodies[j].getPos().x, 2) + Math.pow(bodies[k].getPos().y-bodies[j].getPos().y, 2));
                double F=(G*bodies[j].getM()*bodies[k].getM())/Math.pow(r, 2);

                // increase f for j
                bodies[j].incF(F*(bodies[k].getPos().x-bodies[j].getPos().x)/r, F*(bodies[k].getPos().y-bodies[j].getPos().y)/r);

                // increase f for k 
                // calculating F*(bodies[k].getCoords()[0]-bodies[j].getCoords()[0])/r twice is redundant cus we can just make it -
                // but we can save storage this way
                bodies[k].incF(F*(bodies[j].getPos().x-bodies[k].getPos().x)/r, F*(bodies[j].getPos().y-bodies[k].getPos().y)/r);

                if(r<=bodies[j].getR()+bodies[k].getR()){ //collisions
                    // bodies[j].getVel().mult(-1);
                    // bodies[k].getVel().mult(-1);

                    Body.collision(bodies[j], bodies[k]);
                }
            }
        }

        // wall collisions
        for (int j = 0; j < i; j++) { // can put up
            if(-scale>bodies[j].pos.x-bodies[j].r||bodies[j].pos.x+bodies[j].r>scale){
                // System.out.println("wall collision!!!");
                bodies[j].vel.setX(bodies[j].vel.x*-1);
            }

            if(-scale>bodies[j].pos.y-bodies[j].r||bodies[j].pos.y+bodies[j].r>scale){
                // System.out.println("wall collision!!!");
                bodies[j].vel.setY(bodies[j].vel.y*-1);
            }
        }

        // update vel and pos
        for (int j = 0; j < i; j++) {
            // calc acc & increase vel by acc*dTime
            bodies[j].updateVel(dTime);
            bodies[j].updatePos(dTime);
        }

        // draw
        StdDraw.clear();
        for (int j = 0; j < i; j++) {
            StdDraw.setPenColor(bodies[j].getRGB()[0], bodies[j].getRGB()[1], bodies[j].getRGB()[2]);
            StdDraw.filledCircle(bodies[j].getPos().x, bodies[j].getPos().y, bodies[j].getR());
        }
        StdDraw.show();
    }

    public static void printAll(){
        // System.out.println("\n\n\n");
        for (int j = 0; j < i; j++) {
            // System.out.println(j+":");
            // System.out.println("    Force: (" + bodies[j].getF().x + ", " + bodies[j].getF().y + ") ");
            // System.out.println("    Velocity: (" + bodies[j].getVel().x + ", " + bodies[j].getVel().y + ") ");
            // System.out.println("    Position: (" + bodies[j].getPos().x + ", " + bodies[j].getPos().y + ") ");
        }
    }

    public static void main(String[] args) {
        // createBody(new double[] {-1.4960e+11, 0.0000e+00}, new double[] {0.0000e+00, 2.9800e+04}, 5.9740e+24, 1e10, new int[] {0, 0, 0});
        // createBody(new double[] {1.4960e+11, 0.0000e+00}, new double[] {0.0000e+00, 2.9800e+04}, 5.9740e+24, 1e10, new int[] {0, 0, 0});
        // createBody(new double[] {2.2790e+11, 0.0000e+00}, new double[] {0.0000e+00, 2.4100e+04}, 6.4190e+23, 1e10, new int[] {0, 0, 0});
        // createBody(new double[] {5.7900e+10, 0.0000e+00}, new double[] {0.0000e+00, 4.7900e+04}, 3.3020e+23, 1e10, new int[] {0, 0, 0});
        // createBody(new double[] {0.0000e+00, 0.0000e+00}, new double[] {0.0000e+00, 0.0000e+00}, 1.9890e+30, 3e10, new int[] {100, 0, 0});
        // createBody(new double[] {1.0820e+11, 0.0000e+00}, new double[] {0.0000e+00, 3.5000e+04}, 4.8690e+24, 1e10, new int[] {0, 0, 0});

        createBody(new double[] {0, 500}, new double[] {-0.5, 0}, 200, 300, new int[] {100, 0, 0});
        createBody(new double[] {500, 0}, new double[] {0, 3}, 150, 250, new int[] {0, 100, 0});
        createBody(new double[] {-500, 0}, new double[] {0, -0.5}, 100, 200, new int[] {0, 0, 100});
        createBody(new double[] {0, -500}, new double[] {1.5, -2}, 100, 200, new int[] {0, 0, 0});
        createBody(new double[] {100, 1500}, new double[] {-0.5, 0}, 200, 300, new int[] {100, 0, 0});
        createBody(new double[] {1000, 0}, new double[] {0, 3}, 100, 200, new int[] {0, 100, 0});
        createBody(new double[] {-1000, 0}, new double[] {0, -0.5}, 100, 200, new int[] {0, 0, 100});
        createBody(new double[] {0, -1000}, new double[] {1.5, -2}, 100, 200, new int[] {0, 0, 0});

        // createBody(new double[] {0, 500}, new double[] {0, -0}, 400, 300, new int[] {100, 0, 0});
        // createBody(new double[] {0, -500}, new double[] {0, 1}, 200, 300, new int[] {100, 0, 0});

        StdDraw.setScale(-scale, scale);
        StdDraw.enableDoubleBuffering();

        printAll();

        for (int i = 0; i < 4; ) {
            mainLoop();
            printAll();
        }
    }
}