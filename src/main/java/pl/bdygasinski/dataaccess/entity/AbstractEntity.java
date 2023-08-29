package pl.bdygasinski.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false, name = "creation_time")
    private LocalDateTime creationTime;

    @Setter
    @Column(name = "modification_time")
    private LocalDateTime modificationTime;

    @Column(nullable = false, name = "created_by_account_with_login")
    private String createdByAccountWithLogin;

    @Setter
    @Column(name = "modified_by_account_with_login")
    private String modifiedByAccountWithLogin;
}
