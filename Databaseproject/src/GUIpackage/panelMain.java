package GUIpackage;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

import operating.dbOperating;

/**
 * @author ������
 *
 * 2018��2��25��
 */
public class panelMain extends MagPanelModel{
	
	private static final long serialVersionUID = 1L;

	public panelMain(JFrame jFrame,dbOperating db) {
		super(jFrame,db);
		JLabel jlabel = new JLabel("��ӭ�㣬����Ա������");
		this.tablePanel.setLayout(new BorderLayout());
		//��tablePanel��ʾ��ӭ��Ϣ���������������ɫ
		jlabel.setFont(new Font("Dialog",   1,   50));
		jlabel.setForeground(Color.red);
		this.tablePanel.add(jlabel,BorderLayout.NORTH);
		jlabel = new JLabel("����Ϸ���������ܰ�ť");
		JLabel jlabel1 = new JLabel("�ֱ���벻ͬ�Ĺ��ܽ���");
		jlabel.setFont(new Font("Dialog",   1,   50));
		jlabel.setForeground(Color.red);
		jlabel1.setFont(new Font("Dialog",   1,   50));
		jlabel1.setForeground(Color.red);
		this.tablePanel.add(jlabel,BorderLayout.CENTER);
		this.tablePanel.add(jlabel1,BorderLayout.SOUTH);
		
		//��queryPanel��ʾ��ϵ��ʽ������������������ϵ
		this.queryPanel.setLayout(new BorderLayout());
		jlabel = new JLabel("��������ϵ��ʽ��");
		jlabel1 = new JLabel("2226165160@qq.com");
		jlabel.setFont(new Font("Dialog",   1,   20));
		jlabel.setForeground(Color.yellow);
		jlabel1.setFont(new Font("Dialog",   1,   20));
		jlabel1.setForeground(Color.yellow);
		this.queryPanel.add(jlabel,BorderLayout.CENTER);
		this.queryPanel.add(jlabel1,BorderLayout.SOUTH);
}

}
