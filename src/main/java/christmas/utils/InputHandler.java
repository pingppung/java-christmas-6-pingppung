package christmas.utils;

public class InputHandler {
    public InputHandler() {
    }

    public <T> T retryInputOnInvalid(InputSupplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 다시 입력해 주세요.");
            }
        }
    }

    @FunctionalInterface
    public interface InputSupplier<T> {
        T get() throws IllegalArgumentException;
    }
}
