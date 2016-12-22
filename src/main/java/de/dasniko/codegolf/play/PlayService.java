package de.dasniko.codegolf.play;

import de.dasniko.codegolf.User;
import de.dasniko.codegolf.results.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Service
@RequiredArgsConstructor
public class PlayService {

    private static final String CLASSNAME = "Codegolf";
    private static final String METHODNAME = "play";
    private static final String EXPECTED_RESULT = "Hello World";

    private final File root = new File(System.getProperty("java.io.tmpdir"));

    private final ResultService resultService;

    PlayResult play(PlayRequest playRequest) {
        User user = playRequest.getUser();
        String sourceCode = playRequest.getSourcecode();
        String packageName = user.getUsername().replaceAll("\\W", "");

        File sourceFile = saveSource(packageName, sourceCode);

        try {
            compile(sourceFile);
        } catch (RuntimeException e) {
            return PlayResult.builder()
                    .result(e.getMessage())
                    .build();
        }

        String result = run(packageName);

        boolean success = EXPECTED_RESULT.equals(result);

        int numChars = countChars(sourceCode);

        if (success) {
            resultService.saveSourcecode(user, sourceCode);
            resultService.updateResultlist(user, numChars);
        }

        return PlayResult.builder()
                .success(success)
                .result(result)
                .count(numChars)
                .build();
    }

    @SneakyThrows
    private File saveSource(String packageName, String sourceCode) {
        String source = "package " + packageName + "; public class " + CLASSNAME + " { public String " + METHODNAME + "() { " + sourceCode + "} }";
        File sourceFile = new File(root, packageName + "/" + CLASSNAME + ".java");
        sourceFile.getParentFile().mkdirs();
        Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));
        return sourceFile;
    }

    private void compile(File sourceFile) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        OutputStream outputStream = new ByteArrayOutputStream();
        int compileResult = compiler.run(null, outputStream, outputStream, sourceFile.getPath());
        if (compileResult != 0) {
            throw new RuntimeException(outputStream.toString());
        }
    }

    @SneakyThrows
    private String run(String packageName) {
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()});
        Class<?> cls = Class.forName(packageName + "." + CLASSNAME, true, classLoader);
        Method method = cls.getDeclaredMethod(METHODNAME);
        Object codegolfInstance = cls.newInstance();
        return (String) method.invoke(codegolfInstance);
    }

    private int countChars(String sourceCode) {
        return sourceCode.replaceAll("\\s", "").length();
    }

}
