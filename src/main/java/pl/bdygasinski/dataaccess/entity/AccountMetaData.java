package pl.bdygasinski.dataaccess.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data

@Embeddable
public class AccountMetaData {

    @Column(name = "last_positive_auth_ip_address")
    private String lastPositiveAuthIpAddress;

    @Column(name = "last_negative_auth_ip_address")
    private String lastNegativeAuthIpAddress;

    @Column(name = "last_positive_auth_time")
    private LocalDateTime lastPositiveAuthTime;

    @Column(name = "last_negative_auth_time")
    private LocalDateTime lastNegativeAuthTime;

    @Column(name = "blockade_end_time")
    private LocalDateTime blockadeEndTime;

    @Column(columnDefinition = "int default 0 not null", name = "negative_auth_attempts")
    private Integer negativeAuthAttempts;


}
