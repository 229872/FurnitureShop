package pl.bdygasinski.dataaccess.entity;

import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
public class PersonalData extends AbstractEntity {
}
