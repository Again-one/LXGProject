package xin.yohuyotu.HelloWorld.servlet;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.alibaba.fastjson.TypeReference;

import xin.yohuyotu.HelloWorld.bean.MessageItem;
import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;
import xin.yohuyotu.HelloWorld.utils.State;

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static boolean createUser(String username){
    	String sql="select * from user where username=?";
    	DBUtils_BO bo=new DBUtils_BO();
    	bo.conn=C3p0Utils.getConnection();
    	try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			if(bo.rs.next()){
				DBUtils.realseSource(bo);
				return false;
			}else{
				sql="insert into user(username) values(?)";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setString(1, username);
				DBUtils.executeUpdate(bo);
				return true;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DBUtils.realseSource(bo);
			e.printStackTrace();
		}
		return false;
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		
		switch (method) {
		case "addMessage":
			addMessage(request,response);
			break;
		case "message":
			message(request,response);
			break;
		case "deleteMessage":
			deleteMessage(request,response);
			break;
		case "hot":
			hot(request,response);
			break;
		
		}
	}
	private void hot(HttpServletRequest request, HttpServletResponse response) {
		
		DBUtils_BO bo=new DBUtils_BO();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			
			bo.conn=C3p0Utils.getConnection();
			int start=(int)(7*Math.random())*10;
			String sql="select * from goods where recommend=0 limit ?,20";
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, start);
			DBUtils.executeQuery(bo);
			while(bo.rs.next()){
				Map<String,Object> map=new HashMap<String,Object>();
				String summary=bo.rs.getString("summary");
				String price=bo.rs.getString("price");
				String pay=bo.rs.getString("pay");
				String picture=bo.rs.getString("picture");
				int id=bo.rs.getInt("id");
				int size=bo.rs.getInt("size");
				map.put("summary",summary );
				map.put("price",price);
				map.put("pay", pay);
				map.put("picture", picture);
				map.put("id", id);
				map.put("size", size);
				list.add(map);
			};
			String json=JSON.toJSONString(list);
			response.setHeader("content-type", "text/html;charset=utf-8");
			response.getWriter().write(json);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);;
		}
		
		
	}
	private void deleteMessage(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		String a=request.getParameter("mid");
		if(username==null||a==null){
			return;
		}
		try {
			username=URLDecoder.decode(username,"utf-8");
			int mid=Integer.parseInt(a);
			String sql="select userid from user where username=?";
			DBUtils_BO bo=new DBUtils_BO();
			bo.conn=C3p0Utils.getConnection();
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			Map<String,String> map=new HashMap<String,String>();
			if(bo.rs.next()){
				int uid=bo.rs.getInt("userid");
				sql="delete from message where uid=? and mid=?";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				bo.pst.setInt(2, mid);
				int sum=DBUtils.executeUpdate(bo);
				if(sum>0){
					map.put("state", "1");
					map.put("message", "加载成功");
					State.state(response, map);
				}else{
					map.put("state", "0");
					map.put("message", "加载失败");
					State.state(response, map);
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void message(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		if(username==null){
			return;
		}
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		DBUtils_BO bo=new DBUtils_BO();
		try {
			username=URLDecoder.decode(username,"utf-8");
			String sql="select userid from user where username=?";
			
			bo.conn=C3p0Utils.getConnection();
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			if(bo.rs.next()){
				
				int uid=bo.rs.getInt("userid");
				sql="select * from message where uid=?";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				DBUtils.executeQuery(bo);
				
				while(bo.rs.next()){
					Map<String,Object> map=new HashMap<String,Object>();
					int mid=bo.rs.getInt("mid");
					String link=bo.rs.getString("link");
					String mobile=bo.rs.getString("mobile");
					String city=bo.rs.getString("city");
					String code=bo.rs.getString("code");
					String address=bo.rs.getString("address");
					map.put("mid", mid);
					map.put("link", link);
					map.put("mobile", mobile);
					map.put("address", address);
					map.put("city", city);
					map.put("code", code);
					list.add(map);
				}
				String json=JSON.toJSONString(list);
				response.setHeader("content-type", "text/html;charset=utf-8");
				response.getWriter().write(json);
				response.getWriter().close();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.realseSource(bo);
		}
		
	}
	private void addMessage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String message=request.getParameter("message");
		
		try {
			message=URLDecoder.decode(message,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MessageItem item=JSON.parseObject(message,new TypeReference<MessageItem>(){});
		
		
		
		if(username==null||message==null){
			return;
		}
		try {
			username = URLDecoder.decode(username,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createUser(username);
		String sql="select * from user where username=?";
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		Map<String,String> map=new HashMap<String,String>();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			if(bo.rs.next()){
				int uid=bo.rs.getInt("userid");
				sql="insert into message (uid,link,mobile,city,address,code) values(?,?,?,?,?,?)";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				bo.pst.setString(2, item.getLink());
				bo.pst.setString(3, item.getMobile());
				bo.pst.setString(4, item.getCity());
				bo.pst.setString(5, item.getAddress());
				bo.pst.setString(6, item.getCode());
				DBUtils.executeUpdate(bo);
				map.put("state'", "1");
				map.put("message","成功添加" );
			}else{
				map.put("state'", "0");
				map.put("message","添加失败，请重新添加" );
			}
			String json=JSON.toJSONString(map);
			response.setHeader("content-type", "text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().close();
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