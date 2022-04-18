public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;	
	}
	public double calcDistance(Planet body){
		double distance;
		distance = Math.sqrt(Math.pow(body.xxPos - xxPos, 2) + Math.pow(body.yyPos - yyPos, 2));
		return distance;
	}
	public double calcForceExertedBy(Planet body){
		double F;
		double G = 6.67 * Math.pow(10, -11);
		F = G * mass * body.mass / Math.pow(calcDistance(body), 2);
		return F;
	}
	public double calcForceExertedByX(Planet body){
		double Fx;
		Fx = calcForceExertedBy(body) * (body.xxPos - xxPos) / calcDistance(body);
		return Fx;
	}
	public double calcForceExertedByY(Planet body){
		double Fy;
		Fy = calcForceExertedBy(body) * (body.yyPos - yyPos) / calcDistance(body);
		return Fy;
	}
		public double calcNetForceExertedByX(Planet[] body){
		double Fx = 0;
		for (int i = 0; i < body.length; i++){
			if (!this.equals(body[i]))
			Fx += calcForceExertedBy(body[i]) * (body[i].xxPos - xxPos) / calcDistance(body[i]);
		}
		return Fx;
	}
	public double calcNetForceExertedByY(Planet[] body){
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