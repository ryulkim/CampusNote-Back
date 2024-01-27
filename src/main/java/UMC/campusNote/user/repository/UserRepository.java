package UMC.campusNote.user.repository;

import UMC.campusNote.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
