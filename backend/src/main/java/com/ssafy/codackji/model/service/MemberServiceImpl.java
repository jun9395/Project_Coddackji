package com.ssafy.codackji.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.codackji.model.MemberDto;
import com.ssafy.codackji.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addUser(MemberDto memberDto) {
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
	public MemberDto socialLogin(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).socialLogin(memberDto);
	}
	
	@Override
	public MemberDto userInfo(String email) throws Exception {
		MemberDto memberDto = sqlSession.getMapper(MemberMapper.class).userInfo(email);
		return memberDto;
	}
	
	@Override
	@Transactional
	public boolean updateUser(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).updateUser(memberDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteUser(String email) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteUser(email) == 1;
	}
	
	@Override
	@Transactional
	public boolean updatePassword(MemberDto memberDto) throws Exception{
		return sqlSession.getMapper(MemberMapper.class).updatePassword(memberDto) == 1;
	}

	@Override
	public boolean updateProfile(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).updateProfile(memberDto) == 1;
	}

	@Override
	public boolean updateIsProfile(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).updateIsProfile(memberDto) == 1;
	}

}
