package peaksoft.springboot2025.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq",
    allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    public User(String firstName, String email, String lastName, int age) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.age = age;
    }
}
