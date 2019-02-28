from bs4 import BeautifulSoup
import requests
import xlrd
import xlwt
import time

class WanManBanJi: 
    def __init__(self):
        self.my_ip = "http://www.cisau.com.cn"
        self.excel_title = "标题,具体内容网址"
        self.sheet = ["wanmanbanji"]
        self.sheet_title = [["标题","网页"]]
        self.top_num = 243
        self.save_file = r"D:\Project\PythonProject\python_put_Excel\novel.xls"

    def birth_excel (self):  
       
        

        content = requests.get(self.my_ip+"/html/list_1749.html")
        content.encoding = 'GBK'
        soup = BeautifulSoup(content.text)
        ul_list = soup.find_all(class_ = "noborder")
        sheet,writebook = self.open_work(self.sheet[0])
        i_x,i_y = 0,0
        for title in self.sheet_title[0]:
            sheet.write(i_x,i_y,title)
            i_y+=1
        self.write_excel(ul_list,sheet,1,0,soup.find('a',class_="next")['href'])
        
        # 为以后读取数据做准备
        # work_book = xlrd.open_workbook(r"D:\Project\PythonProject\python_put_Excel\novel.xlsx")
        # print(work_book.sheet_names())
        
        writebook.save(self.save_file)
        

    def write_excel (self,ul_list,sheet,i_x,i_y,content):
        time.sleep(0.1)
        
        i_y = i_y
        i_x = i_x
        for ul_list_list in ul_list :
           
            
            # print(ul_list_list) #查询加载内容
            for ul_list_list_li in ul_list_list :
                content_html = ul_list_list_li.find('a')

                if(content_html != -1):
                    # print(content_html['href']) # 最终获取的内容数据
                    # shee1 = work_book.sheet_by_index()[0] #获得某一个表单
                    
                    sheet.write(i_x,i_y,content_html['title'])
                    sheet.write(i_x,i_y+1,self.my_ip+content_html['href'])

                    i_x+=1
        print(content,"####################")
        print("#####写入一页")
        if content.find('list_1749') != -1:
            
            print(self.my_ip+content)
            content = requests.get(self.my_ip+"/html/"+content)
            content.encoding = 'GBK'
            soup = BeautifulSoup(content.text)
            ul_list = soup.find_all(class_ = "noborder")
            if soup.find('a',class_="next") == -1:
                return
            try:
                self.write_excel(ul_list,sheet,i_x,i_y,soup.find('a',class_="next")['href'])
            except Exception :
                print("报错了")



    def open_work (self,name):
        writebook = xlwt.Workbook()            
        sheet = writebook.add_sheet(name,cell_overwrite_ok=True)
        font = self.excel_font()
        sheet.font = font
        return sheet,writebook

    def excel_font (self):
        font = xlwt.Font() # 为样式创建字体
        # font.name = "name" # 'Times New Roman'
        # font.bold = bold
        # font.color_index = 4
        # font.height = height
        
        # borders= xlwt.Borders()
        # borders.left= 6
        # borders.right= 6
        # borders.top= 6
        # borders.bottom= 6
        return font

class ReadExcel :
    def __init__(self):
        self.save_file = r"D:\Project\PythonProject\python_put_Excel\novel.xls"
        self.excel_save_file = r"D:\Project\PythonProject\python_put_Excel\content.xls"
        self.paper_name = "paper"
        self.sheet_title = [["内容"]]

    def open_excel (self):

        # 为以后读取数据做准备
        work_book = xlrd.open_workbook(self.save_file)
        sheet = work_book.sheet_by_name('wanmanbanji') 
        nrows = sheet.nrows
        ncols = sheet.ncols
        paper = ""
        i_x =0;
        i_y = 0;
        # 写入excel
        sheet2,writebook=self.open_work(self.paper_name)
        for title in self.sheet_title[0]:
            sheet2.write(i_x,i_y,title)
        
        i_x =1;
        
        for i in range(1,4375):
            paper = ""
            time.sleep(0.3)
            try:
                print("完成一章"+str(i) )
                content = sheet.cell(i,1).value
                content = requests.get(content)
                content.encoding = 'GBK'
                soup = BeautifulSoup(content.text)
                content = soup.find("div",class_="articlecontent")
                my_time2 = content.find("div",class_="infoz")
                span = content.find_all("span",style = "font-size: 12pt; font-family: 宋体; line-height: 200%; mso-spacerun: 'yes'; mso-font-kerning: 1.0000pt")
                for i in span:
                    paper+='\t'
                    for string in i.strings:
                        
                        paper = paper+string
                    paper+="\n"
                sheet2.write(i_x,i_y,paper)
                i_x+=1
            except Exception:
                print("报错了")
                    
        
            writebook.save(self.excel_save_file)

    def open_work (self,name):
        writebook = xlwt.Workbook()            
        sheet = writebook.add_sheet(name,cell_overwrite_ok=True)
        font = self.excel_font()
        sheet.font = font
        return sheet,writebook

    def excel_font (self):
        font = xlwt.Font() # 为样式创建字体
        # font.name = "name" # 'Times New Roman'
        # font.bold = bold
        # font.color_index = 4
        # font.height = height
        
        # borders= xlwt.Borders()
        # borders.left= 6
        # borders.right= 6
        # borders.top= 6
        # borders.bottom= 6
        return font




if __name__ == '__main__':
    read = ReadExcel()
    read.open_excel()
    # wanman = WanManBanJi()
    # wanman.birth_excel()
    # print(__name__)
