package uz.pdp.userservice.service;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.dto.UserCreateDto;
import uz.pdp.userservice.dto.VerifyCodeDto;
import uz.pdp.userservice.entity.UserEntity;
import uz.pdp.userservice.entity.UserRole;
import uz.pdp.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final VerifyCodeService verifyCodeService;
    private final ModelMapper modelMapper;

    public String save(UserCreateDto dto){
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        entity.setRole(UserRole.USER);
        entity.setIsVerified(false);
        userRepository.save(entity);
        verifyCodeService.generateCode(dto.getEmail());
        return "Saved";
    }

    public String verification(VerifyCodeDto dto) {
        if(verifyCodeService.isExpire(dto)){
            UserEntity entity = userRepository.findByEmail(dto.getEmail());
            entity.setIsVerified(true);
            userRepository.save(entity);
            return "Verificated";
        }
        return "Wrong password or time finished";
    }
}
