public class User {
    private int id;
    private String nome;
    
    // Construtor
    public User(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    // Métodos utilitários
    @Override
    public String toString() {
        return "User{id=" + id + ", nome='" + nome + "'}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}