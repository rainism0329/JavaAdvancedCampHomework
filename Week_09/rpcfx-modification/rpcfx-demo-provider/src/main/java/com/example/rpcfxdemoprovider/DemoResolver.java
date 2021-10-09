package com.example.rpcfxdemoprovider;

import com.example.rpcfxcore.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        try {
            return this.applicationContext.getBean(Class.forName(serviceClass));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("could not find service by class[" + serviceClass + "]");
        }
    }
}
