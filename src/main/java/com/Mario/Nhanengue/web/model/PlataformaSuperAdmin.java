package com.Mario.Nhanengue.web.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Entity
@Table(name = "platform_superadmin")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class PlataformaSuperAdmin extends Usuario  {

	/**
	 * Especialização de {@link Usuario} para o perfil Platform SuperAdmin
	 * (secção 2.1 e 8.3 do documento de desenvolvimento).
	 *
	 * Representa o proprietário/administrador geral da plataforma: gestão
	 * administrativa global (contas, hotéis, acessos), sem acesso operacional
	 * a preços, quartos, disponibilidade ou reservas de nenhum hotel (RN20).
	 *
	 * Mapeada com JOINED inheritance: partilha a chave primária com "usuario"
	 * através da coluna "usuario_id" na tabela "platform_superadmin".
	 */
	    /** Data em que a conta passou a ter o perfil Platform SuperAdmin. */
	    @Column(name = "data_nomeacao", nullable = false)
	    private LocalDate dataNomeacao;

	    /** Auditoria de acesso administrativo (secção 12.6). */
	    @Column(name = "ultimo_acesso")
	    private LocalDateTime ultimoAcesso;

	    public PlataformaSuperAdmin() {
	        super();
	    }

	    @PrePersist
	    private void aoNomear() {
	        if (this.dataNomeacao == null) {
	            this.dataNomeacao = LocalDate.now();
	        }
	    }

	    public LocalDate getDataNomeacao() {
	        return dataNomeacao;
	    }

	    public void setDataNomeacao(LocalDate dataNomeacao) {
	        this.dataNomeacao = dataNomeacao;
	    }

	    public LocalDateTime getUltimoAcesso() {
	        return ultimoAcesso;
	    }

	    public void setUltimoAcesso(LocalDateTime ultimoAcesso) {
	        this.ultimoAcesso = ultimoAcesso;
	    }
	}

