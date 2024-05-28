package com.example.backenduppgift.Security;

import com.example.backenduppgift.DTO.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserDetailsServiceImpl userService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @RequestMapping("/all")
    public String getAllCustomers(Model model){
        List<UserDto> users = getAllUsers();
        model.addAttribute("allUsers", users);
        model.addAttribute("userTitle", "All Users");
        model.addAttribute("addCustomer", "Add Customers");
        return "showAllUsers";
    }

    @PostMapping("/addDone")
    public String addUserDone(@RequestParam String email, @RequestParam String roles, Model model){
        if(userRepository.getUserByUsername(email) == null){
            addUserWithPassword(email, roles,"Password");
        }

        model.addAttribute("email", email);
        model.addAttribute("roles", roles);
        return "redirect:/user/all";
    }

    private void addUserWithPassword(String mail, String group, String password) {
        ArrayList<Role> roles = new ArrayList<>();
        if (group.equalsIgnoreCase("Receptionist") || group.equalsIgnoreCase("Admin")){
            roles.add(roleRepository.findByName(group));
        } else if (group.equalsIgnoreCase("Both")) {
            roles.add(roleRepository.findByName("Admin"));
            roles.add(roleRepository.findByName("Receptionist"));
        }
        System.out.println(roleRepository.findByName(group));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password);
        User user = User.builder().enabled(true).password(hash).username(mail).roles(roles).build();
        userRepository.save(user);
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
