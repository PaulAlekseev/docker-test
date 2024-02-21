package docker.example.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class MyController {

    private final MyEntityRepository myEntityRepository;

    public MyController(MyEntityRepository myEntityRepository) {
        this.myEntityRepository = myEntityRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<MyEntityDto>> getAllMyEntities() {
        return ResponseEntity.ok().body(myEntityRepository.findByNameNotNullAndIdNotNull());
    }

    @PostMapping("/")
    public ResponseEntity<MyEntityDto> createNew(@RequestBody MyEntityDto myEntityDto) {
        MyEntity myEntity = new MyEntity();
        myEntity.setName(myEntityDto.getName());
        myEntityRepository.save(myEntity);

        return ResponseEntity.ok().body(myEntityDto);
    }
}
