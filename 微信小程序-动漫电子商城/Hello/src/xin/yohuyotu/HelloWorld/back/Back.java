package xin.yohuyotu.HelloWorld.back;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;



import com.alibaba.fastjson.JSON;


import xin.yohuyotu.HelloWorld.bean.GoodsItem;
import xin.yohuyotu.HelloWorld.bean.GoodsOrder;
import xin.yohuyotu.HelloWorld.bean.GoodsOrderItem;
import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;
import xin.yohuyotu.HelloWorld.utils.State;

/**
 * Servlet implementation class Back
 */
@WebServlet("/Back")
public class Back extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Back() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String method=request.getParameter("method");
		switch(method){
		case "fHRequest":
			//进行发货请求
			fHRquest(request,response);
			break;
		case "wFOrder":
			
			allWFOrder(request,response);
			break;
		case "fKOrder":
			allFKOrder(request,response);
			break;
		case "fHOrder":
			allFHOrder(request,response);
			break;
		}
		
		
	}

	
	
	
	public List<GoodsItem> getGoods2(String oid){
		
		DBUtils_BO bo=new DBUtils_BO();
		String sql="select a.id,a.summary,a.price,a.picture,b.sum from goods as a,order2item as b where oid=? and a.id=b.sid";
		bo.conn=C3p0Utils.getConnection();
		List<GoodsItem> list=new LinkedList<GoodsItem>();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, oid);
			
			DBUtils.executeQuery(bo);
			while(bo.rs.next()){
				GoodsItem goods=new GoodsItem();
				goods.setPicture(bo.rs.getString("picture"));
				goods.setPrice(bo.rs.getString("price"));
				goods.setSid(bo.rs.getInt("id"));
				goods.setSum(bo.rs.getInt("sum"));
				goods.setSummary(bo.rs.getString("summary"));
				list.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	private void allFHOrder(HttpServletRequest request, HttpServletResponse response) {
		String url="/admin/jsp/order/fh.jsp";
		int isFu=1;
		int isFa=1;
		allOrder(request,response,url,isFu,isFa);
		
	}

	private void allFKOrder(HttpServletRequest request, HttpServletResponse response) {
		String url="/admin/jsp/order/fk.jsp";
		int isFu=1;
		int isFa=0;
		allOrder(request,response,url,isFu,isFa);
		
	}

	private void allWFOrder(HttpServletRequest request, HttpServletResponse response) {
		String url="/admin/jsp/order/wf.jsp";
		int isFu=0;
		int isFa=0;
		allOrder(request,response,url,isFu,isFa);
		
	}

	private void fHRquest(HttpServletRequest request, HttpServletResponse response) {
		String oid=request.getParameter("oid");
		DBUtils_BO bo=new DBUtils_BO();
		
		try {
			
			bo.conn=C3p0Utils.getConnection();
			String sql="update order2 set isFa=1 where oid=?";
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, oid);
			int c=DBUtils.executeUpdate(bo);
			Map<String,Object> map=new HashMap<String,Object>();
			if(c>0){
				
				map.put("state", 1);
				map.put("message", "发货成功");
				State.state(response, map);
				
			}else{
				map.put("state", 0);
				map.put("message", "发货失败，重新发货");
				State.state(response, map);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.realseSource(bo);
		}
		
	}

	/**
	 * 所有付款订单
	 */
	public void allOrder(HttpServletRequest request,HttpServletResponse response,String url,int isFu,int isFa){
		DBUtils_BO bo=new DBUtils_BO();
		try {
			
		
			int pageSize=10;
			String pageIndex = "1";//默认为第一页
			if(null != request.getParameter("pageIndex") && !"".equals(request.getParameter("pageIndex"))){
				pageIndex = (String)request.getParameter("pageIndex");
			}
			//最后需要将当前页返回给前台，用于样式的展示
			request.setAttribute("pageIndex", pageIndex);
			
			//一顿计算。。。。,取得startNum
			int startNum = (Integer.parseInt(pageIndex)-1)*pageSize;
			
			   
		   //根据条件查询
		   List<GoodsOrder> serviceList = getOrder(startNum,pageSize,isFu,isFa);
		   
		   //查询出总数，用作分页
		   int serviceCount = getCount(isFu,isFa);
		   request.setAttribute("count",serviceCount);//总数				   Integer countPage = serviceCount/10;
		   int countPage = serviceCount/pageSize;
		   if(((double)serviceCount/pageSize-(int)serviceCount/pageSize)>0){//有小数，总页数+1
		    countPage = countPage+1;
		   }
		   request.setAttribute("countPage",countPage);//总页数
		   
		   request.setAttribute("list", serviceList);
		   
		   
		   RequestDispatcher rd;
		   request.setAttribute("pageBean", serviceList);
		  rd=request.getRequestDispatcher(url);
		  rd.forward(request, response);
		} catch (ServletException e) {
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
	 * 获得已经付款了的订单
	 * 通过分页语句获得菜单
	 * @param username
	 * @return
	 */
	public List<GoodsOrder> getOrder(int start,int pageSize,int isFu,int isFa){
		
		DBUtils_BO bo=new DBUtils_BO();
		String sql="select * from order2 where isFu=? and isFa=? limit ?,?";
		bo.conn=C3p0Utils.getConnection();
		List<GoodsOrder> list=new LinkedList<GoodsOrder>();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1,isFu);
			bo.pst.setInt(2,isFa);
			bo.pst.setInt(3, start);
			bo.pst.setInt(4, pageSize);
			DBUtils.executeQuery(bo);
			while(bo.rs.next()){
				GoodsOrder good=new GoodsOrder();
				good.oid=bo.rs.getString("oid");
				good.message=bo.rs.getString("message");
				good.username="用户"+String.valueOf(bo.rs.getInt("uid"));
				good.isFa=bo.rs.getInt("isFa");
				good.isFu=bo.rs.getInt("isFu");
				good.price=bo.rs.getInt("price_sum");
				
				
				list.add(good);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	/**
	 * 返回有多少个数据,返回-1证明没有数据
	 * @param username
	 * @return
	 */
	public int getCount(int isFu,int isFa){
		String sql="select count(*) as t from order2 where isFu=? and isFa=?";
		DBUtils_BO bo=new DBUtils_BO();
		
		bo.conn=C3p0Utils.getConnection();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, isFu);
			bo.pst.setInt(2, isFa);
			
			DBUtils.executeQuery(bo);
			
			
			while(bo.rs.next()){
				return bo.rs.getInt("t");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
