package kr.ezen.winia.domain;

import java.sql.Timestamp;

public class MemberDTO {
	private int no;
	private String id;
	private String pw;
	private String name;
	private int age;
	private String tel;
	private String email;
	private String zipcode;
	private String road_addr;
	private String detail_addr;
	private String plus_addr;
	private Timestamp rdate;
	
	// 기본 생성자
	public MemberDTO() {}
	
	// 인자 생성자
	public MemberDTO(int no, String id, String pw, String name, int age, String tel, String email, String zipcode,
			String road_addr, String detail_addr, String plus_addr, Timestamp rdate) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.tel = tel;
		this.email = email;
		this.zipcode = zipcode;
		this.road_addr = road_addr;
		this.detail_addr = detail_addr;
		this.plus_addr = plus_addr;
		this.rdate = rdate;
	}
	
	// 게터세터
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getRoad_addr() {
		return road_addr;
	}

	public void setRoad_addr(String road_addr) {
		this.road_addr = road_addr;
	}

	public String getDetail_addr() {
		return detail_addr;
	}

	public void setDetail_addr(String detail_addr) {
		this.detail_addr = detail_addr;
	}

	public String getPlus_addr() {
		return plus_addr;
	}

	public void setPlus_addr(String plus_addr) {
		this.plus_addr = plus_addr;
	}

	public Timestamp getRdate() {
		return rdate;
	}

	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	
}
