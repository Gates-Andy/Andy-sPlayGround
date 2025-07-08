package com.andy.playground.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.andy.playground.comments.dto.CommentDto;
import com.andy.playground.comments.service.CommentService;
import com.andy.playground.common.FileManager;
import com.andy.playground.likes.service.LikesService;
import com.andy.playground.post.domain.Post;
import com.andy.playground.post.dto.PostDto;
import com.andy.playground.post.repository.PostRepository;
import com.andy.playground.user.domain.User;
import com.andy.playground.user.service.UserService;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 파이널만
@Service
public class PostService {
	private final PostRepository postRepository; // 필수적인 멤버변수 초기화가 필수 이때 lombok의 어노테이션 requiredArgsConstructor 객체 주입에
													// 필요한 생성자만 전달받고싶을떄
	private final UserService userService;
	private final LikesService likesService;
	private final CommentService commentService;

	/*
	 * @RequiredArgsConstructor //파이널만 생성자를 만들어줌 그래서 쓸 필요 없음 lombok public
	 * PostService( PostRepository postRepository , UserService userService ,
	 * LikesService likesService , CommentService commentService) {
	 * this.postRepository = postRepository; this.userService = userService;
	 * this.likesService = likesService ; this.commentService = commentService; }
	 */

	// 세션에 로그인되어있는사람은 컨트롤러에서 밖에 못가져옴 즉 파라미터를 서비스에서 받아야함
	public List<PostDto> getPostList(long userId) {

		List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		List<PostDto> postDtoList = new ArrayList<>();

		for (Post post : postList) {

			User user = userService.getUserById(post.getLoginid());

			int likeCount = likesService.likeCountByPostId(post.getId());

			boolean isLike = likesService.isLikePostIdAndUserId(post.getId(), userId);

			List<CommentDto> commentList = commentService.getCommentListByPostId(post.getId());

			PostDto postDto = PostDto.builder()

					.id(post.getId()).userId(post.getLoginid()).loginId(user.getLoginId()).title(post.getTitle())
					.contents(post.getContents()).imagePath(post.getImagePath()).place(post.getPlace())

					.likeCount(likeCount).isLike(isLike)

					.commentList(commentList)

					.build();

			postDtoList.add(postDto);
		}

		return postDtoList;
	}

	public boolean addPost(long loginId, String title, String contents, String place, MultipartFile file) {
		String imagePath = FileManager.saveFile(loginId, file);
		if (imagePath == null) {
			return false;
		}
		Post post = Post.builder().loginid(loginId).title(title).contents(contents).imagePath(imagePath).place(place)
				.build();
		try {
			postRepository.save(post);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}

	public Post getPost(long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		if (optionalPost.isPresent()) {
			return optionalPost.get();
		} else {
			return null;
		}
	}

}
