package com.chess;
//具体状态白色
public class WhiteState implements ColorState {
	@Override
	public boolean handleState(boolean isWhite) {
		// TODO Auto-generated method stub
		isWhite = true;
		return isWhite;
	}
}
