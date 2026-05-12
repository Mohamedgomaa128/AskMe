package fm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iD;

    @Column(length = 1000)
    private String question;
    
    @Column(length = 1000)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "asker_id")
    @JsonIgnoreProperties({"toOthers", "fromOthers", "passWord"})
    private User asker;

    @ManyToOne
    @JoinColumn(name = "asked_to_id")
    @JsonIgnoreProperties({"toOthers", "fromOthers", "passWord"})
    private User askedTo;

    public Question(String q, User a, User at) {
        this.question = q;
        this.asker = a;
        this.askedTo = at;
    }
}