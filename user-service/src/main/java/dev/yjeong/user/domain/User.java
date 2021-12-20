package dev.yjeong.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "update users set deleted_at=now() where id=?")
@Where(clause = "deleted_at is null")
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(320)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(65)")
    private String password;

    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String nickname;

    @OneToOne
    @JoinColumn(name = "salt_id")
    private Salt salt;

    @Builder
    public User(String email, String password, String name, String nickname, Salt salt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.salt = salt;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
