package com.kosmo.cmm.controller;

import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private UserService userService;

    @GetMapping("/accessDenied")
    public String accessDenied(){
        //return "error/accessDeniedAlert";//forward 방식
        //return "error/accessDenied";//forward 방식
        // resources/templates/error/accessDenied.html
        return "redirect:/public/accessDenied";
    }

    @GetMapping("/public/accessDenied")
    public String accessDeniedPublic(){
        return "error/accessDenied";
    }


    @GetMapping("/auth/myPage")
    public String myPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("email", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities());

        return "member/myPage";
        // resources/templates/member/myPage.html
    }

    @GetMapping("/admin/users")
    public String userList(Model model){
        List<UserDTO> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "member/userList";
    }

    @GetMapping("/admin/userDelete")
    public String userDelete(@RequestParam Long id){
        userService.deleteUser(id);
        return "redirect:users";
    }

    @GetMapping("/admin/userEdit/{id}")
    public String userEditForm(@PathVariable Long id, Model model){
        UserDTO user = userService.getUser(id);
        model.addAttribute("user", user);
        return "member/userEdit";
    }

    @PostMapping("/admin/userEdit")
    public String userEditEnd(UserDTO dto){
        userService.updateUser(dto);
        return "redirect:users";    //전체목록으로 가기
    }

}
