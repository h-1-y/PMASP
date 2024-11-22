package h1y.my.portfolio.dto;

public class ErrorResponseDto extends ResponseDto {

	public ErrorResponseDto(Integer code, String codeName, String message) {
		super(false, code, codeName, message);
	}
	
}
