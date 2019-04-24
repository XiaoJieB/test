package com.luobo.controller;

import com.luobo.annotation.SerializationFilter;
import com.luobo.entity.Asset;
import com.luobo.entity.Asset.Status;
import com.luobo.entity.dto.AssetDto;
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
 * . Description: Date: 2019/4/22 10:20
 *
 * @author: ws
 * @version: 1.0
 */
@RequestMapping("/asset")
@RestController
public class AssetController {

	@Autowired
	AssetService assetService;

	@RequestMapping("/findAll")
	@SerializationFilter(target = Asset.class, exclusive = false, fields = { "id","name","type","status"})
	public Object findAll() {
		Map map = new HashMap();
		map.put("assetList",assetService.findAll());
		return map;
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public Object save(@RequestBody AssetDto assetDto) {
		Long id = assetService.save(assetDto);
		Asset as = assetService.get(id);
		return as;
	}

	@RequestMapping(path = "/{assetId}", method = RequestMethod.GET)
	public Object get(@PathVariable Long assetId) {
		AssetDto assetDto= assetService.getDto(assetId);
		return assetDto;
	}

	@RequestMapping(path = "/{assetId}", method = RequestMethod.PUT)
	public Object update(@RequestBody Asset asset) throws Exception {
		assetService.update(asset);
		return new String("200");
	}

	@RequestMapping(path = "/{assetId}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable Long assetId) {
		assetService.delete(assetId);
		return new String("200");
	}

	@RequestMapping(path = "/updateStatus/{assetId}", method = RequestMethod.PUT)
	public Object updateStatus(@PathVariable Long assetId) throws Exception {
		assetService.updateStatus(assetId,Status.abnormal);
		return new String("200");
	}
}
