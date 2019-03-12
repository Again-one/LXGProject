package xin.yohuyotu.HelloWorld.back;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.yohuyotu.HelloWorld.bean.Goods;
import xin.yohuyotu.HelloWorld.bean.GoodsItem;
import xin.yohuyotu.HelloWorld.bean.GoodsOrder;
import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;

/**
 * Servlet implementation class BackGoods
 */
@WebServlet("/BackGoods")
public class BackGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackGoods() {
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
			//所有商品列表
			all(request,response);
			break;
		case "allOrder":
			//根据订单id查询商品数据
			allOrder(request,response);
			break;
		}
	}

	private void allOrder(HttpServletRequest request, HttpServletResponse response) {
		String url="/admin/jsp/order/show.jsp";
		allGoodsOreder(request, response, url);
		
	}

	private void all(HttpServletRequest request, HttpServletResponse response) {
		String url="/admin/jsp/goods/goods.jsp";
		allGoods(request,response,url);
		
	}
	public void allGoodsOreder(HttpServletRequest request,HttpServletResponse response,String url){
		DBUtils_BO bo=new DBUtils_BO();
		String oid=request.getParameter("oid");
		try {
			
		
			int pageSize=10;
			String pageIndex2 = "1";//默认为第一页
			if(null != request.getParameter("pageIndex2") && !"".equals(request.getParameter("pageIndex2"))){
				pageIndex2 = (String)request.getParameter("pageIndex2");
			}
			//最后需要将当前页返回给前台，用于样式的展示
			request.setAttribute("pageIndex2", pageIndex2);
			
			//一顿计算。。。。,取得startNum
			int startNum2 = (Integer.parseInt(pageIndex2)-1)*pageSize;
			
			   
		   //根据条件查询
		   List<GoodsItem> serviceList = getGoods2(startNum2,pageSize,oid);
		   
		   //查询出总数，用作分页
		   int serviceCount = getCount2(oid);
		   request.setAttribute("count2",serviceCount);//总数				   Integer countPage = serviceCount/10;
		   int countPage = serviceCount/pageSize;
		   if(((double)serviceCount/pageSize-(int)serviceCount/pageSize)>0){//有小数，总页数+1
		    countPage = countPage+1;
		   }
		   request.setAttribute("countPage2",countPage);//总页数
		   
		   request.setAttribute("list2", serviceList);
		   
		   
		   RequestDispatcher rd;
		   
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
	public void allGoods(HttpServletRequest request,HttpServletResponse response,String url){
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
		   List<Goods> serviceList = getGoods(startNum,pageSize);
		   
		   //查询出总数，用作分页
		   int serviceCount = getCount();
		   request.setAttribute("count",serviceCount);//总数				   Integer countPage = serviceCount/10;
		   int countPage = serviceCount/pageSize;
		   if(((double)serviceCount/pageSize-(int)serviceCount/pageSize)>0){//有小数，总页数+1
		    countPage = countPage+1;
		   }
		   request.setAttribute("countPage",countPage);//总页数
		   
		   request.setAttribute("list", serviceList);
		   
		   
		   RequestDispatcher rd;
		  
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
	public int getCount(){
		String sql="select count(*) as t from goods ";
		DBUtils_BO bo=new DBUtils_BO();
		
		bo.conn=C3p0Utils.getConnection();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			
			
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
	public List<Goods> getGoods(int start,int pageSize){
		
		DBUtils_BO bo=new DBUtils_BO();
		String sql="select * from goods limit ?,?";
		bo.conn=C3p0Utils.getConnection();
		List<Goods> list=new LinkedList<Goods>();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			
			bo.pst.setInt(1, start);
			bo.pst.setInt(2, pageSize);
			DBUtils.executeQuery(bo);
			while(bo.rs.next()){
				Goods goods=new Goods();
				goods.setId(bo.rs.getInt("id"));
				goods.setSummary(bo.rs.getString("summary"));
				goods.setPrice(bo.rs.getString("price"));
				goods.setPay(bo.rs.getString("pay"));
				goods.setSize(bo.rs.getInt("size"));
				goods.setPicture(bo.rs.getString("picture"));
				
				list.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	public int getCount2(String oid){
		String sql="select count(*) as t from goods as a,order2item as b where b.sid=a.id and b.oid=?";
		DBUtils_BO bo=new DBUtils_BO();
		
		bo.conn=C3p0Utils.getConnection();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, oid);
			
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
	public List<GoodsItem> getGoods2(int start,int pageSize,String oid){
		
		DBUtils_BO bo=new DBUtils_BO();
		String sql="select a.id,a.summary,a.price,a.picture,b.sum from goods as a,order2item as b where oid=? and a.id=b.sid limit ?,?";
		bo.conn=C3p0Utils.getConnection();
		List<GoodsItem> list=new LinkedList<GoodsItem>();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, oid);
			bo.pst.setInt(2, start);
			bo.pst.setInt(3, pageSize);
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
