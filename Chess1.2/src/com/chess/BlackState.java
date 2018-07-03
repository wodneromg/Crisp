package com.chess;
//具体状态黑色
public class BlackState implements ColorState {
	@Override
	public boolean handleState(boolean isWhite) {
		// TODO Auto-generated method stub
		isWhite = false;
		return isWhite;
	}
}
