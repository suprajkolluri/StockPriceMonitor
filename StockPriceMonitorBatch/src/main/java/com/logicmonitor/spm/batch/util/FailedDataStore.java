package com.logicmonitor.spm.batch.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.logicmonitor.spm.batch.dto.StockDetailsDTO;

@Component
public class FailedDataStore {

	private List<StockDetailsDTO> failedData = new ArrayList<>();

	public List<StockDetailsDTO> getFailedData() {
		return failedData;
	}

	public void setFailedData(List<StockDetailsDTO> failedData) {
		this.failedData = failedData;
	}
}
