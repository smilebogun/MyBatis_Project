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

import kr.ezen.winia.domain.BrandDTO;
import kr.ezen.winia.mapper.BrandMapper;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandMapper mapper;
	
	@Autowired // root-context에서 생성된 Bean class 를 주입받기 // servlet에서 생성된건 받지 못함
	JavaMailSender mailSender;
	
	@Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
	private BCryptPasswordEncoder pwEncoder;
	
	// 브랜드 등록OK
	@Override
	public void insertBrand(BrandDTO dto) {
		mapper.insertBrand(dto);
	}

	// 브랜드 전체 리스트
	@Override // 페이징처리
	public ArrayList<BrandDTO> brandList() {
		return mapper.brandList(); 
	}
	
	// 브랜드 삭제
	@Override
	public void brandDelete(int brand_num) {
		mapper.brandDelete(brand_num);
	}
	
	// 브랜드 정보
	@Override // public "BoardDTO" 뒤에는 리턴값 // 즉, 리턴은 BoardDTO 값
	public BrandDTO brandUpdate(int brand_num) {
		return mapper.brandUpdate(brand_num);
	}

	// 브랜드 업데이트
	@Override
	public void brandUpdateOk(BrandDTO dto) {
		mapper.brandUpdateOk(dto);
	}

}
