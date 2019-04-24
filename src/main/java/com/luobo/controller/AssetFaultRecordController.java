package com.luobo.controller;

import com.luobo.annotation.SerializationFilter;
import com.luobo.entity.Asset;
import com.luobo.entity.AssetFaultRecord;
import com.luobo.service.AssetFaultRecordService;
import com.luobo.service.AssetService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Description: Date: 2019/4/24 10:00
 *
 * @author: ws
 * @version: 1.0
 */
@RequestMapping("/assetFaultRecord")
@RestController
public class AssetFaultRecordController {

	@Autowired
	AssetFaultRecordService assetFaultRecordService;

	@RequestMapping("/findAll")
	@SerializationFilter(target = Asset.class, exclusive = false, fields = { "id","recNo","state","assetId"})
	public Object findAll() {
		Map map = new HashMap();
		map.put("recordList",assetFaultRecordService.findAll());
		return map;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Object update(@RequestBody AssetFaultRecord assetFaultRecord) throws Exception {
		assetFaultRecordService.update(assetFaultRecord);
		return new String("200");
	}
}
