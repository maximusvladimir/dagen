package com.jpii.dagen.vegetation;

public class Tree {
	
	boolean dead = false;
	TreeType type = TreeType.Pine;
	
	public Tree(TreeType type, boolean isdead) {
		this.type = type;
		dead = isdead;
	}
	
	public void setTreeType(TreeType treetype) {
		type = treetype;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public TreeType getTreeType() {
		return this.type;
	}
	
	public boolean isDead() {
		return dead;
	}
}
