package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exeption.InvalidInputException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {

    private User userOk = new User();
    private User userNotOk = new User();
    private RegistrationServiceImpl registrationService =
            new RegistrationServiceImpl();
    private StorageDao storageDao = new StorageDaoImpl();
    private User registeredUser = new User();
    private User nullUser = new User();

    @BeforeEach
    void setUp() {
        userOk.setPassword("Password");
        userOk.setLogin("Loggin");
        userOk.setAge(18);

        userNotOk.setPassword("Pword");
        userNotOk.setLogin("Lgin");
        userNotOk.setAge(14);

        registeredUser.setPassword(null);
        registeredUser.setLogin("Logginn");
        registeredUser.setAge(18);
        storageDao.add(registeredUser);
    }

    @Test
    void register_validUser_ok() {
        registrationService.register(userOk);
        assertEquals(userOk, storageDao.get(userOk.getLogin()));

    }

    @Test
    void register_userWithInvalidData_notOk() {
        assertThrows(InvalidInputException.class,
                () -> registrationService.register(userNotOk));
    }

    @Test
    void register_existingUser_notOk() {
        assertThrows(InvalidInputException.class,
                () -> registrationService.register(registeredUser));
    }

    @Test
    void registerUser_withNullPassword_NotOk() {
        assertThrows(InvalidInputException.class,
                () -> registrationService.register(nullUser));
    }

    @Test
    void registerUser_withNullLogin_NotOk() {
        assertThrows(InvalidInputException.class,
                () -> registrationService.register(nullUser));
    }

    @Test
    void registerUser_withNullAge_NotOk() {
        assertThrows(InvalidInputException.class,
                () -> registrationService.register(nullUser));
    }
}
