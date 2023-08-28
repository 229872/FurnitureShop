package pl.bdygasinski.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.bdygasinski.dataaccess.entity.enums.AccountRole;
import pl.bdygasinski.dataaccess.entity.enums.AccountState;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
public class Account extends AbstractEntity {

    @Column(nullable = false, updatable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String locale;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(nullable = false, updatable = false, unique = true, name = "personal_data")
    private PersonalData personalData;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountState state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRole role;

    @Embedded
    private AccountMetaData metaData;



}
