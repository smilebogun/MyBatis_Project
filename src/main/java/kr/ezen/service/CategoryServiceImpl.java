package kr.ezen.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ezen.winia.domain.CategoryDTO;
import kr.ezen.winia.mapper.CategoryMapper;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryMapper mapper;
	
	@Autowired // root-context에서 생성된 Bean class 를 주입받기 // servlet에서 생성된건 받지 못함
	JavaMailSender mailSender;
	
	@Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
	private BCryptPasswordEncoder pwEncoder;
	
	// 카테고리 등록OK
	@Override
	public void insertCategory(CategoryDTO dto) {
		mapper.insertCategory(dto);
	}

	// 카테고리 전체 리스트
	@Override // 페이징처리
	public ArrayList<CategoryDTO> categoryList() {
		return mapper.categoryList(); 
	}
	
	// 카테고리 삭제
	@Override
	public void categoryDelete(int cat_num) {
		mapper.categoryDelete(cat_num);
	}
	
	// 카테고리 정보
	@Override // public "BoardDTO" 뒤에는 리턴값 // 즉, 리턴은 BoardDTO 값
	public CategoryDTO catUpdate(int cat_num) {
		return mapper.catUpdate(cat_num);
	}

	// 카테고리 업데이트
	@Override
	public void catUpdateOk(CategoryDTO dto) {
		mapper.catUpdateOk(dto);
	}

}
