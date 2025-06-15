package br.com.radardeabrigos.model;

import br.com.radardeabrigos.enums.StatusAbrigo;
import br.com.radardeabrigos.enums.TipoAbrigo;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_RA_ABRIGO")
public class Abrigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String bairro;

    private String cep;
    private String telefoneContato;

    @Column(nullable = false)
    private int capacidadeTotal;

    private int numeroComodos;
    private boolean acessivel;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private TipoAbrigo tipo;

    @Enumerated(EnumType.STRING)
    private StatusAbrigo status;

    @OneToMany(mappedBy = "abrigoAtual", cascade = CascadeType.ALL)
    private List<Pessoa> pessoasAbrigadas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    // Construtores
    public Abrigo() {
        this.status = StatusAbrigo.ATIVO;
    }

    public Abrigo(String nome, String endereco, String cidade, String bairro, int capacidadeTotal) {
        this();
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.capacidadeTotal = capacidadeTotal;
    }

    // Métodos de negócio
    public int getVagasDisponiveis() {
        return capacidadeTotal - getPessoasAbrigadas().size();
    }

    public boolean temVagasDisponiveis() {
        return getVagasDisponiveis() > 0;
    }

    public boolean podeReceberPessoaComDeficiencia() {
        return acessivel && temVagasDisponiveis();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getTelefoneContato() { return telefoneContato; }
    public void setTelefoneContato(String telefoneContato) { this.telefoneContato = telefoneContato; }

    public int getCapacidadeTotal() { return capacidadeTotal; }
    public void setCapacidadeTotal(int capacidadeTotal) { this.capacidadeTotal = capacidadeTotal; }

    public int getNumeroComodos() { return numeroComodos; }
    public void setNumeroComodos(int numeroComodos) { this.numeroComodos = numeroComodos; }

    public boolean isAcessivel() { return acessivel; }
    public void setAcessivel(boolean acessivel) { this.acessivel = acessivel; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public TipoAbrigo getTipo() { return tipo; }
    public void setTipo(TipoAbrigo tipo) { this.tipo = tipo; }

    public StatusAbrigo getStatus() { return status; }
    public void setStatus(StatusAbrigo status) { this.status = status; }

    public List<Pessoa> getPessoasAbrigadas() { return pessoasAbrigadas; }
    public void setPessoasAbrigadas(List<Pessoa> pessoasAbrigadas) { this.pessoasAbrigadas = pessoasAbrigadas; }

    public Usuario getResponsavel() { return responsavel; }
    public void setResponsavel(Usuario responsavel) { this.responsavel = responsavel; }
}