package de.dasniko.codegolf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Controller
@RequestMapping("/")
public class StaticResourceController {

    @RequestMapping
    public String index() {
        return "index";
    }
}
