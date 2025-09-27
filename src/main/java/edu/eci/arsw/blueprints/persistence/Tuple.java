/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.eci.arsw.blueprints.persistence;

import java.util.Objects;

public class Tuple<T1, T2> {
    private final T1 elem1;
    private final T2 elem2;

    public Tuple(T1 e1, T2 e2) {
        this.elem1 = e1;
        this.elem2 = e2;
    }

    public T1 getElem1() { return elem1; }
    public T2 getElem2() { return elem2; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple<?, ?>)) return false;
        Tuple<?, ?> t = (Tuple<?, ?>) o;
        return Objects.equals(elem1, t.elem1) && Objects.equals(elem2, t.elem2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elem1, elem2);
    }
}
