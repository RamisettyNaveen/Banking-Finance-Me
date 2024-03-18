public class Account {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String accountNumber;
	    // Other account properties, getters, and setters
	}

	public class Policy {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String policyNumber;
	    private String accountNumber;
	    // Other policy properties, getters, and setters
	}
}