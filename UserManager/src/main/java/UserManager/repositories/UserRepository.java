package UserManager.repositories;

import org.springframework.data.repository.CrudRepository;

import UserManager.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByName(String name);
}