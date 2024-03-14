package kr.ezen.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.ezen.winia.domain.CategoryDTO;

@Service
public interface CategoryService {
	
	// 카테고리 등록OK
	void insertCategory(CategoryDTO dto);
	
	// 카테고리 전체 리스트 가져오기
	ArrayList<CategoryDTO> categoryList();
	
	// 카테고리 삭제하기
	void categoryDelete(int cat_num);
	
	// 카테고리 정보보기
	CategoryDTO catUpdate(int cat_num);
	
	// 카테고리 수정하기 OK
	void catUpdateOk(CategoryDTO dto);
	
}
