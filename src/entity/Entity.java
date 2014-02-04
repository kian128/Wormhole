package entity;

import stage.Stage;
import stage.StageComponent;
import static org.lwjgl.opengl.GL11.*;

public class Entity {
	
	private Stage stage;
	
	public int x, y;
	public int width, height;
	
	public float velocityX, velocityY;
	public boolean isMovingXLeft, isMovingXRight;
	public boolean isOnGround;
	
	public Entity(Stage stage, int x, int y, int width, int height) {
		this.stage = stage;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		stage.entityList.add(this);
	}
	
	public void moveX(int speed) {
		int velocityXMax = 10;
		if(velocityX < velocityXMax && velocityX > -velocityXMax) {
			velocityX += speed;
		}
		
		if(speed > 0) isMovingXRight = true;
		if(speed < 0) isMovingXLeft = true;
	}
	
	public void jump(int speed) {
		if(isOnGround) {
			velocityY = speed;
			isOnGround = false;
		}
	}
	
	public void update() {
		int velocityYMax = 25;
		if(velocityY < velocityYMax && velocityY > -velocityYMax) {
			velocityY -= 1;
		}
		
		isOnGround = false;
		
		int newX = (int) (x + velocityX / 3);
		int newY = (int) (y + velocityY / 2);
		boolean isCollidingX = false, isCollidingY = false;
		
		for(int c = 0; c < stage.componentList.size(); c++) {
			StageComponent component = stage.componentList.get(c);
			if(isColliding(newX, y, component)) {
				isCollidingX = true;
				velocityX = 0;
				if(newX < component.x) x = component.x - width;
				if(newX > component.x) x = component.x + component.width;
			}
			if(isColliding(x, newY, component)) {
				isCollidingY = true;
				velocityY = 0;
				if(newY < component.y) y = component.y - height;
				if(newY > component.y) {
					y = component.y + component.height;
					isOnGround = true;
				}
			}
		}
		
		if(!isCollidingX) x = newX;
		if(!isCollidingY) y = newY;
		
		if(!isMovingXLeft) if(velocityX < 0) velocityX += 2;
		if(!isMovingXRight) if(velocityX > 0) velocityX -= 2;
		
		isMovingXLeft = false;
		isMovingXRight = false;
	}
	
	public boolean isColliding(int px, int py, StageComponent component) {
		if((px + width > component.x && px + width < component.x + component.width) || (px > component.x && px < component.x + component.width)) {
			if((py + height > component.y && py + height < component.y + component.height) || (py > component.y && py < component.y + component.height)) {
				return true;
			}
		}
		return false;
	}
	
	public void render() {
		glColor3f(1, 0, 0);
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x, y + height);
			glVertex2f(x + width, y + height);
			glVertex2f(x + width, y);
		glEnd();
		glColor3f(1, 1, 1);
	}
}
