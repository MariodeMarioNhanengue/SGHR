package com.Mario.Nhanengue.web.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Cliente extends Usuario {

    @Column(name = "data_registo_cliente")
    private LocalDate dataRegistoCliente;

    @Column(name = "documento")
    private String documento;

    public Cliente() {
    }

    public Cliente(LocalDate dataRegistoCliente, String documento) {
        this.dataRegistoCliente = dataRegistoCliente;
        this.documento = documento;
    }

    public LocalDate getDataRegistoCliente() {
        return dataRegistoCliente;
    }

    public void setDataRegistoCliente(LocalDate dataRegistoCliente) {
        this.dataRegistoCliente = dataRegistoCliente;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
