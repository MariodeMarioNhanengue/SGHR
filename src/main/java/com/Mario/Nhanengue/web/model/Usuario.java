package com.Mario.Nhanengue.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidade-pai do modelo de utilizadores do SGHR (secção 8.2 e 8.3 do
 * documento de desenvolvimento).
 *
 * Concentra os atributos comuns a todas as contas da plataforma. As quatro
 * especializações diretas — Platform_SuperAdmin, Hotel_SuperAdmin,
 * Hotel_Admin e Cliente — herdam estes atributos através da estratégia de
 * herança JOINED: cada especialização terá a sua própria tabela, ligada a
 * "usuario" pela chave primária partilhada (usuario_id).
 *
 * Não existe um campo "tipo_usuario": o perfil de um utilizador é
 * determinado pela existência do respetivo registo numa das tabelas
 * especializadas (cada usuario_id existe, no máximo, numa delas).
 *
 * Esta classe é abstracta porque nunca deve ser instanciada nem persistida
 * diretamente — só as suas subclasses (especializações) representam contas
 * reais de utilizadores.
 *
 * Estende {@link AbstractEntity}, que fornece o id autogerado e o
 * equals/hashCode comuns a todas as entidades do sistema.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario extends AbstractEntity<Long> {

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    /** Chave de identificação do login (RN16 — deve ser único). */
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    /** Guardada sempre em hash, nunca em texto simples (ver secção 12.2). */
    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "telefone", length = 20)
    private String telefone;

    /** PENDENTE_VERIFICACAO / ATIVO / BLOQUEADO / INATIVO (RN18, RN19). */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private StatusUsuario status;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 80)
    private String nacionalidade;

    @Column(name = "cidade", length = 80)
    private String cidade;

    @Column(name = "pais", length = 80)
    private String pais;

    @Column(name = "foto")
    private String foto;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    protected Usuario() {
        // Construtor protegido: só as subclasses podem criar instâncias.
    }

    @PrePersist
    protected void aoCriar() {
        LocalDateTime agora = LocalDateTime.now();
        this.dataCriacao = agora;
        this.dataAtualizacao = agora;
        if (this.status == null) {
            this.status = StatusUsuario.PENDENTE_VERIFICACAO;
        }
    }

    @PreUpdate
    protected void aoAtualizar() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    // ----- Getters e setters -----
    // getId() já é fornecido por AbstractEntity.

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    // equals()/hashCode() já são fornecidos por AbstractEntity (baseados no id).

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id=" + getId() + ", nome='" + nome + "', email='" + email + "'}";
    }
}
