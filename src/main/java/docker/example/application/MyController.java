package docker.example.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/server/test")
@RequiredArgsConstructor
public class MyController {

    private final MyEntityRepository myEntityRepository;

    @Value("${fortnite.bruh}")
    private String fortnite;

    @GetMapping("/")
    public ResponseEntity<List<MyEntityDto>> getAllMyEntities() {
        return ResponseEntity.ok().body(myEntityRepository.findByNameNotNullAndIdNotNull());
    }

    @GetMapping("/fortnite")
    public ResponseEntity<String> getHello() throws ExecutionException, InterruptedException, TimeoutException {
        return ResponseEntity.ok().body(fortnite);
    }

    @PostMapping("/")
    public ResponseEntity<MyEntityDto> createNew(@RequestBody MyEntityDto myEntityDto) {
        MyEntity myEntity = new MyEntity();
        myEntity.setName(myEntityDto.getName());
        myEntityRepository.save(myEntity);

        return ResponseEntity.ok().body(myEntityDto);
    }


}
