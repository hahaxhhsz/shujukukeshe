package GUIpackage;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import operating.dbOperating;

/**
 * @author 王晨超
 *
 * 2018年2月25日
 */
public class Main extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Main(dbOperating Db)  {
        // 创建 JFrame 实例
        super("人事工资管理系统");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//让关闭窗体时，释放数据库的资源，以免内存过大
    	this.addWindowListener(new WindowAdapter() {  
  
    		public void windowClosing(WindowEvent e) {  
    		super.windowClosing(e);  
    		//加入关闭数据库连接的动作  
    			Db.close();

    		 }  
    		  
    		});  
        this.setSize(1000,750);
        //显示在屏幕中央
        this.setLocationRelativeTo(null);
        //获得已连接上的数据库
        // 创建面板,引用面板模型
        JPanel panel = new panelMain(this,Db);
        // 添加面板
        this.setContentPane(panel);
        // 设置界面可见
        this.setVisible(true);
        //不可以改变窗口大小
        this.setResizable(false);
        
    }

	

    //测试使用
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
					new Main(new dbOperating());
	            }
	        });
        
        
	}


}
