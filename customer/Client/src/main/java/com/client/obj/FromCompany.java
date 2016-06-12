package com.client.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FromCompany {
	String bid_id;
	String message;
	int used;
}
