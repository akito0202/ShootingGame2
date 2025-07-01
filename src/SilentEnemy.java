
public class SilentEnemy extends Enemy{
	public void draw(MyFrame f) {
		//f.setColor(0, 0, 0);
		//f.fillOval(x, y, 30, 30);
		f.setColor(255, 255, 255);
		f.fillOval(x+5, y, 20, 30);
		}
	public SilentEnemy(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
		life=3;
	}
	public void move() {
		super.move();
		vy=vy+1;
}
}