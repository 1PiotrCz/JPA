package pl.piotrcz.Jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piotrcz.Jpa.Models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Piotr Czubkowski on 2017-06-12.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    List<User> findUserByRole(String role);
    List<User> findByDatetimeBetween(Date date1, Date date2);
    List<User> findByUsernameContainingAndIdGreaterThan(String reg, int id);

    //    PAGINACJA:
    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);
}
