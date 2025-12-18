package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exeption.InvalidInputException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int minLoginLength = 6;
    private static final int minPasswordLength = 6;
    private static final int minAge = 18;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new InvalidInputException("User can't be null");
        }

        if (storageDao.get(user.getLogin()) != null) {
            throw new InvalidInputException("User already created");
        }
        try {
            if (user.getLogin().length() < minLoginLength
                    || user.getLogin() == null) {
                throw new InvalidInputException("login must have 6 letters");
            }
        } catch (NullPointerException e) {
            throw new InvalidInputException("Login can't be null", e);
        }
        try {
            if (user.getPassword().length() < minPasswordLength
                    || user.getPassword() == null) {
                throw new InvalidInputException("Password must have 6 letters");
            }
        } catch (NullPointerException e) {
            throw new InvalidInputException("Password can't be null", e);
        }
        try {
            if (user.getAge() < minAge
                    || user.getAge() == null) {
                throw new InvalidInputException("Age must be 18 or older");
            }
        } catch (NullPointerException e) {
            throw new InvalidInputException("Age can't be null", e);
        }
        return storageDao.add(user);
    }
}
