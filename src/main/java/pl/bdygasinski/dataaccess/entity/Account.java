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
@NamedQueries({
        @NamedQuery(
                name = Account.FIND_BY_LOGIN,
                query = "SELECT a FROM Account a WHERE a.login = ?1"
        ),
        @NamedQuery(
                name = Account.FIND_BY_EMAIL,
                query = "SELECT a FROM Account a WHERE a.email = ?1"
        )
})
public class Account extends AbstractEntity {
    public static final String FIND_BY_LOGIN = "Account.findByLogin";
    public static final String FIND_BY_EMAIL = "Account.findByEmail";

    @Column(nullable = false, updatable = false, unique = true)
    private String login;

    @Column(nullable = false, unique = true)
    private String email;

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
