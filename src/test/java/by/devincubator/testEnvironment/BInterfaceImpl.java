package by.devincubator.testEnvironment;

import by.devincubator.annotation.Inject;

public class BInterfaceImpl implements BInterface {

    private AInterface aInterface;

    @Inject
    public BInterfaceImpl(AInterface aInterface){
        this.aInterface = aInterface;
    }


    @Override
    public void testB() {

    }

    public AInterface getaInterface() {
        return aInterface;
    }
}
