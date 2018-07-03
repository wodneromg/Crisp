package com.chess;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
//产品类
public class Rook extends ChessPieces {
	int c;
	public Rook(int x, int y, int c){
		RookSize(x, y, c);
		this.c = c;
	}
	public void RookSize(int x, int y, int c){
		if(c==0){
			java.net.URL imgURL = Rook.class.getResource("bRook.png");
			Icon icon=new ImageIcon(imgURL);
			this.setIcon(icon);
		}else{
			java.net.URL imgURL = Rook.class.getResource("wRook.png");
			Icon icon=new ImageIcon(imgURL);
			this.setIcon(icon);
		}
		this.setSize(new Dimension(80, 80));
		this.setContentAreaFilled(false);
		this.setLocation(x, y);
	}
	@Override
	public boolean[][] moveNext(int x, int y, boolean cell[][]) {
		int sitex=this.getX();
		int sitey=this.getY();
		if(Math.abs(x-sitex)==0||Math.abs(y-sitey)==0){
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
		boolean islegal = true;
		int sx=sitex/80; int cliy=y/80;
		int sy=sitey/80; int clix=x/80;
		if(Math.abs(y-sitey)!=0){
			if(cliy>sy){//往下走
				for(; sy<cliy-1; ){
					sy++;
					if(cell[sx][sy]){
						islegal = false;
					}
				}
			}else if(cliy<sy){//往上走
				for(; cliy+1<sy; ){
					sy--;
					if(cell[sx][sy]){
						islegal = false;
					}
				}
			}
		}else if(Math.abs(x-sitex)!=0){
			if(clix>sx){//往右走
				for(; sx<clix-1; ){
					sx++;
					if(cell[sx][sy]){
						islegal = false;
					}
				}
			}else if(clix<sx){//往左走
				for(; clix+1<sx; ){
					sx--;
					if(cell[sx][sy]){
						islegal = false;
					}
				}
			}
		}else{
			islegal = false;
		}
		return islegal;
	}
	public void setSide(int c){
		this.c = c;
	}
	public int getSide(){
		return c;
	}
}
