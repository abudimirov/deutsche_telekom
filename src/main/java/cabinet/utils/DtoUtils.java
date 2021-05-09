package cabinet.utils;

import cabinet.model.dto.DTOEntity;
import org.modelmapper.ModelMapper;

/**
 * Class converter from entity to DTO and from DTO to entity
 */
public class DtoUtils {

    public static DTOEntity convertToDto(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public static Object convertToEntity(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }

}