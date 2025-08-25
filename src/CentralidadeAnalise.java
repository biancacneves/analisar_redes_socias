import java.util.*;

public class CentralidadeAnalise {
    private Grafo grafo;
    
    public CentralidadeAnalise(Grafo grafo) {
        this.grafo = grafo;
    }
    
    // Centralidade de grau simples
    public int centralidadeGrau(int userId) {
        return grafo.obterVizinhos(userId).size();
    }
    
    // Análise básica de um usuário
    public void analisarUsuario(int userId) {
        User usuario = grafo.obterUsuario(userId);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        int grau = centralidadeGrau(userId);
        double percentual = (double) grau / (grafo.obterNumeroUsuarios() - 1) * 100;
        
        System.out.println("=== ANÁLISE DE CENTRALIDADE: " + usuario.getNome() + " ===");
        System.out.println("Centralidade de Grau: " + grau + " conexões");
        System.out.println("Percentual de conectividade: " + String.format("%.1f%%", percentual));
        
        // Classificação simples
        String classificacao;
        if (grau >= 4) classificacao = "MUITO CONECTADO";
        else if (grau >= 2) classificacao = "CONECTADO";
        else if (grau == 1) classificacao = "POUCO CONECTADO";
        else classificacao = "ISOLADO";
        
        System.out.println("Classificação: " + classificacao);
        System.out.println("=====================================");
    }
    
    // Relatório simples dos tops
    public void relatorioComparativo(int topN) {
        System.out.println("=== RELATÓRIO DE CENTRALIDADE (TOP " + topN + ") ===");
        
        // Criar lista de usuários com suas centralidades
        List<UsuarioRanking> usuarios = new ArrayList<>();
        for (Integer userId : grafo.obterTodosIds()) {
            User user = grafo.obterUsuario(userId);
            int grau = centralidadeGrau(userId);
            usuarios.add(new UsuarioRanking(userId, user.getNome(), grau));
        }
        
        // Ordenar por grau decrescente
        usuarios.sort((a, b) -> Integer.compare(b.getGrau(), a.getGrau()));
        
        System.out.println("\n🏆 TOP USUÁRIOS POR CONEXÕES:");
        for (int i = 0; i < Math.min(topN, usuarios.size()); i++) {
            UsuarioRanking user = usuarios.get(i);
            String emoji = i == 0 ? "🥇" : i == 1 ? "🥈" : "🥉";
            System.out.println(emoji + " " + user.getNome() + " - " + 
                             user.getGrau() + " conexões");
        }
        System.out.println("============================================");
    }
    
    // Ranking completo
    public List<UsuarioRanking> rankingCentralidadeGrau() {
        List<UsuarioRanking> ranking = new ArrayList<>();
        
        for (Integer userId : grafo.obterTodosIds()) {
            User user = grafo.obterUsuario(userId);
            int grau = centralidadeGrau(userId);
            ranking.add(new UsuarioRanking(userId, user.getNome(), grau));
        }
        
        // Ordenar por grau decrescente
        ranking.sort((a, b) -> Integer.compare(b.getGrau(), a.getGrau()));
        return ranking;
    }
    
    // Classe auxiliar simples
    public static class UsuarioRanking {
        private int userId;
        private String nome;
        private int grau;
        
        public UsuarioRanking(int userId, String nome, int grau) {
            this.userId = userId;
            this.nome = nome;
            this.grau = grau;
        }
        
        // Getters
        public int getUserId() { return userId; }
        public String getNome() { return nome; }
        public int getGrau() { return grau; }
        public int getValorInteiro() { return grau; } // Para compatibilidade com Main
    }
}