public class Command {
	private String name;
	private String description;

	public Command(String n, String d) {
		name = n;
		description = d;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
