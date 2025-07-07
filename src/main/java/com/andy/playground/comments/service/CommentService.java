package com.andy.playground.comments.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.andy.playground.comments.domain.Comment;
import com.andy.playground.comments.dto.CommentDto;
import com.andy.playground.comments.repository.CommentRepository;

import jakarta.persistence.PersistenceException;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	// commentRepository는 데이터베이스와 직접 통신하는 DAO 역할이에요
	// final은 생성자에서만 초기화 가능하고, 이후에는 값이 바뀌지 않도록 보장합니다.
	// 이 필드는 아래 생성자를 통해 주입됩니다.

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	// 생성자 주입 방식입니다.
	// 스프링이 CommentRepository 구현체를 자동으로 주입해줍니다.
	// (예: @Autowired 없어도 생성자 1개면 스프링이 자동 주입합니다)

	public List<CommentDto> getCommentsByPostId(Long postId) {
		
		List<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
		
		List<CommentDto> commentDto = new ArrayList<>();

		for (Comment comment : comments) {
			
			CommentDto dto = CommentDto.builder()
					.userName(comment.getuserName())
					.text(comment.getText())
					.build();
			commentDto.add(dto);
		}

		return commentDto;
	}

	public boolean addComment(long loginId, long postId, String text) {

		Comment comment = Comment.builder().loginId(loginId).postId(postId).text(text).build();
		// Builder 패턴을 이용해서 Comment 객체를 만듭니다.
		// @Builder를 쓰면 new Comment(...) 방식보다 더 가독성 좋고, 필드 누락 실수 줄일 수 있어요.
		// 이 부분이 실제로 "댓글을 하나 만든 것"입니다. 아직 DB에 저장은 안 됐어요.

		try {
			commentRepository.save(comment);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}
	// save(comment)는 JPA의 save() 메서드로, DB에 INSERT가 됩니다.
	// 여기서 문제가 생기면 (예: DB 연결 오류, 제약 조건 위반 등) 예외(Exception)가 발생합니다.
	// 그 예외를 잡아서 false를 리턴하는 거예요.
	// 즉, try-catch는 안전하게 실패를 처리하기 위한 목적입니다.

}

//✅ 1. try-catch는 왜 쓰는가?
//try-catch는 예외(Exception)가 발생할 수 있는 코드를 안전하게 실행하기 위해 사용합니다.
//💥 예외란?
//예외(Exception)는 프로그램 실행 중에 생기는 비정상 상황이에요.
//예:
//DB 연결 오류
//저장할 때 제약 조건 위반 (예: null 불가인 필드가 비어있을 때)
//서버 연결 끊김 등
//이런 일이 생기면 프로그램이 강제로 멈추거나 죽을 수 있어요.
//그래서 그런 상황을 대비해서 try-catch로 감싸면 죽지 않고 대처할 수 있게 됩니다.
//
//✅ 2. catch (Exception e)에서 e는 뭐냐?
//
//catch (Exception e) {
//Exception은 모든 예외의 최상위 타입이에요. (모든 에러를 잡을 수 있음)
//e는 그 예외 객체(에러 정보)를 저장하는 변수예요.
//✔️ 즉, e에는 무슨 오류인지 정보가 들어있어요.
//
//예:
//
//catch (Exception e) {
//    System.out.println(e.getMessage()); // 예외 메시지 출력
//}