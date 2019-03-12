package xin.yohuyotu.HelloWorld.bean;

public class GoodsOrderItem {
	
	int sum;
	int sid;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	@Override
	public String toString() {
		return "GoodsOrderItem [sum=" + sum + ", sid=" + sid +  "]";
	}
	
	
	
	
}
