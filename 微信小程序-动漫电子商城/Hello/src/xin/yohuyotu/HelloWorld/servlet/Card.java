package xin.yohuyotu.HelloWorld.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;
import xin.yohuyotu.HelloWorld.utils.State;

/**
 * Servlet implementation class Card
 */
@WebServlet("/Card")
public class Card extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Card() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		switch(method){
		
		case "add":
			add(request,response);
			break;
		case "all":
			all(request,response);
			break;
		case "gain":
			gain(request,response);
			break;
		}
	}

	private void gain(HttpServletRequest request, HttpServletResponse response) {
String username=request.getParameter("username");
		
		
		if(username==null){
			return;
		}
		
		try {
			username=URLDecoder.decode(username,"utf-8");
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
			sql="select cardid from cardbag where uid=?";
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, id);
			
			DBUtils.executeQuery(bo);
			Map<String,Object> map=new HashMap<String,Object>();
			int size=1;
			while(bo.rs.next()){
				
				map.put(""+size, bo.rs.getInt("cardid"));
				size++;
				
			}
			State.state(response, map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		
		String card=request.getParameter("card");
		if(card==null||username==null){
			return;
		}
		int cardid=Integer.parseInt(card);
		try {
			username=URLDecoder.decode(username,"utf-8");
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
			sql="insert into cardbag (uid,cardid) values(?,?)";
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, id);
			bo.pst.setInt(2, cardid);
			int c=DBUtils.executeUpdate(bo);
			if(c>0){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("state", 1);
				map.put("message", "成功获得卡卷");
				State.state(response, map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		
	}

	private void all(HttpServletRequest request, HttpServletResponse response) {
		
		String username=request.getParameter("username");
		
		String card=request.getParameter("card");
		if(card==null||username==null){
			return;
		}
		int cardid=Integer.parseInt(card);
		try {
			username=URLDecoder.decode(username,"utf-8");
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
			
			while(bo.rs.next()){
				int id=bo.rs.getInt("userid");
				sql="select * from cardbag where uid=? and cardid=?";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, id);
				bo.pst.setInt(2, cardid);
				DBUtils.executeQuery(bo);
				if(bo.rs.next()){
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("state", 1);
					map.put("message", "以有卡卷");
					State.state(response, map);
				}
			}
			
		} catch (SQLException e) {
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
