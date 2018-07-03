package com.chess;

import java.awt.Dimension;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class King extends ChessPieces {
	int c;
	public King(int x, int y, int c){
		KingSize(x, y, c);
		this.c = c;
	}
	public void KingSize(int x, int y, int c){
		if(c==0){
			java.net.URL imgURL = King.class.getResource("bKing.png");
			Icon icon=new ImageIcon(imgURL);
			this.setIcon(icon);
		}else{
			java.net.URL imgURL = King.class.getResource("wKing.png");
			Icon icon=new ImageIcon(imgURL);
			this.setIcon(icon);
		}
		this.setContentAreaFilled(false);
		this.setSize(new Dimension(80, 80));
		this.setLocation(x, y);
	}
	
	@Override
	public boolean[][] moveNext(int x, int y, boolean cell[][]) {
		int sitex=this.getX();
		int sitey=this.getY();
		if(((Math.abs(x-sitex)==80)&&(Math.abs(y-sitey)==80))
				||((Math.abs(x-sitex)==80)&&(Math.abs(y-sitey)==0)||
				(Math.abs(x-sitex)==0)&&(Math.abs(y-sitey)==80))){
			if(isLegal(x, y, cell)){
				cell[sitex/80][sitey/80]=false;
				cell[x/80][y/80]=true;
				this.setLocation(x, y);
			}else
				this.setLocation(sitex, sitey);
		}
		return cell;
	};
	public boolean isLegal(int x, int y, boolean cell[][]){
		int sitex=this.getX();
		int sitey=this.getY();
		if(((Math.abs(x-sitex)==80)&&(Math.abs(y-sitey)==80))
				||((Math.abs(x-sitex)==80)&&(Math.abs(y-sitey)==0)||
				(Math.abs(x-sitex)==0)&&(Math.abs(y-sitey)==80))){
			return true;
		}else
			return false;
	}
	public void setSide(int c){
		this.c = c;
	}
	public int getSide(){
		return c;
	}
	
}