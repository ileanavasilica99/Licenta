package ace.ucv.ro.orientationandorganizationapp.entity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @RequestMapping(value = "/index")
    public String index() {
        return "login";
    }
}
