package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exeption.InvalidInputException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistrationServiceImplTest {

    private User userOk;
    private User userNotOk;
    private User registeredUser;
    private RegistrationServiceImpl registrationService;
    private StorageDao storageDao;

    @BeforeEach
    void setUp() {
        registrationService = new RegistrationServiceImpl();
        storageDao = new StorageDaoImpl();

        userOk = new User();
        userOk.setLogin("Loggin");
        userOk.setPassword("Password");
        userOk.setAge(18);

        userNotOk = new User();
        userNotOk.setLogin("Lgin");
        userNotOk.setPassword("Pword");
        userNotOk.setAge(14);

        registeredUser = new User();
        registeredUser.setLogin("Logginn");
        registeredUser.setPassword("Password");
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
    void register_nullUser_notOk() {
        assertThrows(InvalidInputException.class,
                () -> registrationService.register(null));
    }
}
