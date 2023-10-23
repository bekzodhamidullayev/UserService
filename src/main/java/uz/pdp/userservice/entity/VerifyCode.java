package uz.pdp.userservice.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerifyCode extends BaseEntity {

    private String email;

    private String code;

    private LocalDateTime exp;


}
