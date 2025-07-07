package com.andy.playground.comments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andy.playground.comments.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);
	
// CommentRepository 인터페이스에서 사용하는 제네릭 타입 <Comment, Long> 은 JPA의 JpaRepository 
// 인터페이스가 어떤 엔티티와 그 엔티티의 기본 키(PK) 타입을 다룰지를 명시하는 것입니다.
// Comment → JPA에서 관리할 엔티티 클래스입니다.
// Long → Comment 엔티티의 기본 키(PK)의 타입입니다.
}