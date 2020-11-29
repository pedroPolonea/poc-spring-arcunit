package com.psd.filter.architecture;


import com.psd.filter.config.ArchitectureConfig;
import com.psd.filter.service.ProductService;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;


class ServiceLayerConsistencyTests extends ArchitectureConfig {

    @Test
    void shouldTestConsistencyServiceLayer() {
        ArchRule rule = ArchRuleDefinition.classes()
                            .that()
                                .resideInAPackage("..service..")
                            .should()
                                .onlyHaveDependentClassesThat()
                                .resideInAnyPackage("..service..", "..resources..");

        rule.check(importedClasses);
    }

    @Test
    void repositoryMustNotHaveClassesOfResources() {
        ArchRule rule = ArchRuleDefinition.noClasses()
                            .that()
                                .resideInAPackage("..service..")
                            .should()
                                .dependOnClassesThat()
                                .resideInAnyPackage("..resources..");

        rule.check(importedClasses);
    }

    @Test
    void classWithSuffixRepositoryMustBeInTheHomonymousPackage() {
        ArchRule rule = ArchRuleDefinition.classes()
                            .that()
                                .haveSimpleNameEndingWith("Service")
                            .should()
                                .resideInAPackage("..service..");

        rule.check(importedClasses);
    }

    @Test
    void classesThatImplementProductServiceMustHaveTheSuffixServiceImpl() {
        ArchRule rule = ArchRuleDefinition.classes()
                            .that()
                                .implement(ProductService.class)
                            .should()
                                .haveSimpleNameEndingWith("ServiceImpl");

        rule.check(importedClasses);
    }

}
