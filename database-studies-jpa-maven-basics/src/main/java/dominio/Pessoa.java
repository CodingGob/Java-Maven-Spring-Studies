package dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;

    public Pessoa (String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Pessoa (Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Integer getId () {
        return this.id;
    }

    public String getNome () {
        return this.nome;
    }

    public String getEmail () {
        return this.email;
    }

    @Override
    public String toString () {
        return "[id=" 
        + this.id + ", nome=" 
        + this.nome + ", email=" 
        + this.email + "]";
    }
}
