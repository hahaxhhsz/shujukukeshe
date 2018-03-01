package GUIpackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


import operating.dbOperating;

/**
 * @author 王晨超
 *
 * 2018年2月27日
 */
public class panel4 extends QueryPannelModel {

	private static final long serialVersionUID = 1L;

    private JTextField nuText = new JTextField();
    private  JTextField nameText = new JTextField();
    private  JTextField timeText = new JTextField("年-月");

	public panel4(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		theDB=db;

   	 	//设置查询面板
   	 	queryPanel.setLayout(new GridLayout(8, 2));
   	 	placeQueroComponents();

		}

	
	//设置一个私有函数来添加查询面板组件
	private void placeQueroComponents(){
		//设置查询面板
		JLabel label1 = new JLabel("员工号");
		JLabel label2 = new JLabel("姓名");
		JLabel label3 = new JLabel("时间");
		label1.setFont(new Font("Dialog",   1,   20));
		label1.setForeground(Color.yellow);
		label2.setFont(new Font("Dialog",   1,   20));
		label2.setForeground(Color.yellow);
		label3.setFont(new Font("Dialog",   1,   20));
		label3.setForeground(Color.yellow);

		JButton queryButton = new JButton("查询");
		JButton resetButton = new JButton("重置");
		//为查询按钮添加监听器
		queryButton.addActionListener(new queryAction());
		resetButton.addActionListener(new queryAction());
		queryPanel.add(label1);
		nuText.setFont(new Font("Dialog",Font.BOLD,20));
		queryPanel.add(nuText);
		queryPanel.add(label2);
		nameText.setFont(new Font("Dialog",Font.BOLD,20));
		queryPanel.add(nameText);
		

		queryPanel.add(label3);
		timeText.setFont(new Font("Dialog",Font.BOLD,20));
		queryPanel.add(timeText);
		
		queryPanel.add(queryButton);
		queryPanel.add(resetButton);
	}
	
	//创建一个内部类来监听查询和查询重置按钮
		private class queryAction implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="查询"){
				String number = nuText.getText();
				String name = nameText.getText();
				String time = timeText.getText();
				//根据员工号框和姓名框和时间框中的内容，进行查询
					if(!number.equals("")){
						try {
							int num = Integer.parseInt(number);
							if(time.equals("年-月")){
								datas = theDB.query(num,4);
								//关闭查询，释放资源
								theDB.closeState();
								updateTabel(datas);
							}
							else if(isTime(time)){
								datas = theDB.query(num,time,4);
								theDB.closeState();
								updateTabel(datas);
								}
							else{
								new Mydialog("不是一个正确的时间，\n年加上一个-再加月份,且是在2017年1月到2050年1月以前的时间");
								datas=null;
							}
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
							new Mydialog("员工号码格式错误，请输入一个整数");
							datas=null;
						}
						if(datas!=null){
							filename = "员工号为"+number;
						}
					}
					else if(!name.equals("")){
						if(time.equals("年-月")){
							datas = theDB.query(name,4);
							theDB.closeState();
							updateTabel(datas);
						}
						else if(isTime(time)){
							datas = theDB.query(name,time,4);
							theDB.closeState();
							updateTabel(datas);
							}
						else{
							new Mydialog("不是一个正确的时间，\n年加上一个-再加月份,且是在2017年1月到2050年1月以前的时间");
							datas=null;
						}
						
						if(datas!=null){
							filename = "姓名为"+name;
						}
					}
					else {	
						new Mydialog("员工号和姓名至少填写一个，都填写将查询员工号结果");
						datas=null;
					}
					
				}
				else{
					//如果是重置按钮，则清空所有的输入框
					nuText.setText("");
					nameText.setText("");
					timeText.setText("年-月");
				}
		}
			
	}

  	

}
