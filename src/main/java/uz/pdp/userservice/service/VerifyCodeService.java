package uz.pdp.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.dto.MailDto;
import uz.pdp.userservice.dto.VerifyCodeDto;
import uz.pdp.userservice.entity.VerifyCode;
import uz.pdp.userservice.repository.VerifyCodeRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerifyCodeService {
    private final VerifyCodeRepository verifyCodeRepository;
    private final MailService mailService;

    public void generateCode(String email){
        Random random = new Random();
        String code = String.valueOf(100000 + random.nextInt(900000));
        LocalDateTime exp = LocalDateTime.now().plus(5, ChronoUnit.MINUTES);
        VerifyCode verifyCode = new VerifyCode(email, code, exp);
        verifyCodeRepository.save(verifyCode);
        MailDto mailDto = new MailDto(email.lines().toList(), code);
        mailService.sendEmail(mailDto);
    }

    public boolean isExpire(VerifyCodeDto dto) {
        VerifyCode verifyCode = verifyCodeRepository.findByEmail(dto.getEmail());
        if(Objects.equals(verifyCode, dto.getCode())){
            verifyCode.getExp().isBefore(LocalDateTime.now());
            return true;
        }
        return false;
    }
}
