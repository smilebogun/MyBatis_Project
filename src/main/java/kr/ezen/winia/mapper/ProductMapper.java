package kr.ezen.winia.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ezen.winia.domain.BrandDTO;
import kr.ezen.winia.domain.ProductDTO;
import kr.ezen.winia.util.ProdSpec;

// @Mapper 
// DAO대신 Mapper 인터페이스로 사용하기 @Mapper 생략가능
public interface ProductMapper {
	
	// 상품 등록
	void insertProduct(Map map);

	// 상품 리스트 (조회하는게 dto랑 resultType="ProductDTO" 이 쓰임)
	ArrayList<ProductDTO> productList();

	// 상품 삭제
	void deleteProduct(int pnum);
	
	// 상품 조회 (조회하는게 dto랑 resultType="ProductDTO" 이 쓰임)
	ProductDTO getProduct(int pnum);
	
	// 상품 수정OK
	void updateProduct(Map map);
	
	// 카테고리 - 상품별 리스트
	ArrayList<ProductDTO> getProdByCategory(String code);
	
	// 브랜드 - 상품별 리스트
	ArrayList<ProductDTO> getProdByBrand(String brand_code);
	
	// 스펙 - 상품별 리스트
	ArrayList<ProductDTO> getProdBySpec(String pSpec);

	
}
