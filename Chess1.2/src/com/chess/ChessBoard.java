package com.chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

//定义具体主题的观察者
public class ChessBoard extends JPanel implements Watch{
	public List<ChessPieces> btnlist = new ArrayList<>();
	public List<ChessPieces> allbtnlist = new ArrayList<>();
	private MouseEvent e;
	int weight = 8;
	int wall = 80;
	private Color cone = Color.WHITE;
	private Color ctwo = Color.GREEN;
	private boolean isWhite = true;
	private boolean first=true;
	private PiecesAction action;
	public ColorContext colcontext;
	private StringBuffer buf = new StringBuffer();
	private ChessFactory chessFactory;
	private BoardJFrame jf;
	
	private Pawn bpawn1,bpawn2,bpawn3,bpawn4,bpawn5,bpawn6,bpawn7,bpawn8,
				wpawn1,wpawn2,wpawn3,wpawn4,wpawn5,wpawn6,wpawn7,wpawn8;
	private Rook brookleft,brookright,wrookleft,wrookright;
	private Knight bknightleft,bknightright,wknightleft,wknightright;
	private Bishop bbishopleft,bbishopright,wbishopleft,wbishopright;
	private Queen bqueen,wqueen;
	private King bking,wking;
	private boolean cell[][]={{true,true,false,false,false,false,true,true},
							{true,true,false,false,false,false,true,true},
							{true,true,false,false,false,false,true,true},
							{true,true,false,false,false,false,true,true},
							{true,true,false,false,false,false,true,true},
							{true,true,false,false,false,false,true,true},
							{true,true,false,false,false,false,true,true},
							{true,true,false,false,false,false,true,true}
						};
	public ChessBoard(BoardJFrame jf) {
		this.setLayout(null);
		this.jf = jf;
		initAll();
	}
	public void initAll(){
		chessFactory = ChessFactory.getInstance();
		colcontext = new ColorContext();
		initPawn();
		initRook();
		initKnight();
		initBishop();
		initQueen();
		initKing();
		firstStep();
		action = new PiecesAction(this, cell, isWhite, colcontext, jf, buf);
		btnlist = action.getTemplist();
		BoardListener();
		initEvent();
	}
	public void listClear(){
		action = new PiecesAction(this, cell, true, colcontext, jf, buf);
		btnlist = action.getTemplist();
		btnlist.clear();
	}
	public void stepfirst(){
		this.isWhite = true;
//		System.out.println("白方先下");
	}
	//生产类
	public void initPawn(){
		bpawn1  = chessFactory.createPawn(0, 80, 0);
		bpawn2  = chessFactory.createPawn(80, 80, 0);
		bpawn3  = chessFactory.createPawn(160, 80, 0);
		bpawn4  = chessFactory.createPawn(240, 80, 0);
		bpawn5  = chessFactory.createPawn(320, 80, 0);
		bpawn6  = chessFactory.createPawn(400, 80, 0);
		bpawn7  = chessFactory.createPawn(480, 80, 0);
		bpawn8  = chessFactory.createPawn(560, 80, 0);
		this.add(bpawn1);this.add(bpawn2);this.add(bpawn3);this.add(bpawn4);
		this.add(bpawn5);this.add(bpawn6);this.add(bpawn7);this.add(bpawn8);
		wpawn1  = chessFactory.createPawn(0, 480, 1);
		wpawn2  = chessFactory.createPawn(80, 480, 1);
		wpawn3  = chessFactory.createPawn(160, 480, 1);
		wpawn4  = chessFactory.createPawn(240, 480, 1);
		wpawn5  = chessFactory.createPawn(320, 480, 1);
		wpawn6  = chessFactory.createPawn(400, 480, 1);
		wpawn7  = chessFactory.createPawn(480, 480, 1);
		wpawn8  = chessFactory.createPawn(560, 480, 1);
		this.add(wpawn1);this.add(wpawn2);this.add(wpawn3);this.add(wpawn4);
		this.add(wpawn5);this.add(wpawn6);this.add(wpawn7);this.add(wpawn8);
	}
	public void initRook(){
		brookleft = chessFactory.createRook(0, 0, 0);
		brookright = chessFactory.createRook(560, 0, 0);
		wrookleft = chessFactory.createRook(0, 560, 1);
		wrookright = chessFactory.createRook(560, 560, 1);
		this.add(brookleft);this.add(brookright);
		this.add(wrookleft);this.add(wrookright);
	}
	public void initKnight(){
		bknightleft  = chessFactory.createKnight(80, 0, 0);
		this.add(bknightleft);
		bknightright  = chessFactory.createKnight(480, 0, 0);
		this.add(bknightright);
		wknightleft  = chessFactory.createKnight(80, 560, 1);
		this.add(wknightleft);
		wknightright  = chessFactory.createKnight(480, 560, 1);
		this.add(wknightright);
	}
	public void initBishop(){
		bbishopleft  = chessFactory.createBishop(160, 0, 0);
		this.add(bbishopleft);
		bbishopright  = chessFactory.createBishop(400, 0, 0);
		this.add(bbishopright);
		wbishopleft  = chessFactory.createBishop(160, 560, 1);
		this.add(wbishopleft);
		wbishopright  = chessFactory.createBishop(400, 560, 1);
		this.add(wbishopright);
	}
	public void initQueen(){
		bqueen  = chessFactory.createQueen(240, 0, 0);
		this.add(bqueen);
		wqueen  = chessFactory.createQueen(240, 560, 1);
		this.add(wqueen);
	}
	public void initKing(){
		bking  = chessFactory.createKing(320, 0, 0);
		this.add(bking);
		wking  = chessFactory.createKing(320, 560, 1);
		this.add(wking);
	}
	public void initEvent(){
		bpawn1.addActionListener(action);bpawn2.addActionListener(action);bpawn3.addActionListener(action);
		bpawn4.addActionListener(action);bpawn5.addActionListener(action);bpawn6.addActionListener(action);
		bpawn7.addActionListener(action);bpawn8.addActionListener(action);
		wpawn1.addActionListener(action);wpawn2.addActionListener(action);wpawn3.addActionListener(action);
		wpawn4.addActionListener(action);wpawn5.addActionListener(action);wpawn6.addActionListener(action);
		wpawn7.addActionListener(action);wpawn8.addActionListener(action);
		brookleft.addActionListener(action);brookright.addActionListener(action);wrookleft.addActionListener(action);
		wrookright.addActionListener(action);bknightleft.addActionListener(action);bknightright.addActionListener(action);
		wknightleft.addActionListener(action);wknightright.addActionListener(action);bbishopleft.addActionListener(action);
		bbishopright.addActionListener(action);wbishopleft.addActionListener(action);wbishopright.addActionListener(action);
		bqueen.addActionListener(action);wqueen.addActionListener(action);
		bking.addActionListener(action);wking.addActionListener(action);
	}
	public void firstStep(){
		if(first){
			System.out.println("白方先下");
			buf.append("\n"+"白方先下"+"\n");
			jf.setTextArea(buf);
			first=false;
		}
		return;
	}
	public void setColorone(Color cone){
		this.cone = cone;
	}
	public void setColortwo(Color ctwo){
		this.ctwo = ctwo;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		for(int i=0; i<weight; i++){
			for(int j=0; j<weight; j++){
				if((i+j)%2==0){
					g.setColor(cone);
					g.fillRect(i*wall, j*wall, wall, wall);
				}else{
					g.setColor(ctwo);
					g.fillRect(i*wall, j*wall, wall, wall);
				}
			}
		}
	}
	
