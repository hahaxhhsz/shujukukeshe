package GUIpackage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author ������
 *
 * 2018��2��28��
 */
public class Mydialog {

	public Mydialog(String message) {
		// TODO Auto-generated method stub
		JOptionPane op = new JOptionPane(message,JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = op.createDialog("��Ϣ��ʾ");
        // ����һ���¼�ʱ����һ��ʱ����Զ����ٶԻ���
        Timer timer = new Timer();
        // 2���ִ�и�����
        timer.schedule(new TimerTask() {
            public void run() {
                dialog.setVisible(false);
                dialog.dispose();
            }
        }, 2000);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
	}

}
