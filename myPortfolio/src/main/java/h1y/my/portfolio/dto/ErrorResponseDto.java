package h1y.my.portfolio.dto;

public class ErrorResponseDto extends ResponseDto {

	public ErrorResponseDto(Integer code, String message) {
		super(false, code, message);
	}
	
	public static ErrorResponseDto of(Integer code,String message){
        return new ErrorResponseDto(code,message);
    }
	
}
