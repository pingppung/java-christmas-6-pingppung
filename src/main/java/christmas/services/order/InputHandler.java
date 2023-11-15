package christmas.services.order;

public class InputHandler {
    public InputHandler() {
    }

    public <T> T retryInputOnInvalid(InputSupplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    @FunctionalInterface
    public interface InputSupplier<T> {
        T get() throws IllegalArgumentException;
    }
}
