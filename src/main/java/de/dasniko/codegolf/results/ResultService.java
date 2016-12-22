package de.dasniko.codegolf.results;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Service
@RequiredArgsConstructor
public class ResultService {

    private static final String BUCKET = "codegolf";

    private final AmazonS3 s3;
    private final ResultEntryRepository resultEntryRepository;

    public void saveSourcecode(String username, String sourceCode) {
        String fileName = "source_" + username + ".txt";
        s3.putObject(BUCKET, fileName, sourceCode);
    }

    public List<ResultEntry> getResultlist() {
        List<ResultEntry> entries = (List<ResultEntry>) resultEntryRepository.findAll();
        return entries.stream().sorted((o1, o2) -> o2.getCountChars() - o1.getCountChars()).collect(Collectors.toList());
    }

    public void updateResultlist(String username, int count) {
        ResultEntry entry = ResultEntry.builder()
                .username(username)
                .countChars(count)
                .build();

        resultEntryRepository.save(entry);
    }

}
