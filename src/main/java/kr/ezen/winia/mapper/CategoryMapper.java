package kr.ezen.winia.mapper;

import java.util.ArrayList;
import java.util.List;

import kr.ezen.winia.domain.CategoryDTO;

// @Mapper 
// DAO대신 Mapper 인터페이스로 사용하기 @Mapper 생략가능
public interface CategoryMapper {
	
	// 카테고리 등록
	void insertCategory(CategoryDTO dto);
	
	// 카테고리 리스트 (조회하는게 dto랑 resultType="CategoryDTO" 이 쓰임)
	ArrayList<CategoryDTO> categoryList();

	// 카테고리 삭제
	void categoryDelete(int cat_num);
	
	// 카테고리 조회 (조회하는게 dto랑 resultType="CategoryDTO" 이 쓰임)
	CategoryDTO catUpdate(int cat_num);
	
	// 카테고리 수정OK
	void catUpdateOk(CategoryDTO dto);
	
}
