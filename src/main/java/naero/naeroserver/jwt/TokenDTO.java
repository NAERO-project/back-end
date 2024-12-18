package naero.naeroserver.jwt;

public class TokenDTO {

	//토큰 안에 더 넣어야할 정보가 있을 수 있음
	private String grantType;			// 토큰 타입
	private String userFullName; 			// 인증받은 회원 이름? 이름!(userFullName)
	private String accessToken; 		// 액세스 토큰
	private Long accessTokenExpiresIn;	// Long 형의 만료 시간
	
	public TokenDTO() {
	}
	public TokenDTO(String grantType, String memberName, String accessToken, Long accessTokenExpiresIn) {
		this.grantType = grantType;
		this.userFullName = memberName;
		this.accessToken = accessToken;
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}
	
	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getAccessTokenExpiresIn() {
		return accessTokenExpiresIn;
	}

	public void setAccessTokenExpiresIn(Long accessTokenExpiresIn) {
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}

	@Override
	public String toString() {
		return "TokenDTO{" +
				"grantType='" + grantType + '\'' +
				", memberName='" + userFullName + '\'' +
				", accessToken='" + accessToken + '\'' +
				", accessTokenExpiresIn=" + accessTokenExpiresIn +
				'}';
	}
}
