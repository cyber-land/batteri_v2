package main;

abstract public class Bacteria implements Cloneable {
public final static int RANDOM_INITIAL_LIFE = 1500;
public final static int INITIAL_LIFE = 500;
public final static int RANDOM_INITIAL_HEALTH = 600;
public final static int INITIAL_HEALTH = 200;
public final static int GOOD_HEALTH = 200;
public final static int INCREASE_HEALTH = 100;
public final static int INITIAL_REPRODUCTIVE_LOOPS = 500;
public final static int RANDOM_REPRODUCTIVE_LOOPS = 100;
public final static int REPRODUCTIVE_LOOPS = 200;
private int age;
private int health;
private int loopsForCloning;
protected int x;
protected int y;
private static Food food = null;

public Bacteria() {
	this.x = (int) (Math.random() * Food.getWidth());
	this.y = (int) (Math.random() * Food.getHeight());
	this.age = (int) (Math.random() * RANDOM_INITIAL_LIFE) + INITIAL_LIFE;
	this.health = (int) (Math.random() * RANDOM_INITIAL_HEALTH) + INITIAL_HEALTH;
	this.loopsForCloning = INITIAL_REPRODUCTIVE_LOOPS + 
	(int) (Math.random() * RANDOM_REPRODUCTIVE_LOOPS);
}
public static void setFood(Food food) {
	if (Bacteria.food == null) Bacteria.food = food;
}
protected abstract void move() throws Exception;
// TODO optimize ?!
public final boolean isReadyForCloning() {
	if (loopsForCloning == 0 && health > GOOD_HEALTH) {
		loopsForCloning = REPRODUCTIVE_LOOPS;
		return true;
	}
	return false;
}
// return if it's still alive
public final boolean run() {
	if (health < 1 || age < 1) return false;
	int xPrevious = x, yPrevious = y;
	try {
		this.move();
	} catch (Exception e) {
		System.out.println(getClass().getSimpleName() + " panics during movement");
		this.health = 0;
		return false;
	}
	if (food.eatFood(x, y)) health+=INCREASE_HEALTH; // eat
	this.age--;
	// https://it.wikipedia.org/wiki/Geometria_del_taxi
	int effort = Math.abs(x - xPrevious) + Math.abs(y - yPrevious);
	health -= effort;
	if (effort != 0) {
		loopsForCloning--;
	}
	return (health > 1 && age > 1);
}
public final int getAge() {
	return this.age;
}
public final int getHealth() {
	return this.health;
}
public final int getLoopsForCloning() {
	return this.loopsForCloning;
}
// TODO consider make it private
@Override
protected Bacteria clone() throws CloneNotSupportedException {
	Bacteria b = (Bacteria) super.clone();
	b.age = (int) (Math.random() * RANDOM_INITIAL_LIFE) + INITIAL_LIFE;
	b.health = (int) (Math.random() * RANDOM_INITIAL_HEALTH) + INITIAL_HEALTH;
	b.loopsForCloning = INITIAL_REPRODUCTIVE_LOOPS +
		(int) (Math.random() * RANDOM_REPRODUCTIVE_LOOPS);
	return b;
}
}
