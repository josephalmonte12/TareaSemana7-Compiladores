package codigo;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SymbolTable {
    private final Stack<Map<String, Symbol>> scopes;

    public SymbolTable() {
        this.scopes = new Stack<>();
        this.scopes.push(new HashMap<>()); // Alcance global
    }

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        if (!scopes.isEmpty()) {
            scopes.pop();
        } else {
            throw new IllegalStateException("Attempted to exit scope when no scopes are present.");
        }
    }

    public void addSymbol(String name, Symbol symbol) throws IllegalStateException {
        if (scopes.peek().containsKey(name)) {
            throw new IllegalStateException("Variable '" + name + "' is already declared in the current scope.");
        }
        scopes.peek().put(name, symbol);
    }

    public Symbol getSymbol(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Map<String, Symbol> scope = scopes.get(i);
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null; // No encontrado
    }

    // Puedes implementar funciones adicionales según sea necesario, como verificar tipos, etc.
}
