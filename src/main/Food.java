package main;

import java.util.BitSet;
import java.util.Random;

public class Food {
public static BitSet food[]; // TODO testing only
private static int width;
private static int height;
private static Random random = new Random();
private static Distribution distributionType;
private static int foodQuantity = 500;
private static int foodDimension = 50;
public static enum Distribution {
	SQUARE, CORNER, RANDOM
}
private final Runnable distributionMethod;

public Food(int w, int h, Distribution dt) {
	width = w;
	height = h;
	food = new BitSet[h];
	for (int i = 0; i < food.length; i++) food[i] = new BitSet(w);
	distributionMethod = switch (dt) {
		case SQUARE -> Food::squareDistribution;
		case CORNER -> Food::cornerDistribution;
		case RANDOM -> Food::randomDistribution;
		default -> Food::squareDistribution;
	};
}
private static void squareDistribution() {
	int randx = random.nextInt(width - foodDimension - 1);
	int randy = random.nextInt(height - foodDimension - 1);
	for (int i = 0; i < foodQuantity; i++) {
		food[random.nextInt(foodDimension) + randy]
		.set(random.nextInt(foodDimension) + randx);
	}
}
private static void randomDistribution() {
	for (int i = 0; i < foodQuantity; i++) {
		food[random.nextInt(height - 1)].set(random.nextInt(width - 1));
	}
}
private static void cornerDistribution() {
	int x = 0, y = 0, corner = random.nextInt(4);
	switch (corner) {
		case 0 -> { x = 0; y = 0; }
		case 1 -> { x = width - foodDimension - 1; y = 0; }
		case 2 -> { x = 0; y = height - foodDimension - 1; }
		case 3 -> { x = width - foodDimension - 1; y = height - foodDimension - 1; }
	}
	for (int i = 0; i < foodQuantity; i++) {
		food[y + random.nextInt(foodDimension)]
		.set(x + random.nextInt(foodDimension));
	}
}
public void toggle() {
	distributionMethod.run();
}
public static Distribution getDistributionType() {
	return Food.distributionType;
}
public static boolean isFood(int x, int y) {
	if (x < 0 || x >= width || y < 0 || y >= height) {
		return false;
	}
	return food[y].get(x);
}
// TODO optimize?
public boolean eatFood(int x, int y) {
	if (isFood(x, y)) {
		food[y].flip(x);
		return true;
	}
	return false;
}
public static int getWidth() {
	return width;
}
public static int getHeight() {
	return height;
}
}
