package docker.example.application;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link MyEntity}
 */
@Value
public class MyEntityDto implements Serializable {
    Long id;
    String name;
}