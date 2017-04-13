package cz.aimtec.auxilium.object;

public class Member {

	private String domain;
	private String username;
	private String role;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDomainUsername() {
		return domain + "/" + username;
	}
	
	public String getDetails() {
		return domain + "/" + username + "(" + role + ")";
	}
}
