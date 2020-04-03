package io.vincentpalazzo.ratio.model;

public enum RatioValue {

    FOR_NINE(1280, 720),

    SEXTEEN_NINE(1920, 1080),

    ONE_ONE(1080, 1080);

    private int width;
    private int height;

    RatioValue(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        String message;
        switch (this) {
            case SEXTEEN_NINE:
                message = "16:9";
                break;
            case FOR_NINE:
                message = "4:9";
                break;
            default:
                message = "1:1";
                break;
        }
        return message;
    }
}
