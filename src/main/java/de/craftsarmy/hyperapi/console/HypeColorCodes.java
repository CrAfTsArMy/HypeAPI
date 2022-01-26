package de.craftsarmy.hyperapi.console;

public enum HypeColorCodes {
    
    RESET("\u001B[0m", "§r"),

    BLACK("\u001B[90m", "§r"),
    RED("\u001B[91m", "§c"),
    GREEN("\u001B[92m", "§r"),
    YELLOW("\u001B[93m", "§r"),
    BLUE("\u001B[94m", "§r"),
    PURPLE("\u001B[95m", "§r"),
    CYAN("\u001B[96m", "§r"),
    WHITE("\u001B[97m", "§r"),

    DARK_BLACK("\u001B[30m", "§r"),
    DARK_RED("\u001B[31m", "§r"),
    DARK_GREEN("\u001B[32m", "§r"),
    DARK_YELLOW("\u001B[33m", "§r"),
    DARK_BLUE("\u001B[34m", "§r"),
    DARK_PURPLE("\u001B[35m", "§r"),
    DARK_CYAN("\u001B[36m", "§r"),
    DARK_WHITE("\u001B[37m", "§r"),

    BACKGROUND_BLACK("\u001B[100m", "§r"),
    BACKGROUND_RED("\u001B[101m", "§r"),
    BACKGROUND_GREEN("\u001B[102m", "§r"),
    BACKGROUND_YELLOW("\u001B[103m", "§r"),
    BACKGROUND_BLUE("\u001B[104m", "§r"),
    BACKGROUND_PURPLE("\u001B[105m", "§r"),
    BACKGROUND_CYAN("\u001B[106m", "§r"),
    BACKGROUND_WHITE("\u001B[107m", "§r"),

    DARK_BACKGROUND_BLACK("\u001B[40m", "§r"),
    DARK_BACKGROUND_RED("\u001B[41m", "§r"),
    DARK_BACKGROUND_GREEN("\u001B[42m", "§r"),
    DARK_BACKGROUND_YELLOW("\u001B[43m", "§r"),
    DARK_BACKGROUND_BLUE("\u001B[44m", "§r"),
    DARK_BACKGROUND_PURPLE("\u001B[45m", "§r"),
    DARK_BACKGROUND_CYAN("\u001B[46m", "§r"),
    DARK_BACKGROUND_WHITE("\u001B[47m", "§r");

    private final String code;

    HypeColorCodes(String code, String alias) {
        this.code = code;
    }



}
