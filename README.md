# 微信小程序-动漫电子商城
	2017年帮大四学长做的毕业设计是一款前后台皆有的小程序，有完整的一套小程序
	是一款前后台皆有的小程序，前端包括商品列表、购物车、地址增加、搜索、我的
	后端包括商品信息、订单、用户信息
	数据是通过python抓取的，有60多M的数据吧

# novel.py 爬虫笔记
## 地址

2.beautifulsoup官网
  https://www.crummy.com/software/BeautifulSoup/bs4/doc.zh/

## python要点复习

### 类的使用
	class A:
		__init__ (self):
			# 初始化
			self.name = "lxg"
	if __name__ == "__main__":
		a = A()

### 异常机制
	try :
		print("可能html解析错误")
	except Exception :
		print("报错了")

### 字符串方法复习

	a = " "
	a.find("a")
	a.match("a")
	

## 模板学习
	xlrd 和xlwt
	workbook = xlrd.open_workbook()
	sheet = workbook.sheet_by_index(0) #根据索引获得表单
	sheet = workbook.sheet_by_name("表单名字") #根据表单名字获得表单
	nrows = sheet.nrows #多少行
	ncols = sheet.ncols #多少列
	sheet.cell(0,0).value 
	writebook = xlwt.Workbook()#打开一个excel
　　sheet = writebook.add_sheet('test')#在打开的excel中添加一个sheet
	sheet.write(i,0,result[0])#写入excel，i行0列
 　	sheet.write(i,1,result[1])
	 writebook.save('answer.xls')#保存
	



