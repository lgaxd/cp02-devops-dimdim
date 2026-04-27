package br.com.dimdim.cp02.repository;
import br.com.dimdim.cp02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}