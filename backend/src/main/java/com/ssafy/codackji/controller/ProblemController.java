package com.ssafy.codackji.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.codackji.model.MemberDto;
import com.ssafy.codackji.model.ProblemDto;
import com.ssafy.codackji.model.SolvedProblemDto;
import com.ssafy.codackji.model.service.JwtService;
import com.ssafy.codackji.model.service.MemberService;
import com.ssafy.codackji.model.service.ProblemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("ProblemrController V1")
@RestController
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MemberService memberService;
	
	@ApiOperation(value="전체 문제 목록", notes = "전체 문제리스트를 반환한다", response = List.class)
	@GetMapping("/all")
	public ResponseEntity<List<ProblemDto>> allProblem() throws Exception{
		return new ResponseEntity<List<ProblemDto>>(problemService.allProblem(), HttpStatus.OK);
	}
	
	@ApiOperation(value="단계별 문제 목록", notes = "해당 단계의 문제리스트를 반환한다", response = List.class)
	@GetMapping("/rank/{problem_rank}")
	public ResponseEntity<List<ProblemDto>> listProblem(@PathVariable("problem_rank") @ApiParam(value="문제 단계(난이도)", required = true) int problem_rank) throws Exception{
		System.out.println("[단계별 문제리스트]:"+problem_rank);
		return new ResponseEntity<List<ProblemDto>>(problemService.listProblem(problem_rank), HttpStatus.OK);
	}
	
	@ApiOperation(value="문제 보기", notes = "선택한 문제 보기", response = ProblemDto.class)
	@GetMapping("/{problem_number}")
	public ResponseEntity<ProblemDto> getProblem(@PathVariable("problem_number") @ApiParam(value="얻어올 문제 번호", required=true) int problem_number) throws Exception{
		System.out.println("[문제 보기]:"+problem_number);
		return new ResponseEntity<ProblemDto>(problemService.getProblem(problem_number), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "유저가 푼 문제를 반환한다", notes = "Solved_Problem 테이블에서 user_number와 일치하는 정보를 가져온다.", response = MemberDto.class)
	@PostMapping("/user/solved")
	public ResponseEntity<List<SolvedProblemDto>> userSolvedProblem(@RequestBody @ApiParam(value = "토큰", required = true) MemberDto memDto)
			throws Exception {
		
		String token = memDto.getToken();

		List<SolvedProblemDto> nullList = null;
		
		if (jwtService.isUsable(token)) {
			String email = jwtService.getUserEmail(token);
			
			if (jwtService.isInTime(token)) {
				MemberDto memberDto = new MemberDto();
				memberDto.setEmail(email);
				memberDto.setToken(token);
				jwtService.setToken(memberDto);// db에 토큰 renewal_time 갱신
				
				// user_number 값 찾기
				// 토큰에서 이메일 > 이메일에서 userInfo Dto > 에서 user_number가져오기				
				MemberDto memberdto = memberService.userInfo(email);
				int user_number = memberdto.getUser_number();

				System.out.println("사용자 번호" + user_number + "가 푼 문제목록:");
				System.out.println(problemService.userSolvedProblem(user_number));
				return new ResponseEntity<List<SolvedProblemDto>>(problemService.userSolvedProblem(user_number), HttpStatus.OK);
			}
		}

		return new ResponseEntity<List<SolvedProblemDto>>(nullList, HttpStatus.UNAUTHORIZED);// 토큰기간만료 or 토큰유효x
	}
}
