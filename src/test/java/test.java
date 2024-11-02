import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        try {
            // Ruta del directorio de código fuente a analizar
            Path pathProyecto = Paths.get("/home/valkyrie/Documents/Javeriana/sem4/FundamentosIngesoft/fis_2024_g2/src/main/java");

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
                            cu.accept(new VisitanteComplejidad(), null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            // Imprimir promedios finales
            VisitanteComplejidad.imprimirResultados();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase visitante para las clases y sus métodos
    private static class VisitanteComplejidad extends VoidVisitorAdapter<Void> {
        private static final List<Integer> contadorLineasPorClase = new ArrayList<>();
        private static final List<Integer> contadorLineasPorMetodo = new ArrayList<>();
        private static final Map<String, List<Integer>> lineasPromedioMetodoPorClase = new HashMap<>();
        private static final Map<String, Integer> complejidadCiclomaticaPorMetodo = new HashMap<>();
        private static final Map<String, List<Integer>> largoDeIdentificadoresPorClase = new HashMap<>();
        private static final Map<String, Integer> profundidadAnidadoCondicional = new HashMap<>();


        @Override
        public void visit(ClassOrInterfaceDeclaration cid, Void arg) {
            super.visit(cid, arg);
            String nombreClase = cid.getNameAsString();
            int contadorLineasClase = cid.getEnd().map(pos -> pos.line).orElse(0) - cid.getBegin().map(pos -> pos.line).orElse(0) + 1;
            contadorLineasPorClase.add(contadorLineasClase);
            lineasPromedioMetodoPorClase.put(nombreClase, new ArrayList<>());
            largoDeIdentificadoresPorClase.put(nombreClase, new ArrayList<>());

            // Calcular la longitud de los identificadores (nombres de atributos) de la clase
            cid.findAll(FieldDeclaration.class).forEach(field -> {
                field.getVariables().forEach(variable -> {
                    String typeName = field.getElementType().asString();
                    String variableName = variable.getNameAsString();
                    int identifierLength = typeName.length() + variableName.length();
                    largoDeIdentificadoresPorClase.get(nombreClase).add(identifierLength);
                });
            });

            cid.findAll(MethodDeclaration.class).forEach(md -> {
                System.out.println("Clase: " + nombreClase);
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

                // Contar líneas del método
                int contadorDeLineas = md.getEnd().map(pos -> pos.line).orElse(0) - md.getBegin().map(pos -> pos.line).orElse(0) + 1;
                contadorLineasPorMetodo.add(contadorDeLineas);
                lineasPromedioMetodoPorClase.get(nombreClase).add(contadorDeLineas);

                // Calcular complejidad ciclomatica
                int arcos = 0;
                int nodos = 0;
                int p = 1; // Asumimos un solo componente conectado

                // Contar nodos de decisión (if, for, while, do-while, switch)
                int decisiones = md.findAll(IfStmt.class).size() +
                        md.findAll(ForStmt.class).size() +
                        md.findAll(WhileStmt.class).size() +
                        md.findAll(DoStmt.class).size() +
                        md.findAll(SwitchStmt.class).size();
                nodos += decisiones;
                nodos += 1; // Nodo inicial del método

                // Contar arcos (cada nodo de decisión tiene un arco de entrada y uno o más de salida)
                arcos += md.findAll(IfStmt.class).size() * 2;
                arcos += md.findAll(ForStmt.class).size() * 2;
                arcos += md.findAll(WhileStmt.class).size() * 2;
                arcos += md.findAll(DoStmt.class).size() * 2;
                arcos += md.findAll(SwitchStmt.class).stream().mapToInt(s -> s.getEntries().size() + 1).sum();
                arcos += 1; // Arco del nodo inicial

                // Aplicar la fórmula de complejidad ciclomatica M = e - n + 2 * p
                int complejidadCiclomatica = arcos - nodos + 2 * p;
                complejidadCiclomaticaPorMetodo.put(md.getNameAsString(), complejidadCiclomatica);
                System.out.println("  Complejidad ciclomatica: " + complejidadCiclomatica);

                // Calcular la profundidad de anidado condicional
                int anidadosCondicionales = calcularAnidadoCondicional(md);
                profundidadAnidadoCondicional.put(md.getNameAsString(), anidadosCondicionales);
                System.out.println("  Profundidad máxima de anidamiento condicional: " + anidadosCondicionales);
            });
        }

    private int calcularAnidadoCondicional(IfStmt ifS) {
        return ifS.findAll(IfStmt.class, stmt -> stmt != ifS).size() + 1;
    }

    private int calcularAnidadoCondicional(MethodDeclaration md) {
        List<IfStmt> ifStatements = md.findAll(IfStmt.class);
        int cantidadAnidados = 0;
        for (IfStmt ifStmt : ifStatements) {
            int cantActual = calcularAnidadoCondicional(ifStmt);
            cantidadAnidados = Math.max(cantidadAnidados, cantActual);
        }
        return cantidadAnidados;
    }

        public static void imprimirResultados() {
            double promedioLineaClase = contadorLineasPorClase.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            double promedioLineaMetodo = contadorLineasPorMetodo.stream().mapToInt(Integer::intValue).average().orElse(0.0);

            System.out.println("\nComplejidad por longitud de codigo:");
            System.out.println("Promedio de líneas por clase: " + promedioLineaClase);

            lineasPromedioMetodoPorClase.forEach((nombreClase, lineas) -> {
                double promedioLineaMetodoClase = lineas.stream().mapToInt(Integer::intValue).average().orElse(0.0);
                System.out.println("Promedio de líneas por método en la clase " + nombreClase + ": " + promedioLineaMetodoClase);
            });

            System.out.println("Promedio general de líneas por método: " + promedioLineaMetodo);

            complejidadCiclomaticaPorMetodo.forEach((nombreMetodo, complejidad) ->
                    System.out.println("Complejidad ciclomatica del método " + nombreMetodo + ": " + complejidad));

            // Calcular e imprimir la longitud promedio de los identificadores por clase y en todo el programa
            largoDeIdentificadoresPorClase.forEach((nombreClase, longitud) -> {
                double longitudPromedioIdentificador = longitud.stream().mapToInt(Integer::intValue).average().orElse(0.0);
                System.out.println("Promedio de longitud de identificadores en la clase " + nombreClase + ": " + longitudPromedioIdentificador);
            });

            double promedioTotalLargoIdentificadores = largoDeIdentificadoresPorClase.values().stream()
                    .flatMap(List::stream)
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
            System.out.println("Promedio general de longitud de identificadores: " + promedioTotalLargoIdentificadores);

            // Imprimir la profundidad máxima de anidado condicional por método
            profundidadAnidadoCondicional.forEach((metodo, cantidad) ->
                    System.out.println("Profundidad de anidado condicional del metodo " + metodo + ": " + cantidad));
        }
    }
}
