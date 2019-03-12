<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,
xin.yohuyotu.HelloWorld.bean.GoodsOrder,
xin.yohuyotu.HelloWorld.bean.GoodsItem" %>
<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="1">
	<tr>
		<th>商品id</th>
		<th>商品图片</th>
		<th>商品名称</th>
		<th>商品价格</th>
		
		<th>商品数量</th>
		
	</tr>
	
	
	<%List<GoodsItem> list2=(List)request.getAttribute("list2");
	
	for(int i=0;i<list2.size();i++){
		GoodsItem o=list2.get(i);
	%>
		<tr>
			<td><%=o.getSid() %></td>
			<td><img src="<%=request.getContextPath() %>/image/<%=o.getPicture()%>_1.jpg" style="width:20px;height:20px"></td>
			<td><%=o.getSummary()%></td>
			<td><%=o.getPrice() %>元</td>
			<td><%=o.getSum() %></td>
			
			
			</td>
		</tr>
	<%} %>
	</table>