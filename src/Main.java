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
        User clara   = new User(7, "Clara");
        User luan    = new User(8, "Luan");
        User bianca  = new User(9, "Bianca");
        User felipe  = new User(10, "Felipe");
        User sofia   = new User(11, "Sofia");
        User rafael  = new User(12, "Rafael");
        User isabela = new User(13, "Isabela");
        User thiago  = new User(14, "Thiago");
        User amanda  = new User(15, "Amanda");
        User vinicius = new User(16, "Vinícius");
        User julia   = new User(17, "Júlia");
        User gustavo = new User(18, "Gustavo");
        User aline   = new User(19, "Aline");
        User diego   = new User(20, "Diego");
        User renata  = new User(21, "Renata");
        User caio    = new User(22, "Caio");
        User fernanda = new User(23, "Fernanda");
        User bruno   = new User(24, "Bruno");
        User priscila = new User(25, "Priscila");
        User andre   = new User(26, "André");
        User natalia = new User(27, "Natália");
        User daniel  = new User(28, "Daniel");
        User carla   = new User(29, "Carla"); // Usuário isolado
        User henrique = new User(30, "Henrique"); // Usuário isolado
        
        // Adicionar ao grafo
        grafo.adicionarUsuario(joao);
        grafo.adicionarUsuario(maria);
        grafo.adicionarUsuario(pedro);
        grafo.adicionarUsuario(ana);
        grafo.adicionarUsuario(carlos);
        grafo.adicionarUsuario(lucia);
        grafo.adicionarUsuario(clara);
        grafo.adicionarUsuario(luan);
        grafo.adicionarUsuario(bianca);
        grafo.adicionarUsuario(felipe);
        grafo.adicionarUsuario(sofia);
        grafo.adicionarUsuario(rafael);
        grafo.adicionarUsuario(isabela);
        grafo.adicionarUsuario(thiago);
        grafo.adicionarUsuario(amanda);
        grafo.adicionarUsuario(vinicius);
        grafo.adicionarUsuario(julia);
        grafo.adicionarUsuario(gustavo);
        grafo.adicionarUsuario(aline);
        grafo.adicionarUsuario(diego);
        grafo.adicionarUsuario(renata);
        grafo.adicionarUsuario(caio);
        grafo.adicionarUsuario(fernanda);
        grafo.adicionarUsuario(bruno);
        grafo.adicionarUsuario(priscila);
        grafo.adicionarUsuario(andre);
        grafo.adicionarUsuario(natalia);
        grafo.adicionarUsuario(daniel);
        grafo.adicionarUsuario(carla);
        grafo.adicionarUsuario(henrique);
        
        // Criar conexões
        grafo.adicionarAmizade(1, 2); // João - Maria
        grafo.adicionarAmizade(1, 3); // João - Pedro
        grafo.adicionarAmizade(2, 4); // Maria - Ana
        grafo.adicionarAmizade(3, 4); // Pedro - Ana
        grafo.adicionarAmizade(4, 5); // Ana - Carlos
        grafo.adicionarAmizade(7, 8);   // Clara - Luan
        grafo.adicionarAmizade(8, 9);   // Luan - Bianca
        grafo.adicionarAmizade(9, 10);  // Bianca - Felipe
        grafo.adicionarAmizade(10, 11); // Felipe - Sofia
        grafo.adicionarAmizade(11, 12); // Sofia - Rafael

        grafo.adicionarAmizade(13, 14); // Isabela - Thiago
        grafo.adicionarAmizade(14, 15); // Thiago - Amanda
        grafo.adicionarAmizade(15, 16); // Amanda - Vinícius
        grafo.adicionarAmizade(16, 17); // Vinícius - Júlia
        grafo.adicionarAmizade(17, 18); // Júlia - Gustavo

        grafo.adicionarAmizade(19, 20); // Aline - Diego
        grafo.adicionarAmizade(21, 22); // Renata - Caio
        grafo.adicionarAmizade(23, 24); // Fernanda - Bruno
        grafo.adicionarAmizade(25, 26); // Priscila - André
        grafo.adicionarAmizade(27, 28); // Natália - Daniel

        // Ponte entre blocos
        grafo.adicionarAmizade(5, 15); 
                
        System.out.println("Rede criada: 30 usuários, 21 conexões");
        System.out.println("Estrutura:\n");
        System.out.println("Principais conexões:");
        // Rede unida pela ponte (Carlos-Amanda)
        System.out.println("João-Maria-Ana-Carlos");
        System.out.println("João-Pedro-Ana-Carlos");
        System.out.println("Carlos-Amanda-Thiago-Isabela");
        System.out.println("Carlos-Amanda-Vinícius-Júlia-Gustavo\n");
        System.out.println("Grupos isolados:");
        // Grupos que não têm pontes
        System.out.println("Clara-Luan-Bianca-Felipe-Sofia-Rafael");
        System.out.println("Aline-Diego");
        System.out.println("Renata-Caio");
        System.out.println("Fernanda-Bruno");
        System.out.println("Priscila-André");
        System.out.println("Natália-Daniel\n");
        // Usuários sem nenhuma conexão
        System.out.println("Usuários isolados:");
        System.out.println("Lúcia (isolada)");
        System.out.println("Carla (isolada)");
        System.out.println("Henrique (isolado)\n");
        
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
        
        // Teste 1: Caminho curto dentro do primeiro grupo (continua válido)
        System.out.print("Caminho BFS (João -> Carlos): ");
        List<Integer> caminhoBFS_curto = AlgoritmosGrafo.bfs(grafo, 1, 5);
        AlgoritmosGrafo.imprimirCaminho(grafo, caminhoBFS_curto);
        
        System.out.print("Caminho DFS (João -> Gustavo): ");
        List<Integer> caminhoDFS_longo = AlgoritmosGrafo.dfs(grafo, 1, 18);
        AlgoritmosGrafo.imprimirCaminho(grafo, caminhoDFS_longo);
        
        // Teste 4: Caminho inexistente entre comunidades diferentes (continua válido)
        System.out.print("Caminho BFS (João -> Clara): ");
        List<Integer> caminhoInexistente = AlgoritmosGrafo.bfs(grafo, 1, 7);
        // Pequena melhoria na mensagem de saída para clareza
        if (caminhoInexistente.isEmpty()) {
            System.out.println("Nenhum caminho encontrado!");
        } else {
            AlgoritmosGrafo.imprimirCaminho(grafo, caminhoInexistente);
        }
        System.out.println();
    }
    
    private static void analisarRedeSocial(Grafo grafo) {
        System.out.println("=== ANÁLISE SOCIAL ===");
        
        RedeSocial rede = new RedeSocial(grafo);
        
        // Teste de amigos em comum (continua válido, mas podemos adicionar um novo)
        System.out.print("Amigos em comum (João e Ana): ");
        Set<Integer> amigosComuns1 = rede.amigosEmComum(1, 4);
        // Melhoria na impressão para nomes
        amigosComuns1.forEach(id -> System.out.print(grafo.obterUsuario(id).getNome() + " "));
        System.out.println("(" + amigosComuns1.size() + ")");

        // ALTERAÇÃO: Novo teste em um grupo diferente
        System.out.print("Amigos em comum (Thiago e Vinícius): ");
        Set<Integer> amigosComuns2 = rede.amigosEmComum(14, 16);
        amigosComuns2.forEach(id -> System.out.print(grafo.obterUsuario(id).getNome() + " "));
        System.out.println("(" + amigosComuns2.size() + ")");

        // Top usuários (nenhuma alteração necessária, o resultado será diferente e correto)
        System.out.println("\nUsuários mais conectados:");
        List<RedeSocial.Usuario> populares = rede.usuariosMaisPopulares(3);
        for (int i = 0; i < populares.size(); i++) {
            RedeSocial.Usuario u = populares.get(i);
            String emoji = i == 0 ? "1." : i == 1 ? "2." : "3.";
            System.out.println(emoji + " " + u.getNome() + " (" + u.getScore() + " conexões)");
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