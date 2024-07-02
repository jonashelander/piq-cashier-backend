package com.example.PIQResponseMock.repositories;

/*import com.example.PIQResponseMock.dto.SignInDTO;
import com.example.PIQResponseMock.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserRepository {
    public static List<User> users = new ArrayList<>();

    public void save(User user) {
        users.add(user);
    }

    public void updateUserById(User user) {
        System.out.println("Before index");
        int index = users.indexOf(user);
        System.out.println("After Index: " + index);
        users.set(0, user);
    }

    public User getUserBySignInDTO(SignInDTO signInDTO) {
        User user = users.stream().filter((u -> u.getEmail().equals(signInDTO.getEmail()) && u.getPassword().equals(signInDTO.getPassword()))).findFirst().orElseThrow(() -> new NoSuchElementException());
        return user;
    }

    public User getUserById(String userId) {
        User user = users.stream().filter((u -> u.getUserId().equals(userId))).findFirst().orElseThrow(() -> new NoSuchElementException());
        return user;
    }

    public List findAll() {
        return users.stream().toList();
    }

}*/

import com.example.PIQResponseMock.dto.SignInDTO;
import com.example.PIQResponseMock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User getUserByEmail(String email);
}
