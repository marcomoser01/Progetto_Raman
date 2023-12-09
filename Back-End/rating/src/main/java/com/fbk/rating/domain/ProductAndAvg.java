package com.fbk.rating.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ProductAndAvg {
    private Product product;
    private Double avgVote;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getAvgVote() {
        return avgVote;
    }

    public void setAvgVote(Double avgVote) {
        this.avgVote = avgVote;
    }

    public ProductAndAvg() {}

    public ProductAndAvg(Product product, Double avgVote) {
        this.product = product;
        this.avgVote = avgVote;
    }

    public static List<ProductAndAvg> sortList(List<ProductAndAvg> productAndAvgList) {
        Collections.sort(productAndAvgList, new Comparator<ProductAndAvg>() {
            @Override
            public int compare(ProductAndAvg o1, ProductAndAvg o2) {
                // Ordina in modo decrescente in base al valore di avgVote
                return Double.compare(o2.getAvgVote(), o1.getAvgVote());
            }
        });

        return productAndAvgList;
    }

}
