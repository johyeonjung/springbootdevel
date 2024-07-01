package me.johyeonjung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.johyeonjung.springbootdeveloper.domain.Article;
import me.johyeonjung.springbootdeveloper.dto.AddArticleRequest;
import me.johyeonjung.springbootdeveloper.dto.ArticleResponse;
import me.johyeonjung.springbootdeveloper.dto.UpdateArticleRequest;
import me.johyeonjung.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController //HTTP response Body에 객체 데이터를 JSON형식으로 변환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;


    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal) {
        Article savedArticle = blogService.save(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {

        // "/api/articles GET요청이 오면 글 전체를 조회하는 findAll() 호출
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        //응답용 객체인 ArticleResponse로 파싱해 body에 담아 클라이언트에게 전송
        return ResponseEntity.ok()
                .body(articles);
    }

        @GetMapping("/api/articles/{id}")
        //url에서 값을 가져오는 애너테이션
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
        }


    // /api/articles/{id} delete요청이 들어오면 글을 삭제하기 위한 메서드
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id)   {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }


    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }




}



