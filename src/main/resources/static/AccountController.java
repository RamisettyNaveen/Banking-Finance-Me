public class AccountController {
	private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @PutMapping("/updateAccount/{accountNumber}")
    public ResponseEntity<Account> updateAccount(@PathVariable String accountNumber, @RequestBody Account account) {
        // Check if account exists
        Optional<Account> existingAccount = accountService.viewAccount(accountNumber);
        if (!existingAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Update the account
        account.setAccountNumber(accountNumber);
        Account updatedAccount = accountService.updateAccount(account);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/viewAccount/{accountNumber}")
    public ResponseEntity<Account> viewAccount(@PathVariable String accountNumber) {
        Optional<Account> account = accountService.viewAccount(accountNumber);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteAccount/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {
        // Check if account exists
        Optional<Account> existingAccount = accountService.viewAccount(accountNumber);
        if (!existingAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Delete the account
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.noContent().build();
    }
}