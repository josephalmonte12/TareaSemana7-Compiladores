/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

/**
 *
 * @author josep
 */
public class Symbol {
    
    private String name; // Nombre del símbolo
    private String type; // Tipo del símbolo (por ejemplo, int, String, etc.)
    private String scope; // Alcance del símbolo (global, local, etc.)

    // Constructor para inicializar los atributos del símbolo
    public Symbol(String name, String type, String scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

    // Métodos getter y setter para cada atributo
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    // Método toString para representar el símbolo como cadena de texto
    @Override
    public String toString() {
        return "Symbol{" + "name=" + name + ", type=" + type + ", scope=" + scope + '}';
    }
    
}
