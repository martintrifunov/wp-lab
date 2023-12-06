package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.UserFullName;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private boolean credentialsInvalid(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User login(String username, String password) {
        if(credentialsInvalid(username,password)){
            throw new IllegalArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth) {
        if(credentialsInvalid(username,password)){
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        UserFullName fullname=new UserFullName();
        fullname.setName(name);
        fullname.setSurname(surname);
        User user=new User(username, fullname, password, dateOfBirth);
        return userRepository.save(user);
    }
}