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
 * @author ������
 *
 * 2018��2��27��
 */
public class panel5 extends QueryPannelModel {

	private static final long serialVersionUID = 1L;
    private JTextField dpText = new JTextField();
    private JTextField timeText = new JTextField("��-��");

    
	public panel5(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		theDB=db;

   	 	//���ò�ѯ���
   	 	queryPanel.setLayout(new GridLayout(8, 2));
   	 	placeQueroComponents();

		}
	
		
		//����һ��˽�к�������Ӳ�ѯ������
		private void placeQueroComponents(){
			//���ò�ѯ���
			JLabel label1 = new JLabel("����");
			JLabel label3 = new JLabel("ʱ��");
			label1.setFont(new Font("Dialog",   1,   20));
			label1.setForeground(Color.yellow);
			label3.setFont(new Font("Dialog",   1,   20));
			label3.setForeground(Color.yellow);

			
			
			JButton queryButton = new JButton("��ѯ");
			JButton resetButton = new JButton("����");
			//Ϊ��ѯ��ť��Ӽ�����
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
	//����һ���ڲ�����������ѯ�Ͳ�ѯ���ð�ť
	private class queryAction implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="��ѯ"){
			String num = dpText.getText();
			String time = timeText.getText();
			//����Ա���ſ���������ʱ����е����ݣ����в�ѯ
			if(!num.equals("")){
					try {
						if(time.equals("��-��")){
							datas = theDB.query(num,5);								//�رղ�ѯ���ͷ���Դ
							theDB.closeState();
							updateTabel(datas);
							if(datas!=null){
								filename = "����Ϊ"+num;
								}
						 }
						 else if(isTime(time)){
							datas = theDB.query(num,time,5);
							theDB.closeState();
							updateTabel(datas);
							if(datas!=null){
								filename = "����Ϊ"+num+" ʱ��Ϊ"+time;
								}
						  }
						  else{
								new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
							}
						} catch (NumberFormatException e1) {
								e1.printStackTrace();
								new Mydialog("Ա�������ʽ����������һ������");
					}
				}
				else{	
					if(time.equals("��-��")){
						datas = theDB.query(5);
						theDB.closeState();
	                    updateTabel(datas);
						if(datas!=null){
						filename = "��˾������Աȫ��ʱ�乤�����";
					}
				}
				else if(isTime(time)){
					datas = theDB.query(time,5);
					theDB.closeState();
					updateTabel(datas);
					if(datas!=null){
					filename = "ʱ��Ϊ"+time;
						}
					}
				else{
					new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
				}				
			}
					
		}
				else{
					//��������ð�ť����������е������
					dpText.setText("");
					timeText.setText("��-��");
				}
		}
			
	}


	
	
}
