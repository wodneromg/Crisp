package com.chess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
//获取颜色主题角色
public class WatchSubject {//观察者模式
	private List<Watch> list = new ArrayList<Watch>();
	
	public void addWatch(Watch watch){
		list.add(watch);
	}
	public void delWatch(Watch watch){
		list.remove(watch);
	}
	public void notifyWatch(Color cone, Color ctwo){
		for(Watch watch: list){
			watch.update(cone, ctwo);
		}
	}
}
