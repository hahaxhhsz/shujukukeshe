package operating;


import java.sql.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import GUIpackage.Mydialog;

/**
 * @author ������
 *
 * 2018��2��25��
 */
public class dbOperating {
    // JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/salarymanagement";
 
    // ���ݿ���û��������룬��¼�����д�����
    private String USER = "";
    private String PASS = "";
    
    //���ݿ�����ӺͲ�ѯ��������Ա�ȫ����������
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    public dbOperating(){  
        // ע�� JDBC ����
    	try{
    		Class.forName(JDBC_DRIVER);
    	}
    	catch (Exception e) {
            // ���� Class.forName ����
            e.printStackTrace();
		}
    }
    
    public boolean connect(String user,String pass){
    	USER = user;
    	PASS = pass;
    	try{
    		 // ������,�������ݿ�
    		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
    	}catch (Exception e) {
    		//����������ݿ�ʧ�ܣ�֤������ʧ�ܣ�����False
			return false;
		}
    	//�������ݿ�ɹ�������true
    	return true;
    }
    
    //����������
    public void insert(String[] datas,int flag){
    	String sql = null;
      	if(isNull(datas)){
      		new Mydialog("Ա���ź�ʱ�䣨���У������������û������0����");
			return;
      	}
    	//����flag�Ĳ�ͬ���жϲ���(���Բ�ͬ���)�Ĳ�ͬ,���첻ͬsql���
    	if(flag==3){
    		sql = "insert into salary values("+datas[0]+","
    				+packageString(datas[1])+","
    				+datas[2]+","
    				+datas[3]+","
    				+datas[4]+","
    				+datas[5]+","
    				+datas[6]+","
    				+datas[7]+","
    				+datas[8]+","
    				+datas[9]+","
    				+datas[10]+")";
    	}
    	else if(flag==2) {
    		sql = "insert into attendance values("+datas[0]+","
    				+packageString(datas[1])+","
    				+datas[2]+","
    				+datas[3]+","
    				+datas[4]+","
    				+datas[5]+")";
		}
    	else if (flag==1) {
			sql = "insert into employee values("+datas[0]+","
	 				+packageString(datas[1])+","
    				+packageString(datas[3])+","
    				+datas[4]+","
    				+datas[2]+")";
		}
    	
    	
    		try{
    			stmt = (Statement)conn.createStatement();
    			stmt.executeUpdate(sql);
    			new Mydialog("����ɹ�");
    		}catch (MySQLIntegrityConstraintViolationException e) {
				// �����ݿ��ֵ�ظ�ʱ�ᱨ��
    			e.printStackTrace();
    			new Mydialog("�Ѿ��м�¼���޷��ظ����룬ֻ���޸Ļ�ɾ��");
    		}
    		catch (Exception e) {
    			new Mydialog("����ʧ��");
    			e.printStackTrace();
			}

    }
    
