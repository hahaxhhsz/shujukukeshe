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
public class panel4 extends QueryPannelModel {

	private static final long serialVersionUID = 1L;

    private JTextField nuText = new JTextField();
    private  JTextField nameText = new JTextField();
    private  JTextField timeText = new JTextField("��-��");

	public panel4(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		theDB=db;

   	 	//���ò�ѯ���
   	 	queryPanel.setLayout(new GridLayout(8, 2));
   	 	placeQueroComponents();

		}

	
	//����һ��˽�к�������Ӳ�ѯ������
	private void placeQueroComponents(){
		//���ò�ѯ���
		JLabel label1 = new JLabel("Ա����");
		JLabel label2 = new JLabel("����");
		JLabel label3 = new JLabel("ʱ��");
		label1.setFont(new Font("Dialog",   1,   20));
		label1.setForeground(Color.yellow);
		label2.setFont(new Font("Dialog",   1,   20));
		label2.setForeground(Color.yellow);
		label3.setFont(new Font("Dialog",   1,   20));
		label3.setForeground(Color.yellow);

		JButton queryButton = new JButton("��ѯ");
		JButton resetButton = new JButton("����");
		//Ϊ��ѯ��ť��Ӽ�����
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
	
	//����һ���ڲ�����������ѯ�Ͳ�ѯ���ð�ť
		private class queryAction implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="��ѯ"){
				String number = nuText.getText();
				String name = nameText.getText();
				String time = timeText.getText();
				//����Ա���ſ���������ʱ����е����ݣ����в�ѯ
					if(!number.equals("")){
						try {
							int num = Integer.parseInt(number);
							if(time.equals("��-��")){
								datas = theDB.query(num,4);
								//�رղ�ѯ���ͷ���Դ
								theDB.closeState();
								updateTabel(datas);
							}
							else if(isTime(time)){
								datas = theDB.query(num,time,4);
								theDB.closeState();
								updateTabel(datas);
								}
							else{
								new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
								datas=null;
							}
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
							new Mydialog("Ա�������ʽ����������һ������");
							datas=null;
						}
						if(datas!=null){
							filename = "Ա����Ϊ"+number;
						}
					}
					else if(!name.equals("")){
						if(time.equals("��-��")){
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
							new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
							datas=null;
						}
						
						if(datas!=null){
							filename = "����Ϊ"+name;
						}
					}
					else {	
						new Mydialog("Ա���ź�����������дһ��������д����ѯԱ���Ž��");
						datas=null;
					}
					
				}
				else{
					//��������ð�ť����������е������
					nuText.setText("");
					nameText.setText("");
					timeText.setText("��-��");
				}
		}
			
	}

  	

}
