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

import xin.yohuyotu.HelloWorld.bean.Cid;
import xin.yohuyotu.HelloWorld.bean.Goods;
import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;

/**
 * Servlet implementation class BackCid
 */
@WebServlet("/BackCid")
public class BackCid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackCid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
		switch(method){
		case "all":
			//所有商品列表
			all(request,response);
			break;
		
		}
	}

	private void all(HttpServletRequest request, HttpServletResponse response) {
		String url="/admin/jsp/cid/cid.jsp";
		allCid(request,response,url);
		
	}
	public void allCid(HttpServletRequest request,HttpServletResponse response,String url){
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
		   List<Cid> serviceList = getCid(startNum,pageSize);
		   
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
	public int getCount(){
		String sql="select count(*) as t from classify ";
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
	public List<Cid> getCid(int start,int pageSize){
		
		DBUtils_BO bo=new DBUtils_BO();
		String sql="select * from classify limit ?,?";
		bo.conn=C3p0Utils.getConnection();
		List<Cid> list=new LinkedList<Cid>();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			
			bo.pst.setInt(1, start);
			bo.pst.setInt(2, pageSize);
			DBUtils.executeQuery(bo);
			while(bo.rs.next()){
				Cid cid=new Cid();
				cid.setCid(bo.rs.getInt("cid"));
				cid.setName(bo.rs.getString("name"));
				list.add(cid);
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
