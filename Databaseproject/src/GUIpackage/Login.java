package GUIpackage;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import operating.dbOperating;

/**
 * @author 王晨超
 *
 * 2018年2月25日
 */
public class Login extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField userText = new JTextField(20);
    private JPasswordField passwordText = new JPasswordField(20);
    
	public Login(){
		  // 创建一个窗体
        super("人事工资管理系统登录界面");
        this.setSize(350, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示在屏幕中央
        this.setLocationRelativeTo(null);
        
        // 创建面板
        JPanel panel = new JPanel();    
        // 添加面板
        this.add(panel);
        
        // 设置界面可见
        this.setVisible(true);
     
        //调用用户定义的方法并添加组件到面板
        placeComponents(panel);

   
	}
	
	//布局，其中登录的账号和密码就是数据库的账号和密码
	private  void placeComponents(JPanel panel) {
        //设置布局,登陆页面不需要额外布局
        panel.setLayout(null);

        // 创建账户 JLabel
        JLabel userLabel = new JLabel("账户:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        // 创建文本域用于用户输入
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // 创建密码 JLabel
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);
        // 输入密码的文本域，输入会用.表示
        passwordText.setBounds(100,50,165,25);
        //给密码框添加动作
        passwordText.addActionListener(this);
        panel.add(passwordText);
        

        // 创建登录按钮
        JButton loginButton = new JButton("登陆");
        loginButton.setBounds(10, 80, 80, 25);
        //给按钮添加动作
        loginButton.addActionListener(this);
        panel.add(new JLabel());
        panel.add(loginButton);
	}
	
	public void actionPerformed(ActionEvent ae) {
		//点击登陆按钮后，进行连接数据库的验证
		dbOperating theDb = new dbOperating();
		boolean flag = theDb.connect(userText.getText(), String.valueOf(passwordText.getPassword()));
		//成功连接上数据库，则进入主页面，并将连接好的数据库一起传入
		if(flag == true){
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Main(theDb);
	            }
	        });
			this.dispose();
		}
		else {
			//连接失败，则提示账号或密码错误
			new Mydialog("密码或账号错误");
		}
		
	}
	
	//主函数入口
	public static void main(String arg[]) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Login();
	            }
	        });

	}

}
