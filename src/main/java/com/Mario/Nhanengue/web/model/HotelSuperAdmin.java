package com.Mario.Nhanengue.web.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "hotel_superadmin")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class HotelSuperAdmin extends Usuario {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "hotel_id",
        nullable = false,
        unique = true
    )

    @Column(nullable = false)
    private LocalDate dataAssociacao;

    public LocalDate getDataAssociacao() {
        return dataAssociacao;
    }

    public void setDataAssociacao(LocalDate dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }
}
