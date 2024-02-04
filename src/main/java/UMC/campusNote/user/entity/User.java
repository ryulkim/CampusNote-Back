package UMC.campusNote.user.entity;

import UMC.campusNote.common.BaseEntity;
import UMC.campusNote.friend.Friend;
import UMC.campusNote.mapping.UserLesson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(length = 100)
    private String clientId;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String university;

    private String img; // 프로필 이미지

    @ColumnDefault("true")
    private boolean status;

    @Column(length = 10)
    private String currentSemester;

    @Column(length = 10)
    private String role; //USER, ADMIN, MANAGER

    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    @Builder.Default
    List<Friend> friendList1 = new ArrayList<>();

    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    @Builder.Default
    List<Friend> friendList2 = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    List<UserLesson> userLessonList = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role1= Role.valueOf(role);
        log.info("role"+role);

        return role1.getAuthorities();
    }

    @Override
    public String getPassword() {
        return clientId;
    }

    @Override
    public String getUsername() {
        return clientId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
