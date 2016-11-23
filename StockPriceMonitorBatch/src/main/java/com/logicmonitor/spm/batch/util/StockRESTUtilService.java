package com.logicmonitor.spm.batch.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.logicmonitor.spm.batch.dto.StockDetailsDTO;
import com.logicmonitor.spm.batch.exception.InvalidJsonException;

/**
 * Utility to convert JSON data from the Stock information provider to
 * {@link StockDetailsDTO} object
 * 
 * @author Supraj
 *
 */
@Service
public final class StockRESTUtilService {

	/**
	 * Convert JSON input to list of {@link StockDetailsDTO} objects
	 * 
	 * @param jsonString
	 * 
	 * @return List of {@link StockDetailsDTO} objects
	 * 
	 * @throws InvalidJsonException
	 *             If the JSON returned from the stock provider was incorrect
	 */
	public List<StockDetailsDTO> convertMessageToObject(String jsonString) throws InvalidJsonException {
		List<StockDetailsDTO> stockDetailsList = new ArrayList<>();
		try {
			Gson gson = new Gson();
			JsonObject json = gson.fromJson(jsonString, JsonObject.class);
			JsonArray stockList = json.getAsJsonObject("query").getAsJsonObject("results").getAsJsonArray("quote");
			stockList.forEach(s -> {
				stockDetailsList.add(this.toStockDetails(s));
			});
		} catch (Exception e) {
			throw new InvalidJsonException();
		}

		return stockDetailsList;
	}

	/**
	 * Converts the company information in the json element to
	 * {@link StockDetailsDTO} object
	 * 
	 * @param element
	 * @return {@link StockDetailsDTO} object with company symbol, price and
	 *         date when the entry is added
	 */
	private StockDetailsDTO toStockDetails(JsonElement element) {
		JsonObject obj = element.getAsJsonObject();
		String symbol = obj.get("Symbol").getAsString();
		JsonElement priceElement = obj.get("LastTradePriceOnly");
		Double stockPrice = priceElement.isJsonNull() ? 0 : priceElement.getAsDouble();
		return new StockDetailsDTO(stockPrice, symbol);
	}

}
