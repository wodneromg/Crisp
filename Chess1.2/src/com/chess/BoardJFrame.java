package com.chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BoardJFrame extends JFrame{
	private ChessBoard cb;
	private JMenuBar menubar;
	private JMenu gameset,colorset;
	private JMenuItem reset, close, whblack, whblue, whgreen;
	private JPanel panel;
	private TextArea text = new TextArea("",40,20);
	private  Container con;
	private PiecesAction action;
	private MenuAction menuaction;
	private StringBuffer strbuf = new StringBuffer("\n"+"白方先下"+"\n");
	private BoardJFrame jf;
	
	public BoardJFrame(){
		init();
		menuaction = new MenuAction(cb);
		initMenu();
		initPanel();
		initJFrame();
		initEvent();
		
	}
	public void init(){
		cb = new ChessBoard(this);
	}
	public void initEvent(){
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cb.listClear();
				cb.removeAll();
				cb.listClear();
				cb.initAll();
				cb.stepfirst();
				cb.BoardListener();
				cb.setColorone(Color.WHITE);
				cb.setColortwo(Color.GREEN);
//				con.add(cb,BorderLayout.CENTER);
				con.repaint();
			}
		});
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		whblack.addActionListener(menuaction);
		whblue.addActionListener(menuaction);
		whgreen.addActionListener(menuaction);
	}
	
	public void initMenu(){
		menubar = new JMenuBar();
		gameset = new JMenu("游戏设置");
		colorset = new JMenu("棋盘颜色");
		reset = new JMenuItem("重新游戏");
		close = new JMenuItem("关闭游戏");
		whblack = new JMenuItem("白黑相隔");
		whblue = new JMenuItem("白蓝相隔");
		whgreen = new JMenuItem("白绿相隔");
		menubar.add(gameset);
		menubar.add(colorset);
		gameset.add(reset);
		gameset.add(close);
		colorset.add(whblack);
		colorset.add(whblue);colorset.add(whgreen);
	}
	public void setTextArea(StringBuffer strbuf){
		text.setText(strbuf.toString());
		this.text = text;
	}
	public TextArea getText(){
		return this.text;
	}
	public void initPanel(){
		panel = new JPanel();
//		text = new TextArea("",40,20);
		text.setSize(new Dimension(50, 70));
		text.setText(strbuf.toString());
		panel.add(text);
	}
	public void initJFrame(){
		con = getContentPane();
		con.add(menubar,BorderLayout.NORTH);
		con.add(panel,BorderLayout.EAST);
		con.add(cb,BorderLayout.CENTER);
		setSize(826, 703);
		setLocation(60, 1);
		this.setTitle("Chess");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		con.repaint();
	}
	
}
