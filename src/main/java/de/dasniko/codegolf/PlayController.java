package de.dasniko.codegolf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Controller
@RequestMapping("play")
@RequiredArgsConstructor
public class PlayController {

    private final PlayService playService;

    @RequestMapping(method = RequestMethod.GET)
    public String start() {
        return "play";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String play(@RequestBody String sourceCode, Model model) throws Exception {
        String username = "dasniko";
        CodegolfResult result = playService.play(username, sourceCode);
        model.addAttribute(result);
        return "result";
    }

}
