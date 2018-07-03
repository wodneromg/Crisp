package com.chess;

import javax.swing.JButton;

public abstract class ChessPieces extends JButton{//模板 子类继承父类 实现子类的方法
	int c;
	public abstract boolean[][] moveNext(int x, int y, boolean cell[][]);
	public abstract  boolean isLegal(int x, int y, boolean cell[][]);
}
