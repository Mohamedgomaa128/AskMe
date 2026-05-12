package fm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User signUp(String userName, String password, boolean allowAnonymous) {
        if (userRepository.findByUserName(userName).isPresent()) {
            return null;
        }
        User u = new User(userName, password, allowAnonymous);
        return userRepository.save(u);
    }

    public User login(String userName, String password) {
        Optional<User> u = userRepository.findByUserName(userName);
        if (u.isPresent() && u.get().getPassWord().equals(password)) {
            return u.get();
        }
        return null;
    }
}
