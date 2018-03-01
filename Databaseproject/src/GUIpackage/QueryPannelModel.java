package GUIpackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import operating.XLSExport;
import operating.dbOperating;

public class QueryPannelModel extends PanleModel{


	private static final long serialVersionUID = 1L;
	protected String[] colnumsName = new String[]{"Ա����","����","����","ʱ��","��������","���Ž���","������","���շ�","ְ�����","���ڹ���","˰ǰ����","˰����","��˾��֧��"};
    protected String path = null;
    protected JTextField location = null;
    protected String filename = null;
    protected String[][] datas = null;
    protected dbOperating theDB = null;
    
	public QueryPannelModel(JFrame jFrame, dbOperating db) {
		super(jFrame, db);
	 	//���������������ñ���������
	 	DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
	 	tableModel.setColumnIdentifiers(colnumsName);
	 	infoPanel.setLayout(new GridLayout(4, 6));
	 	placeInfoComponents();
		
	}

	//����һ��˽�к����������Ϣ��ʾ������
	private void placeInfoComponents(){
		JButton create = new JButton("���ɱ���");
		JButton choose = new JButton("ѡ��·��");
		create.addActionListener(new submitAction());
		choose.addActionListener(new submitAction());
		JLabel information = new JLabel("ѡ��ô洢·���󣬽��������ʾ������ָ���ط�����Excel�ļ�");
		information.setForeground(Color.yellow);
		information.setFont(new Font("Dialog",   1,   20));
		location = new JTextField("�洢�ط���",50);
		location.setFont(new Font("Dialog",Font.BOLD,20));
		location.setEditable(false);
		
		infoPanel.add(information);
		infoPanel.add(location);
		infoPanel.add(choose);
		infoPanel.add(create);
	}

	//����һ���ڲ���������ѡ��·�������ɱ���ť
  	protected class submitAction implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="ѡ��·��"){
			 JFileChooser cho=new JFileChooser();  
			 cho.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			 cho.showDialog(null,null);
			 File f = cho.getSelectedFile();
			 try{
				 path = f.getAbsolutePath();
			 }catch (Exception e1) {
				// ���ûѡ��·�����׳��쳣����������쳣������Ҫ��ȡ��ʩ
			}
			 if(path!=null){
				location.setText("�洢��ַΪ�� "+path); 
			 }
		}
		else {
			//������е���������Excel�ļ�
			if(path==null){
				new Mydialog("δѡ��·��");
			}
			else if (filename==null||datas==null) {
				new Mydialog("δ������Ϣ����");
			}
			else{
				XLSExport ex = new XLSExport(path+"/"+filename+".xls");
				int rowcount = 1;
				ex.createRow(0);
				int colnumscount = 0;
				for(colnumscount = 0;colnumscount<13;colnumscount++){
					ex.setCell(colnumscount, colnumsName[colnumscount]);
				}
				for(String[] infodata : datas){
					ex.createRow(rowcount);
					rowcount++;
					colnumscount = 0;
					for(String data : infodata){
						ex.setCell(colnumscount, data);
						colnumscount++;
					}
				}						
		        try{
		        	ex.exportXLS();
		        	new Mydialog("����Excel�ļ�[�ɹ�]");
		        } catch(Exception e1)  {
		        	e1.printStackTrace();
					new Mydialog("����Excel�ļ�[ʧ��]");
		        } 
			}
			
		}
	}
  	}
  	
  	//���±��ķ���
  	protected void updateTabel(Object[][] datas){
	  	DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
	  	tableModel.setRowCount(0);// ���ԭ����
  		//�ж��Ƿ��ѯ��������
  		if(datas!=null){
  			tableModel.setDataVector(datas, colnumsName);
  		}else {
  			new Mydialog("δ��ѯ����Ч����");
  		}
  		jTable.invalidate();//ˢ�±��
  	}
}
