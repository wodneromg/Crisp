package com.chess;
//状态上下文
public class ColorContext {
	private ColorState colstate;
	
	public boolean request(boolean isWhite){
		return colstate.handleState(isWhite);
	}
	public void setState(ColorState colstate){
		this.colstate = colstate;
	}
}
