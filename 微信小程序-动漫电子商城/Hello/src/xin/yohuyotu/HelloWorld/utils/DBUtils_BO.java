package xin.yohuyotu.HelloWorld.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * BO������Ҫ��װ��һ�����ݿ����ӡ�
 * һ��Statement/PreStatement���Ƽ�������ȫ��
 * ��һ�����������������DAO���һ�����ݿ������
 * �ɷ�װ�����bo�����ֱ�Ӱ�������󴫸����ݿ�
 * ������ִ�����ݿ�������ɡ�������ɺ�Ľ����Ҳ
 * ��װ�������bo���������Ҫ��ȡ����ĵط�ͨ
 * �����bo�����rs�ֶν��н����ȡ���ɡ�
 * @author d
 *
 */
public class DBUtils_BO {
	public Connection conn=null;
	public PreparedStatement pst=null;
	public ResultSet rs=null;
	
}
