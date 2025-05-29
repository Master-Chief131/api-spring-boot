package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class SubfamiliaImgId implements Serializable {
    private Integer noCia;
    private Integer noFamilia;
    private Integer noSubfamilia;

    public SubfamiliaImgId() {}
    public SubfamiliaImgId(Integer noCia, Integer noFamilia, Integer noSubfamilia) {
        this.noCia = noCia;
        this.noFamilia = noFamilia;
        this.noSubfamilia = noSubfamilia;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubfamiliaImgId that = (SubfamiliaImgId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noFamilia, that.noFamilia) && Objects.equals(noSubfamilia, that.noSubfamilia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noFamilia, noSubfamilia);
    }
}
