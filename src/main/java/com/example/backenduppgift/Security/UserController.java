package com.example.backenduppgift.Security;

import com.example.backenduppgift.DTO.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserDetailsServiceImpl userService;

    private final UserRepository userRepository;

    @RequestMapping("/all")
    public String getAllCustomers(Model model){
        List<UserDto> users = getAllUsers();
        model.addAttribute("allUsers", users);
        model.addAttribute("userTitle", "All Users");
        model.addAttribute("addCustomer", "Add Customers");
        return "showAllUsers";
    }


















    public UserDto userToUserDTO(User user) {
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return UserDto.builder()
                .username(user.getUsername())
                .roles(roleNames)
                .build();
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> test = userRepository.findAll().stream().map(c->userToUserDTO(c)).toList();
        return userRepository.findAll().stream().map(c->userToUserDTO(c)).toList();
    }
}
