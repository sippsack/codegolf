package de.dasniko.codegolf;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@RestController
@RequestMapping("play")
@RequiredArgsConstructor
public class PlayController {

    private final PlayService playService;
    private final ResultService resultService;

    @RequestMapping(method = RequestMethod.POST)
    public CodegolfResult play(@RequestBody String sourceCode) throws Exception {
        String username = "dasniko";
        return playService.play(username, sourceCode);
    }

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public List<ResultEntry> getResults() {
        return resultService.getResultlist();
    }

}
