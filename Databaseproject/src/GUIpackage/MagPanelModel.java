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
import javax.swing.table.DefaultTableModel;

import operating.dbOperating;

/**
 * @author ������
 *
 * 2018��2��26��
 */
public class MagPanelModel extends PanleModel {

	private static final long serialVersionUID = 1L;
	//���ò�ѯ�����������ȫ��ʹ��
    protected JTextField nuText = new JTextField();
    protected JTextField nameText = new JTextField();
    protected JTextField timeText = new JTextField("��-��");
    protected dbOperating theDB = null;
	private String[] colnumsName = new String[]{"Ա����","����","����","ְ��","��ְʱ��","����ʱ��","�ٵ�����","���˴���","��ٴ���","�Ӱ����","���ű��"};

    
    
    //���캯������Ҫ����һ����������Ӻõ����ݿ����
    public MagPanelModel(JFrame jFrame,dbOperating db){
    	super(jFrame,db);
    	this.theDB = db;
       
    	
    	 //���ñ�������
    	 DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
    	 tableModel.setColumnIdentifiers(colnumsName);
    			
    			
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
							String[][] datas = theDB.query(num,0);
							//�رղ�ѯ���ͷ���Դ
							theDB.closeState();
							updateTabel(datas);
						}
						else if(isTime(time)){
							String[][] datas = theDB.query(num,time,0);
							theDB.closeState();
							updateTabel(datas);
							}
						else{
							new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						new Mydialog("Ա�������ʽ����������һ������");
					}
				}
				else if(!name.equals("")){
					if(time.equals("��-��")){
						String[][] datas = theDB.query(name,0);
						theDB.closeState();
						updateTabel(datas);
					}
					else if(isTime(time)){
						String[][] datas = theDB.query(name,time,0);
						theDB.closeState();
						updateTabel(datas);
						}
					else{
						new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
					}
				}
				else {
					if(time.equals("��-��")){
						String[][] datas = theDB.query(0);
						theDB.closeState();
                        updateTabel(datas);

					}
					else if(isTime(time)){
						String[][] datas = theDB.query(time,0);
						theDB.closeState();
						updateTabel(datas);
						}
					else{
						new Mydialog("����һ����ȷ��ʱ�䣬\n�����һ��-�ټ��·�,������2017��1�µ�2050��1����ǰ��ʱ��");
					}
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
	
    
  	//����һ�����������±��
  	protected void updateTabel(String[][] datas){
  		//�ж��Ƿ��ѯ��������
  		if(datas!=null){
  			
  		DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
  		tableModel.setRowCount(0);// ���ԭ����
  		tableModel.setDataVector(datas, colnumsName);
  		jTable.invalidate();//ˢ�±��
  		
  		}else {
  			new Mydialog("δ��ѯ����Ч����");
  		}
  	}
	
	 
}