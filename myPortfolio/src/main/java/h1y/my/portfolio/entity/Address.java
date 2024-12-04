package h1y.my.portfolio.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

	@Column(name = "M_ADRS")
	@Comment(value = "주소")
	private String address;
	
	@Column(name = "M_ADRS_DTL")
	@Comment(value = "상세 주소")
	private String addressDetail;
	
	@Column(name = "M_ZIPCODE")
	@Comment(value = "우편번호")
	private String zipcode;

	public Address(String address, String addressDetail, String zipcode) {
		super();
		this.address = address;
		this.addressDetail = addressDetail;
		this.zipcode = zipcode;
	}
	
}
