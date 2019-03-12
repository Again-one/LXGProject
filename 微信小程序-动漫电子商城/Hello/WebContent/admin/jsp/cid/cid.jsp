<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,xin.yohuyotu.HelloWorld.bean.Cid" 
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
 <!-- 分页处的样式 -->
.page{
 list-style: none;
}
.page li{
 float: left;
 padding: 5px 10px;
 cursor: pointer;
}
.page .pageItem{
 border: solid thin #DDDDDD;
 margin: 5px;
}
.page .pageItemActive{
 border: solid thin #0099FF;
 margin: 5px;
 background-color: #0099FF;
 color:white;
}
.page .pageItem:hover{
 border: solid thin #0099FF;
 background-color: #0099FF;
 color:white;
}
.page .pageItemDisable{
 border: solid thin #DDDDDD;
 margin: 5px;
 background-color: #DDDDDD;
}
#getPage{
	
}
td{
padding-left:4px;

}
 
</style>

</head>
<body>
	<%String path=request.getContextPath()+"/backCid?method=all"; %>
	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/jquery-1.8.3.js"></script>
	
	
	<form action="" method="post" name="serviceForm"></form>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="1">
	<tr>
		<th>分类id</th>
		<th>分类名字</th>
		
	</tr>
	
	
	<%List<Cid> list=(List)request.getAttribute("list");
	
	for(int i=0;i<list.size();i++){
		Cid o=list.get(i);
	%>
		<tr>
			<td><%=o.getCid() %></td>
			
			<td><%=o.getName() %></td>
			
			</td>
		</tr>
	<%} %>
	</table>
	<div class="page" id="getPage" style="list-style: none;"></div>
	<!-- 展示详情 -->
	<div id="xq"></div>
			
	<script language="javascript">
	
	
	$(document).ready(function(){
		 //获取分页数
		console.log("已经开始执行了")
		 var talPage = ${requestScope['countPage']};
		 //获取当前页数
		 var pageIndex = ${requestScope['pageIndex']};
		 var ul = document.getElementById("getPage");
		 document.getElementById("getPage").innerHTML="";
		 var li_0 = document.createElement("li");
		 li_0.innerHTML = "总共："+${requestScope['count']}+"条，共："+${requestScope['countPage']}+"页，每页：10条";
		 ul.appendChild(li_0);
		 if(talPage==1 || pageIndex == 1){//第一页首页和上一页不可操作
		  var li_1 = document.createElement("li");
		  li_1.setAttribute("class","pageItemDisable bt4");
		  li_1.setAttribute("onclick","pageClick(this)")
		  li_1.innerHTML = "首页";
		  ul.appendChild(li_1);
		  var li_2 = document.createElement("li");
		  li_2.setAttribute("class","pageItemDisable bt4");
		  li_2.setAttribute("onclick","pageClick(this)")
		  li_2.innerHTML = "上一页"
		  ul.appendChild(li_2);
		 }else{
		  var li_1 = document.createElement("li");
		  li_1.setAttribute("class","pageItem bt4");
		  li_1.setAttribute("onclick","pageClick(this)")
		  li_1.innerHTML = "首页";
		  ul.appendChild(li_1);
		  var li_2 = document.createElement("li");
		  li_2.setAttribute("class","pageItem bt4");
		  li_2.setAttribute("onclick","pageClick(this)")
		  li_2.innerHTML = "上一页"
		  ul.appendChild(li_2);
		 }
		 //之前需要将，上一页创建出来
		 if(talPage<=5){
		  //总页数在0到5之间时，显示实际的页数
		  for(var i=0;i<talPage;i++){
		   if(i+1 == pageIndex){//循环数和当前页相等时,为当前页样式
		    var li = document.createElement("li");
		    li.setAttribute("class","pageItemActive");
		    li.setAttribute("onclick","pageClick(this)")
		    li.innerHTML = i+1;
		    ul.appendChild(li);
		   }else{
		    var li = document.createElement("li");
		    li.setAttribute("class","pageItem");
		    li.setAttribute("onclick","pageClick(this)");
		    li.innerHTML = i+1;
		    ul.appendChild(li);
		   }
		 
		  }
		 }else if(talPage>5){
		  //总页数大于5时，只显示五页，多出的隐藏
		  //判断当前页的位置
		  if(pageIndex<=3){//当前页小于等于3时，显示1-5
		   for(var i=0;i<5;i++){
		    if(i+1 == pageIndex){//循环数和当前页相等时,为当前页样式
		     var li = document.createElement("li");
		     li.setAttribute("class","pageItemActive");
		     li.setAttribute("onclick","pageClick(this)")
		     li.innerHTML = i+1;
		     ul.appendChild(li);
		    }else{
		     var li = document.createElement("li");
		     li.setAttribute("class","pageItem");
		     li.setAttribute("onclick","pageClick(this)")
		     li.innerHTML = i+1;
		     ul.appendChild(li);
		    }
		   }
		  }else if(pageIndex>talPage-5){//当前页为最后五页时
		   for(var i=talPage-5;i<talPage;i++){
		    if(i+1 == pageIndex){//循环数和当前页相等时,为当前页样式
		     var li = document.createElement("li");
		     li.setAttribute("class","pageItemActive");
		     li.setAttribute("onclick","pageClick(this)")
		     li.innerHTML = i+1;
		     ul.appendChild(li);
		    }else{
		     var li = document.createElement("li");
		     li.setAttribute("class","pageItem");
		     li.setAttribute("onclick","pageClick(this)")
		     li.innerHTML = i+1;
		     ul.appendChild(li);
		    }
		   }
		  }else{//当前页为中间时
		   for(var i=pageIndex-3;i<pageIndex+2;i++){
		    if(i+1 == pageIndex){//循环数和当前页相等时,为当前页样式
		     var li = document.createElement("li");
		     li.setAttribute("class","pageItemActive");
		     li.setAttribute("onclick","pageClick(this)")
		     li.innerHTML = i+1;
		     ul.appendChild(li);
		    }else{
		     var li = document.createElement("li");
		     li.setAttribute("class","pageItem");
		     li.setAttribute("onclick","pageClick(this)")
		     li.innerHTML = i+1;
		     ul.appendChild(li);
		    }
		   } 
		  }
		 }
		 if(pageIndex == talPage){//当前页为最大页时，下一个和尾页不可操作
		  var li_3 = document.createElement("li");
		  li_3.setAttribute("class","pageItemDisable bt4");
		  li_3.setAttribute("onclick","pageClick(this)")
		  li_3.innerHTML = "下一页"
		  ul.appendChild(li_3);
		  var li_4 = document.createElement("li");
		  li_4.setAttribute("class","pageItemDisable bt4");
		  li_4.setAttribute("onclick","pageClick(this)")
		  li_4.innerHTML = "尾页"
		  ul.appendChild(li_4);
		 }else{
		  var li_3 = document.createElement("li");
		  li_3.setAttribute("class","pageItem bt4");
		  li_3.setAttribute("onclick","pageClick(this)")
		  li_3.innerHTML = "下一页"
		  ul.appendChild(li_3);
		  var li_4 = document.createElement("li");
		  li_4.setAttribute("class","pageItem bt4");
		  li_4.setAttribute("onclick","pageClick(this)")
		  li_4.innerHTML = "尾页"
		  ul.appendChild(li_4);
		 }
		 if(0 == talPage){//一页都没有时，将首页，上一页，下一个，尾页都置为不可操作
		   $(".bt4").removeClass("pageItem");
		   $(".bt4").addClass("pageItemDisable");
		 }
		 $(".fahuo").click(function(){
			 var self=$(this);
			 var val=$(this).attr("id");
			 var text=$(this).text();
			 var url="<%=path%>";
			 console.log('path:',url)
			 if("发货"==text){
				 var oid=val;
				 console.log(2)
				 $.ajax({
					 url:url,
					 data:{
						 oid:oid
					 },
					 success:function(res){
						 console.log(res.message);
						 var message=res.message;
						 self.parent().text(message)
					 },
					 type:'get',
					 dataType:'json'
					 
				 })
				
				 
				 
			 }
			 
		 })
		 
		});
		//分页的按钮的点击事件
		function pageClick(obj){
		 var talPage = ${countPage};//总页数
		 var pageIndex = ${pageIndex};//当前页数
		 var text = obj.innerText;//点击标签的值
		 var url = "<%=path%>";
		 console.log(url)
		 //如果为不可操作的直接返回false
		  if($(obj).attr("class").indexOf("pageItemDisable")>=0){
		  return false;
		 } 
		 with(document.forms["serviceForm"]){
		  if("首页" == text){
		   action = url;
		 
		  }else if("上一页" == text){
		   //计算出上一页到底是第几页
		   //第一种方法，获取当前li中class为pageItemActive的标签，取其值
		   //第二种方法，直接el ${pageIndex}获取当前页数，然后-1
		   //var a = $(obj).parent().children("pageItemActive").html();
		   //如果当前页是1，不-,地址和首页相同
		   if(pageIndex <= 1){
		    action = url;   
		   }else{
		    action = url+"&pageIndex="+(pageIndex-1);
		   }
		  }else if("下一页" == text){
		   //如果当前页为尾页，则下一页为尾页，url跟当前url一样
		   if(pageIndex == talPage){
		    action = url;
		   }else{
		    action = url+"&pageIndex="+(pageIndex+1);
		   }
		  }else if("尾页" == text){
		   //如果当前页为尾页，则url不变
		   if(pageIndex == talPage){
		    action = url;
		   }else{
		    action = url+"&pageIndex="+talPage;
		   }
		  }else{
		   //点击页数时
		   action = url+"&pageIndex="+text;
		 	console.log('在这地方执行'+action);
		 	
		  }
		  submit();
		 }
		 
		}
		
	</script>
</body>
</html>