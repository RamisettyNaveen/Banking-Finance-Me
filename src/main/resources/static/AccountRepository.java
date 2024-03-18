public interface AccountRepository extends JpaRepository<Account, Long> {
	    Optional<Account> findByAccountNumber(String accountNumber);
	}

	@Repository
	public interface PolicyRepository extends JpaRepository<Policy, Long> {
	    List<Policy> findByAccountNumber(String accountNumber);
	}
}