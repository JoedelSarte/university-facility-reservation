package dlsud.response.sync;

import java.util.List;

import dlsud.entity.Equipment;
import dlsud.entity.Personnel;
import dlsud.entity.Rate;
import dlsud.utilities.AbstractResponse;

public class SyncResponse extends AbstractResponse {

	private List<Personnel> personnelList;
	
	private List<Rate> rateList;
	
	private List<Equipment> equipmentList;

	public List<Personnel> getPersonnelList() {
		return personnelList;
	}

	public void setPersonnelList(List<Personnel> personnelList) {
		this.personnelList = personnelList;
	}

	public List<Rate> getRateList() {
		return rateList;
	}

	public void setRateList(List<Rate> rateList) {
		this.rateList = rateList;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}
	
}
