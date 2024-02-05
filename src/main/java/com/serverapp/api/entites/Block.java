package com.serverapp.api.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "blocks")
@Data
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id1;
    private String blockType;
    private double dx;
    private double dy;
    private Integer value;
    private Integer input1;
    private Integer input2;
    private Integer output;

    // Bağlı olduğu blokların ID'leri
    @ElementCollection
    private List<Integer> connectedBlocks;

    // Bu bloğun ait olduğu proje
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;
}
