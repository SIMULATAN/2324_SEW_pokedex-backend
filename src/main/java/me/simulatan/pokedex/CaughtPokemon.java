package me.simulatan.pokedex;

public class CaughtPokemon {
	String name;
	Integer level;
	PokeType typePrimary;
	PokeType typeSecondary;
	Integer catchCount;
	String pictureUrl;

	public CaughtPokemon(String name, Integer level, PokeType typePrimary, PokeType typeSecondary, Integer catchCount, String pictureUrl) {
		this.name = name;
		this.level = level;
		this.typePrimary = typePrimary;
		this.typeSecondary = typeSecondary;
		this.catchCount = catchCount;
		this.pictureUrl = pictureUrl;
	}

	public String getName() {
		return name;
	}

	public Integer getLevel() {
		return level;
	}

	public PokeType getTypePrimary() {
		return typePrimary;
	}

	public PokeType getTypeSecondary() {
		return typeSecondary;
	}

	public Integer getCatchCount() {
		return catchCount;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}
}
