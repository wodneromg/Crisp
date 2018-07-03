package com.chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class PiecesAction extends AbstractAction {
	public ChessBoard cb;
	private Pawn pawn;
	private Rook rook;
	private Knight knight;
	private Bishop bishop;
	private Queen queen;
	private King king;
	private boolean[][] cell;
	private boolean isWhite = true;
	public ColorContext colcontext;
	private BoardJFrame jf;
	private StringBuffer buf;
	private List<ChessPieces> btnlist = new ArrayList<>();

	public PiecesAction(ChessBoard cb, boolean[][] cell, boolean isWhite, 
			ColorContext colcontext, BoardJFrame jf, StringBuffer buf) {
		this.cb = cb;
		this.cell = cell;
		this.isWhite = isWhite;
		this.colcontext = colcontext;
		this.jf = jf;
		this.buf = buf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ChessPieces cp = (ChessPieces) arg0.getSource();
		isWhite = getIswhite();
		if(!btnlist.contains(cp)){
			if(btnlist.size()<2){
				btnlist.add(cp);//cp为第一个点击的button并且不是点击两次同一button
			}
			if(btnlist.size()==2){
				ChessPieces chessp = btnlist.get(0);
				//白棋吃黑棋,0标记为白方
				if(isWhite){
					if(isSide(chessp)==1){//chessp为白棋时
						eatBlack(chessp, cp, btnlist, cell, isWhite);
					}
				}else if(!isWhite){
					//chessp为黑棋时 吃白棋
						eatWhite(chessp, cp, btnlist, cell, isWhite);
					}
				btnlist.remove(chessp);
			}
			
		}
	}
	public void eatWhite(ChessPieces chessp, ChessPieces cp, 
			List<ChessPieces> btnlist, boolean[][] cell, boolean iswhite){
		if(isSide(cp)==1){//第二次点击的button，cp已经置换,即白棋
			if(cb.isPawn(chessp)){//若是黑卒，则吃斜上方白棋子
				int bx = cp.getX();
				int by = cp.getY();
				if((bx-chessp.getX()==-80&&by-chessp.getY()==80)
						||(bx-chessp.getX()==80&&by-chessp.getY()==80)){
					cell[chessp.getX()/80][chessp.getY()/80] = false;
					cell[bx/80][by/80] = true;
					cb.remove(cp);
					chessp.setLocation(bx, by);
					btnlist.clear();
					colcontext.setState(new WhiteState());
					isWhite = colcontext.request(isWhite);
					System.out.println("黑方吃完， 白方下");
					buf.append("\n"+"黑方吃完， 白方下"+"\n");
					jf.setTextArea(buf);
				}
				btnlist.clear();
			}else if(chessp.isLegal(cp.getX(), cp.getY(), cell)){
				chessp.moveNext(cp.getX(), cp.getY(), cell);
				cb.remove(cp);
				btnlist.clear();
				colcontext.setState(new WhiteState());
				isWhite = colcontext.request(isWhite);
				System.out.println("黑方吃完， 白方下");
				buf.append("\n"+"黑方吃完， 白方下"+"\n");
				jf.setTextArea(buf);
			}else{
				btnlist.remove(chessp);
			}
		}
	}
	public void eatBlack(ChessPieces chessp, ChessPieces cp, 
			List<ChessPieces> btnlist, boolean[][] cell, boolean iswhite){
		if(isSide(cp)==0){//第二次点击的button，cp已经置换,即黑棋
			if(cb.isPawn(chessp)){//若是白卒，则吃斜下方黑棋子
				int bx = cp.getX();
				int by = cp.getY();
				if((bx-chessp.getX()==80&&by-chessp.getY()==-80)
						||(bx-chessp.getX()==-80&&by-chessp.getY()==-80)){
					cell[chessp.getX()/80][chessp.getY()/80] = false;
					cell[bx/80][by/80] = true;
					cb.remove(cp);
					chessp.setLocation(bx, by);
					btnlist.clear();
					colcontext.setState(new BlackState());
					isWhite = colcontext.request(isWhite);
					System.out.println("白方吃完， 黑方下");
					buf.append("\n"+"白方吃完， 黑方下"+"\n");
					jf.setTextArea(buf);
				}
				btnlist.clear();
			}else if(chessp.isLegal(cp.getX(), cp.getY(), cell)){
				chessp.moveNext(cp.getX(), cp.getY(), cell);
				cb.remove(cp);
				btnlist.clear();
				colcontext.setState(new BlackState());
				isWhite = colcontext.request(isWhite);
				System.out.println("白方吃完， 黑方下");
				buf.append("\n"+"白方吃完， 黑方下"+"\n");
				jf.setTextArea(buf);
			}else{
				btnlist.remove(chessp);
			}
		}
	}
	public int isSide(ChessPieces chessp){
		if(cb.isPawn(chessp)){
			pawn = (Pawn) chessp;
			return pawn.getSide();
		}else if(cb.isRook(chessp)){
			rook = (Rook) chessp;
			return rook.getSide();
		}else if(cb.isKnight(chessp)){
			knight = (Knight) chessp;
			return knight.getSide();
		}else if(cb.isBishop(chessp)){
			bishop = (Bishop) chessp;
			return bishop.getSide();
		}else if(cb.isQueen(chessp)){
			queen = (Queen) chessp;
			return queen.getSide();
		}else if(cb.isKing(chessp)){
			king = (King) chessp;
			return king.getSide();
		}
		return 1;
	}
	
	public void setTemplist(List<ChessPieces> btnlist){
		this.btnlist = btnlist;
	}
	public List<ChessPieces> getTemplist(){
		return btnlist;
	}
	
	public void setIswhite(boolean isWhite){
		this.isWhite = isWhite;
	}
	public boolean getIswhite(){
		return isWhite;
	}
	
}
