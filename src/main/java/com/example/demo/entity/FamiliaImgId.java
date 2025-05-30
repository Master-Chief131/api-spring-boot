package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FamiliaImgId implements Serializable {
    private Integer noCia;
    private Integer noFamilia;

    public FamiliaImgId() {}
    public FamiliaImgId(Integer noCia, Integer noFamilia) {
        this.noCia = noCia;
        this.noFamilia = noFamilia;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamiliaImgId that = (FamiliaImgId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noFamilia, that.noFamilia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noFamilia);
    }
}
