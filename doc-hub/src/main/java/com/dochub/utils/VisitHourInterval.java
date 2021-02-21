package com.dochub.utils;

import lombok.Getter;

@Getter
public enum VisitHourInterval {
	EIGHT_0(8, 0), //
	EIGHT_15(8, 15), //
	EIGHT_30(8, 30), //
	EIGHT_45(8, 45), //
	NINE_0(9, 0), //
	NINE_15(9, 15), //
	NINE_30(9, 30), //
	NINE_45(9, 45), //
	TEN_0(10, 0), //
	TEN_15(10, 15), //
	TEN_30(10, 30), //
	TEN_45(10, 45), //
	ELEVEN_0(11, 0), //
	ELEVEN_15(11, 15), //
	ELEVEN_30(11, 30), //
	ELEVEN_45(11, 45), //
	TWELVE_0(12, 0), //
	TWELVE_15(12, 15), //
	TWELVE_30(12, 30), //
	TWELVE_45(12, 45), //
	THIRTEEN_0(13, 0), //
	THIRTEEN_15(13, 15), //
	THIRTEEN_30(13, 30), //
	THIRTEEN_45(13, 45), //
	FOURTHEEN_0(14, 0), //
	FOURTHEEN_15(14, 15), //
	FOURTHEEN_30(14, 30), //
	FOURTHEEN_45(14, 45), //
	FIFTEEN_0(15, 0), //
	FIFTEEN_15(15, 15), //
	FIFTEEN_30(15, 30), //
	FIFTEEN_45(15, 45), //
	SIXTEEN_0(16, 0), //
	SIXTEEN_15(16, 15); //

	VisitHourInterval(int minutes, int hour) {
		this.hour = hour;
		this.minutes = 0;
	}

	private final int hour;
	private final int minutes;
	
}
