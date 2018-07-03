package com.chess;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JMenuBar;
//添加观察者
public class MenuAction extends AbstractAction {
	private ChessBoard cb;
	private WatchSubject watch;
	public MenuAction(ChessBoard cb) {
		this.cb = cb;
		watch = new WatchSubject();
		watch.addWatch(cb);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String text = ((AbstractButton) e.getSource()).getText();
		if(text.equals("白黑相隔")){
			watch.notifyWatch(Color.WHITE, Color.BLACK);
		}else if(text.equals("白蓝相隔")){
			watch.notifyWatch(Color.WHITE, Color.BLUE);
		}else if(text.equals("白绿相隔")){
			watch.notifyWatch(Color.WHITE, Color.GREEN);
		}
	}

}
