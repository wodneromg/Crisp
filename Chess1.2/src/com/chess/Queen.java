package com.chess;

import java.awt.Dimension;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Queen extends ChessPieces {
	int c;
	public Queen(int x, int y, int c){
		QueenSize(x, y, c);
		this.c = c;
	}
	public void QueenSize(int x, int y, int c){
		if(c==0){
			java.net.URL imgURL = Queen.class.getResource("bQueen.png");
			Icon icon=new ImageIcon(imgURL);
			this.setIcon(icon);
		}else{
			java.net.URL imgURL = Queen.class.getResource("wQueen.png");
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
		if((Math.abs(x-sitex)==Math.abs(y-sitey))
				||(Math.abs(x-sitex)==0||Math.abs(y-sitey)==0)){
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
		int sx=sitex/80; int clix=x/80;
		int sy=sitey/80; int cliy=y/80;
		if((Math.abs(x-sitex)==Math.abs(y-sitey))
				||(Math.abs(x-sitex)==0||Math.abs(y-sitey)==0)){
			if((clix-sx)>0&&cliy!=sy){//bishop的规则
				if((cliy-sy)>0){//往右下方
					for(;sx<clix-1; ){
						for(; sy<cliy-1;){
							sx++;sy++;
							if(cell[sx][sy]){
								islegal = false;
							}
						}
					}
				}else if(cliy<sy){
					for(;sx<clix-1; ){//往右上方
						for(;cliy+1<sy; ){
							sx++;sy--;
							if(cell[sx][sy]){
								islegal = false;
	//							System.out.println((sy-1)+"sa"+sx+1);
							}
						}
					}
				}
			}else if((clix-sx)<0&&cliy!=sy){
				if((cliy-sy)<0){//往左上方
					for(;clix+1<sx&&cliy+1<sy; ){
						sx--;sy--;
						if(cell[sx][sy]){
							islegal = false;
						}
					}
				}else if(cliy>sy){
					for(;clix+1<sx; ){//往左下方
						for(;cliy-1>sy; ){
							sx--;sy++;
							if(cell[sx][sy]){
								islegal = false;
							}
						}
					}
				}
			}else if(Math.abs(y-sitey)!=0&&Math.abs(x-sitex)==0){//rook的规则
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
			}else if(Math.abs(x-sitex)!=0&&Math.abs(y-sitey)==0){
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