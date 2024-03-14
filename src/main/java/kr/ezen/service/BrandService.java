package kr.ezen.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.ezen.winia.domain.BrandDTO;

@Service
public interface BrandService {
	
	// 브랜드 등록OK
	void insertBrand(BrandDTO dto);
	
	// 브랜드 전체 리스트 가져오기
	ArrayList<BrandDTO> brandList();
	
	// 브랜드 삭제하기
	void brandDelete(int brand_num);
	
	// 브랜드 정보보기
	BrandDTO brandUpdate(int brand_num);
	
	// 브랜드 수정하기 OK
	void brandUpdateOk(BrandDTO dto);
	
}
