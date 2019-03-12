package xin.yohuyotu.HelloWorld.utils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class State {
	public static void state(HttpServletResponse response,Map map){
		String json=JSON.toJSONString(map);
		response.setHeader("content-type", "text/html;charset=utf-8");
		try {
			response.getWriter().write(json);
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
