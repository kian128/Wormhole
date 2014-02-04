package main;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;

import stage.Stage;
import stage.StageList;

public class Game {
	
	public static String version = "0.0";
	
	public static Screen screen;
	public static Input input;
	
	public static Stage stageCurrent;
	
	public Game(String title, int screenWidth, int screenHeight) {
		screen = new Screen(title, screenWidth, screenHeight);
		input = new Input();
		
		StageList.buildStages();
		stageCurrent = StageList.stage1;
		
		while(!Display.isCloseRequested()) {
			run();
		}
	}
	
	public void run() {
		glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
		
		if(stageCurrent != null) {
			stageCurrent.update();
			stageCurrent.render();
		}
		
		input.update();
		
		Display.update();
		Display.sync(60);
	}
}