package com.chess;

import java.awt.Dimension;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Pawn extends ChessPieces {
	int c;
	private boolean firstflag = true;
	public Pawn(int x, int y, int c){
		PawnSize(x, y, c);
		this.c = c;
	}
	public void PawnSize(int x, int y, int c){
		if(c==0){
			java.net.URL imgURL = Pawn.class.getResource("bPawn.png");
			Icon icon=new ImageIcon(imgURL);
			this.setIcon(icon);
		}else if(c==1){
			java.net.URL imgURL = Pawn.class.getResource("wPawn.png");
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
		if(sitey==160||sitey==400){
			firstflag = false;
		}
		if(Math.abs(x-sitex)==0&&Math.abs(y-sitey)<=160){
			if(firstflag == true){
				if(isLegal(x, y, cell)){
					cell[sitex/80][sitey/80]=false;
					cell[x/80][y/80]=true;
					this.setLocation(x, y);
					firstflag = false;
				}else{
					this.setLocation(sitex, sitey);
				}
			}else {
				if(Math.abs(x-sitex)==0&&Math.abs(y-sitey)<=80){
					if(isLegal(x, y, cell)){
						cell[sitex/80][sitey/80]=false;
						cell[x/80][y/80]=true;
						this.setLocation(x, y);
					}else{
						this.setLocation(sitex, sitey);
					}
				}else{
					this.setLocation(sitex, sitey);
				}
			}
		}
		return cell;
	};
	public boolean isLegal(int x, int y, boolean cell[][]){
		int sitex=this.getX();
		int sitey=this.getY();
		boolean islegal = true;
		int sx=sitex/80; int cliy=y/80;
		int sy=sitey/80; int clix=x/80;
		if(this.getSide()==0){//黑棋不后退
			if(Math.abs(x-sitex)==0&&(0<=(y-sitey)&&(y-sitey)<=160)){
				if(cell[sx][sy+1]){
					islegal = false;
				}else{
					islegal = true;
				}
			}else{
				islegal = false;
			}
		}else if(this.getSide()==1){//白棋不后退
			if(Math.abs(x-sitex)==0&&(0<=(sitey-y)&&(sitey-y)<=160)){
				if(cell[sx][sy-1]){
					islegal = false;
				}else{
					islegal = true;
				}
			}else{
				islegal = false;
			}
		}
		return islegal;
	}
	public void setSide(int c){
		this.c = c;
	}
	public int getSide(){
		return c;
	}
	public void setfirstflag(boolean firstflag){
		this.firstflag = firstflag;
	}
	public boolean getfirstflag(){
		return firstflag;
	}
}