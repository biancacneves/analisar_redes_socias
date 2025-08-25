import java.util.*;

public class DetectorComunidades {
    private Grafo grafo;
    
    public DetectorComunidades(Grafo grafo) {
        this.grafo = grafo;
    }
    
    // Detectar todas as comunidades (componentes conexas)
    public List<List<Integer>> detectarComunidades() {
        Set<Integer> visitados = new HashSet<>();
        List<List<Integer>> comunidades = new ArrayList<>();
        
        // Para cada usuário não visitado
        for (Integer userId : grafo.obterTodosIds()) {
            if (!visitados.contains(userId)) {
                // Encontrar todos os usuários conectados a este
                List<Integer> comunidade = new ArrayList<>();
                dfsComponente(userId, visitados, comunidade);
                comunidades.add(comunidade);
            }
        }
        
        return comunidades;
    }
    
    // DFS para encontrar uma componente conexa
    private void dfsComponente(int userId, Set<Integer> visitados, List<Integer> comunidade) {
        visitados.add(userId);
        comunidade.add(userId);
        
        // Visitar todos os vizinhos não visitados
        for (int vizinho : grafo.obterVizinhos(userId)) {
            if (!visitados.contains(vizinho)) {
                dfsComponente(vizinho, visitados, comunidade);
            }
        }
    }
    
    // Encontrar qual comunidade um usuário pertence
    public int encontrarComunidadeUsuario(int userId) {
        List<List<Integer>> comunidades = detectarComunidades();
        
        for (int i = 0; i < comunidades.size(); i++) {
            if (comunidades.get(i).contains(userId)) {
                return i;
            }
        }
        
        return -1; // Usuário não encontrado
    }
    
    // Verificar se dois usuários estão na mesma comunidade
    public boolean mesmaComunidade(int userId1, int userId2) {
        int comunidade1 = encontrarComunidadeUsuario(userId1);
        int comunidade2 = encontrarComunidadeUsuario(userId2);
        
        return comunidade1 != -1 && comunidade1 == comunidade2;
    }
    
    // Analisar estatísticas das comunidades
    public EstatisticasComunidades analisarComunidades() {
        List<List<Integer>> comunidades = detectarComunidades();
        
        if (comunidades.isEmpty()) {
            return new EstatisticasComunidades(0, 0, 0, 0.0);
        }
        
        int numComunidades = comunidades.size();
        int menorTamanho = comunidades.get(0).size();
        int maiorTamanho = comunidades.get(0).size();
        int somaTotal = 0;
        
        for (List<Integer> comunidade : comunidades) {
            int tamanho = comunidade.size();
            somaTotal += tamanho;
            
            if (tamanho < menorTamanho) menorTamanho = tamanho;
            if (tamanho > maiorTamanho) maiorTamanho = tamanho;
        }
        
        double tamanhoMedio = (double) somaTotal / numComunidades;
        
        return new EstatisticasComunidades(numComunidades, menorTamanho, maiorTamanho, tamanhoMedio);
    }
    
    // Mostrar todas as comunidades
    public void mostrarComunidades() {
        List<List<Integer>> comunidades = detectarComunidades();
        EstatisticasComunidades stats = analisarComunidades();
        
        System.out.println("=== ANÁLISE DE COMUNIDADES ===");
        System.out.println("Número total de comunidades: " + stats.getNumComunidades());
        System.out.println("Tamanho médio: " + String.format("%.1f", stats.getTamanhoMedio()));
        System.out.println("Menor comunidade: " + stats.getMenorTamanho() + " usuário(s)");
        System.out.println("Maior comunidade: " + stats.getMaiorTamanho() + " usuário(s)");
        System.out.println();
        
        for (int i = 0; i < comunidades.size(); i++) {
            List<Integer> comunidade = comunidades.get(i);
            System.out.print("Comunidade " + (i + 1) + " (" + comunidade.size() + " usuários): ");
            
            for (int j = 0; j < comunidade.size(); j++) {
                User usuario = grafo.obterUsuario(comunidade.get(j));
                System.out.print(usuario.getNome());
                if (j < comunidade.size() - 1) System.out.print(", ");
            }
            System.out.println();
        }
        
        // Verificar se a rede é totalmente conectada
        if (comunidades.size() == 1) {
            System.out.println("\n A rede é totalmente conectada!");
        } else {
            System.out.println("\n A rede possui " + comunidades.size() + " grupos isolados.");
        }
        
        System.out.println("==============================");
    }
    
    // Encontrar usuários isolados (sem amigos)
    public List<Integer> encontrarUsuariosIsolados() {
        List<Integer> isolados = new ArrayList<>();
        
        for (Integer userId : grafo.obterTodosIds()) {
            if (grafo.obterVizinhos(userId).isEmpty()) {
                isolados.add(userId);
            }
        }
        
        return isolados;
    }
    
    // Sugerir conexões para unir comunidades
    public void sugerirConexoesComunidades() {
        List<List<Integer>> comunidades = detectarComunidades();
        
        if (comunidades.size() <= 1) {
            System.out.println("Não há comunidades isoladas para conectar.");
            return;
        }
        
        System.out.println("\n -> SUGESTÕES PARA CONECTAR COMUNIDADES:");
        
        for (int i = 0; i < comunidades.size() - 1; i++) {
            List<Integer> com1 = comunidades.get(i);
            List<Integer> com2 = comunidades.get(i + 1);
            
            // Pegar um usuário de cada comunidade para sugerir conexão
            User user1 = grafo.obterUsuario(com1.get(0));
            User user2 = grafo.obterUsuario(com2.get(0));
            
            System.out.println("- Conectar " + user1.getNome() + 
                             " (Comunidade " + (i + 1) + ") com " + 
                             user2.getNome() + " (Comunidade " + (i + 2) + ")");
        }
    }
    
    // Classe para armazenar estatísticas
    public static class EstatisticasComunidades {
        private int numComunidades;
        private int menorTamanho;
        private int maiorTamanho;
        private double tamanhoMedio;
        
        public EstatisticasComunidades(int numComunidades, int menorTamanho, int maiorTamanho, double tamanhoMedio) {
            this.numComunidades = numComunidades;
            this.menorTamanho = menorTamanho;
            this.maiorTamanho = maiorTamanho;
            this.tamanhoMedio = tamanhoMedio;
        }
        
        // Getters
        public int getNumComunidades() { return numComunidades; }
        public int getMenorTamanho() { return menorTamanho; }
        public int getMaiorTamanho() { return maiorTamanho; }
        public double getTamanhoMedio() { return tamanhoMedio; }
    }
}