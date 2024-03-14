package kr.ezen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ezen.winia.domain.BrandDTO;
import kr.ezen.winia.domain.ProductDTO;
import kr.ezen.winia.mapper.ProductMapper;
import kr.ezen.winia.util.ProdSpec;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper mapper;
	
	@Autowired // root-context에서 생성된 Bean class 를 주입받기 // servlet에서 생성된건 받지 못함
	JavaMailSender mailSender;
	
	@Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
	private BCryptPasswordEncoder pwEncoder;
	
	// 상품 등록OK
	@Override
	public void insertProduct(Map map) {
		mapper.insertProduct(map);
	}

	// 상품 전체 리스트
	@Override // 페이징처리
	public ArrayList<ProductDTO> productList() {
		return mapper.productList(); 
	}
	
	// 상품 삭제
	@Override
	public void deleteProduct(int pnum) {
		mapper.deleteProduct(pnum);
	}
	
	// 상품 정보
	@Override // public "BoardDTO" 뒤에는 리턴값 // 즉, 리턴은 BoardDTO 값
	public ProductDTO getProduct(int pnum) {
		return mapper.getProduct(pnum);
	}

	// 상품 업데이트
	@Override
	public void updateProduct(Map map) {
		mapper.updateProduct(map);
	}
	
	// 카테고리 - 상품별 리스트
	@Override
	public ArrayList<ProductDTO> getProdByCategory(String code) {
		return mapper.getProdByCategory(code); 
	}
	
	// 브랜드 - 상품별 리스트
	@Override
	public ArrayList<ProductDTO> getProdByBrand(String brand_code) {
		return mapper.getProdByBrand(brand_code); 
	}
	
	
	// 스펙 - 상품별 리스트
	@Override
	public ArrayList<ProductDTO> getProdBySpec(String pSpec) {
		return mapper.getProdBySpec(pSpec); 
	}

}
