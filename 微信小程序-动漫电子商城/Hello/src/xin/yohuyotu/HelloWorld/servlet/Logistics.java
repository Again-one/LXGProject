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

import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;

/**
 * Servlet implementation class Logistics
 */
@WebServlet("/Logistics")
public class Logistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logistics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		switch(method){
		case "all":
			all(request,response);
			break;
		}
	}

	private void all(HttpServletRequest request, HttpServletResponse response) {
		
		String username=request.getParameter("username");
		
		if(username==null){
			return;
		}
		DBUtils_BO bo=new DBUtils_BO();
		try {
			username=URLDecoder.decode(username,"utf-8");
			String sql="select userid from user where username=?";
			bo.conn=C3p0Utils.getConnection();
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			Map<String,Object> map= new HashMap<String,Object>();
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			if(bo.rs.next()){
				int uid=bo.rs.getInt("userid");
				sql="select isFa,oid from order2 where isFu=1 and uid=? ";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				DBUtils.executeQuery(bo);
				while(bo.rs.next()){
					map= new HashMap<String,Object>();
					
					int isFa=bo.rs.getInt("isFa");
					String oid=bo.rs.getString("oid");
					
					map.put("isFa", isFa);
					map.put("oid", oid);
					list.add(map);
				}
				String json=JSON.toJSONString(list);
				response.setHeader("content-type", "text/html;charset=utf-8");
				response.getWriter().write(json);
				
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
