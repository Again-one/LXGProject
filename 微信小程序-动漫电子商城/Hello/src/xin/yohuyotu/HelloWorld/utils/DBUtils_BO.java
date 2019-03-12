package xin.yohuyotu.HelloWorld.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * BO类中主要封装了一个数据库连接、
 * 一个Statement/PreStatement（推荐，更安全）
 * 、一个结果集。这样，在DAO层的一次数据库操作就
 * 可封装在这个bo对象里，直接把这个对象传给数据库
 * 操作类执行数据库操作即可。操作完成后的结果集也
 * 封装在了这个bo对象里，在需要提取结果的地方通
 * 过这个bo对象的rs字段进行结果提取即可。
 * @author d
 *
 */
public class DBUtils_BO {
	public Connection conn=null;
	public PreparedStatement pst=null;
	public ResultSet rs=null;
	
}
