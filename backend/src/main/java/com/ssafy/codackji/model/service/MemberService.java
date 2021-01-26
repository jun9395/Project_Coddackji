package com.ssafy.codackji.model.service;

import com.ssafy.codackji.model.MemberDto;

public interface MemberService {

	void addUser(MemberDto memberDto);
	int emailCheck(String email);
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String email) throws Exception;
}
