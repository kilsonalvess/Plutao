package br.edu.ifpb.pweb2.plutao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdministradorController {
    @GetMapping
    public ModelAndView getHome(ModelAndView mav) {
        mav.setViewName("administrador/home");
        return mav;
    }
}
