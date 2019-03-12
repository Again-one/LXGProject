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
import xin.yohuyotu.HelloWorld.utils.State;

/**
 * Servlet implementation class Pay
 */
@WebServlet("/Pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		switch(method){
		case "pay":
			pay(request,response);
			break;
		}
	}

	private void pay(HttpServletRequest request, HttpServletResponse response) {
		String oid=request.getParameter("oid");
		if(oid==null){
			return;
		}
		DBUtils_BO bo=new DBUtils_BO();
		try {
			bo.conn=C3p0Utils.getConnection();
			String sql;
			sql="update order2 set isFu=1 where oid=?";
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, oid);
			
			int c=DBUtils.executeUpdate(bo);
			Map<String,Object> map=new HashMap<String,Object>();
			if(c>0){
				map.put("state", 1);
				map.put("message", "支付成功");
				State.state(response, map);
				
			}else{
				map.put("state", 0);
				map.put("message", "支付失败");
				State.state(response, map);
			}
				
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
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
