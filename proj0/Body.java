public class Body{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;	
	}
	public double calcDistance(Body body){
		double distance;
		distance = Math.sqrt(Math.pow(body.xxPos - xxPos, 2) + Math.pow(body.yyPos - yyPos, 2));
		return distance;
	}
	public double calcForceExertedBy(Body body){
		double F;
		double G = 6.67 * Math.pow(10, -11);
		F = G * mass * body.mass / Math.pow(calcDistance(body), 2);
		return F;
	}
	public double calcForceExertedByX(Body body){
		double Fx;
		Fx = calcForceExertedBy(body) * (body.xxPos - xxPos) / calcDistance(body);
		return Fx;
	}
	public double calcForceExertedByY(Body body){
		double Fy;
		Fy = calcForceExertedBy(body) * (body.yyPos - yyPos) / calcDistance(body);
		return Fy;
	}
		public double calcNetForceExertedByX(Body[] body){
		double Fx = 0;
		for (int i = 0; i < body.length; i++){
			if (!this.equals(body[i]))
			Fx += calcForceExertedBy(body[i]) * (body[i].xxPos - xxPos) / calcDistance(body[i]);
		}
		return Fx;
	}
	public double calcNetForceExertedByY(Body[] body){
		double Fy = 0;
		for (int i = 0; i < body.length; i++){
			if (!this.equals(body[i]))
			Fy += calcForceExertedBy(body[i]) * (body[i].yyPos - yyPos) / calcDistance(body[i]);
		}
		return Fy;
	}
	public void update(double t, double fx, double fy){
		double ax = fx / mass;
		double ay = fy / mass;
		xxVel = xxVel + t * ax;
		yyVel = yyVel + t * ay;
		xxPos = xxPos + t * xxVel;
		yyPos = yyPos + t * yyVel;
	}
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

}