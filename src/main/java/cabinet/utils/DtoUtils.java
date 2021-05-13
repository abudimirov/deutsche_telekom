package cabinet.utils;

import org.modelmapper.ModelMapper;

/**
 * Class converter from entity to DTO and from DTO to entity
 */
public class DtoUtils {

    public static <T> T convertToDto(Object obj, Class<T> mapper) {
        return new ModelMapper().map(obj, mapper);
    }

    public static <T> T convertToEntity(Class<T> obj, Object mapper) {
        return new ModelMapper().map(mapper, obj);
    }


}