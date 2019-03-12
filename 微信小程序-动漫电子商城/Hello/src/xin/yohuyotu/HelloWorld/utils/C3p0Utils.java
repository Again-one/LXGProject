package xin.yohuyotu.HelloWorld.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {
	//给数据库连接池一个标示符
	static ComboPooledDataSource dataSource=new ComboPooledDataSource("mysql");
	//获得链接
	public static  Connection getConnection(){
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("数据库连接池出错");
			e.printStackTrace();
		}
		
		return null;
		
	}
	//释放链接
	public static void close(Connection conn,PreparedStatement pst,ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				System.out.println("数据库关闭出错");
				e.printStackTrace();
			}
		}
		if(pst!=null){
			try{
				pst.close();
			}catch(SQLException e){
				System.out.println("数据库关闭出错");
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				System.out.println("数据库关闭出错");
				e.printStackTrace();
			}
		}
	}
}
