package kr.ezen.winia.util;

public enum ProdSpec {

	None("일반"), HIT("인기"), NEW("신규"), RECOMMEND("추천");
	
	private final String value;
	
	private ProdSpec(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
