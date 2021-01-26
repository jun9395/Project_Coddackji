package com.ssafy.codackji.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.codackji.model.MemberDto;
import com.ssafy.codackji.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addUser(MemberDto memberDto) {
		System.out.println("[service-회원가입]");
		sqlSession.getMapper(MemberMapper.class).addUser(memberDto);
	}

	@Override
	public int emailCheck(String email) {
		return sqlSession.getMapper(MemberMapper.class).emailCheck(email);
	}
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getEmail() == null || memberDto.getPassword() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String email) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(email);
	}
}
