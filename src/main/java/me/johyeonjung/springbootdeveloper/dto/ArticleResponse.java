package me.johyeonjung.springbootdeveloper.dto;

import lombok.Getter;
import me.johyeonjung.springbootdeveloper.domain.Article;

@Getter
public class ArticleResponse {
    // /api/articles GET요청이 오면 글 목록을 조회할 finAllArticles()메서드
    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
