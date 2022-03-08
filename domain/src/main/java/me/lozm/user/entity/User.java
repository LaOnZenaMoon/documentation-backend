package me.lozm.user.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lozm.common.entity.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "PASSWORD", nullable = false, unique = true)
    private String encryptedPassword;


    public void encryptPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
