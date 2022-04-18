public class NBody{

	public static double readRadius (String st){
		In in = new In(st);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}
	public static Planet[] readPlanets (String st){
		In in = new In(st);
		int nums = in.readInt();
		double radius = in.readDouble();
		Planet[] res = new Planet[nums];
		for (int i = 0; i < nums; i++){
			res[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
				in.readDouble(),in.readDouble(),in.readString());
		}
		return res;
	}
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In in = new In(filename);
		Planet[] bodies = NBody.readPlanets
		(filename);
		double Radius = readRadius(filename);

		StdDraw.setScale(-Radius, Radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (int i = 0; i < bodies.length; i++){
			bodies[i].draw();
		}

		StdDraw.enableDoubleBuffering();

		double time = 0;

		for (int i = 0; i < T; i += dt){
			double[] xForces = new double[bodies.length];
			for (int j = 0; j < xForces.length; j++){
				xForces[j] = bodies[j].calcNetForceExertedByX(bodies);
			}

			double[] yForces = new double[bodies.length];
			for (int j = 0; j < yForces.length; j++){
				yForces[j] = bodies[j].calcNetForceExertedByY(bodies);
			}

			for (int j = 0; j < bodies.length; j++){
				bodies[j].update(dt, xForces[j], yForces[j]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (int j = 0; j < bodies.length; j++){
				bodies[j].draw();

			StdDraw.show();
			StdDraw.pause(10);
			}			
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", Radius);
		for (int i = 0; i < bodies.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}

	}

}	