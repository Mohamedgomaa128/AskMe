package fm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread implements Serializable {
    private static final long serialVersionUID = 1L;

    private Question parentQuestion;
    private List<Question> questions = new ArrayList<>();
}
