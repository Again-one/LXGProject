package xin.yohuyotu.HelloWorld.back;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
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

import xin.yohuyotu.HelloWorld.bean.Goods;
import xin.yohuyotu.HelloWorld.bean.User;
import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;
import xin.yohuyotu.HelloWorld.utils.State;

/**
 * Servlet implementation class BackUser
 */
@WebServlet("/BackUser")
public class BackUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackUser() {
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
		case "deleteLogistics":
			deleteLogistics(request,response);
			break;
		case "deleteCard":
			deleteCard(request,response);
			break;
		case "deleteUser":
			deleteUser(request,response);
			break;
		}
	}

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
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
			Map<String,Object> map=new HashMap<String,Object>();
			if(bo.rs.next()){
				int uid=bo.rs.getInt("userid");
				sql="delete from user where userid=? ";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				int a=DBUtils.executeUpdate(bo);
				if(a>0){
					map.put("state", "1");
					map.put("message", "成功");
					State.state(response, map);
				}else{
					map.put("state", "0");
					map.put("message", "该用户已经被删除");
					State.state(response, map);
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		
		
	}

	private void deleteCard(HttpServletRequest request, HttpServletResponse response) {
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
			Map<String,Object> map=new HashMap<String,Object>();
			if(bo.rs.next()){
				int uid=bo.rs.getInt("userid");
				sql="delete from cardbag where uid=? ";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				int a=DBUtils.executeUpdate(bo);
				if(a>0){
					map.put("state", "1");
					map.put("message", "成功");
					State.state(response, map);
				}else{
					map.put("state", "0");
					map.put("message", "没有领取卡卷");
					State.state(response, map);
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		
	}

	private void deleteLogistics(HttpServletRequest request, HttpServletResponse response) {
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
			Map<String,Object> map=new HashMap<String,Object>();
			if(bo.rs.next()){
				int uid=bo.rs.getInt("userid");
				sql="delete from order2 where uid=? and isFa=1 ";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				int a=DBUtils.executeUpdate(bo);
				if(a>0){
					map.put("state", "1");
					map.put("message", "成功");
					State.state(response, map);
				}else{
					map.put("state", "0");
					map.put("message", "没有物流");
					State.state(response, map);
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		
	}

	private void all(HttpServletRequest request, HttpServletResponse response) {
		String url="/admin/jsp/user/user.jsp";
		allUser(request,response,url);
		
	}
	public void allUser(HttpServletRequest request,HttpServletResponse response,String url){
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
		   List<User> serviceList = getUser(startNum,pageSize);
		   
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
		String sql="select count(*) as t from user ";
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
	public List<User> getUser(int start,int pageSize){
		
		DBUtils_BO bo=new DBUtils_BO();
		String sql="select * from user limit ?,?";
		bo.conn=C3p0Utils.getConnection();
		List<User> list=new LinkedList<User>();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			
			bo.pst.setInt(1, start);
			bo.pst.setInt(2, pageSize);
			DBUtils.executeQuery(bo);
			while(bo.rs.next()){
				User user=new User();
				user.setUid(bo.rs.getInt("userid"));
				user.setUsername(bo.rs.getString("username"));
				list.add(user);
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
