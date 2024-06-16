package me.simulatan.pokedex.model;

import jakarta.persistence.*;
import org.eclipse.microprofile.config.ConfigProvider;

@Entity
public class Pokemon {
	@Id
	private Integer id;
	@Column(name = "type_primary")
	@Enumerated(EnumType.STRING)
	private PokeType primary;
	@Column(name = "type_secondary")
	@Enumerated(EnumType.STRING)
	private PokeType secondary;
	private String name;
	@Column(name = "level_min")
	private int levelMin;
	@Column(name = "level_max")
	private int levelMax;
	@Column(name = "catch_count")
	private int catchCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PokeType getPrimary() {
		return primary;
	}

	public void setPrimary(PokeType primary) {
		this.primary = primary;
	}

	public PokeType getSecondary() {
		return secondary;
	}

	public void setSecondary(PokeType secondary) {
		this.secondary = secondary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevelMin() {
		return levelMin;
	}

	public void setLevelMin(int levelMin) {
		this.levelMin = levelMin;
	}

	public int getLevelMax() {
		return levelMax;
	}

	public void setLevelMax(int levelMax) {
		this.levelMax = levelMax;
	}

	public int getCatchCount() {
		return catchCount;
	}

	public void setCatchCount(int catchCount) {
		this.catchCount = catchCount;
	}

	public void incrementCatchCount() {
		// atomic operation
		this.catchCount++;
	}

	public String getPictureUrl() {
		return ConfigProvider.getConfig().getValue("pokedex.spritesTemplate", String.class)
			.replace("{name}", name.toLowerCase());
	}
}
