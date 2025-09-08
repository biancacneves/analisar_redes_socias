import java.util.*;

public class Grafo {
    // Lista de adjacência: ID do usuário -> Lista de IDs dos amigos
    private Map<Integer, List<Integer>> listaAdjacencia;
    
    // Mapa de usuários: ID -> Objeto User
    private Map<Integer, User> usuarios;
    
    // Construtor
    public Grafo() {
        this.listaAdjacencia = new HashMap<>();
        this.usuarios = new HashMap<>();
    }
    
    // Adicionar usuário ao grafo
    public void adicionarUsuario(User usuario) {
        int id = usuario.getId();
        usuarios.put(id, usuario);
        
        // Se não existe lista de adjacência, criar uma vazia
        if (!listaAdjacencia.containsKey(id)) {
            listaAdjacencia.put(id, new ArrayList<>());
        }
    }
    
    // Adicionar amizade (conexão bidirecional)
    public void adicionarAmizade(int id1, int id2) {
        // Verificar se os usuários existem
        if (!usuarios.containsKey(id1) || !usuarios.containsKey(id2)) {
            System.err.println("Erro: Um ou ambos os usuários não existem!");
            return;
        }
        
        // Evitar auto-amizade
        if (id1 == id2) {
            System.err.println("Erro: Usuário não pode ser amigo dele mesmo!");
            return;
        }
        
        // Adicionar conexão bidirecional
        if (!listaAdjacencia.get(id1).contains(id2)) {
            listaAdjacencia.get(id1).add(id2);
        }
        
        if (!listaAdjacencia.get(id2).contains(id1)) {
            listaAdjacencia.get(id2).add(id1);
        }
    }
    
    // Obter vizinhos (amigos) de um usuário
    public List<Integer> obterVizinhos(int id) {
        return listaAdjacencia.getOrDefault(id, new ArrayList<>());
    }
    
    // Obter usuário pelo ID
    public User obterUsuario(int id) {
        return usuarios.get(id);
    }
    
    // Obter todos os usuários
    public Collection<User> obterTodosUsuarios() {
        return usuarios.values();
    }
    
    // Obter todos os IDs
    public Set<Integer> obterTodosIds() {
        return usuarios.keySet();
    }
    
    // Obter número total de usuários
    public int obterNumeroUsuarios() {
        return usuarios.size();
    }
    
    // Obter número total de amizades (arestas)
    public int obterNumeroAmizades() {
        int total = 0;
        for (List<Integer> vizinhos : listaAdjacencia.values()) {
            total += vizinhos.size();
        }
        return total / 2; // Dividir por 2 porque cada amizade é contada duas vezes
    }
}
    
    