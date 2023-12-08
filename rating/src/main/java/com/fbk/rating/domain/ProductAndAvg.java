package com.fbk.rating.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductAndAvg {
    //due attributi
    private Product product;
    private Double avgVote;

    //metodi getter e setter
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

    //  (costruttore)
    public ProductAndAvg(Product product, Double avgVote) {
        this.product = product;
        this.avgVote = avgVote;
    }
    // ordinamento di una lista di oggetti ProductAndAvg in modo decrescente in base al valori
    public static List<ProductAndAvg> sortList(List<ProductAndAvg> productAndAvgList) {
        // (classe.metodo) Collection.sort metodo utilizzato per ordinare la lista 
        //new Comparator<ProductAndAvg> Viene creata un'istanza di un oggetto anonimo che implementa l'interfaccia  
        Collections.sort(productAndAvgList, new Comparator<ProductAndAvg>() {

            @Override//un metodo della classe sta effettivamente sovrascrivendo un metodo di una sua superclasse o di un'interfaccia che implementa
            // specifica come confrontare due oggetti 
            public int compare(ProductAndAvg o1, ProductAndAvg o2) {
                // Ordina in modo decrescente in base al valore di avgVote
                // valore negativo se O2 > O1 altrimenti se sono uguali il valore 0 (decrescente)
                return Double.compare(o2.getAvgVote(), o1.getAvgVote());
            }
        });

        return productAndAvgList;
    }

}
