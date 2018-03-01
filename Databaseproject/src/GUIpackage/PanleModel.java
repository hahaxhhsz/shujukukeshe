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
 * @author ������
 *
 * 2018��2��25��
 */
public class PanleModel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	//�������ܰ�ť��
	protected JButton[] Gbutton = new JButton[5];
	//���������Ӱ�ťʱ��λ���ڼ�����ť
	private int index = 0;
	//���������
	protected JPanel tablePanel = new JPanel();
	protected JPanel queryPanel = new JPanel();
    protected JPanel infoPanel = new JPanel();
    protected JFrame jFrame = null;
    private dbOperating theDB = null;
	//����GridBagLayout��
	private GridBagConstraints s = new GridBagConstraints();
	//���
    protected JTable jTable = null;
    public  PanleModel(JFrame jFrame,dbOperating db) {
		// ���캯������Ҫ����һ����������Ӻõ����ݿ����
    	this.jFrame = jFrame;
    	this.setLayout(new GridBagLayout());
    	this.theDB = db;
    	 // �������ܰ�ť
    	addButton("��Ϣ����",0,0);
    	addButton("���ڹ���",1,0);
    	addButton("���ʹ���",2,0);
    	addButton("���˲�ѯ",3,0);
    	addButton("���ܲ�ѯ",4,0);
    	//���������
    	addPanel(tablePanel, 0, 2, 14,4);
    	addPanel(queryPanel, 4, 2, 14,1);
    	addPanel(infoPanel, 0, 16, 1,5);
    	
    	//������ʾ�������
    	jTable = new JTable(){
    	        	/**
					 * 
					 */
				private static final long serialVersionUID = 1L;

				//ʹ�������ʾ�����ݲ��ɱ༭
    	        public boolean isCellEditable(int row, int column) {
    	        	return false; 
    	        	}
    	        };

    	 //�������
    	 JScrollPane jscrollPane = new JScrollPane(jTable);
    	 jscrollPane.setPreferredSize(new Dimension(200,100));
    	 tablePanel.setLayout(new BorderLayout());
    	 tablePanel.add(jscrollPane,BorderLayout.CENTER);
    	 jTable.setRowHeight(40);
	}
  //���ñ���ͼƬ
    protected void paintComponent(Graphics g) {  
        ImageIcon icon = new ImageIcon("src\\image\\beijin.jpg");  
        Image img = icon.getImage();  
        g.drawImage(img, 0, 0, icon.getIconWidth(),  
                icon.getIconHeight(), icon.getImageObserver()); 
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
	
}
    
    //�����������ӹ��ܰ�ť
    public void addButton(String name,int x,int y){
        Gbutton[index] = new JButton(name);
        Gbutton[index].setPreferredSize(new Dimension(150,50));
        s.weightx = 100;
        s.weighty = 100;
        s.gridx = x;
        s.gridy = y;
        s.gridheight = 1;
        s.gridwidth = 1;
        //����ť��Ӷ���
        Gbutton[index].addActionListener(this);
        // ������ť��λ����
        Gbutton[index].setFocusable(false); 
        this.add(Gbutton[index],s); 
        index++;
    }
    
    //�����������ӷ����
    public void addPanel(JPanel panel,int x,int y,int xl,int yl){
    	//�������ӱ߿�������
    	panel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
    	//�����������Ϊ͸��
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
  //���ܰ�ť����¼�
  	public void actionPerformed(ActionEvent e) {
  		//�Ƴ�ԭ�����
  		jFrame.remove(this);
  		String type = e.getActionCommand();

  		//���ݲ�ͬ�İ�ť�����ʵ�ֲ�ͬ��������ˢ��
  		switch (type) {
  		case "��Ϣ����":
  			 panel1 newpanel = new panel1(jFrame,theDB);
  	  		 //����ť�Ӹ��߿���ʾ�û������ĸ�����
  			 newpanel.setButtonColor(0);
  	         jFrame.setContentPane(newpanel);
  	         
  			break;
  		case "���ڹ���":
 			 panel2 newpanel2 = new panel2(jFrame,theDB);
  			 newpanel2.setButtonColor(1);
  	         jFrame.setContentPane(newpanel2);
  			break;
  		case "���ʹ���":
			 panel3 newpanel3 = new panel3(jFrame,theDB);
  			 newpanel3.setButtonColor(2);
  	         jFrame.setContentPane(newpanel3);
  			break;
  		case "���˲�ѯ":
			 panel4 newpanel4 = new panel4(jFrame,theDB);
  			 newpanel4.setButtonColor(3);
  	         jFrame.setContentPane(newpanel4);
  			break;
  		case "���ܲ�ѯ":
			 panel5 newpanel5 = new panel5(jFrame,theDB);
  			 newpanel5.setButtonColor(4);
  	         jFrame.setContentPane(newpanel5);
  			break;
  		default:
  			break;
  		}
  		//ʹ�����²�����
          jFrame.validate();
          jFrame.repaint(); 
  	}
  //���ð�ť�߿�
  	public void setButtonColor(int index){
	    LineBorder b = new LineBorder(Color.YELLOW, 2);
	    Gbutton[index].setBorder(b);
  	}
  //�ж��Ƿ�һ���Ϸ�ʱ��
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
