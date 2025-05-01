package peaksoft.springboot2025.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
    @SequenceGenerator(name = "post_gen", sequenceName = "post_seq", allocationSize = 1)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private String image;
    private LocalDate date;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private User user;

    public Post(String title, String description, String image, LocalDate date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
    }


}
