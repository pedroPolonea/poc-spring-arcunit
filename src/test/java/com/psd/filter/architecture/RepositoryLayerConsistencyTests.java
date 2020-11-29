package com.psd.filter.architecture;


import com.psd.filter.config.ArchitectureConfig;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;


class RepositoryLayerConsistencyTests extends ArchitectureConfig {

    @Test
    void shouldTestConsistencyRepositoryLayer() {
        ArchRule rule = ArchRuleDefinition.classes()
                            .that()
                                .resideInAPackage("..repository..")
                            .should()
                                .onlyHaveDependentClassesThat()
                                .resideInAnyPackage("..service..");

        rule.check(importedClasses);
    }

    @Test
    void repositoryMustNotHaveClassesOfService() {
        ArchRule rule = ArchRuleDefinition.noClasses()
                            .that()
                                .resideInAPackage("..repository..")
                            .should()
                                .dependOnClassesThat()
                                .resideInAnyPackage("..service..");

        rule.check(importedClasses);
    }

    @Test
    void classWithSuffixRepositoryMustBeInTheHomonymousPackage() {
        ArchRule rule = ArchRuleDefinition.classes()
                            .that()
                                .haveSimpleNameEndingWith("Repository")
                            .should()
                                .resideInAPackage("..repository..");

        rule.check(importedClasses);
    }

    /* generic architectural tests */
    @Test
    void shouldReturnSuccessWhenThereIsNoCyclicalDependence() {
        ArchRule rule = SlicesRuleDefinition.slices()
                .matching("com.psd.filter.(*)..")
                .should()
                    .beFreeOfCycles();

        rule.check(importedClasses);
    }

    @Test
    void validateAccessHierarchyOfLayers() {
        ArchRule rule = Architectures.layeredArchitecture()
                .layer("Service").definedBy("..service..")
                .layer("Repository").definedBy("..repository..")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

        rule.check(importedClasses);
    }
}
