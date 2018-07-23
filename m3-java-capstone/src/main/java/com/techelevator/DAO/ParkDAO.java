package com.techelevator.DAO;

import java.util.List;

import com.techelevator.Object.Park;

public interface ParkDAO {

	List<Park> getParkList();
	
	Park getParkByID(String parkCode);
	
}
