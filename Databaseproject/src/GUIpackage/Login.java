package GUIpackage;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import operating.dbOperating;

/**
 * @author ������
 *
 * 2018��2��25��
 */
public class Login extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField userText = new JTextField(20);
    private JPasswordField passwordText = new JPasswordField(20);
    
	public Login(){
		  // ����һ������
        super("���¹��ʹ���ϵͳ��¼����");
        this.setSize(350, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʾ����Ļ����
        this.setLocationRelativeTo(null);
        
        // �������
        JPanel panel = new JPanel();    
        // ������
        this.add(panel);
        
        // ���ý���ɼ�
        this.setVisible(true);
     
        //�����û�����ķ����������������
        placeComponents(panel);

   
	}
	
	//���֣����е�¼���˺ź�����������ݿ���˺ź�����
	private  void placeComponents(JPanel panel) {
        //���ò���,��½ҳ�治��Ҫ���Ⲽ��
        panel.setLayout(null);

        // �����˻� JLabel
        JLabel userLabel = new JLabel("�˻�:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        // �����ı��������û�����
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // �������� JLabel
        JLabel passwordLabel = new JLabel("����:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);
        // ����������ı����������.��ʾ
        passwordText.setBounds(100,50,165,25);
        //���������Ӷ���
        passwordText.addActionListener(this);
        panel.add(passwordText);
        

        // ������¼��ť
        JButton loginButton = new JButton("��½");
        loginButton.setBounds(10, 80, 80, 25);
        //����ť��Ӷ���
        loginButton.addActionListener(this);
        panel.add(new JLabel());
        panel.add(loginButton);
	}
	
	public void actionPerformed(ActionEvent ae) {
		//�����½��ť�󣬽����������ݿ����֤
		dbOperating theDb = new dbOperating();
		boolean flag = theDb.connect(userText.getText(), String.valueOf(passwordText.getPassword()));
		//�ɹ����������ݿ⣬�������ҳ�棬�������Ӻõ����ݿ�һ����
		if(flag == true){
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Main(theDb);
	            }
	        });
			this.dispose();
		}
		else {
			//����ʧ�ܣ�����ʾ�˺Ż��������
			new Mydialog("������˺Ŵ���");
		}
		
	}
	
	//���������
	public static void main(String arg[]) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Login();
	            }
	        });

	}

}
