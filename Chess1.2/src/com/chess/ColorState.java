package com.chess;
//抽象状态
public interface ColorState {//状态模式
	public boolean handleState(boolean isWhite);
}
