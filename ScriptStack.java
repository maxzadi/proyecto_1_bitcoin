import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;

public class ScriptStack {

    private Deque<byte[]> stack;

    public ScriptStack() {
        this.stack = new ArrayDeque<>();
    }

    // Push
    public void push(byte[] data) {
        if (data == null) {
            throw new ScriptException("No se puede insertar null en la pila");
        }
        stack.push(data);
    }

    // Pop
    public byte[] pop() {
        if (stack.isEmpty()) {
            throw new ScriptException("Error: pila vacía");
        }
        return stack.pop();
    }

    // Peek
    public byte[] peek() {
        if (stack.isEmpty()) {
            throw new ScriptException("Error: pila vacía");
        }
        return stack.peek();
    }

    // Tamaño
    public int size() {
        return stack.size();
    }

    // Verificar si está vacía
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Limpiar pila
    public void clear() {
        stack.clear();
    }

    // Imprimir pila (modo trace)
    public void printStack() {
        System.out.println("Estado actual de la pila:");
        for (byte[] item : stack) {
            System.out.println(Arrays.toString(item));
        }
        System.out.println("---------------------------");
    }
}

