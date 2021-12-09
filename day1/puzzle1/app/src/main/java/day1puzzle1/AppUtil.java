package day1puzzle1;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AppUtil {

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
