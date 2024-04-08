package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal {
    public static void main(String[] args) throws Exception {
        // Asegúrate de que estas rutas reflejan la ubicación correcta de tus archivos flex y cup
        String rutaLexerFlex = "C:/Users/josep/OneDrive/Documents/NetBeansProjects/Tarea Semana 7/AnalizadorSintactico/src/codigo/Lexer.flex";
        String rutaLexerCupFlex = "C:/Users/josep/OneDrive/Documents/NetBeansProjects/Tarea Semana 7/AnalizadorSintactico/src/codigo/LexerCup.flex";
        String[] rutaSintaxCup = {"-parser", "Sintax", "C:/Users/josep/OneDrive/Documents/NetBeansProjects/Tarea Semana 7/AnalizadorSintactico/src/codigo/Sintax.cup"};
        
        generar(rutaLexerFlex, rutaLexerCupFlex, rutaSintaxCup);
    }

    public static void generar(String rutaLexerFlex, String rutaLexerCupFlex, String[] rutaSintaxCup) throws IOException, Exception {
        // Generar analizador léxico
        JFlex.Main.generate(new File(rutaLexerFlex));
        JFlex.Main.generate(new File(rutaLexerCupFlex));

        // Generar analizador sintáctico
        java_cup.Main.main(rutaSintaxCup);

        // Mover archivos sym.java y Sintax.java a la carpeta correcta
        moverArchivo("C:/Users/josep/OneDrive/Documents/NetBeansProjects/Tarea Semana 7/AnalizadorSintactico/sym.java",
                     "C:/Users/josep/OneDrive/Documents/NetBeansProjects/Tarea Semana 7/AnalizadorSintactico/src/codigo/sym.java");

        moverArchivo("C:/Users/josep/OneDrive/Documents/NetBeansProjects/Tarea Semana 7/AnalizadorSintactico/Sintax.java",
                     "C:/Users/josep/OneDrive/Documents/NetBeansProjects/Tarea Semana 7/AnalizadorSintactico/src/codigo/Sintax.java");
    }

    private static void moverArchivo(String origen, String destino) throws IOException {
        Path rutaOrigen = Paths.get(origen);
        Path rutaDestino = Paths.get(destino);
        if (Files.exists(rutaOrigen)) {
            Files.move(rutaOrigen, rutaDestino);
        }
    }
}
