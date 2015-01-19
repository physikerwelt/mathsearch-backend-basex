package com.formulasearchengine.backend.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.formulasearchengine.backend.rest.HelloResource;

@Machine
public class HelloResourceFactoryMachine extends SingleNameFactoryMachine<HelloResource> {
    public static final Name<HelloResource> NAME = Name.of(HelloResource.class, "HelloResource");

    public HelloResourceFactoryMachine() {
        super(0, new StdMachineEngine<HelloResource>(NAME, 0, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected HelloResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new HelloResource(

                );
            }
        });
    }

}
