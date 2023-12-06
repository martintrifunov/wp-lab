package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserFullNameConverter implements AttributeConverter<UserFullName,String> {

    @Override
    public String convertToDatabaseColumn(UserFullName userFullname) {
        if (userFullname == null) {
            return null;
        }
        return userFullname.getName() + " " + userFullname.getSurname();

    }

    @Override
    public UserFullName convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        String[] parts = dbData.split(" ");
        UserFullName fullname = new UserFullName();
        fullname.setName(parts[0]);
        fullname.setSurname(parts[1]);
        return fullname;
    }
}