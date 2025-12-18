package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exeption.InvalidInputException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {

    static User userOk = new User();
    static User userNotOk = new User();
    RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
    static StorageDao storageDao = new StorageDaoImpl();
    static User registeredUser = new User();

    @BeforeAll
    static void beforeAll() {
        userOk.setId(1L);
        userOk.setPassword("Password");
        userOk.setLogin("Loggin");
        userOk.setAge(18);

        userNotOk.setId(1L);
        userNotOk.setPassword("Pword");
        userNotOk.setLogin("Lgin");
        userNotOk.setAge(14);

        registeredUser.setId(1L);
        registeredUser.setPassword("Password");
        registeredUser.setLogin("Logginn");
        registeredUser.setAge(18);

        storageDao.add(registeredUser);
    }

    @Test
    void registerUser_Ok() {
        assertNotNull(registrationService.register(userOk));
    }

    @Test
    void registerUser_NotOk() {
        assertThrows(InvalidInputException.class, () -> {
            registrationService.register(userNotOk);
        });
    }

    @Test
    void registeredUserNotOk() {
        assertThrows(InvalidInputException.class, () -> {
            registrationService.register(registeredUser);
        });
    }
}