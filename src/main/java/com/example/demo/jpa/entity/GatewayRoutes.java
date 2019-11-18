package com.example.demo.jpa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author zhenwei.wang 2019/7/25
 */
@Entity
@Table(name = "gateway_routes")
public class GatewayRoutes {
    private int id;
    private String predicates;
    private String filters;
    private String uri;
    private int order;
    private Timestamp gmtCreate;
    private Timestamp gmtModifiy;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "predicates", nullable = false, length = 300)
    public String getPredicates() {
        return predicates;
    }

    public void setPredicates(String predicates) {
        this.predicates = predicates;
    }

    @Basic
    @Column(name = "filters", nullable = true, length = 300)
    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    @Basic
    @Column(name = "uri", nullable = false, length = 200)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Basic
    @Column(name = "order", nullable = false)
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Basic
    @Column(name = "gmt_create", nullable = false)
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @Column(name = "gmt_modifiy", nullable = true)
    public Timestamp getGmtModifiy() {
        return gmtModifiy;
    }

    public void setGmtModifiy(Timestamp gmtModifiy) {
        this.gmtModifiy = gmtModifiy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GatewayRoutes that = (GatewayRoutes) o;
        return id == that.id &&
                order == that.order &&
                Objects.equals(predicates, that.predicates) &&
                Objects.equals(filters, that.filters) &&
                Objects.equals(uri, that.uri) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModifiy, that.gmtModifiy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, predicates, filters, uri, order, gmtCreate, gmtModifiy);
    }
}
