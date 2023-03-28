package com.asif.EmailsenderSpringBootOauth.controller;

import com.asif.EmailsenderSpringBootOauth.model.EmailDetails;
import com.asif.EmailsenderSpringBootOauth.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComposeMailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/compose")
    public String getMailPage(Model model){
        EmailDetails emailDetails=new EmailDetails();
        model.addAttribute("emailDetails", emailDetails);
        return "compose-page";
    }

    @PostMapping("/sendmail")
    public String sendEmail(@ModelAttribute("emailDetails")EmailDetails emailDetails, BindingResult bindingResult){
        EmailDetails emailDetails1=emailDetails;
        emailService.sendSimpleEmail(emailDetails);
        return "redirect:/compose";
    }
}
