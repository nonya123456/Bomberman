package entity.base;

import exception.InvalidBombPlantingException;
import exception.InvalidLandMinePlantingException;

public interface Updatable {
	public abstract void update()
			throws InvalidBombPlantingException, InvalidLandMinePlantingException;
}
