package naero.naeroserver.exception;

import naero.naeroserver.exception.dto.ApiExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiExceptionAdvice {


	/* 설명. AuthService에서 비밀번호 불일치 시 발생하는 예외 상황 처리 */
	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(LoginFailedException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	
	/* 설명.
	 *  TokenProvider에서 토큰 유효성 검사용 메소드 정의 시 사용되며,
	 *  유효성 검사 메소드는 JwtFilter에서 토큰 유효성 검사 시 발생하는 예외 상황 처리
	 */
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(TokenException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ApiExceptionDTO(HttpStatus.UNAUTHORIZED, e.getMessage()));
				
	}
	
	/* 설명. 이메일이 중복됐을 때 발생하는 예외 상황 처리 */
	@ExceptionHandler(DuplicatedMemberEmailException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(DuplicatedMemberEmailException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	
	/*아이디 중복*/
	@ExceptionHandler(DuplicatedUsernameException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(DuplicatedUsernameException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	@ExceptionHandler(UpdateUserException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(UpdateUserException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}


}




