package UMC.campusNote.friend.repository;

import UMC.campusNote.friend.entity.Friend;
import UMC.campusNote.user.entity.User;
<<<<<<< HEAD
import io.lettuce.core.dynamic.annotation.Param;
=======
>>>>>>> f48bd7b1b20b984b1b6589a058a71b1dac291212
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query("SELECT f FROM Friend f WHERE (f.user1=:user1 AND f.user2=:user2) OR (f.user1=:user2 AND f.user2=:user1)")
<<<<<<< HEAD
    Optional<Friend> findByUser1AndUser2(@Param("user1") User user1, @Param("user2") User user2);
=======
    Optional<Friend> findByUser1AndUser2(User user1, User user2);
>>>>>>> f48bd7b1b20b984b1b6589a058a71b1dac291212
}
