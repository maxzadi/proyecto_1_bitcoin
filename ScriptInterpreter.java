import java.nio.charset.StandardCharsets;

public class ScriptInterpreter {

    private ScriptStack stack;

    public ScriptInterpreter() {
        this.stack = new ScriptStack();
    }

    public boolean execute(String script, boolean trace) {

        String[] tokens = script.split(" ");

        for (String token : tokens) {

            switch (token) {

                // ===== LITERALES NUMÉRICOS =====
                case "0":
                    stack.push(new byte[]{0});
                    break;
                case "1":
                    stack.push(new byte[]{1});
                    break;
                case "2":
                    stack.push(new byte[]{2});
                    break;
                case "3":
                    stack.push(new byte[]{3});
                    break;

                // ===== OPERACIONES DE PILA =====
                case "OP_DUP":
                    opDup();
                    break;

                case "OP_DROP":
                    opDrop();
                    break;

                case "OP_SWAP":
                    opSwap();
                    break;

                case "OP_OVER":
                    opOver();
                    break;

                default:
                    throw new ScriptException("Opcode no soportado: " + token);
            }

            if (trace) {
                System.out.println("Ejecutado: " + token);
                stack.printStack();
            }
        }

        // Validación final
        if (!stack.isEmpty()) {
            byte[] top = stack.peek();
            return top[0] != 0;
        }

        return false;
    }

    // ===== OPCODES IMPLEMENTABLES =====

    private void opDup() {
        byte[] top = stack.peek();
        stack.push(top);
    }

    private void opDrop() {
        stack.pop();
    }

    private void opSwap() {
        byte[] first = stack.pop();
        byte[] second = stack.pop();
        stack.push(first);
        stack.push(second);
    }

    private void opOver() {
        byte[] first = stack.pop();
        byte[] second = stack.peek();
        stack.push(first);
        stack.push(second);
    }
}