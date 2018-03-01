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
public class panel3 extends MagPanelModel {

	private static final long serialVersionUID = 1L;
	private JTextField[] theText = new JTextField[11];
	//设置自动算工资的参数
	private int[] a =  new int[11];
	//构造函数，需要知道是哪个窗体和哪个已连接的数据库
	public panel3(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		this.theDB = db;
        //给表格添加监听器
        jTable.addMouseListener(new jtableActiong());
		//设置信息显示面板
		infoPanel.setLayout(new GridLayout(4, 6));
		placeInfoComponents();
		//给自动算工资参数设置初始值
		//a[0]为入职每多一年基本工资涨多少
		a[0] = 100;
		//a[1]为入职第一年的基本工资
		a[1] = 1000;
		//a[2]为市场部部门奖金，a[3]为财政部部门奖金，a[4]为技术部部门奖金
		a[2] = 1500;
		a[3] = 1100;
		a[4] = 1200;
		//a[5]为经理职务奖金，a[6]为临时工职务奖金
		a[5] = 5000;
		a[6] = 100;
		//a[7]迟到一次扣多少，a[8]早退一次扣多少，a[9]请假一次扣多少，a[10]加班一次加多少
		a[7] = -50;
		a[8] = -30;
		a[9] = -80;
		a[10] = 150;
		}
	
	
	

	
	
	
	//设置一个私有函数来添加信息显示面板组件
	private void placeInfoComponents(){
		String[] theLabel = {"员工号","时间","基本工资","部门奖金","公积金","五险费","职务津贴","考勤工资","税前工资","税后工资","公司总支出"};
		JButton submit = new JButton("提交");
		JButton reset = new JButton("设置自动算工资参数");
		submit.addActionListener(new submitAction());
		reset.addActionListener(new submitAction());
		int i = 0;
		for(i = 0;i<6;i++){
			JLabel theLab = new JLabel(theLabel[i]);
			theLab.setForeground(Color.yellow);
			theLab.setFont(new Font("Dialog",   1,   20));
			infoPanel.add(theLab);
		}
		for(i = 0;i<6;i++){
			theText[i] = new JTextField();
			theText[i].setFont(new Font("Dialog",Font.BOLD,20));
			infoPanel.add(theText[i]);
		}
		for(i=6;i<11;i++){
			JLabel theLab = new JLabel(theLabel[i]);
			theLab.setForeground(Color.yellow);
			theLab.setFont(new Font("Dialog",   1,   20));
			infoPanel.add(theLab);
		}
		infoPanel.add(submit);
		for(i=6;i<11;i++){
			theText[i] = new JTextField();
			theText[i].setFont(new Font("Dialog",Font.BOLD,20));
			infoPanel.add(theText[i]);
		}
		infoPanel.add(reset);
		

	}
	
	
	
	

	//创建一个内部类来监听提交和提交重置按钮
	private class submitAction implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="提交"){
			String[] datas = new String[11];
			for(int i = 0;i<11;i++){
				datas[i] = theText[i].getText();
			}
			if(isTime(datas[1])){
				theDB.insert(datas, 3);
				theDB.closeState();
			}else {
				new Mydialog("不是一个正确的时间，\n年加上一个-再加月份,且是在2017年1月到2050年1月以前的时间");
			}

		}
		else {
        	//点击设置参数按钮，跳出对话框，设置参数
			JOptionPaneMultiInput showset = new JOptionPaneMultiInput(a);
			a = showset.show();
		}
		
	}	
	}		

	
	private class jtableActiong extends MouseAdapter{
		//单击某行，触发事件
        public void mouseClicked(MouseEvent event)  
        {  
        	int row = jTable.getSelectedRow();
        	Object[] theData = new Object[11];
        	//根据公司具体的规定，算出该月该员工的工资
        	theData[0] = jTable.getValueAt(row, 0);
        	theData[1] = jTable.getValueAt(row, 5);
        	theData[2] = Integer.parseInt((String) jTable.getValueAt(row, 4))*a[0]+a[1];
        	String dtype = (String) jTable.getValueAt(row, 2);
        	if(dtype==null){
        		dtype="";
        	}
        	switch (dtype) {
			case "市场部":
				theData[3] = a[2];
				break;
			case "财政部":
				theData[3] = a[3];
				break;
			case "技术部":
				theData[3] = a[4];
				break;
			default:
				theData[3] = 0;
				break;
			}
        	//不知道五险一金怎么算的，就用随机数来代替缴纳不同的
        	theData[4] = -(int)1000-(int)Math.random()*1000;
        	theData[5] = -(int)500-(int)Math.random()*500;
        	dtype = (String) jTable.getValueAt(row, 3);
        	//根据不同职务给不同职务奖金，我随便设的规则
        	int difference = (a[5]-a[6])/5;
        	switch (dtype) {
			case "经理":
				theData[6] = a[5];
				break;
			case "副经理":
				theData[6] = a[6]+difference*4;
				break;
			case "组长":
				theData[6] = a[6]+difference*3;
				break;
			case "副组长":
				theData[6] = a[6]+difference*2;
				break;
			case "职工":
				theData[6] = a[6]+difference*1;
				break;
			case "临时工":
				theData[6] = a[6];
				break;	
			default:
				theData[6] = 0;
				break;
			}
        	theData[7] = Integer.parseInt((String)jTable.getValueAt(row, 6))*a[7]
        			+Integer.parseInt((String)jTable.getValueAt(row, 7))*a[8]
        			+Integer.parseInt((String)jTable.getValueAt(row, 8))*a[9]
        			+Integer.parseInt((String)jTable.getValueAt(row, 9))*a[10];
        	int all = 0;
        	for(int i =2;i<8;i++){
        		all += (int)theData[i];
        	}
        	theData[8] = all;
        	theData[9] = afterTax(all);
        	theData[10] = all-(int)theData[4]-(int)theData[5];
        	
        	//对应地将算出的结果在相应text出显示
        	for(int i = 0 ;i < 11;i++){
        		if(theData[i]==null){
        			theData[i]="";
        		}
        		theText[i].setText(theData[i].toString());
        	}
        } 
        
        //扣除个人所得税
        private double afterTax(int before) {
        	double base = before - 3500;  
            double tax = 0;  
            if(base <= 0) {  
                tax = 0;  
            }  
            else if(base <= 1500) {  
                tax = base * 0.03;  
            }  
            else if(base <= 4500) {  
                tax = base * 0.1 - 105;  
            }  
            else if(base <= 9000) {  
                tax = base * 0.2 - 555;  
            }  
            else if(base <= 35000) {  
                tax = base * 0.25 - 1005;  
            }  
            else if(base <= 55000) {  
                tax = base * 0.3 - 2755;  
            }  
            else if(base <= 80000) {  
                tax = base * 0.35 - 5505;  
            }  
            else {  
                tax = base * 0.45 - 13505;  
            }  
            return before-tax;
		}
	}
}

