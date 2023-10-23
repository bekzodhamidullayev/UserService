package uz.pdp.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.userservice.dto.UserCreateDto;
import uz.pdp.userservice.dto.VerifyCodeDto;
import uz.pdp.userservice.service.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String auth (@RequestBody UserCreateDto dto) {
        return userService.save(dto);
    }

    @PostMapping("/verification")
    public String verification (@RequestBody VerifyCodeDto dto) {
        return userService.verification(dto);
    }


//    @PostMapping("/sign-in")
//    public String test (@RequestBody AuthDto dto) {
//        userService.signIn(dto);
//        return "test";
//    }
}
