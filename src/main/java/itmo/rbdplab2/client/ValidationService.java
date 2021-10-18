package itmo.rbdplab2.client;

import java.time.LocalDate;

public class ValidationService {

    public void checkLocalDate(String value, String paramName) throws BadRequestException {
        try {
            LocalDate.parse(value);
        } catch (Exception e) {
            throw new BadRequestException(String.format("%s should be a local date", paramName));
        }
    }

    public String checkNotEmptyString(String value, String paramName) throws BadRequestException {
        if (value == null || value.isEmpty()){
            throw new BadRequestException(String.format("%s should be a not empty string", paramName));
        }
        return value;
    }

    public Integer checkPositiveIntOrNull(String value, String paramName) throws BadRequestException {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer number;
        try {
            number = Integer.parseInt(value);
        }
        catch (Exception e) {
            throw new BadRequestException(String.format("%s should be a number", paramName));
        }
        if (number < 1) {
            throw new BadRequestException(String.format("%s should be >= 1", paramName));
        }
        return number;
    }

    public Integer checkInteger(String value,  String paramName) throws BadRequestException {
        Integer number;
        try {
            number = Integer.parseInt(value);
        }
        catch (Exception e) {
            throw new BadRequestException(String.format("%s should be a number", paramName));
        }
        return number;
    }

    public Integer checkIntegerNotNull(String value, String paramName) throws BadRequestException {
        Integer number;
        try {
            number = Integer.parseInt(value);
        }
        catch (Exception e) {
            throw new BadRequestException(String.format("%s should be a number", paramName));
        }
        if (number == null) {
            throw new BadRequestException(String.format("%s should be != null", paramName));
        }
        return number;
    }

    public Long checkLong(String value, String paramName) throws BadRequestException {
        Long number;
        try {
            number = Long.parseLong(value);
        }
        catch (Exception e) {
            throw new BadRequestException(String.format("%s should be a number", paramName));
        }
        return number;
    }

    public boolean checkBoolean(String value) {
        return Boolean.parseBoolean(value);
    }
}
