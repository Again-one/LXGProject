package xin.yohuyotu.HelloWorld.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
 * Servlet implementation class Goods
 */
@WebServlet("/Goods")
public class Goods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Goods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		Map<String,String> map= new HashMap<String,String>();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		String sql="Select summary,price,pay,detail,picture from goods ";
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			DBUtils.executeQuery(bo);
			while (bo.rs.next()) {
				map= new HashMap<String,String>();
				String summary=bo.rs.getString("summary");
				String price=bo.rs.getString("price");
				String pay=bo.rs.getString("pay");
				
				String picture=bo.rs.getString("picture");
				map.put("summary",summary );
				map.put("price",price);
				map.put("pay", pay);
				
				map.put("picture", picture);
				list.add(map);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		String json=JSON.toJSONString(list);
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
