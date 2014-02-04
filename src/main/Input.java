package main;

import org.lwjgl.input.Keyboard;

public class Input {
	
	private int key_MoveLeftP1, key_MoveRightP1;
	private int key_MoveLeftP2, key_MoveRightP2;
	private int key_JumpP1, key_JumpP2;
	private int key_Confirm;
	
	public Input() {
		initKeys();
	}
	
	public void initKeys() {
		key_MoveLeftP1 = Keyboard.KEY_A;
		key_MoveRightP1 = Keyboard.KEY_D;
		key_MoveLeftP2 = Keyboard.KEY_LEFT;
		key_MoveRightP2 = Keyboard.KEY_RIGHT;
		key_JumpP1 = Keyboard.KEY_W;
		key_JumpP2 = Keyboard.KEY_UP;
		
		key_Confirm = Keyboard.KEY_RETURN;
	}
	
	public void update() {
		if(Game.stageCurrent != null) {
			if(Keyboard.isKeyDown(key_MoveLeftP1)) Game.stageCurrent.player1.moveX(-1);
			if(Keyboard.isKeyDown(key_MoveRightP1)) Game.stageCurrent.player1.moveX(1);
			if(Keyboard.isKeyDown(key_MoveLeftP2)) Game.stageCurrent.player2.moveX(-1);
			if(Keyboard.isKeyDown(key_MoveRightP2)) Game.stageCurrent.player2.moveX(1);
			
			if(Keyboard.isKeyDown(key_JumpP1)) Game.stageCurrent.player1.jump(15);
			if(Keyboard.isKeyDown(key_JumpP2)) Game.stageCurrent.player2.jump(15);
		}
	}
}
