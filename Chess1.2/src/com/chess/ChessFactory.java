package com.chess;
//工厂类
public class ChessFactory {//创建工厂模式
	public static ChessFactory chessFactory = new ChessFactory();//单例模式，饿汉式
	private ChessFactory(){
		
	}
	public static ChessFactory getInstance(){
		return chessFactory;
	}
	public Rook createRook(int x, int y, int c){
		return new Rook(x, y, c);
	}
	
	public Knight createKnight(int x, int y, int c){
		return new Knight(x, y, c);
	}
	public Bishop createBishop(int x, int y, int c){
		return new Bishop(x, y, c);
	}
	public Queen createQueen(int x, int y, int c){
		return new Queen(x, y, c);
	}
	public King createKing(int x, int y, int c){
		return new King(x, y, c);
	}
	public Pawn createPawn(int x, int y, int c){
		return new Pawn(x, y, c);
	}
}
