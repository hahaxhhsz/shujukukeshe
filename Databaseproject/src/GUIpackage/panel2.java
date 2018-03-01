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
public class panel2 extends MagPanelModel {

	private static final long serialVersionUID = 1L;
	private JTextField[] theText = new JTextField[7];
	private Object[] oldDatas = new Object[6];
	//���캯������Ҫ֪�����ĸ�������ĸ������ӵ����ݿ�
	public panel2(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		this.theDB = db;
		
		
        //�������Ӽ�����
        jTable.addMouseListener(new jtableActiong());
		//������Ϣ��ʾ���
		infoPanel.setLayout(new GridLayout(4, 6));
		placeInfoComponents();
		
		}
	
	
	
	
	
	
	//����һ��˽�к����������Ϣ��ʾ������
	private void placeInfoComponents(){
		String[] theLabel = {"Ա����","ʱ��","�ٵ�����","���˴���","��ٴ���","�Ӱ����"};
		JButton submit = new JButton("����");
		JButton update = new JButton("�޸�");
		JButton delete = new JButton("ɾ��");
		JButton reset = new JButton("����");
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
	
	
	
	
	//����һ���ڲ���������������޸İ�ť
	private class submitAction implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="����"){
			String[] datas = new String[6];
			for(int i = 0;i<6;i++){
				datas[i] = theText[i].getText();
			}		
			if(isTime(datas[1])){
				theDB.insert(datas, 2);
				theDB.closeState();
			}else {
				new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
			}

		}
		else if (e.getActionCommand()=="�޸�") {
			String[] datas = new String[6];
			for(int i = 0;i<6;i++){
				datas[i] = theText[i].getText();
			}
			if(isTime(datas[1])){
				theDB.update(datas, 2);
				theDB.closeState();
			}else {
				new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
			}
		}
		else if (e.getActionCommand()=="ɾ��") {
			String[] datas = new String[2];
			for(int i = 0;i<2;i++){
				datas[i] = theText[i].getText();
			}
			if(isTime(datas[1])){
				theDB.delete(datas, 2);
				theDB.closeState();
			}else {
				new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
			}
		}
		else {
	       	//��������oldDatas�����е��������¸��µ�text�У�ʵ�����ù���
        	for(int i = 0 ;i < 6;i++){
        		theText[i].setText(oldDatas[i].toString());
        	}
		}
		
	}	
	}		

	
	private class jtableActiong extends MouseAdapter{
		//����ĳ�У������¼�
        public void mouseClicked(MouseEvent event)  
        {  
        	int row = jTable.getSelectedRow();
        	Object[] theData = new Object[6];
        	//�ӱ���л�ȡ��Ӧ������ʾ����Ϣ����
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
