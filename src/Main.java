import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ANALISADOR DE REDES SOCIAIS ===\n");
        
        // Criar e configurar a rede
        Grafo grafo = criarRedeExemplo();
        
        // Mostrar estrutura inicial
        mostrarEstatisticasBasicas(grafo);
        
        // Análise de algoritmos
        testarAlgoritmos(grafo);
        
        // Análise de funcionalidades sociais
        analisarRedeSocial(grafo);
        
        // Análise de comunidades
        analisarComunidades(grafo);
        
        // Análise de centralidade
        analisarCentralidade(grafo);
        
        // Resumo final
        mostrarResumoFinal();
    }
    
    private static Grafo criarRedeExemplo() {
        Grafo grafo = new Grafo();
        
        // Criar usuários
        User joao = new User(1, "João");
        User maria = new User(2, "Maria");
        User pedro = new User(3, "Pedro");
        User ana = new User(4, "Ana");
        User carlos = new User(5, "Carlos");
        User lucia = new User(6, "Lúcia"); // Usuário isolado
        
        // Adicionar ao grafo
        grafo.adicionarUsuario(joao);
        grafo.adicionarUsuario(maria);
        grafo.adicionarUsuario(pedro);
        grafo.adicionarUsuario(ana);
        grafo.adicionarUsuario(carlos);
        grafo.adicionarUsuario(lucia);
        
        // Criar conexões
        grafo.adicionarAmizade(1, 2); // João - Maria
        grafo.adicionarAmizade(1, 3); // João - Pedro
        grafo.adicionarAmizade(2, 4); // Maria - Ana
        grafo.adicionarAmizade(3, 4); // Pedro - Ana
        grafo.adicionarAmizade(4, 5); // Ana - Carlos
        
        System.out.println("Rede criada: 6 usuários, 5 conexões");
        System.out.println("Estrutura: João-Maria-Ana-Carlos");
        System.out.println("           João-Pedro-Ana");
        System.out.println("           Lúcia (isolada)\n");
        
        return grafo;
    }
    
    private static void mostrarEstatisticasBasicas(Grafo grafo) {
        System.out.println("=== ESTATÍSTICAS DA REDE ===");
        System.out.println("Usuários: " + grafo.obterNumeroUsuarios());
        System.out.println("Conexões: " + grafo.obterNumeroAmizades());
        System.out.println("Densidade: " + String.format("%.2f%%", calcularDensidade(grafo) * 100));
        System.out.println();
    }
    
    private static void testarAlgoritmos(Grafo grafo) {
        System.out.println("=== ALGORITMOS DE BUSCA ===");
        
        // Teste de caminho existente
        List<Integer> caminhoBFS = AlgoritmosGrafo.bfs(grafo, 1, 5);
        System.out.print("Caminho BFS (João → Carlos): ");
        AlgoritmosGrafo.imprimirCaminho(grafo, caminhoBFS);
        
        List<Integer> caminhoDFS = AlgoritmosGrafo.dfs(grafo, 1, 5);
        System.out.print("Caminho DFS (João → Carlos): ");
        AlgoritmosGrafo.imprimirCaminho(grafo, caminhoDFS);
        
        // Teste de caminho inexistente
        List<Integer> caminhoIsolado = AlgoritmosGrafo.bfs(grafo, 1, 6);
        System.out.println("Caminho para usuário isolado: " + 
                          (caminhoIsolado.isEmpty() ? "Não encontrado" : "Encontrado"));
        System.out.println();
    }
    
    private static void analisarRedeSocial(Grafo grafo) {
        System.out.println("=== ANÁLISE SOCIAL ===");
        
        RedeSocial rede = new RedeSocial(grafo);
        
        // Amigos em comum
        Set<Integer> amigosComuns = rede.amigosEmComum(1, 4);
        System.out.println("Amigos em comum (João-Ana): " + amigosComuns.size());
        
        // Top usuários
        System.out.println("\nUsuários mais conectados:");
        List<RedeSocial.Usuario> populares = rede.usuariosMaisPopulares(3);
        for (int i = 0; i < populares.size(); i++) {
            RedeSocial.Usuario u = populares.get(i);
            System.out.println((i+1) + ". " + u.getNome() + " (" + u.getScore() + " conexões)");
        }
        System.out.println();
    }
    
    private static void analisarComunidades(Grafo grafo) {
        System.out.println("=== ANÁLISE DE COMUNIDADES ===");
        
        DetectorComunidades detector = new DetectorComunidades(grafo);
        DetectorComunidades.EstatisticasComunidades stats = detector.analisarComunidades();
        
        System.out.println("Total de comunidades: " + stats.getNumComunidades());
        System.out.println("Tamanho médio: " + String.format("%.1f", stats.getTamanhoMedio()));
        System.out.println("Maior comunidade: " + stats.getMaiorTamanho() + " usuários");
        
        // Verificar conectividade
        if (stats.getNumComunidades() == 1) {
            System.out.println("Status: Rede totalmente conectada");
        } else {
            System.out.println("Status: " + stats.getNumComunidades() + " grupos isolados");
        }
        
        // Usuários isolados
        List<Integer> isolados = detector.encontrarUsuariosIsolados();
        if (!isolados.isEmpty()) {
            System.out.println("Usuários isolados: " + isolados.size());
        }
        System.out.println();
    }
    
    private static void analisarCentralidade(Grafo grafo) {
        System.out.println("=== ANÁLISE DE CENTRALIDADE ===");
        
        CentralidadeAnalise centralidade = new CentralidadeAnalise(grafo);
        
        // Encontrar usuário mais central
        List<CentralidadeAnalise.UsuarioRanking> ranking = centralidade.rankingCentralidadeGrau();
        
        System.out.println("Ranking por conexões:");
        for (int i = 0; i < Math.min(3, ranking.size()); i++) {
            CentralidadeAnalise.UsuarioRanking user = ranking.get(i);
            System.out.println((i+1) + ". " + user.getNome() + ": " + 
                             user.getGrau() + " conexões");
        }
        
        // Análise do usuário mais central
        if (!ranking.isEmpty()) {
            System.out.println("\nAnálise do usuário mais central:");
            centralidade.analisarUsuario(ranking.get(0).getUserId());
        }
        System.out.println();
    }
    
    private static void mostrarResumoFinal() {
        System.out.println("=======================================");
        System.out.println("ANÁLISE CONCLUÍDA COM SUCESSO");
        System.out.println("=======================================");
        System.out.println("Módulos testados:");
        System.out.println("- Estrutura de dados");
        System.out.println("- Algoritmos de busca (BFS/DFS)");
        System.out.println("- Funcionalidades sociais");
        System.out.println("- Detecção de comunidades");
        System.out.println("- Análise de centralidade");
        System.out.println("=======================================");
    }
    
    // Método auxiliar para calcular densidade da rede
    private static double calcularDensidade(Grafo grafo) {
        int n = grafo.obterNumeroUsuarios();
        int m = grafo.obterNumeroAmizades();
        
        if (n <= 1) return 0.0;
        return (2.0 * m) / (n * (n - 1));
    }
}