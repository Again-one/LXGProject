package xin.yohuyotu.HelloWorld.servlet;

import java.io.IOException;
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
 * Servlet implementation class CidGoods
 */
@WebServlet("/CidGoods")
public class CidGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CidGoods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		switch(method){
		case "cid":
			cid(request,response);
			
			break;
		
		}
	}

	private void cid(HttpServletRequest request, HttpServletResponse response) {
		String cidName=request.getParameter("cid");
		if(cidName==null){
			return;
		}
		int cid=Integer.parseInt(cidName);
		DBUtils_BO bo=new DBUtils_BO();
		bo.conn=C3p0Utils.getConnection();
		Map<String,Object> map= new HashMap<String,Object>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String sql="Select id, summary,price,pay,picture,size from goods where cid=?";
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, cid);
			DBUtils.executeQuery(bo);
			while (bo.rs.next()) {
				map= new HashMap<String,Object>();
				String summary=bo.rs.getString("summary");
				String price=bo.rs.getString("price");
				String pay=bo.rs.getString("pay");
				int size=bo.rs.getInt("size");
				int id=bo.rs.getInt("id");
				String picture=bo.rs.getString("picture");
				map.put("summary",summary );
				map.put("price",price);
				map.put("pay", pay);
				map.put("size", size);
				map.put("picture", picture);
				map.put("id", id);
				list.add(map);
			}
			String json=JSON.toJSONString(list);
			response.setHeader("content-type", "text/html;charset=utf-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			
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
