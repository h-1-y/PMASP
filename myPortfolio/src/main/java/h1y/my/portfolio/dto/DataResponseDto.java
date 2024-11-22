package h1y.my.portfolio.dto;

import lombok.Getter;

@Getter
public class DataResponseDto<T> extends ResponseDto {

	private final T data;
	
	private DataResponseDto(T data) {
        super(true, 200, "OK", "success");
        this.data = data;
    }

    private DataResponseDto(T data, String message) {
        super(true, 200, "OK", "success");
        this.data = data;
    }

    public static <T> DataResponseDto<T> of(T data) {
        return new DataResponseDto<>(data);
    }

    public static <T> DataResponseDto<T> of(T data, String message) {
        return new DataResponseDto<>(data, message);
    }

    public static <T> DataResponseDto<T> empty() {
        return new DataResponseDto<>(null);
    }
	
}
