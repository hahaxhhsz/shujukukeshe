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
 * @author ������
 *
 * 2018��2��26��
 */
public class panel3 extends MagPanelModel {

	private static final long serialVersionUID = 1L;
	private JTextField[] theText = new JTextField[11];
	//�����Զ��㹤�ʵĲ���
	private int[] a =  new int[11];
	//���캯������Ҫ֪�����ĸ�������ĸ������ӵ����ݿ�
	public panel3(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		this.theDB = db;
        //�������Ӽ�����
        jTable.addMouseListener(new jtableActiong());
		//������Ϣ��ʾ���
		infoPanel.setLayout(new GridLayout(4, 6));
		placeInfoComponents();
		//���Զ��㹤�ʲ������ó�ʼֵ
		//a[0]Ϊ��ְÿ��һ����������Ƕ���
		a[0] = 100;
		//a[1]Ϊ��ְ��һ��Ļ�������
		a[1] = 1000;
		//a[2]Ϊ�г������Ž���a[3]Ϊ���������Ž���a[4]Ϊ���������Ž���
		a[2] = 1500;
		a[3] = 1100;
		a[4] = 1200;
		//a[5]Ϊ����ְ�񽱽�a[6]Ϊ��ʱ��ְ�񽱽�
		a[5] = 5000;
		a[6] = 100;
		//a[7]�ٵ�һ�ο۶��٣�a[8]����һ�ο۶��٣�a[9]���һ�ο۶��٣�a[10]�Ӱ�һ�μӶ���
		a[7] = -50;
		a[8] = -30;
		a[9] = -80;
		a[10] = 150;
		}
	
	
	

	
	
	
	//����һ��˽�к����������Ϣ��ʾ������
	private void placeInfoComponents(){
		String[] theLabel = {"Ա����","ʱ��","��������","���Ž���","������","���շ�","ְ�����","���ڹ���","˰ǰ����","˰����","��˾��֧��"};
		JButton submit = new JButton("�ύ");
		JButton reset = new JButton("�����Զ��㹤�ʲ���");
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
	
	
	
	

	//����һ���ڲ����������ύ���ύ���ð�ť
	private class submitAction implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="�ύ"){
			String[] datas = new String[11];
			for(int i = 0;i<11;i++){
				datas[i] = theText[i].getText();
			}
			if(isTime(datas[1])){
				theDB.insert(datas, 3);
				theDB.closeState();
			}else {
				new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
			}

		}
		else {
        	//������ò�����ť�������Ի������ò���
			JOptionPaneMultiInput showset = new JOptionPaneMultiInput(a);
			a = showset.show();
		}
		
	}	
	}		

	
	private class jtableActiong extends MouseAdapter{
		//����ĳ�У������¼�
        public void mouseClicked(MouseEvent event)  
        {  
        	int row = jTable.getSelectedRow();
        	Object[] theData = new Object[11];
        	//���ݹ�˾����Ĺ涨��������¸�Ա���Ĺ���
        	theData[0] = jTable.getValueAt(row, 0);
        	theData[1] = jTable.getValueAt(row, 5);
        	theData[2] = Integer.parseInt((String) jTable.getValueAt(row, 4))*a[0]+a[1];
        	String dtype = (String) jTable.getValueAt(row, 2);
        	if(dtype==null){
        		dtype="";
        	}
        	switch (dtype) {
			case "�г���":
				theData[3] = a[2];
				break;
			case "������":
				theData[3] = a[3];
				break;
			case "������":
				theData[3] = a[4];
				break;
			default:
				theData[3] = 0;
				break;
			}
        	//��֪������һ����ô��ģ������������������ɲ�ͬ��
        	theData[4] = -(int)1000-(int)Math.random()*1000;
        	theData[5] = -(int)500-(int)Math.random()*500;
        	dtype = (String) jTable.getValueAt(row, 3);
        	//���ݲ�ְͬ�����ְͬ�񽱽��������Ĺ���
        	int difference = (a[5]-a[6])/5;
        	switch (dtype) {
			case "����":
				theData[6] = a[5];
				break;
			case "������":
				theData[6] = a[6]+difference*4;
				break;
			case "�鳤":
				theData[6] = a[6]+difference*3;
				break;
			case "���鳤":
				theData[6] = a[6]+difference*2;
				break;
			case "ְ��":
				theData[6] = a[6]+difference*1;
				break;
			case "��ʱ��":
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
        	
        	//��Ӧ�ؽ�����Ľ������Ӧtext����ʾ
        	for(int i = 0 ;i < 11;i++){
        		if(theData[i]==null){
        			theData[i]="";
        		}
        		theText[i].setText(theData[i].toString());
        	}
        } 
        
        //�۳���������˰
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

