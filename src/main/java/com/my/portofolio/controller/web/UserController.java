package com.my.portofolio.controller.web;

import com.my.portofolio.dto.user.UserResponse;
import com.my.portofolio.dto.user.UserUpdateRequest;
import com.my.portofolio.model.User;
import com.my.portofolio.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String viewProfile(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "redirect:/login";
        }
        UserResponse userResponse = userService.getById(user.getId());
        model.addAttribute("user", userResponse);
        if (!model.containsAttribute("userUpdateRequest")) {
            UserUpdateRequest request = UserUpdateRequest.builder()
                    .email(userResponse.getEmail())
                    .roleUser(userResponse.getRoleUser())
                    .build();
            model.addAttribute("userUpdateRequest", request);
        }
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @Valid @ModelAttribute("userUpdateRequest") UserUpdateRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (user == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userUpdateRequest", bindingResult);
            redirectAttributes.addFlashAttribute("userUpdateRequest", request);
            redirectAttributes.addFlashAttribute("error", "Validation failed: " + bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/profile";
        }

        try {
            userService.updateUser(user.getId(), request);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("userUpdateRequest", request);
        }
        return "redirect:/profile";
    }
}
