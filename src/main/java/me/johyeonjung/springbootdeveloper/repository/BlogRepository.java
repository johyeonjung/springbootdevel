package me.johyeonjung.springbootdeveloper.repository;

import me.johyeonjung.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
