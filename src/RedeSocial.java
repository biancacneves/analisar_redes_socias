import java.util.*;

public class RedeSocial {
    private Grafo grafo;
    
    public RedeSocial(Grafo grafo) {
        this.grafo = grafo;
    }
    
    // Encontrar amigos em comum entre dois usuários
    public Set<Integer> amigosEmComum(int userId1, int userId2) {
        Set<Integer> amigos1 = new HashSet<>(grafo.obterVizinhos(userId1));
        Set<Integer> amigos2 = new HashSet<>(grafo.obterVizinhos(userId2));
        
        // Interseção dos conjuntos
        amigos1.retainAll(amigos2);
        return amigos1;
    }
    
    // Sugerir amigos para um usuário (amigos dos amigos)
    public List<Usuario> sugerirAmigos(int userId, int limite) {
        Set<Integer> amigosAtuais = new HashSet<>(grafo.obterVizinhos(userId));
        Map<Integer, Integer> candidatos = new HashMap<>(); // ID -> score (qtd amigos em comum)
        
        // Para cada amigo atual
        for (int amigoId : amigosAtuais) {
            // Para cada amigo do amigo
            for (int amigoDoAmigoId : grafo.obterVizinhos(amigoId)) {
                // Se não é ele mesmo e não é amigo direto
                if (amigoDoAmigoId != userId && !amigosAtuais.contains(amigoDoAmigoId)) {
                    candidatos.put(amigoDoAmigoId, candidatos.getOrDefault(amigoDoAmigoId, 0) + 1);
                }
            }
        }
        
        // Ordenar candidatos por score (mais amigos em comum primeiro)
        List<Map.Entry<Integer, Integer>> listaOrdenada = new ArrayList<>(candidatos.entrySet());
        listaOrdenada.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        // Converter para lista de usuários
        List<Usuario> sugestoes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : listaOrdenada) {
            if (sugestoes.size() >= limite) break;
            
            User usuario = grafo.obterUsuario(entry.getKey());
            sugestoes.add(new Usuario(usuario.getId(), usuario.getNome(), entry.getValue())); // score como terceiro parâmetro
        }
        
        return sugestoes;
    }
    
    // Encontrar usuários mais populares (maior número de amigos)
    public List<Usuario> usuariosMaisPopulares(int limite) {
        List<Usuario> usuarios = new ArrayList<>();
        
        for (User user : grafo.obterTodosUsuarios()) {
            int numAmigos = grafo.obterVizinhos(user.getId()).size();
            usuarios.add(new Usuario(user.getId(), user.getNome(), numAmigos));
        }
        
        // Ordenar por número de amigos (decrescente)
        usuarios.sort((a, b) -> b.getScore() - a.getScore());
        
        // Retornar apenas os primeiros 'limite'
        return usuarios.subList(0, Math.min(limite, usuarios.size()));
    }
    
    // Classe auxiliar para usuários com score
    public static class Usuario {
        private int id;
        private String nome;
        private int score; // Para ranking/score
        
        public Usuario(int id, String nome, int score) {
            this.id = id;
            this.nome = nome;
            this.score = score;
        }
        
        public int getId() { return id; }
        public String getNome() { return nome; }
        public int getScore() { return score; }
        
        @Override
        public String toString() {
            return nome + " (score: " + score + ")";
        }
    }
}