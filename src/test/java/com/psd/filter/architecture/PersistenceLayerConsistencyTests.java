package com.psd.filter.architecture;


import com.psd.filter.config.ArchitectureConfig;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;


class PersistenceLayerConsistencyTests extends ArchitectureConfig {

    @Test
    void shouldTestConsistencyPersistenceLayer() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                    .resideInAPackage("..repository..")
                .should()
                    .onlyHaveDependentClassesThat()
                    .resideInAnyPackage("..service..");

        rule.check(importedClasses);
    }
}
