package com.github.bscordeiro.issura;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.core.ApplicationModules;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packagesOf = IssuraApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTests {

	private static final String ROOT_PACKAGE = "com.github.bscordeiro.issura";

	@ArchTest
	static final ArchRule applicationEntryPointStaysInRootPackage = classes().that()
		.areAnnotatedWith(SpringBootApplication.class)
		.should()
		.resideInAPackage(ROOT_PACKAGE);

	@ArchTest
	static final ArchRule globalTechnicalPackagesStayEmpty = noClasses().should()
		.resideInAnyPackage(ROOT_PACKAGE + ".controller..", ROOT_PACKAGE + ".mapper..", ROOT_PACKAGE + ".repository..",
				ROOT_PACKAGE + ".service..")
		.because("business code belongs to capability packages");

	@Test
	void moduleBoundariesRespectModulithRules() {
		ApplicationModules.of(IssuraApplication.class).verify();
	}

}
