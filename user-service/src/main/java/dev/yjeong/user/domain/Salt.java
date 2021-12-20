package dev.yjeong.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "update salts set deleted_at=now() where id=?")
@Where(clause = "deleted_at is null")
@Table(name = "salts")
@Entity
public class Salt extends BaseEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(35)")
    private String value;

    @Builder
    private Salt(String value) {
        this.value = value;
    }

    public static Salt toEntity(String salt) {
        return Salt.builder()
                .value(salt)
                .build();
    }

    public void updateValue(String value) {
        this.value = value;
    }

}