    //���²���
    public void update(String[] datas,int flag){
      	String sql = null;
      	if(isNull(datas)){
      		new Mydialog("Ա���ź�ʱ�䣨���У������������û������0����");
			return;
      	}
      	//����flag�Ĳ�ͬ���жϸ���(���Բ�ͬ���)�Ĳ�ͬ,���첻ͬsql���
    	if(flag==2){
    		sql = "update attendance set latecount="+datas[2]
    				+",learlyount="+datas[3]
    				+",leavecount="+datas[4]
    				+",overtimecount="+datas[5]
    				+ " where pno="+datas[0]+" and time="
    				+packageString(datas[1]);
    	}
    	else if (flag==1) {
			sql = "update employee set position="+packageString(datas[3])
					+",name="+packageString(datas[1])
					+",thetime="+datas[4]
					+",dno=" +datas[2]
					+" where pno="+datas[0];
		}
		try{
			stmt = (Statement)conn.createStatement();
			int count = stmt.executeUpdate(sql);
			if(count==0){
			   new Mydialog("�޸�Ա��������Ϣ�����Ȳ���");
			}
			else {
				new Mydialog("���³ɹ�");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			new Mydialog("����ʧ��");
		}
    }
    
    //ɾ������
    public void delete(String[] datas,int flag){
    	String sql = null;
      	if(isNull(datas)){
			new Mydialog("Ա���ź�ʱ�䣨���У������������û������0����");
			return;
      	}
      	
    	//����flag�Ĳ�ͬ���ж�ɾ��(���Բ�ͬ���)�Ĳ�ͬ,���첻ͬsql���
    	if(flag==2){
    		sql = "delete from attendance where pno="+datas[0]+" and time="
    				+packageString(datas[1]);
    	}else if (flag==1) {
      		sql = "delete from employee where pno="+datas[0];
		}
    	
		try{
			stmt = (Statement)conn.createStatement();
			int count = stmt.executeUpdate(sql);
			if(count==0){
				   new Mydialog("�޸�Ա��������Ϣ�����Ȳ���");
				}
			else {
				new Mydialog("ɾ���ɹ�");
			}
		}catch (Exception e) {
			e.printStackTrace();
			new Mydialog("ɾ��ʧ��");
		}
    }
    
    //��ѯ����,����flag�Ĳ�ͬ���жϲ�ѯ(���Բ�ͬ���)�Ĳ�ͬ
    public String[][] query(int num,int flag){
    
    		String[][] datas = null;
    		String sql = null;
    		if(flag==0){
    		sql = "select * from gongziguanli where pno="+num;
    		}
    		else{
    		sql = "select * from info where pno="+num;	
    		}
    		try {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = stmt.executeQuery(sql);
				int rowcount = 0;
				rs.last();
				//��ò�ѯ����ж����У����ڹ�������
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
				datas = new String[rowcount][11];
				//��Ӧ�ع���Jtabel���������
				for(int i = 0;i<rowcount;i++){
					datas[i][0] = rs.getInt(4)+"";
					datas[i][1] = rs.getString(1);
					datas[i][2] = rs.getString(10);
					datas[i][3] = rs.getString(2);
					datas[i][4] = rs.getInt(3)+"";
					datas[i][5] = rs.getString(5);
					datas[i][6] = rs.getInt(6)+"";
					datas[i][7] = rs.getInt(7)+"";
					datas[i][8] = rs.getInt(8)+"";
					datas[i][9] = rs.getInt(9)+"";
					datas[i][10] = rs.getInt(11)+"";
					//����ѯ��ָ��ָ����һ��
					rs.next();
				   }
				}
				else{
					datas = new String[rowcount][13];
					for(int i = 0;i<rowcount;i++){
						datas[i][0] = rs.getInt(1)+"";
						datas[i][1] = rs.getString(2);
						datas[i][2] = rs.getString(3);
						datas[i][3] = rs.getString(4);
						datas[i][4] = rs.getDouble(5)+"";
						datas[i][5] = rs.getDouble(6)+"";
						datas[i][6] = rs.getDouble(7)+"";
						datas[i][7] = rs.getDouble(8)+"";
						datas[i][8] = rs.getDouble(9)+"";
						datas[i][9] = rs.getDouble(10)+"";
						datas[i][10] = rs.getDouble(11)+"";
						datas[i][11] = rs.getDouble(12)+"";
						datas[i][12] = rs.getDouble(13)+"";
						rs.next();
					   }
				}
			} catch (SQLException e) {
    			e.printStackTrace();
				new Mydialog("��ѯʧ��");
			}
    		return datas;

    }
    //�ع���ѯ����1
    public String[][] query(int num,String time,int flag){
    	
    		String sql = null;
     		String[][] datas = null;
    		time = packageString(time);
    		if(flag==0){
    			sql = "select * from gongziguanli where pno="+num+" and time="+time;
    		}
    		else{
    			sql = "select * from info where pno="+num+" and time="+time;
    		}
    		try {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = stmt.executeQuery(sql);
				int rowcount = 0;
				rs.last();
				//��ò�ѯ����ж����У����ڹ�������
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
					datas = new String[rowcount][11];
					//��Ӧ�ع���Jtabel���������
					for(int i = 0;i<rowcount;i++){
						datas[i][0] = rs.getInt(4)+"";
						datas[i][1] = rs.getString(1);
						datas[i][2] = rs.getString(10);
						datas[i][3] = rs.getString(2);
						datas[i][4] = rs.getInt(3)+"";
						datas[i][5] = rs.getString(5);
						datas[i][6] = rs.getInt(6)+"";
						datas[i][7] = rs.getInt(7)+"";
						datas[i][8] = rs.getInt(8)+"";
						datas[i][9] = rs.getInt(9)+"";
						datas[i][10] = rs.getInt(11)+"";
						//����ѯ��ָ��ָ����һ��
						rs.next();
					   }
					}
					else{
						datas = new String[rowcount][13];
						for(int i = 0;i<rowcount;i++){
							datas[i][0] = rs.getInt(1)+"";
							datas[i][1] = rs.getString(2);
							datas[i][2] = rs.getString(3);
							datas[i][3] = rs.getString(4);
							datas[i][4] = rs.getDouble(5)+"";
							datas[i][5] = rs.getDouble(6)+"";
							datas[i][6] = rs.getDouble(7)+"";
							datas[i][7] = rs.getDouble(8)+"";
							datas[i][8] = rs.getDouble(9)+"";
							datas[i][9] = rs.getDouble(10)+"";
							datas[i][10] = rs.getDouble(11)+"";
							datas[i][11] = rs.getDouble(12)+"";
							datas[i][12] = rs.getDouble(13)+"";
							rs.next();
						   }
					}
			} catch (SQLException e) {
    			e.printStackTrace();
    			new Mydialog("��ѯʧ��");
			}
    		return datas;

    }
    //�ع���ѯ����2
    public String[][] query(String name,String time,int flag){
    	
 
    		String[][] datas = null;
    		String sql =null;
    		time = packageString(time);
    		name = packageString(name);
    	   	if(flag==0){
    	   		sql = "select * from gongziguanli where name="+name+" and time="+time;
    	   	}
    	   	else if(flag==4){
        		sql = "select * from info where name="+name+" and time="+time;	
    	   	}
    	   	else {
    	   		sql = "select * from info where dname="+name+" and time="+time;
			}
    		try {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = stmt.executeQuery(sql);
				int rowcount = 0;
				rs.last();
				//��ò�ѯ����ж����У����ڹ�������
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
					datas = new String[rowcount][11];
					//��Ӧ�ع���Jtabel���������
					for(int i = 0;i<rowcount;i++){
						datas[i][0] = rs.getInt(4)+"";
						datas[i][1] = rs.getString(1);
						datas[i][2] = rs.getString(10);
						datas[i][3] = rs.getString(2);
						datas[i][4] = rs.getInt(3)+"";
						datas[i][5] = rs.getString(5);
						datas[i][6] = rs.getInt(6)+"";
						datas[i][7] = rs.getInt(7)+"";
						datas[i][8] = rs.getInt(8)+"";
						datas[i][9] = rs.getInt(9)+"";
						datas[i][10] = rs.getInt(11)+"";
						//����ѯ��ָ��ָ����һ��
						rs.next();
					   }
					}
					else{
						datas = new String[rowcount][13];
						for(int i = 0;i<rowcount;i++){
							datas[i][0] = rs.getInt(1)+"";
							datas[i][1] = rs.getString(2);
							datas[i][2] = rs.getString(3);
							datas[i][3] = rs.getString(4);
							datas[i][4] = rs.getDouble(5)+"";
							datas[i][5] = rs.getDouble(6)+"";
							datas[i][6] = rs.getDouble(7)+"";
							datas[i][7] = rs.getDouble(8)+"";
							datas[i][8] = rs.getDouble(9)+"";
							datas[i][9] = rs.getDouble(10)+"";
							datas[i][10] = rs.getDouble(11)+"";
							datas[i][11] = rs.getDouble(12)+"";
							datas[i][12] = rs.getDouble(13)+"";
							rs.next();
						   }
					}
			}catch (SQLException e){
    			e.printStackTrace();
    			new Mydialog("��ѯʧ��");
			}
    		return datas;
 
    }

    //�ع���ѯ����3
    public String[][] query(String timeorname,int flag){
    	
  
    		String[][] datas = null;
    		String sql = null;
    		timeorname = packageString(timeorname);
    		if(timeorname.contains("-")){
    			if(flag==0){
        		    sql = "select * from gongziguanli where time="+timeorname;
    			}
    			else{
    				sql = "select * from info where time="+timeorname;
    			}
    		}else {
    			if(flag==0){
        		    sql = "select * from gongziguanli where name="+timeorname;
    			}
    			else if(flag==4){
    				sql = "select * from info where name="+timeorname;	
    			}
    			else {
    				sql = "select * from info where dname="+timeorname;
				}
    		}

    		try {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = stmt.executeQuery(sql);
				int rowcount = 0;
				rs.last();
				//��ò�ѯ����ж����У����ڹ�������
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
					datas = new String[rowcount][11];
					//��Ӧ�ع���Jtabel���������
					for(int i = 0;i<rowcount;i++){
						datas[i][0] = rs.getInt(4)+"";
						datas[i][1] = rs.getString(1);
						datas[i][2] = rs.getString(10);
						datas[i][3] = rs.getString(2);
						datas[i][4] = rs.getInt(3)+"";
						datas[i][5] = rs.getString(5);
						datas[i][6] = rs.getInt(6)+"";
						datas[i][7] = rs.getInt(7)+"";
						datas[i][8] = rs.getInt(8)+"";
						datas[i][9] = rs.getInt(9)+"";
						datas[i][10] = rs.getInt(11)+"";
						//����ѯ��ָ��ָ����һ��
						rs.next();
					   }
					}
					else{
						datas = new String[rowcount][13];
						for(int i = 0;i<rowcount;i++){
							datas[i][0] = rs.getInt(1)+"";
							datas[i][1] = rs.getString(2);
							datas[i][2] = rs.getString(3);
							datas[i][3] = rs.getString(4);
							datas[i][4] = rs.getDouble(5)+"";
							datas[i][5] = rs.getDouble(6)+"";
							datas[i][6] = rs.getDouble(7)+"";
							datas[i][7] = rs.getDouble(8)+"";
							datas[i][8] = rs.getDouble(9)+"";
							datas[i][9] = rs.getDouble(10)+"";
							datas[i][10] = rs.getDouble(11)+"";
							datas[i][11] = rs.getDouble(12)+"";
							datas[i][12] = rs.getDouble(13)+"";
							rs.next();
						   }
					}
			}catch(SQLException e) {
				e.printStackTrace();
				new Mydialog("��ѯʧ��");
			}
    		return datas;

    }
    //�ع���ѯ����4
    public String[][] query(int flag){
    	
   
    		String[][] datas = null;
    		String sql = null;
    		if(flag==0){
    			sql = "select * from gongziguanli";
    		}
    		else{
    			sql = "select * from info";
    		}
    		try {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = stmt.executeQuery(sql);
				int rowcount = 0;
				rs.last();
				//��ò�ѯ����ж����У����ڹ�������
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
					datas = new String[rowcount][11];
					//��Ӧ�ع���Jtabel���������
					for(int i = 0;i<rowcount;i++){
						datas[i][0] = rs.getInt(4)+"";
						datas[i][1] = rs.getString(1);
						datas[i][2] = rs.getString(10);
						datas[i][3] = rs.getString(2);
						datas[i][4] = rs.getInt(3)+"";
						datas[i][5] = rs.getString(5);
						datas[i][6] = rs.getInt(6)+"";
						datas[i][7] = rs.getInt(7)+"";
						datas[i][8] = rs.getInt(8)+"";
						datas[i][9] = rs.getInt(9)+"";
						datas[i][10] = rs.getInt(11)+"";
						//����ѯ��ָ��ָ����һ��
						rs.next();
					   }
					}
					else{
						datas = new String[rowcount][13];
						for(int i = 0;i<rowcount;i++){
							datas[i][0] = rs.getInt(1)+"";
							datas[i][1] = rs.getString(2);
							datas[i][2] = rs.getString(3);
							datas[i][3] = rs.getString(4);
							datas[i][4] = rs.getDouble(5)+"";
							datas[i][5] = rs.getDouble(6)+"";
							datas[i][6] = rs.getDouble(7)+"";
							datas[i][7] = rs.getDouble(8)+"";
							datas[i][8] = rs.getDouble(9)+"";
							datas[i][9] = rs.getDouble(10)+"";
							datas[i][10] = rs.getDouble(11)+"";
							datas[i][11] = rs.getDouble(12)+"";
							datas[i][12] = rs.getDouble(13)+"";
							rs.next();
						   }
					}
			}catch(SQLException e) {
    			e.printStackTrace();
    			new Mydialog("��ѯʧ��");
			}
    		return datas;


    }
    
    //��Ϊsql������ַ���Ҫ�ӵ����ţ�������Ҫ��װ
    private String packageString(String data) {
		return "'"+data+"'";
	}
    //��һ���������ж������Ƿ��У����������Ч���ݣ�����sql����ʽ����
    private boolean isNull(String[] datas) {
		for(String data : datas){
			if(data.equals("")){
				return true;
			}
		}
		return false;
	}
    
    //�ر������ݿ�����ӣ��ͷ���Դ
    public boolean close() {
    	try{
        if(rs!=null){
        	rs.close();
        }	
    	if(stmt!=null){
    		stmt.close();
    	}
    	if(conn!=null){
    		conn.close();
    	}
        }catch (Exception e) {
			e.printStackTrace();
			new Mydialog("���ݿ�ر��쳣");
			return false;
		}
		return true;
	}
    
    //�رն����ݿ��Statement�����ͷ���Դ
    public boolean closeState() {
    	try{
    	if(rs!=null){
    		rs.close();
    	}
    	if(stmt!=null){
    		stmt.close();
    	}
        }catch(Exception e) {
			e.printStackTrace();
			new Mydialog("���ݿ�Statement����ر��쳣");
			return false;
		}
		return true;
	}
    
}
