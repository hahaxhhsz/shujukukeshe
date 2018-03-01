package GUIpackage;

import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import operating.dbOperating;

/**
 * @author 王晨超
 *
 * 2018年2月26日
 */
public class panel2 extends MagPanelModel {

	private static final long serialVersionUID = 1L;
	private JTextField[] theText = new JTextField[7];
	private Object[] oldDatas = new Object[6];
	//构造函数，需要知道是哪个窗体和哪个已连接的数据库
	public panel2(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		this.theDB = db;
		
		
        //给表格添加监听器
        jTable.addMouseListener(new jtableActiong());
		//设置信息显示面板
		infoPanel.setLayout(new GridLayout(4, 6));
		placeInfoComponents();
		
		}
	
	
	
	
	
	
	//设置一个私有函数来添加信息显示面板组件
	private void placeInfoComponents(){
		String[] theLabel = {"员工号","时间","迟到次数","早退次数","请假次数","加班次数"};
		JButton submit = new JButton("插入");
		JButton update = new JButton("修改");
		JButton delete = new JButton("删除");
		JButton reset = new JButton("重置");
		submit.addActionListener(new submitAction());
		delete.addActionListener(new submitAction());
		update.addActionListener(new submitAction());
		reset.addActionListener(new submitAction());
		int i = 0;
		for(i = 0;i<4;i++){
			JLabel theLab = new JLabel(theLabel[i]);
			theLab.setForeground(Color.yellow);
			theLab.setFont(new Font("Dialog",   1,   20));
			infoPanel.add(theLab);
		}
		for(i = 0;i<4;i++){
			theText[i] = new JTextField();
			theText[i].setFont(new Font("Dialog",Font.BOLD,20));
			infoPanel.add(theText[i]);
		}
		for(i=4;i<6;i++){
			JLabel theLab = new JLabel(theLabel[i]);
			theLab.setForeground(Color.yellow);
			theLab.setFont(new Font("Dialog",   1,   20));
			infoPanel.add(theLab);
		}
		infoPanel.add(submit);
		infoPanel.add(update);
		for(i=4;i<6;i++){
			theText[i] = new JTextField();
			theText[i].setFont(new Font("Dialog",Font.BOLD,20));
			infoPanel.add(theText[i]);
		}
		infoPanel.add(delete);
		infoPanel.add(reset);
	}
	
	
	
	
	//创建一个内部类来监听插入和修改按钮
	private class submitAction implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="插入"){
			String[] datas = new String[6];
			for(int i = 0;i<6;i++){
				datas[i] = theText[i].getText();
			}		
			if(isTime(datas[1])){
				theDB.insert(datas, 2);
				theDB.closeState();
			}else {
				new Mydialog("不是一个正确的时间，\n年加上一个-再加月份,且是在2017年1月到2050年1月以前的时间");
			}

		}
		else if (e.getActionCommand()=="修改") {
			String[] datas = new String[6];
			for(int i = 0;i<6;i++){
				datas[i] = theText[i].getText();
			}
			if(isTime(datas[1])){
				theDB.update(datas, 2);
				theDB.closeState();
			}else {
				new Mydialog("不是一个正确的时间，\n年加上一个-再加月份,且是在2017年1月到2050年1月以前的时间");
			}
		}
		else if (e.getActionCommand()=="删除") {
			String[] datas = new String[2];
			for(int i = 0;i<2;i++){
				datas[i] = theText[i].getText();
			}
			if(isTime(datas[1])){
				theDB.delete(datas, 2);
				theDB.closeState();
			}else {
				new Mydialog("不是一个正确的时间，\n年加上一个-再加月份,且是在2017年1月到2050年1月以前的时间");
			}
		}
		else {
	       	//将保存在oldDatas数组中的数据重新更新到text中，实现重置功能
        	for(int i = 0 ;i < 6;i++){
        		theText[i].setText(oldDatas[i].toString());
        	}
		}
		
	}	
	}		

	
	private class jtableActiong extends MouseAdapter{
		//单击某行，触发事件
        public void mouseClicked(MouseEvent event)  
        {  
        	int row = jTable.getSelectedRow();
        	Object[] theData = new Object[6];
        	//从表格中获取相应数据显示在信息框中
        	theData[0] = jTable.getValueAt(row, 0);
        	theData[1] = jTable.getValueAt(row, 5);
        	theData[2] = jTable.getValueAt(row, 6);
        	theData[3] = jTable.getValueAt(row, 7);
        	theData[4] = jTable.getValueAt(row, 8);
        	theData[5] = jTable.getValueAt(row, 9);      	
        	for(int i = 0 ;i < 6;i++){
        		if(theData[i]==null){
        			theData[i]="";
        		}
        		theText[i].setText(theData[i].toString());
        	}
        	oldDatas = theData;
        } 

	}
}
