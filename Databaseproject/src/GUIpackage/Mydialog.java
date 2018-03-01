package GUIpackage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author 王晨超
 *
 * 2018年2月28日
 */
public class Mydialog {

	public Mydialog(String message) {
		// TODO Auto-generated method stub
		JOptionPane op = new JOptionPane(message,JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = op.createDialog("消息提示");
        // 创建一个新计时器，一定时间后自动销毁对话框
        Timer timer = new Timer();
        // 2秒后执行该任务
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
