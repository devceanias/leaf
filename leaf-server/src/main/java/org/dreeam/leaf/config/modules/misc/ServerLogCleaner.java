package org.dreeam.leaf.config.modules.misc;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;
import org.dreeam.leaf.config.LeafConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class ServerLogCleaner extends ConfigModules {
    public static boolean enabled = true;
    public static int expiry = 14;

    public String getBasePath() {
        return EnumConfigCategory.MISC.getBaseKeyName() + ".server-log-cleaner";
    }

    @Override
    public void onLoaded() {
        final String base = getBasePath();

        enabled = config.getBoolean(
            base + ".enabled", enabled, config.pickStringRegionBased(
            "Prunes stale files from the server log directory during startup.",
            "在服务器启动时清理日志目录中的过期文件。"
        ));
        expiry = config.getInt(
            base + ".expiry", expiry, config.pickStringRegionBased(
            "Removes log files whose last modified time is older than this many days.",
            "删除最后修改时间早于指定天数的日志文件。"
        ));

        cleanLogs();
    }

    private void cleanLogs() {
        if (!enabled) {
            return;
        }

        if (expiry < 0) {
            return;
        }

        final Path directory = Path.of("logs");

        if (!Files.isDirectory(directory)) {
            return;
        }

        final Instant cutoff = Instant.now().minus(expiry, ChronoUnit.DAYS);

        try (final Stream<Path> files = Files.walk(directory)) {
            files
                .filter(Files::isRegularFile)
                .forEach(path -> deleteLog(path, cutoff));
        } catch (final IOException exception) {
            LeafConfig.LOGGER.warn("Failed to scan log directory for cleanup.", exception);
        }
    }

    private void deleteLog(final Path path, final Instant cutoff) {
        final FileTime time;

        try {
            time = Files.getLastModifiedTime(path);
        } catch (final IOException exception) {
            LeafConfig.LOGGER.warn("Failed to read log timestamp for {}.", path, exception);

            return;
        }

        if (!time.toInstant().isBefore(cutoff)) {
            return;
        }

        try {
            Files.deleteIfExists(path);
        } catch (final IOException exception) {
            LeafConfig.LOGGER.warn("Failed to delete old log file {}.", path, exception);
        }
    }
}
