package kr.ezen.winia.mapper;

import java.util.ArrayList;
import java.util.List;

import kr.ezen.winia.domain.BrandDTO;

// @Mapper 
// DAO대신 Mapper 인터페이스로 사용하기 @Mapper 생략가능
public interface BrandMapper {
	
	// 브랜드 등록
	void insertBrand(BrandDTO dto);
	
	// 브랜드 리스트 (조회하는게 dto랑 resultType="CategoryDTO" 이 쓰임)
	ArrayList<BrandDTO> brandList();

	// 브랜드 삭제
	void brandDelete(int brand_num);
	
	// 브랜드 조회 (조회하는게 dto랑 resultType="CategoryDTO" 이 쓰임)
	BrandDTO brandUpdate(int brand_num);
	
	// 브랜드 수정OK
	void brandUpdateOk(BrandDTO dto);
	
}
