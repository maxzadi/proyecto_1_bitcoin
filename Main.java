public class Main {

    public static void main(String[] args) {

        System.out.println("=== Bitcoin Script Interpreter (Fase Inicial) ===");

        ScriptInterpreter interpreter = new ScriptInterpreter();

        try {

            String script = "1 2 OP_DUP OP_SWAP";

            boolean result = interpreter.execute(script, true);

            if (result) {
                System.out.println("Script válido ✅");
            } else {
                System.out.println("Script inválido ❌");
            }

        } catch (ScriptException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("=== Fin de ejecución ===");
    }
}
