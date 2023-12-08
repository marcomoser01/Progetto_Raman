package com.fbk.rating.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.rating.domain.Rating;


// JpaRepository<Rating,Integer> indicando che manipolerà entità di tipo Rating
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	//nomenclatura di Spring Data JPA, che genera le query di accesso ai dati in base al nome del metodo. 

	// Restituisce una valutazione basata sull'ID specificato
	//Optional per gestire il caso in cui la valutazione non esista.
	Optional<Rating> findById(Integer id);
	// Restituisce una lista di valutazioni basate sull'ID dell'utente specificato
	List<Rating> findByUserId(Integer userId);
	// Restituisce una lista di valutazioni basate sull'ID del prodotto specificato
	List<Rating> findByProductId(Integer productId);
	// Restituisce una valutazione basata sull'ID del prodotto e dell'utente specificati
	Rating findByProductIdAndUserId(Integer productId, Integer userId);

}
