package com.hr.api.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public static Pageable prepareSortAndPaging(String column, String order, int page, int size) {
		Sort sort = Sort.by(column);
		if(order.equals("ASC")) {
			sort = sort.ascending();
		} else {
			sort = sort.descending();
		}
		PageRequest pageable = PageRequest.of(page, size, sort);
		return pageable;
	}
}
