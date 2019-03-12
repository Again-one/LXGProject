package xin.yohuyotu.HelloWorld.dao;

import java.sql.SQLException;

import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;
/**
 * 测试数据库的连接
 * @author d
 *
 */
public class DB {
	public DB(){
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		String sql="insert into user values(2,'sdfsdf')";
		try {
			System.out.println("执行");
			System.out.println(bo.conn);
			bo.pst=bo.conn.prepareStatement(sql);
			DBUtils.executeUpdate(bo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
