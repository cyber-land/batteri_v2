package children;
import main.Food;
public class Dumb extends main.Bacteria{
	private boolean goDown;
	public Dumb() {
		goDown = true;
	}
	@Override
	protected void move() {
		return;
	}
	@Override
	public main.Bacteria clone() throws CloneNotSupportedException {
		Dumb clone = (Dumb)super.clone();
		clone.goDown = !goDown;
		return clone;
	}
}