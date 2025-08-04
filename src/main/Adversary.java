package main;

import java.awt.Color;
import java.util.LinkedList;

public class Adversary<Type> {
	public String name;
	public Color color;
	public LinkedList<Type> entities;
	public long medium_time = 0;
	public static int INITIAL_QUANTITY = 100;
	public static Color[] colors = {
		Color.RED, Color.BLUE, Color.MAGENTA, Color.ORANGE,
		Color.DARK_GRAY, Color.GRAY, Color.BLACK, Color.LIGHT_GRAY,
		Color.PINK,  Color.CYAN, Color.GREEN, Color.YELLOW
	};
	private static int color_index = 0;

	public Adversary(String n, Type bacteria_type) {
		name = n;
		if (color_index == colors.length) color_index = 0;
		color = colors[color_index++];
		entities = new LinkedList<>();
		for (int i=0; i<INITIAL_QUANTITY; i++) {
			try {
				entities.add(
					((Class<Type>) bacteria_type)
					.getConstructor().newInstance()
				);
			} catch (Exception e) {
				System.out.println("cannot instance new bacteria_type");
				System.exit(1);
			}
		}
	}
}
