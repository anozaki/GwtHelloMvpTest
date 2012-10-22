package com.hellomvp.client.ui;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;


public class ConcordionTestRunner extends ConcordionRunner {

    public ConcordionTestRunner(Class<?> fixtureClass) throws InitializationError {
        super(fixtureClass);
    }
    
    @Override
    public void run(RunNotifier notifier) {
        // TODO Auto-generated method stub
        super.run(notifier);
    }

}
