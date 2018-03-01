package GUIpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import operating.dbOperating;


/**
 * @author 王晨超
 *
 * 2018年2月25日
 */
public class PanleModel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	//创建功能按钮组
	protected JButton[] Gbutton = new JButton[5];
	//向面板中添加按钮时定位到第几个按钮
	private int index = 0;
	//三个分面板
	protected JPanel tablePanel = new JPanel();
	protected JPanel queryPanel = new JPanel();
    protected JPanel infoPanel = new JPanel();
    protected JFrame jFrame = null;
    private dbOperating theDB = null;
	//布局GridBagLayout的
	private GridBagConstraints s = new GridBagConstraints();
	//表格
    protected JTable jTable = null;
    public  PanleModel(JFrame jFrame,dbOperating db) {
		// 构造函数，需要接受一个窗体和连接好的数据库对象
    	this.jFrame = jFrame;
    	this.setLayout(new GridBagLayout());
    	this.theDB = db;
    	 // 创建功能按钮
    	addButton("信息管理",0,0);
    	addButton("考勤管理",1,0);
    	addButton("工资管理",2,0);
    	addButton("个人查询",3,0);
    	addButton("汇总查询",4,0);
    	//创建分面板
    	addPanel(tablePanel, 0, 2, 14,4);
    	addPanel(queryPanel, 4, 2, 14,1);
    	addPanel(infoPanel, 0, 16, 1,5);
    	
    	//设置显示表格的面板
    	jTable = new JTable(){
    	        	/**
					 * 
					 */
				private static final long serialVersionUID = 1L;

				//使表格中显示的数据不可编辑
    	        public boolean isCellEditable(int row, int column) {
    	        	return false; 
    	        	}
    	        };

    	 //滚动面板
    	 JScrollPane jscrollPane = new JScrollPane(jTable);
    	 jscrollPane.setPreferredSize(new Dimension(200,100));
    	 tablePanel.setLayout(new BorderLayout());
    	 tablePanel.add(jscrollPane,BorderLayout.CENTER);
    	 jTable.setRowHeight(40);
	}
  //设置背景图片
    protected void paintComponent(Graphics g) {  
        ImageIcon icon = new ImageIcon("src\\image\\beijin.jpg");  
        Image img = icon.getImage();  
        g.drawImage(img, 0, 0, icon.getIconWidth(),  
                icon.getIconHeight(), icon.getImageObserver()); 
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
	
}
    
    //在主面板上添加功能按钮
    public void addButton(String name,int x,int y){
        Gbutton[index] = new JButton(name);
        Gbutton[index].setPreferredSize(new Dimension(150,50));
        s.weightx = 100;
        s.weighty = 100;
        s.gridx = x;
        s.gridy = y;
        s.gridheight = 1;
        s.gridwidth = 1;
        //给按钮添加动作
        Gbutton[index].addActionListener(this);
        // 不允许按钮定位焦点
        Gbutton[index].setFocusable(false); 
        this.add(Gbutton[index],s); 
        index++;
    }
    
    //在主面板中添加分面板
    public void addPanel(JPanel panel,int x,int y,int xl,int yl){
    	//给分面板加边框，来区分
    	panel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
    	//将分面板设置为透明
    	panel.setOpaque(false); 
        s.weightx = 100;
        s.weighty = 150;
        s.fill = GridBagConstraints.BOTH;
        s.gridx = x;
        s.gridy = y;
        s.gridheight = xl;
        s.gridwidth = yl;
        this.add(panel,s);
    }
  //功能按钮点击事件
  	public void actionPerformed(ActionEvent e) {
  		//移除原有面板
  		jFrame.remove(this);
  		String type = e.getActionCommand();

  		//根据不同的按钮点击，实现不同功能面板的刷新
  		switch (type) {
  		case "信息管理":
  			 panel1 newpanel = new panel1(jFrame,theDB);
  	  		 //给按钮加个边框，提示用户是在哪个界面
  			 newpanel.setButtonColor(0);
  	         jFrame.setContentPane(newpanel);
  	         
  			break;
  		case "考勤管理":
 			 panel2 newpanel2 = new panel2(jFrame,theDB);
  			 newpanel2.setButtonColor(1);
  	         jFrame.setContentPane(newpanel2);
  			break;
  		case "工资管理":
			 panel3 newpanel3 = new panel3(jFrame,theDB);
  			 newpanel3.setButtonColor(2);
  	         jFrame.setContentPane(newpanel3);
  			break;
  		case "个人查询":
			 panel4 newpanel4 = new panel4(jFrame,theDB);
  			 newpanel4.setButtonColor(3);
  	         jFrame.setContentPane(newpanel4);
  			break;
  		case "汇总查询":
			 panel5 newpanel5 = new panel5(jFrame,theDB);
  			 newpanel5.setButtonColor(4);
  	         jFrame.setContentPane(newpanel5);
  			break;
  		default:
  			break;
  		}
  		//使面板更新不卡顿
          jFrame.validate();
          jFrame.repaint(); 
  	}
  //设置按钮边框
  	public void setButtonColor(int index){
	    LineBorder b = new LineBorder(Color.YELLOW, 2);
	    Gbutton[index].setBorder(b);
  	}
  //判断是否一个合法时间
  	protected boolean isTime(String time) {
  		if(!time.contains("-")){
  			return false;
  		}
  		String[] nowtime = time.split("-"); 
  		if(nowtime.length!=2){
  			return false;
  		}
  		int year;
  		int month;
  		try{
  			year = Integer.parseInt(nowtime[0]);
  			month = Integer.parseInt(nowtime[1]);
  		}catch (Exception e) {
  			return false;
  		}
  		if(year<2017 || year>2049){
  			return false;
  		}
  		if(month<1||month>12){
  			return false;
  		}
  		
  		return true;
  	}
}
