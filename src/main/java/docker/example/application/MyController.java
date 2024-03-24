package docker.example.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/test")
@RequiredArgsConstructor
public class MyController {

    private final MyEntityRepository myEntityRepository;

    @GetMapping("/")
    public ResponseEntity<List<MyEntityDto>> getAllMyEntities() {
        return ResponseEntity.ok().body(myEntityRepository.findByNameNotNullAndIdNotNull());
    }

    @GetMapping("/fortnite")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok().body("hello2");
    }

    @PostMapping("/")
    public ResponseEntity<MyEntityDto> createNew(@RequestBody MyEntityDto myEntityDto) {
        MyEntity myEntity = new MyEntity();
        myEntity.setName(myEntityDto.getName());
        myEntityRepository.save(myEntity);

        return ResponseEntity.ok().body(myEntityDto);
    }


}
