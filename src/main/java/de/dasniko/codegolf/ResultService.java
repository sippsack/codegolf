package de.dasniko.codegolf;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Sort sort = new Sort("countChars");
        return (List<ResultEntry>) resultEntryRepository.findAll(sort);
    }

    public void updateResultlist(String username, int count) {
        ResultEntry entry = ResultEntry.builder()
                .username(username)
                .countChars(count)
                .build();

        resultEntryRepository.save(entry);
    }

}
