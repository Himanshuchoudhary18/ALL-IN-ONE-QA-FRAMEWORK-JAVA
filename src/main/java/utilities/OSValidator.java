package utilities;

public class OSValidator {
    public static String shellType;
    public static String delimiter;

    public static void setPropValues(String OS) {
        if (isWindows(OS)) {
            shellType = "cmd";
            delimiter = "\\";
        } else if (isMac(OS)) {
            shellType = "/bin/bash";
            delimiter = "/";
        } else if (isUnix(OS)) {
            shellType = "/bin/sh";
            delimiter = "/";
        } else {
            shellType = "cmd";
            delimiter = "\\";
        }
    }

    private static boolean isWindows(String OS) {
        return (OS.contains("win"));
    }

    private static boolean isMac(String OS) {
        return (OS.contains("mac"));
    }

    private static boolean isUnix(String OS) {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
    }
}
