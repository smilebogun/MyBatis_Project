package kr.ezen.winia.domain;

public class ProductDTO {

	private int pnum;
	private String pname;
	private String pcategory_fk;
	private String pcompany;
	private String pimage;
	private int pqty;
	private int price;
	private String pspec;
	private String pcontent;
	private int point;
	private String pinput_date;
	
	private int totPrice;
	private int totPoint;

	public ProductDTO() {
		
	}
	
	
	public ProductDTO(int pnum, String pname, String pcategory_fk, String pcompany, String pimage, int pqty, int price,
			String pspec, String pcontent, int point, String pinput_date, int totPrice, int totPoint) {
		super();
		this.pnum = pnum;
		this.pname = pname;
		this.pcategory_fk = pcategory_fk;
		this.pcompany = pcompany;
		this.pimage = pimage;
		this.pqty = pqty;
		this.price = price;
		this.pspec = pspec;
		this.pcontent = pcontent;
		this.point = point;
		this.pinput_date = pinput_date;
		
		this.totPrice = totPrice;
		this.totPoint = totPoint;
	}

	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcategory_fk() {
		return pcategory_fk;
	}
	public void setPcategory_fk(String pcategory_fk) {
		this.pcategory_fk = pcategory_fk;
	}
	public String getPcompany() {
		return pcompany;
	}
	public void setPcompany(String pcompany) {
		this.pcompany = pcompany;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public int getPqty() {
		return pqty;
	}
	public void setPqty(int pqty) {
		this.pqty = pqty;
		totPrice = this.pqty*price;		// 장바구니 합계값 구하기 추가
		totPoint = this.pqty*point;		// 장바구니 합계값 구하기 추가
	}
	public int getTotPrice() {		// tot 게터세터 추가
		return totPrice;
	}

	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}

	public int getTotPoint() {		// tot 게터세터 추가
		return totPoint;
	}

	public void setTotPoint(int totPoint) {
		this.totPoint = totPoint;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPspec() {
		return pspec;
	}
	public void setPspec(String pspec) {
		this.pspec = pspec;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getPinput_date() {
		return pinput_date;
	}
	public void setPinput_date(String pinput_date) {
		this.pinput_date = pinput_date;
	}
	
}