	public void BoardListener(){
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(btnlist.size()==1){
					ChessPieces button = btnlist.get(0);
					
					int x = e.getX()/80*80;
					int y = e.getY()/80*80;

					if(isPawn(button)||isRook(button)||isKnight(button)||isBishop(button)
							||isQueen(button)||isKing(button)){
						isWhite = action.getIswhite();
						if(isWhite==true){
							if(button.isLegal(x, y, cell)){
								if(isWhite(button)){
									cell = button.moveNext(x, y, cell);
									btnlist.clear();
									colcontext.setState(new BlackState());//设为false 黑状态
									isWhite = colcontext.request(isWhite);
									action.setIswhite(isWhite);
									System.out.println("白方下完， 黑方下");
									buf.append("\n"+"白方下完， 黑方下"+"\n");
									jf.setTextArea(buf);
								}
							}
						}else{
							if(button.isLegal(x, y, cell)){
								if(!isWhite(button)){
									cell = button.moveNext(x, y, cell);
									btnlist.clear();
									colcontext.setState(new WhiteState());
									isWhite = colcontext.request(isWhite);
									action.setIswhite(isWhite);
									System.out.println("黑方下完， 白方下");
									buf.append("\n"+"黑方下完， 白方下"+"\n");
									jf.setTextArea(buf);
								}
							}
						}
					}else{
						return;
					}
				}
				return;
			}
			
		});
	}
	//是否为白棋子
	public boolean isWhite(ChessPieces cps){
		if(cps.equals(wpawn1)||cps.equals(wpawn2)
				||cps.equals(wpawn3)||cps.equals(wpawn4)
				||cps.equals(wpawn5)||cps.equals(wpawn6)
				||cps.equals(wpawn7)||cps.equals(wpawn8)
				||cps.equals(wrookleft)||cps.equals(wrookright)
				||cps.equals(wknightleft)||cps.equals(wknightright)
				||cps.equals(wbishopleft)||cps.equals(wbishopright)
				||cps.equals(wqueen)||cps.equals(wking)){
				
					return true;
			}
		return false;
	}
	//是否为下列某个棋子对象
	public boolean isPawn(ChessPieces chp){
		if(chp.equals(wpawn1)||chp.equals(wpawn2)
				||chp.equals(wpawn3)||chp.equals(wpawn4)
				||chp.equals(wpawn5)||chp.equals(wpawn6)
				||chp.equals(wpawn7)||chp.equals(wpawn8)
				||chp.equals(bpawn1)||chp.equals(bpawn2)
				||chp.equals(bpawn3)||chp.equals(bpawn4)
				||chp.equals(bpawn5)||chp.equals(bpawn6)
				||chp.equals(bpawn7)||chp.equals(bpawn8)){
			return true;
		}
		return false;
	}
	public boolean isRook(ChessPieces chp){
		if(chp.equals(wrookleft)||chp.equals(wrookright)
				||chp.equals(brookleft)||chp.equals(brookright)){
			return true;
		}
		return false;
	}
	public boolean isKnight(ChessPieces chp){
		if(chp.equals(wknightleft)||chp.equals(wknightright)
				||chp.equals(bknightleft)||chp.equals(bknightright)){
			return true;
		}
		return false;
	}
	public boolean isBishop(ChessPieces chp){
		if(chp.equals(wbishopleft)||chp.equals(wbishopright)
				||chp.equals(bbishopleft)||chp.equals(bbishopright)){
			return true;
		}
		return false;
	}
	public boolean isQueen(ChessPieces chp){
		if(chp.equals(wqueen)||chp.equals(bqueen)){
			return true;
		}
		return false;
	}
	public boolean isKing(ChessPieces chp){
		if(chp.equals(wking)||chp.equals(bking)){
			return true;
		}
		return false;
	}
	@Override
	public void update(Color cone, Color ctwo) {
		// TODO Auto-generated method stub
		setColortwo(ctwo);
		repaint();
	}
	
}
