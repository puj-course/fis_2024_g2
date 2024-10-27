import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        try {
            // Ruta del directorio de código fuente a analizar
            Path pathProyecto = Paths.get("C:\\Users\\SEBASTIAN\\OneDrive\\Escritorio\\fis_2024_g2\\src\\main\\java");

            // Recorrer todos los archivos .java del directorio
            Files.walk(pathProyecto)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> {
                        try (FileInputStream in = new FileInputStream(path.toFile())) {
                            JavaParser parserJava = new JavaParser();
                            // Parsear el archivo
                            CompilationUnit cu = parserJava.parse(in).getResult().orElseThrow();
                            // Visitar cada clase y método
                            cu.accept(new VisitanteFan(), null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase visitante para las clases y sus métodos
    private static class VisitanteFan extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(ClassOrInterfaceDeclaration cid, Void arg) {
            super.visit(cid, arg);
            String className = cid.getNameAsString();
            cid.findAll(MethodDeclaration.class).forEach(md -> {
                System.out.println("Clase: " + className);
                System.out.println("Método: " + md.getName());
                System.out.println("Complejidad fan-out/fan-in del método " + md.getName());

                // Mapa para contar la cantidad de veces que se llama a cada método
                Map<String, Integer> contadorPorMetodoLlamado = new HashMap<>();

                // Buscar todas las llamadas a métodos dentro del método actual
                md.findAll(MethodCallExpr.class).forEach(call -> {
                    String nombreMetodoLlamado = call.getNameAsString();
                    contadorPorMetodoLlamado.put(nombreMetodoLlamado,
                            contadorPorMetodoLlamado.getOrDefault(nombreMetodoLlamado, 0) + 1);
                });

                // Imprimir la cantidad de veces que se llama a cada método
                contadorPorMetodoLlamado.forEach((metodoLlamado, contador) ->
                        System.out.println("  Llama a: " + metodoLlamado + " - " + contador + " veces"));

                // Imprimir la sumatoria de métodos llamados
                int totalMetodosLlamados = contadorPorMetodoLlamado.values().stream().mapToInt(Integer::intValue).sum();
                System.out.println("  Total de métodos llamados: " + totalMetodosLlamados);
            });
        }
    }
}
