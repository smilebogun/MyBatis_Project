package kr.ezen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.ezen.winia.domain.ProductDTO;
import kr.ezen.winia.util.ProdSpec;


@Service
public interface ProductService {
	
	// 상품 등록OK
	void insertProduct(Map map);
	
	// 상품 전체 리스트 가져오기
	ArrayList<ProductDTO> productList();
	
	// 상품 삭제하기
	void deleteProduct(int pnum);
	
	// 상품 정보보기
	ProductDTO getProduct(int pnum);
	
	// 상품 수정하기 OK
	void updateProduct(Map map);
	
	// 카테고리 - 상품별 리스트
	ArrayList<ProductDTO> getProdByCategory(String code);
	
	// 브랜드 - 상품별 리스트
	ArrayList<ProductDTO> getProdByBrand(String brand_code);
	
	// 스펙 - 상품별 리스트
	ArrayList<ProductDTO> getProdBySpec(String pSpec);
	
}
