package de.dasniko.codegolf.results;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Controller
@RequestMapping("results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @RequestMapping(method = RequestMethod.GET)
    public String getResults(Model model) {
        List<ResultEntry> entries = resultService.getResultlist();
        model.addAttribute(entries);
        return "results";
    }

}
