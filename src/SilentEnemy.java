
public class SilentEnemy extends Enemy {
    private boolean visible = false; // 最初は表示しない

    public SilentEnemy(double x, double y, double vx, double vy) {
        super(x, y, vx, vy);
        life = 3;
    }

    // 重力っぽい移動
    public void move() {
        super.move();
        vy = vy + 0.1;
    }

    // プレイヤーとの距離をチェックして可視状態を更新
    public void updateVisibility(double playerX, double playerY) {
        double dx = playerX - x;
        double dy = playerY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // 距離が200以下になったら表示状態にする
        if (distance <= 150) {
            visible = true;
        }
    }

    // 可視状態のときだけ描画
    public void draw(MyFrame f) {
        if (visible) {
            f.setColor(144, 255, 144);
            f.fillOval(x + 5, y, 20, 30);
        }
    }
}