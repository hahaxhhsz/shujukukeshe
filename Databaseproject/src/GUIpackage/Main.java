package GUIpackage;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import operating.dbOperating;

/**
 * @author ������
 *
 * 2018��2��25��
 */
public class Main extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Main(dbOperating Db)  {
        // ���� JFrame ʵ��
        super("���¹��ʹ���ϵͳ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//�ùرմ���ʱ���ͷ����ݿ����Դ�������ڴ����
    	this.addWindowListener(new WindowAdapter() {  
  
    		public void windowClosing(WindowEvent e) {  
    		super.windowClosing(e);  
    		//����ر����ݿ����ӵĶ���  
    			Db.close();

    		 }  
    		  
    		});  
        this.setSize(1000,750);
        //��ʾ����Ļ����
        this.setLocationRelativeTo(null);
        //����������ϵ����ݿ�
        // �������,�������ģ��
        JPanel panel = new panelMain(this,Db);
        // ������
        this.setContentPane(panel);
        // ���ý���ɼ�
        this.setVisible(true);
        //�����Ըı䴰�ڴ�С
        this.setResizable(false);
        
    }

	

    //����ʹ��
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
					new Main(new dbOperating());
	            }
	        });
        
        
	}


}
