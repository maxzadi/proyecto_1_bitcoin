import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class ScriptInterpreter {

    private ScriptStack stack;

    public ScriptInterpreter() {
        this.stack = new ScriptStack();
    }

    public boolean execute(String script, boolean trace) {

        String[] tokens = script.split(" ");

        for (String token : tokens) {

            switch (token) {

                // ===== OP_0 / OP_1 .. OP_16 =====
                case "OP_0":
                    stack.push(new byte[]{0});
                    break;

                case "OP_1": case "OP_2": case "OP_3": case "OP_4":
                case "OP_5": case "OP_6": case "OP_7": case "OP_8":
                case "OP_9": case "OP_10": case "OP_11": case "OP_12":
                case "OP_13": case "OP_14": case "OP_15": case "OP_16":
                    int value = Integer.parseInt(token.substring(3));
                    stack.push(new byte[]{(byte) value});
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

                // ===== COMPARACIONES =====
                case "OP_EQUAL":
                    opEqual();
                    break;

                case "OP_EQUALVERIFY":
                    opEqualVerify();
                    break;

                // ===== CRIPTO =====
                case "OP_HASH160":
                    opHash160();
                    break;

                case "OP_CHECKSIG":
                    opCheckSig();
                    break;

                default:
                    stack.push(token.getBytes(StandardCharsets.UTF_8));
            }

            if (trace) {
                System.out.println("Ejecutado: " + token);
                stack.printStack();
            }
        }

        if (!stack.isEmpty()) {
            byte[] top = stack.peek();
            return top[0] != 0;
        }

        return false;
    }

    // ===== OPCODES =====

    private void opDup() {
        byte[] top = stack.peek();
        stack.push(top);
    }

    private void opDrop() {
        stack.pop();
    }

    private void opSwap() {
        byte[] a = stack.pop();
        byte[] b = stack.pop();
        stack.push(a);
        stack.push(b);
    }

    private void opOver() {
        byte[] a = stack.pop();
        byte[] b = stack.peek();
        stack.push(a);
        stack.push(b);
    }

    private void opEqual() {
        byte[] a = stack.pop();
        byte[] b = stack.pop();
        stack.push(new byte[]{(byte) (Arrays.equals(a, b) ? 1 : 0)});
    }

    private void opEqualVerify() {
        opEqual();
        byte[] result = stack.pop();
        if (result[0] == 0) {
            throw new ScriptException("OP_EQUALVERIFY falló");
        }
    }

    private void opHash160() {
        try {
            byte[] data = stack.pop();

            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(data);

            byte[] hash160 = Arrays.copyOf(hash, 20);

            stack.push(hash160);

        } catch (Exception e) {
            throw new ScriptException("Error en OP_HASH160");
        }
    }

    private void opCheckSig() {
        byte[] pubKey = stack.pop();
        byte[] signature = stack.pop();

        boolean valid = signature.length > 0 && pubKey.length > 0;

        stack.push(new byte[]{(byte) (valid ? 1 : 0)});
    }
}
