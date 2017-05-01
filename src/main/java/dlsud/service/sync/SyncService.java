package dlsud.service.sync;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dlsud.entity.Equipment;
import dlsud.entity.Personnel;
import dlsud.entity.Rate;
import dlsud.repository.EquipmentRepository;
import dlsud.repository.PersonnelRepository;
import dlsud.repository.RateRepository;
import dlsud.request.sync.SyncRequest;
import dlsud.response.sync.SyncResponse;
import dlsud.utilities.AbstractService;

@Service
public class SyncService extends AbstractService<SyncRequest, SyncResponse> {
	
	private Log log;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	@Autowired
	private RateRepository rateRepository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	public SyncService() {
		log = LogFactory.getLog(SyncService.class);
	}

	@Override
	public Log getLog() {
		return log;
	}

	public SyncResponse handleRequest() {
		SyncResponse syncResponse = new SyncResponse();
		try{
			List<Personnel> personnels = (List<Personnel>) personnelRepository.findAll();
			List<Rate> rates = (List<Rate>) rateRepository.findAll();
			List<Equipment> equipments = (List<Equipment>) equipmentRepository.findAll();
			syncResponse.setCode(SyncResponse.CODE_SUCCESS);
			syncResponse.setRateList(rates);
			syncResponse.setPersonnelList(personnels);
			syncResponse.setEquipmentList(equipments);
		}catch(Exception e){
			syncResponse.setCode(SyncResponse.CODE_FAILED);
		}
		
		return syncResponse;
	}
	
	@Override
	public SyncResponse createResponse(String requestId) {
		return null;
	}

	@Override
	public void validateRequest(SyncRequest request) throws Exception {
		
	}

	@Override
	protected void handleRequest(String requestId, SyncRequest request, SyncResponse response) throws Exception {
		
	}

}
