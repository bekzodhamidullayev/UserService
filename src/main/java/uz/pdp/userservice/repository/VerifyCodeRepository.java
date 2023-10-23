package uz.pdp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.userservice.entity.VerifyCode;
import java.util.UUID;

@Repository
public interface VerifyCodeRepository extends JpaRepository<VerifyCode, UUID> {
    VerifyCode findByEmail(String email);
}
