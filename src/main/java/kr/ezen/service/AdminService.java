package kr.ezen.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.ezen.winia.domain.AdminDTO;
import kr.ezen.winia.domain.MemberDTO;

@Service
public interface AdminService {

	// 멤버 로그인 OK
	boolean adminLogin(AdminDTO dto, HttpServletRequest request);

////////////////////////////////////////////////////////////////
	// 어드민-멤버리스트 이동 및 띄우기
	ArrayList<MemberDTO> adMemberList();

	// 어드민-멤버 삭제하기
	void adMemberDelete(int no);
	
	// 어드민-멤버 정보보기
	MemberDTO adminMemberInfo(int no);
	
	// 어드민-멤버 수정하기 OK
	void adMemberUpdate(MemberDTO dto);
	
	// 마이프로필 폼 -> 비밀번호 변경 OK
	int admodifyPw(MemberDTO dto);
	
}
