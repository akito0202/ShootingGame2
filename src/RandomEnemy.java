
public class RandomEnemy extends Enemy{
public void draw(MyFrame f) {
		
		f.setColor(0, 200, 0);
		f.fillRect(x, y, 10, 10);
		f.fillRect(x+10, y-20, 10, 20);
		f.fillRect(x-10, y-20, 10, 20);
}
	public RandomEnemy(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
		life=2;
	}
	public void move() {
		super.move();
		vx=Math.random()*6-2;
	}

}
