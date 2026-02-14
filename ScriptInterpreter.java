
public class ScriptInterpreter {

    private ScriptStack stack;

    public ScriptInterpreter() {
        this.stack = new ScriptStack();
    }

    public boolean execute(String script, boolean trace) {

        String[] tokens = script.split(" ");

        for (String token : tokens) {

            switch (token) {

                // ===== OP_0 =====

                case "OP_0":
                    stack.push(new byte[]{0});
                    break;

                // ===== OP_1 a OP_16 =====
                case "OP_1":
                    stack.push(new byte[]{1});
                    break;

                case "OP_2":
                    stack.push(new byte[]{2});
                    break;

                case "OP_3":
                    stack.push(new byte[]{3});
                    break;

                case "OP_4":
                    stack.push(new byte[]{4});
                    break;

                case "OP_5":
                    stack.push(new byte[]{5});
                    break;

                case "OP_6":
                    stack.push(new byte[]{6});
                    break;

                case "OP_7":
                    stack.push(new byte[]{7});
                    break;

                case "OP_8":
                    stack.push(new byte[]{8});
                    break;

                case "OP_9":
                    stack.push(new byte[]{9});
                    break;

                case "OP_10":
                    stack.push(new byte[]{10});
                    break;

                case "OP_11":
                    stack.push(new byte[]{11});
                    break;

                case "OP_12":
                    stack.push(new byte[]{12});
                    break;

                case "OP_13":
                    stack.push(new byte[]{13});
                    break;

                case "OP_14":
                    stack.push(new byte[]{14});
                    break;

                case "OP_15":
                    stack.push(new byte[]{15});
                    break;
                    
                case "OP_16":
                    stack.push(new byte[]{16});
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