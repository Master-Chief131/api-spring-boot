package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class SubSubfamiliaId implements Serializable {
    private Integer noCia;
    private Integer noFamilia;
    private Integer noSubfamilia;
    private Integer noSubSubfamilia;

    public SubSubfamiliaId() {}
    public SubSubfamiliaId(Integer noCia, Integer noFamilia, Integer noSubfamilia, Integer noSubSubfamilia) {
        this.noCia = noCia;
        this.noFamilia = noFamilia;
        this.noSubfamilia = noSubfamilia;
        this.noSubSubfamilia = noSubSubfamilia;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubSubfamiliaId that = (SubSubfamiliaId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noFamilia, that.noFamilia) && Objects.equals(noSubfamilia, that.noSubfamilia) && Objects.equals(noSubSubfamilia, that.noSubSubfamilia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noFamilia, noSubfamilia, noSubSubfamilia);
    }
}
