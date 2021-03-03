package cc.kinami.wp.service.impl;

import cc.kinami.wp.service.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public void keepConnection(String name) {
        System.out.println(name);
    }
}
