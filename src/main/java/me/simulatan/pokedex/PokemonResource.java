package me.simulatan.pokedex;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import me.simulatan.pokedex.model.CaughtPokemon;
import me.simulatan.pokedex.model.PokeType;
import me.simulatan.pokedex.model.PokedexStats;
import me.simulatan.pokedex.model.Pokemon;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Path("/pokemon")
public class PokemonResource {
	@Inject
	PokemonRepository pokemonRepository;

	@GET
	@Path("list")
	public List<Pokemon> getList() {
		return pokemonRepository.findAll();
	}

	@GET
	@Path("caught")
	public List<Pokemon> getCaught() {
		return pokemonRepository.getCaught();
	}

	@GET
	@Path("list/{type}")
	public List<Pokemon> getByType(@PathParam("type") PokeType type) {
		return pokemonRepository.findByType(type);
	}

	@GET
	@Path("{id}")
	public Pokemon getById(@PathParam("id") Integer id) {
		Pokemon pokemon = pokemonRepository.findById(id);

		if (pokemon == null) {
			throw new NotFoundException();
		}

		return pokemon;
	}

	@POST
	@Path("{id}/catch")
	public Response tryCatch(@PathParam("id") Integer id) {
		// will throw NotFoundException if not found => not-null
		Pokemon pokemon = getById(id);

		if (ThreadLocalRandom.current().nextFloat() > (float) pokemon.getLevelMin() / pokemon.getLevelMax()) {
			// caught yay
			pokemon.incrementCatchCount();
			pokemonRepository.update(pokemon);
			Integer level = ThreadLocalRandom.current().nextInt(pokemon.getLevelMin(), pokemon.getLevelMax());
			return Response.ok(new CaughtPokemon(
				pokemon.getName(),
				level,
				pokemon.getPrimary(),
				pokemon.getSecondary(),
				pokemon.getCatchCount(),
				pokemon.getPictureUrl()
			)).build();
		}

		return Response.noContent().build();
	}

	@GET
	@Path("random")
	public Pokemon getRandomPokemon() {
		return pokemonRepository.getRandomPokemon();
	}

	@GET
	@Path("stats")
	public PokedexStats getStats() {
		return pokemonRepository.getStats();
	}
}
