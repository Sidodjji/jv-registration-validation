package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exeption.InvalidInputException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistrationServiceImplTest {

    private static User userOk = new User();
    private static User userNotOk = new User();
    private static RegistrationServiceImpl registrationService =
            new RegistrationServiceImpl();
    private static StorageDao storageDao = new StorageDaoImpl();
    private static User registeredUser = new User();

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
        assertThrows(InvalidInputException.class,
                () -> registrationService.register(userNotOk));
    }

    @Test
    void registeredUserNotOk() {
        assertThrows(InvalidInputException.class,
                () -> registrationService.register(registeredUser));
    }
}
