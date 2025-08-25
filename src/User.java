public class User {
    private int id;
    private String nome;
    
    // Construtor
    public User(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Construtor alternativo (só com ID)
    public User(int id) {
        this.id = id;
        this.nome = "Usuario" + id;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
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