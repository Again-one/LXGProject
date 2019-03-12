package xin.yohuyotu.HelloWorld.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
 * Servlet implementation class Cid
 */
@WebServlet("/Cid")
public class Cid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cid() {
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
		
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		try {
			String sql;
			sql="select * from classify ";
			bo.pst=bo.conn.prepareStatement(sql);
			
			DBUtils.executeQuery(bo);
			List<Map<String,Object>> list=new LinkedList<Map<String,Object>>();
			
			while(bo.rs.next()){
				Map map= new HashMap<String,Object>();
				String name=bo.rs.getString("name");
				
				int cid=bo.rs.getInt("cid");
				map.put("cid",cid);
				map.put("name",name);
				
				list.add(map);
			}
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
