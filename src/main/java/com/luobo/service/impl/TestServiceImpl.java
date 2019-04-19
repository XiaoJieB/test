package com.luobo.service.impl;

import com.luobo.service.TestService;
import org.springframework.stereotype.Service;

/**
 * . Description: Date: 2019/3/8 16:53
 *
 * @author: ws
 * @version: 1.0
 */
@Service
public class TestServiceImpl implements TestService {
	public String test() {
		return "test";
	}
}
