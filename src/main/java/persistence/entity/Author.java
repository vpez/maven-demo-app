package persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(generator = "author_sequence")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
