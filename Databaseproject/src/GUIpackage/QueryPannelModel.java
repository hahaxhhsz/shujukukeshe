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
	protected String[] colnumsName = new String[]{"员工号","姓名","部门","时间","基本工资","部门奖金","公积金","五险费","职务津贴","考勤工资","税前工资","税后工资","公司总支出"};
    protected String path = null;
    protected JTextField location = null;
    protected String filename = null;
    protected String[][] datas = null;
    protected dbOperating theDB = null;
    
	public QueryPannelModel(JFrame jFrame, dbOperating db) {
		super(jFrame, db);
	 	//在子类中重新设置表格的列名，
	 	DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
	 	tableModel.setColumnIdentifiers(colnumsName);
	 	infoPanel.setLayout(new GridLayout(4, 6));
	 	placeInfoComponents();
		
	}

	//设置一个私有函数来添加信息显示面板组件
	private void placeInfoComponents(){
		JButton create = new JButton("生成报表");
		JButton choose = new JButton("选择路径");
		create.addActionListener(new submitAction());
		choose.addActionListener(new submitAction());
		JLabel information = new JLabel("选择好存储路径后，将表格中显示内容在指定地方生成Excel文件");
		information.setForeground(Color.yellow);
		information.setFont(new Font("Dialog",   1,   20));
		location = new JTextField("存储地方：",50);
		location.setFont(new Font("Dialog",Font.BOLD,20));
		location.setEditable(false);
		
		infoPanel.add(information);
		infoPanel.add(location);
		infoPanel.add(choose);
		infoPanel.add(create);
	}

	//创建一个内部类来监听选择路径和生成报表按钮
  	protected class submitAction implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="选择路径"){
			 JFileChooser cho=new JFileChooser();  
			 cho.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			 cho.showDialog(null,null);
			 File f = cho.getSelectedFile();
			 try{
				 path = f.getAbsolutePath();
			 }catch (Exception e1) {
				// 如果没选择路径会抛出异常，捕获这个异常，不需要采取措施
			}
			 if(path!=null){
				location.setText("存储地址为： "+path); 
			 }
		}
		else {
			//将表格中的数据生成Excel文件
			if(path==null){
				new Mydialog("未选择路径");
			}
			else if (filename==null||datas==null) {
				new Mydialog("未进行信息搜索");
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
		        	new Mydialog("导出Excel文件[成功]");
		        } catch(Exception e1)  {
		        	e1.printStackTrace();
					new Mydialog("导出Excel文件[失败]");
		        } 
			}
			
		}
	}
  	}
  	
  	//更新表格的方法
  	protected void updateTabel(Object[][] datas){
	  	DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
	  	tableModel.setRowCount(0);// 清除原有行
  		//判断是否查询到了数据
  		if(datas!=null){
  			tableModel.setDataVector(datas, colnumsName);
  		}else {
  			new Mydialog("未查询到有效数据");
  		}
  		jTable.invalidate();//刷新表格
  	}
}
