package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FamiliaId implements Serializable {
    private Integer noCia;
    private Integer noFamilia;

    public FamiliaId() {}
    public FamiliaId(Integer noCia, Integer noFamilia) {
        this.noCia = noCia;
        this.noFamilia = noFamilia;
    }
    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamiliaId that = (FamiliaId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noFamilia, that.noFamilia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noFamilia);
    }
}
