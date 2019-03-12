package xin.yohuyotu.HelloWorld.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {
	//�����ݿ����ӳ�һ����ʾ��
	static ComboPooledDataSource dataSource=new ComboPooledDataSource("mysql");
	//�������
	public static  Connection getConnection(){
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("���ݿ����ӳس���");
			e.printStackTrace();
		}
		
		return null;
		
	}
	//�ͷ�����
	public static void close(Connection conn,PreparedStatement pst,ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				System.out.println("���ݿ�رճ���");
				e.printStackTrace();
			}
		}
		if(pst!=null){
			try{
				pst.close();
			}catch(SQLException e){
				System.out.println("���ݿ�رճ���");
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				System.out.println("���ݿ�رճ���");
				e.printStackTrace();
			}
		}
	}
}
