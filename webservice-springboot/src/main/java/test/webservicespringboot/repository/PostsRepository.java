package test.webservicespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.webservicespringboot.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
