package com.menushop.service;

import com.menushop.model.dataService.UserDataService;
import com.menushop.model.entity.CustomUserDetail;
import com.menushop.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CartService cartService;

    public List<User> findAll() {
        return userDataService.findAll();
    }

    public User findById(long id) {
        return userDataService.findById(id);
    }

    public Optional<User> findByUsername(String name) {
        return userDataService.findByUsername(name);
    }

    public Optional<User> findByEmail(String email) {
        return userDataService.findByEmail(email);
    }

    public void deleteById(long id) {
        userDataService.deleteById(id);
    }

    public void save(User user) {
        userDataService.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public User getCurrentUser(User user) {
        return findById(user.getId());
    }

    public boolean create(User user) {
        user.setActive(true);
        user.setRole(roleService.findByTitle("USER"));
        save(user);
        return true;
    }

    public void update(User user, String role, String username, String password, String email) {
        user.setUsername(username);
        user.setRole(roleService.findByTitle(role));
        user.setEmail(email);
        user.setPassword(password);
        save(user);
    }

    private void update(User user, String username, String password, String email) {
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        save(user);
    }

    public String userEditConfiguration(CustomUserDetail customUserDetail, @Valid User newUserInformation, BindingResult bindingResult, String newPassword, String repeatNewPassword, Model model) {
        User currentUser = getCurrentUser(customUserDetail);
        boolean newPasswordEmpty = StringUtils.isEmpty(newPassword);
        boolean repeatNewPasswordEmpty = StringUtils.isEmpty(repeatNewPassword);
        boolean equalsNewPasswords = newPassword.equals(repeatNewPassword);
        boolean currentInputOldPassword = newUserInformation.getPassword().equals(currentUser.getPassword());
        boolean isEmailAlreadyExist = findByEmail(newUserInformation.getEmail()).isPresent();
        if (!currentUser.getEmail().equals(newUserInformation.getEmail()) && isEmailAlreadyExist)
            model.addAttribute("emailExistError", "Email is already in use");
        boolean present1 = findByUsername(newUserInformation.getUsername()).isPresent();
        if (!currentUser.getUsername().equals(newUserInformation.getUsername()) && present1) {
            model.addAttribute("usernameExistError", "Username is already in use");
        }
        if (!currentInputOldPassword) {
            model.addAttribute("passwordInputError", "Old password incorrect");
        }
        if (newPasswordEmpty) {
            model.addAttribute("password1EmptyError", "new password can't be empty");
        }
        if (repeatNewPasswordEmpty) {
            model.addAttribute("password2EmptyError", "repeat password can't be empty");
        }
        if (!newPassword.equals(repeatNewPassword)) {
            model.addAttribute("password1DifferentError", "Password are different");
            model.addAttribute("password2DifferentError", "Password are different");
        }
        if (newPasswordEmpty || repeatNewPasswordEmpty || !currentInputOldPassword || bindingResult.hasErrors() || !equalsNewPasswords
                || (!currentUser.getEmail().equals(newUserInformation.getEmail()) && isEmailAlreadyExist) || (!currentUser.getUsername().equals(newUserInformation.getUsername()) && present1)) {
            Collector<FieldError, ?, Map<String, String>> fieldErrorMapCollector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );

            Map<String, String> collectErrors = bindingResult.getFieldErrors().stream().collect(fieldErrorMapCollector);
            model.mergeAttributes(collectErrors);
            model.addAttribute("currentUser", currentUser);
            return "accountEdit";
//            model.addAttribute("url","accountEdit");
        }
        update(currentUser, newUserInformation.getUsername(), newPassword, newUserInformation.getEmail());
//        model.addAttribute("url","redirect:/account");
        return "redirect:/account";
    }
}
