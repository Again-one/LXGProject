package xin.yohuyotu.HelloWorld.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import xin.yohuyotu.HelloWorld.dao.DB;
import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;

/**
 * Servlet implementation class shopping
 */
@WebServlet("/Shopping")
public class Shopping extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shopping() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		switch(method){
		case "addGoods":
			addGoods(request, response);
			break;
		case "sum":
			sum(request,response);
			break;
		case "delete":
			delete(request,response);
			break;
		case "loadGoods":
			loadGoods(request,response);
			break;
		}
		
		
	}
	private void loadGoods(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		
		
		if(username==null){
			return;
		}
		
		try {
			username = new String(username.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		
		String sql="Select userid from user where username=?";
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			bo.rs.next();
			int id=bo.rs.getInt("userid");
			sql="select a.* , b.* from shopping as a,goods as b where a.sid=b.id and a.uid=?";
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, id);
			
			
			DBUtils.executeQuery(bo);
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			while (bo.rs.next()) {
				
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("sid", bo.rs.getInt("sid"));
				map.put("sum", bo.rs.getInt("sum"));
				map.put("summary", bo.rs.getString("summary"));
				map.put("price", bo.rs.getString("price"));
				map.put("picture", bo.rs.getString("picture"));
				list.add(map);
				
			}
			response.setHeader("content-type", "text/html;charset=utf-8");
			String json=JSON.toJSONString(list);
			response.getWriter().write(json);
			response.getWriter().close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			
			DBUtils.realseSource(bo);
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		
		String goods=request.getParameter("goods");
		if(goods==null||username==null){
			return;
		}
		int sid=Integer.parseInt(goods);
		try {
			username = new String(username.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		
		String sql="Select userid from user where username=?";
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			bo.rs.next();
			int id=bo.rs.getInt("userid");
			sql="delete from shopping where uid=? and sid=?";
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, id);
			bo.pst.setInt(2, sid);
			DBUtils.executeUpdate(bo);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
	}

	protected void addGoods(HttpServletRequest request, HttpServletResponse response){
		String username=request.getParameter("username");
		
		String goods=request.getParameter("goods");
		if(goods==null||username==null){
			return;
		}
		int sid=Integer.parseInt(goods);
		try {
			username = new String(username.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		
		String sql="Select userid from user where username=?";
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			if(!bo.rs.next()){
				
				sql="insert into user(username) values(?)";
				bo.conn=C3p0Utils.getConnection();
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setString(1, username);
				DBUtils.executeUpdate(bo);
				bo.conn=C3p0Utils.getConnection();
				sql="Select userid from user where username=?";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setString(1, username);
				
				DBUtils.executeQuery(bo);
				bo.rs.next();
			}
			
			
			int id=bo.rs.getInt("userid");
			sql="Select sum from shopping where uid=? and sid=?";
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, id);
			bo.pst.setInt(2, sid);
			DBUtils.executeQuery(bo);
			if(bo.rs.next()){
				int sum=bo.rs.getInt("sum");
				sum++;
				sql="update shopping set sum=? where sid=? and uid=?";
				
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, sum);
				bo.pst.setInt(2, sid);
				bo.pst.setInt(3, id);
				
				DBUtils.executeUpdate(bo);
			}else{
				sql="insert into shopping (uid,sid,sum) values(?,?,1)";
				bo.conn=C3p0Utils.getConnection();
				bo.pst=bo.conn.prepareStatement(sql);
				
				bo.pst.setInt(1, id);
				bo.pst.setInt(2, sid);
				DBUtils.executeUpdate(bo);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		
	}
	protected void sum(HttpServletRequest request, HttpServletResponse response){
		String username=request.getParameter("username");
		
		if(username==null){
			return;
		}
		try {
			username = new String(username.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		String sql="select userid from user where username=?";
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			if(!bo.rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("state", "1");//获取数据成功
				map.put("sum", "0");
				String json=JSON.toJSONString(map);
				response.getWriter().write(json);
			}else{
				
				int uid=bo.rs.getInt("userid");
				sql="select sum from shopping where uid=?";
				
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				DBUtils.executeQuery(bo);
				int sum=0;
				while(bo.rs.next()){
					int a=bo.rs.getInt("sum");
					sum+=a;
					
				}
				Map<String,String> map=new HashMap<String,String>();
				map.put("state", "1");//获取数据成功
				map.put("sum", ""+sum);
				String json=JSON.toJSONString(map);
				response.getWriter().write(json);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
