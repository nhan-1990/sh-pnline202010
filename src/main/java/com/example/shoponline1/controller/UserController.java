package com.example.shoponline1.controller;

import com.example.shoponline1.dao.IRoleDao;
import com.example.shoponline1.dao.IUserDao;
import com.example.shoponline1.dto.CartInfo;
import com.example.shoponline1.dto.ChangePassForm;
import com.example.shoponline1.dto.EditProfile;
import com.example.shoponline1.dto.OrderDetailInfo;
import com.example.shoponline1.dto.OrderInfo;
import com.example.shoponline1.dto.RegisterForm;
import com.example.shoponline1.entity.Role;
import com.example.shoponline1.entity.User;
import com.example.shoponline1.service.IOrderService;
import com.example.shoponline1.utils.Utils;
import com.example.shoponline1.validation.ChangePassValidation;
import com.example.shoponline1.validation.CheckPassValidation;
import com.example.shoponline1.validation.EditProfileValidation;
import com.example.shoponline1.validation.RegisterValidation;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    RegisterValidation registerValidation;

    @Autowired
    EditProfileValidation editProfileValidation;

    @Autowired
    CheckPassValidation checkPassValidation;

    @Autowired
    ChangePassValidation changePassValidation;

    @Autowired
    IOrderService iOrderService;

    @Autowired
    JavaMailSender emailSender;

    @RequestMapping("/login")
    public String login() {
        return "/business/user/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "redirect:/home";
    }

    @RequestMapping("/register")
    public String register(Model model) {

        RegisterForm register = new RegisterForm();
        model.addAttribute("register", register);

        return "/business/user/register";
    }

    @RequestMapping("/registers")
    public String registers(@ModelAttribute("register") RegisterForm register,
            Model model, BindingResult bindingResult) {

        registerValidation.validate(register, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/business/user/register";
        }
        User user = new User();
        user.setEmail(register.getEmail());
        user.setUserName(register.getName());
        user.setAddress(register.getAddress());
        user.setPhone(register.getPhone());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleDao.findByRoleName("ROLE_MEMBER"));
        user.setRole(roles);
        userDao.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(register.getEmail());
        message.setText("Đăng ký thành công!");
        this.emailSender.send(message);

        return "redirect:/login";
    }

    @RequestMapping("/seeProfile")
    public String seeProfile(Model model, HttpSession session) {

        EditProfile register = new EditProfile();
        User user = (User) session.getAttribute("user");
        register.setId(user.getUserId());
        register.setEmail(user.getEmail());
        register.setName(user.getUserName());
        register.setAddress(user.getAddress());
        register.setPhone(user.getPhone());
        model.addAttribute("register", register);
        //session.removeAttribute("user");

        return "/business/user/seeProfile";
    }

    @RequestMapping("/editProfile")
    public String editProfile(Model model, HttpSession session) {

        EditProfile register = new EditProfile();
        User user = (User) session.getAttribute("user");
        register.setId(user.getUserId());
        register.setEmail(user.getEmail());
        register.setName(user.getUserName());
        register.setAddress(user.getAddress());
        register.setPhone(user.getPhone());
        //register.setPassword(user.getPassword());

        model.addAttribute("register", register);
        //session.removeAttribute("user");

        return "/business/user/editProfile";
    }

    @RequestMapping("/saveEditProfile")
    public String saveEditProfile(@ModelAttribute("register") EditProfile register,
            Model model, BindingResult bindingResult, HttpSession session) {

        editProfileValidation.validate(register, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/business/user/editProfile";
        }
        User user = (User) session.getAttribute("user");
        user.setUserId(register.getId());
        user.setEmail(register.getEmail());
        user.setUserName(register.getName());
        user.setAddress(register.getAddress());
        user.setPhone(register.getPhone());
        //user.setPassword(passwordEncoder.encode(register.getPassword()));
        //HashSet<Role> roles = new HashSet<>();
        //roles.add(roleDao.findByRoleName("ROLE_MEMBER"));
        //user.setRole(roles);
        userDao.save(user);

        session.setAttribute("user", user);

        return "redirect:/home";
    }

    @RequestMapping("/checkPassword")
    public String enterCheckPass(Model model, HttpSession session,
            @ModelAttribute("changePassForm") ChangePassForm changePassForm) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "business/user/checkPassword";
    }

    @RequestMapping("/checkPass")
    public String checkPass(HttpSession session, Model model,
            @ModelAttribute("changePassForm") ChangePassForm changePassForm,
            BindingResult bindingResult) {

        User user = (User) session.getAttribute("user");

        checkPassValidation.validate(changePassForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/business/user/checkPassword";
        }
        String message = null;
        if (passwordEncoder.matches(changePassForm.getOldPass(), user.getPassword())) {
            return "business/user/changePassword";
        } else {
            message = "Incorrect";
            model.addAttribute("message", message);
            return "business/user/checkPassword";
        }
    }

    @RequestMapping("/changePassword")
    public String changePass(Model model, HttpSession session) {

        ChangePassForm changePassForm = new ChangePassForm();
        User user = (User) session.getAttribute("user");

        return "business/user/changePassword";
    }

    @RequestMapping("/saveChangePass")
    public String saveChangePass(@ModelAttribute("changePassForm") ChangePassForm changePassForm,
            Model model, BindingResult bindingResult, HttpSession session) {

        changePassValidation.validate(changePassForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/business/user/changePassword";
        }
        String message = null;
        if(!(changePassForm.getComfirmPass().equals(changePassForm.getNewPass()))){
            message = "";
            model.addAttribute("message", message);
            return "/business/user/changePassword";
        }
        
        User user = (User) session.getAttribute("user");

        user.setPassword(passwordEncoder.encode(changePassForm.getNewPass()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleDao.findByRoleName("ROLE_MEMBER"));
        user.setRole(roles);
        userDao.save(user);

        session.setAttribute("user", user);

        return "redirect:/home";
    }

    @RequestMapping("/history")
    public String seeHistory(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        CartInfo cartInfo = Utils.getCartInSession(session);
        model.addAttribute("cartInfo", cartInfo);

        if (user.getOrder() != null) {
            List<OrderInfo> orderInfoList = iOrderService.getAllOrderInfo(user.getUserId());
            model.addAttribute("orderDetail", orderInfoList);

            //System.out.println("rom: " + orderInfoList.get(0).getRom());
            return "business/user/history";

        } else {

            return "redirect:/home";
        }
    }

    @RequestMapping("/orderDetailInfo")
    public String seeOrderDetail(Model model, HttpSession session,
            @RequestParam(value = "orderid") int orderid) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        CartInfo cartInfo = Utils.getCartInSession(session);
        model.addAttribute("cartInfo", cartInfo);

        List<OrderDetailInfo> orderDetailInfos = iOrderService.orderDetailInfos(orderid);
        model.addAttribute("orderDetailInfos", orderDetailInfos);

        return "business/user/historyDetail";
    }

    @RequestMapping("/403")
    public String erros() {
        return "/system/403";
    }

}
