package br.com.nunesmaia.apiUserViaCep.repository;

import br.com.nunesmaia.apiUserViaCep.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(@NotBlank String email);
}
