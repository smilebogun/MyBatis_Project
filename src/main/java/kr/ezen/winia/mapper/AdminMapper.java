package kr.ezen.winia.mapper;

import java.util.ArrayList;
import java.util.List;

import kr.ezen.winia.domain.AdminDTO;
import kr.ezen.winia.domain.MemberDTO;


// @Mapper 
// DAO대신 Mapper 인터페이스로 사용하기 @Mapper 생략가능
public interface AdminMapper {
	
	// 멤버 로그인 OK
	AdminDTO adminLogin(AdminDTO dto);

////////////////////////////////////////////////////////////////
	// 어드민-멤버 리스트 (조회하는게 dto랑 resultType="CategoryDTO" 이 쓰임)
	ArrayList<MemberDTO> adMemberList();
	
	// 어드민-멤버 삭제
	void adMemberDelete(int no);
	
	// 어드민-멤버 조회 (조회하는게 dto랑 resultType="CategoryDTO" 이 쓰임)
	MemberDTO adminMemberInfo(int no);
	
	// 어드민-멤버 수정OK
	void adMemberUpdate(MemberDTO dto);
	
	// 마이프로필 폼 -> 비밀번호 변경 OK
	int adupdatePw(MemberDTO dto);

}
