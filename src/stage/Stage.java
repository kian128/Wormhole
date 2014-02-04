package stage;

import java.util.ArrayList;
import java.util.List;

import main.Game;

import entity.Entity;
import entity.EntityPlayer;

public class Stage {
	
	public int id;
	public String name;
	
	public List<StageComponent> componentList;
	public List<Entity> entityList;
	
	public EntityPlayer player1, player2;
	public int spawnXP1, spawnYP1, spawnXP2, spawnYP2;
	
	private int[][] grid;
	
	public Stage(int id, String name, int spawnXP1, int spawnYP1, int spawnXP2, int spawnYP2, int[][] grid) {
		this.id = id;
		this.name = name;
		this.spawnXP1 = spawnXP1;
		this.spawnYP1 = spawnYP1;
		this.spawnXP2 = spawnXP2;
		this.spawnYP2 = spawnYP2;
		this.grid = grid;
		
		componentList = new ArrayList<StageComponent>();
		entityList = new ArrayList<Entity>();
		
		build();
	}
	
	public void build() {
		player1 = new EntityPlayer(this, spawnXP1, spawnYP1);
		player2 = new EntityPlayer(this, spawnXP2, spawnYP2);
		
		int stageWidth = grid[0].length, stageHeight = grid.length;
		int componentWidth = Game.screen.screenWidth / stageWidth;
		int componentHeight = Game.screen.screenHeight / stageHeight;
		
		for(int j = 0; j < stageHeight; j++) {
			for(int i = 0; i < stageWidth; i++) {
				Material material = null;
				if(grid[j][i] == 1) material = Material.NORMAL;
				
				if(grid[j][i] != 0) {
					StageComponent component = new StageComponent(material, i * componentWidth, Game.screen.screenHeight - (j + 1) * componentHeight, componentWidth, componentHeight);
					componentList.add(component);
				}
			}
		}
	}
	
	public void update() {
		for(int c = 0; c < componentList.size(); c++) componentList.get(c).update();
		for(int e = 0; e < entityList.size(); e++) entityList.get(e).update();
	}
	
	public void render() {
		for(int c = 0; c < componentList.size(); c++) componentList.get(c).render();
		for(int e = 0; e < entityList.size(); e++) entityList.get(e).render();
	}
}
