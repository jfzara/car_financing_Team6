package DAO;
import java.util.List;
public interface UserDao {
    User getUserById(int id);
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}

