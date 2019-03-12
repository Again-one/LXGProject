package xin.yohuyotu.HelloWorld.servlet;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.xml.internal.messaging.saaj.soap.MessageImpl;

import xin.yohuyotu.HelloWorld.bean.GoodsOrderItem;
import xin.yohuyotu.HelloWorld.bean.MessageItem;
import xin.yohuyotu.HelloWorld.utils.C3p0Utils;
import xin.yohuyotu.HelloWorld.utils.DBUtils;
import xin.yohuyotu.HelloWorld.utils.DBUtils_BO;
import xin.yohuyotu.HelloWorld.utils.State;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		
		switch(method){
		case "addOrder":
			addOrder(request,response);
			
			break;
		case "order":
			order(request,response);
			break;
		case "deleteOrder":
			deleteOrder(request,response);
			break;
		case "deleteOneOrder":
			deleteOneOrder(request,response);
			
			break;
		case "getOrderInShopping":
			getOrderInShopping(request,response);
			break;
		
		}
		
	}
	private void deleteOneOrder(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		String oid=request.getParameter("oid");
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
				sql="delete from order2 where oid=?";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setString(1, oid);
				int a=DBUtils.executeUpdate(bo);
				if(a>0){
					map.put("state", "1");
					map.put("message", "成功");
					State.state(response, map);
				}else{
					map.put("state", "0");
					map.put("message", "删除失败");
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
private void getOrderInShopping(HttpServletRequest request, HttpServletResponse response) {
		
		String username=request.getParameter("username");
		
		String oid=request.getParameter("oid");
		
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
				sql="select a.id,a.summary,a.size,a.price,a.pay,a.picture,a.recommend,b.sum from goods a,order2item b where a.id=b.sid And b.oid=? ";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setString(1, oid);
				DBUtils.executeQuery(bo);
				while(bo.rs.next()){
					map= new HashMap<String,Object>();
					String summary=bo.rs.getString("summary");
					String price=bo.rs.getString("price");
					String pay=bo.rs.getString("pay");
					String picture=bo.rs.getString("picture");
					int size=bo.rs.getInt("size");
					int sum=bo.rs.getInt("sum");
					map.put("summary",summary );
					map.put("price",price);
					map.put("pay", pay);
					map.put("size", size);
					map.put("picture", picture);
					map.put("sum", sum);
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
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
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
				sql="delete from order2 ";
				bo.pst=bo.conn.prepareStatement(sql);
				
				int a=DBUtils.executeUpdate(bo);
				if(a>0){
					map.put("state", "1");
					map.put("message", "成功");
					State.state(response, map);
				}else{
					map.put("state", "0");
					map.put("message", "删除失败");
					State.state(response, map);
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void order(HttpServletRequest request, HttpServletResponse response) {
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
			
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			if(bo.rs.next()){
				int uid=bo.rs.getInt("userid");
				sql="select * from order2 where uid=? and isFu=0";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				DBUtils.executeQuery(bo);
				while(bo.rs.next()){
					Map<String,Object> map=new HashMap<String,Object>();
					String oid=bo.rs.getString("oid");
					String message=bo.rs.getString("message");
					int sum=bo.rs.getInt("price_sum");
					map.put("message", message);
					map.put("oid", oid);
					map.put("price_sum",sum);
					
					list.add(map);
				}
			}
			response.setHeader("content-type", "utf-8");
			String json=JSON.toJSONString(list);
			
			response.getWriter().write(json);
			response.getWriter().close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response) {
		String message=request.getParameter("message");
		String username=request.getParameter("username");
		String c=request.getParameter("goods");
		
		String d=request.getParameter("price");
		
		List <GoodsOrderItem> list=new ArrayList<GoodsOrderItem>();
		String oid=UUID.randomUUID().toString();
		
		if(message==null||username==null||c==null){
			return;
		}
		DBUtils_BO bo=new DBUtils_BO();
		try {
			username = URLDecoder.decode(username, "UTF-8");
			c=URLDecoder.decode(c, "utf-8");
			message=URLDecoder.decode(message, "UTF-8");
			int price=Integer.parseInt(d);
			list=JSON.parseObject(c,new TypeReference<ArrayList<GoodsOrderItem>>(){});
			
			
			Message.createUser(username);
			int uid=0;
			String sql="select userid from user where username=?";
			
			bo.conn=C3p0Utils.getConnection();
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setString(1, username);
			DBUtils.executeQuery(bo);
			if(bo.rs.next()){
				uid=bo.rs.getInt("userid");
				sql="insert into order2 (uid,message,price_sum,oid) values(?,?,?,?)";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setInt(1, uid);
				bo.pst.setString(2, message);
				bo.pst.setInt(3, price);
				bo.pst.setString(4, oid);
				DBUtils.executeUpdate(bo);
				
				addOrderItem(oid, list,uid);
				
				Map<String,String> map=new HashMap<String,String>();
				map.put("state", "1");
				map.put("message", "订单生成成功");
				map.put("oid",oid);
				State.state(response, map);
			}
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			DBUtils.realseSource(bo);
		}
		
		
		
		
		
	}
	private void addOrderItem(String oid,List<GoodsOrderItem> list, int uid) {
		// TODO Auto-generated method stub
		DBUtils_BO bo=new DBUtils_BO();
		
		
		try {
			for(int i=0;i<list.size();i++){
				bo.conn=C3p0Utils.getConnection();
				String sql="insert into order2item (oid,sid,sum) values(?,?,?)";
				bo.pst=bo.conn.prepareStatement(sql);
				bo.pst.setString(1, oid);
				bo.pst.setInt(2, list.get(i).getSid());
				
				bo.pst.setInt(3, list.get(i).getSum());
				DBUtils.executeUpdate(bo);
				deleteShopping(list.get(i).getSid(),uid);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.realseSource(bo);
		}
		
		
	}

	private void deleteShopping(int sid, int uid) {
		// TODO Auto-generated method stub
		DBUtils_BO bo=new DBUtils_BO();
		String sql="delete from shopping where uid=? and sid=?";
		bo.conn=C3p0Utils.getConnection();
		try {
			bo.pst=bo.conn.prepareStatement(sql);
			bo.pst.setInt(1, uid);
			bo.pst.setInt(2, sid);
			DBUtils.executeUpdate(bo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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