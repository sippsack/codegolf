package de.dasniko.codegolf.play;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String start(Model model) {
        model.addAttribute(new PlayRequest());
        return "play";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String play(PlayRequest playRequest, Model model) throws Exception {
        PlayResult result = playService.play(playRequest);
        model.addAttribute(playRequest);
        model.addAttribute(result);
        return "play";
    }

}
