package me.simulatan.pokedex.model;

public class PokedexStats {
	Integer caught;

	public PokedexStats(Long caught) {
		this.caught = caught.intValue();
	}

	public Integer getCaught() {
		return caught;
	}
}
