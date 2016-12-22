package de.dasniko.codegolf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Controller
@RequestMapping("/")
public class StaticResourceController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "rules", method = RequestMethod.GET)
    public String rules() {
        return "rules";
    }
}
