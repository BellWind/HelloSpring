package anotation.repository;

import org.springframework.stereotype.Repository;

@Repository("userRepostory")
public class UserRepositoryImlp implements UserRepository {

    @Override
    public void save() {
        System.out.println("UserRepositoryImlp saved.");
    }
}
