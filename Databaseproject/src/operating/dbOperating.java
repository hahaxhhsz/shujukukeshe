package operating;


import java.sql.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import GUIpackage.Mydialog;

/**
 * @author 王晨超
 *
 * 2018年2月25日
 */
public class dbOperating {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/salarymanagement";
 
    // 数据库的用户名与密码，登录界面中传进来
    private String USER = "";
    private String PASS = "";
    
    //数据库的连接和查询结果对象，以便全部函数调用
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    public dbOperating(){  
        // 注册 JDBC 驱动
    	try{
    		Class.forName(JDBC_DRIVER);
    	}
    	catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
		}
    }
    
    public boolean connect(String user,String pass){
    	USER = user;
    	PASS = pass;
    	try{
    		 // 打开链接,连接数据库
    		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
    	}catch (Exception e) {
    		//如果连接数据库失败，证明登入失败，返回False
			return false;
		}
    	//连接数据库成功，返回true
    	return true;
    }
    
    //插入新数据
    public void insert(String[] datas,int flag){
    	String sql = null;
      	if(isNull(datas)){
      		new Mydialog("员工号和时间（若有）必填，其他项如没有请填0或无");
			return;
      	}
    	//根据flag的不同来判断插入(来自不同面板)的不同,构造不同sql语句
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
    			new Mydialog("插入成功");
    		}catch (MySQLIntegrityConstraintViolationException e) {
				// 当数据库键值重复时会报错
    			e.printStackTrace();
    			new Mydialog("已经有记录，无法重复插入，只能修改或删除");
    		}
    		catch (Exception e) {
    			new Mydialog("插入失败");
    			e.printStackTrace();
			}

    }
    
    //更新操作
    public void update(String[] datas,int flag){
      	String sql = null;
      	if(isNull(datas)){
      		new Mydialog("员工号和时间（若有）必填，其他项如没有请填0或无");
			return;
      	}
      	//根据flag的不同来判断更新(来自不同面板)的不同,构造不同sql语句
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
			   new Mydialog("无该员工该月信息，请先插入");
			}
			else {
				new Mydialog("更新成功");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			new Mydialog("更新失败");
		}
    }
    
    //删除操作
    public void delete(String[] datas,int flag){
    	String sql = null;
      	if(isNull(datas)){
			new Mydialog("员工号和时间（若有）必填，其他项如没有请填0或无");
			return;
      	}
      	
    	//根据flag的不同来判断删除(来自不同面板)的不同,构造不同sql语句
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
				   new Mydialog("无该员工该月信息，请先插入");
				}
			else {
				new Mydialog("删除成功");
			}
		}catch (Exception e) {
			e.printStackTrace();
			new Mydialog("删除失败");
		}
    }
    
    //查询操作,根据flag的不同来判断查询(来自不同面板)的不同
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
				//获得查询结果有多少行，便于构建数组
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
				datas = new String[rowcount][11];
				//对应地构建Jtabel所需的数组
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
					//将查询集指针指向下一行
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
				new Mydialog("查询失败");
			}
    		return datas;

    }
    //重构查询函数1
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
				//获得查询结果有多少行，便于构建数组
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
					datas = new String[rowcount][11];
					//对应地构建Jtabel所需的数组
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
						//将查询集指针指向下一行
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
    			new Mydialog("查询失败");
			}
    		return datas;

    }
    //重构查询函数2
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
				//获得查询结果有多少行，便于构建数组
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
					datas = new String[rowcount][11];
					//对应地构建Jtabel所需的数组
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
						//将查询集指针指向下一行
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
    			new Mydialog("查询失败");
			}
    		return datas;
 
    }

    //重构查询函数3
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
				//获得查询结果有多少行，便于构建数组
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
					datas = new String[rowcount][11];
					//对应地构建Jtabel所需的数组
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
						//将查询集指针指向下一行
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
				new Mydialog("查询失败");
			}
    		return datas;

    }
    //重构查询函数4
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
				//获得查询结果有多少行，便于构建数组
				rowcount = rs.getRow();
				if(rowcount==0){
					return null;
				}
				rs.first();
				if(flag==0){
					datas = new String[rowcount][11];
					//对应地构建Jtabel所需的数组
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
						//将查询集指针指向下一行
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
    			new Mydialog("查询失败");
			}
    		return datas;


    }
    
    //因为sql语句中字符串要加单引号，所以需要包装
    private String packageString(String data) {
		return "'"+data+"'";
	}
    //用一个方法来判断数据是否都有，避免出现无效数据，导致sql语句格式出错
    private boolean isNull(String[] datas) {
		for(String data : datas){
			if(data.equals("")){
				return true;
			}
		}
		return false;
	}
    
    //关闭与数据库的连接，释放资源
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
			new Mydialog("数据库关闭异常");
			return false;
		}
		return true;
	}
    
    //关闭对数据库的Statement对象，释放资源
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
			new Mydialog("数据库Statement对象关闭异常");
			return false;
		}
		return true;
	}
    
}
