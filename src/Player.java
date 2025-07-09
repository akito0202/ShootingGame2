import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Character implements KeyListener{
	private boolean isShooting = false;
	private boolean isInCooldown = false;

	private double shootingTime = 0.0;    // 連射継続時間（最大2秒）
	private double cooldownTime = 0.0;    // クールタイム（最大1秒）

	private double shootCooldown = 0.1;   // 弾間隔（連射間隔）
	private double timeSinceLastShot = 0; // 前回の弾からの時間
	

	public void draw(MyFrame f) {
		f.setColor(0, 128, 0);
		f.fillRect(x, y+20, 30, 10);
		f.setColor(200, 200, 200);
		f.fillRect(x+10, y, 10, 30);
	}
	public Player(double x,double y, double vx,double vy) {
		super(x,y,vx,vy);
	}
	public void updateCooldown(double deltaTime) {
	    timeSinceLastShot += deltaTime;
	}

	// 弾を撃てるかどうかチェックして撃つ
	public void tryShoot() {
		if (timeSinceLastShot >= shootCooldown) {
	        GameWorld.playerBullets.add(new PlayerBullet(x, y, 2, -10));
	        GameWorld.playerBullets.add(new PlayerBullet(x, y, 0, -10));
	        GameWorld.playerBullets.add(new PlayerBullet(x, y, -2, -10));
	        timeSinceLastShot = 0;

	        System.out.println("玉の数=" + GameWorld.playerBullets.size());
	    }
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			isShooting = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			vx = -5;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			vx = 5;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("Enterキーが押されました");
			GameWorld.enterPressed = true;
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			isShooting = false;
			shootingTime = 0;
			cooldownTime = 0;
			isInCooldown = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			vx = 0;
		}
	
	}
	public void keyTyped(KeyEvent e) {
		
	}
	public void move() {
		double deltaTime = 0.03;

		updateCooldown(deltaTime);  // クールタイムの時間経過更新

		if (isShooting) {
			if (!isInCooldown) {
				shootingTime += deltaTime;
				if (shootingTime <= 2.0) {
					tryShoot(); // 連射可能時間中
				} else {
					isInCooldown = true;
					cooldownTime = 0;
				}
			} else {
				cooldownTime += deltaTime;
				if (cooldownTime >= 1.0) {
					// クールタイム終了
					shootingTime = 0;
					isInCooldown = false;
				}
			}
		}

		super.move();

		if (x < 0) x = 0;
		if (x > 370) x = 370;
	}}

	
