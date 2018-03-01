package GUIpackage;

import java.awt.GridLayout;

import javax.swing.*;

public class JOptionPaneMultiInput {
	
	private int[] datas = null;
	
	public JOptionPaneMultiInput(int[] datas){
		this.datas = datas;
	}
	
	
   public  int[] show() {
      JTextField[] Field = new JTextField[11];
      for(int i = 0;i<11;i++){
    	  Field[i] = new JTextField(datas[i]+"",5);
      }
      JPanel myPanel = new JPanel();
      myPanel.setLayout(new GridLayout(11, 2));
      myPanel.add(new JLabel("��ְÿ��һ����������Ƕ���:"));
      myPanel.add(Field[0]);
      myPanel.add(new JLabel("��ְ��һ��Ļ�������:"));
      myPanel.add(Field[1]);
      myPanel.add(new JLabel("�г������Ž���:"));
      myPanel.add(Field[2]);
      myPanel.add(new JLabel("���������Ž���:"));
      myPanel.add(Field[3]);
      myPanel.add(new JLabel("���������Ž���:"));
      myPanel.add(Field[4]);
      myPanel.add(new JLabel("����ְ�񽱽�:"));
      myPanel.add(Field[5]);
      myPanel.add(new JLabel("��ʱ��ְ�񽱽�:"));
      myPanel.add(Field[6]);
      myPanel.add(new JLabel("�ٵ�һ�ο۶���:"));
      myPanel.add(Field[7]);
      myPanel.add(new JLabel("����һ�ο۶���:"));
      myPanel.add(Field[8]);
      myPanel.add(new JLabel("���һ�ο۶���:"));
      myPanel.add(Field[9]);
      myPanel.add(new JLabel("�Ӱ�һ�μӶ���:"));
      myPanel.add(Field[10]);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "����������Զ��㹤�ʵ���ز���", JOptionPane.OK_CANCEL_OPTION);
      int[] newDatas = new int[11];
      if (result == JOptionPane.OK_OPTION) {
         try{
        	 for(int i = 0;i < 11;i++){
        		 newDatas[i] = Integer.parseInt(Field[i].getText());
        	 }
         }catch (Exception e) {
			// �����һ����û�����������ͻᱨ��
        	 new Mydialog("�и���û��������������ϸ���");
        	 return datas;
		}
         return newDatas;
      }
      else {
    	  return datas;
	}
	
      
   }
}