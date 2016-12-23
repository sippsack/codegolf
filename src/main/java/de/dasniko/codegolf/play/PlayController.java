package de.dasniko.codegolf.play;

import de.dasniko.codegolf.User;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessToken;
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
    private final AccessToken accessToken;

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model) {
        model.addAttribute(new PlayRequest());
        return "play";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String play(PlayRequest playRequest, Model model) throws Exception {
        playRequest.setUser(User.from(accessToken));
        PlayResult result = playService.play(playRequest);
        model.addAttribute(playRequest);
        model.addAttribute(result);
        return "play";
    }

}
