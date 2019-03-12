<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
	body{
		padding:0;
		margin:0
	}
</style>
<title>目录</title>
</head>
<body style="width=100%;height=71px;background:url(./image/bac.jpg);">
	
	<div style="background:url(./image/title.png);margin:0 auto;width:600px;height: 71px;">
	</div>
	<div style="background:#999;height:35px;">
	<script language="JavaScript">

		tmpDate = new Date();
		date = tmpDate.getDate();
		month= tmpDate.getMonth() + 1 ;
		year= tmpDate.getFullYear();
		document.write(year);
		document.write("年");
		document.write(month);
		document.write("月");
		document.write(date);
		document.write("日 ");
		
		myArray=new Array(6);
		myArray[0]="星期日"
		myArray[1]="星期一"
		myArray[2]="星期二"
		myArray[3]="星期三"
		myArray[4]="星期四"
		myArray[5]="星期五"
		myArray[6]="星期六"
		weekday=tmpDate.getDay();
		if (weekday==0 | weekday==6)
		{
		document.write(myArray[weekday])
		}
		else
		{document.write(myArray[weekday])
		};
	</script>
	</div>
	
</body>
</html>