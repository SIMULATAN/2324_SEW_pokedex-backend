package me.simulatan.pokedex;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PokemonRepository {
	@Inject
	EntityManager entityManager;

	public List<Pokemon> findAll() {
		return entityManager.createQuery("SELECT p FROM Pokemon p", Pokemon.class).getResultList();
	}

	public List<Pokemon> findByType(PokeType type) {
		return entityManager
			.createQuery("SELECT p FROM Pokemon p WHERE p.primary = :type OR p.secondary = :type", Pokemon.class)
			.setParameter("type", type)
			.getResultList();
	}

	public Pokemon findById(Integer id) {
		return entityManager.find(Pokemon.class, id);
	}

	public Pokemon getRandomPokemon() {
		return entityManager.createQuery("SELECT p FROM Pokemon p ORDER BY random()", Pokemon.class)
			.setMaxResults(1)
			.getSingleResult();
	}

	@Transactional
	public void update(Pokemon pokemon) {
		entityManager.merge(pokemon);
	}
}