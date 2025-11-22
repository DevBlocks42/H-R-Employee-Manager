package com.hr.api.utils;

import org.springframework.data.domain.Sort;

public class SortUtils {
	public static Sort prepareSort(String column, String order) {
		Sort sort = Sort.by(column);
		if(order.equals("ASC")) {
			sort = sort.ascending();
		} else {
			sort = sort.descending();
		}
		return sort;
	}
}
