package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exeption.InvalidInputException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();


    @Override
    public User register(User user) {
        if (storageDao.get(user.getLogin()) != null) {
            throw new InvalidInputException("User already created");
        }
        if (user.getLogin().length() < 6) {
            throw new InvalidInputException("login must have 6 letters");
        }
        if (user.getPassword().length() < 6) {
            throw new InvalidInputException("Password must have 6 letters");
        }
        if (user.getAge() < 18) {
            throw new InvalidInputException("Age must be 18 or older");
        }
        return user;
    }
}
