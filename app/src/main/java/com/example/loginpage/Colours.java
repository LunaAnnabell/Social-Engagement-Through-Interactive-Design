package com.example.loginpage;

/**
 * Utility class for color manipulation.
 */
public class Colours {

    /**
     * Lightens the given ARGB color by blending it with white according to the specified factor.
     * The factor determines how much of the original color is preserved:
     * - A factor of 1 returns the original color (no change).
     * - A factor of 0 returns white.
     * Values between 0 and 1 produce progressively lighter colors.
     *
     * @param color  the original ARGB color integer
     * @param factor the lightening factor between 0 (white) and 1 (original color)
     * @return the lightened color as an ARGB integer
     */
    public static int lightenColor(int color, float factor) {
        int r = (int) (((color >> 16) & 0xFF) * factor + 255 * (1 - factor));
        int g = (int) (((color >> 8) & 0xFF) * factor + 255 * (1 - factor));
        int b = (int) ((color & 0xFF) * factor + 255 * (1 - factor));
        return (0xFF << 24) | (r << 16) | (g << 8) | b;
    }
}
