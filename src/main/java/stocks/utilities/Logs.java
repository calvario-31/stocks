package stocks.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Formatter;

public class Logs {
    private static final Logger log = LogManager.getLogger("AUTOMATION");
    private static final String bigSeparator =
            "------------------------------------------------------------------------------------------";
    private static final String smallSeparator = "**********************************************************";

    private static void printTestSeparator() {
        log.info(bigSeparator);
    }

    private static void printSeparator() {
        log.info(smallSeparator);
    }

    private static void printSeparatorDebug() {
        log.debug(smallSeparator);
    }

    private static void printNewLine() {
        log.info("");
    }

    public static void startTest(String testName) {
        printTestSeparator();
        log.info("Test: " + testName);
        printTestSeparator();
    }

    public static void endTest(String status) {
        printTestSeparator();
        log.info(status);
        printTestSeparator();
        printNewLine();
        printNewLine();
    }

    public static void startSuite(String suiteName) {
        printNewLine();
        printTestSeparator();
        printTestSeparator();
        log.info("Suite: " + suiteName);
        printTestSeparator();
        printTestSeparator();
        printNewLine();
    }

    public static void preconditionStart() {
        printSeparator();
        log.info("SETUP");
        printSeparator();
    }

    public static void postConditionStart() {
        printSeparatorDebug();
        log.debug("TEARDOWN");
        printSeparatorDebug();
    }

    public static void postConditionFinish() {
        printSeparatorDebug();
    }

    public static void testSteps() {
        printSeparator();
        log.info("TEST");
        printSeparator();
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void trace(String format, Object... args) {
        log.trace(new Formatter().format(format, args).toString());
    }

    public static void debug(String format, Object... args) {
        log.debug(new Formatter().format(format, args).toString());
    }

    public static void info(String format, Object... args) {
        log.info(new Formatter().format(format, args).toString());
    }

    public static void error(String format, Object... args) {
        log.error(new Formatter().format(format, args).toString());
    }
}
