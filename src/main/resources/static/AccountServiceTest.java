import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountServiceTest {


    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountService bankAccountService;

    @Test
    public void testCreateAccount() {
        // Mock data
        BankAccount newAccount = new BankAccount();
        newAccount.setAccountNumber("123456789");
        newAccount.setBalance(1000);

        // Mocking the behavior of repository
        Mockito.when(bankAccountRepository.save(newAccount)).thenReturn(newAccount);

        // Create account
        BankAccount createdAccount = bankAccountService.createAccount(newAccount);

        // Assertions
        assertEquals(newAccount.getAccountNumber(), createdAccount.getAccountNumber());
        assertEquals(newAccount.getBalance(), createdAccount.getBalance());
    }

    @Test
    public void testUpdateAccount() {
        // Mock data
        BankAccount existingAccount = new BankAccount();
        existingAccount.setAccountNumber("123456789");
        existingAccount.setBalance(1000);

        // Mocking the behavior of repository
        Mockito.when(bankAccountRepository.findByAccountNumber("123456789")).thenReturn(Optional.of(existingAccount));
        Mockito.when(bankAccountRepository.save(existingAccount)).thenReturn(existingAccount);

        // New account data
        BankAccount updatedAccountData = new BankAccount();
        updatedAccountData.setAccountNumber("123456789");
        updatedAccountData.setBalance(2000);

        // Update account
        BankAccount updatedAccount = bankAccountService.updateAccount("123456789", updatedAccountData);

        // Assertions
        assertEquals(updatedAccountData.getBalance(), updatedAccount.getBalance());
    }

    @Test
    public void testViewAccount() {
        // Mock data
        BankAccount existingAccount = new BankAccount();
        existingAccount.setAccountNumber("123456789");
        existingAccount.setBalance(1000);

        // Mocking the behavior of repository
        Mockito.when(bankAccountRepository.findByAccountNumber("123456789")).thenReturn(Optional.of(existingAccount));

        // View account
        Optional<BankAccount> account = bankAccountService.viewAccount("123456789");

        // Assertions
        assertTrue(account.isPresent());
        assertEquals(existingAccount.getAccountNumber(), account.get().getAccountNumber());
    }

    @Test
    public void testDeleteAccount() {
        // Mock data
        BankAccount existingAccount = new BankAccount();
        existingAccount.setAccountNumber("123456789");
        existingAccount.setBalance(1000);

        // Mocking the behavior of repository
        Mockito.when(bankAccountRepository.findByAccountNumber("123456789")).thenReturn(Optional.of(existingAccount));

        // Delete account
        boolean isDeleted = bankAccountService.deleteAccount("123456789");

        // Assertions
        assertTrue(isDeleted);
    }
}