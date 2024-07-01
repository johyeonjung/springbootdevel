package me.johyeonjung.springbootdeveloper.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.johyeonjung.springbootdeveloper.domain.Article;

@NoArgsConstructor  //기본 생성자 추가
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {
    //dto패키지를 컨트롤러에서 요창한 본문을 받을 객체 파일
    private String title;
    private String content;

    //DTO를 엔티티로 만들어주는 메소드
    //추후 블로그 글을 추가할 때 저장할 엔티티로 변환하는 용도로 사용
    public Article toEntity(String author) {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
