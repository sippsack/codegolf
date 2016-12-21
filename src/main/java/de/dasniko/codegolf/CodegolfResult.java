package de.dasniko.codegolf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodegolfResult {
    private boolean success;
    private String result;
    private int count;
    private int rank;
}
