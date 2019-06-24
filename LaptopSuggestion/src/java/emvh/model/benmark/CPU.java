/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.model.benmark;

import java.io.Serializable;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class CPU implements Serializable {

    private int id;
    private String name;
    private int point;
    private int rank;

    public CPU() {
    }

    public CPU(String name, int point, int rank) {
        this.name = name;
        this.point = point;
        this.rank = rank;
    }

    public CPU(int id, String name, int point, int rank) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "CPU{" + "name=" + name + ", point=" + point + ", rank=" + rank + '}';
    }

}
