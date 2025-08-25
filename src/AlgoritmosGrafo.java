import java.util.*;

public class AlgoritmosGrafo {
    
    // BFSc - Busca em Largura
    public static List<Integer> bfs(Grafo grafo, int origem, int destino) {
        Queue<Integer> fila = new LinkedList<>();
        Set<Integer> visitados = new HashSet<>();
        Map<Integer, Integer> pais = new HashMap<>();
        
        fila.offer(origem);
        visitados.add(origem);
        pais.put(origem, null);
        
        while (!fila.isEmpty()) {
            int atual = fila.poll();
            
            // Se chegou no destino, reconstrói o caminho
            if (atual == destino) {
                return reconstruirCaminho(pais, origem, destino);
            }
            
            // Visitar todos os vizinhos não visitados
            for (int vizinho : grafo.obterVizinhos(atual)) {
                if (!visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    pais.put(vizinho, atual);
                    fila.offer(vizinho);
                }
            }
        }
        
        // Não encontrou caminho
        return new ArrayList<>();
    }
    
    // BFS para calcular distância (número de passos)
    public static int bfsDistancia(Grafo grafo, int origem, int destino) {
        if (origem == destino) return 0;
        
        Queue<Integer> fila = new LinkedList<>();
        Set<Integer> visitados = new HashSet<>();
        Map<Integer, Integer> distancia = new HashMap<>();
        
        fila.offer(origem);
        visitados.add(origem);
        distancia.put(origem, 0);
        
        while (!fila.isEmpty()) {
            int atual = fila.poll();
            
            for (int vizinho : grafo.obterVizinhos(atual)) {
                if (!visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    distancia.put(vizinho, distancia.get(atual) + 1);
                    fila.offer(vizinho);
                    
                    // Se chegou no destino, retorna a distância
                    if (vizinho == destino) {
                        return distancia.get(vizinho);
                    }
                }
            }
        }
        
        // Não há caminho
        return -1;
    }
    
    // DFS - Busca em Profundidade (recursiva)
    public static List<Integer> dfs(Grafo grafo, int origem, int destino) {
        Set<Integer> visitados = new HashSet<>();
        List<Integer> caminho = new ArrayList<>();
        
        if (dfsRecursiva(grafo, origem, destino, visitados, caminho)) {
            return caminho;
        }
        
        // Não encontrou caminho
        return new ArrayList<>();
    }
    
    // DFS recursiva auxiliar
    private static boolean dfsRecursiva(Grafo grafo, int atual, int destino, 
                                       Set<Integer> visitados, List<Integer> caminho) {
        visitados.add(atual);
        caminho.add(atual);
        
        // Se chegou no destino
        if (atual == destino) {
            return true;
        }
        
        // Explorar vizinhos
        for (int vizinho : grafo.obterVizinhos(atual)) {
            if (!visitados.contains(vizinho)) {
                if (dfsRecursiva(grafo, vizinho, destino, visitados, caminho)) {
                    return true;
                }
            }
        }
        
        // Backtrack - remove do caminho se não levou ao destino
        caminho.remove(caminho.size() - 1);
        return false;
    }
    
    // DFS para encontrar todos os nós alcançáveis
    public static Set<Integer> dfsAlcancaveis(Grafo grafo, int origem) {
        Set<Integer> visitados = new HashSet<>();
        dfsRecursivaAlcancaveis(grafo, origem, visitados);
        return visitados;
    }
    
    private static void dfsRecursivaAlcancaveis(Grafo grafo, int atual, Set<Integer> visitados) {
        visitados.add(atual);
        
        for (int vizinho : grafo.obterVizinhos(atual)) {
            if (!visitados.contains(vizinho)) {
                dfsRecursivaAlcancaveis(grafo, vizinho, visitados);
            }
        }
    }
    
    // Função auxiliar para reconstruir caminho no BFS
    private static List<Integer> reconstruirCaminho(Map<Integer, Integer> pais, int origem, int destino) {
        List<Integer> caminho = new ArrayList<>();
        Integer atual = destino;
        
        // Reconstroi o caminho de trás para frente
        while (atual != null) {
            caminho.add(atual);
            atual = pais.get(atual);
        }
        
        // Inverte para ficar na ordem correta (origem -> destino)
        Collections.reverse(caminho);
        return caminho;
    }
    
    // Método utilitário para imprimir caminho
    public static void imprimirCaminho(Grafo grafo, List<Integer> caminho) {
        if (caminho.isEmpty()) {
            System.out.println("Nenhum caminho encontrado!");
            return;
        }
        
        System.out.print("Caminho: ");
        for (int i = 0; i < caminho.size(); i++) {
            User user = grafo.obterUsuario(caminho.get(i));
            System.out.print(user.getNome());
            
            if (i < caminho.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println(" (Passos: " + (caminho.size() - 1) + ")");
    }
    
}


