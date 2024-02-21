package docker.example.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
    List<MyEntityDto> findByNameNotNullAndIdNotNull();
    MyEntityDto findByNameNotNull();
}