package xin.yohuyotu.HelloWorld.bean;

public class MessageItem {
	String link;
	String mobile;
	String city;
	String address;
	String code;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "MessageItem [link=" + link + ", mobile=" + mobile + ", city=" + city + ", address=" + address
				+ ", code=" + code + "]";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}