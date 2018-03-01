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
      myPanel.add(new JLabel("入职每多一年基本工资涨多少:"));
      myPanel.add(Field[0]);
      myPanel.add(new JLabel("入职第一年的基本工资:"));
      myPanel.add(Field[1]);
      myPanel.add(new JLabel("市场部部门奖金:"));
      myPanel.add(Field[2]);
      myPanel.add(new JLabel("财政部部门奖金:"));
      myPanel.add(Field[3]);
      myPanel.add(new JLabel("技术部部门奖金:"));
      myPanel.add(Field[4]);
      myPanel.add(new JLabel("经理职务奖金:"));
      myPanel.add(Field[5]);
      myPanel.add(new JLabel("临时工职务奖金:"));
      myPanel.add(Field[6]);
      myPanel.add(new JLabel("迟到一次扣多少:"));
      myPanel.add(Field[7]);
      myPanel.add(new JLabel("早退一次扣多少:"));
      myPanel.add(Field[8]);
      myPanel.add(new JLabel("请假一次扣多少:"));
      myPanel.add(Field[9]);
      myPanel.add(new JLabel("加班一次加多少:"));
      myPanel.add(Field[10]);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "请输入关于自动算工资的相关参数", JOptionPane.OK_CANCEL_OPTION);
      int[] newDatas = new int[11];
      if (result == JOptionPane.OK_OPTION) {
         try{
        	 for(int i = 0;i < 11;i++){
        		 newDatas[i] = Integer.parseInt(Field[i].getText());
        	 }
         }catch (Exception e) {
			// 如果有一个框没有填整数，就会报错
        	 new Mydialog("有个框没有填整数，请仔细检查");
        	 return datas;
		}
         return newDatas;
      }
      else {
    	  return datas;
	}
	
      
   }
}