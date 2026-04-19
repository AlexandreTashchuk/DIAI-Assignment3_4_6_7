package pt.unl.fct.iadi.novaevents.controller

import org.springframework.stereotype.Controller
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController {

    @GetMapping("/login")
    fun loginPage(authentication: Authentication?): String {
        if (authentication != null && authentication.isAuthenticated && authentication.name != "anonymousUser") {
            return "redirect:/clubs"
        }
        return "auth/login"
    }
}

