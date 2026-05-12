package fm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iD;

    @Column(unique = true)
    private String userName;
    private String passWord;
    private boolean allowAnonymous;

    @OneToMany(mappedBy = "asker", cascade = CascadeType.ALL)
    private List<Question> toOthers = new ArrayList<>();

    @OneToMany(mappedBy = "askedTo", cascade = CascadeType.ALL)
    private List<Question> fromOthers = new ArrayList<>();

    public User(String userName, String passWord, boolean allowAnonymous) {
        this.userName = userName;
        this.passWord = passWord;
        this.allowAnonymous = allowAnonymous;
    }
}
