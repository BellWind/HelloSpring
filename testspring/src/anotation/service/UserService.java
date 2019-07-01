package anotation.service;

import anotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void hi() {
        System.out.println("UserService hi~");
        userRepository.save();
    }

}
