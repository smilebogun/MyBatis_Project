package kr.ezen.winia.domain;

public class BrandDTO {

	private int brand_num;
	private String brand_code;
	private String brand_name;
	
	public BrandDTO() {
		
	}
	
	public BrandDTO(int brand_num, String brand_code, String brand_name) {
		super();
		this.brand_num = brand_num;
		this.brand_code = brand_code;
		this.brand_name = brand_name;
	}
	
	public int getBrand_num() {
		return brand_num;
	}
	public void setBrand_num(int brand_num) {
		this.brand_num = brand_num;
	}
	public String getBrand_code() {
		return brand_code;
	}
	public void setBrand_code(String brand_code) {
		this.brand_code = brand_code;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

}
