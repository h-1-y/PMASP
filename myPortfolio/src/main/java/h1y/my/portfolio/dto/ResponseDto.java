package h1y.my.portfolio.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ResponseDto {

	private final Boolean success;
	private final Integer code;
	private final String codeName;
	private final String message;
	
	public static ResponseDto of(Boolean success, int code, String codeName, String message) {
        return new ResponseDto(success, code, codeName, message);
    }
	
}
