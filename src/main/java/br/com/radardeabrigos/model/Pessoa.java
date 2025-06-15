package br.com.radardeabrigos.model;

import br.com.radardeabrigos.enums.StatusPessoa;
import jakarta.persistence.*;

@Entity
@Table(name = "T_RA_PESSOA")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String cpf;
    private String telefone;
    private int idade;
    private boolean possuiDeficiencia;
    private String tipoDeficiencia;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigoAtual;

    @Enumerated(EnumType.STRING)
    private StatusPessoa status;

    // Construtores
    public Pessoa() {
        this.status = StatusPessoa.AGUARDANDO_ABRIGO;
    }

    public Pessoa(String nome, String cpf, String telefone, int idade, boolean possuiDeficiencia) {
        this();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idade = idade;
        this.possuiDeficiencia = possuiDeficiencia;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public boolean isPossuiDeficiencia() { return possuiDeficiencia; }
    public void setPossuiDeficiencia(boolean possuiDeficiencia) { this.possuiDeficiencia = possuiDeficiencia; }

    public String getTipoDeficiencia() { return tipoDeficiencia; }
    public void setTipoDeficiencia(String tipoDeficiencia) { this.tipoDeficiencia = tipoDeficiencia; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public Abrigo getAbrigoAtual() { return abrigoAtual; }
    public void setAbrigoAtual(Abrigo abrigoAtual) { this.abrigoAtual = abrigoAtual; }

    public StatusPessoa getStatus() { return status; }
    public void setStatus(StatusPessoa status) { this.status = status; }
}