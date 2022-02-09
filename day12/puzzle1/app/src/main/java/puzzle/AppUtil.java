package puzzle;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AppUtil {

    static List<Integer> toIntList(String singleDigitNumbers) {
        var numbers = new ArrayList<Integer>();
        for (char ch : singleDigitNumbers.toCharArray()) {
            numbers.add(Character.digit(ch, 10));
        }
        return numbers;
    }

    static String resourceAsString(String resourceName) {
        var input = AppUtil.class.getClassLoader().getResourceAsStream(resourceName);
        if (input == null) {
            throw new IllegalArgumentException("Could not load resource " + resourceName);
        }
        try (input) {
            var bytes = input.readAllBytes();
            return new String(bytes);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read resource " + resourceName, e);
        }
    }

    static List<String> linesFromResource(String resourceName) {
        var input = AppUtil.class.getClassLoader().getResourceAsStream(resourceName);
        if (input == null) {
            throw new IllegalArgumentException("Could not load resource " + resourceName);
        }
        try (input) {
            var bytes = input.readAllBytes();
            return Arrays.asList(new String(bytes).split("\n"));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read resource " + resourceName, e);
        }
    }

    static Path createTempDirectory() {
        try {
            return Files.createTempDirectory("aoc-2021-day1puzzle1");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    static void deleteDirectoryAndChildren(Path tempDir) {
        try {
            Files.walk(tempDir, FileVisitOption.FOLLOW_LINKS)
                    .forEach(p -> p.toFile().delete());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to tidy up", e);
        }
    }

    public static Path writeResourceToFile(Path dir, String resourceName) {
        try {
            var file = Path.of(dir.toString(), resourceName);
            var resourceStream = AppUtil.class.getResourceAsStream(resourceName);
            if (resourceStream == null) {
                throw new IllegalStateException("Failed to read resource " + resourceName);
            }
            Files.write(file, resourceStream.readAllBytes());
            return file;
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Failed to write resource to disk", e);
        }
    }
}
