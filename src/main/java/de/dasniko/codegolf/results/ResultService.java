package de.dasniko.codegolf.results;

import com.amazonaws.services.s3.AmazonS3;
import de.dasniko.codegolf.User;
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

    public void saveSourcecode(User user, String sourceCode) {
        String fileName = "source_" + user.getUsername() + ".txt";
        s3.putObject(BUCKET, fileName, sourceCode);
    }

    public List<ResultEntry> getResultlist() {
        List<ResultEntry> entries = (List<ResultEntry>) resultEntryRepository.findAll();
        return entries.stream().sorted((o1, o2) -> o2.getCountChars() - o1.getCountChars()).collect(Collectors.toList());
    }

    public void updateResultlist(User user, int count) {
        ResultEntry entry = ResultEntry.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .countChars(count)
                .build();

        resultEntryRepository.save(entry);
    }

}
