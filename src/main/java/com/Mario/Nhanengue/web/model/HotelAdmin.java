package com.Mario.Nhanengue.web.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "hotel_admin")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class HotelAdmin extends Usuario {

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusUsuario status;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }
}
