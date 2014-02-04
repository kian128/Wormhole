package main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;

public class Screen {
	
	public int screenWidth, screenHeight;
	public String title;
	
	public Screen(String title, int width, int height) {
		this.title = title;
		this.screenWidth = width;
		this.screenHeight = height;
		
		initDisplay();
	}
	
	public void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.setResizable(false);
			Display.setVSyncEnabled(false);
			Display.setTitle(title + " " + Game.version);
			Display.create();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, screenWidth, 0, screenHeight, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
}
