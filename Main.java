public class Main {

    public static void main(String[] args) {

        System.out.println("=== Bitcoin Script Interpreter (Fase Inicial) ===");

        ScriptInterpreter interpreter = new ScriptInterpreter();

        try {

            String script = "1 2 OP_DUP OP_SWAP";

            boolean result = interpreter.execute(script, true);

            if (result) {
                System.out.println("Script valido ✅");
            } else {
                System.out.println("Script invalido ❌");
            }

        } catch (ScriptException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("=== Fin de ejecucion ===");
    }
}
