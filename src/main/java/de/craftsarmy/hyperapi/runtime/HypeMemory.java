package de.craftsarmy.hyperapi.runtime;

public enum HypeMemory {

    ONEGB(954728448),
    TWOGB(ONEGB.getMinMemory() * 2),
    THREEGB(ONEGB.getMinMemory() * 3),
    FOURGB(ONEGB.getMinMemory() * 4),
    FIVEGB(ONEGB.getMinMemory() * 5),
    SIXGB(ONEGB.getMinMemory() * 6),
    SEVENGB(ONEGB.getMinMemory() * 7),
    EIGHTGB(ONEGB.getMinMemory() * 8),
    NINEGB(ONEGB.getMinMemory() * 9),
    TENGB(ONEGB.getMinMemory() * 10);

    private final long minMem;

    HypeMemory(long minMem) {
        this.minMem = minMem;
    }

    public long getMinMemory() {
        return minMem;
    }

    public static long custom(int min) {
        return ONEGB.getMinMemory() * min;
    }
}
