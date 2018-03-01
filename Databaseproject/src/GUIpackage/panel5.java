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
public class panel5 extends QueryPannelModel {

	private static final long serialVersionUID = 1L;
    private JTextField dpText = new JTextField();
    private JTextField timeText = new JTextField("年-月");

    
	public panel5(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		theDB=db;

   	 	//设置查询面板
   	 	queryPanel.setLayout(new GridLayout(8, 2));
   	 	placeQueroComponents();

		}
	
		
		//设置一个私有函数来添加查询面板组件
		private void placeQueroComponents(){
			//设置查询面板
			JLabel label1 = new JLabel("部门");
			JLabel label3 = new JLabel("时间");
			label1.setFont(new Font("Dialog",   1,   20));
			label1.setForeground(Color.yellow);
			label3.setFont(new Font("Dialog",   1,   20));
			label3.setForeground(Color.yellow);

			
			
			JButton queryButton = new JButton("查询");
			JButton resetButton = new JButton("重置");
			//为查询按钮添加监听器
			queryButton.addActionListener(new queryAction());
			resetButton.addActionListener(new queryAction());
			queryPanel.add(label1);
			dpText.setFont(new Font("Dialog",Font.BOLD,20));
			queryPanel.add(dpText);

			

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
			String num = dpText.getText();
			String time = timeText.getText();
			//根据员工号框和姓名框和时间框中的内容，进行查询
			if(!num.equals("")){
					try {
						if(time.equals("年-月")){
							datas = theDB.query(num,5);								//关闭查询，释放资源
							theDB.closeState();
							updateTabel(datas);
							if(datas!=null){
								filename = "部门为"+num;
								}
						 }
						 else if(isTime(time)){
							datas = theDB.query(num,time,5);
							theDB.closeState();
							updateTabel(datas);
							if(datas!=null){
								filename = "部门为"+num+" 时间为"+time;
								}
						  }
						  else{
								new Mydialog("不是一个正确的时间，\n年加上一个-再加月份,且是在2017年1月到2050年1月以前的时间");
							}
						} catch (NumberFormatException e1) {
								e1.printStackTrace();
								new Mydialog("员工号码格式错误，请输入一个整数");
					}
				}
				else{	
					if(time.equals("年-月")){
						datas = theDB.query(5);
						theDB.closeState();
	                    updateTabel(datas);
						if(datas!=null){
						filename = "公司所有人员全部时间工资情况";
					}
				}
				else if(isTime(time)){
					datas = theDB.query(time,5);
					theDB.closeState();
					updateTabel(datas);
					if(datas!=null){
					filename = "时间为"+time;
						}
					}
				else{
					new Mydialog("不是一个正确的时间，\n年加上一个-再加月份,且是在2017年1月到2050年1月以前的时间");
				}				
			}
					
		}
				else{
					//如果是重置按钮，则清空所有的输入框
					dpText.setText("");
					timeText.setText("年-月");
				}
		}
			
	}


	
	
}
