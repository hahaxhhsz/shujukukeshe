package GUIpackage;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

import operating.dbOperating;

/**
 * @author 王晨超
 *
 * 2018年2月25日
 */
public class panelMain extends MagPanelModel{
	
	private static final long serialVersionUID = 1L;

	public panelMain(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		JLabel jlabel = new JLabel("欢迎你，管理员！！！");
		this.tablePanel.setLayout(new BorderLayout());
		//在tablePanel提示欢迎信息，并设置字体和颜色
		jlabel.setFont(new Font("Dialog",   1,   50));
		jlabel.setForeground(Color.red);
		this.tablePanel.add(jlabel,BorderLayout.NORTH);
		jlabel = new JLabel("点击上方的五个功能按钮");
		JLabel jlabel1 = new JLabel("分别进入不同的功能界面");
		jlabel.setFont(new Font("Dialog",   1,   50));
		jlabel.setForeground(Color.red);
		jlabel1.setFont(new Font("Dialog",   1,   50));
		jlabel1.setForeground(Color.red);
		this.tablePanel.add(jlabel,BorderLayout.CENTER);
		this.tablePanel.add(jlabel1,BorderLayout.SOUTH);
		
		//在queryPanel提示联系方式，若程序出现问题可联系
		this.queryPanel.setLayout(new BorderLayout());
		jlabel = new JLabel("制作者联系方式：");
		jlabel1 = new JLabel("2226165160@qq.com");
		jlabel.setFont(new Font("Dialog",   1,   20));
		jlabel.setForeground(Color.yellow);
		jlabel1.setFont(new Font("Dialog",   1,   20));
		jlabel1.setForeground(Color.yellow);
		this.queryPanel.add(jlabel,BorderLayout.CENTER);
		this.queryPanel.add(jlabel1,BorderLayout.SOUTH);
}

}
