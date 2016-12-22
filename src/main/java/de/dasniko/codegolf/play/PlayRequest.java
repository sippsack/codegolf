package de.dasniko.codegolf.play;

import lombok.Data;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Data
public class PlayRequest {
    private String username;
    private String sourcecode = "return \"Hello World\";";
}
